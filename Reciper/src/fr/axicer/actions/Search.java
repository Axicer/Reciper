package fr.axicer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import fr.axicer.GUI.SearchRecipeGUI;
import fr.axicer.GUI.SearchResultGUI;
import fr.axicer.main.Main;
import fr.axicer.object.Recipe;
import fr.axicer.util.StorageManager;

public class Search implements ActionListener {

	public static ArrayList<Recipe> recipes;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<File> relatedSearch = new ArrayList<File>();
		String search = SearchRecipeGUI.word.getText();
		String words[] = search.split(" ");
		for (String folders : StorageManager.recipeFolder.list()) {
			for (String word : words) {
				if (folders.contains(word)) {
					if (!relatedSearch.contains(new File(StorageManager.recipeFolder + "/" + folders))) {
						relatedSearch.add(new File(StorageManager.recipeFolder+ "/" + folders));
					}
				}
			}
		}
		// TODO draw result GUI
		
		recipes = new ArrayList<Recipe>();
		
		for (File f : relatedSearch) {
			recipes.add(new Recipe(f));
		}
		if(Main.isMaximized){
			Main.drawFrame(new SearchResultGUI("searchResultGUI"), 0, 0, Main.screenWidth, Main.screeenHeight);
		}else{
			Main.drawFrame(new SearchResultGUI("searchResultGUI"), Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
		}
		SearchResultGUI.loadGUI(recipes);
		Main.gui.show();
	}
}
