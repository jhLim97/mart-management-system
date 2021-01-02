package com.company.View;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainView extends JFrame {
    public JPanel buttonPanel, bottomPanel, centerPanel;
    public JButton productButton, orderListButton, customerButton, shoppingButton, chatButton;
    public JLabel messageLabel, timeLabel, blinkLabel;
    public String date, time;

    public LoginViewPanel loginViewPanel;
    public OrderListViewPanel orderListViewPanel;
    public CustomerViewPanel customerViewPanel;
    public ProductViewPanel productViewPanel;

    public MainView() {

        buttonPanel = new JPanel();
        productButton = new JButton("Product");
        orderListButton = new JButton("OrderList");
        customerButton = new JButton("Customer");
        blinkLabel = new JLabel();
        shoppingButton = new JButton("Shopping");
        centerPanel = new JPanel();
        bottomPanel = new JPanel();
        messageLabel = new JLabel("메시지: ");
        chatButton = new JButton("채팅");
        timeLabel = new JLabel("현재 시간: ");

    }

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
         getContentPane().add(orderListViewPanel,BorderLayout.CENTER);
         setVisible(true);
    }
    public void drawCustomerViewPanel(){
        if(customerViewPanel == null) customerViewPanel = new CustomerViewPanel();
        customerViewPanel.drawView();
        getContentPane().add(customerViewPanel,BorderLayout.CENTER);
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
        add(productViewPanel, BorderLayout.CENTER);
        setVisible(true);

    }
    public void drawMainPanel() {

        buttonPanel.setLayout(new GridLayout(1, 5));
        buttonPanel.add(productButton);
        buttonPanel.add(orderListButton);
        buttonPanel.add(customerButton);
        buttonPanel.add(blinkLabel);
        buttonPanel.add(shoppingButton);

        add(buttonPanel, BorderLayout.PAGE_START);

        centerPanel.setSize(1200, 700);
        bottomPanel.setPreferredSize(new Dimension(1200, 30));
        bottomPanel.setLayout(null);

        add(bottomPanel, BorderLayout.PAGE_END);
        messageLabel.setBounds(20, 0, 600, 30);

        chatButton.setBounds(620, 0, 100, 30);
        timeLabel.setBounds(800, 0, 500, 30);

        bottomPanel.add(messageLabel);
        bottomPanel.add(chatButton);
        bottomPanel.add(timeLabel);

    }



}
