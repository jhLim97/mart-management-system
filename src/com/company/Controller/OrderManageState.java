package com.company.Controller;

import com.company.View.MainView;
import com.company.View.OrderListViewPanel;
import com.company.View.ProductViewPanel;
import com.company.View.ViewManager;

import javax.swing.*;

public class OrderManageState implements State{
    OrderListViewPanel listViewPanel;
    MainView mainView;

    @Override
    public void draw() {
    }

    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() {
        mainView = ViewManager.getInstance().getMainView();
        mainView.customerViewPanel.setVisible(false);
        mainView.productViewPanel.setVisible(false);
        mainView.orderListViewPanel.setVisible(true);
    }

    @Override
    public void applyListener() {
        listViewPanel = ViewManager.getInstance().getMainView().orderListViewPanel;
        mmsListener.getInstance().orderListViewPanelListener(listViewPanel);
    }

    @Override
    public void update() {

    }
}
