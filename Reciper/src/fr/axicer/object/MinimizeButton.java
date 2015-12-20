package fr.axicer.object;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.axicer.Images;
import fr.axicer.GUI.CreateRecipeGUI;
import fr.axicer.GUI.SearchResultGUI;
import fr.axicer.GUI.selectRecipeGUI;
import fr.axicer.actions.Search;
import fr.axicer.main.Main;

public class MinimizeButton extends JButton implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	
	ImageIcon button = Images.MINIMIZE_BUTTON.getIcon();
	ImageIcon button_hover = Images.MINIMIZE_BUTTON_HOVER.getIcon();

	public MinimizeButton() {
		this.setBounds(Main.frame.getWidth()-66, 0, 32, 32);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setIcon(button);
		this.addActionListener(this);
		this.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setIcon(button_hover);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setIcon(button);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		Main.isMaximized = false;
		if(Main.gui instanceof SearchResultGUI){
			Main.drawFrame(Main.gui, Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
			SearchResultGUI.loadGUI(Search.recipes);
			Main.gui.show();
		}else if(Main.gui instanceof CreateRecipeGUI){
			CreateRecipeGUI gui = (CreateRecipeGUI) Main.gui;
			String title = gui.title.getText();
			String recipe = gui.recette.getText();
			Main.drawFrame(Main.gui, Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
			CreateRecipeGUI.loadGUI(title, recipe);
			Main.gui.show();
		}else if(Main.gui instanceof selectRecipeGUI){
			Main.drawFrame(Main.gui, Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
			selectRecipeGUI.loadGUI();
			Main.gui.show();
		}else{
			Main.drawFrame(Main.gui, Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
		}
	}
}
