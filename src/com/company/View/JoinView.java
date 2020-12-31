package com.company.View;

import javax.swing.*;

public class JoinView extends JFrame{
    public JLabel idLabel, pwLabel, nameLabel;
    public JTextField txtId, txtPw, txtName;
    public JRadioButton adminRButton, staffRButton;
    public JButton joinButton;
    public ButtonGroup buttonGroup;

    public static void main(String[] args) { new JoinView().drawView(); }

    public JoinView() {

        idLabel = new JLabel("ID");
        pwLabel = new JLabel("PW");
        nameLabel = new JLabel("이름");

        txtId = new JTextField(20);
        txtPw = new JTextField(20);
        txtName = new JTextField(20);

        adminRButton = new JRadioButton("관리자");

        staffRButton = new JRadioButton("직원");

        buttonGroup = new ButtonGroup();

        joinButton = new JButton("등록");
    }

    public void drawView() {
        setTitle("회원가입");
        setLayout(null);

        setSize(500,750);

        adminRButton.setSelected(false);
        staffRButton.setSelected(false);
        buttonGroup.add(adminRButton);
        buttonGroup.add(staffRButton);

        adminRButton.setBounds(120,320,80,30);
        staffRButton.setBounds(220,320,80,30);

        idLabel.setBounds(100,200,50,30);
        txtId.setBounds(160,200,150,30);

        pwLabel.setBounds(100,240,50,30);
        txtPw.setBounds(160,240,150,30);

        nameLabel.setBounds(100,280,50,30);
        txtName.setBounds(160,280,150,30);

        joinButton.setBounds(380,650,60,30);

        add(idLabel);
        add(pwLabel);
        add(nameLabel);
        add(txtId);
        add(txtPw);
        add(txtName);
        add(adminRButton);
        add(staffRButton);
        add(joinButton);

        setVisible(true);

    }


}