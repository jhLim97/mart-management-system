package com.company.Controller;

import com.company.View.MainView;
import com.company.View.OrderListViewPanel;

import javax.swing.*;

public class OrderManageState implements State{


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

    }

    @Override
    public void update() {

    }
}
