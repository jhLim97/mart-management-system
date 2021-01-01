package com.company.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class ProductDAO {
    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/MMS?&serverTimezone=Asia/Seoul&useSSL=false";
    public Connection conn;

    public PreparedStatement pstmt;
    public ResultSet rs;

    public  ProductDTO product;
    public Vector<String> items = null;

    public String sql;

    //DB 연결 메서드
    public void  connectDB() throws ClassNotFoundException, SQLException {
        //1단계 : JDBC 드라이버 로드
        Class.forName(jdbcDriver);

        //2단계 : 데이터베이스 연결
        conn = DriverManager.getConnection(jdbcUrl, "root", "wlfkf132");
    }



    public void registerUser(String uname, String email) throws SQLException {
        String sql = "Insert into event values(?, ?, ?, ?, ?, ?, ?)";

        //3단계 : Statement 생성
        pstmt = conn.prepareStatement(sql);

        //4단계 : SQL 문 전송
        pstmt.executeUpdate();
    }

    public void printList() throws SQLException {
        String sql = "select * from Product";

        pstmt = conn.prepareStatement(sql);
        //5단계 : 결과받기
        rs = pstmt.executeQuery();
    }

    public ArrayList<ProductDTO> getAll() throws SQLException, ClassNotFoundException {
        connectDB();
        sql= "select * from Product";

        //전체 검색 데이터를 전달하는 ArrayList
        ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();

        //관리번호 콤보박스 데이터를 위한 벡터 초기화
        items = new Vector<String>();
        items.add("전체");

        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        while(rs.next()){
            ProductDTO p = new ProductDTO();
            p.setPrCode(rs.getInt("pr_code"));
            p.setPrName(rs.getString("pr_name"));
            p.setPrice(rs.getInt("price"));
            p.setLocation(rs.getString("location"));
            p.setExpDate(rs.getDate("exp_date"));
            p.setAmount(rs.getInt("amount"));
            p.setState(rs.getString("state"));

            datas.add(p);
            items.add(String.valueOf(rs.getInt("pr_code")));

        }
        closeDB();
        return datas;
    }

    public ProductDTO getProduct(int prcode) throws SQLException, ClassNotFoundException {
        sql = "select * from Product where pr_code = ?";

        connectDB();

        ProductDTO p = null;
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, prcode);
            rs=pstmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(rs.next()) {
            p = new ProductDTO();
            p.setPrCode(rs.getInt("pr_code"));
            p.setPrName(rs.getString("pr_name"));
            p.setPrice(rs.getInt("price"));
            p.setLocation(rs.getString("location"));
            p.setExpDate(rs.getDate("exp_date"));
            p.setAmount(rs.getInt("amount"));
            p.setState(rs.getString("state"));
        }
        else{
            p.setPrCode(-1);
            p.setPrName(rs.getString("pr_name"));
            p.setPrice(rs.getInt("price"));
            p.setLocation(rs.getString("location"));
            p.setExpDate(rs.getDate("exp_date"));
            p.setAmount(rs.getInt("amount"));
            p.setState(rs.getString("state"));

        }
        closeDB();
        return p;
    }

    public boolean delProduct(int prcode) throws SQLException, ClassNotFoundException {
        String sql = "delete from Product where pr_code = ?";
        connectDB();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, prcode);

            if(pstmt.executeUpdate() == 0) {
                closeDB();
                return false;
            } else {
                closeDB();
                return true;
            }
        } catch (SQLException e) {
            closeDB();
            e.printStackTrace();
            return false;
        }

    }

    public void updateProduct(ProductDTO product) throws SQLException, ClassNotFoundException {
        ProductDTO p = product;
        String sql = "update product set pr_name = ?, PRICE = ?, location = ?, exp_date = ?, amount = ?, state = ? where pr_code = ?";
        connectDB();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, p.getPrName());
            pstmt.setInt(2, p.getPrice());
            pstmt.setString(3, p.getLocation());
            pstmt.setDate(4, (Date) p.getExpDate());
            pstmt.setInt(5, p.getAmount());
            pstmt.setString(6, p.getState());
            pstmt.setInt(7, p.getPrCode());


            if(pstmt.executeUpdate() == 0) {
                closeDB();
            } else {
                closeDB();
            }
        } catch (SQLException e) {
            closeDB();
            e.printStackTrace();
        }
    }

    public boolean newProduct(ProductDTO product) throws SQLException, ClassNotFoundException {
        ProductDTO p = product;
        String sql = "insert into Product(pr_code, pr_name, price, location, exp_date, amount, state) values(?, ?, ?, ?, ?, ?, ?)";
        connectDB();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, p.getPrCode());
            pstmt.setString(2, p.getPrName());
            pstmt.setInt(3, p.getPrice());
            pstmt.setString(4, p.getLocation());
            pstmt.setDate(5, (Date) p.getExpDate());
            pstmt.setInt(6, p.getAmount());
            pstmt.setString(7, p.getState());

            if(pstmt.executeUpdate() == 0)  {
                closeDB();
                return false;
            } else  {
                closeDB();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            closeDB();
            return false;
        }
    }//new

    public void closeDB() throws SQLException {
        //6단계 : 연결 해제
        pstmt.close();;
        conn.close();
    }
}
