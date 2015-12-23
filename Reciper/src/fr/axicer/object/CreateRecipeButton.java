package fr.axicer.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.axicer.GUI.CreateRecipeGUI;
import fr.axicer.lang.EN;
import fr.axicer.lang.FR;
import fr.axicer.main.Main;
import fr.axicer.util.Configuration;

public class CreateRecipeButton extends JButton implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	public CreateRecipeButton() {
		if(Configuration.getProperties().getProperty("language").equals("FR")){
			this.setText(FR.createRecipe);
		}else{
			this.setText(EN.createRecipe);
		}
		setForeground(Color.WHITE);
		setBackground(new Color(59, 89, 182));
		setFocusPainted(false);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		this.setBounds(0, 0, 145, 50);
		this.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(Main.isMaximized){
			Main.drawFrame(new CreateRecipeGUI("createRecipeGUI"), 0, 0, Main.screenWidth, Main.screeenHeight);
		}else{
			Main.drawFrame(new CreateRecipeGUI("createRecipeGUI"), Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
		}
		CreateRecipeGUI.loadGUI("", "");
		Main.gui.show();
	}
}
