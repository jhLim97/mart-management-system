package com.company.Controller;

import com.company.View.*;

import java.sql.*;

public class ProgramManager {

    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/mms?&serverTimezone=Asia/Seoul&useSSL=false";
    Connection conn;
    private MainState mainState;
    private LoginState loginState;
    private OrderManageState orderManageState;
    private CustomerManageState customerManageState;
    private CustomerController CC;
    public String id,pw;
    private ChattingController chattingController;
    private State state;

    private static ProgramManager s_Instance;
    public static ProgramManager getInstance(){
        if (s_Instance == null) s_Instance = new ProgramManager();
        return s_Instance;
    }

    public ChattingController getChattingController() {
        if(chattingController == null) chattingController = new ChattingController();
        return chattingController;
    }


    // --------- 준혁 컨트롤러 접근 개체 생성 ------------
    MainController mainController;


    public MainController getMainController() {
        if(mainController == null) mainController = new MainController();
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    OrderController orderController;

    public OrderController getOrderController() {
        if(orderController == null) orderController = new OrderController();
        return orderController;
    }

    ShoppingController shoppingController;

    public ShoppingController getShoppingController() {
        if(shoppingController == null) {
            try {
                shoppingController = new ShoppingController();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        return shoppingController;
    }
    // ----------------------------------------------



    ProductController PC;

    public CustomerController getCC() {
        if(CC == null) CC = new CustomerController();
        return CC;
    }

    public ProductController getPC() throws SQLException, ClassNotFoundException {
        if(PC ==null) PC= new ProductController(ViewManager.getInstance().getMainView().productViewPanel);
        return PC;
    }

    public void setCC(CustomerController CC) {
        this.CC = CC;
    }
    public void setPC(ProductController PC){ this.PC = PC;}


    public void setMainState(){
        if(mainState == null) {
            mainState = new MainState();
            mainState.draw();
        }
        if(state instanceof LoginState) {
            ViewManager.getInstance().getMainView().mainViewPanel.messageLabel.setText(id + " 님이 로그인 하셨습니다.");
            try {
                setPC(getPC());
            }catch(Exception e) { }
        }
        this.state = mainState;
        mainState.drawPanel();
    }
    public void setLoginState() {
        if(loginState == null) {
            loginState = new LoginState();
            loginState.draw();
        } else {
            loginState.drawPanel();
        }

        this.state = loginState;
    }
    public void setOrderManageState(){
        if(orderManageState == null) {
            orderManageState = new OrderManageState();
            orderManageState.applyListener();
        }
        orderManageState.drawPanel();
        this.state = orderManageState;

    }
    public void setCustomerManageState(){
        if(customerManageState == null) {
            customerManageState = new CustomerManageState();
            customerManageState.applyListener();
        }
        this.state = customerManageState;
        customerManageState.drawPanel();
        setCC(getCC());
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }



}
