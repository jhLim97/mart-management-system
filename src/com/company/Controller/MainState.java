package com.company.Controller;

import com.company.View.MainView;
import com.company.View.ProductViewPanel;

import javax.swing.*;

public class MainState implements State{

    MainView productView;
    ProductViewPanel productPanel;

    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() {
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
