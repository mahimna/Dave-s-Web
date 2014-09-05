package com.example.davesweb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.ListView;

public class ListViewControl extends Activity {

	List<Object> list = null;
	String storageFile = "Storage.txt";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listview_main);	
		
		Intent intent = getIntent();		
		String author = "";
		if(intent.hasExtra("author_name")){
			author = intent.getStringExtra("author_name");
			if(author.contains(" ")) author = author.replaceAll(" ", "%20");
		}
		
		GetInformationFromWeb task = new GetInformationFromWeb();
		task.execute(author);			
	}
	
	
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		IOControl io = new IOControl(storageFile);
		if (io.fileExistance()){
		list = io.readFromFile();
		}		
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IOControl io = new IOControl(storageFile);
		if (io.fileExistance()){
			list = io.readFromFile();
		}
	}

	


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		File file = new File(storageFile);
		file.delete();
	}

	class GetInformationFromWeb extends AsyncTask <String,Void,List<Object>> {
		
		String url_HarperCollins = "http://api.harpercollins.com/api/v3/hcapim?apiname=catalog&format=JSON&authorname=";
		String author_name;
		String url_api = "&apikey=p9k29um2t95hgdzb943njgxy";
		private ProgressDialog pDialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(ListViewControl.this);
			pDialog.setTitle("Getting Information");
			pDialog.setMessage("Information about the books is being loaded from the web");
			
			if(!this.pDialog.isShowing()){
	            this.pDialog.show();
	        }
		}
			
		@Override
		protected List<Object> doInBackground(String... params) {
			// TODO Auto-generated method stub
			String author = params[0];
			if (author!="") author_name = author;
			else author_name = "Stephen%20King";
			String final_url = url_HarperCollins + author_name + url_api;
			List<Object> list = new ArrayList<Object>();
			
			JSONParser jParser = new JSONParser ();
			JSONObject json = jParser.makeHttpRequest(final_url, "GET");
			
			try {
				JSONArray allProducts = json.getJSONArray("Product_Group");
				
				for (int i = 0; (i<allProducts.length()&&i<10);i++){
					JSONObject cur_obj = (JSONObject) allProducts.get(i);
					String title = cur_obj.getString("Product_Group_Title");
					JSONObject prods = (JSONObject)(cur_obj.getJSONArray("Products").get(0));
					String image_url = prods.getString("CoverImageURL_Medium");
					Time stamp = new Time(Time.getCurrentTimezone());
					stamp.setToNow();
					String time_stamp = stamp.monthDay + "/" + stamp.month + "/" + stamp.year + " " + stamp.format("%k:%M:%S");
					BookDataContainer new_book = new BookDataContainer(title,time_stamp,image_url);
					list.add(new_book);
				}
				
				return list;
				
			} catch (JSONException e){
				e.printStackTrace();
			}		
			
			return null;
		}

		@Override
		protected void onPostExecute(List<Object> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
			final ListView listview = (ListView) findViewById(R.id.listView);
			list = result;
			IOControl io = new IOControl(storageFile);
			io.writeToFile(list);
			final MahimnaArrayAdapter adapter = new MahimnaArrayAdapter(ListViewControl.this, R.layout.listview_item, list);
			listview.setAdapter(adapter);
		}
		
		
		
	}	
	
	
		
}





