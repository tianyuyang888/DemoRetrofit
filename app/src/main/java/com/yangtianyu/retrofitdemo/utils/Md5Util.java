package com.yangtianyu.retrofitdemo.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by MicroInk on 2016/7/19 10:13.
 * Email microink@foxmail.com
 */
public class Md5Util {
    public final static String APP_SECRET = "qo5ee6f5-cb6b-4dt0-8o1l-3d415c17a500";

    //  参数--->password=b111111&phoneNumber=15829583809
    //  参数--->{password=b111111, phoneNumber=15829583809}

    public static String getSign(HashMap map) {

       // Log.i("123456789",sign);
        List<String> list = new ArrayList<>();
        for(Object key : map.keySet()){
            list.add("\""+key+"\""+":"+"\""+map.get(key)+"\"");
        }
        Collections.sort(list);

        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size()-1){
                str.append(list.get(i)).append("}");
            }else {
                str.append(list.get(i)).append(",");
            }
        }
//
//        String sign = map.toString();
//
//        String arr[] =  sign.substring(1,sign.length()-1).split(",");
//        for(int i = 0;i<arr.length;i++){
//            arr[i] = arr[i].toString().trim();
//        }
//        Arrays.sort(arr);  //进行排序
//        String str = "";
//        for (int i = 0; i < arr.length; i++) {
//            if (i == arr.length - 1) {
//                str += (arr[i]);
//            } else {
//                str += (arr[i] + "\",\"");
//            }
//        }
//        str = "{\"" + str.replace("=", "\":\"") + "\"}";
        Log.i("3333", str.toString().trim());
        return get(str.toString().trim(), APP_SECRET);

    }

    public static String getSign(RequestBody params) {

        // FormBody.Builder body = new FormBody.Builder().add("", "");

        String sign = params.toString();
        String arr[] = sign.split("&");
        Arrays.sort(arr);  //进行排序
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                str += (arr[i]);
            } else {
                str += (arr[i] + "\",\"");
            }
        }
        str = "{\"" + str.replace("=", "\":\"") + "\"}";
        Log.i("222222", str);
        return get(str, APP_SECRET);
    }

    public static String get(String id, String num) {
        String str = id + num;
        byte[] digest = null;
        try {
            digest = MessageDigest.getInstance("MD5").digest(
                    str.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder md5 = new StringBuilder(digest.length * 2);
        for (byte b : digest) {
            if ((b & 0xFF) < 0x10)
                md5.append("0");
            md5.append(Integer.toHexString(b & 0xFF));
        }
        return md5.toString();
    }
}
