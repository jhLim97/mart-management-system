package com.company.Controller;

import com.company.View.MainView;
import com.company.View.ProductViewPanel;

import javax.swing.*;

import java.sql.SQLException;

public class MainState implements State{

    ProgramManager manager;
    MainView mainView;
    ProductViewPanel productViewPanel;

    public void drawFrameInit(){
        mainView = ProgramManager.getInstance().getMainView();
        mainView.loginViewPanel.setVisible(false);
        mainView.drawMainPanel();
        mainView.drawProductViewPanel();
        applyListener();
    }

    @Override
    public void draw() {
        drawPanel();
        applyListener();
    }

    @Override
    public void drawFrame() {

    }

    public void drawPanel() {
        ProgramManager.getInstance().getMainView().drawProductViewPanel();


    }

    @Override
    public void applyListener() {
        mainView = ProgramManager.getInstance().getMainView();
        mmsListener.getInstance().mainViewPanelListener(mainView);
    }

    @Override
    public void update() {

    }
}
