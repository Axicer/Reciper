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

public class MinimizeButton extends JButton implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	
	ImageIcon button = Images.MINIMIZE_BUTTON.getIcon();
	ImageIcon button_hover = Images.MINIMIZE_BUTTON_HOVER.getIcon();

	public MinimizeButton() {
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
		Reciper.isMaximized = false;
		if(Reciper.gui instanceof SearchResultGUI){
			Reciper.drawFrame(Reciper.gui, Reciper.screenWidth/5, Reciper.screeenHeight/8, 800, 600);
			SearchResultGUI.loadGUI(Search.recipes);
			Reciper.gui.show();
		}else if(Reciper.gui instanceof CreateRecipeGUI){
			CreateRecipeGUI gui = (CreateRecipeGUI) Reciper.gui;
			String title = gui.title.getText();
			String recipe = gui.recette.getText();
			Reciper.drawFrame(Reciper.gui, Reciper.screenWidth/5, Reciper.screeenHeight/8, 800, 600);
			CreateRecipeGUI.loadGUI(title, recipe);
			Reciper.gui.show();
		}else if(Reciper.gui instanceof SelectModificationRecipeGUI){
			Reciper.drawFrame(Reciper.gui, Reciper.screenWidth/5, Reciper.screeenHeight/8, 800, 600);
			SelectModificationRecipeGUI.loadGUI();
			Reciper.gui.show();
		}else if(Reciper.gui instanceof SelectDeletionRecipeGUI){
			Reciper.drawFrame(Reciper.gui, Reciper.screenWidth/5, Reciper.screeenHeight/8, 800, 600);
			SelectDeletionRecipeGUI.loadGUI();
			Reciper.gui.show();
		}else{
			Reciper.drawFrame(Reciper.gui, Reciper.screenWidth/5, Reciper.screeenHeight/8, 800, 600);
		}
	}
}
