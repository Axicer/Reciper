package fr.axicer;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.axicer.GUI.GUI;
import fr.axicer.images.Images;
import fr.axicer.languages.LanguageManager;
import fr.axicer.object.CloseButton;
import fr.axicer.object.CreateRecipeButton;
import fr.axicer.object.DeleteRecipeButton;
import fr.axicer.object.Frame;
import fr.axicer.object.MaximizeButton;
import fr.axicer.object.MinimizeButton;
import fr.axicer.object.ModifyRecipeButton;
import fr.axicer.object.ReduceButton;
import fr.axicer.object.SearchRecipeButton;
import fr.axicer.object.SettingsButton;
import fr.axicer.util.Configuration;
import fr.axicer.util.OSCheck;
import fr.axicer.util.OSEnum;
import fr.axicer.util.StorageManager;
import fr.axicer.util.TitleManager;

public class Reciper {
	
	public static GraphicsDevice gd;
	public static int screenWidth;
	public static int screeenHeight;
	public static final String AppName = "Reciper";
	public static final double AppVersion = 1.3;
	public static boolean isMaximized = false;
	public static Frame frame;
	public static Frame lastFrame;
	public static GUI gui;
	public static GUI lastgui;
	
	public static void main(String[] args) {
		JFrame starting = new JFrame("starting");
		starting.setUndecorated(true);
		starting.setLayout(null);
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screeenHeight = gd.getDisplayMode().getHeight();
		starting.setBounds(screenWidth/5, screeenHeight/8, 800, 600);
		JLabel img = new JLabel(Images.STARTING.getIcon());
		img.setBounds(0, 0, 800, 600);
		starting.add(img);
		starting.setAlwaysOnTop(true);
		starting.setVisible(true);
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(OSCheck.getOS() == OSEnum.SOLARIS || OSCheck.getOS() == OSEnum.OTHER){
			JFrame error = new JFrame("ERREUR");
			error.setSize(800, 600);
			JLabel label = new JLabel(LanguageManager.getText("systemNotSupported"));
			error.setLayout(null);
			error.setLocationRelativeTo(null);
			error.add(label);
			error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else{
			starting.setVisible(false);
			new StorageManager();
			new Configuration();
			new LanguageManager();
			drawFrame(null, screenWidth/5, screeenHeight/8, 800, 600);
		}
	}
	
	public static void drawFrame(GUI gui, int x, int y, int width, int height){
		lastFrame = frame;
		frame = new Frame(TitleManager.getTitle(),x, y, width, height, true);
		frame.setVisible(true);
		if(lastFrame != null){
			lastFrame.setVisible(false);
		}
		
		frame.setContentPane(new JLabel(new ImageIcon(Images.CHOOSE.getIcon().getImage().getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH))));
		
		frame.add(new CloseButton());
		if(isMaximized){
			frame.add(new MinimizeButton());
		}else{
			frame.add(new MaximizeButton());
		}
		frame.add(new SettingsButton());
		frame.add(new ReduceButton());
		frame.add(new CreateRecipeButton());
		frame.add(new SearchRecipeButton());
		frame.add(new ModifyRecipeButton());
		frame.add(new DeleteRecipeButton());
		
		if(Reciper.gui != null)Reciper.gui.hide();
		if(gui != null){
			Reciper.lastgui = Reciper.gui;
			Reciper.gui = gui;
			gui.show();
		}
		
		frame.repaint();
		frame.setVisible(true);
	}
}
