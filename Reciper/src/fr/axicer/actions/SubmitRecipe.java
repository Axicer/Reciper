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

import fr.axicer.GUI.CreateRecipeGUI;
import fr.axicer.lang.EN;
import fr.axicer.lang.FR;
import fr.axicer.main.Main;
import fr.axicer.util.Configuration;
import fr.axicer.util.StorageManager;

public class SubmitRecipe implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String title = CreateRecipeGUI.title.getText();
		String recipe = CreateRecipeGUI.recette.getText();
		File recipeFolder = new File(StorageManager.recipeFolder.toString()+"/"+title);
		File recipeFile = new File(recipeFolder+"/"+title+".rcp");
		File recipePropertiesFile = new File(recipeFolder+"/"+title+".properties");
		Properties recipeProperties = new Properties();
		
		if(title.isEmpty()){
			JFrame error = new JFrame();
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
			error.setResizable(false);
			error.setBounds(Main.screenWidth/2-40, Main.screeenHeight/2-40, 250, 50);
			error.add(message);
			error.setVisible(true);
			return;
		}
		
		if(recipe.isEmpty()){
			JFrame error = new JFrame();
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
			error.setResizable(false);
			error.setBounds(Main.screenWidth/2-40, Main.screeenHeight/2-40, 250, 50);
			error.add(message);
			error.setVisible(true);
			return;
		}
		
		if(!recipeFolder.exists())recipeFolder.mkdir();
		
		if(recipeFile.exists() || recipePropertiesFile.exists()){
			JFrame error = new JFrame();
			if(Configuration.getProperties().getProperty("language").equals("FR")){
				error.setTitle(FR.error);
			}else{
				error.setTitle(EN.error);
			}
			JLabel message = new JLabel();
			if(Configuration.getProperties().getProperty("language").equals("FR")){
				message.setText(FR.recipeAlreadyAdded);
			}else{
				message.setText(EN.recipeAlreadyAdded);
			}
			message.setForeground(Color.RED);
			error.setResizable(false);
			error.setBounds(Main.screenWidth/2-40, Main.screeenHeight/2-40, 250, 50);
			error.add(message);
			error.setVisible(true);
			return;
		}
		
		try {
			//Write recipe
			BufferedWriter recipeBuffer = new BufferedWriter( new FileWriter( recipeFile));
			recipeBuffer.write(recipe);
			recipeBuffer.close();
			
			//write properties about recipe
			try {
				recipePropertiesFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			OutputStream output = null;

			try {

				output = new FileOutputStream(recipePropertiesFile);

				// set the properties value
				File image = new File(CreateRecipeGUI.imageField.getText());
				if(image.exists()){
					recipeProperties.setProperty("image", CreateRecipeGUI.imageField.getText());
				}else{
					recipeProperties.setProperty("image", "null");
				}

				// save properties to project root folder
				recipeProperties.store(output, null);

			} catch (IOException io) {
				io.printStackTrace();
			}
			
			// clear GUI	
			CreateRecipeGUI.title.setText("");
			CreateRecipeGUI.recette.setText("");
				
			//inform that the recipe has been added
			JFrame create = new JFrame("Recette crée !");
			JLabel message = new JLabel("Recette ajoutée !");
			message.setForeground(Color.GREEN);
			create.setResizable(false);
			create.setBounds(Main.screenWidth/2-40, Main.screeenHeight/2-40, 150, 50);
			create.add(message);
			create.setVisible(true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
