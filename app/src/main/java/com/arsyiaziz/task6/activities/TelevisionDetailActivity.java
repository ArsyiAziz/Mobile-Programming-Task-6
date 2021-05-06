package com.arsyiaziz.task6.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arsyiaziz.task6.R;
import com.arsyiaziz.task6.misc.Constants;
import com.arsyiaziz.task6.models.movie.MovieModel;
import com.arsyiaziz.task6.models.television.TelevisionModel;
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

public class TelevisionDetailActivity extends AppCompatActivity {
    private static final String TAG = "TVDetailActivity";

    public static final String TELEVISION_ID = "TELEVISION_ID";
    private TextView tvTitle;
    private TextView tvRating;
    private TextView tvOverview;
    private ImageView ivPoster;
    private FrameLayout progressOverlay;
    private TextView tvNumSeasons;
    private TextView tvNumEpisodes;
    private TextView tvInProduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_television_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressOverlay = findViewById(R.id.progress_overlay);
        tvTitle = findViewById(R.id.tv_title);
        tvRating = findViewById(R.id.tv_rating);
        tvInProduction = findViewById(R.id.tv_in_production);
        tvOverview = findViewById(R.id.tv_overview);
        tvNumSeasons = findViewById(R.id.tv_num_seasons);
        ivPoster = findViewById(R.id.iv_poster);
        tvNumEpisodes = findViewById(R.id.tv_num_episodes);
        loadData(getIntent().getStringExtra(TELEVISION_ID));
    }

    private void loadData(String televisionID) {
        MovieApiInterface movieApiInterface = MovieApiClient.getRetrofit()
                .create(MovieApiInterface.class);
        Call<TelevisionModel> televisionModelCall = movieApiInterface
                .getTelevision(televisionID, Constants.API_KEY);
        televisionModelCall.enqueue(new Callback<TelevisionModel>() {
            @Override
            public void onResponse(Call<TelevisionModel> call, Response<TelevisionModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    setActionBarTitle(response.body().getTitle());
                    tvTitle.setText(response.body().getTitle());
                    tvRating.setText(response.body().getRating());
                    tvInProduction.setText(response.body().getInProduction());
                    tvOverview.setText(response.body().getOverview());
                    tvNumSeasons.setText(response.body().getNumSeasons());
                    tvNumEpisodes.setText(response.body().getNumEpisodes());
                    Glide.with(TelevisionDetailActivity.this)
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
                    Toast.makeText(TelevisionDetailActivity.this, "An error has occurred", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<TelevisionModel> call, Throwable t) {
                Log.d(TAG,"onFailure: " + t.getMessage());
                Toast.makeText(TelevisionDetailActivity.this, "An error has occurred", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}