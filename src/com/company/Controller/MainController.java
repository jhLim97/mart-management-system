package com.company.Controller;

<<<<<<< HEAD
import java.sql.SQLException;
=======
>>>>>>> 17264d35ecf291b28383368a0fefa28424ce7abc
import java.util.Scanner;

public class MainController {

<<<<<<< HEAD
    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextByte();

        ProgramManager manager = ProgramManager.getInstance();
        switch (n) {
            case 1:
                manager.setState(new LoginState());
                break;
            case 2:
                manager.setState(new MainState());
                break;
            case 3:
                manager.setState(new CustomerManageState());
                break;
            case 4:
                manager.setState(new OrderManageState());
                break;
        }
        manager.drawMainView();
        manager.getState().drawPanel();

=======
    public static void main(String[] args) {

        ProgramManager manager = ProgramManager.getInstance();
        manager.setLoginState();

//        manager.setMainState();
//        manager.setCustomerManageState();
//        manager.setOrderManageState();
;
>>>>>>> 17264d35ecf291b28383368a0fefa28424ce7abc
    }
}