package com.example.hemamostafa.hatly_1;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.hemamostafa.hatly_1.Adapter.ViewPagerAdapter;

public class DealsAndMatchshipments extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals_and_match);
        toolbar = (Toolbar) findViewById(R.id.collapsingToolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager_2);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs_2);
        tabLayout.setupWithViewPager(viewPager);

    }

        private void setupViewPager(ViewPager viewPager) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new FragmentDeals(), "DEALS");
            adapter.addFragment(new FragmentMatchingShipments(), "MATCHING SHIPMENTS");
            viewPager.setAdapter(adapter);
        }
}
