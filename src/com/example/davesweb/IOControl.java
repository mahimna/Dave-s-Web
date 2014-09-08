package com.example.davesweb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class IOControl {

	String fileName;
	Context context;
	
	public IOControl (String file, Context cntxt){
		fileName = file;
		context = cntxt;
	}
	
	boolean writeToFile (List<Object> list){
		
		try {
			FileOutputStream fou = context.openFileOutput(fileName,context.MODE_PRIVATE);
			OutputStreamWriter output = new OutputStreamWriter(fou);
			int size = list.size();
			for (int i = 0; i<size; i++){
				BookDataContainer cur_obj = (BookDataContainer) list.get(i);
				output.write(cur_obj.title + "\n");
				output.write(cur_obj.time_stamp + "\n");
				output.write(cur_obj.image_url + "\n");		
			}
			output.flush();
			output.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return false;
		
	}
	
	ArrayList<Object> readFromFile(){
		ArrayList<Object> list = new ArrayList<Object>();
		StringBuffer datax = new StringBuffer("");
		
		
		try{
			FileInputStream fIn = context.openFileInput(fileName);
			InputStreamReader isr = new InputStreamReader (fIn);
			BufferedReader input = new BufferedReader(isr);
			String line1, line2, line3;
			while ((line1 = input.readLine())!=null){
				line2 = input.readLine();
				line3 = input.readLine();
				BookDataContainer cur_obj = new BookDataContainer (line1, line2, line3);
				list.add(cur_obj);
			}
			isr.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	boolean fileExistance (){
		File file = context.getFileStreamPath(fileName);
		return file.exists();
	}
	
	boolean deleteFile (){
		File file = context.getFileStreamPath(fileName);
		return file.delete();
	}
}

