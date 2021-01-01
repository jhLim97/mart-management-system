package com.company.View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ShoppingLogin extends JFrame {
    JLabel lblName, lblPhone;
    JTextField txtName, txtPhone;
    JButton btnEnter;

    public static void main(String[] args) {
        new ShoppingLogin().drawView();
    }

    public ShoppingLogin() {
        lblName = new JLabel("이름");
        lblPhone = new JLabel("휴대폰");
        btnEnter = new JButton("입장");
        txtName = new JTextField(10);
        txtPhone = new JTextField(10);
    }

    public void drawView() {
        setTitle("Shopping Login");
        setSize(400,550);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblName.setBounds(100,200,50,40);
        lblPhone.setBounds(100,250,50,40);
        btnEnter.setBounds(300,450,80,50);

        txtName.setBounds(160,200,150,40);
        txtPhone.setBounds(160,250,150,40);

        add(lblName);
        add(lblPhone);
        add(btnEnter);
        add(txtName);
        add(txtPhone);

        setVisible(true);
    }

    public void addEnterButtonListener(ActionListener listener) {
        btnEnter.addActionListener(listener);
    }
}
