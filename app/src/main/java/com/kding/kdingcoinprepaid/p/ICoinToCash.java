package com.kding.kdingcoinprepaid.p;

/**
 * Created by Toast-pc on 2015/12/25.
 */
public interface ICoinToCash {
    void getDataFromServer();

    void postToServer(String... params);
}
