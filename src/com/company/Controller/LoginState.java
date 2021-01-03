package com.company.Controller;

import com.company.View.LoginViewPanel;
import com.company.View.MainView;
import com.company.View.ViewManager;

import javax.swing.*;

public class LoginState implements State{

    LoginViewPanel loginViewPanel;
    MainView mainView;

    public void draw(){
        ViewManager.getInstance().getMainView().drawView();
        ViewManager.getInstance().getMainView().drawLoginPanel();
        applyListener();
    }

    @Override
    public void drawFrame() {
    }

    @Override
    public void drawPanel() {
        mainView = ViewManager.getInstance().getMainView();
        if(mainView.mainViewPanel != null ) mainView.mainViewPanel.setVisible(false);
        mainView.loginViewPanel.txtId.setText("");
        mainView.loginViewPanel.txtPw.setText("");
        mainView.loginViewPanel.setVisible(true);
    }

    @Override
    public void applyListener() {
        loginViewPanel = ViewManager.getInstance().getMainView().loginViewPanel;
        mmsListener.getInstance().loginPanelListener(loginViewPanel);
    }

    @Override
    public void update() {

    }
}
