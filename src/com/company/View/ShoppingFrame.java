package com.company.View;

import javax.swing.*;
import java.awt.*;

public class ShoppingFrame extends JFrame {

    //private JPanel selectProductPanel, productListPanel, myShoppingPanel;
    private JLabel lblProduct, lblSearch, lblCount, lblItemList, lblMyList, lblMsg;
    private JTextField jtfSearch, jtfCount;
    private JComboBox cb;
    private JTable tbItem, tbMyList;
    private JScrollPane jspItem, jspMyList;
    private JButton btnDelete1, btnDelete2, btnPay;

    public ShoppingFrame() {

        super("Shopping View");
        setSize(400, 520);
        setLayout(null);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        // 폰트 설정
        Font fnt = new Font("Dialog", Font.BOLD, 15);
        Font fntDelete = new Font("Dialog", Font.BOLD, 10);

        // -----------label, textfield-----------
        lblProduct = new JLabel("상품");
        lblProduct.setFont(fnt);
        lblProduct.setBounds(20, 20, 60, 40);

        lblSearch = new JLabel("검색");
        lblSearch.setFont(fnt);
        lblSearch.setBounds(20, 70, 60, 40);

        lblCount = new JLabel("수량");
        lblCount.setFont(fnt);
        lblCount.setBounds(210, 70, 60, 40);

        cb = new JComboBox(); // 상품을 콤보박스에서 조회할 수 있도록 생성
        cb.setBounds(90, 25, 80, 30);

        jtfSearch = new JTextField();
        jtfSearch.setBounds(90, 75, 100, 30);

        jtfCount = new JTextField();
        jtfCount.setBounds(280, 75, 70, 30);

        // -----------ItemList-----------
        lblItemList = new JLabel("구매 할 수 있는 물품 List");
        lblItemList.setFont(fnt);
        lblItemList.setBounds(20, 115, 200, 30);

        // table에 들어갈 rows, columns 정의
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
        jspItem.setBounds(10, 150, 360, 160);

        // -----------MyList-----------
        lblMyList = new JLabel("내가 담은 List(장바구니)");
        lblMyList.setFont(fnt);
        lblMyList.setBounds(20, 315, 200, 30);

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
        jspMyList.setBounds(10, 350, 310, 70);

        btnDelete1 = new JButton("삭제");
        lblMyList.setFont(fntDelete);
        btnDelete1.setBounds(320, 373, 60, 15);

        btnDelete2 = new JButton("삭제");
        lblMyList.setFont(fntDelete);
        btnDelete2.setBounds(320, 388, 60, 15);

        // -----------PayMsg-----------
        lblMsg = new JLabel("결제 금액 : 75,000원");
        lblMsg.setFont(fnt);
        lblMsg.setBounds(30, 420, 150, 60);

        btnPay = new JButton("결제하기");
        lblMyList.setFont(fnt);
        btnPay.setBounds(285, 435, 90, 30);

        drawView();
    }

    public void drawView() {

        // -----------label, textfield add-----------
        add(lblProduct);
        add(lblSearch);
        add(lblCount);

        add(cb);
        add(jtfSearch);
        add(jtfCount);
        add(lblItemList);

        // -----------ItemList add-----------
        add(jspItem);

        // -----------MyList-----------
        add(lblMyList);

        add(jspMyList);

        add(btnDelete1);
        add(btnDelete2);

        // -----------PayMsg-----------
        add(lblMsg);

        add(btnPay);

        //pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new ShoppingFrame();
    }

}
