package com.kding.kdingcoinprepaid.p;

import android.content.Intent;

import java.util.List;

/**
 * Created by Toast-pc on 2015/12/18.
 */
public interface ISelectOrg {

    String getGameEditTextText();
    Intent getGameEditTextIntent();
    String getUnionEditTextText();
    Intent getUnionEditTextIntent();
    boolean getUnionEditTextClickable();
    void postSelectInfo();
}
