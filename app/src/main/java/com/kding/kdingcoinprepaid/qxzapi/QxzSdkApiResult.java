package com.kding.kdingcoinprepaid.qxzapi;

import android.os.Bundle;

/**
 * Created by wxue(xueminwei@weimiansoft.com) on 15/1/11.
 */
public interface QxzSdkApiResult {
    final int ERROR_CONTEXT_INVALID = 0x1;

    final int ERROR_REGISTER_FAILED = 0x2;
    final int ERROR_USER_EXIST = 0x3;

    final int ERROR_LOGIN_FAILED = 0x4;
    final int ERROR_PASSWD_NOT_MATCH = 0x5;
    final int ERROR_USERNAME_TOO_SHORT = 0x6;
    final int ERROR_PASSD_TOO_SHORT = 0x7;
    final int ERROR_AGREEMENT_NOT_AGREED = 0x8;

    final int ERROR_ALIPAY_RESULT_CONFIRMING = 0x9;
    final int ERROR_ALIPAY_RESULT_FAIED = 0xa;
//    final int ERROR_ALIPAY_RESULT_CANCELED = 0xb;

    final int ERROR_UNIONPAY_NETWORK_ERROR = 0xc;

    final int ERROR_UNIONPAY_RESULT_FAILED = 0xd;
    final int ERROR_UNIONPAY_RESULT_CANCELED = 0xe;

    final int ERROR_ACCOUNT_BINDING_FAILED = 0xf;

    final int ERROR_NOT_LOGIN = 0x10;

    final int ERROR_GOLDCOINPAY_RESULT_FAILED = 0x11;
    final int ERROR_GOLDCOINPAY_RESULT_NOT_ENOUGH = 0x12;

    final int ERROR_DLG_EXIT_CANCEL = 0x13;

    public void onSuccess(Bundle bundle);
    public void onFailure(int nCode);
}
