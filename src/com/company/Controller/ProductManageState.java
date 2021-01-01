package com.company.Controller;

import java.sql.SQLException;

public class ProductManageState implements State{
    @Override
    public void draw() {

    }

    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() throws SQLException, ClassNotFoundException {

    }

    @Override
    public void applyListener() {
        mmsListener.getInstance().productViewPanelListener(ProgramManager.getInstance().getMainView().productViewPanel);
    }

    @Override
    public void update() {

    }
}
