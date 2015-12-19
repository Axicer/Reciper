package fr.axicer.object;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.axicer.Images;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.isMaximized = false;
		Main.drawFrame(Main.gui, Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
	}
}
