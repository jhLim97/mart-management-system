package com.company.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
        setSize(400,470);
        setLayout(new BorderLayout());

        centerPanel.setSize(400,470);
        centerPanel.setLayout(null);

        codeLabel.setBounds(10,10,190,50);
        codeLabel.setFont(new Font("", Font.BOLD, 20));
        centerPanel.add(codeLabel);
        codeText.setBounds(180,10,190,50);
        codeText.setFont(new Font("", Font.BOLD, 17));
        centerPanel.add(codeText);


        nameLabel.setBounds(10,70,190,50);
        nameLabel.setFont(new Font("", Font.BOLD, 20));
        centerPanel.add(nameLabel);
        nameText.setBounds(180,70,190,50);
        nameText.setFont(new Font("", Font.BOLD, 17));
        centerPanel.add(nameText);


        priceLabel.setBounds(10,130,190,50);
        priceLabel.setFont(new Font("", Font.BOLD, 20));
        centerPanel.add(priceLabel);
        priceText.setBounds(180,130,190,50);
        priceText.setFont(new Font("", Font.BOLD, 17));
        centerPanel.add(priceText);


        locationLabel.setBounds(10,190,190,50);
        locationLabel.setFont(new Font("", Font.BOLD, 20));
        centerPanel.add(locationLabel);
        locationText.setBounds(180,190,190,50);
        locationText.setFont(new Font("", Font.BOLD, 17));
        centerPanel.add(locationText);


        expDateLabel.setBounds(10,250,190,50);
        expDateLabel.setFont(new Font("", Font.BOLD, 20));
        centerPanel.add(expDateLabel);
        expDateText.setBounds(180,250,190,50);
        expDateText.setFont(new Font("", Font.BOLD, 17));
        centerPanel.add(expDateText);


        countLabel.setBounds(10,310,190,50);
        countLabel.setFont(new Font("", Font.BOLD, 20));
        centerPanel.add(countLabel);
        countText.setBounds(180,310,190,50);
        countText.setFont(new Font("", Font.BOLD, 17));
        centerPanel.add(countText);

        completeButton.setBounds(125, 370, 150,50);
        centerPanel.add(completeButton);
        add(centerPanel,BorderLayout.CENTER);

        setVisible(true);
    }

    public void completeButtonListener(ActionListener listener){
        completeButton.addActionListener(listener);
    }
}
