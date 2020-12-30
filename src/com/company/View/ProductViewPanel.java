package com.company.View;

import javax.swing.*;

public class ProductViewPanel extends JPanel {
    JPanel menuBarPanel, bodyPanel, txtAreaPanel;
    JTextField txtSearch;
    JButton findButton, addButton, updateButton, deleteButton;
    JTable productTable;
    JTextField smallAMountArea, almostExpiredArea;
    String tableHeader[] = {"Code","Name","Price","Location","Date","Count","State"}, tableContents[][];


    public ProductViewPanel() {
        menuBarPanel = new JPanel();

        txtSearch = new JTextField(20);
        findButton = new JButton("찾기");

        addButton = new JButton("등록");
        updateButton = new JButton("수정");
        deleteButton = new JButton("삭제");

        bodyPanel = new JPanel();

        tableContents = new String[3][7];
        productTable = new JTable(tableContents, tableHeader);



    }
}
