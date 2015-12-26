package fr.axicer.object;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.axicer.Reciper;
import fr.axicer.GUI.CreateRecipeGUI;
import fr.axicer.GUI.SearchResultGUI;
import fr.axicer.GUI.SelectDeletionRecipeGUI;
import fr.axicer.GUI.SelectModificationRecipeGUI;
import fr.axicer.actions.Search;
import fr.axicer.images.Images;

public class MaximizeButton extends JButton implements ActionListener,MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon button = Images.MAXIMIZE_BUTTON.getIcon();
	ImageIcon button_hover = Images.MAXIMIZE_BUTTON_HOVER.getIcon();

	public MaximizeButton() {
		this.setBounds(Reciper.frame.getWidth()-66, 0, 32, 32);
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
		Reciper.isMaximized = true;
		if(Reciper.gui instanceof SearchResultGUI){
			Reciper.drawFrame(Reciper.gui, 0, 0, Reciper.screenWidth, Reciper.screeenHeight);
			SearchResultGUI.loadGUI(Search.recipes);
			Reciper.gui.show();
		}else if(Reciper.gui instanceof CreateRecipeGUI){
			CreateRecipeGUI gui = (CreateRecipeGUI) Reciper.gui;
			String title = gui.title.getText();
			String recipe = gui.recette.getText();
			Reciper.drawFrame(Reciper.gui, 0, 0, Reciper.screenWidth, Reciper.screeenHeight);
			CreateRecipeGUI.loadGUI(title, recipe);
			Reciper.gui.show();
		}else if(Reciper.gui instanceof SelectModificationRecipeGUI){
			Reciper.drawFrame(Reciper.gui, 0, 0, Reciper.screenWidth, Reciper.screeenHeight);
			SelectModificationRecipeGUI.loadGUI();
			Reciper.gui.show();
		}else if(Reciper.gui instanceof SelectDeletionRecipeGUI){
				Reciper.drawFrame(Reciper.gui, 0, 0, Reciper.screenWidth, Reciper.screeenHeight);
				SelectDeletionRecipeGUI.loadGUI();
				Reciper.gui.show();
		}else{
			Reciper.drawFrame(Reciper.gui, 0, 0, Reciper.screenWidth, Reciper.screeenHeight);
		}
	}
}
