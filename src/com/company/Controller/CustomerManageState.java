package com.company.Controller;

import com.company.View.CustomerManageView;
import com.company.View.CustomerViewPanel;
import com.company.View.MainView;
import com.company.View.ProductViewPanel;

import javax.swing.*;

public class CustomerManageState implements State {
    MainView productView;
    CustomerViewPanel customerViewPanel;
    @Override
    public void draw() {

    }

    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() {
        ProgramManager.getInstance().getMainView().drawCustomerViewPanel();
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
