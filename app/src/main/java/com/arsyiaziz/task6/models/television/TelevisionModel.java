package com.arsyiaziz.task6.models.television;

import com.google.gson.annotations.SerializedName;

public class TelevisionModel {
    @SerializedName("name")
    private String title;

    @SerializedName("vote_average")
    private double rating;

    @SerializedName("in_production")
    private boolean inProduction;

    @SerializedName("number_of_episodes")
    private int numEpisodes;

    @SerializedName("number_of_seasons")
    private int numSeasons;

    private String overview;

    @SerializedName("poster_path")
    private String imgUrl;

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return String.format("%.1f/10", rating);
    }

    public String getInProduction() {
        return (inProduction ? "In Production" : "Not in Production");
    }

    public String getNumEpisodes() {
        return Integer.toString(numEpisodes);
    }

    public String getNumSeasons() {
        return Integer.toString(numSeasons);
    }

    public String getOverview() {
        return overview;
    }

    public String getImgUrl() {
        return imgUrl;
    }



}
