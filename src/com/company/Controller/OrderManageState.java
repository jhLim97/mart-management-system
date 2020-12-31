package com.company.Controller;

import com.company.View.MainView;
import com.company.View.OrderListViewPanel;
import com.company.View.ProductViewPanel;

import javax.swing.*;

public class OrderManageState implements State{
    OrderListViewPanel listViewPanel;
    MainView productView;
    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() {
        ProgramManager.getInstance().getMainView().drawMainPanel();
        ProgramManager.getInstance().getMainView().drawOrderListViewPanel();
    }

    @Override
    public void applyListener() {
        productView = ProgramManager.getInstance().getMainView();
        productView.productButton.addActionListener(e -> {
            productView.productViewPanel.setVisible(false);
            ProgramManager.getInstance().setMainState();

        });
        productView.orderListButton.addActionListener(e -> {
            productView.productViewPanel.setVisible(false);
            ProgramManager.getInstance().setOrderManageState();

        });
        productView.customerButton.addActionListener(e -> {
            productView.productViewPanel.setVisible(false);
            ProgramManager.getInstance().setCustomerManageState();

        });
    }

    @Override
    public void update() {

    }
}
