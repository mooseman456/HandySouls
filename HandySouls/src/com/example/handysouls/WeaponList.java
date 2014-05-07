package com.example.handysouls;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.parser.*;

public class WeaponList extends ListActivity {
	ArrayList<weapon> mWArray;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weapon_list);
		mWArray = new ArrayList<weapon>();
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String URL = bundle.getString("URL");
		
		
		Toast toast = Toast.makeText(getApplicationContext(), URL, Toast.LENGTH_LONG);
		toast.show();
		//setListAdapter(new weaponList(this));
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
			View rootView = inflater.inflate(R.layout.fragment_weapon_list,
					container, false);
			return rootView;
		}
	}
	
	public class weaponList extends ArrayAdapter<weapon> {
    	Context context;
    	public weaponList(Context context) {
    		super(context, R.layout.cat_list, mWArray);
    		Log.d("JSA", "weaponList constructor");
    		this.context = context;
    	}
    	
    	@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
    		View view;
    		String URL;
    		LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		Log.d("JSA", "Inside getView");
    		if (convertView == null) {
    			view = mInflater.inflate(R.layout.cat_list, parent, false);
    		}
    		else {
    			view = convertView;
    		}
    		
    		Button nameCat = (Button) view.findViewById(R.id.cat_name);
    		
    		String toastText;
    		nameCat.setText(mWArray.get(position).name);
    		return view;
    	}
	}
	
	private class GetParseWeapons extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... Url) {
			// TODO Auto-generated method stub
			try {
				Document doc = Jsoup.connect(Url[0]).get();
				Elements weapons = doc.select("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onProgressUpdate(Integer... values){
			
		}
		
		protected void onPostExecute(Bitmap result) {
			
		}
		
	}

}
