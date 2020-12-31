package com.company.Model;

import java.sql.*;
import java.util.ArrayList;

public class OrderHistoryDAO {

    Connection con;
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;
    String Driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
    String userid = "madang";
    String pwd = "madang";
    String sql;

    public OrderHistoryDAO() {

    }

    public void connectDB() {
        try {
            Class.forName(Driver);
            System.out.println("드라이버 로드 성공");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("데이터베이스 연결 준비...");
            con = DriverManager.getConnection(url, userid, pwd);
            System.out.println("데이터베이스 연결 성공");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            pstmt.close();
            if(rs != null) rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<OrderHistoryDTO> getAll() {
        connectDB();
        sql = "select * from OrderHistory";

        ArrayList<OrderHistoryDTO> datas = new ArrayList<OrderHistoryDTO>();

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                OrderHistoryDTO ordHis = new OrderHistoryDTO();
                ordHis.setHistory_id(rs.getInt("history_id"));
                ordHis.setOrder_code(rs.getInt("order_code"));
                ordHis.setPr_code(rs.getInt("pr_code"));
                ordHis.setPr_count(rs.getInt("pr_count"));
                ordHis.setPr_price(rs.getInt("pr_price"));
                datas.add(ordHis);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        closeDB();

        if(datas.isEmpty()) return null;
        else return datas;

    }

    public OrderHistoryDTO getOrderHistory(int historyId) {
        connectDB();
        sql = "select * from OrderHistory where history_id = ?";
        OrderHistoryDTO ordHis = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, historyId);
            rs = pstmt.executeQuery();
            rs.next();
            ordHis = new OrderHistoryDTO();
            ordHis.setHistory_id(rs.getInt("history_id"));
            ordHis.setOrder_code(rs.getInt("order_code"));
            ordHis.setPr_code(rs.getInt("pr_code"));
            ordHis.setPr_count(rs.getInt("pr_count"));
            ordHis.setPr_price(rs.getInt("pr_price"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDB();

        return ordHis;
    }

    public boolean addOrderHistory(OrderHistoryDTO ordHis) {
        connectDB();
        sql = "insert into OrderHistory(order_code, pr_code, pr_count, pr_price) value(?, ?, ?, ?)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, ordHis.getOrder_code());
            pstmt.setInt(2, ordHis.getPr_code());
            pstmt.setInt(3, ordHis.getPr_count());
            pstmt.setInt(4, ordHis.getPr_price());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        closeDB();

        return true;
    }

    // ------- 아래 함수는 사용 여부 미정 -------
    public boolean delOrderHistory(int historyId) {
        connectDB();
        sql = "delete from OrderHistory where history_id = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, historyId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        closeDB();

        return true;
    }

    // ------- 아래 함수는 사용 여부 미정 -------
    public boolean updateOrderHistory(OrderHistoryDTO ordHis) { // 파라미터의 객체 정보로 업데이트
        connectDB();
        sql = "update OrderHistory set order_code = ?, pr_code = ?, pr_count = ?, pr_price = ? where history_id = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, ordHis.getOrder_code());
            pstmt.setInt(2, ordHis.getPr_code());
            pstmt.setInt(3, ordHis.getPr_count());
            pstmt.setInt(4, ordHis.getPr_price());
            pstmt.setInt(5, ordHis.getHistory_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        closeDB();

        return true;
    }

}
