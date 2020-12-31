package com.company.Model;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAO {

    Connection con;
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;
    String Driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
    String userid = "madang";
    String pwd = "madang";
    String sql;

    public OrderDAO() {

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
            if(rs!=null) rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<OrderDTO> getAll() {
        connectDB();
        sql = "select * from Orders";

        ArrayList<OrderDTO> datas = new ArrayList<OrderDTO>();

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                OrderDTO order = new OrderDTO();
                order.setOrder_code(rs.getInt("order_code"));
                order.setEntry_price(rs.getInt("entry_price"));
                order.setC_name(rs.getString("c_name"));
                order.setPhone_num(rs.getString("phone_num"));
                order.setBuy_date(rs.getString("buy_date"));
                datas.add(order);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        closeDB();

        if(datas.isEmpty()) return null;
        else return datas;

    }

    public OrderDTO getOrder(int orderCode) {
        connectDB();
        sql = "select * from Orders where order_code = ?";
        OrderDTO order = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, orderCode);
            rs = pstmt.executeQuery();
            rs.next();
            order = new OrderDTO();
            order.setOrder_code(rs.getInt("order_code"));
            order.setEntry_price(rs.getInt("entry_price"));
            order.setC_name(rs.getString("c_name"));
            order.setPhone_num(rs.getString("phone_num"));
            order.setBuy_date(rs.getString("buy_date"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDB();

        return order;
    }

    public boolean addOrder(OrderDTO order) { // 날짜는 추후 수정...
        connectDB();
        sql = "insert into Orders(entry_price, c_name, phone_num, buy_date) value(?, ?, ?, ?)";

        try {
            System.out.println(1);
            pstmt = con.prepareStatement(sql);
            //pstmt.setInt(1, product.getPrcode()); // 이거 수정하기 --> 가장 최근 숫자 + 1로
            //pstmt.setInt(1, items.size()); // 이거 맞나? --> 앞에 하나 삭제할 경우 주키 중복될 수 있음
            //int s = items.size();
            //pstmt.setInt(1, Integer.valueOf(items.get(s-1))+1); // prcode컬럼에 AUTO_INCREMENT 속성을 적용했으므로 따로 입력안해줘서 맥스값으로 계속 채워짐
            pstmt.setInt(1, order.getEntry_price());
            pstmt.setString(2, order.getC_name());
            pstmt.setString(3, order.getPhone_num());
            pstmt.setString(4, order.getBuy_date());
            System.out.println(2);
            pstmt.executeUpdate();
            System.out.println(3);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        closeDB();

        return true;
    }

    public boolean delOrder(int orderCode) {
        connectDB();
        sql = "delete from Orders where order_code = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, orderCode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        closeDB();

        return true;
    }

    // ------- 아래 함수는 사용 여부 미정 -------
    public boolean updateOrder(OrderDTO order) { // 파라미터의 객체 정보로 업데이트
        connectDB();
        sql = "update Orders set entry_price = ?, c_name = ?, phone_num = ?, buy_date = ? where order_code = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, order.getEntry_price());
            pstmt.setString(2, order.getC_name());
            pstmt.setString(3, order.getPhone_num());
            pstmt.setString(4, order.getBuy_date());
            pstmt.setInt(5, order.getOrder_code());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        closeDB();

        return true;
    }

}

