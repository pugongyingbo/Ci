package com.it.zzb.ci.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.it.zzb.ci.gson.OneData;

import org.json.JSONObject;


/**
 * Created by zzb on 2017/12/30.
 */

public class Utility {

    public static OneData handleOnedata(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
           // JSONArray jsonArray = jsonObject.getJSONArray("data");
            String text = jsonObject.toString();
            OneData data = new Gson().fromJson(text,OneData.class);
            return data;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
