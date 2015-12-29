package com.kding.kdingcoinprepaid.bean.result;

import com.kding.kdingcoinprepaid.bean.UserInfoBean;

/**
 * Created by Toast-pc on 2015/12/29.
 */
public class LoginBean extends ResultBean{

    private UserInfoBean data;

    public UserInfoBean getData() {
        return data;
    }

    public void setData(UserInfoBean data) {
        this.data = data;
    }


}
