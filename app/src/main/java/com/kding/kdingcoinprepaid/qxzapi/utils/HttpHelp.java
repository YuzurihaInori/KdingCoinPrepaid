package com.kding.kdingcoinprepaid.qxzapi.utils;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;

import com.kding.gc.httprequesttest.HttpRequestMy;
import com.kding.gc.httprequesttest.HttpRequestParames;
import com.kding.gc.httprequesttest.RequestInte;
import com.kding.kdingcoinprepaid.consts.HttpUrl;
import com.kding.kdingcoinprepaid.qxzapi.General;
import com.kding.kdingcoinprepaid.qxzapi.QxzSdkApi;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Created by wxue(xueminwei@weimiansoft.com) on 15/1/12.
 */

public class HttpHelp {
	

	// bind username and tradenumber
	// data collection
	public static void saveInfo(String acount,String userName,String szTradeNumber, String szMoney,
			String szPayType, RequestInte objRequestInte) {
		HttpRequestParames params = new HttpRequestParames();
		params.setUrl(HttpUrl.URL_RECHARGE);

		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("username", acount));
		list.add(new BasicNameValuePair("lineno", szTradeNumber));
		list.add(new BasicNameValuePair("money", szMoney));
		list.add(new BasicNameValuePair("paytype", szPayType));

		// params.setNameValuePairs(list);
		List<NameValuePair> encryptList = EncryptHelp.encrypt(list);
		params.setNameValuePairs(encryptList);

		HttpRequestMy request = HttpRequestMy.getSingleInstance();
		request.post(params, objRequestInte, 1);
	}

}
