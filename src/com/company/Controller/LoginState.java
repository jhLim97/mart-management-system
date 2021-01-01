package com.company.Controller;

import com.company.View.LoginViewPanel;
import com.company.View.MainView;

import javax.swing.*;

public class LoginState implements State{

    LoginViewPanel loginViewPanel;
    MainView mainView;

    public void draw(){
        drawFrame();
        drawPanel();
        applyListener();
    }
    @Override
    public void drawFrame() {
        mainView = ProgramManager.getInstance().getMainView();
        mainView.drawView();
    }

    @Override
    public void drawPanel() {
        ProgramManager.getInstance().getMainView().drawLoginPanel();
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
