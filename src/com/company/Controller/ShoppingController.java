package com.company.Controller;

import com.company.Model.ProductDAO;
import com.company.Model.ProductDTO;
import com.company.View.ShoppingView;
import com.company.View.ViewManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShoppingController {
    public ArrayList<ProductDTO> datas ;
    public ProductDAO dao;
    public ShoppingView v;
    public ArrayList<ProductDTO> datas2 ; //마이리스트 출력 어레이리스트
    public int total=0;

    public ShoppingController(ShoppingView v) throws SQLException, ClassNotFoundException {
        this.v=v;
        dao=new ProductDAO();
        refreshData();
        v.btnEnroll.addActionListener(new EnrollBtnListener());
        v.btnPay.addActionListener(new PaymentBtnListener());
        v.btnDelete.addActionListener(new deleteBtnListener());
        datas2 = new ArrayList<ProductDTO>();
    }

    public class EnrollBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                addMyList();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
    }//상품담기버튼리스너

    public void addMyList() throws SQLException, ClassNotFoundException {
        ProductDTO p = dao.getProduct(Integer.parseInt(v.jtfSearch.getText()));

        if( p.getAmount() < Integer.parseInt(v.jtfCount.getText())){ //수량비교
            v.lblstate.setText("상태 : 재고 초과 입력");
        }
        else{
            p.setAmount(Integer.parseInt(v.jtfCount.getText()));
            datas2.add(p);
            total = p.getAmount() * p.getPrice() + total;
            refreshData2();
        }
        v.lblMsg.setText("결제 금액 : " + total +"원");
    }//상품담기

    public class PaymentBtnListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                payment();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
    }//상품결제 버튼리스너

    public void payment() throws SQLException, ClassNotFoundException {
        for(ProductDTO p : datas2){
            for(ProductDTO p2 : datas) {
                if (p.getPrCode() == p2.getPrCode()) {
                    p2.setAmount(p2.getAmount() - p.getAmount());
                    dao.updateProduct(p2);

                }
            }
        }
        refreshData();
        datas2.clear();
        refreshData2();
        total=0;
        v.lblMsg.setText("결제 금액 : 0원");
    }//상품결제

    public void refreshData() throws SQLException, ClassNotFoundException {

        datas = dao.getAll();
        Object record[] = new Object[5];
        v.tableModel.setNumRows(0); // 다시붙일때 테이블 로우 초기화

        if( datas != null){

            for(ProductDTO p : datas) {
                record[0] = p.getPrCode();
                record[1] = p.getPrName();
                record[2] = p.getPrice();
                record[3] = p.getAmount();
                record[4] = p.getState();
                v.tableModel.addRow(record);

            }
        }
    }//위에 현재재고 리프레쉬

    public void refreshData2() throws SQLException, ClassNotFoundException {

        Object record[] = new Object[5];
        v.tableModel2.setNumRows(0); // 다시붙일때 테이블 로우 초기화

        if( datas2 != null){

            for(ProductDTO p : datas2) {
                record[0] = p.getPrCode();
                record[1] = p.getPrName();
                record[2] = p.getPrice();
                record[3] = p.getAmount();
                record[4] = p.getState();
                v.tableModel2.addRow(record);

            }
        }
    }//아래 my데이터 리프레쉬

    public class deleteBtnListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                deleteMy();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
    }//상품지우기

    public void deleteMy() throws SQLException, ClassNotFoundException {
        datas2.remove(datas2.size()-1);
        refreshData2();
    }


}
