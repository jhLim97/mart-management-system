package com.company.Server;

import com.company.Controller.ProgramManager;
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


public class ChangMinServer {

    // -------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------
    //여기

    static final int login = 1; // 여기에 타입 번호 맵핑한 후 임의로 작성 한 후 주석으로 남기기
    static final int ADD_PRODUCT =5;
    static final int UPDATE_PRODUCT =6;
    static final int DELETE_PRODUCT = 7;

    //여기
    // -------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------

    private ServerSocket ss = null;
    private Socket s = null; // client를 받기 위한 매개체
    private boolean status = true;



    Connection conn;
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;
    String userid = "jaewon";
    String pwd = "wlfkf132";
    String sql;
    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://mms.crgsa3qt3jqa.ap-northeast-2.rds.amazonaws.com/mms?user=jaewon&password=wlfkf132";






    // 연결된 client 스레드를 관리하는 ArrayList
    ArrayList<MMSThread> mmsThreadList = new ArrayList<MMSThread>();

    Logger logger; // 로거 객체 선언

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
        ChangMinServer multiChatServer = new ChangMinServer();
        multiChatServer.start();
    }

    class MMSThread extends Thread {

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

                    switch (m.getType()) {
                        case login:
                            break;

                        //여기

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

                        //여기/////////////////////////////////
                    }

                } catch(IOException | SQLException e){
                    e.printStackTrace();
                    status = false;
                }

            }

            this.interrupt();
            logger.info(this.getName() + " 종료됨!!");
        }
    }

    public void msgSendAll(String msg) {
        for (MMSThread ct : mmsThreadList) {
            ct.outMsg.println(msg);
        }
    }


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
}

