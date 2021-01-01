package com.company.Controller;

import java.sql.SQLException;
import java.util.Scanner;

public class MainController {


    public static void main(String[] args) {

        ProgramManager manager = ProgramManager.getInstance();
        manager.setLoginState();

    }
}