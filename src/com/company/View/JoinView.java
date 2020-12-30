package com.company.View;

import javax.swing.*;

public class JoinView extends JFrame{
    JLabel idLabel, pwLabel, nameLabel;
    JTextField txtId, txtPw, txtName;
    JRadioButton isSuperUser,isStaff;
    JButton joinButton;
    ButtonGroup buttonGroup;

    public JoinView() {

        idLabel = new JLabel("ID");
        pwLabel = new JLabel("PW");
        nameLabel = new JLabel("이름");
        txtId = new JTextField(20);
        txtPw = new JTextField(20);
        txtName = new JTextField(20);
        isSuperUser = new JRadioButton("관리자");
        isStaff = new JRadioButton("직원");
        buttonGroup = new ButtonGroup();
        joinButton = new JButton("등록");

    }

    public void drawView() {
        setTitle("회원가입");
        setLayout(null);

        isSuperUser.setSelected(false);
        isStaff.setSelected(false);

        buttonGroup.add(isSuperUser);
        buttonGroup.add(isStaff);

        setSize(500,750);
        setVisible(true);

        idLabel.setBounds(100,200,50,20);
    }
}
