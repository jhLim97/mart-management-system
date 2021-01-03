package com.company.Controller;

import com.company.View.MainView;
import com.company.View.ProductViewPanel;

import javax.swing.*;

import java.sql.SQLException;

public class MainState implements State{

    ProgramManager manager;
    MainView mainView;
    ProductViewPanel productViewPanel;

    @Override
    public void draw() {
        mainView = ProgramManager.getInstance().getMainView();
        mainView.drawMainPanel();
        mainView.drawCustomerViewPanel();
        mainView.drawOrderListViewPanel();
        mainView.drawProductViewPanel();
        mainView.drawMainPanel();
        applyListener();
    }

    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() {
        mainView = ProgramManager.getInstance().getMainView();
        mainView.mainViewPanel.setVisible(true);
        mainView.loginViewPanel.setVisible(false);
        mainView.customerViewPanel.setVisible(false);
        mainView.orderListViewPanel.setVisible(false);
        mainView.productViewPanel.setVisible(true);
        ProgramManager.getInstance().getChattingView().drawView();
    }

    @Override
    public void applyListener() {
        mainView = ProgramManager.getInstance().getMainView();
        mainView = ProgramManager.getInstance().getMainView();
        mmsListener.getInstance().mainViewPanelListener(mainView.mainViewPanel);
        mmsListener.getInstance().productViewPanelListener(mainView.productViewPanel);
        mmsListener.getInstance().productCRUDViewListener(ProgramManager.getInstance().getProductCRUDView());
        mmsListener.getInstance().chattingViewListener(ProgramManager.getInstance().getChattingView());
    }

    @Override
    public void update() {

    }
}
