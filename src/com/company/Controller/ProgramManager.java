package com.company.Controller;

import com.company.View.CustomerManageView;
import com.company.View.MainView;

public class ProgramManager {

    private MainView mainView;
    private State state;

    public MainView getMainView() {
        return mainView;
    }

    public void drawMainView() {
        if(mainView == null) mainView = new MainView();
        mainView.drawView();
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    private static ProgramManager s_Instance;
    public static ProgramManager getInstance(){
        if (s_Instance == null) s_Instance = new ProgramManager();
        return s_Instance;
    }

}
