package com.it.zzb.ci.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zzb on 2017/12/30.
 */

public class OneData {
    @SerializedName("content")
    public String content;
    @SerializedName("origin")
    public String origin;
    @SerializedName("author")
    public String author;
    @SerializedName("category")
    public String category;
}
