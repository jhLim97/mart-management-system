package com.company.Server;

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


public class JunhyukServer {

    Connection con;
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
    static final int LOGIN = 1; // 여기에 타입 번호 맵핑한 후 임의로 작성 한 후 주석으로 남기기
    static final int ORDER = 11; //오더 추가, 연관 : 오더, 오더히스토리, 상품, 고객
    static final int PRODUCTDELETE = 8;
    static final int ORDERHISTORY = 16;
    // -------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------

    private ServerSocket ss = null;
    private Socket s = null; // client를 받기 위한 매개체
    private boolean status = true;

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
            con = DriverManager.getConnection(jdbcUrl, userid, pwd);
            System.out.println("데이터베이스 연결 성공");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            pstmt.close();
            if(rs!=null) rs.close();
            con.close();
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
        GyubinServer multiChatServer = new GyubinServer();
//        multiChatServer.start();
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

                    switch(m.getType()) {
                        case LOGIN : System.out.println("로그인 성공!!") ;break; // 준혁 테스트 중
                        case ORDER : {
                            connectDB();
                            try {
                                pstmt = con.prepareStatement(m.getMsg());
                                pstmt.executeUpdate();

                                // 가장 최신의 입력된 auto_increment 값 가져오기
                                // --> mysql에서는 해당 값을 컨넥션 별로 관리하므로 멀티 스레드 구현 시 race condition같은 문제를 걱정할 필요없음
                                // 즉, 락을 걸거나 트랜잭션을 구현할 필요가 X
                                sql = "select last_insert_id()";
                                pstmt = con.prepareStatement(sql);
                                rs = pstmt.executeQuery();

                                if(rs.next()) orderCode = rs.getInt(1);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            closeDB();
                            break;
                        }
                        case PRODUCTDELETE : {
                            connectDB();
                            String str[] = m.getMsg().split("@");
                            int cnt = Integer.parseInt(str[1]); // 구매 예정인 즉, 업데이트 할 품목 개수
                            System.out.println("품목개수 : " + cnt);
                            boolean canBuy = true;
                            String str1[] = str[0].split("/");
                            for(int i=1; i<=cnt; i++) {
                                try {
                                    sql = "select * from Product where pr_code = ?";
                                    pstmt = con.prepareStatement(sql);
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
                                        pstmt = con.prepareStatement(sql);
                                        pstmt.setInt(1, Integer.parseInt(str1[i*3-3]));
                                        pstmt.setInt(2, Integer.parseInt(str1[i*3-1]));
                                        System.out.println(Integer.parseInt(str1[i*3-3]) + "/" + Integer.parseInt(str1[i*3-2]) + "/" + Integer.parseInt(str1[i*3-1]));
                                        pstmt.executeUpdate();
                                    } catch (SQLException e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                            //else msgSendAll();

                            closeDB();
                            break;
                        }
                        case ORDERHISTORY : {
                            connectDB();
                            String str[] = m.getMsg().split("/");
                            try {
                                sql = str[0] + orderCode + str[1];
                                pstmt = con.prepareStatement(sql);
                                pstmt.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            closeDB();
                            break;
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
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

