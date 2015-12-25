package com.kding.kdingcoinprepaid.bean;

/**
 * Created by Toast-pc on 2015/12/24.
 */
public class CTCRecordBean {

    public static final int STATUS_OPERATION = 202;
    public static final int STATUS_EXAMINE  = 201;
    public static final int STATUS_SUC = 200;
    public static final int STATUS_FAIL = -1;

    public String cTCRecordLeft ="";
    public String cTCRecordMid ="";
    public String cTCRecordRight ="";

    public int status = STATUS_FAIL;

}
