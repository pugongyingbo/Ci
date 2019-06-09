package com.it.zzb.ci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.it.zzb.ci.gson.OneData;
import com.it.zzb.ci.util.HttpUtil;
import com.it.zzb.ci.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    RecyclerView recyclerView;
    OneAdapter adapter;
    List<OneData> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        tv = findViewById(R.id.tv);
    }
    public void init(){
        String url = "https://api.gushi.ci/all";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"获取信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
    //            final List<OneData> data  = Utility.handleOnedata(responseText);
               final OneData data = Utility.handleOnedata(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //更新界面
                        //showInfo(data);
                        tv.setText(data.content);
                    }
                });

            }
        });

    }
}