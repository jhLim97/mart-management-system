package com.company.View;

import javax.swing.*;
import java.awt.*;

public class ProductViewPanel extends JPanel {
    JPanel menuBarPanel, bodyPanel, txtAreaPanel;
    JTextField txtSearch;
    JButton searchButton, addButton, updateButton, deleteButton;
    JTable productTable;
    JTextArea smallAMountArea, almostExpiredArea;
    JScrollPane scroll1, scroll2, scroll3;
    String tableHeader[] = {"", "Code","Name","Price","Location","Date","Count","State"}, tableContents[][];

    public static void main(String[] args) {
        MainView app = new MainView();
        app.drawView();
        app.drawMainPanel();
        ProductViewPanel productView = new ProductViewPanel();
        productView.drawView();
        app.add(productView, BorderLayout.CENTER);
        app.setVisible(true);
    }

    public ProductViewPanel() {
        setLayout(new BorderLayout());

        menuBarPanel = new JPanel();
        menuBarPanel.setLayout(new FlowLayout());

        txtSearch = new JTextField(20);
        searchButton = new JButton("찾기");

        addButton = new JButton("등록");
        updateButton = new JButton("수정");
        deleteButton = new JButton("삭제");

        bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());

        tableContents = new String[][]{
                {"1", " "," ", " ", " ", " ", " ", " "},
                {"2", " "," ", " ", " ", " ", " ", " "},
                {"3", " "," ", " ", " ", " ", " ", " "},
                {"4", " "," ", " ", " ", " ", " ", " "}
        };
        productTable = new JTable(tableContents, tableHeader);

        txtAreaPanel = new JPanel();
        txtAreaPanel.setLayout(new GridLayout(2,1));

        smallAMountArea = new JTextArea(15,30);
        almostExpiredArea = new JTextArea(15,30);

        setVisible(true);
    }

    public void drawView() {
        menuBarPanel.add(txtSearch);
        menuBarPanel.add(searchButton);
        menuBarPanel.add(addButton);
        menuBarPanel.add(updateButton);
        menuBarPanel.add(deleteButton);
        add(menuBarPanel, BorderLayout.PAGE_START);

        scroll1 = new JScrollPane(productTable);
        scroll2 = new JScrollPane(smallAMountArea);
        scroll3 = new JScrollPane(almostExpiredArea);

        scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        bodyPanel.add(scroll1, BorderLayout.CENTER);

        scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        txtAreaPanel.add(scroll2);
        txtAreaPanel.add(scroll3);

        bodyPanel.add(txtAreaPanel, BorderLayout.EAST);

        add(bodyPanel, BorderLayout.CENTER);

    }
}