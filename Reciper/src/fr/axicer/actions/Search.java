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
import fr.axicer.lang.EN;
import fr.axicer.lang.FR;
import fr.axicer.main.Main;
import fr.axicer.object.Recipe;
import fr.axicer.util.Configuration;
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
		recipes = new ArrayList<Recipe>();
		
		for (File f : relatedSearch) {
			recipes.add(new Recipe(f));
		}
		
		if(recipes.size() == 0){
			JFrame error = new JFrame();
			if(Configuration.getProperties().getProperty("language").equals("FR")){
				error.setTitle(FR.error);
			}else{
				error.setTitle(EN.error);
			}
			error.setBounds(Main.screenWidth/2-150, Main.screeenHeight/2-75, 300, 150);
			error.setResizable(false);
			error.setAlwaysOnTop(true);
			error.setIconImage(Images.ICON.getIcon().getImage());
			
			JLabel notfound = new JLabel();
			if(Configuration.getProperties().getProperty("language").equals("FR")){
				notfound.setText(FR.norecipefound);
			}else{
				notfound.setText(EN.norecipefound);
			}
			
			error.add(notfound);
			
			error.setVisible(true);
		}else{
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
