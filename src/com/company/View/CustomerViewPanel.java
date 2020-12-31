package com.company.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerViewPanel extends JPanel {

    public JPanel optionPanel, cusInfoPanel, newCustomerPanel;
    public JTextField jtfPhoneNum;
    public JButton btnSearch, btnCustomerManage;
    public JTextArea jtaCustomerList, jtaNewCustomer;
    public JLabel lblPhoneNum, lblName, lblPoint, lblNewCustomer;
    public JScrollPane jsp;

    public CustomerViewPanel() {

        // -------optionPanel--------
        optionPanel = new JPanel(); // 상단 조회 및 관리 패널

        jtfPhoneNum = new JTextField(15);
        btnSearch = new JButton("조회");
        btnCustomerManage = new JButton("고객 정보 관리");

        // ------- cusInfoPanel -------
        cusInfoPanel = new JPanel(); // 고객 칼럼 라벨들 add 할 패널

        lblPhoneNum = new JLabel("휴대폰 번호");
        lblName = new JLabel("고객 이름");
        lblPoint = new JLabel("누적 포인트");

        // ------- jtaCustomerList -------
        jtaCustomerList = new JTextArea(" ", 10, 30); // 고객 정보 리스팅 하는 Area
        jsp = new JScrollPane(jtaCustomerList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // -------newCustomerPanel-------
        newCustomerPanel = new JPanel();
        lblNewCustomer = new JLabel("신규 고객");
        jtaNewCustomer = new JTextArea(" ", 5, 5);

    }

    public void drawView() {

        //setSize(500, 300);
        setBounds(0, 30, 1200, 735);
        setPreferredSize(new Dimension(1200, 735));
        setLayout(null);

        // ------- optionPanel --------
        optionPanel.setBounds(0, 0, 1200, 30);
        optionPanel.setLayout(null);

        jtfPhoneNum.setBounds(20, 5, 100, 20);
        btnSearch.setBounds(140, 5, 60, 20);
        btnCustomerManage.setBounds(220, 5, 130, 20);

        optionPanel.add(jtfPhoneNum);
        optionPanel.add(btnSearch);
        optionPanel.add(btnCustomerManage);

        // ------- cusInfoPanel -------
        cusInfoPanel.setBounds(0, 35, 1000, 70);
        cusInfoPanel.setLayout(null);

        lblPhoneNum.setBounds(130, 30, 150, 50);
        lblName.setBounds(320, 30, 150, 50);
        lblPoint.setBounds(510, 30, 150, 50);

        cusInfoPanel.add(lblPhoneNum);
        cusInfoPanel.add(lblName);
        cusInfoPanel.add(lblPoint);

        // ------- jtaCustomerList -------
        jtaCustomerList.setEditable(false);
        jsp.setBounds(100, 120, 800, 600);

        // ------- newCustomerPanel -------
        newCustomerPanel.setBounds(950, 120, 200, 600);
        newCustomerPanel.setLayout(null);

        lblNewCustomer.setBounds(20, 50, 160, 50);
        jtaNewCustomer.setBounds(20, 120, 160, 400);

        newCustomerPanel.add(lblNewCustomer);
        newCustomerPanel.add(jtaNewCustomer);

        add(optionPanel);
        add(cusInfoPanel);
        add(jsp);
        add(newCustomerPanel);

        setVisible(true);
    }

    public void addSearchButtonListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }

    public void addCustomerManageButtonListener(ActionListener listener) {
        btnCustomerManage.addActionListener(listener);
    }
}

