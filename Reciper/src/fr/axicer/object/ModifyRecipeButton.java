package fr.axicer.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.axicer.GUI.SelectModificationRecipeGUI;
import fr.axicer.lang.LanguageManager;
import fr.axicer.main.Main;

public class ModifyRecipeButton extends JButton implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModifyRecipeButton() {
		setText(LanguageManager.getText("modifyRecipe"));
		setForeground(Color.WHITE);
		setBackground(new Color(59, 89, 182));
		setFocusPainted(false);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setBounds(145+163+2, 0, 170, 50);
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(Main.isMaximized){
			Main.drawFrame(new SelectModificationRecipeGUI("selectRecipeGUI"), 0, 0, Main.screenWidth, Main.screeenHeight);
		}else{
			Main.drawFrame(new SelectModificationRecipeGUI("selectRecipeGUI"), Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
		}
		SelectModificationRecipeGUI.loadGUI();
		Main.gui.show();
	}

}
