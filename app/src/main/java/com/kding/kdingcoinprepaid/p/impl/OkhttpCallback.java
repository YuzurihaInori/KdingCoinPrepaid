package com.kding.kdingcoinprepaid.p.impl;

import android.os.Handler;
import android.os.Looper;

import com.android.volley.Response;
import com.kding.kdingcoinprepaid.p.callback.IOkHttpCallBack;


import java.io.IOException;

/**
 * Created by Toast-pc on 2015/12/28.
 */
public class OkhttpCallback/* implements Callback*/{

//    private final IOkHttpCallBack callBack;
//
//    public OkhttpCallback(IOkHttpCallBack callBack) {
//        this.callBack = callBack;
//    }
//
//    Handler mainHandler = new Handler(Looper.getMainLooper());
//
//    @Override
//    public void onFailure(final Request request, final IOException e) {
//        mainHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                callBack.onFailure(request,e);
//            }
//        });
//    }
//
//    @Override
//    public void onResponse(final Response response) throws IOException {
//        mainHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                callBack.onResponse(response);
//            }
//        });
//    }
}
