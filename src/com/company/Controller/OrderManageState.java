package com.company.Controller;

import com.company.View.MainView;
import com.company.View.OrderListViewPanel;
import com.company.View.ProductViewPanel;

import javax.swing.*;

public class OrderManageState implements State{
    OrderListViewPanel listViewPanel;
    MainView productView;
    @Override
    public void drawFrame() {

    }

    @Override
    public void drawPanel() {
        ProgramManager.getInstance().getMainView().drawMainPanel();
        ProgramManager.getInstance().getMainView().drawOrderListViewPanel();
    }

    @Override
    public void applyListener() {
        productView = ProgramManager.getInstance().getMainView();
        productView.productButton.addActionListener(e -> {
            //productView.productViewPanel.setVisible(false);
            //if(productView.customerViewPanel!=null) productView.customerViewPanel.setVisible(false); // 임준 수정중..
            productView.orderListViewPanel.setVisible(false); // 임준 수정중..
            if(productView.productViewPanel!=null) productView.productViewPanel.setVisible(true); // 임준 테스트
            else ProgramManager.getInstance().setMainState(); // 임준 테스트
            //ProgramManager.getInstance().setMainState();

        });
        productView.orderListButton.addActionListener(e -> {
            //productView.productViewPanel.setVisible(false);
            //if(productView.customerViewPanel!=null) productView.customerViewPanel.setVisible(false); // 임준 수정중..
            //if(productView.productViewPanel!=null) productView.productViewPanel.setVisible(false); // 임준 수정중..
            //ProgramManager.getInstance().setOrderManageState();

        });
        productView.customerButton.addActionListener(e -> {
            //productView.productViewPanel.setVisible(false);
            productView.orderListViewPanel.setVisible(false); // 임준 수정중..
            if(productView.customerViewPanel!=null) productView.customerViewPanel.setVisible(true); // 임준 테스트
            else ProgramManager.getInstance().setCustomerManageState(); // 임준 테스트 --> state 가 변경이 안되므로 수정중..
            //if(productView.productViewPanel!=null) productView.productViewPanel.setVisible(false); // 임준 수정중..
            //ProgramManager.getInstance().setCustomerManageState();

        });

        listViewPanel = ProgramManager.getInstance().getMainView().orderListViewPanel;
        mmsListener.getInstance().orderListViewPanelListener(listViewPanel);
    }

    @Override
    public void update() {

    }
}
