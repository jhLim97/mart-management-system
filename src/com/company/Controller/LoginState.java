package com.company.Controller;

import com.company.View.LoginViewPanel;
import com.company.View.MainView;

import javax.swing.*;

public class LoginState implements State{

    LoginViewPanel loginViewPanel;
    MainView mainView;

    public void draw(){
        ProgramManager.getInstance().getMainView().drawView();
        ProgramManager.getInstance().getMainView().drawLoginPanel();
        applyListener();
    }

    @Override
    public void drawFrame() {
    }

    @Override
    public void drawPanel() {
        mainView = ProgramManager.getInstance().getMainView();
        if(mainView.mainViewPanel != null ) mainView.mainViewPanel.setVisible(false);
        mainView.loginViewPanel.txtId.setText("");
        mainView.loginViewPanel.txtPw.setText("");
        mainView.loginViewPanel.setVisible(true);
    }

    @Override
    public void applyListener() {
        loginViewPanel = ProgramManager.getInstance().getMainView().loginViewPanel;
        mmsListener.getInstance().loginPanelListener(loginViewPanel);
    }

    @Override
    public void update() {

    }
}
