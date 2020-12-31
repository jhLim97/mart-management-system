package com.company.Controller;

import com.company.View.LoginViewPanel;
import com.company.View.MainView;

import javax.swing.*;

public class LoginState implements State{

    LoginViewPanel loginViewPanel;
    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() {
        ProgramManager.getInstance().getMainView().drawLoginPanel();
    }

    @Override
    public void applyListener() {
        loginViewPanel = ProgramManager.getInstance().getMainView().loginViewPanel;

    }

    @Override
    public void update() {

    }
}
