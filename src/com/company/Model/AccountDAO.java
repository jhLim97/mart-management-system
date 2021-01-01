package com.company.Model;

import com.company.Controller.ProgramManager;

import java.sql.*;
import java.util.AbstractCollection;
import java.util.ArrayList;

public class AccountDAO {
    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/MMS?&serverTimezone=Asia/Seoul&useSSL=false";

    private AccountDTO account;
    private String sql;

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    ArrayList<AccountDTO> accountList;
    public void connectDB() {
        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(jdbcUrl, "root", "ghrja132!@");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void closeDB() {
        try {
            pstmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ArrayList<AccountDTO> getAll(){
        sql = "select * from accounts";
        connectDB();
        accountList = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){

                AccountDTO dto = new AccountDTO();
                dto.setId(rs.getString("id"));
                dto.setPassword(rs.getString("passwd"));
                dto.setIsSupperUser(rs.getBoolean("is_superuser"));
                dto.setIsStaff(rs.getBoolean("isStaff"));
                dto.setUserName(rs.getString("user_name"));
                accountList.add(dto);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        closeDB();
        return accountList;
    }
    public boolean loginProgram(String id, String PW){
        sql = "select * from accounts where id = ?";
        account = new AccountDTO();
//        ProgramManager.getInstance().connectDB(conn);
        connectDB();
        try{

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            rs.next();
            account.setId(rs.getString("id"));
            account.setPassword(rs.getString("passwd"));
            account.setIsSupperUser(rs.getBoolean("is_superuser"));
            account.setIsStaff(rs.getBoolean("is_staff"));
            account.setUserName(rs.getString("user_name"));

        }catch(Exception e){
            e.printStackTrace();
        }

        //ProgramManager.getInstance().closeDB(conn,pstmt,rs);
        closeDB();
        if(account.getId().equals(id)  && account.getPassword().equals(PW)){
            return true;
        }
        return false;
    }

    public boolean makeAccount(AccountDTO account){
        int check = 0;
        sql = "insert into accounts(id, passwd, is_superuser, is_staff, user_name) values(?, ?, ?, ?, ?)";
        //ProgramManager.getInstance().connectDB(conn);
        connectDB();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,account.getId());
            pstmt.setString(2,account.getPassword());
            pstmt.setBoolean(3,account.getIsSupperUser());
            pstmt.setBoolean(4,account.getIsStaff());
            pstmt.setString(5,account.getUserName());
            check = pstmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
//        ProgramManager.getInstance().closeDB(conn,pstmt,rs);
        if(check != 0) return true;
        return false;
    }

}
