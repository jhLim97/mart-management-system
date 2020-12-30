package com.company.View;

import javax.swing.*;

public class JoinFrame extends JFrame{
    JLabel idLabel, pwLabel, nameLabel;
    JTextField txtId, txtPw, txtName;
    JRadioButton isSuperUser,isStaff;
    JButton joinButton;
    ButtonGroup buttonGroup;

    public JoinFrame() {
        setTitle("회원가입");
        setLayout(null);

        idLabel = new JLabel("ID");
        pwLabel = new JLabel("PW");
        nameLabel = new JLabel("이름");

        txtId = new JTextField(20);
        txtPw = new JTextField(20);
        txtName = new JTextField(20);

        isSuperUser = new JRadioButton("관리자");
        isSuperUser.setSelected(false);
        isStaff = new JRadioButton("직원");
        isStaff.setSelected(false);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(isSuperUser);
        buttonGroup.add(isStaff);

        joinButton = new JButton("등록");

        setSize(500,750);
        setVisible(true);
    }

    public void drawJoinFrame() {
        idLabel.setBounds(100,200,50,20);
    }
}
