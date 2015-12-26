package fr.axicer.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.axicer.Reciper;
import fr.axicer.GUI.ModifyRecipeGUI;
import fr.axicer.images.Images;
import fr.axicer.languages.LanguageManager;
import fr.axicer.object.Recipe;
import fr.axicer.util.StorageManager;

public class ModifyRecipe implements ActionListener{

	//action when click on "modify" button
	@Override
	public void actionPerformed(ActionEvent e) {
		//get some variables of the GUI
		Recipe r = ModifyRecipeGUI.recipe;
		String newTitle = ModifyRecipeGUI.title.getText();
		String newRecipe = ModifyRecipeGUI.recette.getText().replace("\n", "<br>");
		
		//check if title is empty
		if(newTitle.isEmpty()){
			//create a new frame to inform that the name can't be empty
			JFrame error = new JFrame(LanguageManager.getText("error"));
			error.setResizable(false);
			error.setBounds(Reciper.screenWidth/2-40, Reciper.screeenHeight/2-40, 250, 50);
			
			JLabel message = new JLabel(LanguageManager.getText("titleCantbeEmpty"));
			message.setForeground(Color.RED);
			
			error.add(message);
			
			error.setVisible(true);
			return;
		}
		
		//check if recipe is empty
		if(newRecipe.isEmpty()){
			//same as title
			JFrame error = new JFrame(LanguageManager.getText("error"));
			error.setResizable(false);
			error.setBounds(Reciper.screenWidth/2-40, Reciper.screeenHeight/2-40, 250, 50);
			
			JLabel message = new JLabel(LanguageManager.getText("recipeCantbeEmpty"));
			message.setForeground(Color.RED);
			
			error.add(message);
			
			error.setVisible(true);
			return;
		}
		
		//if title and recipe are not empty than get some more variable
		File recipeFolder = new File(StorageManager.recipeFolder.toString()+"/"+r.getTitle());
		File recipeFile = new File(recipeFolder+"/"+r.getTitle()+".rcp");
		File recipePropertiesFile = new File(recipeFolder+"/"+r.getTitle()+".properties");
		Properties recipeProperties = new Properties();
		
		try {
			//modify recipe
			BufferedWriter recipeBuffer = new BufferedWriter( new FileWriter( recipeFile, false));
			recipeBuffer.write("<html>"+newRecipe.replace("\n", "<br>")+"</html>");
			recipeBuffer.close();
			
			OutputStream output = null;

			try {
				//write properties about recipe
				output = new FileOutputStream(recipePropertiesFile);

				// set the properties value
				File image = new File(ModifyRecipeGUI.imageField.getText());
				if(image.exists()){
					recipeProperties.setProperty("image", ModifyRecipeGUI.imageField.getText());
				}else{
					recipeProperties.setProperty("image", "null");
				}

				// save properties to project root folder
				recipeProperties.store(output, null);

			} catch (IOException io) {
				io.printStackTrace();
			}
			
			if(Reciper.isMaximized){
				Reciper.drawFrame(null, 0, 0, Reciper.screenWidth, Reciper.screeenHeight);
			}else{
				Reciper.drawFrame(null, Reciper.screenWidth/5, Reciper.screeenHeight/8, 800, 600);
			}
			
			//inform that the recipe has been added
			JFrame create = new JFrame(LanguageManager.getText("modification"));
			JLabel message = new JLabel(LanguageManager.getText("modified"));
			create.setIconImage(Images.ICON.getIcon().getImage());
			message.setForeground(Color.GREEN);
			create.setResizable(false);
			create.setBounds(Reciper.screenWidth/2-125, Reciper.screeenHeight/2-62, 250, 125);
			create.add(message);
			create.setVisible(true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
