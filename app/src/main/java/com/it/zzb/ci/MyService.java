package com.it.zzb.ci;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.util.Random;


/**
 * Created by zzb on 2017/12/30.
 */

/**
 * 用来与widget进行交互的Service 提供Widget中显示的数字
 */

public class MyService extends Service {
    MyReceiver receiver;
    @Override
    public void onCreate() {
        super.onCreate();
        //动态注册广播接收器
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ACTION_MAKE_NUMBER");
        registerReceiver(receiver, filter);
    }
    @Override
    public void onDestroy() {
        //注销广播接收器
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 广播接收器
     */
    private class MyReceiver extends BroadcastReceiver {
        // 接收到Widget发送的广播
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("ACTION_MAKE_NUMBER".equals(intent.getAction())) {
                // 生成一个随机数字，以系统广播的形式将这个数字提交到
                AppWidgetManager manager = AppWidgetManager.getInstance(context);
                ComponentName provider = new ComponentName(context,MyWidget.class);
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                //设置要显示的TextView，及显示的内容
                views.setTextViewText(R.id.tv_widget_number, new Random().nextInt(2000)+"");
                // 发送一个系统广播
                manager.updateAppWidget(provider, views);
            }
        }

    }

}