package com.company.Model;

import com.company.Controller.ProgramManager;

import java.sql.*;
import java.util.AbstractCollection;
import java.util.ArrayList;

public class AccountDAO {
    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://mms.crgsa3qt3jqa.ap-northeast-2.rds.amazonaws.com/mms?user=jaewon&password=wlfkf132";

    private AccountDTO account;
    private String sql;

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    ArrayList<AccountDTO> accountList;

    public AccountDAO(){
        accountList = getAll();
    }

    public void connectDB() {
        try {
            Class.forName(jdbcDriver);

            conn = DriverManager.getConnection(jdbcUrl, "jaewon", "wlfkf132");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void closeDB() {
        try {
            pstmt.close();
            if(rs != null) rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ArrayList<AccountDTO> getAll(){
        sql = "select * from Accounts";
        connectDB();
        ArrayList<AccountDTO> accountList = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){

                AccountDTO dto = new AccountDTO();
                dto.setId(rs.getString("id"));
                dto.setPassword(rs.getString("passwd"));
                dto.setIsSupperUser(rs.getBoolean("is_superuser"));
                dto.setIsStaff(rs.getBoolean("is_Staff"));
                dto.setUserName(rs.getString("user_name"));
                dto.setIsLogin(rs.getBoolean("is_login"));
                accountList.add(dto);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        closeDB();
        return accountList;
    }
    public boolean setLogin(String id, String PW){
        int check=0;
        sql = "update Accounts set is_login = ? where id = ? and passwd = ?";
        connectDB();
        try {

            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1,true);
            pstmt.setString(2,id);
            pstmt.setString(3,PW);
            check = pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeDB();
        if(check != 0) return true;
        return false;
    }
    public boolean setLogout(String id, String PW){
        int check=0;
        sql = "update Accounts set is_login = ? where id = ? and passwd = ?";
        connectDB();
        try {

            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1,false);
            pstmt.setString(2,id);
            pstmt.setString(3,PW);
            check = pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeDB();
        if(check != 0) return true;
        return false;
    }
    public boolean loginProgram(String id, String PW){

        for(AccountDTO account : accountList) {
            System.out.println("Check");
            if( account.getId().equals(id) ) {
                if (account.getPassword().equals(PW)) {
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


    public boolean makeAccount(AccountDTO account){
        int check = 0;
        sql = "insert into Accounts(id, passwd, is_superuser, is_staff, user_name, is_login) values(?, ?, ?, ?, ?, ?)";
        connectDB();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,account.getId());
            pstmt.setString(2,account.getPassword());
            pstmt.setBoolean(3,account.getIsSupperUser());
            pstmt.setBoolean(4,account.getIsStaff());
            pstmt.setString(5,account.getUserName());
            pstmt.setBoolean(6,account.getIsLogin());
            check = pstmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
        if(check != 0) return true;
        return false;
    }

}
