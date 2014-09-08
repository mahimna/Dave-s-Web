package com.example.davesweb;

import java.util.List;

import android.content.Context;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MahimnaArrayAdapter extends ArrayAdapter<Object> {

	private final Context context;
	private final List<Object> objects;
	
	public MahimnaArrayAdapter(Context context, int resource,
			List<Object> objects) {
		super(context, resource, objects);
		this.context = context;
		this.objects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.listview_item, parent, false);
		TextView bookTile = (TextView) rowView.findViewById(R.id.book_title);
		TextView timeStamp = (TextView) rowView.findViewById(R.id.time_stamp);
		ImageView bookCover = (ImageView) rowView.findViewById(R.id.image);
		
		Object cur_obj = objects.get(position);
		BookDataContainer cur_book = (BookDataContainer) cur_obj;
		
	    // Set the timestamp in the listview 
		timeStamp.setText(cur_book.time_stamp);
		 
		// Set the book title in the listview 
		bookTile.setText(cur_book.title);
		
		// Set the image 
		LoadImageFromURL setImage = new LoadImageFromURL(cur_book.image_url,bookCover);
		setImage.execute();
		
		return rowView;
	}
	
}
