package com.company.View;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class CustomerManageFrame extends JFrame {
    JPanel mainPanel;
    JLabel nameLabel;//고객 이름
    JLabel phoneLabel;//핸드폰 번호
    JLabel pointLabel;//포인트 번호
    JTextField nameTF;//이름 텍스트필드
    JTextField phoneTF;//번호 텍스트필드
    JTextField pointTF;//포인트 텍스트필드
    JButton enrollBtn;//등록버튼
    JButton deleteBtn;//삭제버튼
    JButton updateBtn;//업데이트버튼
    JTextField stateTF;//상태텍스트필드
    JLabel customerMangeLab;//고객관리

    public CustomerManageFrame(){


        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(370,410));
        mainPanel.setLayout(null);
        this.add(mainPanel);

        //고객관리라벨
        customerMangeLab = new JLabel("고객 관리");
        //이름관련
        nameLabel = new JLabel("고객 이름");
        nameTF = new JTextField("홍길동");
        //핸드폰번호관련
        phoneLabel = new JLabel("핸드폰 번호");
        phoneTF = new JTextField("010-0000-0000");
        //포인트관련
        pointLabel = new JLabel("포인트");
        pointTF = new JTextField("0");
        //등록버튼
        enrollBtn = new JButton("등록");
        //수정버튼
        updateBtn = new JButton("수정");
        //삭제버튼
        updateBtn = new JButton("삭제");
        //상태 텍스트 필드
        stateTF = new JTextField("## 상태 : ");

    }
    void startUI(){
        this.setSize(370,410);
        this.setTitle("고객 정보 관리");
        this.setResizable(false);

        customerMangeLab.setFont(new Font("",Font.BOLD,15));
        customerMangeLab.setBounds(10,10,150,30);
        mainPanel.add(customerMangeLab);


        //이름관련
        nameLabel.setFont(new Font("",Font.BOLD,25));
        nameLabel.setBounds(10,50,150,50);
        nameLabel.setBackground(Color.gray);
        nameLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        mainPanel.add(nameLabel);

        nameTF.setHorizontalAlignment(SwingConstants.CENTER);
        nameTF.setBounds(165,50,190,50);
        nameTF.setFont(new Font("",Font.BOLD,25));
        mainPanel.add(nameTF);

        //핸드폰관련
        phoneLabel.setFont(new Font("",Font.BOLD,25));
        phoneLabel.setBounds(10,130,150,50);
        phoneLabel.setBackground(Color.gray);
        phoneLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        mainPanel.add(phoneLabel);

        phoneTF.setHorizontalAlignment(SwingConstants.CENTER);
        phoneTF.setBounds(165,130,190,50);
        phoneTF.setFont(new Font("",Font.BOLD,25));
        mainPanel.add(phoneTF);

        //포인트관련
        pointLabel.setFont(new Font("",Font.BOLD,25));
        pointLabel.setBounds(10,210,150,50);
        pointLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        pointLabel.setBackground(Color.gray);
        mainPanel.add(pointLabel);

        pointTF.setHorizontalAlignment(SwingConstants.CENTER);
        pointTF.setBounds(165,210,190,50);
        pointTF.setFont(new Font("",Font.BOLD,25));
        mainPanel.add(pointTF);

        //등록버튼
        enrollBtn.setFont(new Font("",Font.BOLD,25));
        enrollBtn.setBounds(10,290,100,50);
        mainPanel.add(enrollBtn);

        //수정버튼
        updateBtn.setFont(new Font("",Font.BOLD,25));
        updateBtn.setBounds(135,290,100,50);
        mainPanel.add(updateBtn);

        //삭제버튼
        updateBtn.setFont(new Font("",Font.BOLD,25));
        updateBtn.setBounds(260,290,100,50);
        mainPanel.add(updateBtn);

//        /상태텍스트필드
        stateTF.setBounds(10,380,350,20);
        stateTF.setEditable(false);
        mainPanel.add(stateTF);


        pack();
        this.setVisible(true);
    }

}
