package com.company.View;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProductViewPanel extends JPanel {
    public JPanel menuBarPanel, bodyPanel, txtAreaPanel;
    public JTextField txtSearch;
    public JButton searchButton, addButton, updateButton, deleteButton;
    public JTable productTable;
    public JTextArea smallAMountArea, almostExpiredArea;
    public JScrollPane scroll1, scroll2, scroll3;
    public String tableHeader[] = { "Code","Name","Price","Location","Date","Count","State"}, tableContents[][];
    public DefaultTableModel tableModel;
    public JTextArea SUDtxt;
    public JLabel SUDLab;
    public boolean editMode=false;



    public ProductViewPanel() {
        setLayout(new BorderLayout());

        SUDtxt = new JTextArea();
        SUDLab = new JLabel("검색 정보 : ");


        menuBarPanel = new JPanel();
        menuBarPanel.setLayout(new FlowLayout());

        txtSearch = new JTextField(20);
        searchButton = new JButton("찾기");

        addButton = new JButton("등록");
        updateButton = new JButton("수정");
        deleteButton = new JButton("삭제");

        bodyPanel = new JPanel();
        bodyPanel.setLayout(null);

        tableModel = new DefaultTableModel(tableHeader,0);

        productTable = new JTable(tableModel);



        txtAreaPanel = new JPanel();
        txtAreaPanel.setLayout(new GridLayout(2,1));

        smallAMountArea = new JTextArea(15,30);
        almostExpiredArea = new JTextArea(15,30);

        setVisible(true);
    }

    public void drawView() throws SQLException, ClassNotFoundException {

        //new ProductController(this);

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
        scroll1.setBounds(0, 0,800,550);


        bodyPanel.add(scroll1);

        scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //여기

        txtAreaPanel.setBounds(800,0,375,640);

        //여기///////

        SUDtxt.setBounds(10,600,780,35);
        SUDtxt.setEditable(false);
        SUDtxt.setBackground(Color.LIGHT_GRAY);

        bodyPanel.add(SUDtxt);

        txtAreaPanel.add(scroll2);
        txtAreaPanel.add(scroll3);

        SUDLab.setBounds(10,550,780,40);
        SUDLab.setFont(new Font("",Font.BOLD,20));
        bodyPanel.add(SUDLab);
        bodyPanel.add(txtAreaPanel);

        add(bodyPanel, BorderLayout.CENTER);
    }

}