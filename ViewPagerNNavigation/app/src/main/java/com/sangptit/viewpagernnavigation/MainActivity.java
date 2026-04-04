package com.sangptit.viewpagernnavigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        ViewpagerAdater adapter = new ViewpagerAdater(
                getSupportFragmentManager(),
                androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);

        // ViewPager → BottomNav sync
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float offset, int offsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: mBottomNavigationView.getMenu().findItem(R.id.person).setChecked(true); break;
                    case 1: mBottomNavigationView.getMenu().findItem(R.id.home).setChecked(true); break;
                    case 2: mBottomNavigationView.getMenu().findItem(R.id.settings).setChecked(true); break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        // BottomNav → ViewPager sync
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.person) {
                mViewPager.setCurrentItem(0);
            } else if (id == R.id.home) {
                mViewPager.setCurrentItem(1);
            } else if (id == R.id.settings) {
                mViewPager.setCurrentItem(2);
            }
            return true;
        });
    }
}