package fr.axicer.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.axicer.Reciper;
import fr.axicer.GUI.SearchRecipeGUI;
import fr.axicer.languages.LanguageManager;

public class SearchRecipeButton extends JButton implements ActionListener{

	private static final long serialVersionUID = 1L;

	public SearchRecipeButton() {
		setText(LanguageManager.getText("searchRecipe"));
		setForeground(Color.WHITE);
		setBackground(new Color(59, 89, 182));
		setFocusPainted(false);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setBounds(146, 0, 163, 50);
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(Reciper.isMaximized){
			Reciper.drawFrame(new SearchRecipeGUI("searchRecipeGUI"), 0, 0, Reciper.screenWidth, Reciper.screeenHeight);
		}else{
			Reciper.drawFrame(new SearchRecipeGUI("searchRecipeGUI"), Reciper.screenWidth/5, Reciper.screeenHeight/8, 800, 600);
		}
		SearchRecipeGUI.loadGUI();
		Reciper.gui.show();
	}
}
