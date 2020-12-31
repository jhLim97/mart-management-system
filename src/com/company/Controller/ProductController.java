package com.company.Controller;

import com.company.Model.ProductDAO;
import com.company.Model.ProductDTO;
import com.company.View.MainView;
import com.company.View.ProductViewPanel;
import com.company.View.productCRUDView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController extends Thread{
    public ProductViewPanel v;
    public productCRUDView CRUDv ;
    ProductDAO dao;
    public ArrayList<ProductDTO> datas;
    public boolean editMode = false;
    MainView mainView ;

    public ProductController(ProductViewPanel v) throws SQLException, ClassNotFoundException {
        this.v=v;
        dao=new ProductDAO();
        appMain();
        refreshData();
        mainView = new MainView();
    }
    public void appMain(){
        v.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == v.addButton){
                    CRUDv = new productCRUDView();
                    CRUDv.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                             if( e.getSource() == CRUDv.completeButton){
                                ProductDTO productDTO = new ProductDTO();
                                productDTO.setPrCode(Integer.parseInt(CRUDv.codeText.getText()));
                                productDTO.setPrName(CRUDv.nameText.getText());
                                productDTO.setPrice(Integer.parseInt(CRUDv.priceText.getText()));
                                productDTO.setLocation(CRUDv.locationText.getText());
                                productDTO.setExpDate(Date.valueOf(CRUDv.expDateText.getText()));
                                productDTO.setAmount(Integer.parseInt(CRUDv.countText.getText()));
                                productDTO.setState("판매");
                                try {
                                    if(dao.newProduct(productDTO)) { //상품등록 완료
                                        System.out.println("상품등록 완료");
                                        CRUDv.codeText.setText("");
                                        CRUDv.nameText.setText("");
                                        CRUDv.priceText.setText("");
                                        CRUDv.locationText.setText("");
                                        CRUDv.expDateText.setText("");
                                        CRUDv.countText.setText("");
                                        mainView.messageLabel.setText("메시지 : 상품 등록 완료");
                                        editMode = false;
                                        refreshData();
                                    }
                                    else{
                                        System.out.println("실패");
                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                } catch (ClassNotFoundException classNotFoundException) {
                                    classNotFoundException.printStackTrace();
                                }


                                 v.SUDLab.setText("검색 정보 :                                                         EditMode : " + editMode);
                            }
                        }
                    });
                    appMain();
                    CRUDv.drawView();
                }//add
                else if(e.getSource() == v.deleteButton){
                    if(editMode){
                        try {
                            editMode = dao.delProduct(Integer.parseInt(v.txtSearch.getText()));
                            refreshData();
                            v.SUDtxt.setText("삭제되었습니다.");
                            v.txtSearch.setText("");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                        editMode = true;

                    }
                    else{
                        v.SUDtxt.setText("삭제할 수 없습니다.");
                    }

                    v.SUDLab.setText("검색 정보 :                                                         EditMode : " + editMode);
                }//delete
                else if(e.getSource() == v.searchButton){
                    try {
                        ProductDTO p = dao.getProduct(Integer.parseInt(v.txtSearch.getText()));
                        if(p.getPrCode() !=-1){
                            System.out.println(p.getPrCode());
                            v.SUDtxt.setText("");
                            v.SUDtxt.append("코드\t이름\t가격\t위치\t유통기한\t재고\t상태\n" +
                                    Integer.toString(p.getPrCode()) +"\t" + p.getPrName() +"\t"+ p.getPrice() +"\t"+ p.getLocation() +"\t"+ p.getExpDate() +"\t"+ p.getAmount() +"\t"
                                    + p.getState());;

                                    editMode = true; //찾았으면 수정,삭제가능
                        }else {

                            System.out.println(p.getPrCode());
                            v.SUDtxt.setText("검색하는 코드에 대한 정보가 없음");
                        }

                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }


                    v.SUDLab.setText("검색 정보 :                                                         EditMode : " + editMode);
                }//search
                else if (e.getSource() == v.updateButton) {

                    if(editMode){
                         CRUDv = new productCRUDView();
                         CRUDv.drawView();
                    try {
                        ProductDTO p = dao.getProduct(Integer.parseInt(v.txtSearch.getText()));

                        CRUDv.codeText.setText(Integer.toString(p.getPrCode()));
                        CRUDv.nameText.setText(p.getPrName());
                        CRUDv.priceText.setText(Integer.toString(p.getPrice()));
                        CRUDv.locationText.setText(p.getLocation());
                        CRUDv.expDateText.setText(String.valueOf(p.getExpDate()));
                        CRUDv.countText.setText(Integer.toString(p.getAmount()));

                        CRUDv.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if( e.getSource() == CRUDv.completeButton){
                                    try {
                                        v.SUDtxt.setText("");
                                        p.setPrCode(Integer.parseInt(CRUDv.codeText.getText()));
                                        p.setPrName(CRUDv.nameText.getText());
                                        p.setPrice(Integer.parseInt(CRUDv.priceText.getText()));
                                        p.setLocation(CRUDv.locationText.getText());
                                        p.setExpDate(Date.valueOf(CRUDv.expDateText.getText()));
                                        p.setAmount(Integer.parseInt(CRUDv.countText.getText()));

                                        dao.updateProduct(p);
                                        refreshData();
                                        editMode=false;

                                        v.SUDtxt.append("코드\t이름\t가격\t위치\t유통기한\t재고\t상태\n" +
                                                Integer.toString(p.getPrCode()) +"\t" + p.getPrName() +"\t"+ p.getPrice() +"\t"+ p.getLocation() +"\t"+ p.getExpDate() +"\t"+ p.getAmount() +"\t"
                                                + p.getState());;

                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    } catch (ClassNotFoundException classNotFoundException) {
                                        classNotFoundException.printStackTrace();
                                    }//update try


                                    v.SUDLab.setText("검색 정보 :                                                         EditMode : " + editMode);
                                }
                            }
                        });

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    } //try getProduct



                    }
                    else{
                        v.SUDtxt.setText("수정 할 수 없습니다.");
                    }


                    v.SUDLab.setText("검색 정보 :                                                         EditMode : " + editMode);
                }//update

            }

        });


    }
    public void refreshData() throws SQLException, ClassNotFoundException {
        datas = dao.getAll();
        Object record[] = new Object[7];
        v.tableModel.setNumRows(0); // 다시붙일때 테이블 로우 초기화

        if( datas != null){

            for(ProductDTO p : datas) {
                record[0] = p.getPrCode();
                record[1] = p.getPrName();
                record[2] = p.getPrice();
                record[3] = p.getLocation();
                record[4] = p.getExpDate();
                record[5] = p.getAmount();
                record[6] = p.getState();
                v.tableModel.addRow(record);

            }
        }
    }


    public void start(){
        while(true){

        }
    }

}
