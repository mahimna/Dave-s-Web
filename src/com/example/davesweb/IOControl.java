package com.example.davesweb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class IOControl {

	String fileName;
	
	public IOControl (String file){
		fileName = file;
	}
	
	boolean writeToFile (List<Object> list){
		
		File file = new File (fileName);
		try {
			FileWriter fw = new FileWriter(fileName);
			Writer output = new BufferedWriter(fw);
			int size = list.size();
			for (int i = 0; i<size; i++){
				BookDataContainer cur_obj = (BookDataContainer) list.get(i);
				output.write(cur_obj.title + "\n");
				output.write(cur_obj.time_stamp + "\n");
				output.write(cur_obj.image_url + "\n");		
			}
			output.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
		
	}
	
	ArrayList<Object> readFromFile(){
		ArrayList<Object> list = new ArrayList<Object>();
		try{
			File file = new File(fileName);
			FileReader fr = new FileReader (fileName);
			BufferedReader input = new BufferedReader (fr);
			String line1, line2, line3;
			if(!input.ready()){
				throw new IOException();
			}
			while ((line1 = input.readLine())!=null){
				line2 = input.readLine();
				line3 = input.readLine();
				BookDataContainer cur_obj = new BookDataContainer(line1,line2,line3);
				list.add(cur_obj);
			}			
			fr.close();
			//file.delete();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
				
		return list;
	}
	
	boolean fileExistance (){
		File file  = new File(fileName);
		return file.exists();
	}
}
