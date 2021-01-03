package com.company.View;

import com.company.Controller.ProgramManager;
import com.company.Controller.mmsListener;

public class ViewManager {

    private JoinView joinView;
    private ProductCRUDView productCRUDView;
    private ShoppingView shoppingView;
    private CustomerManageView customerManageView;
    private OrderListViewPanel orderListViewPanel;
    private ChattingView chattingView;
    private MainView mainView;
    private boolean isShoppingViewOpen;
    private static ViewManager s_Instance;


    public static ViewManager getInstance(){
        if (s_Instance == null) s_Instance = new ViewManager();
        return s_Instance;
    }

    public void joinViewOpen(){
        if(joinView == null) joinView = new JoinView();
        joinView.drawView();
    }

    public boolean isShoppingViewOpen() {
        return isShoppingViewOpen;
    }

    public void shoppingViewOpen(){
        if(shoppingView == null) shoppingView = new ShoppingView();
        shoppingView.setVisible(true);
    }
    public ChattingView getChattingView() {
        if(chattingView == null) chattingView = new ChattingView();
        return chattingView;
    }
    public ShoppingView getShoppingView() {
        if(shoppingView == null){
            isShoppingViewOpen = true;
            shoppingView = new ShoppingView();
            shoppingView.drawView();
            mmsListener.getInstance().shoppingViewListener(shoppingView);
        }
        return shoppingView;
    }
    public OrderListViewPanel getOrderListViewPanel() {
        if(orderListViewPanel == null) orderListViewPanel = new OrderListViewPanel();
        return orderListViewPanel;
    }

    public MainView getMainView() {
        if(mainView == null) mainView = new MainView();
        return mainView;
    }
    public JoinView getJoinView() {
        if(joinView == null) joinView = new JoinView();
        return joinView;
    }

    public ProductCRUDView getProductCRUDView() {
        if(productCRUDView == null) productCRUDView = new ProductCRUDView();
        return productCRUDView;
    }

    public CustomerManageView getCustomerManageView() {
        if(customerManageView == null) customerManageView = new CustomerManageView();
        return customerManageView;
    }

    public void setCustomerManageView(CustomerManageView customerManageView) {
        this.customerManageView = customerManageView;
    }

}
