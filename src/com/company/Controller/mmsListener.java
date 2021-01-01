package com.company.Controller;

import com.company.Model.AccountDAO;
import com.company.Model.AccountDTO;
import com.company.View.*;

import javax.swing.text.View;

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
            joinViewListener(ViewManager.getInstance().joinView);

        });

    }
    public void joinViewListener(JoinView frame){
        frame.joinButton.addActionListener(e -> {
            String id = frame.txtId.getText();
            String pw = frame.txtPw.getText();
            String name = frame.txtName.getText();
            AccountDTO account = new AccountDTO();
            AccountDAO dao = new AccountDAO();
            account.setIsStaff(true);
            account.setId(id);
            account.setPassword(pw);
            account.setIsSupperUser(false);
            account.setUserName(name);
            System.out.println(name);
            System.out.println(dao.makeAccount(account));
        });

    }
    public void mainViewPanelListener(MainView frame){ // **********

        MainView mainView = ProgramManager.getInstance().getMainView();
        frame.productButton.addActionListener(e -> {
            if(ProgramManager.getInstance().getState() instanceof OrderManageState){
                mainView.orderListViewPanel.setVisible(false);
                ProgramManager.getInstance().setMainState();
            }
            else if(ProgramManager.getInstance().getState() instanceof CustomerManageState){
                mainView.customerViewPanel.setVisible(false);
                ProgramManager.getInstance().setMainState();
            }
        });
        frame.orderListButton.addActionListener(e-> {
            if(ProgramManager.getInstance().getState() instanceof MainState) {
                mainView.productViewPanel.setVisible(false);
                ProgramManager.getInstance().setOrderManageState();
            }
            else if(ProgramManager.getInstance().getState() instanceof CustomerManageState){
                mainView.customerViewPanel.setVisible(false);
                ProgramManager.getInstance().setOrderManageState();
            }
        });
        frame.customerButton.addActionListener(e->{
            if(ProgramManager.getInstance().getState() instanceof MainState) {
                mainView.productViewPanel.setVisible(false);
                ProgramManager.getInstance().setCustomerManageState();
            }
            else if(ProgramManager.getInstance().getState() instanceof OrderManageState){
                mainView.orderListViewPanel.setVisible(false);
                ProgramManager.getInstance().setCustomerManageState();
            }
        });
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
