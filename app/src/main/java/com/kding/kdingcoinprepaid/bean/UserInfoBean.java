package com.kding.kdingcoinprepaid.bean;

/**
 * Created by Toast-pc on 2015/12/18.
 */
public class UserInfoBean {

    public UserInfoBean() {
    }

    private int usertype =-1;
    private String usertypew;
    private int uid ;
    private String username;
    private int cellphone = 0;
    private int coin = 0;
    private boolean iscellphone = false;

    //是否提交入会申请
    private boolean isjoindata = false;

    public boolean isjoindata() {
        return isjoindata;
    }

    public void setIsjoindata(boolean isjoindata) {
        this.isjoindata = isjoindata;
    }

    public int getCellphone() {
        return cellphone;
    }

    public void setCellphone(int cellphone) {
        this.cellphone = cellphone;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public boolean iscellphone() {
        return iscellphone;
    }

    public void setIscellphone(boolean iscellphone) {
        this.iscellphone = iscellphone;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public String getUsertypew() {
        return usertypew;
    }

    public void setUsertypew(String usertypew) {
        this.usertypew = usertypew;
    }


    @Override
    public String toString() {
        return "UserInfoBean{" +
                "cellphone=" + cellphone +
                ", usertype=" + usertype +
                ", usertypew='" + usertypew + '\'' +
                ", uid=" + uid +
                ", username='" + username + '\'' +
                ", coin=" + coin +
                ", iscellphone=" + iscellphone +
                ", isjoindata=" + isjoindata +
                '}';
    }
}
