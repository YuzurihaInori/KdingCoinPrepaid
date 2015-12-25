package com.kding.kdingcoinprepaid.p.impl;

import com.kding.kdingcoinprepaid.bean.AccountBean;
import com.kding.kdingcoinprepaid.bean.FragmentBean;
import com.kding.kdingcoinprepaid.p.ICashManger;
import com.kding.kdingcoinprepaid.p.callback.ICashManegerCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toast-pc on 2015/12/24.
 */
public class CashManagerImpl implements ICashManger{

    private final ICashManegerCallBack callback;

    public CashManagerImpl(ICashManegerCallBack callBack) {
        this.callback = callBack;
    }

    @Override
    public void getDataFromServer() {


        FragmentBean fragmentBean = new FragmentBean();
        fragmentBean.name = "充值流水";
        fragmentBean.localType = FragmentBean.TYPE_NORMAL;

        FragmentBean fragmentBean2 = new FragmentBean();
        fragmentBean2.name = "进货流水";
        fragmentBean2.localType = FragmentBean.TYPE_NORMAL;

        FragmentBean fragmentBean3 = new FragmentBean();
        fragmentBean3.name = "返点流水";
        fragmentBean3.localType = FragmentBean.TYPE_NORMAL;

        FragmentBean fragmentBean4 = new FragmentBean();
        fragmentBean4.name = "收入统计";
        fragmentBean4.localType = FragmentBean.TYPE_SPECIAL;

        List<FragmentBean> fragmentBeans = new ArrayList<>();
        fragmentBeans.add(fragmentBean);
        fragmentBeans.add(fragmentBean2);
        fragmentBeans.add(fragmentBean3);
        fragmentBeans.add(fragmentBean4);


        for (FragmentBean bean :fragmentBeans){
            callback.initFragments(bean);
        }

        callback.bindData();

    }
}
