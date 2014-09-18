package com.example.cicerone.first.ActionBar;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.example.cicerone.first.Activity.MainActivity;
import com.example.cicerone.first.Adapter.CollectionAdapter;
import com.example.cicerone.first.Fragment.Forecast3hFragment;
import com.example.cicerone.first.Fragment.ForecastDailyFragment;
import com.example.cicerone.first.R;

import java.util.ArrayList;


/**
 * Created by francesco on 17/09/14.
 */
public class TabListener<T extends Fragment> implements ActionBar.TabListener {

    private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;
    private CollectionAdapter mCollectionAdapter;
    private ViewPager mViewPager;


    public TabListener(final MainActivity mainActivity, String title, Class<T> forecastFragmentClass) {

        this.mActivity = mainActivity;
        this.mTag = title;
        this.mClass = forecastFragmentClass;

        /*
            ViewPager setup for Tab Scrolling
         */
        mCollectionAdapter =
                new CollectionAdapter(
                        mainActivity.getSupportFragmentManager(),new ArrayList<Fragment>(){{add(new ForecastDailyFragment());add(new Forecast3hFragment());}});
        mViewPager = (ViewPager) this.mActivity.findViewById(R.id.pager);
        mViewPager.setAdapter(mCollectionAdapter);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        /* When swiping between pages, select the
                           corresponding tab.*/
                        mainActivity.getSupportActionBar().setSelectedNavigationItem(position);
                    }
                });

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        this.mViewPager.setCurrentItem(tab.getPosition());


    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        if (this.mFragment != null) {
            /* Detach the fragment, because another one is being attached*/
            fragmentTransaction.detach(this.mFragment);
        }

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
