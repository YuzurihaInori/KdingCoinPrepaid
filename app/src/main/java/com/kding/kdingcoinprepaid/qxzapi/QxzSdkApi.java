package com.kding.kdingcoinprepaid.qxzapi;

import android.app.Activity;
import android.content.Context;

import com.kding.kdingcoinprepaid.qxzapi.aliapy.Alipay;

/**
 * Created by wxue on 15/1/11.
 */
public class QxzSdkApi {

	public static void aliPay(Activity activity, String acount,String szName,
			String szDescript, String szPrice, QxzSdkApiResult objResult) {

		Alipay.pay(activity, acount, szName, szDescript, szPrice, objResult);
	}

}
