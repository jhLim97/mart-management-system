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
        if(mainView.mainViewPanel == null) mainView.drawMainPanel();
        else mainView.mainViewPanel.setVisible(true);
        if(mainView.productViewPanel == null) mainView.drawProductViewPanel();
        else mainView.productViewPanel.setVisible(true);
        ProgramManager.getInstance().getChattingView().drawView();

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


    }

    @Override
    public void applyListener() {
        mainView = ProgramManager.getInstance().getMainView();
        mmsListener.getInstance().mainViewPanelListener(mainView.mainViewPanel);
        mmsListener.getInstance().productViewPanelListener(
                (ProgramManager.getInstance().getMainView().productViewPanel));
        mmsListener.getInstance().productCRUDViewListener(ProgramManager.getInstance().getProductCRUDView());
        mmsListener.getInstance().chattingViewListener(ProgramManager.getInstance().getChattingView());
    }

    @Override
    public void update() {

    }
}
