package com.company.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangMinServer {

    private Logger logger;

    // Message 객체를 json 객체로 파싱하기 위한 Gson 객체 생성
    private Gson gson = new Gson();

    private Socket s;

    // 입출력 스트림
    private BufferedReader inMsg = null; // 따로 설정 안해도 되나?
    private PrintWriter outMsg = null;

    private boolean status;

    private Message m;

    private Thread thread;


    public void connectServer() {
        try {
            s = new Socket("127.0.0.1", 7777);
            logger.log(Level.INFO, "[Client]Server 연결 성공!!");
            inMsg = new BufferedReader(new InputStreamReader(s.getInputStream()));
            outMsg = new PrintWriter(s.getOutputStream(), true);

            m = new Message(v.id, "", "", "login");
            outMsg.println(gson.toJson(m));

            thread = new Thread(this);
            thread.start();

        } catch (Exception e) {
            logger.log(Level.WARNING, "[MultiChatUI]connectServer() Exception 발생!!");
            e.printStackTrace();
        }
    }

}
