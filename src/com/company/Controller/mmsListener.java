package com.company.Controller;

import com.company.Model.AccountDAO;
import com.company.View.JoinView;
import com.company.View.LoginViewPanel;
import com.company.View.ViewManager;

import javax.swing.*;

public class mmsListener {
    private static mmsListener s_Instance;
    public static mmsListener getInstance(){
        if (s_Instance == null) s_Instance = new mmsListener();
        return s_Instance;
    }

    public void loginPanelListener(LoginViewPanel panel){

        panel.loginButton.addActionListener(e -> {
            String id = panel.txtId.getText();
            String pw = panel.txtPw.getText();
            AccountDAO dao = new AccountDAO();

            if(dao.loginProgram(id,pw)) ProgramManager.getInstance().setMainState();
            else{
                panel.txtId.setText("");
                panel.txtPw.setText("");
            }
        });
        panel.joinButton.addActionListener(e -> {
            ViewManager.getInstance().joinViewOpen();
        });

    }


}
