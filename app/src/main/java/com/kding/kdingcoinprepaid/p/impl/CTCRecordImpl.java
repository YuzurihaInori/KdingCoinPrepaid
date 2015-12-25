package com.kding.kdingcoinprepaid.p.impl;

import com.kding.kdingcoinprepaid.bean.AccountBean;
import com.kding.kdingcoinprepaid.bean.CTCRecordBean;
import com.kding.kdingcoinprepaid.p.callback.IRefreshAndLoadMoreData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toast-pc on 2015/12/25.
 */
public class CTCRecordImpl extends RefreshAndLoadImpl<CTCRecordBean> {


    public CTCRecordImpl(IRefreshAndLoadMoreData callback) {
        super(callback);
    }


    @Override
    public void postRefresh(String... params) {
        List<CTCRecordBean> accountBeans = new ArrayList<>();

        CTCRecordBean accountBean = new CTCRecordBean();
        accountBean.cTCRecordLeft = "5000";
        accountBean.cTCRecordMid = "12-24";
        accountBean.cTCRecordRight = "审核中";
        accountBean.status = CTCRecordBean.STATUS_EXAMINE;

        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);

        showRefreshData(accountBeans);
    }

    @Override
    public void postLoadMore(String... params) {

        List<CTCRecordBean> accountBeans = new ArrayList<>();

        CTCRecordBean accountBean = new CTCRecordBean();
        accountBean.cTCRecordLeft = "5000";
        accountBean.cTCRecordMid = "12-24";
        accountBean.cTCRecordRight = "审核中";
        accountBean.status = CTCRecordBean.STATUS_EXAMINE;

        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);
        accountBeans.add(accountBean);

        showLoadMore(accountBeans);
    }
}
