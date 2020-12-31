package com.company.Controller;

import com.company.Main;
import com.company.View.MainView;
import com.company.View.ProductViewPanel;

import javax.swing.*;

public class MainState implements State{

    ProgramManager manager;
    MainView productView;
    ProductViewPanel productViewPanel;
    public MainState(){


    }
    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() {
//        productView.drawMainPanel();
//        productView.drawProductViewPanel();

    }

    @Override
    public void applyListener() {
        productView = ProgramManager.getInstance().getMainView();
        productView.productButton.addActionListener(e -> {
            ProgramManager.getInstance().setMainState();
            System.out.println("check");
        });
        productView.orderListButton.addActionListener(e -> {
            ProgramManager.getInstance().setOrderManageState();
            System.out.println("check");
        });
        productView.customerButton.addActionListener(e -> {
            ProgramManager.getInstance().setCustomerManageState();
            System.out.println("check");
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
