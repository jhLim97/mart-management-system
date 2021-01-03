package com.company.Controller;

import com.company.View.*;

import javax.swing.*;

public class CustomerManageState implements State {
    MainView mainView;
    CustomerViewPanel customerViewPanel;
    @Override
    public void draw() {

    }

    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() {
        mainView = ViewManager.getInstance().getMainView();
        mainView.orderListViewPanel.setVisible(false);
        mainView.productViewPanel.setVisible(false);
        mainView.customerViewPanel.setVisible(true);
    }


    @Override
    public void applyListener() {

        customerViewPanel = ViewManager.getInstance().getMainView().customerViewPanel;
        mmsListener.getInstance().customerViewPanelListener(customerViewPanel);

    }

    @Override
    public void update() {


    }
}
