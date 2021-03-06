package fr.axicer.object;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Recipe {

	private String title;
	private String recipe;
	private File image;
	private File folder;

	public Recipe(File file) {
		setFolder(file);
		String folderName = file.getName();
		File recipe = new File(file.getPath()+"/"+folderName+".rcp");
		File properties = new File(file.getPath()+"/"+folderName+".properties");
		
		if(!recipe.exists() || !properties.exists())return;
		
		setTitle(recipe.getName().replace(".rcp", ""));
		
		String rcp = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(recipe));
			StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    
		    while (line != null) {
		        sb.append(line);
		        line = br.readLine();
		    }
		    rcp = sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br != null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		setRecipe(rcp);
		
		try {
			Properties property = new Properties();
			FileInputStream input = new FileInputStream(properties);
			property.load(input);
			if(property.getProperty("image") != "null" && property.getProperty("image") != ""){
				image = new File(property.getProperty("image"));
			}
			FileOutputStream output = new FileOutputStream(properties);
			property.store(output, null);
			
			input.close();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public File getFolder() {
		return folder;
	}

	public void setFolder(File folder) {
		this.folder = folder;
	}
}
