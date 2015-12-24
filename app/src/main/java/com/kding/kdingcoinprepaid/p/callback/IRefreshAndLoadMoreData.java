package com.kding.kdingcoinprepaid.p.callback;

import java.util.List;

/**
 * Created by Toast-pc on 2015/12/24.
 */
public interface IRefreshAndLoadMoreData<T> {
    void showRefreshData(List<T> data);
    void showLoadMoreData(List<T> data,boolean isEnd);
}
