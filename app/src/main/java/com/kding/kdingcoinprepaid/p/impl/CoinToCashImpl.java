package com.kding.kdingcoinprepaid.p.impl;

import com.kding.kdingcoinprepaid.bean.CoinToCashBean;
import com.kding.kdingcoinprepaid.p.ICoinToCash;
import com.kding.kdingcoinprepaid.p.IUserInterface;
import com.kding.kdingcoinprepaid.p.callback.IResultCallBack;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toast-pc on 2015/12/25.
 */
public class CoinToCashImpl implements ICoinToCash{

    private final IResultCallBack<CoinToCashBean> iResultCallBack;
    private final IUserInterfaceCallBack callBack;

    public CoinToCashImpl(IUserInterfaceCallBack callBack,IResultCallBack<CoinToCashBean> iResultCallBack) {
        this.iResultCallBack = iResultCallBack;
        this.callBack = callBack;
    }

    @Override
    public void getDataFromServer() {

        CoinToCashBean coinToCashBean = new CoinToCashBean();
        coinToCashBean.coinToCashMax = 17777;
        coinToCashBean.coinToCashCard = "8888 8888 8888 8888";
        coinToCashBean.coinToCashMin = 100;
        coinToCashBean.coinToCashRemain = 4;

        List<CoinToCashBean> coinToCashBeans =new ArrayList<>();
        coinToCashBeans.add(coinToCashBean);

        iResultCallBack.resultSuc(coinToCashBeans);
    }

    @Override
    public void postToServer(String... params) {
        callBack.operationSuc();
    }
}
