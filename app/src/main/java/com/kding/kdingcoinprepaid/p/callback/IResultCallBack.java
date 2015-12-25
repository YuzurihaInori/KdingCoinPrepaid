package com.kding.kdingcoinprepaid.p.callback;

import java.util.List;

/**
 * Created by Toast-pc on 2015/12/21.
 */
public interface IResultCallBack<T>{
    void resultSuc(List<T> result);
    void resultFail(String errorMsg);
}
