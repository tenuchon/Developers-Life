package com.tenuchon.developerslife.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.tenuchon.developerslife.R;
import com.tenuchon.developerslife.utils.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private LatestFragment latestFragment;
    private HotFragment hotFragment;
    private TopFragment topFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        setSupportActionBar(toolbar);

        latestFragment = new LatestFragment();
        topFragment = new TopFragment();
        hotFragment = new HotFragment();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(latestFragment, getString(R.string.latest));
        viewPagerAdapter.addFragment(topFragment, getString(R.string.top));
        viewPagerAdapter.addFragment(hotFragment, getString(R.string.hot));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}