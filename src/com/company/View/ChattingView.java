package com.company.View;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChattingView extends JFrame{
    JPanel chatNamePanel;
    JLabel chatNameLabel;
    JPanel msgPanel;
    JTextField msgInput;
    JButton enterButton;
    JTextArea msgOut;

    String id;

    public ChattingView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("::멀티채팅::");
        setLayout(new BorderLayout());

        chatNamePanel = new JPanel();
        chatNamePanel.setLayout(new BorderLayout());

        chatNameLabel = new JLabel("대화명:");

        chatNamePanel.add(chatNameLabel, BorderLayout.WEST);
        add(chatNamePanel, BorderLayout.PAGE_START);

        msgOut = new JTextArea(10,30);
        msgOut.setEditable(false);

        JScrollPane scroll = new JScrollPane(msgOut);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll, BorderLayout.CENTER);

        msgPanel = new JPanel();
        msgPanel.setLayout(new BorderLayout());

        msgInput = new JTextField();
        enterButton = new JButton("입력");

        msgPanel.add(msgInput, BorderLayout.CENTER);
        msgPanel.add(enterButton, BorderLayout.EAST);

        add(msgPanel, BorderLayout.PAGE_END);

        setSize(400,300);
        setVisible(true);
    }

    public void refreshData(String msg) {
        msgOut.append(msg);
    }

    public static void main(String[] args) {
        ChattingView cv = new ChattingView();
    }

    public void addButtonActionListener(ActionListener listener) {
        enterButton.addActionListener(listener);
        msgInput.addActionListener(listener);
    }
}
