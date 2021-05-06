package com.arsyiaziz.task6.models;

import com.google.gson.annotations.SerializedName;

public class NowPlayingModel {
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    private String id;
    private String title;

    @SerializedName("poster_path")
    private String imgUrl;

}
