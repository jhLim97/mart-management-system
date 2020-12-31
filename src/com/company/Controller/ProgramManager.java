package com.company.Controller;

import com.company.View.CustomerManageView;
import com.company.View.MainView;


import com.company.Main;
import com.company.View.CustomerManageView;
import com.company.View.LoginViewPanel;
import com.company.View.MainView;

import java.sql.*;

public class ProgramManager {

    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/MMS?&serverTimezone=Asia/Seoul&useSSL=false";

    MainState mainState;
    LoginState loginState;
    OrderManageState orderManageState;
    CustomerManageState customerManageState;

    private MainView mainView;
    private State state;

    public MainView getMainView() {
        return mainView;
    }

    public void drawMainView() {
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

}
