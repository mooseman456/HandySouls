package com.example.handysouls;

import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
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
	
	int activityID = 0xFACE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //FrameLayout start = (FrameLayout) findViewById(R.id.start_screen_group);
        Button enter = (Button) findViewById(R.id.move_on);
        cats = getResources().getStringArray(R.array.category_names);
        setListAdapter(new catListAdapter(this));
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
    		this.context = context;
    	}
    	
    	@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
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
    		
    		Button nameCat = (Button) view.findViewById(R.id.cat_name);
    		
    		String toastText;
    		nameCat.setText(cats[position]);
    		switch(position) {
    		case 0:
    			URL = "http://darksouls.wikidot.com/axes";
    			wArray = new weapon[6];
    			toastText = "axes";
    			break;
    		case 1:
    			URL = "http://darksouls.wikidot.com/bows";
    			wArray = new weapon[5];
    			toastText = "bows";
    			break;
    		case 2:
    			URL = "http://darksouls.wikidot.com/crossbows";
    			wArray = new weapon[4];
    			toastText = "crossbows";
    			break;
    		case 3:
    			URL = "http://darksouls.wikidot.com/curved-greatswords";
    			wArray = new weapon[3];
    			toastText = "curved greatswords";
    			break;
    		case 4:
    			URL = "http://darksouls.wikidot.com/curved-swords";
    			wArray = new weapon[7];
    			toastText = "curved swords";
    			break;
    		case 5:
    			URL = "http://darksouls.wikidot.com/daggers";
    			wArray = new weapon[6];
    			toastText = "daggers";
    			break;
    		case 6:
    			URL = "http://darksouls.wikidot.com/fist";
    			wArray = new weapon[5];
    			toastText = "first";
    			break;
    		case 7:
    			URL = "http://darksouls.wikidot.com/greatbows";
    			wArray = new weapon[2];
    			toastText = "greatbows";
    			break;
    		case 8:
    			URL = "http://darksouls.wikidot.com/greatswords";
    			wArray = new weapon[13];
    			toastText = "greatswords";
    			break;
    		case 9:
    			URL = "http://darksouls.wikidot.com/greataxes";
    			wArray = new weapon[5];
    			toastText = "great axes";
    			break;
    		case 10:
    			URL = "http://darksouls.wikidot.com/great-hammers";
    			wArray = new weapon[6];
    			toastText = "great hammers";
    			break;
    		case 11:
    			URL = "http://darksouls.wikidot.com/halberds";
    			wArray = new weapon[9];
    			toastText = "halberds";
    			break;
    		case 12:
    			URL = "http://darksouls.wikidot.com/hammers";
    			wArray = new weapon[9];
    			toastText = "hammers";
    			break;
    		case 13:
    			URL = "http://darksouls.wikidot.com/katanas";
    			wArray = new weapon[4];
    			toastText = "katanas";
    			break;
    		case 14:
    			URL = "http://darksouls.wikidot.com/spears";
    			wArray = new weapon[10];
    			toastText = "spears";
    			break;
    		case 15:
    			URL = "http://darksouls.wikidot.com/straightswords";
    			wArray =  new weapon[13];
    			toastText = "straight swords";
    			break;
    		case 16:
    			URL = "http://darksouls.wikidot.com/thrusting-swords";
    			wArray = new weapon[5];
    			toastText = "thrusting swords";
    			break;
    		case 17:
    			URL = "http://darksouls.wikidot.com/ultra-greatswords";
    			wArray = new weapon[5];
    			toastText = "ultra greatswords";
    			break;
    		case 18:
    			URL = "http://darksouls.wikidot.com/whips";
    			wArray = new weapon[3];
    			toastText = "whips";
    			break;
    		default:
    			URL = "failed";
    			wArray = fist;
    			toastText = "Unknown Category";
    			break;
    		}
    		categoryPicked click = new categoryPicked(URL, wArray);
    		nameCat.setOnClickListener(click);
    		return view;
    	}
    }
    
    public class categoryPicked implements OnClickListener {
    	
    	private String URL;
    	private weapon[] wArray;   	
    	
    	public categoryPicked (String URL, weapon[] wArray) {
    		this.URL = URL;
    		this.wArray = wArray;
    	}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(getApplicationContext(), WeaponList.class);
			i.putExtra("URL", URL);
			i.putExtra("WEAPON_ARRAY", wArray);
			startActivity(i);
			
		}
    	//Intent intent = new Intent(this, WeaponCategory.class);
    	
    }
    
}
