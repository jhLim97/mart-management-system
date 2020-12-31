package com.company.Model;

import com.company.Controller.ProgramManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {

    private AccountDTO account;
    private String sql;
    Connection conn;

    PreparedStatement pstmt;
    ResultSet rs;

    public AccountDAO(){
        account = new AccountDTO();
        sql = "select * from account where id = ?";

        ProgramManager.getInstance().connectDB();

        try{


        }catch(Exception e){
            e.printStackTrace();
        }


        ProgramManager.getInstance().closeDB();

    }




}
