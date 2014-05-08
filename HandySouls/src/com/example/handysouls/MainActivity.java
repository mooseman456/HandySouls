package com.example.handysouls;

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
import android.widget.RelativeLayout;

public class MainActivity extends ListActivity {
	
	String[] cats;
	
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
				RelativeLayout start = (RelativeLayout) findViewById(R.id.start_screen_group);
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
    		LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		
    		if (convertView == null) {
    			view = mInflater.inflate(R.layout.cat_list, parent, false);
    		}
    		else {
    			view = convertView;
    		}
    		
    		Button nameCat = (Button) view.findViewById(R.id.cat_name);
    		
    		nameCat.setText(cats[position]);
    		switch(position) {
    		case 0:
    			URL = "http://darksouls.wikidot.com/axes";
    			break;
    		case 1:
    			URL = "http://darksouls.wikidot.com/bows";
    			break;
    		case 2:
    			URL = "http://darksouls.wikidot.com/crossbows";
    			break;
    		case 3:
    			URL = "http://darksouls.wikidot.com/curved-greatswords";
    			break;
    		case 4:
    			URL = "http://darksouls.wikidot.com/curved-swords";
    			break;
    		case 5:
    			URL = "http://darksouls.wikidot.com/daggers";
    			break;
    		case 6:
    			URL = "http://darksouls.wikidot.com/fist";
    			break;
    		case 7:
    			URL = "http://darksouls.wikidot.com/greatbows";
    			break;
    		case 8:
    			URL = "http://darksouls.wikidot.com/greatswords";
    			break;
    		case 9:
    			URL = "http://darksouls.wikidot.com/greataxes";
    			break;
    		case 10:
    			URL = "http://darksouls.wikidot.com/great-hammers";
    			break;
    		case 11:
    			URL = "http://darksouls.wikidot.com/halberds";
    			break;
    		case 12:
    			URL = "http://darksouls.wikidot.com/hammers";
    			break;
    		case 13:
    			URL = "http://darksouls.wikidot.com/katanas";
    			break;
    		case 14:
    			URL = "http://darksouls.wikidot.com/spears";
    			break;
    		case 15:
    			URL = "http://darksouls.wikidot.com/straightswords";
    			break;
    		case 16:
    			URL = "http://darksouls.wikidot.com/thrusting-swords";
    			break;
    		case 17:
    			URL = "http://darksouls.wikidot.com/ultra-greatswords";
    			break;
    		case 18:
    			URL = "http://darksouls.wikidot.com/whips";
    			break;
    		default:
    			URL = "failed";
    			break;
    		}
    		categoryPicked click = new categoryPicked(URL);
    		nameCat.setOnClickListener(click);
    		return view;
    	}
    }
    
    public class categoryPicked implements OnClickListener {
    	
    	private String URL; 	
    	
    	public categoryPicked (String URL) {
    		this.URL = URL;
    	}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(getApplicationContext(), WeaponList.class);
			i.putExtra("URL", URL);
			startActivity(i);
		}
    	
    }
    
}
