package com.company.Controller;

import com.company.View.MainView;
import com.company.View.OrderListViewPanel;
import com.company.View.ProductViewPanel;

import javax.swing.*;

public class OrderManageState implements State{
    OrderListViewPanel listViewPanel;
    MainView productView;

    @Override
    public void draw() {
        drawPanel();
        applyListener();
    }

    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() {
        ProgramManager.getInstance().getMainView().drawOrderListViewPanel();
    }

    @Override
    public void applyListener() {
        listViewPanel = ProgramManager.getInstance().getMainView().orderListViewPanel;
        mmsListener.getInstance().orderListViewPanelListener(listViewPanel);
    }

    @Override
    public void update() {

    }
}
