package com.company.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class CustomerDAO {
    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://mms.crgsa3qt3jqa.ap-northeast-2.rds.amazonaws.com/mms?user=jaewon&password=wlfkf132";    Connection conn;

    PreparedStatement pstmt;
    ResultSet rs;

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
            if(rs !=null) rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CustomerDTO> getAll() {
        String sql = "select * from Customer";
        connectDB();

        ArrayList<CustomerDTO> datas = new ArrayList<CustomerDTO>();

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                CustomerDTO c = new CustomerDTO();
                c.setCName(rs.getString("c_name"));
                c.setPhoneNum(rs.getString("phone_num"));
                c.setCPoint(rs.getInt("c_point"));
                datas.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDB();
        return datas;
    }

    public CustomerDTO getCustomer(String phone_num) {
        String sql = "select * from Customer where phone_num = ?";
        CustomerDTO c = null;
        connectDB();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone_num);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                c = new CustomerDTO();
                c.setPhoneNum(rs.getString("phone_num"));
                c.setCName(rs.getString("c_name"));
                c.setCPoint(rs.getInt("c_point"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDB();
        return c;
    }

    public boolean newCustomer(CustomerDTO customer) {
        CustomerDTO c = customer;
        String sql = "insert into Customer(phone_num, c_name, c_point) values(?, ?, ?)";

        connectDB();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, c.getPhoneNum());
            pstmt.setString(2, c.getCName());
            pstmt.setInt(3, c.getCPoint());

            if(pstmt.executeUpdate() != 0) {
                closeDB();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDB();
        return false;
    }

    public boolean delCustomer(String phone_num) {
        String sql = "delete from Customer where phone_num = ?";
        connectDB();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone_num);

            if(pstmt.executeUpdate() != 0) {
                closeDB();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDB();
        return false;
    }

    public boolean updateCustomer(CustomerDTO customer, String previousPhoneNum) {
        CustomerDTO c = customer;
        String sql = "update Customer set phone_num = ?, c_name = ?, c_point = ? where phone_num = ?";
        connectDB();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, c.getPhoneNum());
            pstmt.setString(2, c.getCName());
            pstmt.setInt(3, c.getCPoint());
            pstmt.setString(4, previousPhoneNum);

            if(pstmt.executeUpdate() != 0) {
                closeDB();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDB();
        return false;
    }

    public boolean updateCustomer(CustomerDTO customer, int point) {
        CustomerDTO c= customer;
        String sql = "update Customer set c_point = ? where phone_num = ?";
        connectDB();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, c.getCPoint() + point);
            pstmt.setString(2, c.getPhoneNum());
            if(pstmt.executeUpdate() != 0) {
                closeDB();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDB();
        return false;

    }

}
