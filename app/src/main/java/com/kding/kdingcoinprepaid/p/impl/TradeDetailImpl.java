package com.kding.kdingcoinprepaid.p.impl;

import com.kding.kdingcoinprepaid.bean.TradeDetailBean;
import com.kding.kdingcoinprepaid.p.ITradeDetail;
import com.kding.kdingcoinprepaid.p.callback.IResultCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toast-pc on 2015/12/25.
 */
public class TradeDetailImpl implements ITradeDetail{


    private final IResultCallBack<TradeDetailBean> iResultCallBack;

    public TradeDetailImpl(IResultCallBack<TradeDetailBean> iResultCallBack) {
        this.iResultCallBack = iResultCallBack;
    }

    @Override
    public void getDataFromServer(String... params) {

        TradeDetailBean bean1 = new TradeDetailBean();
        bean1.tradeItem = "交易时间:2015-11-17 16:29:12";
        TradeDetailBean bean2 = new TradeDetailBean();
        bean2.tradeItem = "所属游戏:穿越火线";
        TradeDetailBean bean3 = new TradeDetailBean();
        bean3.tradeItem = "充值方式:玩家充值";
        TradeDetailBean bean4 = new TradeDetailBean();
        bean4.tradeItem = "玩家账号:Satan666";
        TradeDetailBean bean5 = new TradeDetailBean();
        bean5.tradeItem = "充值金额:10000匣币";
        TradeDetailBean bean6 = new TradeDetailBean();
        bean6.tradeItem = "付款金额:7000.00";


        List<TradeDetailBean> tradeDetailBeans = new ArrayList<>();
        tradeDetailBeans.add(bean1);
        tradeDetailBeans.add(bean2);
        tradeDetailBeans.add(bean3);
        tradeDetailBeans.add(bean4);
        tradeDetailBeans.add(bean5);
        tradeDetailBeans.add(bean6);


        iResultCallBack.resultSuc(tradeDetailBeans);
    }
}
