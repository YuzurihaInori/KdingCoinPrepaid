package com.kding.kdingcoinprepaid.bean.result;

import com.kding.kdingcoinprepaid.bean.SelectBean;

import java.util.List;

/**
 * Created by Toast-pc on 2015/12/29.
 */
public class SelectListBean extends ResultBean{

    private List<SelectBean> data;

    public List<SelectBean> getData() {
        return data;
    }

    public void setData(List<SelectBean> data) {
        this.data = data;
    }
}
