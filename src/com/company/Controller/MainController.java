package com.company.Controller;

import com.company.Model.Message;
import com.google.gson.Gson;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
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


            m = new Message("v.id", "", "", 1); // 여기 내가 테스트용으로 임시 수정.. (임준)
            outMsg.println(gson.toJson(m));


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
                switch (m.getType()) {
                    case 4 :
                        ProgramManager.getInstance().getChattingView().refreshData(m.getMsg());
                        break;
                    //여기
                    case 5 :
                        try {
                            ProgramManager.getInstance().getPC().refreshData();}
                        catch(Exception e1){}
                        break;
                    case 6 :
                        try {
                            ProgramManager.getInstance().getPC().refreshData();}
                        catch(Exception e1){}
                        break;
                    case 7 :
                        try {
                            ProgramManager.getInstance().getPC().refreshData();}
                        catch(Exception e1){}
                        break;
                    case 8 :
                        ProgramManager.getInstance().getMainView().customerViewPanel.drawTextArea(m.getMsg());
                        break;
                    case 9 : break;
                    case 10 : break;
                    case 18 : // 주문 성공
                    {
                        System.out.println("클라이언트 체크 : " + "/" + m.getId());
                        if(m.getId().equals(ProgramManager.getInstance().id)) {
                            ProgramManager.getInstance().getOrderController().OrderItems(ProgramManager.getInstance().getShoppingView(), ProgramManager.getInstance().getShoppingController().getTotal()); // 주문, 주문 내역 쿼리 실행
                            ProgramManager.getInstance().getCC().savePoint(ProgramManager.getInstance().getShoppingView().txtPhone.getText(), (int)(ProgramManager.getInstance().getShoppingController().getTotal()*0.01)); // 고객 포인트 업데이트

                            try {
                                ProgramManager.getInstance().getPC().refreshData();} // 상품 화면 업데이트
                            catch(Exception e1){}

                            try {
                                ProgramManager.getInstance().getShoppingController().payComplete(ProgramManager.getInstance().getShoppingView());
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e2) {
                                e2.printStackTrace();
                            }

                            ProgramManager.getInstance().getShoppingView().repaint();
                        }
                        break;
                    }
                    case 19 : // 주문 실패
                    {
                        if(m.getId().equals(ProgramManager.getInstance().id)) {
                            try {
                                ProgramManager.getInstance().getShoppingController().payFailed(ProgramManager.getInstance().getShoppingView());
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e2) {
                                e2.printStackTrace();
                            }

                        }
                        break;
                    }
                }


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