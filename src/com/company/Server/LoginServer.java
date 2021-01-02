package com.company.Server;

import com.company.Model.AccountDAO;
import com.company.Model.AccountDTO;
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

public class LoginServer {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String userId = "jaewon";
    String pwd = "wlfkf132";
    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://mms.crgsa3qt3jqa.ap-northeast-2.rds.amazonaws.com/mms?user=jaewon&password=wlfkf132";
    static final int INSERT_ACCOUNT = 1, LOGIN = 2, LOGOUT = 3, CHAT_MESSAGE = 4;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private AccountDAO accountDAO;
    private ArrayList<MMSLoginThread> loginThreadList = new ArrayList<MMSLoginThread>();

    public static void main(String args[]){
        LoginServer server = new LoginServer();
        server.start();
    }

    Logger logger;
    public void start(){
        logger = Logger.getLogger(this.getClass().getName());
        try{
            serverSocket = new ServerSocket(7777);
            logger.info("LoginServer start");
            while(true){
                socket = serverSocket.accept();
                MMSLoginThread loginThread = new MMSLoginThread();
                loginThreadList.add(loginThread);
                loginThread.start();
            }
        } catch(Exception e){
            logger.info("[LoginServer]start() Exception 발생!!");
            e.printStackTrace();
        }
    }

    class MMSLoginThread extends Thread {
        String id;
        String pw;
        public boolean status = true;
        String msgString;
        Message msg = new Message();
        Gson gson = new Gson();
        AccountDAO dao = new AccountDAO();

        private BufferedReader inputMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        private PrintWriter outputMessage = new PrintWriter(socket.getOutputStream(),true);

        public MMSLoginThread() throws IOException {} // 상위클래스로 Exception 던져버리기~!
//    static final int INSERT_ACCOUNT = 1, LOGIN = 2, LOGOUT = 3, CHAT_MESSAGE = 4;
        public void run(){
            while(status){
                try {
                    msgString = inputMessage.readLine();
                    msg = gson.fromJson(msgString,Message.class);

                    switch(msg.getType()){
                        case INSERT_ACCOUNT:
                            AccountDTO account = new AccountDTO();
                            account.setIsStaff(true);
                            account.setId(msg.getId());
                            account.setPassword(msg.getPasswd());
                            account.setIsSupperUser(false);
                            account.setUserName(msg.getMsg());
                            account.setIsLogin(false);
                            dao.makeAccount(account);
                            break;
                        case LOGIN:
                            id = msg.getId();
                            pw = msg.getPasswd();
                            dao.setLogin(id,pw);
                            logger.info("id : " + id + " pw : " + pw);
                            break;
                        case LOGOUT:
                            dao.setLogout(msg.getId(),msg.getPasswd());
                            logger.info("id :" + id + " logout");
                            status = false;

                            break;
                        case CHAT_MESSAGE:
                            break;

                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }

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
            connection = DriverManager.getConnection(jdbcUrl, userId, pwd);
            System.out.println("데이터베이스 연결 성공");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            preparedStatement.close();
            if(resultSet!=null) resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
