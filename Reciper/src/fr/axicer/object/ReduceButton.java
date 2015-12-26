package fr.axicer.object;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.axicer.Reciper;
import fr.axicer.images.Images;

public class ReduceButton extends JButton implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	ImageIcon button = Images.REDUCE_BUTTON.getIcon();
	ImageIcon button_hover = Images.REDUCE_BUTTON_HOVER.getIcon();
	
	public ReduceButton() {
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setBounds(Reciper.frame.getWidth()-64-32-4, 0, 32, 32);
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
		Reciper.frame.setState(Frame.ICONIFIED);
	}
}
