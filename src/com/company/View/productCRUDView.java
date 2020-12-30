package com.company.View;

import javax.swing.*;
import java.awt.*;

public class productCRUDView extends JFrame {
    public JLabel codeLabel,nameLabel,priceLabel,locationLabel,expDateLabel,countLabel;
    public JTextField codeText,nameText,priceText,locationText,expDateText,countText;
    public JButton completeButton;
    public JPanel centerPanel,bottomPanel;

    public static void main(String[] args) { new productCRUDView().drawView();}

    public productCRUDView(){

        codeLabel = new JLabel("Code");
        nameLabel = new JLabel("Name");
        priceLabel = new JLabel("Price");
        locationLabel = new JLabel("Location");
        expDateLabel = new JLabel("Expiration date");
        countLabel = new JLabel("Count");

        codeText = new JTextField(15);
        nameText = new JTextField(15);
        priceText = new JTextField(15);
        locationText = new JTextField(15);
        expDateText = new JTextField(15);
        countText = new JTextField(15);

        completeButton = new JButton("Complete");

        centerPanel = new JPanel();
        bottomPanel = new JPanel();

    }
    public void drawView(){

        setTitle("상품 등록/수정/삭제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,700);
        setLayout(new BorderLayout());

        centerPanel.setSize(700,500);
        centerPanel.setLayout(new GridLayout(0,2));
        centerPanel.add(codeLabel);
        centerPanel.add(codeText);
        centerPanel.add(nameLabel);
        centerPanel.add(nameText);
        centerPanel.add(priceLabel);
        centerPanel.add(priceText);
        centerPanel.add(locationLabel);
        centerPanel.add(locationText);
        centerPanel.add(expDateLabel);
        centerPanel.add(expDateText);
        centerPanel.add(countLabel);
        centerPanel.add(countText);
        centerPanel.add(completeButton);
        add(centerPanel,BorderLayout.CENTER);

        setVisible(true);
    }
}
