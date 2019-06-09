package com.it.zzb.ci;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;


import com.it.zzb.ci.gson.OneData;
import com.it.zzb.ci.util.HttpUtil;
import com.it.zzb.ci.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zzb on 2017/12/30.
 */

public class MyWidget extends AppWidgetProvider{

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d("TAG","onReceive方法调用了");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d("TAG","onUpdate方法调用了");
        //给Button绑定一个PendingIntent，当点击按钮是发送给Service发广播
        //当点击Button时，触发PendingIntent,发广播给MyService
        final AppWidgetManager manager = AppWidgetManager.getInstance(context);
        final ComponentName provider = new ComponentName(context,MyWidget.class);
        final RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget_layout);


        String url = "https://api.gushi.ci/all";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final OneData data  = Utility.handleOnedata(responseText);

                        if (data != null && !"".equals(data)){
                            String content = data.content;
                            views.setTextViewText(R.id.tv_widget_number, content);
                            manager.updateAppWidget(provider, views);
                        }
            }
        });


        Intent numberIntent = new Intent("ACTION_MAKE_NUMBER");
     //   views.setOnClickPendingIntent(R.id.btn_widget_button, PendingIntent.getBroadcast(context, 0, numberIntent , PendingIntent.FLAG_UPDATE_CURRENT));


    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d("TAG","onDeleted方法调用了");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d("TAG","onEnabled方法调用了");
        //启动MyService
        Intent intent = new Intent(context,MyService.class);
        context.startService(intent);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d("TAG","onDisabled方法调用了");
        //停止MyService
        Intent intent = new Intent(context,MyService.class);
        context.stopService(intent);
    }

}