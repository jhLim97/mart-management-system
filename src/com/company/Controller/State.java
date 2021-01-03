package com.company.Controller;

import javax.swing.*;
import java.sql.SQLException;

public interface State {

    public void draw();

    public void drawFrame();

    public void drawPanel() throws SQLException, ClassNotFoundException;

    public void applyListener();

    public void update();

}
