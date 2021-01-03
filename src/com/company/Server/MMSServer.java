package com.company.Server;

import com.company.Model.AccountDAO;
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
import java.util.Collections;
import java.util.logging.Logger;


public class MMSServer {
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
    static final int ORDER = 11; // 주문 테이블 갱신
    static final int ORDERHISTORY = 16; // 주문 내역 테이블 갱신
    static final int PAYTRY = 17; // 구매 시도
    static final int ORDERCOMPLETE = 18; // 주문 성공 시
    static final int ORDERFAIL = 19; // 재고 부족으로 주문 실패 시..'
    static final int UPDATECUSTOMERPOINT = 20; // 구매완료 시 총 구매금액의 10% 갱신
    // -------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------

    private ServerSocket ss = null;
    private Socket s = null; // client를 받기 위한 매개체
    private CustomerDAO cdao = new CustomerDAO();

    int orderCode; // 주문 내역을 위한 변수

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
            Collections.synchronizedList(mmsThreadList); // List synchronized 준혁 추가 ...
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
        MMSServer multiChatServer = new MMSServer();
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
                    String str[] = {};
                    switch(m.getType()) {
                        case CHATTING:
                            msgSendAll(gson.toJson(new Message("","",str[0] + " : " + str[1],CHATTING)));
                            break;

                        case NEWCUSTOMER:
                            str = m.getMsg().split("/");
                            connectDB();
                            pstmt = conn.prepareStatement(str[1]);
                            if(pstmt.executeUpdate() != 0) {
                                msgSendAll(gson.toJson(new Message("", "", str[0] + "님이 등록되었습니다.", NEWCUSTOMER)));
                            }
                            closeDB();
                            break;
                        case UPDATECUSTOMER:
                            connectDB();
                            pstmt = conn.prepareStatement(m.getMsg());
                            System.out.println(m.getMsg());
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

                        case ORDER :
                            connectDB();
                            try {
                                pstmt = conn.prepareStatement(m.getMsg());
                                pstmt.executeUpdate();

                                // 가장 최신의 입력된 auto_increment 값 가져오기
                                // --> mysql에서는 해당 값을 컨넥션 별로 관리하므로 멀티 스레드 구현 시 race condition같은 문제를 걱정할 필요없음
                                // 즉, 락을 걸거나 트랜잭션을 구현할 필요가 X
                                sql = "select last_insert_id()";
                                pstmt = conn.prepareStatement(sql);
                                rs = pstmt.executeQuery();

                                if(rs.next()) orderCode = rs.getInt(1);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            closeDB();
                            break;

                        case PAYTRY :
                            connectDB();
                            str = m.getMsg().split("@");
                            int cnt = Integer.parseInt(str[1]); // 구매 예정인 즉, 업데이트 할 품목 개수
                            System.out.println("품목개수 : " + cnt);
                            boolean canBuy = true;
                            String str1[] = str[0].split("/");
                            for(int i=1; i<=cnt; i++) {
                                try {
                                    sql = "select * from Product where pr_code = ?";
                                    pstmt = conn.prepareStatement(sql);
                                    pstmt.setInt(1, Integer.parseInt(str1[i*3-1]));
                                    System.out.println("코드번호 : " + Integer.parseInt(str1[i*3-1]));
                                    rs = pstmt.executeQuery();

                                    if(rs.next()) {
                                        if(rs.getInt("amount") < Integer.parseInt(str1[i*3-2])) {
                                            System.out.println("품목별 재고 : " + rs.getInt("pr_code"));
                                            canBuy = false;
                                        }
                                    }
                                } catch (SQLException e){
                                    e.printStackTrace();
                                }
                            }

                            System.out.println(canBuy);

                            if(canBuy) {
                                for(int i=1; i<=cnt; i++) {
                                    try {
                                        sql = "update Product set amount = ? where pr_code = ?";
                                        pstmt = conn.prepareStatement(sql);
                                        pstmt.setInt(1, Integer.parseInt(str1[i*3-3]));
                                        pstmt.setInt(2, Integer.parseInt(str1[i*3-1]));
                                        System.out.println(Integer.parseInt(str1[i*3-3]) + "/" + Integer.parseInt(str1[i*3-2]) + "/" + Integer.parseInt(str1[i*3-1]));
                                        pstmt.executeUpdate();
                                    } catch (SQLException e){
                                        e.printStackTrace();
                                    }
                                }
                                msgSendAll(gson.toJson(new Message(m.getId(), "", "", ORDERCOMPLETE))); // 고객 아이디로 보내기
                            }
                            else msgSendAll(gson.toJson(new Message(m.getId(), "", "", ORDERFAIL))); // 고객 아이디로 보내기

                            System.out.println("서버에서 아이디는 : " + m.getId());
                            closeDB();
                            break;

                        case ORDERHISTORY :
                            connectDB();
                            str = m.getMsg().split("/");
                            try {
                                sql = str[0] + orderCode + str[1];
                                pstmt = conn.prepareStatement(sql);
                                pstmt.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            closeDB();
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


