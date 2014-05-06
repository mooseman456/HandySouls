package com.example.handysouls;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	weapon[] fist;
	weapon[] hammers;
	weapon[] greatHammers;
	weapon[] axes;
	weapon[] greataxes;
	weapon[] daggers;
	weapon[] thrustingSwords;
	weapon[] straightSwords;
	weapon[] greatSwords;
	weapon[] ultraGreatSwords;
	weapon[] katanas;
	weapon[] curvedSwords;
	weapon[] curvedGreatSwords;
	weapon[] spears;
	weapon[] halberds;
	weapon[] bows;
	weapon[] crossbows;
	weapon[] greatbows;
	weapon[] whips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //FrameLayout start = (FrameLayout) findViewById(R.id.start_screen_group);
        Button enter = (Button) findViewById(R.id.move_on);
        enter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FrameLayout start = (FrameLayout) findViewById(R.id.start_screen_group);
				start.setVisibility(View.GONE);
				RelativeLayout next = (RelativeLayout) findViewById(R.id.weapon_list_group);
				next.setVisibility(View.VISIBLE);
			}
        	
        });
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
        if (id == R.id.developer_name) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    
    public void categoryPicked(String URL, weapon[] wArray) {
    	//Intent intent = new Intent(this, WeaponCategory.class);
    	
    }
    
}
