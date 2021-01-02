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

    public ArrayList<ProductDTO> datas2 ; //마이리스트 출력 어레이리스트
    public int total=0;

    public ShoppingController() throws SQLException, ClassNotFoundException {
        dao=new ProductDAO();
        datas2 = new ArrayList<ProductDTO>();
    }

    public void addMyList(ShoppingView v) throws SQLException, ClassNotFoundException {

        ProductDTO p = dao.getProduct(Integer.parseInt(v.jtfSearch.getText()));

        if( p.getAmount() < Integer.parseInt(v.jtfCount.getText())){ //수량비교
            v.lblstate.setText("상태 : 재고 초과 입력");
        }
        else{
            p.setAmount(Integer.parseInt(v.jtfCount.getText()));
            datas2.add(p);
            total = p.getAmount() * p.getPrice() + total;

            refreshData2(v);

        }
        v.lblMsg.setText("결제 금액 : " + total +"원");
    }//상품담기


    public void payment(ShoppingView v) throws SQLException, ClassNotFoundException {
        for(ProductDTO p : datas2){ // 담은 리스트
            for(ProductDTO p2 : datas) { // 재고 리스트

                if (p.getPrCode() == p2.getPrCode()) {
                    p2.setAmount(p2.getAmount() - p.getAmount());
                    dao.updateProduct(p2);

                }
            }
        }

        refreshData(v); // itemList JTable 리프레쉬
        datas2.clear();
        refreshData2(v);

        total=0;
        v.lblMsg.setText("결제 금액 : 0원");
    }//상품결제


    public void refreshData(ShoppingView v) throws SQLException, ClassNotFoundException {


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


    public void refreshData2(ShoppingView v) throws SQLException, ClassNotFoundException {

        Object record[] = new Object[4];

        v.tableModel2.setNumRows(0); // 다시붙일때 테이블 로우 초기화

        if( datas2 != null){

            for(ProductDTO p : datas2) {
                record[0] = p.getPrCode();
                record[1] = p.getPrName();
                record[2] = p.getPrice();
                record[3] = p.getAmount();

                v.tableModel2.addRow(record);

            }
        }
    }//아래 my데이터 리프레쉬


    public void deleteMy(ShoppingView v) throws SQLException, ClassNotFoundException {
        ProductDTO p = datas2.get(datas2.size()-1);
        total -= (p.getPrice()*p.getAmount());
        v.lblMsg.setText("결제 금액 : " + total +"원");
        datas2.remove(datas2.size()-1);
        refreshData2(v);

    }


}