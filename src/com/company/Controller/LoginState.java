package com.company.Controller;

import com.company.View.LoginViewPanel;
import com.company.View.MainView;

import javax.swing.*;

public class LoginState implements State{

<<<<<<< HEAD
=======
    LoginViewPanel loginViewPanel;
>>>>>>> 17264d35ecf291b28383368a0fefa28424ce7abc
    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() {
        ProgramManager.getInstance().getMainView().drawLoginPanel();
    }

    @Override
    public void applyListener() {
<<<<<<< HEAD
=======
        loginViewPanel = ProgramManager.getInstance().getMainView().loginViewPanel;
>>>>>>> 17264d35ecf291b28383368a0fefa28424ce7abc

    }

    @Override
    public void update() {

    }
}
