package com.kding.kdingcoinprepaid.p.impl;

import com.kding.kdingcoinprepaid.bean.AccountBean;
import com.kding.kdingcoinprepaid.p.IRefreshAndLoad;
import com.kding.kdingcoinprepaid.p.callback.IRefreshAndLoadMoreData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toast-pc on 2015/12/24.
 */
public abstract class RefreshAndLoadImpl<T> implements IRefreshAndLoad {

    private static final int PAGER_SIZE = 10;
    private int pagerIndex = 1;

    private final IRefreshAndLoadMoreData callback;

    public RefreshAndLoadImpl(IRefreshAndLoadMoreData callback) {
        this.callback = callback;
    }

    public void showRefreshData(List<T> accountBeans){
        pagerIndex = 1;
        callback.showRefreshData(accountBeans);
    }
    public void showLoadMore(List<T> accountBeans){
        pagerIndex++;
        callback.showLoadMoreData(accountBeans, accountBeans.size()<PAGER_SIZE);
    }
}
