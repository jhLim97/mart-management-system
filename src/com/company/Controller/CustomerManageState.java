package com.company.Controller;

import com.company.View.CustomerManageView;
import com.company.View.CustomerViewPanel;
import com.company.View.MainView;
import com.company.View.ProductViewPanel;

import javax.swing.*;

public class CustomerManageState implements State {

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

    }

    @Override
    public void update() {

    }
}
