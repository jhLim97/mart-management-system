package com.company.Controller;

<<<<<<< HEAD
=======
import com.company.Main;
>>>>>>> 17264d35ecf291b28383368a0fefa28424ce7abc
import com.company.View.MainView;
import com.company.View.ProductViewPanel;

import javax.swing.*;
<<<<<<< HEAD
import java.sql.SQLException;

public class MainState implements State{

    MainView productView;
    ProductViewPanel productPanel;

=======

public class MainState implements State{

    ProgramManager manager;
    MainView productView;
    ProductViewPanel productViewPanel;
    public MainState(){


    }
>>>>>>> 17264d35ecf291b28383368a0fefa28424ce7abc
    @Override
    public void drawFrame() {

    }

    @Override
<<<<<<< HEAD
    public void drawPanel() throws SQLException, ClassNotFoundException {
        ProgramManager.getInstance().getMainView().drawMainPanel();
        ProgramManager.getInstance().getMainView().drawProductViewPanel();
=======
    public void drawPanel() {
//        productView.drawMainPanel();
//        productView.drawProductViewPanel();
>>>>>>> 17264d35ecf291b28383368a0fefa28424ce7abc

    }

    @Override
    public void applyListener() {
<<<<<<< HEAD
=======
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
>>>>>>> 17264d35ecf291b28383368a0fefa28424ce7abc

    }

    @Override
    public void update() {

    }
<<<<<<< HEAD
=======
//    public static void main(String[] args) {
//        MainState state = new MainState();
//        ProgramManager.getInstance().setState(state);
//        ProgramManager.getInstance().drawMainView();
//        ProgramManager.getInstance().getMainView().drawMainPanel();
//        ProgramManager.getInstance().getMainView().drawProductViewPanel();
//        state.applyListener();
//    }
>>>>>>> 17264d35ecf291b28383368a0fefa28424ce7abc
}
