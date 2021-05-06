package com.arsyiaziz.task6.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AiringTodayResponse {
    @SerializedName("results")
    private List<AiringTodayModel> getAiringToday;


    public List<AiringTodayModel> getGetAiringToday() {
        return getAiringToday;
    }
}
