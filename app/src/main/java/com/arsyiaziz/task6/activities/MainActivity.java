package com.arsyiaziz.task6.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.arsyiaziz.task6.fragments.AiringTodayFragment;
import com.arsyiaziz.task6.fragments.NowPlayingFragment;
import com.arsyiaziz.task6.fragments.UpcomingFragment;
import com.arsyiaziz.task6.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{
    private FrameLayout flMain;
    private BottomNavigationView bnvMain;
    private Map<Integer, Fragment> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flMain = findViewById(R.id.fl_main);
        bnvMain = findViewById(R.id.bnv_main);

        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.menu_item_now_playing, new NowPlayingFragment());
        fragmentMap.put(R.id.menu_item_airing_today, new AiringTodayFragment());
        fragmentMap.put(R.id.menu_item_upcoming, new UpcomingFragment());
        bnvMain.setOnNavigationItemSelectedListener(this);
        bnvMain.setSelectedItemId(R.id.menu_item_now_playing);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = fragmentMap.get(item.getItemId());
        assert fragment != null;
        switch (item.getItemId()) {
            case R.id.menu_item_now_playing:
                setActionBarTitle(getResources().getString(R.string.now_playing));
                break;
            case R.id.menu_item_airing_today:
                setActionBarTitle(getResources().getString(R.string.airing_today));
                break;
            case R.id.menu_item_upcoming:
                setActionBarTitle(getResources().getString(R.string.upcoming));
                break;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_main, fragment)
                .commit();
        return true;
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}