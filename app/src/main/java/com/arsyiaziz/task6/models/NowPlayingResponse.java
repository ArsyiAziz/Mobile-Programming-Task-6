package com.arsyiaziz.task6.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingResponse {
    @SerializedName("results")
    private List<NowPlayingModel> nowPlaying;

    public List<NowPlayingModel> getNowPlaying() {
        return nowPlaying;
    }
}
