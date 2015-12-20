package fr.axicer.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.axicer.Images;
import fr.axicer.GUI.CreateRecipeGUI;
import fr.axicer.GUI.SearchResultGUI;
import fr.axicer.GUI.selectRecipeGUI;
import fr.axicer.actions.Search;
import fr.axicer.lang.EN;
import fr.axicer.lang.FR;
import fr.axicer.main.Main;
import fr.axicer.util.Configuration;
import fr.axicer.util.StorageManager;

public class SettingsButton extends JButton implements ActionListener,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon button = Images.SETTINGS.getIcon();
	private ImageIcon button_hover = Images.SETTINGS_HOVER.getIcon();

	public SettingsButton() {
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setBorderPainted(false);
		this.setIcon(button);
		this.setBounds(Main.frame.getWidth()-134, 0, 32, 32);
		this.addActionListener(this);
		this.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final JFrame frame = new JFrame();
		if(Configuration.getProperties().getProperty("language").equals("FR")){
			frame.setTitle(FR.settings);
		}else{
			frame.setTitle(EN.settings);
		}
		frame.setIconImage(Images.ICON.getIcon().getImage());
		frame.setBounds(Main.screenWidth/4, Main.screeenHeight/6, 600, 450);
		frame.setLayout(null);
		frame.setResizable(false);
		
		JLabel chooselang = new JLabel();
		if(Configuration.getProperties().getProperty("language").equals("FR")){
			chooselang.setText(FR.chooseLanguage);
		}else{
			chooselang.setText(EN.chooseLanguage);
		}
		chooselang.setBounds(0, 0, 200, 30);
		
		final JComboBox<String> lang = new JComboBox<String>(new String[]{"Francais","English"});
		if(Configuration.getProperties().getProperty("language").equals("FR")){
			lang.setSelectedIndex(0);
		}else{
			lang.setSelectedIndex(1);
		}
		lang.setBounds(0, 31, 150, 30);
		
		final JTextField recipeFolder = new JTextField(StorageManager.recipeFolder.toString());
		recipeFolder.setBounds(0, 100, 400, 30);
		
		final JLabel chooseFolder = new JLabel();
		if(Configuration.getProperties().getProperty("language").equals("FR")){
			chooseFolder.setText(FR.recipeFolder);
		}else{
			chooseFolder.setText(EN.recipeFolder);
		}
		chooseFolder.setBounds(0, 70, 200, 30);
		
		JButton choose = new JButton();
		choose.setContentAreaFilled(false);
		choose.setBorderPainted(false);
		choose.setIcon(Images.BROWSE.getIcon());
		choose.setBounds(410, 100, 30, 30);
		choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(recipeFolder.getText());
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setBounds(0, 0, 600, 450);
				int returnVal = chooser.showOpenDialog(chooseFolder);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selected = chooser.getSelectedFile();
					recipeFolder.setText(selected.toString());
				}
			}
		});
		
		JButton apply = new JButton();
		if(Configuration.getProperties().getProperty("language").equals("FR")){
			apply.setText(FR.apply);
		}else{
			apply.setText(EN.apply);
		}
		apply.setBounds(frame.getWidth()-200, frame.getHeight()-80, 200, 50);
		apply.setForeground(Color.WHITE);
		apply.setBackground(Color.GREEN);
		apply.setFocusPainted(false);
		apply.setFont(new Font("Tahoma", Font.BOLD, 12));
		apply.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(lang.getSelectedItem() == "Francais"){
					Configuration.getProperties().setProperty("language", "FR");
				}else{
					Configuration.getProperties().setProperty("language", "EN");
				}
				Configuration.getProperties().setProperty("recipesFolder", recipeFolder.getText());
				Configuration.saveProperties();
				StorageManager.recipeFolder = new File(recipeFolder.getText());
				frame.setVisible(false);
				if(Main.isMaximized){
					if(Main.gui instanceof SearchResultGUI){
						Main.drawFrame(Main.gui, 0, 0, Main.screenWidth, Main.screeenHeight);
						SearchResultGUI.loadGUI(Search.recipes);
						Main.gui.show();
					}else if(Main.gui instanceof CreateRecipeGUI){
						CreateRecipeGUI gui = (CreateRecipeGUI) Main.gui;
						String title = gui.title.getText();
						String recipe = gui.recette.getText();
						Main.drawFrame(Main.gui, 0, 0, Main.screenWidth, Main.screeenHeight);
						CreateRecipeGUI.loadGUI(title, recipe);
						Main.gui.show();
					}else if(Main.gui instanceof selectRecipeGUI){
						Main.drawFrame(Main.gui, 0, 0, Main.screenWidth, Main.screeenHeight);
						selectRecipeGUI.loadGUI();
						Main.gui.show();
					}else{
						Main.drawFrame(Main.gui, 0, 0, Main.screenWidth, Main.screeenHeight);
					}
				}else{
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
		});
		
		JButton cancel = new JButton();
		if(Configuration.getProperties().getProperty("language").equals("FR")){
			cancel.setText(FR.cancel);
		}else{
			cancel.setText(EN.cancel);
		}
		cancel.setBounds(0, frame.getHeight()-80, 200, 50);
		cancel.setForeground(Color.WHITE);
		cancel.setBackground(Color.RED);
		cancel.setFocusPainted(false);
		cancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		frame.add(chooselang);
		frame.add(lang);
		frame.add(apply);
		frame.add(cancel);
		frame.add(recipeFolder);
		frame.add(chooseFolder);
		frame.add(choose);
		
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		setIcon(button_hover);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setIcon(button);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
