package com.kding.kdingcoinprepaid.bean.result;

import java.util.List;

/**
 * Created by Toast-pc on 2015/12/29.
 */
public class ResultBean {

    private int error = 0;
    private String msg = "";

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                '}';
    }
}