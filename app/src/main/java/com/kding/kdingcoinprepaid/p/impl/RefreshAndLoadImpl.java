package com.kding.kdingcoinprepaid.p.impl;

import com.kding.kdingcoinprepaid.bean.AccountBean;
import com.kding.kdingcoinprepaid.p.IRefreshAndLoad;
import com.kding.kdingcoinprepaid.p.callback.IRefreshAndLoadMoreData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toast-pc on 2015/12/24.
 */
public class RefreshAndLoadImpl implements IRefreshAndLoad {

    private static final int PAGER_SIZE = 10;
    private int pagerIndex = 1;

    private final IRefreshAndLoadMoreData callback;

    public RefreshAndLoadImpl(IRefreshAndLoadMoreData callback) {
        this.callback = callback;
    }

    @Override
    public void postRefresh(String id, String type) {

        pagerIndex = 1;

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

        callback.showRefreshData(accountBeans);
    }

    @Override
    public void postLoadMore(String id, String type) {
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

        callback.showLoadMoreData(accountBeans, accountBeans.size()<PAGER_SIZE);

        pagerIndex++;
    }
}
