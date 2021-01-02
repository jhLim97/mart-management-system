package com.company.View;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainView extends JFrame {

    public LoginViewPanel loginViewPanel;
    public OrderListViewPanel orderListViewPanel;
    public CustomerViewPanel customerViewPanel;
    public ProductViewPanel productViewPanel;
    public MainViewPanel mainViewPanel;
    public MainView() { }

    public void drawView() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MMS");
        setSize(1200, 800);
        setLayout(new BorderLayout());

        setVisible(true);
    }


    public void drawLoginPanel() {
        if(loginViewPanel == null) loginViewPanel = new LoginViewPanel();
        loginViewPanel.drawView();
        getContentPane().add(loginViewPanel,BorderLayout.CENTER);
        setVisible(true);
    }

    public void drawOrderListViewPanel(){
         if(orderListViewPanel == null) orderListViewPanel = new OrderListViewPanel();

         orderListViewPanel.drawView();
         mainViewPanel.add(orderListViewPanel,BorderLayout.CENTER);
         setVisible(true);
    }
    public void drawCustomerViewPanel(){
        if(customerViewPanel == null) customerViewPanel = new CustomerViewPanel();
        customerViewPanel.drawView();
        mainViewPanel.add(customerViewPanel,BorderLayout.CENTER);
        setVisible(true);
    }

    public void drawProductViewPanel(){
        if(productViewPanel == null) productViewPanel = new ProductViewPanel();
        try {
            productViewPanel.drawView();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mainViewPanel.add(productViewPanel, BorderLayout.CENTER);
        setVisible(true);

    }
    public void drawMainPanel() {
        if(mainViewPanel == null) {
            mainViewPanel = new MainViewPanel();
            mainViewPanel.drawView();
            add(mainViewPanel);

        }  setVisible(true);



    }


}
