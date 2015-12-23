package fr.axicer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Configuration {
	
	private static File file = new File(StorageManager.dataFolder+"/configuration.properties");
	private static Properties properties = new Properties();
	
	public Configuration() {
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			OutputStream output = null;

			try {

				output = new FileOutputStream(file);

				// set the properties value
				properties.setProperty("language", "FR");
				properties.setProperty("recipesFolder", StorageManager.recipeFolder.getPath());

				// save properties to project root folder
				properties.store(output, null);

			} catch (IOException io) {
				io.printStackTrace();
			}
		}else{
			InputStream input = null;
			try{
				input = new FileInputStream(file);
				properties.load(input);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static Properties getProperties(){
		return properties;
	}
	
	public static void saveProperties(){
		OutputStream output = null;

		try {
			output = new FileOutputStream(file);
			
			properties.store(output, null);
		}catch(Exception e){}
	}
}
