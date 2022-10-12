package com.digagr.farming.jpush;

import android.content.Context;

import com.digagr.farming.baselibrary.ToastHelper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;

/**
 * Copyright(c) 2020 极光
 * Description
 */
public class MsgUtils {
    private MsgUtils() {
    }

    public static void buildLocalNotification(Context context, String tittle, String content){
        JPushLocalNotification ln = new JPushLocalNotification();
        ln.setBuilderId(0);
        ln.setContent(content);
        ln.setTitle(tittle);
        long id = System.currentTimeMillis()/1000;
        ln.setNotificationId(id) ;
        ln.setBroadcastTime(System.currentTimeMillis());
        Map<String , Object> map = new HashMap<String, Object>() ;
        map.put("name", "jpush") ;
        map.put("test", "111") ;
        JSONObject json = new JSONObject(map) ;
        ln.setExtras(json.toString()) ;
        JPushInterface.addLocalNotification(context, ln);
        ToastHelper.showOk(context, "添加本地通知，notification_id:" + id);
    }

}
