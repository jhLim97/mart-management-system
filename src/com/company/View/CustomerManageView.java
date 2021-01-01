package com.company.View;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerManageView extends JFrame {
    public JPanel mainPanel;
    public JLabel nameLabel;//고객 이름
    public JLabel phoneLabel;//핸드폰 번호
    public JLabel pointLabel;//포인트 번호
    public JTextField txtName;//이름 텍스트필드
    public JTextField txtPhone;//번호 텍스트필드
    public JTextField txtPoint;//포인트 텍스트필드
    public JButton btnRegister;//등록버튼
    public JButton btnExit;//업데이트버튼
    public JLabel customerMangeLab;//고객관리

    public CustomerManageView(){

        mainPanel = new JPanel();
        //고객관리라벨
        customerMangeLab = new JLabel("고객 관리");
        //이름관련
        nameLabel = new JLabel("고객 이름");
        txtName = new JTextField("");
        //핸드폰번호관련
        phoneLabel = new JLabel("핸드폰 번호");
        txtPhone = new JTextField("");
        //포인트관련
        pointLabel = new JLabel("포인트");
        txtPoint = new JTextField("");
        //등록버튼
        btnRegister = new JButton("등록");
        //닫기버튼
        btnExit = new JButton("닫기");

    }
    public void drawView(){
        this.setSize(370,410);
        this.setTitle("고객 정보 관리");
        this.setResizable(false);

        mainPanel.setPreferredSize(new Dimension(370,410));
        mainPanel.setLayout(null);
        this.add(mainPanel);

        customerMangeLab.setFont(new Font("",Font.BOLD,15));
        customerMangeLab.setBounds(10,10,150,30);
        mainPanel.add(customerMangeLab);


        //이름관련
        nameLabel.setFont(new Font("",Font.BOLD,25));
        nameLabel.setBounds(10,50,150,50);
        nameLabel.setBackground(Color.gray);
        nameLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        mainPanel.add(nameLabel);

        txtName.setHorizontalAlignment(SwingConstants.CENTER);
        txtName.setBounds(165,50,190,50);
        txtName.setFont(new Font("",Font.BOLD,25));
        mainPanel.add(txtName);

        //핸드폰관련
        phoneLabel.setFont(new Font("",Font.BOLD,25));
        phoneLabel.setBounds(10,130,150,50);
        phoneLabel.setBackground(Color.gray);
        phoneLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        mainPanel.add(phoneLabel);

        txtPhone.setHorizontalAlignment(SwingConstants.CENTER);
        txtPhone.setBounds(165,130,190,50);
        txtPhone.setFont(new Font("",Font.BOLD,25));
        mainPanel.add(txtPhone);

        //포인트관련
        pointLabel.setFont(new Font("",Font.BOLD,25));
        pointLabel.setBounds(10,210,150,50);
        pointLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        pointLabel.setBackground(Color.gray);
        mainPanel.add(pointLabel);

        txtPoint.setHorizontalAlignment(SwingConstants.CENTER);
        txtPoint.setBounds(165,210,190,50);
        txtPoint.setFont(new Font("",Font.BOLD,25));
        mainPanel.add(txtPoint);

        //등록버튼
        btnRegister.setFont(new Font("",Font.BOLD,25));
        btnRegister.setBounds(10,290,100,50);
        mainPanel.add(btnRegister);

        //수정버튼
        btnExit.setFont(new Font("",Font.BOLD,25));
        btnExit.setBounds(135,290,100,50);
        mainPanel.add(btnExit);

        //삭제버튼
        btnExit.setFont(new Font("",Font.BOLD,25));
        btnExit.setBounds(260,290,100,50);
        mainPanel.add(btnExit);

        pack();
        this.setVisible(true);
    }

    public void refreshTextField() {
        txtName.setText("");
        txtPhone.setText("");
        txtPoint.setText("");
    }

    public void addRegisterButtonListener(ActionListener listener) {
        btnRegister.addActionListener(listener);
    }

    public void addExitButtonListener(ActionListener listener) {
        btnExit.addActionListener(listener);
    }

}
