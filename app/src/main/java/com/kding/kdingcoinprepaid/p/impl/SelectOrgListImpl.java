package com.kding.kdingcoinprepaid.p.impl;

import android.app.Activity;
import android.content.Intent;

import com.kding.kdingcoinprepaid.bean.SelectBean;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.p.ISelectOrgList;
import com.kding.kdingcoinprepaid.p.callback.IResultCallBack;
import com.kding.kdingcoinprepaid.p.callback.SelectListChangeObsever;
import com.socks.library.KLog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toast-pc on 2015/12/21.
 */
public class SelectOrgListImpl implements ISelectOrgList{


    private final Activity activity;
    private final IResultCallBack resultCallBack;
    private List<SelectBean> selectList = new ArrayList<>();
    private final SelectListChangeObsever obsever;

    public SelectOrgListImpl(Activity activity,SelectListChangeObsever obsever ,IResultCallBack resultCallBack) {
        this.activity = activity;
        this.obsever = obsever;
        this.resultCallBack = resultCallBack;
    }

    @Override
    public List<String> getDataFromServer(String tag) {

        switch (tag){
            case ConstantTag.GAME_LIST_KEY:
                List<SelectBean> selectBeans = new ArrayList<>();
                SelectBean selectBean1 = new SelectBean();
                selectBean1.selectName = "烈王的分钟";
                selectBeans.add(selectBean1);
                SelectBean selectBean2 = new SelectBean();
                selectBean2.selectName = "船业火线";
                selectBeans.add(selectBean2);
                SelectBean selectBean3 = new SelectBean();
                selectBean3.selectName = " 大话西游";
                selectBeans.add(selectBean3);
                SelectBean selectBean4 = new SelectBean();
                selectBean4.selectName = "斗罗大陆";
                selectBeans.add(selectBean4);
                SelectBean selectBean5 = new SelectBean();
                selectBean5.selectName = "到他传奇";
                selectBeans.add(selectBean5);
                SelectBean selectBean6 = new SelectBean();
                selectBean6.selectName = "风云";
                selectBeans.add(selectBean6);
                SelectBean selectBean7 = new SelectBean();
                selectBean7.selectName = "风暴幻想";
                selectBeans.add(selectBean7);
                SelectBean selectBean8 = new SelectBean();
                selectBean8.selectName = "功夫就";
                selectBeans.add(selectBean8);
                SelectBean selectBean9 = new SelectBean();
                selectBean9.selectName = "关门放卢布";
                selectBeans.add(selectBean9);

                resultCallBack.resultSuc(selectBeans);
                // TODO: 2015/12/21  
                
                break;
            case ConstantTag.UNION_LIST_KEY:

                // TODO: 2015/12/21  
                break;
            default:
                break;
        }


        return new ArrayList<>();
    }

    @Override
    public List<String> getSelectData() {
        return null;
    }

    @Override
    public void addSelectData(SelectBean selectBean) {

        if (!selectBean.isSelected()){
            if (!contains(selectBean.selectName)){
                selectBean.setSelected(true);
                selectList.add(selectBean);
                KLog.e("addddd");
                obsever.onListChange(selectList.toString());
            }
        }else {
            if (contains(selectBean.selectName)){
                selectBean.setSelected(false);
                selectList.remove(selectBean);
                KLog.e("remove");
                obsever.onListChange(selectList.toString());
            }
        }
    }

    private boolean contains(String selectName){
        for (SelectBean selectBean : selectList){
            if (selectBean.selectName.equals(selectName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void postGames() {

        Intent intent = new Intent();
        intent.putExtra(ConstantTag.SELECT_RESULT_KEY, (Serializable) selectList);
        activity.setResult(ConstantTag.SELECT_RESULTCODE,intent);
        activity.finish();
    }

}
