package com.abhishesh.projects.medicine.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.abhishesh.projects.medicine.R;
import com.abhishesh.projects.medicine.adapter.MedicinePagerAdapter;

/**
 * Created by Abhishesh on 15/10/15.
 */
public class DetailActivity extends FragmentActivity implements View.OnClickListener {

    public static final String EXTRA_ITEM = "extra_item";
    private long id;
    private ItemPagerAdapter mAdapter;
    private static String TAG = "DetailActivity";
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_layout);

        mAdapter = new ItemPagerAdapter(getSupportFragmentManager(), 10);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(2);

        final int extraCurrentItem = getIntent().getIntExtra(EXTRA_ITEM, -1);
        if (extraCurrentItem != -1) {
            mPager.setCurrentItem(extraCurrentItem);
        }
    }

    @Override
    public void onClick(View view) {

    }

    private class ItemPagerAdapter extends FragmentStatePagerAdapter {
        private final int mSize;

        public ItemPagerAdapter(FragmentManager fm, int size) {
            super(fm);
            mSize = size;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public Fragment getItem(int position) {
            return DetailFragment.newInstance(id);
        }
    }
}
