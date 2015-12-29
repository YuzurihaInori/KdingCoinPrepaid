package com.kding.kdingcoinprepaid.qxzapi.utils;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by mike on 15-1-19.
 */
public class EncryptHelp {

    private static final String SERVER_KEY_PUBLIC =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsqlbe/B5drdIDTFzyWbALOzul" +
                    "8OSGcIxWXLRLWgJfMkhVo+pwsZvXw5QN/LbXD0J4h1ZdyhLKXr1PtFk70DRbbihl" +
                    "HAvBZ81gZrrNJjiUu0AFH9R4KqqYwDM679avSCUJSpUUMz1tqxV2D7uoF+/myI83" +
                    "hzm48u/cwwsaz81HUwIDAQAB";

    private static final String CLIENT_KEY_PRIVATE =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALyyi6PfuTdZ+9mc" +
                    "RMu88UfE0tea20+KUFj+yHnkmDflp7ucw+losv23Gm6of0OwTh2PNYcHeDLpk6xq" +
                    "lkfECAELny1n6PdPIMrAV1VYMOD+oLsL1+cskOjouCQJtrXR4hmWWX6qC2nJWvbe" +
                    "RZaASQ5jHUAg2HLX4mxtPsWr4ju3AgMBAAECgYB0ofdl5xbYe6oLq2dqdvK75ZBc" +
                    "6766v0dCetj3XrAnfK/cat09HBXmdJLF6ygeco8V/jqbp6ZH8c/xNkCFQ0meKf1z" +
                    "v9P07qrGuWNhOalAU/I+ITJcEtcl15AzN+3U+paPd7s6caLnrvNV91dNaRAg2jgj" +
                    "9sIKy8lwqaXhiLL2oQJBAPjrSQwfWxD9EPMLxvtKFv5Ny0Nwagz/bCj55of1qeGN" +
                    "iDzdaHiUWY53KfbB999GIn6DaSqOFcHKO/rLdEaNPtMCQQDCELSSvvATklZ0YDFH" +
                    "eX7dWNBDdYksfaOV6zgfIl1pA9bNOFNJeLk1SLvGCWqaQJCSDxHX/l2BJj3TTahQ" +
                    "WOkNAkEAxIMj8SEUCO5xIh/LIHnWez+5V+14m/hOUG8x02Zbjojo5Hw7TO55YWKs" +
                    "S3XIlYlOFCj0rrbrcEmTXqSekFBUJwJAGCwCgeC8gIOSty4gFToB3koorq5eJqeD" +
                    "j7HbrK0YG3N59tfUL+uUjhmAIfucRphSKY8s9s1dEjAUNVSP6WoZpQJARSwak50+" +
                    "OCdncbUM/gwfnDHvr8AhV5G0gQovi70OqVPh6K8bNpmv+rzbDiUpHH695FyG0BM1" +
                    "FlDvnfRZGRI82Q==";


