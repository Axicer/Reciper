package fr.axicer.util;

import java.io.File;

public class StorageManager {
	public static File dataFolder;
	public static File recipeFolder;
	public StorageManager() {
		if(OSCheck.getOS() == OSEnum.WINDOWS){
			dataFolder = new File(System.getenv("APPDATA")+"/.reciper");
		}else if(OSCheck.getOS() == OSEnum.MAC){
			dataFolder = new File(System.getProperty("user.home") + "/Library/Application Support"+"/.reciper");
		}else if(OSCheck.getOS() == OSEnum.LINUX){
			dataFolder = new File(System.getProperty("user.home")+"/.reciper");
		}
		recipeFolder = new File(dataFolder, "recipes");
		if(!recipeFolder.exists()){
			recipeFolder.mkdirs();
		}
	}
}
