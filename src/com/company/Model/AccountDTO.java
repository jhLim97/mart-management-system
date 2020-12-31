package com.company.Model;

public class AccountDTO {
    private String id,password,userName;
    private boolean isSupperUser,isStaff;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isSupperUser() {
        return isSupperUser;
    }

    public void setSupperUser(boolean supperUser) {
        isSupperUser = supperUser;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }
}
