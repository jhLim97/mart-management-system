package com.company.Model;

public class AccountDTO {
    private String id,password,userName;
    private boolean isSupperUser,isStaff,isLogin;

    public boolean getIsLogin() { return isLogin; }

    public void setIsLogin(boolean login) { isLogin = login; }

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

    public boolean getIsSupperUser() {
        return isSupperUser;
    }

    public void setIsSupperUser(boolean supperUser) {
        isSupperUser = supperUser;
    }

    public boolean getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(boolean staff) {
        isStaff = staff;
    }
}
