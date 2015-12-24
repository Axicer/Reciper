package fr.axicer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.axicer.Images;
import fr.axicer.GUI.SearchRecipeGUI;
import fr.axicer.GUI.SearchResultGUI;
import fr.axicer.lang.LanguageManager;
import fr.axicer.main.Main;
import fr.axicer.object.Recipe;
import fr.axicer.util.StorageManager;

public class Search implements ActionListener {

	public static ArrayList<Recipe> recipes;
	
	//action when "search button" is pressed
	@Override
	public void actionPerformed(ActionEvent e) {
		//get all related search
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
		//put them as recipe in a list
		recipes = new ArrayList<Recipe>();
		for (File f : relatedSearch) {
			recipes.add(new Recipe(f));
		}
		
		//if there is no recipe in the list
		if(recipes.size() == 0){
			//create a new frame to inform that there is an error
			JFrame error = new JFrame(LanguageManager.getText("error"));
			error.setBounds(Main.screenWidth/2-150, Main.screeenHeight/2-75, 300, 150);
			error.setResizable(false);
			error.setAlwaysOnTop(true);
			error.setIconImage(Images.ICON.getIcon().getImage());
			
			JLabel notfound = new JLabel(LanguageManager.getText("norecipefound"));
			
			error.add(notfound);
			
			error.setVisible(true);
		}else{
			//else draw frame with result
			if(Main.isMaximized){
				Main.drawFrame(new SearchResultGUI("searchResultGUI"), 0, 0, Main.screenWidth, Main.screeenHeight);
			}else{
				Main.drawFrame(new SearchResultGUI("searchResultGUI"), Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
			}
			SearchResultGUI.loadGUI(recipes);
			Main.gui.show();
		}
	}
}
