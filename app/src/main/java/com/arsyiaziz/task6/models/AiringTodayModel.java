package com.arsyiaziz.task6.models;

import com.google.gson.annotations.SerializedName;

public class AiringTodayModel {
    private String id;

    @SerializedName("name")
    private String title;

    @SerializedName("poster_path")
    private String imgUrl;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImgUrl() {
        return imgUrl;
    }



}
