package com.example.cicerone.first.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cicerone.first.ActionBar.TabListener;
import com.example.cicerone.first.Adapter.CollectionAdapter;
import com.example.cicerone.first.Fragment.Forecast3hFragment;
import com.example.cicerone.first.Fragment.ForecastDailyFragment;
import com.example.cicerone.first.R;


public class MainActivity extends ActionBarActivity {

    private ActionBar actionBar;
    CollectionAdapter mCollectionAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* setup action bar for tabs */
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.Tab tab = actionBar.newTab()
                .setText("Daily")
                .setTabListener(new TabListener<ForecastDailyFragment>(
                        this, "daily", ForecastDailyFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText("3 Hours")
                .setTabListener(new TabListener<Forecast3hFragment>(
                        this, "3hours", Forecast3hFragment.class));
        actionBar.addTab(tab);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Inflate the menu;
        this adds items to the action bar if it is present.
         */
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Handle action bar item clicks here. The action bar will
          automatically handle clicks on the Home/Up button, so long
          as you specify a parent activity in AndroidManifest.xml*/
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (id == R.id.action_map) {
            OpenPreferredLocationInMap();
        }
        return super.onOptionsItemSelected(item);
    }

    private void OpenPreferredLocationInMap() {
        PackageManager manager = getApplicationContext().getPackageManager();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String lat = prefs.getString(getString(R.string.pref_latitude_key), getString(R.string.pref_latitude_default));
        String lon = prefs.getString(getString(R.string.pref_longitude_key), getString(R.string.pref_longitude_default));
        String uri = "geo:" + lat + "," + lon + "?z=11";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            /*No Application can handle your intent*/
            Toast toast = Toast.makeText(getApplicationContext(), "No map app", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}
