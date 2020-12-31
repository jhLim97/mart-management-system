package com.company.Controller;

import java.sql.SQLException;
import java.util.Scanner;

public class MainController {

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

    }
}