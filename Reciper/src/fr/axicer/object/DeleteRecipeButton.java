package fr.axicer.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.axicer.GUI.SelectDeletionRecipeGUI;
import fr.axicer.lang.LanguageManager;
import fr.axicer.main.Main;

public class DeleteRecipeButton extends JButton implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	public DeleteRecipeButton() {
		setText(LanguageManager.getText("deleteRecipe"));
		setForeground(Color.WHITE);
		setBackground(new Color(59, 89, 182));
		setFocusPainted(false);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setBounds(145+163+173, 0, 175, 50);
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(Main.isMaximized){
			Main.drawFrame(new SelectDeletionRecipeGUI("selectDeletionRecipeGUI"), 0, 0, Main.screenWidth, Main.screeenHeight);
		}else{
			Main.drawFrame(new SelectDeletionRecipeGUI("selectDeletionRecipeGUI"), Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
		}
		SelectDeletionRecipeGUI.loadGUI();
		Main.gui.show();
	}
}
