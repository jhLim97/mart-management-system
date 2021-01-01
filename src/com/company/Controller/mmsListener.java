package com.company.Controller;

import com.company.Model.AccountDAO;
import com.company.View.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mmsListener {
    private static mmsListener s_Instance;
    public static mmsListener getInstance(){
        if (s_Instance == null) s_Instance = new mmsListener();
        return s_Instance;
    }

    public void loginPanelListener(LoginViewPanel panel){

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

        panel.addAddButtonListener(e -> {
            ProgramManager.getInstance().getCC().register = true;
            System.out.println("register");
        });

        panel.addSearchButtonListener(e -> {
            ProgramManager.getInstance().getCC().search = true;
            System.out.println("search");
        });

        panel.addUpdateButtonListener(e -> {
            ProgramManager.getInstance().getCC().update = true;
            System.out.println("update");
        });

        panel.addDeleteButtonListener(e-> {
            ProgramManager.getInstance().getCC().delete =true;
            System.out.println("delete");
        });

        panel.tblCustomerList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ProgramManager.getInstance().getCC().isClick =true;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


    }
    public void productCRUDViewListener(ProductCRUDView frame){

    }
    public void shoppingViewListener(ShoppingView frame){

    }
    public void customerManageViewListener(CustomerManageView frame){

    }
}
