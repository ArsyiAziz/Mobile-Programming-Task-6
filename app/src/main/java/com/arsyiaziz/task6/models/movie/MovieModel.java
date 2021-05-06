package com.arsyiaziz.task6.models.movie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieModel {
    private String title;

    @SerializedName("vote_average")
    @Expose(serialize = false)
    private double rating;

    private String status;

    private int runtime;

    @SerializedName("release_date")
    private String releaseDate;

    private String overview;

    @SerializedName("imdb_id")
    private String imdbID;

    @Expose(serialize = false)
    private int budget;

    @SerializedName("poster_path")
    private String imgUrl;

    @Expose(serialize = false)
    private int revenue;

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return String.format("%.1f/10", rating);
    }

    public String getStatus() {
        return status;
    }

    public String getRuntime() {
        int hours = runtime/60;
        int minutes = runtime%60;
        return String.format("%02d:%02d", hours, minutes);
    }

    public String getReleaseDate() {
        return LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern( "yyyy-MM-dd" )).format(DateTimeFormatter.ofPattern( "dd MMMM yyyy" ));
    }

    public String getOverview() {
        return overview;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getBudget() {
        if (budget > 0) {
            return String.format("$%,d", budget);
        } else {
            return "Not Available";
        }
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getRevenue() {
        if (revenue > 0) {
            return String.format("$%,d", revenue);
        } else {
            return "Not Available";
        }
    }
}
