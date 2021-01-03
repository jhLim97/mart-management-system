package com.company.Controller;

import com.company.Model.AccountDAO;
import com.company.Model.AccountDTO;
import com.company.Model.Message;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class LoginController{
    AccountDAO adao = new AccountDAO();
    AccountDTO adto;
    public boolean loginSuccess(String id, String pw) {
        ArrayList<AccountDTO> accountList = adao.getAll();
        for(AccountDTO account : accountList) {
            System.out.println("Check");
            if( account.getId().equals(id) ) {
                if (account.getPassword().equals(pw)) {
                    System.out.println("로그인에 성공하였습니다!");
                    return true;
                } else {
                    System.out.println("비밀번호가 틀렸습니다.");
                    return false;
                }
            }
        }
        System.out.println("입력하신 아이디는 없는 아이디입니다.");
        return false;
    }
}
