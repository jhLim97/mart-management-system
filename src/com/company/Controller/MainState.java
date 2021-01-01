package com.company.Controller;

import com.company.View.MainView;
import com.company.View.ProductViewPanel;

import javax.swing.*;

import java.sql.SQLException;

public class MainState implements State{

    ProgramManager manager;
    MainView productView;
    ProductViewPanel productViewPanel;
    public MainState(){


    }
    @Override
    public void drawFrame() {

    }

    public void drawPanel() throws SQLException, ClassNotFoundException {
        ProgramManager.getInstance().getMainView().drawMainPanel();
        ProgramManager.getInstance().getMainView().drawProductViewPanel();
    }

    @Override
    public void applyListener() {
        productView = ProgramManager.getInstance().getMainView();
        productView.productButton.addActionListener(e -> {

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
//    public static void main(String[] args) {
//        MainState state = new MainState();
//        ProgramManager.getInstance().setState(state);
//        ProgramManager.getInstance().drawMainView();
//        ProgramManager.getInstance().getMainView().drawMainPanel();
//        ProgramManager.getInstance().getMainView().drawProductViewPanel();
//        state.applyListener();
//    }
}
