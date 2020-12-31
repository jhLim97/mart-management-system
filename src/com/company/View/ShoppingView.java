package com.company.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ShoppingView extends JFrame {

    //private JPanel selectProductPanel, productListPanel, myShoppingPanel;
    public JLabel lblProduct, lblSearch, lblCount, lblItemList, lblMyList, lblMsg, lblCname, lblCphoneNum;
    public JTextField jtfSearch, jtfCount;
    public JComboBox cb;
    public JTable tbItem, tbMyList;
    public JScrollPane jspItem, jspMyList;
    public JButton btnDelete1, btnDelete2, btnPay;
    public Font fnt, fntDelete;
    public DefaultTableModel modelItemList, modelMyList;

    public ShoppingView() {

        // 폰트 설정
        fnt = new Font("Dialog", Font.BOLD, 15);
        fntDelete = new Font("Dialog", Font.BOLD, 10);

        // -----------label, textfield-----------
        lblProduct = new JLabel("상품");
        lblSearch = new JLabel("검색");
        lblCount = new JLabel("수량");
        lblCname = new JLabel("고객이름 : 4조");
        lblCphoneNum = new JLabel("고객 번호 : 010-8890-2749");

        cb = new JComboBox(); // 상품을 콤보박스에서 조회할 수 있도록 생성
        jtfSearch = new JTextField();
        jtfCount = new JTextField();

        // -----------ItemList-----------
        lblItemList = new JLabel("구매 할 수 있는 물품 List");

        // table에 들어갈 rows, columns 생성
        String Itemcolumns[]={"No.", "A", "B", "C", "D", "E"};
        String Itemcontents[][]={
                {"1", " ", " ", " ", " ", " "},
                {"2", " ", " ", " ", " ", " "},
                {"3", " ", " ", " ", " ", " "},
                {"4", " ", " ", " ", " ", " "},
                {"5", " ", " ", " ", " ", " "},
                {"6", " ", " ", " ", " ", " "},
                {"7", " ", " ", " ", " ", " "},
                {"8", " ", " ", " ", " ", " "},
                {"9", " ", " ", " ", " ", " "},
        };

        // table 생성
        modelItemList = new DefaultTableModel(Itemcontents, Itemcolumns);
        tbItem = new JTable(modelItemList);
        jspItem = new JScrollPane(tbItem, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // -----------MyList-----------
        lblMyList = new JLabel("내가 담은 List(장바구니)");

        // table에 들어갈 rows, columns 정의
        String MyListcolumns[]={"No.", "A", "B", "C", "수량"};
        String MyListcontents[][]={
                {"1", "5", "홈런볼", "1500", "3"},
                {"2", "2", "다우니", "4700", "8"},
                {"3", "3", "치킨", "15000", "11"},
        };

        // table 생성
        modelMyList = new DefaultTableModel(MyListcontents, MyListcolumns);
        tbMyList = new JTable(modelMyList);
        jspMyList = new JScrollPane(tbMyList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        btnDelete1 = new JButton("삭제");
        btnDelete2 = new JButton("삭제");

        // -----------PayMsg-----------
        lblMsg = new JLabel("결제 금액 : 75,000원");
        btnPay = new JButton("결제하기");

        drawView();
    }

    public void drawView() {

        setTitle("Shopping View");
        setSize(400, 550);
        setLayout(null);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        // -----------label, textfield add-----------
        lblProduct.setFont(fnt);
        lblProduct.setBounds(20, 50, 60, 40);
        add(lblProduct);

        lblSearch.setFont(fnt);
        lblSearch.setBounds(20, 100, 60, 40);
        add(lblSearch);

        lblCount.setFont(fnt);
        lblCount.setBounds(210, 100, 60, 40);
        add(lblCount);

        lblCname.setFont(fnt);
        lblCname.setBounds(20, 15, 120, 30);
        add(lblCname);

        lblCphoneNum.setFont(fnt);
        lblCphoneNum.setBounds(175, 15, 300, 30);
        add(lblCphoneNum);

        cb.setBounds(90, 55, 80, 30);
        add(cb);

        jtfSearch.setBounds(90, 105, 100, 30);
        add(jtfSearch);

        jtfCount.setBounds(280, 105, 70, 30);
        add(jtfCount);

        lblItemList.setFont(fnt);
        lblItemList.setBounds(20, 145, 200, 30);
        add(lblItemList);

        // -----------ItemList add-----------
        jspItem.setBounds(10, 180, 360, 160);
        add(jspItem);

        // -----------MyList-----------
        lblMyList.setFont(fnt);
        lblMyList.setBounds(20, 345, 200, 30);
        add(lblMyList);

        jspMyList.setBounds(10, 380, 310, 70);
        add(jspMyList);

        btnDelete1.setFont(fntDelete);
        btnDelete1.setBounds(320, 403, 60, 15);
        add(btnDelete1);

        btnDelete2.setFont(fntDelete);
        btnDelete2.setBounds(320, 418, 60, 15);
        add(btnDelete2);

        // -----------PayMsg-----------
        lblMsg.setFont(fnt);
        lblMsg.setBounds(30, 450, 150, 60);
        add(lblMsg);

        btnPay.setBounds(285, 465, 90, 30);
        add(btnPay);

        //pack();
        setVisible(true);
    }

    public void addOrderActionListner(ActionListener listener) {
        btnPay.addActionListener(listener);
        // 다른 리스너들은 추가하기...

    } // addButtonActionListener()



}