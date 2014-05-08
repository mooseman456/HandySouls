package com.example.handysouls;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
		new GetParseWeapons().execute(URL);
		
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
	
	public class weaponList extends ArrayAdapter<weapon> {
    	Context context;
    	public weaponList(Context context) {
    		super(context, R.layout.weapon_row, mWArray);
    		this.context = context;
    	}
    	
    	@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
    		View view;
    		String URL;
    		LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		Log.d("JSA", "Inside getView");
    		if (convertView == null) {
    			view = mInflater.inflate(R.layout.weapon_row, parent, false);
    		}
    		else {
    			view = convertView;
    		}
    		
    		TextView nameCat = (TextView) view.findViewById(R.id.weapon_name);
    		
    		String toastText;
    		nameCat.setText(mWArray.get(position).name);
    		
    		nameCat.setCompoundDrawables(mWArray.get(position).img, null, null, null);
    		return view;
    	}
	}
	
	public Bitmap loadImageFromNetwork(String imgUrl) {
    	Bitmap img = null;
    	URL url;
    	try {
    			url = new URL(imgUrl);
    			img = BitmapFactory.decodeStream(url.openStream());
    	} catch (MalformedURLException e){
    		Log.e("JSA", "URL is bad");
    		e.printStackTrace();
    	} catch (IOException e){
    		Log.e("JSA", "Failed to decode Bitmap");
    		e.printStackTrace();
    	}
    	return img;
    }
	
	private class GetParseWeapons extends AsyncTask<String, Integer, ArrayList<weapon>> {

		@Override
		protected ArrayList<weapon> doInBackground(String... Url) {
			// TODO Auto-generated method stub
			try {
				int numWeaps = 0;
				boolean first = true;
				Document doc = Jsoup.connect(Url[0]).get();
				Elements weapons = doc.select("table.wiki-content-table tbody tr");
				String imgLoc;
				for (Element row : weapons) {
					if (!first) {
						mWArray.add(new weapon());
						
						imgLoc = row.child(0).childNode(0).attr("src");
						mWArray.get(numWeaps).imgLoc = imgLoc;
						mWArray.get(numWeaps).img = loadImageFromNetwork(imgLoc);
						mWArray.get(numWeaps).name = row.child(1).text();
						mWArray.get(numWeaps).damage = row.child(2).text();
						mWArray.get(numWeaps).durability = row.child(3).text();
						mWArray.get(numWeaps).weight = row.child(4).text();
						mWArray.get(numWeaps).availability = row.child(6).text();
						mWArray.get(numWeaps).specialNote = row.child(7).text();
						numWeaps++;
					}
					else
						first = false;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mWArray;
		}
		
		protected void onProgressUpdate(Integer... values){
			
		}
		
		protected void onPostExecute(ArrayList<weapon> array) {
			setListAdapter(new weaponList(getApplicationContext()));
			ProgressBar spinnyThing = (ProgressBar)findViewById(R.id.spinny_bar);
			spinnyThing.setVisibility(View.INVISIBLE);
		}
		
	}

}
