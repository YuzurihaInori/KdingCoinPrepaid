package com.kding.kdingcoinprepaid.qxzapi.aliapy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.kding.gc.httprequesttest.RequestInte;
import com.kding.kdingcoinprepaid.qxzapi.General;
import com.kding.kdingcoinprepaid.qxzapi.QxzSdkApiResult;
import com.kding.kdingcoinprepaid.qxzapi.utils.HttpHelp;

/**
 * Created by wxue(xueminwei@weimiansoft.com) on 15/1/19.
 * Wrapped the methods for alipay.
 */
public class Alipay {
    private static final String PARTNER = "2088712527685232";
    private static final String SELLER = "goodluckyemin@163.com";
    private static final String ASYNC_NOTIFY_URL = "http://sdk.7xz.com/sdk/notify";
    private static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANpLLMHvICcOYUvhVa5QFj0y6OikNMDy3iWOlpgvTZg6uPvj8ZRXgR/8RyUnMF3KKOzEbf0yTtFq8IVxec/binXr9FJBMaKmC8eRIBhVgB+kmDFm4Y9XRJZTMSMYYw1wCD5C4jFEmFeszq+Hs9VycGZhnHc2Ad8PiOEpuOHUoLgVAgMBAAECgYEAnz9sPWZ3LEbfT/3pBatDLg4vv1fbbE9BJGDrF8aacs2UGZ5mpHNZrb3bIQ1/yUwrot6B5s6GlVvQb2kjFjHwxVENv4s+ZB/4ArJ3StQINK+nQr+PfLlRm4uRgOTLhja7LhhV/8ujNsLR2GH7UMs4v9IOw235GXmBH4C0EtsZClkCQQDw4NoTr4+tBNuLCeK1agfvNswcG2VouJPsEnNjbEHL6PEOvzJ6mWwf6zxn7TiqZ8cYu76kWJdZBlt6um8rle6fAkEA5/9dh0AWmeiRpcJD1EQYSZ2RVYOVhXqEUZxHmkDgFVS5gcbgERO8hBfU/AJapmufMdbqCnegDHffO89BZHOAywJBAK10rXVJkgO8oL+hscCDhw0qf5ap37+mUtbHfpIUzdiheJzu79G7E1J02b3Y5FpJeuSDpcfBB9OSsWNSR2WyhO8CQB/A2gJLCUzjghvX7QS+lTxuLpgvvGVakYXCCCLawlDpGcLjf4OW2fSSdnpPsCybClz992abXPKX7Zv4EBE5peUCQQCe0wnXJH+1caoSuwe5JtJdXdP6Piw+15KBzJhnsRwC+hld1KuBnNmvZ3HITjJIP6UmnxhFrGBpruCo2SEbKg5+";

    private static final String ALGORITHM = "RSA";
    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final int SDK_PAY_FLAG = 1;
//    private static final int SDK_CHECK_FLAG = 2;
    private static final int SDK_PAY_BINDING = 3;
    private static final int SDK_PAY_FAILED = 4;

    private static QxzSdkApiResult mObjResult = null;
    private static String mszPayInfo = "";
    private static Handler mHandler = null;
    private static Activity mActivity = null;

    private static ProgressDialog mLoadingDialog = null;

