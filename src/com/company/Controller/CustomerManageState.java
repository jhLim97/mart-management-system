package com.company.Controller;

import com.company.View.CustomerManageView;
import com.company.View.CustomerViewPanel;
import com.company.View.MainView;
import com.company.View.ProductViewPanel;

import javax.swing.*;

public class CustomerManageState implements State {
    MainView productView;
    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() {
        ProgramManager.getInstance().getMainView().drawMainPanel();
        ProgramManager.getInstance().getMainView().drawCustomerViewPanel();
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
        CustomerViewPanel customerView = ProgramManager.getInstance().getMainView().customerViewPanel;
        mmsListener.getInstance().customerViewPanelListener(customerView);

    }

    @Override
    public void update() {

    }
}
