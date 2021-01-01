package com.company.Controller;

import com.company.View.*;

import java.sql.*;

public class ProgramManager {

    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/MMS?&serverTimezone=Asia/Seoul&useSSL=false";
    Connection conn;

    MainState mainState;
    LoginState loginState;
    OrderManageState orderManageState;
    CustomerManageState customerManageState;

    // --------- 준혁 컨트롤러 접근 개체 생성 ------------
    OrderController orderController;

    public OrderController getOrderController() {
        if(orderController == null) orderController = new OrderController();
        return orderController;
    }
    // ----------------------------------------------

    public JoinView getJoinView() {
        if(joinView == null) joinView = new JoinView();
        return joinView;
    }

    public void setJoinView(JoinView joinView) {
        this.joinView = joinView;
    }

    public ProductCRUDView getProductCRUDView() {
        if(productCRUDView == null) productCRUDView = new ProductCRUDView();
        return productCRUDView;
    }

    public void setProductCRUDView(ProductCRUDView productCRUDView) {
        this.productCRUDView = productCRUDView;
    }

    public ShoppingView getShoppingView() {
        if(shoppingView == null) shoppingView = new ShoppingView();
        return shoppingView;
    }

    public void setShoppingView(ShoppingView shoppingView) {
        this.shoppingView = shoppingView;
    }

    public CustomerManageView getCustomerManageView() {
        if(customerManageView == null) customerManageView = new CustomerManageView();
        return customerManageView;
    }

    public void setCustomerManageView(CustomerManageView customerManageView) {
        this.customerManageView = customerManageView;
    }

    public OrderListViewPanel getOrderListViewPanel() {
        if(orderListViewPanel == null) orderListViewPanel = new OrderListViewPanel();
        return orderListViewPanel;
    }

    public void setOrderListViewPanel(OrderListViewPanel orderListViewPanel) {
        this.orderListViewPanel = orderListViewPanel;
    }

    private JoinView joinView;
    private ProductCRUDView productCRUDView;
    private ShoppingView shoppingView;
    private CustomerManageView customerManageView;
    private OrderListViewPanel orderListViewPanel;

    private MainView mainView;
    private State state;

    public MainView getMainView() {
        return mainView;
    }

    public void drawMainView() {
        if(mainView == null) mainView = new MainView();
        mainView.drawView();
    }

    public void drawOrderListView() {
        if(mainView == null) mainView = new MainView();
        mainView.drawView();
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    public void setMainState(){
        this.state = mainState;
        if(mainState == null) mainState = new MainState();
        mainView.loginViewPanel.setVisible(false);
        drawMainView();
        mainView.drawMainPanel();
        mainView.drawProductViewPanel();
        mainState.applyListener();

    }
    public void setLoginState() {
        this.state = loginState;
        if(loginState == null) loginState = new LoginState();
        drawMainView();
        mainView.drawLoginPanel();
        loginState.applyListener();

    }
    public void setOrderManageState(){
        this.state = orderManageState;
        if(orderManageState == null) orderManageState = new OrderManageState();
        mainView.drawOrderListViewPanel();
        orderManageState.applyListener();

    }
    public void setCustomerManageState(){
        this.state = customerManageState;
        if(customerManageState == null) customerManageState = new CustomerManageState();
        mainView.drawCustomerViewPanel();
        customerManageState.applyListener();
    }

    private static ProgramManager s_Instance;
    public static ProgramManager getInstance(){
        if (s_Instance == null) s_Instance = new ProgramManager();
        return s_Instance;
    }


    public void connectDB(Connection conn){
        try{
            // 1단계 : JDBC 드라이버 로드
            Class.forName(jdbcDriver);
            // 2단계 : 데이터베이스 연결
            conn = DriverManager.getConnection(jdbcUrl,"root","root");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void closeDB(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try {
            // 6단계 : 연결 해제
            pstmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
