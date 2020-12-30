package com.company.View;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    JPanel buttonPanel, bottomPanel, centerPanel;
    JButton productButton, orderListButton, customerButton, shoppingButton, chatButton;
    JLabel messageLabel, timeLabel, blinkLabel;
    String date, time;

    LoginView loginView;

    public MainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MMS");

        setSize(1200,800);
        setVisible(true);
    }

    public void loginView() {
        loginView = new LoginView();
        loginView.drawLoginView();
        getContentPane().add(loginView);
        setVisible(true);
    }

    public void drawMainView() {
        setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,5));

        productButton = new JButton("Product");
        orderListButton = new JButton("OrderList");
        customerButton = new JButton("Customer");
        blinkLabel = new JLabel();
        shoppingButton = new JButton("Shopping");

        buttonPanel.add(productButton);
        buttonPanel.add(orderListButton);
        buttonPanel.add(customerButton);
        buttonPanel.add(blinkLabel);
        buttonPanel.add(shoppingButton);

        add(buttonPanel, BorderLayout.PAGE_START);

        centerPanel = new JPanel();
        centerPanel.setSize(1200,700);
        add(centerPanel, BorderLayout.CENTER);

        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(1200,30));
        bottomPanel.setLayout(null);
        add(bottomPanel, BorderLayout.PAGE_END);

        messageLabel = new JLabel("메시지: ");
        messageLabel.setBounds(20,0,600,30);
        chatButton = new JButton("채팅");
        chatButton.setBounds(620,0,100,30);
        timeLabel = new JLabel("현재 시간: ");
        timeLabel.setBounds(800,0,500,30);

        bottomPanel.add(messageLabel);
        bottomPanel.add(chatButton);
        bottomPanel.add(timeLabel);
    }

}

