package com.company.Controller;

import com.company.View.ShoppingLogin;

import java.sql.SQLException;
import java.util.Scanner;

public class MainController{

    public static void main(String[] args) {

        ProgramManager manager = ProgramManager.getInstance();
        manager.setLoginState();

    }
}