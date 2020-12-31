package com.company.View;

import javax.swing.*;
import java.awt.*;

public class LoginViewPanel extends JPanel{
    public JTextField txtId, txtPw;
    public JButton loginButton, joinButton;
    public JLabel idLabel, pwLabel;

    public LoginViewPanel() {
        setPreferredSize(new Dimension(1200,800));
        setLayout(null);

        idLabel = new JLabel("ID");
        txtId = new JTextField(20);
        loginButton = new JButton("로그인");
        pwLabel = new JLabel("PW");
        txtPw = new JTextField(20);
        joinButton = new JButton("회원가입");

    }

    public void drawView() {
        idLabel.setBounds(400,305,30,30);
        add(idLabel);
        txtId.setBounds(440,305,150,30);
        add(txtId);
        loginButton.setBounds(610,300,80,80);
        add(loginButton);
        pwLabel.setBounds(400,345,30,30);
        add(pwLabel);
        txtPw.setBounds(440,345,150,30);
        add(txtPw);
        joinButton.setBounds(610,380,80,20);
        add(joinButton);
    }
}
