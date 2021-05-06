package com.arsyiaziz.task6.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arsyiaziz.task6.R;
import com.arsyiaziz.task6.misc.Constants;
import com.arsyiaziz.task6.models.movie.MovieModel;
import com.arsyiaziz.task6.networks.MovieApiClient;
import com.arsyiaziz.task6.networks.MovieApiInterface;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String TAG = "MovieDetailActivity";

    public static final String MOVIE_ID = "MOVIE_ID";
    private TextView tvTitle;
    private TextView tvRating;
    private TextView tvRuntime;
    private TextView tvReleaseDate;
    private TextView tvOverview;
    private TextView tvBudget;
    private ImageView ivPoster;
    private TextView tvRevenue;
    private FrameLayout progressOverlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_movie_detail);
        progressOverlay = findViewById(R.id.progress_overlay);
        progressOverlay.setVisibility(View.VISIBLE);
        tvTitle = findViewById(R.id.tv_title);
        tvRating = findViewById(R.id.tv_rating);
        tvRuntime = findViewById(R.id.tv_runtime);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvOverview = findViewById(R.id.tv_overview);
        tvBudget = findViewById(R.id.tv_budget);
        ivPoster = findViewById(R.id.iv_poster);
        tvRevenue = findViewById(R.id.tv_revenue);
        loadData(getIntent().getStringExtra(MOVIE_ID));
    }

    private void loadData(String movieID) {
        MovieApiInterface movieApiInterface = MovieApiClient.getRetrofit()
                .create(MovieApiInterface.class);
        Call<MovieModel> movieModelCall = movieApiInterface
                .getMovie(movieID, Constants.API_KEY);
        movieModelCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    setActionBarTitle(response.body().getTitle());
                    tvTitle.setText(response.body().getTitle());
                    tvRating.setText(response.body().getRating());
                    tvRuntime.setText(response.body().getRuntime());
                    tvReleaseDate.setText(response.body().getReleaseDate());
                    tvOverview.setText(response.body().getOverview());
                    tvBudget.setText(response.body().getBudget());
                    tvRevenue.setText(response.body().getRevenue());
                    Glide.with(MovieDetailActivity.this)
                            .load(Constants.BASE_IMG_URL + response.body().getImgUrl())
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    progressOverlay.setVisibility(View.GONE);
                                    return false;
                                }
                            })
                            .into(ivPoster);
                } else {
                    Toast.makeText(MovieDetailActivity.this, "An error has occurred", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.d(TAG,"onFailure: " + t.getMessage());
                Toast.makeText(MovieDetailActivity.this, "An error has occurred", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}