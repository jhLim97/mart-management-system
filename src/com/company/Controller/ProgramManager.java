package com.company.Controller;

<<<<<<< HEAD
import com.company.View.CustomerManageView;
import com.company.View.MainView;

public class ProgramManager {

=======
import com.company.Main;
import com.company.View.CustomerManageView;
import com.company.View.LoginViewPanel;
import com.company.View.MainView;

import java.sql.*;

public class ProgramManager {

    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/javadb?&serverTimezone=Asia/Seoul&useSSL=false";
    Connection conn;

    MainState mainState;
    LoginState loginState;
    OrderManageState orderManageState;
    CustomerManageState customerManageState;
    PreparedStatement pstmt;
    ResultSet rs;

>>>>>>> 17264d35ecf291b28383368a0fefa28424ce7abc
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
<<<<<<< HEAD
=======
    public void setMainState(){
        if(mainState == null) mainState = new MainState();
        drawMainView();
        mainView.drawMainPanel();
        mainView.drawProductViewPanel();
        mainState.applyListener();
        this.state = mainState;
    }
    public void setLoginState() {
        if(loginState == null) loginState = new LoginState();
        drawMainView();
        mainView.drawLoginPanel();
        loginState.applyListener();
        this.state = loginState;
    }
    public void setOrderManageState(){
        if(orderManageState == null) orderManageState = new OrderManageState();

        mainView.drawOrderListViewPanel();
        this.state = orderManageState;
    }
    public void setCustomerManageState(){
        if(customerManageState == null) customerManageState = new CustomerManageState();
        mainView.drawCustomerViewPanel();
        this.state = customerManageState;
    }
>>>>>>> 17264d35ecf291b28383368a0fefa28424ce7abc

    private static ProgramManager s_Instance;
    public static ProgramManager getInstance(){
        if (s_Instance == null) s_Instance = new ProgramManager();
        return s_Instance;
    }

<<<<<<< HEAD
=======

    public void connectDB(){
        try{
            // 1단계 : JDBC 드라이버 로드
            Class.forName(jdbcDriver);
            // 2단계 : 데이터베이스 연결
            conn = DriverManager.getConnection(jdbcUrl,"root","wlfkf132");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void closeDB(){
        try {
            // 6단계 : 연결 해제
            pstmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
>>>>>>> 17264d35ecf291b28383368a0fefa28424ce7abc
}
