package com.company.Controller;

import com.company.Model.Message;
import com.google.gson.Gson;

//import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController extends Thread {

    private Logger logger; // 모르면 사용한해도 될듯..

    // Message 객체를 json 객체로 파싱하기 위한 Gson 객체 생성
    private Gson gson = new Gson();

    private Socket s;

    // 입출력 스트림
    private BufferedReader inMsg = null; // 따로 설정 안해도 되나?
    private PrintWriter outMsg = null;

    private boolean status;

    private Message m;

    private Thread thread;

    public MainController() {
        // 로거 객체 초기화
        logger = Logger.getLogger(this.getClass().getName());

    }

    public void connectServer() {
        try {
            s = new Socket("127.0.0.1", 7777);
            logger.log(Level.INFO, "[Client]Server 연결 성공!!");
            inMsg = new BufferedReader(new InputStreamReader(s.getInputStream()));
            outMsg = new PrintWriter(s.getOutputStream(), true);

//            m = new Message(v.id, "", "", "login");
            //outMsg.println(gson.toJson(m));

            thread = new Thread(this);
            thread.start();

        } catch (Exception e) {
            logger.log(Level.WARNING, "[MultiChatUI]connectServer() Exception 발생!!");
            e.printStackTrace();
        }
    }

    // 각 컨트롤로에서 maincontroller의 객체를 사용해 msgSend
    public void msgSend(Message msg) {
        outMsg.println(gson.toJson(msg));
    }

    public void run() {
        String msg;
        status = true; // 종료 시 false 제어 되게끔하기...

        while (status) {
            try {
                msg = inMsg.readLine();
                m = gson.fromJson(msg, Message.class);

                // MultiChatDat 객체로 데이터 갱신
//                chatData.refreshData(m.getId() + ">" + m.getMsg() + "\n");

                // 커서를 현재 대화 메세지에 표시
                //v.msgOut.setCaretPosition(v.msgOut.getDocument().getLength());
            } catch (IOException e) {
                logger.log(Level.WARNING, "[MultiChatUI]메세지 스트림 종료!!");
            }
        }
        logger.info("[MultiChatUI]" + thread.getName() + " 메세지 수신 스레드 종료됨!!");
    }

    public static void main(String[] args) {
        ProgramManager manager = ProgramManager.getInstance();
        manager.getMainController().connectServer();
        manager.setLoginState();

    }
}