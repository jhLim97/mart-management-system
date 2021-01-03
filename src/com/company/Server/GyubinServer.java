package com.company.Server;

import com.company.Model.AccountDAO;
import com.company.Model.AccountDTO;
import com.company.Model.CustomerDAO;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;


public class GyubinServer {
    Connection conn;
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;
    String userid = "jaewon";
    String pwd = "wlfkf132";
    String sql;
    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://mms.crgsa3qt3jqa.ap-northeast-2.rds.amazonaws.com/mms?user=jaewon&password=wlfkf132";


    // -------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------
    static final int INSERT_ACCOUNT = 1, LOGIN = 2, LOGOUT = 3, CHATTING = 4, NEWCUSTOMER = 8, UPDATECUSTOMER = 9, DELETECUSTOMER = 10, ERROR = 15;
    static final int ADD_PRODUCT =5;
    static final int UPDATE_PRODUCT =6;
    static final int DELETE_PRODUCT = 7;
    // -------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------

    private ServerSocket ss = null;
    private Socket s = null; // client를 받기 위한 매개체
    private CustomerDAO cdao = new CustomerDAO();

    // 연결된 client 스레드를 관리하는 ArrayList
    ArrayList<MMSThread> mmsThreadList = new ArrayList<MMSThread>();

    Logger logger; // 로거 객체 선언

    public void connectDB() {
        try {
            Class.forName(jdbcDriver);
            System.out.println("드라이버 로드 성공");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("데이터베이스 연결 준비...");
            conn = DriverManager.getConnection(jdbcUrl, userid, pwd);
            System.out.println("데이터베이스 연결 성공");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            pstmt.close();
            if(rs!=null) rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void start() {
        logger = Logger.getLogger(this.getClass().getName());

        try {
            ss = new ServerSocket(7777); // 임의의 포트번호를 통해 서버 소켓 생성
            logger.info("MultiChatServer start");

            while (true) {
                s = ss.accept();

                MMSThread chat = new MMSThread();
                mmsThreadList.add(chat);
                chat.start();
            }

        } catch (Exception e) {
            logger.info("[MultiChatServer]start() Exception 발생!!");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        GyubinServer multiChatServer = new GyubinServer();
        multiChatServer.start();
    }

    class MMSThread extends Thread {
        private boolean status = true;

        String msg;
        Message m = new Message();

        Gson gson = new Gson(); // Message 객체를 json 객체로 파싱하기 위한 Gson 객체 생성

        // 입출력 스트림
        private BufferedReader inMsg = new BufferedReader(new InputStreamReader(s.getInputStream()));
        private PrintWriter outMsg = new PrintWriter(s.getOutputStream(), true);

        public String id = null;

        public MMSThread() throws IOException {
        }

        public void run() {
            while (status) { // 상태 정보가 true 일경우 반복문을 돌며 수신된 메세지 처리
                try {
                    msg = inMsg.readLine(); // 메세지 수신
                    m = gson.fromJson(msg, Message.class); // Message 클래스로 매핑
                    String index[] = {};
                    switch(m.getType()) {
                        case CHATTING:
                            msgSendAll(gson.toJson(new Message("","",index[0] + " : " + index[1],CHATTING)));
                            break;

                        case NEWCUSTOMER:
                            index = m.getMsg().split("/");
                            connectDB();
                            pstmt = conn.prepareStatement(index[1]);
                            if(pstmt.executeUpdate() != 0) {
                                msgSendAll(gson.toJson(new Message("", "", index[0] + "님이 등록되었습니다.", NEWCUSTOMER)));
                            }
                            closeDB();
                            break;
                        case UPDATECUSTOMER:
                            connectDB();
                            pstmt = conn.prepareStatement(m.getMsg());
                            if(pstmt.executeUpdate() != 0) {
                                msgSendAll(gson.toJson(new Message("", "", "정보가 수정되었습니다.", UPDATECUSTOMER)));
                            }
                            closeDB();
                            break;
                        case DELETECUSTOMER:
                            connectDB();
                            pstmt = conn.prepareStatement(m.getMsg());
                            if(pstmt.executeUpdate() != 0) {
                                msgSendAll(gson.toJson(new Message("", "", "정보가 삭제되었습니다.", DELETECUSTOMER)));
                            }
                            closeDB();
                            break;
                        case INSERT_ACCOUNT:
//                            AccountDTO account = new AccountDTO();
//                            account.setIsStaff(true);
//                            account.setId(msg.getId());
//                            account.setPassword(msg.getPasswd());
//                            account.setIsSupperUser(false);
//                            account.setUserName(msg.getMsg());
//                            account.setIsLogin(false);
//                            dao.makeAccount(account);
                            connectDB();
                            pstmt = conn.prepareStatement(m.getMsg());
                            if(pstmt.executeUpdate() != 0) {
                                msgSendAll(gson.toJson(new Message("","","계정 생성 완료",INSERT_ACCOUNT)));
                            }
                            closeDB();
                            break;
                        case LOGIN:
                            connectDB();

                            pstmt = conn.prepareStatement(m.getMsg());
                            rs = pstmt.executeQuery();

                            if(rs.next()) {
                                AccountDAO adao = new AccountDAO();
                                adao.setLogin(m.getId(),m.getPasswd());
                                msgSendAll(gson.toJson(new Message(m.getId(), m.getPasswd(), m.getId() + "님이 로그인 하셨습니다.", LOGIN)));
                                msgSendAll(gson.toJson(new Message("","",m.getId() + "님이 접속하셨습니다.", CHATTING)));
                                logger.info("id : " + m.getId() + " pw : " + m.getPasswd());
                            }
                            closeDB();
                            break;
                        case LOGOUT:
                            connectDB();
                            AccountDAO adao = new AccountDAO();
                            adao.setLogout(m.getId(), m.getPasswd());
                            msgSendAll(gson.toJson(new Message(m.getId(), m.getPasswd(), m.getId() + "님이 로그아웃 하셨습니다.", LOGOUT)));
                            msgSendAll(gson.toJson(new Message("","",m.getId() + "님이 접속종료 하셨습니다.", CHATTING)));
                            logger.info("id :" + id + " logout");
                            closeDB();
                            break;
                        case ADD_PRODUCT:
                            connectDB();
                            pstmt = conn.prepareStatement(m.getMsg());
                            if (pstmt.executeUpdate() != 0) {
                                msgSendAll(gson.toJson(new Message("", "", "상품이 등록되었습니다.", ADD_PRODUCT)));
                                closeDB();
                            }

                            break; //Add

                        case UPDATE_PRODUCT:
                            connectDB();
                            pstmt = conn.prepareStatement(m.getMsg());
                            if (pstmt.executeUpdate() != 0) {
                                msgSendAll(gson.toJson(new Message("", "", "상품이 수정되었습니다.", UPDATE_PRODUCT)));
                                closeDB();
                            }
                            break;
                        case DELETE_PRODUCT:
                            connectDB();
                            System.out.println("삭제에 성공했나?");
                            pstmt =conn.prepareStatement(m.getMsg());
                            if( pstmt.executeUpdate() != 0 ){

                                msgSendAll(gson.toJson(new Message("", "", "상품이 삭제되었습니다.", DELETE_PRODUCT)));
                                closeDB();
                            }

                            break;
                    }

                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                    closeDB();
                    status = false;
                }

            }
            this.interrupt();
            logger.info(this.getName() + " 종료됨!!");
        }

        public void msgSendAll(String msg) {
            for (MMSThread ct : mmsThreadList) {
                ct.outMsg.println(msg);
            }
        }
    }
}

