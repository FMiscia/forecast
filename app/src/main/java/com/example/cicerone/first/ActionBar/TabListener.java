package com.example.cicerone.first.ActionBar;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.R.id;

import com.example.cicerone.first.Activity.MainActivity;
import com.example.cicerone.first.R;


/**
 * Created by francesco on 17/09/14.
 */
public class TabListener<T extends Fragment> implements ActionBar.TabListener {

    private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;



    public TabListener(MainActivity mainActivity, String title, Class<T> forecastFragmentClass) {

        this.mActivity = mainActivity;
        this.mTag = title;
        this.mClass = forecastFragmentClass;

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        // Check if the fragment is already initialized
        if (mFragment == null) {
            // If not, instantiate and add it to the activity
            mFragment = Fragment.instantiate(mActivity, mClass.getName());
            fragmentTransaction.add(R.id.container,this.mFragment,this.mTag);
        } else {
            // If it exists, simply attach it in order to show it
            fragmentTransaction.attach(this.mFragment);
        }

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        if (this.mFragment != null) {
            // Detach the fragment, because another one is being attached
            fragmentTransaction.detach(this.mFragment);
        }

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
