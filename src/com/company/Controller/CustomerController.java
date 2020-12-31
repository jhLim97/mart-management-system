package com.company.Controller;

import com.company.Model.CustomerDAO;
import com.company.Model.CustomerDTO;
import com.company.View.CustomerManageView;
import com.company.View.CustomerViewPanel;
import com.company.View.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CustomerController {
    public CustomerDAO cdao = new CustomerDAO();
    String bufferedString = null;
    CustomerDTO customer = null;
    CustomerManageView cmv;
    CustomerViewPanel cvp;

    public CustomerController(CustomerViewPanel cvp) {
        this.cvp = cvp;
        cvp.addSearchButtonListener(new SearchButtonListener());
        cvp.addAddButtonListener(new AddButtonListener());
        cvp.addDeleteButtonListener(new DeleteButtonListener());
        cvp.tblCustomerList.addMouseListener(new TableClickListener());
        cvp.addUpdateButtonListener(new UpdateButtonListener());
    }

    public class DeleteButtonListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            deleteCustomer();
        }
    }

    public class SearchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cvp.initDTModel();
            String phoneNum = cvp.txtPhoneNum.getText();
            if(phoneNum.equals("")) {
                searchAllCustomer();
            } else searchCustomer(phoneNum);
        }
    }

    public class RegisterButtonListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            makeCustomerManageView();
        }
    }

    public class UpdateButtonListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            updateCustomer(bufferedString);
        }
    }

    public class TableClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = cvp.tblCustomerList.getSelectedRow();
            bufferedString = (String)cvp.dtmodel.getValueAt(row, 0);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public void makeCustomerManageView() {
        cmv = new CustomerManageView();
        cmv.drawView();
    }

    public void searchAllCustomer() {
        ArrayList<CustomerDTO> datas = cdao.getAll();
        if(datas != null) {
            for(CustomerDTO c : datas) {
                String line[] = {c.getPhoneNum(), c.getCName(), String.valueOf(c.getCPoint())};
                cvp.addRowToTable(line);
            }
        }
    }

    public void searchCustomer(String phoneNum) {
        String cName, cPoint;
        customer = cdao.getCustomer(phoneNum);
        if(customer != null) {
            cName = customer.getCName();
            cPoint = String.valueOf(customer.getCPoint());
            String row[] = {phoneNum, cName, cPoint};
            cvp.addRowToTable(row);
        } else {
            JOptionPane.showMessageDialog(cvp, "등록되지 않은 회원 입니다.");
        }
    }

    public void updateCustomer(String bufferedString ) {
        int row = cvp.tblCustomerList.getSelectedRow();
        if(row == -1 || bufferedString == null) {
            JOptionPane.showMessageDialog(cvp, "수정할 정보를 선택해 주세요.");
        } else {
            String phoneNum = (String)cvp.dtmodel.getValueAt(row, 0);
            String cName = (String)cvp.dtmodel.getValueAt(row, 1);
            int cPoint = Integer.parseInt((String)cvp.dtmodel.getValueAt(row, 2));
            customer = new CustomerDTO();
            customer.setPhoneNum(phoneNum);
            customer.setCName(cName);
            customer.setCPoint(cPoint);
            cdao.updateCustomer(customer, bufferedString);
        }
    }

    public void deleteCustomer() {
        int row = cvp.tblCustomerList.getSelectedRow();
        if(row == -1) {
            JOptionPane.showMessageDialog(cvp, "삭제할 정보를 조회 후 선택해 주세요.");
        } else {
            String phoneNum = (String)cvp.dtmodel.getValueAt(row, 0);
            cdao.delCustomer(phoneNum);
            cvp.dtmodel.removeRow(row);
        }
    }

    public void registerCustomer() {

    }
    public static void main(String[] args) {
        MainView mv = new MainView();
        CustomerViewPanel cvp= new CustomerViewPanel();
        cvp.drawView();
        mv.drawView();
        mv.drawMainPanel();
        mv.add(cvp, BorderLayout.CENTER);


    }
}
