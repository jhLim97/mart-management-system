package com.company.View;

import com.company.Controller.ShoppingController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.sql.SQLException;

public class ShoppingView extends JFrame {

    //private JPanel selectProductPanel, productListPanel, myShoppingPanel;

    public JLabel lblEnterName, lblEnterPhoneNum, lblProduct, lblSearch, lblCount, lblItemList, lblMyList, lblMsg, lblCname, lblCphoneNum, lblstate;
    public JTextField jtfSearch, jtfCount, txtName, txtPhone;

    public JComboBox cb;
    //public JTable tbItem, tbMyList;
    public JScrollPane jspItem, jspMyList;

    public JButton btnDelete, btnPay, btnEnter, btnEnroll;

    public Font fnt, fntDelete;
    //public DefaultTableModel modelItemList, modelMyList;
    public JPanel pn1, pn2;

    public JTable productTable,productTable2;
    public DefaultTableModel tableModel,tableModel2;
    public String tableHeader[] = { "Code","Name","Price","Count","State"};


    public ShoppingView() {

        // 폰트 설정
        fnt = new Font("Dialog", Font.BOLD, 15);
        fntDelete = new Font("Dialog", Font.BOLD, 10);

        pn1 = new JPanel();
        pn2 = new JPanel();

        // ---------- 구매를 위한 이름, 번호 입력 뷰 ---------
        lblEnterName = new JLabel("이름");
        lblEnterPhoneNum = new JLabel("휴대폰");
        btnEnter = new JButton("입장");
        txtName = new JTextField(10);
        txtPhone = new JTextField(10);

        // -----------shoppingView---------------
        // -----------label, textfield-----------
        lblProduct = new JLabel("상품");
        lblSearch = new JLabel("코드");
        lblCount = new JLabel("수량");
        lblCname = new JLabel("고객이름 : 4조");
        lblCphoneNum = new JLabel("고객 번호 : 010-8890-2749");

        jtfSearch = new JTextField();
        jtfCount = new JTextField();
        
        
        // -----------ItemList-----------
        lblItemList = new JLabel("구매 할 수 있는 물품 List");
        lblstate = new JLabel("상태 : ");


        btnEnroll = new JButton("담기");


        // table 생성
        tableModel = new DefaultTableModel(tableHeader,0);
        productTable = new JTable(tableModel);

        jspItem = new JScrollPane(productTable);
        jspItem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspItem.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        // -----------MyList-----------
        lblMyList = new JLabel("내가 담은 List(장바구니)");

        // table에 들어갈 rows, columns 정의

        String MyListcolumns[]={"Code", "Name", "Price", "Count"};
        tableModel2 = new DefaultTableModel(MyListcolumns, 0);
        productTable2 = new JTable(tableModel2);
        jspMyList = new JScrollPane(productTable2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        btnDelete = new JButton("삭제");

        // -----------PayMsg-----------
        lblMsg = new JLabel("결제 금액 : 원");
        btnPay = new JButton("결제하기");

    }

    public void drawView() {

        setTitle("Shopping View");
        setSize(400, 550);
        setLayout(null);
        //setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        // ---------- 구매를 위한 이름, 번호 입력 뷰 ---------
        // ----------------------------------------------
        pn1.setBounds(0, 0, 400, 550);
        pn1.setLayout(null);

        lblEnterName.setFont(fnt);
        lblEnterPhoneNum.setFont(fnt);
        lblEnterName.setBounds(90,170,50,40);
        lblEnterPhoneNum.setBounds(90,220,50,40);
        btnEnter.setBounds(90,300,120,50);

        txtName.setBounds(150,170,150,40);
        txtPhone.setBounds(150,220,150,40);

        pn1.add(lblEnterName);
        pn1.add(lblEnterPhoneNum);
        pn1.add(btnEnter);
        pn1.add(txtName);
        pn1.add(txtPhone);

        add(pn1);
        pn1.setVisible(true);
        // -----------------------------------------------

        // -----------shoppingView---------------
        // -----------label, textfield add-----------

        pn2.setBounds(0, 0, 400, 550);
        pn2.setLayout(null);

        lblSearch.setFont(fnt);
        lblSearch.setBounds(10, 75, 60, 40);
        pn2.add(lblSearch);

        lblCount.setFont(fnt);
        lblCount.setBounds(155, 75, 60, 40);
        pn2.add(lblCount);


        lblCname.setFont(fnt);
        lblCname.setBounds(20, 15, 120, 30);
        pn2.add(lblCname);

        lblCphoneNum.setFont(fnt);
        lblCphoneNum.setBounds(175, 15, 300, 30);
        pn2.add(lblCphoneNum);



        jtfSearch.setBounds(45, 75, 100, 40);
        pn2.add(jtfSearch);

        jtfCount.setBounds(190, 75, 100, 40);
        pn2.add(jtfCount);

        //ENROLL BTN
        btnEnroll.setBounds(300,75,60,40);
        btnEnroll.setFont(new Font("",Font.BOLD, 13));
        pn2.add(btnEnroll);


        lblItemList.setFont(fnt);
        lblItemList.setBounds(20, 145, 200, 30);
        pn2.add(lblItemList);

        lblstate.setFont(fnt);
        lblstate.setBounds(210,145,140,30);
        pn2.add(lblstate);


        lblstate.setFont(fnt);
        lblstate.setBounds(210,145,140,30);
        pn2.add(lblstate);

        // -----------ItemList add-----------
        jspItem.setBounds(10, 180, 360, 160);
        pn2.add(jspItem);

        // -----------MyList-----------
        lblMyList.setFont(fnt);
        lblMyList.setBounds(20, 345, 200, 30);
        pn2.add(lblMyList);

        jspMyList.setBounds(10, 380, 310, 70);
        pn2.add(jspMyList);

        btnDelete.setFont(fntDelete);
        btnDelete.setBounds(320, 390, 60, 40);

        pn2.add(btnDelete);


        // -----------PayMsg-----------
        lblMsg.setFont(fnt);
        lblMsg.setBounds(30, 450, 150, 60);
        pn2.add(lblMsg);

        btnPay.setBounds(285, 465, 90, 30);
        pn2.add(btnPay);
        add(pn2);
        pn2.setVisible(false);

        //pack();
        setVisible(true);
    }

}