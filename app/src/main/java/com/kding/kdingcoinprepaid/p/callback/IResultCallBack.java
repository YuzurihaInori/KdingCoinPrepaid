package com.kding.kdingcoinprepaid.p.callback;

/**
 * Created by Toast-pc on 2015/12/21.
 */
public interface IResultCallBack<T>{
    void resultSuc(T result);
    void resultFail(String errorMsg);
}
