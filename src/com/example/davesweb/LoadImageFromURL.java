package com.example.davesweb;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class LoadImageFromURL extends AsyncTask <Void,Void,Bitmap>  {
	
	private String url;
	private ImageView image_view;
	
	public LoadImageFromURL(String url, ImageView image_view){
		this.url = url;
		this.image_view = image_view;
	}

	@Override
	protected Bitmap doInBackground(Void... params) {
		// TODO Auto-generated method stub
		try{
			URL urlConnection = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (Exception e){
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		image_view.setImageBitmap(result);
	}
	
}
