package com.company.Controller;

import com.company.Model.AccountDAO;
import com.company.Model.AccountDTO;
import com.company.View.*;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.company.Model.ProductDAO;
import com.company.Model.ProductDTO;
import com.company.View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class mmsListener {
    private static mmsListener s_Instance;
    public static mmsListener getInstance(){
        if (s_Instance == null) s_Instance = new mmsListener();
        return s_Instance;
    }

    public void loginPanelListener(LoginViewPanel panel){
        panel.loginButton.addActionListener(e -> {
            String id = panel.txtId.getText();
            String pw = panel.txtPw.getText();
            AccountDAO dao = new AccountDAO();

            if(dao.loginProgram(id,pw)){
                ProgramManager.getInstance().setMainState();
            }

            else{
                panel.txtId.setText("");
                panel.txtPw.setText("");
            }
        });
        panel.joinButton.addActionListener(e -> {
            ViewManager.getInstance().joinViewOpen();
            joinViewListener(ViewManager.getInstance().joinView);
        });
    }
    public void joinViewListener(JoinView frame){
        frame.joinButton.addActionListener(e -> {
            String id = frame.txtId.getText();
            String pw = frame.txtPw.getText();
            String name = frame.txtName.getText();
            AccountDTO account = new AccountDTO();
            AccountDAO dao = new AccountDAO();
            account.setIsStaff(true);
            account.setId(id);
            account.setPassword(pw);
            account.setIsSupperUser(false);
            account.setUserName(name);
            System.out.println(name);
            System.out.println(dao.makeAccount(account));
        });

    }
    public void mainViewPanelListener(MainView frame){ // **********

        MainView mainView = ProgramManager.getInstance().getMainView();
        frame.productButton.addActionListener(e -> {
            if(ProgramManager.getInstance().getState() instanceof OrderManageState){
                mainView.orderListViewPanel.setVisible(false);
                ProgramManager.getInstance().setMainState();
            }
            else if(ProgramManager.getInstance().getState() instanceof CustomerManageState){
                mainView.customerViewPanel.setVisible(false);
                ProgramManager.getInstance().setMainState();
            }
        });
        frame.orderListButton.addActionListener(e-> {
            if(ProgramManager.getInstance().getState() instanceof MainState) {
                mainView.productViewPanel.setVisible(false);
                ProgramManager.getInstance().setOrderManageState();
            }
            else if(ProgramManager.getInstance().getState() instanceof CustomerManageState){
                mainView.customerViewPanel.setVisible(false);
                ProgramManager.getInstance().setOrderManageState();
            }
        });
        frame.customerButton.addActionListener(e->{
            if(ProgramManager.getInstance().getState() instanceof MainState) {
                mainView.productViewPanel.setVisible(false);
                ProgramManager.getInstance().setCustomerManageState();
            }
            else if(ProgramManager.getInstance().getState() instanceof OrderManageState){
                mainView.orderListViewPanel.setVisible(false);
                ProgramManager.getInstance().setCustomerManageState();
            }
        });
        frame.shoppingButton.addActionListener(e -> {
            ViewManager.getInstance().shoppingViewOpen();
            ShoppingView shoppingView = ViewManager.getInstance().shoppingView;

            shoppingViewListener(shoppingView);
        });
    }
    public void productViewPanelListener(ProductViewPanel panel){
        ProductDAO dao = new ProductDAO();
        ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();

        panel.addButton.addActionListener(e -> {
            addProduct();
        });
        panel.searchButton.addActionListener(e -> {
            searchProduct(dao, panel.editMode, panel);
            panel.editMode = true;
        });
        panel.deleteButton.addActionListener(e -> {
            deleteProduct(dao,panel.editMode,panel, datas);
        });
        panel.updateButton.addActionListener(e -> {
            try {
                updateProduct(dao, panel, panel.editMode);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
    } //ProductViewPaneListener


    //ProductViewPanelListener Method //메소드/////////////////
    public void deleteProduct(ProductDAO dao, boolean editMode, ProductViewPanel panel, ArrayList<ProductDTO> datas){
        if (editMode) {
            try {
                editMode = dao.delProduct(Integer.parseInt(panel.txtSearch.getText()));
                refreshData(datas,dao,panel);
                panel.SUDtxt.setText("삭제되었습니다.");
                panel.txtSearch.setText("");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            editMode = true;

        } else {
            panel.SUDtxt.setText("삭제할 수 없습니다.");
        }

        panel.SUDLab.setText("검색 정보 :                                                         EditMode : " + editMode);
    }//정보 검색후 삭제하면 삭제
    public void searchProduct(ProductDAO dao, boolean editMode,ProductViewPanel panel){
        try {

            ProductDTO p = dao.getProduct(Integer.parseInt(panel.txtSearch.getText()));
            if (p.getPrCode() != -1) {
                System.out.println(p.getPrCode());
                panel.SUDtxt.setText("");
                panel.SUDtxt.append("코드\t이름\t가격\t위치\t유통기한\t재고\t상태\n" +
                        Integer.toString(p.getPrCode()) + "\t" + p.getPrName() + "\t" + p.getPrice() + "\t" + p.getLocation() + "\t" + p.getExpDate() + "\t" + p.getAmount() + "\t"
                        + p.getState());
                ;

                editMode = true; //찾았으면 수정,삭제가능
            } else {

                System.out.println(p.getPrCode());
                panel.SUDtxt.setText("검색하는 코드에 대한 정보가 없음");
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


        panel.SUDLab.setText("검색 정보 :                                                         EditMode : " + editMode);
    }//search한후 텍스트area에 정보띄우기
    public void addProduct(){
        ProgramManager.getInstance().getProductCRUDView().drawView();
        ProgramManager.getInstance().getProductCRUDView().chk = 1;
    } //CRUD창 추가후 버튼리스너 추가
    public void refreshData (ArrayList<ProductDTO> datas, ProductDAO dao, ProductViewPanel panel)throws SQLException, ClassNotFoundException {
        datas = dao.getAll();
        Object record[] = new Object[7];
        panel.tableModel.setNumRows(0); // 다시붙일때 테이블 로우 초기화

        if( datas != null){

            for(ProductDTO p : datas) {
                record[0] = p.getPrCode();
                record[1] = p.getPrName();
                record[2] = p.getPrice();
                record[3] = p.getLocation();
                record[4] = p.getExpDate();
                record[5] = p.getAmount();
                record[6] = p.getState();
                panel.tableModel.addRow(record);

            }
        }
    }
    public void updateProduct(ProductDAO dao, ProductViewPanel panel, boolean editMode) throws SQLException, ClassNotFoundException {
        System.out.println("마마마");
        if (editMode) {
            ProgramManager.getInstance().getProductCRUDView().drawView();
            ProgramManager.getInstance().getProductCRUDView().chk = 2;
            update_setText(dao,panel,ProgramManager.getInstance().getProductCRUDView());
        }
    }
    public void update_setText(ProductDAO dao, ProductViewPanel v, ProductCRUDView CRUDv) throws SQLException, ClassNotFoundException {

        ProductDTO p = dao.getProduct(Integer.parseInt(v.txtSearch.getText()));
        CRUDv.codeText.setText(Integer.toString(p.getPrCode()));
        CRUDv.nameText.setText(p.getPrName());
        CRUDv.priceText.setText(Integer.toString(p.getPrice()));
        CRUDv.locationText.setText(p.getLocation());
        CRUDv.expDateText.setText(String.valueOf(p.getExpDate()));
        CRUDv.countText.setText(Integer.toString(p.getAmount()));

    }
    ////////////메소드///////////////


    public void orderListViewPanelListener(OrderListViewPanel panel){

        panel.btnSerach.addActionListener(e -> {
            ProgramManager.getInstance().getOrderController().searchOrder(panel); // 주문 목록 조회
        });

    }
    public void customerViewPanelListener(CustomerViewPanel panel){

        panel.addButton.addActionListener(e -> {
            CustomerManageView cmv = ProgramManager.getInstance().getCC().makeCustomerManageView();
            customerManageViewListener(cmv);
            System.out.println("register");
        });
        panel.searchButton.addActionListener(e -> {
            ProgramManager.getInstance().getCC().search = true;
            ProgramManager.getInstance().getCC().appMain();
            System.out.println("search");
        });
        panel.updateButton.addActionListener(e -> {
            ProgramManager.getInstance().getCC().update = true;
            ProgramManager.getInstance().getCC().appMain();
            System.out.println("update");
        });
        panel.deleteButton.addActionListener(e -> {
            ProgramManager.getInstance().getCC().delete =true;
            ProgramManager.getInstance().getCC().appMain();
            System.out.println("delete");
        });

        panel.tblCustomerList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ProgramManager.getInstance().getCC().isClick =true;
                ProgramManager.getInstance().getCC().appMain();
            }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
        });
    }

    public void productCRUDViewListener(ProductCRUDView frame){
        ProductDAO dao=new ProductDAO();
        ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();

        frame.completeButton.addActionListener(e -> {
            if(frame.chk==1)
                addProduct_inCRUD(frame, dao, ProgramManager.getInstance().getMainView().productViewPanel.editMode, datas);
            else
                update_inCRUD(dao, frame,datas);
        });
    }//productCRUDViewListener

    //productCRUDViewListener Method CRUD패널 메소드 ////////////////////
    public void addProduct_inCRUD(ProductCRUDView CRUDv, ProductDAO dao, boolean editMode, ArrayList<ProductDTO> datas){
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
                editMode = false;
                refreshData(datas,dao,ProgramManager.getInstance().getMainView().productViewPanel);
            }
            else{
                System.out.println("실패");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }


        ProgramManager.getInstance().getMainView().productViewPanel.SUDLab.setText("검색 정보 :                                                         EditMode : " + editMode);

    } //addProduct_inCRUD complete누르면 정보 추가
    public void update_inCRUD(ProductDAO dao, ProductCRUDView CRUDv, ArrayList<ProductDTO> datas){
        if(ProgramManager.getInstance().getMainView().productViewPanel.editMode){
            try {
                ProductDTO p = dao.getProduct(Integer.parseInt(ProgramManager.getInstance().getMainView().productViewPanel.txtSearch.getText()));


                try {
                    ProgramManager.getInstance().getMainView().productViewPanel.SUDtxt.setText("");
                    p.setPrCode(Integer.parseInt(CRUDv.codeText.getText()));
                    p.setPrName(CRUDv.nameText.getText());
                    p.setPrice(Integer.parseInt(CRUDv.priceText.getText()));
                    p.setLocation(CRUDv.locationText.getText());
                    p.setExpDate(Date.valueOf(CRUDv.expDateText.getText()));
                    p.setAmount(Integer.parseInt(CRUDv.countText.getText()));
                    dao.updateProduct(p);
                    refreshData(datas, dao, ProgramManager.getInstance().getMainView().productViewPanel);
                    ProgramManager.getInstance().getMainView().productViewPanel.editMode = false;

                    ProgramManager.getInstance().getMainView().productViewPanel.SUDtxt.append("코드\t이름\t가격\t위치\t유통기한\t재고\t상태\n" +
                            Integer.toString(p.getPrCode()) + "\t" + p.getPrName() + "\t" + p.getPrice() + "\t" + p.getLocation() + "\t" + p.getExpDate() + "\t" + p.getAmount() + "\t"
                            + p.getState());
                    ;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }//update try

                ProgramManager.getInstance().getMainView().productViewPanel.SUDLab.setText("검색 정보 :                                                         EditMode : " + ProgramManager.getInstance().getMainView().productViewPanel.editMode);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } //try getProduct


        } else {
            ProgramManager.getInstance().getMainView().productViewPanel.SUDtxt.setText("수정 할 수 없습니다.");
        }

        ProgramManager.getInstance().getMainView().productViewPanel.SUDLab.setText("검색 정보 :                                                         EditMode : " + ProgramManager.getInstance().getMainView().productViewPanel.editMode);
    } //
    //메소드///////////////////////////////


    public void shoppingViewListener(ShoppingView frame){


        frame.btnEnter.addActionListener(e -> {
            String name = frame.txtName.getText();
            String phone = frame.txtPhone.getText();

            if(name != null && phone != null) {
                try {
                    ProgramManager.getInstance().getShoppingController().refreshData(frame);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }

                frame.lblCname.setText("고객이름 : " + name);
                frame.lblCphoneNum.setText("고객 번호 : " + phone);

                frame.pn1.setVisible(false);
                frame.pn2.setVisible(true);
            }
            else System.out.println("이름과 번호를 모두 입력하세요!");

        });

        frame.btnEnroll.addActionListener(e -> {
            try {
                ProgramManager.getInstance().getShoppingController().addMyList(frame);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }

        });

        frame.btnPay.addActionListener(e -> {
            ProgramManager.getInstance().getOrderController().OrderItems(frame);
            try {
                ProgramManager.getInstance().getShoppingController().payment(frame);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        });

        frame.btnDelete.addActionListener(e -> {
            try {
                ProgramManager.getInstance().getShoppingController().deleteMy(frame);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }

        });

    }
    public void customerManageViewListener(CustomerManageView frame){

        frame.btnRegister.addActionListener(e -> {
            ProgramManager.getInstance().setCustomerManageView(frame);
            ProgramManager.getInstance().getCC().register =true;
            ProgramManager.getInstance().getCC().appMain();
        });
        frame.btnExit.addActionListener(e -> {
            frame.dispose();
        });
    }
}
