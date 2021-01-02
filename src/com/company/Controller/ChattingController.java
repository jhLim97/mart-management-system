package com.company.Controller;

import com.company.Model.Message;
import com.company.View.ChattingView;

public class ChattingController {
    ChattingView chattingView;

    public void exitChatting() {
        chattingView = ProgramManager.getInstance().getChattingView();
        chattingView.setVisible(false);
    }

    public void sendTextMessage(String text) {
        ProgramManager.getInstance().getMainController().msgSend(new Message("","",text,4));
    }
}
