package com.company.Controller;

import com.company.Model.AccountDAO;
import com.company.View.*;

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
    public void joinViewListener(JoinView frame){

    }
    public void mainViewPanelListener(MainView frame){ // **********

    }
    public void productViewPanelListener(ProductViewPanel panel){

    }

    public void orderListViewPanelListener(OrderListViewPanel panel){

    }
    public void customerViewPanelListener(CustomerViewPanel panel){

    }
    public void productCRUDViewListener(ProductCRUDView frame){

    }
    public void shoppingViewListener(ShoppingView frame){

    }
    public void customerManageViewListener(CustomerManageView frame){

    }
}
