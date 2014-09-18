package com.example.cicerone.first.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.ArrayList;

/**
 * Created by francesco on 18/09/14.
 */


public class CollectionAdapter extends FragmentStatePagerAdapter {

    private
    ArrayList<Fragment> _collection;

    public CollectionAdapter(FragmentManager fm,ArrayList<Fragment> items) {
        super(fm);
        this._collection = items;
    }

    @Override
    public Fragment getItem(int i) {

        return this._collection.get(i);
    }

    @Override
    public int getCount() {
        return this._collection.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return (CharSequence)this._collection.get(position).getTag();

    }
}


