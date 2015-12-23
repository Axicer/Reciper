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

import fr.axicer.Images;
import fr.axicer.GUI.ModifyRecipeGUI;
import fr.axicer.lang.EN;
import fr.axicer.lang.FR;
import fr.axicer.main.Main;
import fr.axicer.object.Recipe;
import fr.axicer.util.Configuration;
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
			JFrame error = new JFrame();
			error.setResizable(false);
			error.setBounds(Main.screenWidth/2-40, Main.screeenHeight/2-40, 250, 50);
			
			if(Configuration.getProperties().getProperty("language").equals("FR")){
				error.setTitle(FR.error);
			}else{
				error.setTitle(EN.error);
			}
			
			JLabel message = new JLabel();
			if(Configuration.getProperties().getProperty("language").equals("FR")){
				message.setText(FR.titleCantbeEmpty);
			}else{
				message.setText(EN.titleCantbeEmpty);
			}
			message.setForeground(Color.RED);
			
			error.add(message);
			
			error.setVisible(true);
			return;
		}
		
		//check if recipe is empty
		if(newRecipe.isEmpty()){
			//same as title
			JFrame error = new JFrame();
			error.setResizable(false);
			error.setBounds(Main.screenWidth/2-40, Main.screeenHeight/2-40, 250, 50);
			
			if(Configuration.getProperties().getProperty("language").equals("FR")){
				error.setTitle(FR.error);
			}else{
				error.setTitle(EN.error);
			}
			
			JLabel message = new JLabel();
			if(Configuration.getProperties().getProperty("language").equals("FR")){
				message.setText(FR.recipeCantbeEmpty);
			}else{
				message.setText(EN.recipeCantbeEmpty);
			}
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
			
			if(Main.isMaximized){
				Main.drawFrame(null, 0, 0, Main.screenWidth, Main.screeenHeight);
			}else{
				Main.drawFrame(null, Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
			}
			
			//inform that the recipe has been added
			JFrame create = new JFrame();
			JLabel message = new JLabel();
			if(Configuration.getProperties().getProperty("language").equals("FR")){
				create.setTitle(FR.modification);
				message.setText(FR.modified);
			}else{
				create.setTitle(EN.modification);
				message.setText(EN.modified);
			}
			create.setIconImage(Images.ICON.getIcon().getImage());
			message.setForeground(Color.GREEN);
			create.setResizable(false);
			create.setBounds(Main.screenWidth/2-125, Main.screeenHeight/2-62, 250, 125);
			create.add(message);
			create.setVisible(true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
