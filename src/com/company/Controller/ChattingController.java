package com.company.Controller;

import com.company.Model.Message;
import com.company.View.ChattingView;
import com.company.View.ViewManager;

public class ChattingController {
    ChattingView chattingView;

    public void exitChatting() {
        chattingView = ViewManager.getInstance().getChattingView();
        chattingView.setVisible(false);
    }

    public void sendTextMessage(String text) {
        ProgramManager.getInstance().getMainController().msgSend(new Message(ProgramManager.getInstance().id,"",text,4));
    }
}
