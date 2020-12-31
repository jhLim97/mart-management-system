package com.company.Controller;

import com.company.Model.CustomerDAO;
import com.company.Model.CustomerDTO;
import com.company.View.CustomerManageView;
import com.company.View.CustomerViewPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerController {
    CustomerDAO cdao;
    CustomerDTO customer;
    CustomerManageView cmv;
    CustomerViewPanel cvp;

    public CustomerController() {
        cvp.addSearchButtonListener(new SearchButtonListener());
    }

    public class SearchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String phoneNum = cvp.jtfPhoneNum.getText();
            cvp.jtaCustomerList.append(searchCustomer(phoneNum));
        }
    }

    public void makeCustomerManageView() {

    }

    public String searchCustomer(String phoneNum) {
        String cName;
        int cPoint;
        customer = cdao.getCustomer(phoneNum);
        if(customer != null) {
            cName = customer.getCName();
            cPoint = customer.getCPoint();
            return phoneNum + "\t\t" + cName + "\t\t" + cPoint;
        } else {
            return "등록되지 않은 회원 입니다.";
        }
    }

    public String informNewCustomer() {
        return "";
    }


}
