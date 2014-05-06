package com.example.handysouls;

import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	weapon[] fist;
	weapon[] hammers;
	weapon[] greatHammers;
	weapon[] axes;
	weapon[] greatAxes;
	weapon[] daggers;
	weapon[] thrustingSwords;
	weapon[] straightSwords;
	weapon[] greatswords;
	weapon[] ultraGreatswords;
	weapon[] katanas;
	weapon[] curvedSwords;
	weapon[] curvedGreatswords;
	weapon[] spears;
	weapon[] halberds;
	weapon[] bows;
	weapon[] crossbows;
	weapon[] greatbows;
	weapon[] whips;
	
	String[] cats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //FrameLayout start = (FrameLayout) findViewById(R.id.start_screen_group);
        Button enter = (Button) findViewById(R.id.move_on);
        cats = getResources().getStringArray(R.array.category_names);
        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, cats));
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
    
    public class catListAdapter extends ArrayAdapter<String> {
    	Context context;
    	public catListAdapter(Context context) {
    		super(context, R.layout.cat_list, cats);
    	}
    	
    	@Override View getView(final int position, View convertView, ViewGroup parent) {
    		View view;
    		String URL;
    		weapon[] wArray;
    		LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		
    		if (convertView == null) {
    			view = mInflater.inflate(R.layout.cat_list, parent, false);
    		}
    		else {
    			view = convertView;
    		}
    		
    		TextView nameCat = (TextView) view.findViewById(R.id.cat_name);
    		
    		String toastText;
    		nameCat.setText(cats[position]);
    		switch(position) {
    		case 0:
    			URL = "";
    			wArray = axes;
    			toastText = "axes";
    			break;
    		case 1:
    			URL = "";
    			wArray = bows;
    			toastText = "bows";
    			break;
    		case 2:
    			URL = "";
    			wArray = crossbows;
    			toastText = "crossbows";
    			break;
    		case 3:
    			URL = "";
    			wArray = curvedGreatswords;
    			toastText = "curved greatswords";
    			break;
    		case 4:
    			URL = "";
    			wArray = curvedSwords;
    			toastText = "curved swords";
    			break;
    		case 5:
    			URL = "";
    			wArray = daggers;
    			toastText = "daggers";
    			break;
    		case 6:
    			URL = "";
    			wArray = fist;
    			toastText = "first";
    			break;
    		case 7:
    			URL = "";
    			wArray = greatbows;
    			toastText = "greatbows";
    			break;
    		case 8:
    			URL = "";
    			wArray = greatswords;
    			toastText = "greatswords";
    			break;
    		case 9:
    			URL = "";
    			wArray = greatAxes;
    			toastText = "great axes";
    			break;
    		case 10:
    			URL = "";
    			wArray = greatHammers;
    			toastText = "great hammers";
    			break;
    		case 11:
    			URL = "";
    			wArray = halberds;
    			toastText = "halberds";
    			break;
    		case 12:
    			URL = "";
    			wArray = hammers;
    			toastText = "hammers";
    			break;
    		case 13:
    			URL = "";
    			wArray = katanas;
    			toastText = "katanas";
    			break;
    		case 14:
    			URL = "";
    			wArray = spears;
    			toastText = "spears";
    			break;
    		case 15:
    			URL = "";
    			wArray = straightSwords;
    			toastText = "straight swords";
    			break;
    		case 16:
    			URL = "";
    			wArray = thrustingSwords;
    			toastText = "thrusting swords";
    			break;
    		case 17:
    			URL = "";
    			wArray = ultraGreatswords;
    			toastText = "ultra greatswords";
    			break;
    		case 18:
    			URL = "";
    			wArray = whips;
    			toastText = "whips";
    			break;
    		default:
    			toastText = "Unknown Category";
    			
    			break;
    		}
    		Toast toast = Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT);
			toast.show();
    	}
    }
    
    public void categoryPicked(String URL, weapon[] wArray) {
    	//Intent intent = new Intent(this, WeaponCategory.class);
    	
    }
    
}
