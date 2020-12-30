package com.company.View;

import javax.swing.*;
import java.awt.*;

public class ShoppingView extends JFrame {

    //private JPanel selectProductPanel, productListPanel, myShoppingPanel;
    private JLabel lblProduct, lblSearch, lblCount, lblItemList, lblMyList, lblMsg;
    private JTextField jtfSearch, jtfCount;
    private JComboBox cb;
    private JTable tbItem, tbMyList;
    private JScrollPane jspItem, jspMyList;
    private JButton btnDelete1, btnDelete2, btnPay;
    private Font fnt, fntDelete;

    public ShoppingView() {

        // 폰트 설정
        fnt = new Font("Dialog", Font.BOLD, 15);
        fntDelete = new Font("Dialog", Font.BOLD, 10);

        // -----------label, textfield-----------
        lblProduct = new JLabel("상품");
        lblSearch = new JLabel("검색");
        lblCount = new JLabel("수량");

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
        tbItem = new JTable(Itemcontents, Itemcolumns);
        jspItem = new JScrollPane(tbItem, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // -----------MyList-----------
        lblMyList = new JLabel("내가 담은 List(장바구니)");

        // table에 들어갈 rows, columns 정의
        String MyListcolumns[]={"No.", "A", "B", "C", "수량"};
        String MyListcontents[][]={
                {"1", "물건1", " ", " ", " "},
                {"2", "물건2", " ", " ", " "},
                {"3", "물건3", " ", " ", " "},
                {"4", " ", " ", " ", " "},
                {"5", " ", " ", " ", " "},
                {"6", " ", " ", " ", " "},
                {"7", " ", " ", " ", " "},
                {"8", " ", " ", " ", " "},
                {"9", " ", " ", " ", " "},
        };

        // table 생성
        tbMyList = new JTable(MyListcontents, MyListcolumns);
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
        setSize(400, 520);
        setLayout(null);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        // -----------label, textfield add-----------
        lblProduct.setFont(fnt);
        lblProduct.setBounds(20, 20, 60, 40);
        add(lblProduct);

        lblSearch.setFont(fnt);
        lblSearch.setBounds(20, 70, 60, 40);
        add(lblSearch);

        lblCount.setFont(fnt);
        lblCount.setBounds(210, 70, 60, 40);
        add(lblCount);

        cb.setBounds(90, 25, 80, 30);
        add(cb);

        jtfSearch.setBounds(90, 75, 100, 30);
        add(jtfSearch);

        jtfCount.setBounds(280, 75, 70, 30);
        add(jtfCount);

        lblItemList.setFont(fnt);
        lblItemList.setBounds(20, 115, 200, 30);
        add(lblItemList);

        // -----------ItemList add-----------
        jspItem.setBounds(10, 150, 360, 160);
        add(jspItem);

        // -----------MyList-----------
        lblMyList.setFont(fnt);
        lblMyList.setBounds(20, 315, 200, 30);
        add(lblMyList);

        jspMyList.setBounds(10, 350, 310, 70);
        add(jspMyList);

        btnDelete1.setFont(fntDelete);
        btnDelete1.setBounds(320, 373, 60, 15);
        add(btnDelete1);

        btnDelete2.setFont(fntDelete);
        btnDelete2.setBounds(320, 388, 60, 15);
        add(btnDelete2);

        // -----------PayMsg-----------
        lblMsg.setFont(fnt);
        lblMsg.setBounds(30, 420, 150, 60);
        add(lblMsg);

        btnPay.setBounds(285, 435, 90, 30);
        add(btnPay);

        //pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new ShoppingView();
    }

}