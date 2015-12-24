package fr.axicer.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.axicer.GUI.CreateRecipeGUI;
import fr.axicer.lang.LanguageManager;
import fr.axicer.main.Main;

public class CreateRecipeButton extends JButton implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	public CreateRecipeButton() {
		setText(LanguageManager.getText("createRecipe"));
		setForeground(Color.WHITE);
		setBackground(new Color(59, 89, 182));
		setFocusPainted(false);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setBounds(0, 0, 145, 50);
		addActionListener(this);
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
