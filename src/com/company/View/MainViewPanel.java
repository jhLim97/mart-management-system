package com.company.View;

import javax.swing.*;
import java.awt.*;

public class MainViewPanel extends JPanel {
    public JPanel buttonPanel, bottomPanel, centerPanel;
    public JButton productButton, orderListButton, customerButton, shoppingButton, chatButton, logoutButton;
    public JLabel messageLabel, timeLabel, blinkLabel;
    public String date, time;

    public MainViewPanel() {
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
        logoutButton = new JButton("로그아웃");
        timeLabel = new JLabel("현재 시간: ");
    }

    public void drawView(){
        setSize(1200, 800);
        setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(1, 5));
        buttonPanel.add(productButton);
        buttonPanel.add(orderListButton);
        buttonPanel.add(customerButton);
        buttonPanel.add(logoutButton);
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
