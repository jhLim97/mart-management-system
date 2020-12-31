package com.company.Controller;

import com.company.View.MainView;
import com.company.View.ProductViewPanel;

import javax.swing.*;
import java.sql.SQLException;

public class MainState implements State{

    MainView productView;
    ProductViewPanel productPanel;

    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() throws SQLException, ClassNotFoundException {
        ProgramManager.getInstance().getMainView().drawMainPanel();
        ProgramManager.getInstance().getMainView().drawProductViewPanel();

    }

    @Override
    public void applyListener() {

    }

    @Override
    public void update() {

    }
}
