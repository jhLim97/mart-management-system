package com.company.Controller;

import com.company.Model.CustomerDAO;
import com.company.Model.CustomerDTO;
import com.company.View.CustomerManageView;
import com.company.View.CustomerViewPanel;

public class CustomerController {
    CustomerDAO cdao;
    CustomerDTO customer;
    CustomerManageView cmv;
    CustomerViewPanel cvp;

    public void makeCustomerManageView() {

    }

    public void searchCustomer(String phoneNum) {
        String cInfo;
        customer = cdao.getCustomer(phoneNum);

    }

    public String informNewCustomer() {
        return "";
    }
}
