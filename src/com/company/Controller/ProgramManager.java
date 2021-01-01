package com.company.Controller;

import com.company.View.*;

import java.sql.*;

public class ProgramManager {

    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/mms?&serverTimezone=Asia/Seoul&useSSL=false";
    Connection conn;
    MainState mainState;
    LoginState loginState;
    OrderManageState orderManageState;
    CustomerManageState customerManageState;
    CustomerController CC;

    private JoinView joinView;
    private ProductCRUDView productCRUDView;
    private ShoppingView shoppingView;
    private CustomerManageView customerManageView;
    private OrderListViewPanel orderListViewPanel;

    private MainView mainView;
    private State state;

    private static ProgramManager s_Instance;
    public static ProgramManager getInstance(){
        if (s_Instance == null) s_Instance = new ProgramManager();
        return s_Instance;
    }


    // --------- 준혁 컨트롤러 접근 개체 생성 ------------
    OrderController orderController;

    public OrderController getOrderController() {
        if(orderController == null) orderController = new OrderController();
        return orderController;
    }
    // ----------------------------------------------


    public ShoppingView getShoppingView() {
        if(shoppingView == null) shoppingView = new ShoppingView();
        return shoppingView;
    }
    public CustomerController getCC() {
        if(CC == null) CC = new CustomerController();
        return CC;
    }

    public void setCC(CustomerController CC) {
        this.CC = CC;
    }

    public OrderListViewPanel getOrderListViewPanel() {
        if(orderListViewPanel == null) orderListViewPanel = new OrderListViewPanel();
        return orderListViewPanel;
    }

    public MainView getMainView() {
        if(mainView == null) mainView = new MainView();
        return mainView;
    }

    public void setMainState(){
        if(mainState == null) mainState = new MainState();
        if(state instanceof LoginState) {
            mainState.drawFrameInit();
            this.state = mainState;
            return;
        }

        this.state = mainState;
        mainState.mainView.productViewPanel.setVisible(true);
        state.applyListener();
    }
    public void setLoginState() {

        if(loginState == null) loginState = new LoginState();
        this.state = loginState;
        state.draw();
    }
    public void setOrderManageState(){
        if(orderManageState == null) orderManageState = new OrderManageState();
        this.state = orderManageState;
        if(mainView.orderListViewPanel == null) { mainView.drawOrderListViewPanel();}
        else mainView.orderListViewPanel.setVisible(true);
        state.applyListener();

    }
    public void setCustomerManageState(){

        if(customerManageState == null) customerManageState = new CustomerManageState();
        this.state = customerManageState;
        if(mainView.customerViewPanel == null) mainView.drawCustomerViewPanel();
        else mainView.customerViewPanel.setVisible(true);
        state.applyListener();
        setCC(getCC());
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
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
