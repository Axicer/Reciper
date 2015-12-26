package fr.axicer.object;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.axicer.Reciper;
import fr.axicer.images.Images;

public class CloseButton extends JButton implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	ImageIcon button = Images.CLOSE_BUTTON.getIcon();
	ImageIcon button_hover = Images.CLOSE_BUTTON_HOVER.getIcon();
	
	public CloseButton() {
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setBorderPainted(false);
		this.setIcon(button);
		this.setBounds(Reciper.frame.getWidth()-32, 0, 32, 32);
		this.addActionListener(this);
		this.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
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
	
}
