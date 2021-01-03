package com.company.Controller;

import com.company.View.CustomerManageView;
import com.company.View.CustomerViewPanel;
import com.company.View.MainView;
import com.company.View.ProductViewPanel;

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
        mainView = ProgramManager.getInstance().getMainView();
        mainView.orderListViewPanel.setVisible(false);
        mainView.productViewPanel.setVisible(false);
        mainView.customerViewPanel.setVisible(true);
    }


    @Override
    public void applyListener() {

        customerViewPanel = ProgramManager.getInstance().getMainView().customerViewPanel;
        mmsListener.getInstance().customerViewPanelListener(customerViewPanel);

    }

    @Override
    public void update() {


    }
}
