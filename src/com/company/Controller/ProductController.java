
package com.company.Controller;

import com.company.Model.ProductDAO;
import com.company.Model.ProductDTO;
import com.company.View.MainView;
import com.company.View.ProductViewPanel;
import com.company.View.ProductCRUDView;
import com.company.View.ViewManager;

import javax.naming.ServiceUnavailableException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class ProductController extends Thread{
    public ProductViewPanel v;
    public ProductCRUDView CRUDv ;
    ProductDAO dao;
    public ArrayList<ProductDTO> datas;
    public boolean editMode = false;
    MainView mainView ;
    Date date ;
    public boolean isClick = false;
    public int bufferedString =-1;
    SimpleDateFormat format = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분");


    public ProductController(ProductViewPanel v) throws SQLException, ClassNotFoundException {
        this.v=v;
        dao=new ProductDAO();
        refreshData();
        mainView = new MainView();
        start();
        v.smallAMountArea.setText("코드\t이름\t가격\t위치\t유통기한\t재고\t상태\n");
        v.almostExpiredArea.setText("코드\t이름\t가격\t위치\t유통기한\t재고\t상태\n");
    }

    public void appMain(){

        if(isClick){
            int row = v.productTable.getSelectedRow();
            bufferedString = Integer.parseInt(v.tableModel.getValueAt(row, 0).toString());
            isClick = false;
        }
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


    public void run(){

        String txt="코드\t이름\t가격\t위치\t유통기한\t재고\t상태\n";
        String txt2="코드\t이름\t가격\t위치\t유통기한\t재고\t상태\n";

        ArrayList<ProductDTO> data = null;

        while(true){//while
            java.util.Date now = new java.util.Date();

            Calendar time = Calendar.getInstance();
            String format_time = format.format(time.getTime());
            ViewManager.getInstance().getMainView().mainViewPanel.timeLabel.setText(format_time);

            try {
                data=dao.getAll();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } //daoGeoAll try

            if(data!=null) {
                for (ProductDTO p : data) {
                    boolean chk1=false,chk2=false;

                    long diff = p.getExpDate().getTime() - now.getTime();
                    long sec = diff / 1000;

                    if ( sec < 604800) {//시간측정 일주일
                        p.setState("유통기한임박");
                        try {
                            dao.updateProduct(p);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        txt += p.getPrCode() + "\t" + p.getPrName() + "\t" + p.getPrice() + "\t" + p.getLocation() +
                                "\t" + p.getExpDate() + "\t" + p.getAmount() +"\t" + p.getState() + "\n";
                        chk1 =true;
                    }//if 시간측정

                    if( p.getAmount() < 10){//재고측정
                        p.setState("재고부족임박");
                        try {
                            dao.updateProduct(p);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                        txt2 += p.getPrCode() + "\t" + p.getPrName() + "\t" + p.getPrice() + "\t" + p.getLocation() +
                                "\t" + p.getExpDate() + "\t" + p.getAmount() +"\t" + p.getState() + "\n";
                        chk2 = true;
                    }//재고측정

                    if(chk1){
                        if(chk2){
                            p.setState("재고유통기한임박");
                            try {
                                dao.updateProduct(p);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }//상태변환 재고 유통기한 둘다 측정할떄 변화
                    chk1 =false; chk2 =false;
                }

            }try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            v.smallAMountArea.setText(txt);
            v.almostExpiredArea.setText(txt2);
            txt="코드\t이름\t가격\t위치\t유통기한\t재고\t상태\n";
            txt2="코드\t이름\t가격\t위치\t유통기한\t재고\t상태\n";


        }
    }

}