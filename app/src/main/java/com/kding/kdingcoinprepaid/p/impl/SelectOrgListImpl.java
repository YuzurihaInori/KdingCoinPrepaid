package com.kding.kdingcoinprepaid.p.impl;

import android.app.Activity;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kding.kdingcoinprepaid.bean.SelectBean;
import com.kding.kdingcoinprepaid.bean.result.SelectListBean;
import com.kding.kdingcoinprepaid.bean.result.SelectOrgBean;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.consts.HttpUrl;
import com.kding.kdingcoinprepaid.consts.UserType;
import com.kding.kdingcoinprepaid.p.ISelectOrgList;
import com.kding.kdingcoinprepaid.p.callback.IResultCallBack;
import com.kding.kdingcoinprepaid.p.callback.SelectListChangeObsever;
import com.socks.library.KLog;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toast-pc on 2015/12/21.
 */
public class SelectOrgListImpl implements ISelectOrgList{


    private final Activity activity;
    private final IResultCallBack<SelectBean> resultCallBack;
    private List<SelectBean> selectList = new ArrayList<>();
    private final SelectListChangeObsever obsever;

    public SelectOrgListImpl(Activity activity,SelectListChangeObsever obsever ,IResultCallBack<SelectBean> resultCallBack) {
        this.activity = activity;
        this.obsever = obsever;
        this.resultCallBack = resultCallBack;
    }

    @Override
    public List<String> getDataFromServer(String tag, final String gameId) {

        switch (tag){
            case ConstantTag.GAME_LIST_KEY:
                RequestQueue requestQueue = Volley.newRequestQueue(activity.getApplicationContext());

                StringRequest jsonObjectRequest = new StringRequest(
                        Request.Method.POST,
                        HttpUrl.URL_GETAPPS,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                KLog.json(response);

                                SelectListBean selectListBean = JSON.parseObject(response, SelectListBean.class);

                                if (selectListBean.getError() == 1) {
                                    resultCallBack.resultSuc(selectListBean.getData());
                                } else {
                                    resultCallBack.resultFail(selectListBean.getMsg());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                resultCallBack.resultFail("连接服务器出错");
                            }
                        }) {};
                requestQueue.add(jsonObjectRequest);

                break;
            case ConstantTag.UNION_LIST_KEY:

                RequestQueue requestQueue2 = Volley.newRequestQueue(activity.getApplicationContext());

                StringRequest jsonObjectRequest2 = new StringRequest(
                        Request.Method.POST,
                        HttpUrl.URL_GETGUILDSBYAPP,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                KLog.e(response);

                                SelectListBean selectListBean = JSON.parseObject(response, SelectListBean.class);

                                if (selectListBean.getError() == 1) {
                                    resultCallBack.resultSuc(selectListBean.getData());
                                } else {
                                    resultCallBack.resultFail(selectListBean.getMsg());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                resultCallBack.resultFail("连接服务器出错");
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<>();
                        params.put("appid", gameId);
                        KLog.d(params);
                        return params;
                    }
                };
                requestQueue2.add(jsonObjectRequest2);
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

        if (UserInfoImpl.getInstance().getUserInfo().getUsertype() == UserType.UNBIND_PRESIDENT){
            addToSelect(selectBean);
        }else {

            for (SelectBean bean :selectList){
                bean.setSelected(false);
            }
            selectList.clear();
            addToSelect(selectBean);
        }
    }

    private void addToSelect(SelectBean selectBean) {
        if (!selectBean.isSelected()){
            if (!contains(selectBean.id)){
                selectBean.setSelected(true);
                selectList.add(selectBean);
                KLog.e("addddd");
                obsever.onListChange(selectList.toString());
            }
        }else {
            if (contains(selectBean.id)){
                selectBean.setSelected(false);
                selectList.remove(selectBean);
                KLog.e("remove");
                obsever.onListChange(selectList.toString());
            }
        }
    }

    private boolean contains(String selectName){
        for (SelectBean selectBean : selectList){
            if (selectBean.id.equals(selectName)){
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
