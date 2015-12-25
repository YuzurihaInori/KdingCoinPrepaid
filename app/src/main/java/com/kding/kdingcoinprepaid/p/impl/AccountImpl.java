package com.kding.kdingcoinprepaid.p.impl;

import com.kding.kdingcoinprepaid.bean.AccountBean;
import com.kding.kdingcoinprepaid.p.IRefreshAndLoad;
import com.kding.kdingcoinprepaid.p.callback.IRefreshAndLoadMoreData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toast-pc on 2015/12/25.
 */
public class AccountImpl extends RefreshAndLoadImpl<AccountBean> {


    public AccountImpl(IRefreshAndLoadMoreData callback) {
        super(callback);
    }


    @Override
    public void postRefresh(String... params) {
        List<AccountBean> accountBeans = new ArrayList<>();

        AccountBean accountBean = new AccountBean();
        accountBean.accountLeft = "Ghost";
        accountBean.accountMid = "500.00";
        accountBean.accountRight = "12-24";
        accountBean.account = "1300";

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

        List<AccountBean> accountBeans = new ArrayList<>();

        AccountBean accountBean = new AccountBean();
        accountBean.accountLeft = "Ghost";
        accountBean.accountMid = "500.00";
        accountBean.accountRight = "12-24";

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
