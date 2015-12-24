package com.kding.kdingcoinprepaid.utils;

import android.support.annotation.NonNull;

/**
 * Created by Toast-pc on 2015/12/23.
 */
public class StringUtils {

    /**
     * 字符串不能为空
     * @param a
     * @param b
     * @return
     */
    public static boolean isEqualsUnNull(@NonNull String a,@NonNull String b){

        return a.equals(b);
    }

    public static boolean isEquals(String a,String b){

        return a == null || b == null || isEqualsUnNull(a,b);
    }

    public static boolean isEqualsUnNullAndHasSize(@NonNull String a,@NonNull String b){

        return !(a.equals("")||b.equals("")) && isEqualsUnNull(a,b);

    }
}