    public static void pay(Activity activity,
    						String acount,
                           String szName,
                           String szDescript,
                           String szPrice,
                           QxzSdkApiResult objResult) {
        mObjResult = objResult;
        mActivity = activity;

        mHandler = Alipay.createHandler(mObjResult);

        String szTradeNumber = Alipay.getOutTradeNo();
        mszPayInfo = Alipay.createPayInfo(szName, szDescript, szPrice, szTradeNumber);

        mLoadingDialog = ProgressDialog.show(activity, "", "正在努力的获取交易流水号中,请稍候...", true);

        HttpHelp.saveInfo(acount, szName, szTradeNumber, szPrice, "alipay", new RequestInte() {
            @Override
            public void textLoaded(String s, int i) {
                if (mLoadingDialog.isShowing()) {
                    mLoadingDialog.dismiss();
                }

                if (null != s && s.equals("success")) {
                    Log.d(General.LOG_TAG, "account and tradeno binding succeded!");

                    Message msg = mHandler.obtainMessage();
                    msg.what = Alipay.SDK_PAY_BINDING;
                    mHandler.sendMessage(msg);
                } else {
                    Log.d(General.LOG_TAG, "account and tradenumber binding failed!");

                    Message msg = mHandler.obtainMessage();
                    msg.what = SDK_PAY_FAILED;
                    msg.arg1 = QxzSdkApiResult.ERROR_ACCOUNT_BINDING_FAILED;
                    mHandler.sendMessage(msg);
                }
            }

            @Override
            public void textfail(int i, Exception e) {
                if (mLoadingDialog.isShowing()) {
                    mLoadingDialog.dismiss();
                }

                Log.d(General.LOG_TAG, "account and tradenumber binding failed!");

                Message msg = mHandler.obtainMessage();
                msg.what = SDK_PAY_FAILED;
                msg.arg1 = QxzSdkApiResult.ERROR_ACCOUNT_BINDING_FAILED;
                mHandler.sendMessage(msg);
            }
        });

    }

    public static String createPayInfo(String szName, String szDesc, String szPrice, String szTradeNumber) {
    	System.out.println(szName+"|"+szDesc+"|"+szPrice+"|"+szTradeNumber);
        String szOrderInfo = getOrderInfo(szName, szDesc, szPrice, szTradeNumber);
        String szSignedOrder = sign(szOrderInfo);

        try {
            szSignedOrder = URLEncoder.encode(szSignedOrder, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String szPayInfo = szOrderInfo + "&sign=\"" + szSignedOrder + "\"&" + getSignType();
        return szPayInfo;
    }

    private static Handler createHandler(final QxzSdkApiResult objResult) {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case Alipay.SDK_PAY_FLAG: {
                        Result resultObj = new Result((String) msg.obj);
                        String szResultStatus = resultObj.resultStatus;

                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        if (TextUtils.equals(szResultStatus, "9000")) {
                            objResult.onSuccess(Bundle.EMPTY);
                        } else {
                            // 判断resultStatus 为非“9000”则代表可能支付失败
                            // “8000” 代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            if (TextUtils.equals(szResultStatus, "8000")) {
                                objResult.onFailure(QxzSdkApiResult.ERROR_ALIPAY_RESULT_CONFIRMING);
                            } else {
                                objResult.onFailure(QxzSdkApiResult.ERROR_ALIPAY_RESULT_FAIED);
                            }
                        }
                        break;
                    }
                    case Alipay.SDK_PAY_BINDING: {

                        Alipay.pay(mActivity, mHandler, mszPayInfo);
                        break;
                    }
                    case Alipay.SDK_PAY_FAILED: {
                        mObjResult.onFailure(msg.arg1);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        };

        return handler;
    }

    private static void pay(final Activity activity, final Handler handler, final String szPayInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipayTask = new PayTask(activity);
                String szResult = alipayTask.pay(szPayInfo);

                Message msg = new Message();
                msg.what = Alipay.SDK_PAY_FLAG;
                msg.obj = szResult;
                handler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * create the order info. 创建订单信息
     *
     */
    private static String getOrderInfo(String subject, String body, String price, String tradeNo) {
        // 合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + tradeNo + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + ASYNC_NOTIFY_URL
                + "\"";

        // 接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }


    /**
     * get the out_trade_no for an order. 获取外部订单号
     *
     */
    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
                Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    private static String sign(String content) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.decode(RSA_PRIVATE));
            KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            Signature signature = Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));

            byte[] signed = signature.sign();

            return Base64.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    private static String getSignType() {
        return "sign_type=\"RSA\"";
    }
}
