package com.arsyiaziz.task6.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.arsyiaziz.task6.R;
import com.arsyiaziz.task6.activities.TelevisionDetailActivity;
import com.arsyiaziz.task6.adaptors.AiringTodayAdapter;
import com.arsyiaziz.task6.misc.Constants;
import com.arsyiaziz.task6.misc.OnItemClickListener;
import com.arsyiaziz.task6.models.AiringTodayModel;
import com.arsyiaziz.task6.models.AiringTodayResponse;
import com.arsyiaziz.task6.networks.MovieApiClient;
import com.arsyiaziz.task6.networks.MovieApiInterface;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AiringTodayFragment extends Fragment implements OnItemClickListener<AiringTodayModel> {
    private static final String TAG = "AiringTodayFragment";
    private RecyclerView rvAiringToday;
    private AiringTodayAdapter airingTodayAdapter;
    private FrameLayout progressOverlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_airing_today, container, false);
        progressOverlay = view.findViewById(R.id.progress_overlay);
        progressOverlay.setVisibility(View.VISIBLE);
        rvAiringToday = view.findViewById(R.id.rv_airing_today);
        loadData();
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(view.getContext());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setAlignItems(AlignItems.FLEX_START);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        rvAiringToday.setLayoutManager(layoutManager);
        return view;
    }

    private void loadData() {
        MovieApiInterface movieApiInterface = MovieApiClient.getRetrofit()
                .create(MovieApiInterface.class);
        Call<AiringTodayResponse> airingTodayResponseCall = movieApiInterface
                .getAiringToday(Constants.API_KEY);
        airingTodayResponseCall.enqueue(new Callback<AiringTodayResponse>() {
            @Override
            public void onResponse(Call<AiringTodayResponse> call, Response<AiringTodayResponse> response) {
                if (response.isSuccessful() && response.body().getGetAiringToday() != null) {
                    airingTodayAdapter = new AiringTodayAdapter(response.body().getGetAiringToday(), AiringTodayFragment.this);
                    rvAiringToday.setAdapter(airingTodayAdapter);
                    progressOverlay.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG);
                }

            }

            @Override
            public void onFailure(Call<AiringTodayResponse> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Failed" + t.getLocalizedMessage(), Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public void onClick(AiringTodayModel airingTodayModel) {
        Intent intent = new Intent(getActivity(), TelevisionDetailActivity.class);
        intent.putExtra(TelevisionDetailActivity.TELEVISION_ID, airingTodayModel.getId());
        startActivity(intent);
    }
}