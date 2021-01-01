package com.company.Controller;

import com.company.Main;
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

    private JoinView joinView;
    private ProductCRUDView productCRUDView;
    private ShoppingView shoppingView;
    private CustomerManageView customerManageView;

    private MainView mainView;
    private State state;

    public MainView getMainView() {
        if(mainView == null) mainView = new MainView();
        return mainView;
    }

    public void setMainState(){

        if(state instanceof LoginState) {
            mainState = new MainState();
            mainState.drawFrameInit();
            this.state = mainState;
            return;
        }

        if(mainState == null) mainState = new MainState();
        this.state = mainState;
        mainState.mainView.productViewPanel.setVisible(true);
        state.applyListener();
    }
    public void setLoginState() {

        if(loginState == null) loginState = new LoginState();
        this.state = loginState;
        state.draw();
        state.applyListener();
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
            conn = DriverManager.getConnection(jdbcUrl,"root","wlfkf132");

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


}
