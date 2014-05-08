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
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WeaponList extends ListActivity {
	ArrayList<weapon> mWArray;
	RelativeLayout container;
	RelativeLayout weaponView;
	
	ImageView img;
	TextView name;
	TextView availability;
	TextView durability;
	TextView damage;
	TextView weight;
	TextView specialNote;
	TextView requirements;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weapon_list);
		
		container = (RelativeLayout)findViewById(R.id.weapon_list_container);
		weaponView = (RelativeLayout)findViewById(R.id.weapon_page);
		img = (ImageView)findViewById(R.id.weapon_image);
		name = (TextView)findViewById(R.id.weapon_name);
		availability = (TextView)findViewById(R.id.availability);
		weight = (TextView)findViewById(R.id.weight);
		damage = (TextView)findViewById(R.id.damage);
		durability = (TextView)findViewById(R.id.durability);
		specialNote = (TextView)findViewById(R.id.special);
		requirements = (TextView)findViewById(R.id.reqs);
		
		mWArray = new ArrayList<weapon>();
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String URL = bundle.getString("URL");
		new GetParseWeapons().execute(URL);
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
	
	public void back(View v) {
		container.setVisibility(View.VISIBLE);
		weaponView.setVisibility(View.GONE);
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
    		Drawable d = new BitmapDrawable(getResources(),mWArray.get(position).img);
    		ImageView v = (ImageView)view.findViewById(R.id.weapon_image);
    		v.setImageBitmap(mWArray.get(position).img);
    		
    		weaponPicked wp = new weaponPicked(mWArray.get(position));
    		v.setClickable(true);
    		view.setOnClickListener(wp);
    		//d.setBounds(10, 10, 10, 10);
    		//nameCat.setCompoundDrawables(d, null, null, null);
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
	
	private class GetParseWeapons extends AsyncTask<String, String, ArrayList<weapon>> {

		@Override
		protected ArrayList<weapon> doInBackground(String... Url) {
			// TODO Auto-generated method stub
			try {
				int numWeaps = 0;
				boolean first = true;
				boolean bad = false;
				Document doc = Jsoup.connect(Url[0]).get();
				Elements weapons = doc.select("table.wiki-content-table tbody tr");
				String imgLoc;
				String parseReqs;
				for (Element row : weapons) {
					if (!first && !bad) {
						mWArray.add(new weapon());
						
						imgLoc = row.child(0).childNode(0).attr("src");
						mWArray.get(numWeaps).imgLoc = imgLoc;
						mWArray.get(numWeaps).img = loadImageFromNetwork(imgLoc);
						mWArray.get(numWeaps).name = row.child(1).text();
						mWArray.get(numWeaps).damage = row.child(2).text();
						mWArray.get(numWeaps).durability = row.child(3).text();
						mWArray.get(numWeaps).weight = row.child(4).text();
						
						parseReqs = row.child(5).text();
						int spaceLoc = parseReqs.indexOf(" ");
						mWArray.get(numWeaps).statBonuses = parseReqs.substring(spaceLoc);
						mWArray.get(numWeaps).statsNeeded = parseReqs.substring(0, spaceLoc);
						
						mWArray.get(numWeaps).availability = row.child(6).text();
						mWArray.get(numWeaps).specialNote = row.child(7).text();
						numWeaps++;
					}
					else if(!first && bad){
						mWArray.add(new weapon());
						
						imgLoc = row.child(0).childNode(0).attr("src");
						mWArray.get(numWeaps).imgLoc = imgLoc;
						mWArray.get(numWeaps).img = loadImageFromNetwork(imgLoc);
						mWArray.get(numWeaps).name = row.child(1).text();
						mWArray.get(numWeaps).damage = row.child(2).text();
						mWArray.get(numWeaps).durability = row.child(4).text();
						mWArray.get(numWeaps).weight = row.child(5).text();mWArray.get(numWeaps).weight = row.child(4).text();
						
						parseReqs = row.child(6).text();
						int spaceLoc = parseReqs.indexOf(" ");
						mWArray.get(numWeaps).statBonuses = parseReqs.substring(spaceLoc);
						mWArray.get(numWeaps).statsNeeded = parseReqs.substring(0, spaceLoc);
						
						mWArray.get(numWeaps).availability = row.child(7).text();
						mWArray.get(numWeaps).specialNote = row.child(8).text();
						numWeaps++;
					}
					else {
						first = false;
						if (row.child(3).text().compareTo("Durability") != 0)
							bad = true;
						
					}
						
						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mWArray;
		}
		
		protected void onProgressUpdate(String... values){
		}
		
		protected void onPostExecute(ArrayList<weapon> array) {
			setListAdapter(new weaponList(getApplicationContext()));
			ProgressBar spinnyThing = (ProgressBar)findViewById(R.id.spinny_bar);
			spinnyThing.setVisibility(View.INVISIBLE);
		}
		
	}
	
	public class weaponPicked implements OnClickListener {
    	
    	private weapon w;   	
    	
    	public weaponPicked (weapon w) {
    		this.w = w;
    	}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			container.setVisibility(View.GONE);
			weaponView.setVisibility(View.VISIBLE);
			
			img.setImageBitmap(w.img);
			name.setText(w.name);
			availability.setText(w.availability);
			durability.setText(w.durability);
			weight.setText(w.weight);
			damage.setText(w.damage);
			specialNote.setText(w.specialNote);
			requirements.setText(w.statsNeeded);
		}
    	//Intent intent = new Intent(this, WeaponCategory.class);
    	
    }

}
