package com.company.View;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel{
    JTextField txtId, txtPw;
    JButton loginButton, joinButton;
    JLabel idLabel, pwLabel;

    public LoginView() {
        setPreferredSize(new Dimension(1200,800));
        setLayout(null);

        idLabel = new JLabel("ID");
        idLabel.setBounds(400,305,30,30);

        txtId = new JTextField(20);
        txtId.setBounds(440,305,150,30);

        loginButton = new JButton("로그인");
        loginButton.setBounds(610,300,80,80);

        pwLabel = new JLabel("PW");
        pwLabel.setBounds(400,345,30,30);

        txtPw = new JTextField(20);
        txtPw.setBounds(440,345,150,30);

        joinButton = new JButton("회원가입");
        joinButton.setBounds(610,380,80,20);
    }

    public void drawLoginView() {
        add(idLabel);
        add(txtId);
        add(loginButton);
        add(pwLabel);
        add(txtPw);
        add(joinButton);
    }
}
