package fr.axicer.languages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fr.axicer.util.Configuration;
import fr.axicer.util.StorageManager;


public class LanguageManager {
	
	public static String languageName;
	public static JSONObject lang;
	
	public LanguageManager() {
		for(BaseLanguages bl : BaseLanguages.values()){
			File file = new File(StorageManager.langFolder+"/"+bl.getFile().getName());
			if(!file.exists()){
				try {
					copyFile(bl.getFile(), file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		String configLang = Configuration.getProperties().getProperty("language");
		File langFileTest = new File(StorageManager.langFolder.getPath()+"/"+configLang+".lang");
		if(langFileTest.exists()){
			try {
				JSONParser parser = new JSONParser();
	            Object obj = parser.parse(new FileReader(langFileTest));
	 
	            lang = (JSONObject) obj;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}else{
			try {
				JSONParser parser = new JSONParser();
	            Object obj = parser.parse(new FileReader(new File(StorageManager.langFolder.getPath()+"/EN.lang")));
	 
	            lang = (JSONObject) obj;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	}
	
	public static String getText(String text){
		return (String) lang.get(text);
	}
	
	private static void copyFile(File source, File dest) throws IOException {
	    Files.copy(source.toPath(), dest.toPath());
	}

	public static List<JSONObject> getAllAvailableLanguages(){
		ArrayList<JSONObject> list = new ArrayList<>();
		for(File f : StorageManager.langFolder.listFiles()){
			if(f.getName().endsWith(".lang")){
				JSONParser parser = new JSONParser();
				try {
					Object obj = parser.parse(new FileReader(f));
					JSONObject json = (JSONObject) obj;
					list.add(json);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
