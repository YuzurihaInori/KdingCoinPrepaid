package com.kding.kdingcoinprepaid.p;

import com.kding.kdingcoinprepaid.bean.SelectBean;

import java.util.List;

/**
 * Created by Toast-pc on 2015/12/21.
 */
public interface ISelectOrgList {

    List<String> getDataFromServer(String tag,String gameId);

    List<String> getSelectData();
    void addSelectData(SelectBean selectName);
    void postGames();
}