    public static List<NameValuePair> encrypt(List<NameValuePair> list) {
        List<NameValuePair> result = new ArrayList<NameValuePair>();
        try
        {
            //request data
            String strBusiness = generateBusinessString(list);
            String tmpKey = generateTempKey();
            String strReqData = generateReqData(strBusiness, tmpKey);

            //request key
            String strKey = encryptTempKey(tmpKey);

//            String showReqData = "reqdata=";
//            showReqData += strReqData;
//            System.out.println(showReqData);

//            String showKey = "reqkey=";
//            showKey += strKey;
//            System.out.println(showKey);

            result.add(new BasicNameValuePair("reqdata", strReqData));
            result.add(new BasicNameValuePair("reqkey", strKey));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public static String decryptResponse(String strResponse)
    {
        String strResult = "";

        try
        {
            //urldecode
            String strUrlDecode = java.net.URLDecoder.decode(strResponse, "UTF-8");

            //base64 decode
            byte[] decodeBytes = Base64Utils.decode(strUrlDecode);

            //AES解密
            String tempKey = generateTempKey();
            String decAES = decryptResponseWithAES(decodeBytes, tempKey);

            strResult = decAES;
//            System.out.println("decrypt result = " + strResult);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return strResult;
    }


    private static String decryptResponseWithAES(byte[] strBusiness, String sKey)
    {
        String strResult = "";

        try
        {
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decrypted = cipher.doFinal(strBusiness);

            strResult = new String(decrypted);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return strResult;
    }

    private static String generateBusinessString(List<NameValuePair> list) {
        String strResult = "";

        try {
            //step1. 将键值对按key排序并组装
            String strBusiness = "";
            Collections.sort(list, new Comparator<NameValuePair>() {
                public int compare(NameValuePair arg0, NameValuePair arg1) {
                    return arg0.getName().compareTo(arg1.getName());
                }
            });
            for (NameValuePair p : list) {
                if (strBusiness != "")
                {
                    strBusiness += "&";
                }
                strBusiness += p.getName();
                strBusiness += "=";
                strBusiness += p.getValue();
            }
            System.out.println(strBusiness);

            //get private key
            PrivateKey clientPKey = getPrivateKey();

            //step2. 生成sign. MD5withRSA
            java.security.Signature signet = java.security.Signature.getInstance("MD5withRSA");
            signet.initSign(clientPKey);
            signet.update(strBusiness.getBytes("UTF-8"));
            byte[] digest = signet.sign();
            String sign = Base64Utils.encode(digest);
            System.out.println(sign);

            //step3. 连接sign的业务参数串
            strBusiness += "&sign=" + java.net.URLEncoder.encode(sign, "UTF-8");
            System.out.println(strBusiness);

            strResult = strBusiness;

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return strResult;
    }

    private static PrivateKey getPrivateKey()
    {
        PrivateKey privateKey = null;

        try
        {
            byte[] keys = Base64Utils.decode(CLIENT_KEY_PRIVATE);
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(keys);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            privateKey = factory.generatePrivate(priPKCS8);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return privateKey;
    }

    private static PublicKey getPublicKey()
    {
        PublicKey publicKey = null;

        try
        {
            byte[] keys = Base64Utils.decode(SERVER_KEY_PUBLIC);
            KeySpec keySpec = new X509EncodedKeySpec(keys);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            publicKey = factory.generatePublic(keySpec);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return publicKey;
    }

    private static String generateTempKey(){
        String strResult = "9acda1ea7f99396b";
        return strResult;

//        SimpleDateFormat sdf=new SimpleDateFormat("yymmddhhmmssSSS");
//        strResult = sdf.format(new Date());
//        strResult += "0";
//        System.out.println(strResult);
//
//        return strResult;
    }

    private static String generateReqData(String strBusiness, String sKey)
    {
        String strResult = "";
        try
        {
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(strBusiness.getBytes("utf-8"));

            strResult = Base64Utils.encode(encrypted);

            strResult = java.net.URLEncoder.encode(strResult, "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return  strResult;
    }

    private static String encryptTempKey(String strKey)
    {
        String strResult = "";

        try
        {
            PublicKey publicKey = getPublicKey();

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypted = cipher.doFinal(strKey.getBytes());

            strResult = Base64Utils.encode(encrypted);
            strResult = java.net.URLEncoder.encode(strResult, "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return strResult;
    }

    public static List<NameValuePair> ParseUrlParams(String params) {
        List<NameValuePair> result = new ArrayList<NameValuePair>();

        String[] arrSplit=null;
        arrSplit = params.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            if (arrSplitEqual.length > 1) {
                result.add(new BasicNameValuePair(arrSplitEqual[0], arrSplitEqual[1]));
            } else {
                if (arrSplitEqual[0] != "") {
                    result.add(new BasicNameValuePair(arrSplitEqual[0], ""));
                }
            }
        }

        return result;
    }

    public static String FindParamInList(List<NameValuePair> list, String name)
    {
        String result = "";
        NameValuePair temp;

        int count = list.size();
        for (int index = 0; index < count; index++)
        {
            temp = list.get(index);
            if (null == temp)
            {
                continue;
            }

            if (temp.getName().equalsIgnoreCase(name))
            {
                result = temp.getValue();
                break;
            }
        }

        return result;
    }

}
