package com.company.View;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChattingView extends JFrame{
    JPanel chatNamePanel;
    JLabel chatNameLabel;
    JPanel msgPanel;
    public JTextField msgInput;
    public JButton exitButton;
    JTextArea msgOut;

    String id;

    public ChattingView() {
        setTitle("::멀티채팅::");
        setLayout(new BorderLayout());

        chatNamePanel = new JPanel();
        chatNamePanel.setLayout(new BorderLayout());

        chatNameLabel = new JLabel("대화명:");

        msgOut = new JTextArea(10,30);

        msgPanel = new JPanel();
        msgPanel.setLayout(new BorderLayout());

        msgInput = new JTextField();
        exitButton = new JButton("닫기");
    }

    public void drawView() {

        chatNamePanel.add(chatNameLabel, BorderLayout.WEST);
        add(chatNamePanel, BorderLayout.PAGE_START);

        msgOut.setEditable(false);

        JScrollPane scroll = new JScrollPane(msgOut);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll, BorderLayout.CENTER);

        msgPanel.add(msgInput, BorderLayout.CENTER);
        msgPanel.add(exitButton, BorderLayout.EAST);

        add(msgPanel, BorderLayout.PAGE_END);

        setSize(400,300);
    }

    public void refreshData(String msg) {
        msgOut.append(msg + "\n");
    }

    public static void main(String[] args) {
        ChattingView cv = new ChattingView();
    }

    public void addButtonActionListener(ActionListener listener) {
        exitButton.addActionListener(listener);
        msgInput.addActionListener(listener);
    }
}
