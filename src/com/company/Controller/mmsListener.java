package com.company.Controller;

import com.company.Model.*;
import com.company.View.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.company.View.*;
import com.google.gson.Gson;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class mmsListener {
    private Gson gson = new Gson();
    private Message msg;
    private Socket socket;
    private BufferedReader inMsg = null;
    private PrintWriter outMsg = null;
    static final int INSERT_ACCOUNT = 1, LOGIN = 2, LOGOUT = 3, CHAT_MESSAGE = 4;

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
                ProgramManager.getInstance().id = id;
                ProgramManager.getInstance().pw = pw;
                ProgramManager.getInstance().setMainState();
                msg = new Message(id,pw,"로그인",LOGIN);
                ProgramManager.getInstance().getMainController().msgSend(msg);

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
            if(id != null && pw != null && name != null) {
                msg = new Message(id, pw, name, INSERT_ACCOUNT);
                ProgramManager.getInstance().getMainController().msgSend(msg);
                frame.dispose();

            }
            else {

                JOptionPane.showMessageDialog(frame, "잘못된 양식입니다.");

            }

        });

    }
    public void mainViewPanelListener(MainViewPanel panel){ // **********

        MainView mainView = ProgramManager.getInstance().getMainView();
        panel.productButton.addActionListener(e -> {
            if(ProgramManager.getInstance().getState() instanceof OrderManageState){
                mainView.orderListViewPanel.setVisible(false);
                ProgramManager.getInstance().setMainState();
            }
            else if(ProgramManager.getInstance().getState() instanceof CustomerManageState){
                mainView.customerViewPanel.setVisible(false);
                ProgramManager.getInstance().setMainState();
            }
        });
        panel.orderListButton.addActionListener(e-> {
            if(ProgramManager.getInstance().getState() instanceof MainState) {
                mainView.productViewPanel.setVisible(false);
                ProgramManager.getInstance().setOrderManageState();
            }
            else if(ProgramManager.getInstance().getState() instanceof CustomerManageState){
                mainView.customerViewPanel.setVisible(false);
                ProgramManager.getInstance().setOrderManageState();
            }
        });
        panel.customerButton.addActionListener(e->{
            if(ProgramManager.getInstance().getState() instanceof MainState) {
                mainView.productViewPanel.setVisible(false);
                ProgramManager.getInstance().setCustomerManageState();
            }
            else if(ProgramManager.getInstance().getState() instanceof OrderManageState){
                mainView.orderListViewPanel.setVisible(false);
                ProgramManager.getInstance().setCustomerManageState();
            }
        });
        panel.shoppingButton.addActionListener(e -> {
            ViewManager.getInstance().shoppingViewOpen();
            ShoppingView shoppingView = ViewManager.getInstance().shoppingView;

            shoppingViewListener(shoppingView);
        });
        panel.logoutButton.addActionListener(e -> {
            msg = new Message(ProgramManager.getInstance().id, ProgramManager.getInstance().pw, "로그아웃",LOGOUT);
            ProgramManager.getInstance().getMainController().msgSend(msg);
            if(ProgramManager.getInstance().getState() instanceof MainState) {
                mainView.productViewPanel.setVisible(false);
            } else if(ProgramManager.getInstance().getState() instanceof CustomerManageState) {
                mainView.customerViewPanel.setVisible(false);
            }else if(ProgramManager.getInstance().getState() instanceof OrderManageState){
                mainView.orderListViewPanel.setVisible(false);
            }
            panel.setVisible(false);
            ProgramManager.getInstance().setLoginState();
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
                updateProduct(datas, dao, ProgramManager.getInstance().getPC().bufferedString);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });

        panel.productTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    ProgramManager.getInstance().getPC().isClick =true;
                    ProgramManager.getInstance().getPC().appMain();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
        });
    } //ProductViewPaneListener
    //ProductViewPanelListener Method //메소드/////////////////
    public void deleteProduct(ProductDAO dao, boolean editMode, ProductViewPanel panel, ArrayList<ProductDTO> datas){
        int row = ProgramManager.getInstance().getMainView().productViewPanel.productTable.getSelectedRow();
        if(row == -1 ){
            JOptionPane.showMessageDialog(ProgramManager.getInstance().getMainView().productViewPanel, " 삭제할 정보를 조회 후 선택해 주세요.");

        }else {
            int prCode = (int) (ProgramManager.getInstance().getMainView().productViewPanel.tableModel.getValueAt(row, 0));
            try {
                 dao.delProduct(prCode);
            } catch (Exception e) {
            }
            try {
                refreshData(datas, dao, ProgramManager.getInstance().getMainView().productViewPanel);
            }catch (Exception e) {

            }
        }

        panel.SUDLab.setText("검색 정보 :                                                         EditMode : " + editMode);
    }//테이블 누르고 삭제
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
    public void updateProduct(ArrayList<ProductDTO> datas, ProductDAO dao, int bufferedString ) {

        int row = ProgramManager.getInstance().getMainView().productViewPanel.productTable.getSelectedRow();
        if(row == -1 ) {
            JOptionPane.showMessageDialog(ProgramManager.getInstance().getMainView().productViewPanel, "수정할 정보를 선택해 주세요.");
        } else {
            int prcode = Integer.parseInt((ProgramManager.getInstance().getMainView().productViewPanel.tableModel.getValueAt(row, 0).toString()));
            String prName = (String)ProgramManager.getInstance().getMainView().productViewPanel.tableModel.getValueAt(row, 1);
            int price = Integer.parseInt(ProgramManager.getInstance().getMainView().productViewPanel.tableModel.getValueAt(row, 2).toString());
            String location = (String)ProgramManager.getInstance().getMainView().productViewPanel.tableModel.getValueAt(row, 3);
            Date date = Date.valueOf(ProgramManager.getInstance().getMainView().productViewPanel.tableModel.getValueAt(row, 4).toString());
            int amount = Integer.parseInt(ProgramManager.getInstance().getMainView().productViewPanel.tableModel.getValueAt(row, 5).toString());
            String state = (String)ProgramManager.getInstance().getMainView().productViewPanel.tableModel.getValueAt(row, 6);


            ProductDTO p = new ProductDTO();
            p.setPrCode(prcode);
            p.setPrName(prName);
            p.setPrice(price);
            p.setLocation(location);
            p.setExpDate(date);
            p.setAmount(amount);
            p.setState(state);

            try {

                dao.updateProduct2(p, bufferedString);
                System.out.println("야호");

            }catch (Exception e){}

            try {
                refreshData(datas, dao, ProgramManager.getInstance().getMainView().productViewPanel);
            }catch(Exception e){}

            ProgramManager.getInstance().getMainView().productViewPanel.SUDtxt.setText("수정이 완료되었습니다.");
        }
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

            try {
                int total = ProgramManager.getInstance().getShoppingController().getTotal();
                ProgramManager.getInstance().getShoppingController().payment(frame); // 여기를 통과해야 되게끔..
                ProgramManager.getInstance().getOrderController().OrderItems(frame, total); // 주문, 주문 내역 쿼리 실행
                ProgramManager.getInstance().getCC().savePoint(frame.txtPhone.getText(), (int)(total*0.01));
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
    public void chattingViewListener(ChattingView frame) {
        frame.exitButton.addActionListener(e-> {
            ProgramManager.getInstance().getChattingController().exitChatting();
        });
        frame.msgInput.addActionListener((e -> {
            ProgramManager.getInstance().getChattingController().sendTextMessage("아무개" + "/" + frame.msgInput.getText());
        }));
    }
}
