package com.company.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class TestOrderListViewPanel extends JFrame {
    JPanel ListPanel;
    JLabel orderLab; //판매 정보 및 매출
    JTextArea orderHistoryView; //상품 판매기록 뵤여주는 JTestArea
    JPanel revenuePanel;
    JLabel revenueMonth; //월별 매출
    JLabel revenueDay; //요일별 매출
    JLabel maxMonth; // 최고월 표기라벨
    JLabel minMonth; // 최저월 표기라벨
    JLabel maxDay; //최고요일 표기라벨
    JLabel minDay; //최저요일 표기라벨
    JLabel revenueTotal; // 총합 금액
    JLabel total;//총합금액표기라벨
    JTable jt;

    // --------- 최상단 라벨을 콤보 박스로.. ---------
    public JComboBox cb;
    public JButton btnSerach;

    public JTable orderTable, orderHistoryTable;
    public JScrollPane orderScroll, orderHistoryScroll;
    public String orderHeader[] = { "orderCode","entryPrice","cName","phoneNum","buyDate"}, orderContents[][];
    public String orderHistoryHeader[] = { "HistoryId", "orderCode","prCode","prName", "prCount", "prPrice"}, orderHistoryContents[][];
    public DefaultTableModel orderModel;
    public DefaultTableModel orderHistoryModel;
    // -------------------------------------------

    public TestOrderListViewPanel() {

        //ListPanel
        ListPanel = new JPanel();

        //OrderLab
        //orderLab = new JLabel("판매 정보 및 매출");
        String menu[] = {"요일별 조회", "월별 조회", "주문 내역", "주문 상세 내역"};
        cb = new JComboBox(menu); // 상품을 콤보박스에서 조회할 수 있도록 생성
        btnSerach = new JButton("조회");

        //orderHistory View
        //orderHistoryView = new JTextArea();
        orderModel = new DefaultTableModel(orderHeader,0);
        orderHistoryModel = new DefaultTableModel(orderHistoryHeader,0);
        orderTable = new JTable(orderModel);
        orderHistoryTable = new JTable(orderHistoryModel);
        orderScroll = new JScrollPane(orderTable);
        orderHistoryScroll = new JScrollPane(orderHistoryTable);
        //orderHistoryScroll = new JScrollPane(orderTable);

        //revenue panel + Label
        revenuePanel = new JPanel();
        revenueDay = new JLabel("요일별 매출");
        minDay = new JLabel("최저 : ");
        maxDay = new JLabel("최고 : ");
        revenueMonth = new JLabel("월별 매출");
        minMonth = new JLabel("최저 : ");
        maxMonth = new JLabel("최고 : ");
        revenueTotal = new JLabel("총 매출");
        total = new JLabel("총액 : ");

    }

    public void drawView(){

        // ------ 여기는 단순히 프레임화 시킨거라 아래 주석처리된게 맞음 -------
        setTitle("Shopping View");
        setSize(1200, 500);
        setLayout(null);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        //this.setPreferredSize(new Dimension(1200,500));
        //this.setLayout(null);

        //ListPanel
        ListPanel.setLayout(null);
        ListPanel.setBackground(Color.gray);
        ListPanel.setBounds(10,10,1170,50);
        //OrderLab
        //orderLab.setHorizontalAlignment(JLabel.LEFT);
        //orderLab.setVerticalAlignment(JLabel.CENTER);
        //orderLab.setFont(new Font("", Font.BOLD, 30));
        //orderLab.setBounds(10,00,300,50);

        //cb.setHorizontalAlignment(JLabel.LEFT);
        //cb.setVerticalAlignment(JLabel.CENTER);
        cb.setBounds(30,5,130,40);
        btnSerach.setBounds(170, 5, 70, 40);

        //TextArea
        //orderHistoryView.setBounds(10, 70, 800, 380);
        //orderHistoryView.setText("\n       주문정보\t    주문코드\t제품코드\t제품갯수\t제품가격\t판매날짜");
        //orderHistoryView.setEditable(false);
        //orderHistoryView.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        //JScrollPane sp = new JScrollPane(orderHistoryView);
        orderScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        orderScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        orderScroll.setBounds(10, 70,800,380);
        orderScroll.setVisible(true);

        orderHistoryScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        orderHistoryScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        orderHistoryScroll.setBounds(10, 70,800,380);
        orderHistoryScroll.setVisible(false);

        //revenuePanel
        revenuePanel.setBackground(Color.lightGray);
        revenuePanel.setBounds(820,70,360,380);
        revenuePanel.setLayout(null);
        //revenue panel + Label
        revenueDay.setBounds(20,20,320,30);
        minDay.setBounds(20,60,320,30);
        maxDay.setBounds(20,100,320,30);
        revenueMonth.setBounds(20,140,320,30);
        minMonth.setBounds(20,180,320,30);
        maxMonth.setBounds(20,220,320,30);
        revenueTotal.setBounds(20,260,320,30);
        total.setBounds(20,300,320,30);

        revenueDay.setFont(new Font("",Font.BOLD,20));
        revenueMonth.setFont(new Font("",Font.BOLD,20));
        revenueTotal.setFont(new Font("",Font.BOLD,20));
        total.setFont(new Font("", Font.BOLD,15));


        //add
        this.add(ListPanel);
        //ListPanel.add(orderLab);
        ListPanel.add(cb);
        ListPanel.add(btnSerach);
        //this.add(orderHistoryView);
        this.add(orderScroll);
        this.add(orderHistoryScroll);
        this.add(revenuePanel);
        revenuePanel.add(revenueDay);
        revenuePanel.add(revenueMonth);
        revenuePanel.add(revenueTotal);
        revenuePanel.add(minDay); revenuePanel.add(maxDay);
        revenuePanel.add(minMonth); revenuePanel.add(maxMonth);
        revenuePanel.add(total);

        setVisible(true);
    }

}

