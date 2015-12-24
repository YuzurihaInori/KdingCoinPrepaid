package com.kding.kdingcoinprepaid.p;

/**
 * Created by Toast-pc on 2015/12/23.
 */
public interface IRecharge {
    void post(String rmb);
    int getCoin(String rmb,String discount);
}
