package com.example.cicerone.first.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cicerone.first.Fragment.ForecastFragment;
import com.example.cicerone.first.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailActivity.PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }else if(id == R.id.action_map){
            OpenPreferredLocationInMap();
        }
        return super.onOptionsItemSelected(item);
    }

    private void OpenPreferredLocationInMap() {
        PackageManager manager = getApplicationContext().getPackageManager();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String lat = prefs.getString(getString(R.string.pref_latitude_key),getString(R.string.pref_latitude_default));
        String lon = prefs.getString(getString(R.string.pref_longitude_key),getString(R.string.pref_longitude_default));
        String uri = "geo:"+ lat + "," + lon + "?z=11" ;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }else{
            //No Application can handle your intent
            Toast toast = Toast.makeText(getApplicationContext(), "No map app", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
