package fr.axicer.object;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.axicer.Images;
import fr.axicer.main.Main;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	ImageIcon icon = Images.ICON.getIcon();
	private static Point point = new Point();
	
	/**
	 * Generate the main frame
	 */
	public Frame(String title, int x, int y, int width, int height,
			boolean undecorated) {
		this.getContentPane().setBackground(Color.WHITE);
		this.setTitle(title);
		this.setBounds(x, y, width, height);
		this.setUndecorated(undecorated);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(icon.getImage());
		this.addMouseListener(new MouseAdapter() {
		      public void mousePressed(MouseEvent e) {
		    	  if(!Main.isMaximized){
		    		  point.x = e.getX();
		    		  point.y = e.getY();
		    	  }
		      }
		    });
		this.addMouseMotionListener(new MouseMotionAdapter() {
		      public void mouseDragged(MouseEvent e) {
		    	  if(!Main.isMaximized){
		    		  Point p = getLocation();
				      setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
		    	  }
		      }
		    });
	}
}
