package com.kding.kdingcoinprepaid.p;

/**
 * Created by Toast-pc on 2015/12/28.
 */
public interface IBindPhone {

    void initView();
    void sendCode(int phoneNum);

    void bindPhone(String code);
}
