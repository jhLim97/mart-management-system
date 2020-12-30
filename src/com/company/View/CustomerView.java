package com.company.View;

import javax.swing.*;
import java.awt.*;

public class CustomerView extends JPanel {

    private JPanel customerPanel, optionPanel, customerListPanel, cusInfoPanel, newCustomerPanel;
    private JTextField jtfPhoneNum;
    private JButton btnSearch, btnCustomerMange;
    private JTextArea jtaCustomerList, jtaNewCustomer;
    private JLabel lblPhoneNum, lblName, lblPoint, lblNewCustomer;
    private JScrollPane jsp;

    public CustomerView() {
        //setSize(500, 300);
        setBounds(0, 30, 1200, 735);
        setPreferredSize(new Dimension(1200, 735));
        setLayout(null);

        // -------customerPanel-------
        //customerPanel = new JPanel(); // 세 개의 패널을 add할 커스터머 전체 패널
        //customerPanel.setLayout(new BorderLayout());

        // -------optionPanel--------
        optionPanel = new JPanel(); // 상단 조회 및 관리 패널
        optionPanel.setBounds(0, 0, 1200, 30);
        optionPanel.setLayout(null);

        jtfPhoneNum = new JTextField(15);
        jtfPhoneNum.setBounds(20, 5, 100, 20);

        btnSearch = new JButton("조회");
        btnSearch.setBounds(140, 5, 60, 20);

        btnCustomerMange = new JButton("고객 정보 관리");
        btnCustomerMange.setBounds(220, 5, 130, 20);

        // -------customerListPanel-------
        //customerListPanel = new JPanel();
        //customerListPanel.setBounds(0, 35, 1200, 30);
        //customerListPanel.setLayout(null);

        cusInfoPanel = new JPanel(); // 고객 칼럼 라벨들 add 할 패널
        cusInfoPanel.setBounds(0, 35, 1000, 70);
        cusInfoPanel.setLayout(null);

        lblPhoneNum = new JLabel("휴대폰 번호");
        lblPhoneNum.setBounds(130, 30, 150, 50);

        lblName = new JLabel("고객 이름");
        lblName.setBounds(320, 30, 150, 50);

        lblPoint = new JLabel("누적 포인트");
        lblPoint.setBounds(510, 30, 150, 50);

        jtaCustomerList = new JTextArea(" ", 10, 30); // 고객 정보 리스팅 하는 Area
        jtaCustomerList.setEditable(false);

        jsp = new JScrollPane(jtaCustomerList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(100, 120, 800, 600);

        // -------newCustomerPanel-------
        newCustomerPanel = new JPanel();
        newCustomerPanel.setBounds(950, 120, 200, 600);
        newCustomerPanel.setLayout(null);

        lblNewCustomer = new JLabel("신규 고객");
        lblNewCustomer.setBounds(20, 50, 160, 50);

        jtaNewCustomer = new JTextArea(" ", 5, 5);
        jtaNewCustomer.setBounds(20, 120, 160, 400);

        drawView();

        setVisible(true);

    }

    public void drawView() {
        optionPanel.add(jtfPhoneNum);
        optionPanel.add(btnSearch);
        optionPanel.add(btnCustomerMange);

        cusInfoPanel.add(lblPhoneNum);
        cusInfoPanel.add(lblName);
        cusInfoPanel.add(lblPoint);

        //customerListPanel.add(cusInfoPanel);
        //customerListPanel.add(jsp);

        newCustomerPanel.add(lblNewCustomer);
        newCustomerPanel.add(jtaNewCustomer);

        add(optionPanel);
        //add(customerListPanel);
        add(cusInfoPanel);
        add(jsp);
        add(newCustomerPanel);
    }

}

