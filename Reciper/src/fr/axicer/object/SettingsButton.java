package fr.axicer.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import fr.axicer.Reciper;
import fr.axicer.GUI.CreateRecipeGUI;
import fr.axicer.GUI.SearchResultGUI;
import fr.axicer.GUI.SelectDeletionRecipeGUI;
import fr.axicer.GUI.SelectModificationRecipeGUI;
import fr.axicer.actions.Search;
import fr.axicer.images.Images;
import fr.axicer.languages.LanguageManager;
import fr.axicer.util.Configuration;
import fr.axicer.util.StorageManager;

public class SettingsButton extends JButton implements ActionListener,MouseListener{
	
	private static final long serialVersionUID = 1L;
	private ImageIcon button = Images.SETTINGS.getIcon();
	private ImageIcon button_hover = Images.SETTINGS_HOVER.getIcon();

	public SettingsButton() {
		setContentAreaFilled(false);
		setOpaque(false);
		setBorderPainted(false);
		setIcon(button);
		setBounds(Reciper.frame.getWidth()-134, 0, 32, 32);
		addActionListener(this);
		addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final JFrame frame = new JFrame(LanguageManager.getText("settings"));
		frame.setIconImage(Images.ICON.getIcon().getImage());
		frame.setBounds(Reciper.screenWidth/4, Reciper.screeenHeight/6, 600, 450);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		
		JLabel chooselang = new JLabel(LanguageManager.getText("chooseLanguage"));
		chooselang.setBounds(0, 0, 200, 30);
		
		ArrayList<String> combo = new ArrayList<>();
		for(JSONObject json : LanguageManager.getAllAvailableLanguages()){
			combo.add((String) json.get("LanguageName"));
		}
		String[] list = new String[combo.size()];
		list = combo.toArray(list);
		final JComboBox<String> lang = new JComboBox<String>(list);
		lang.setBounds(0, 31, 150, 30);
		
		final JTextField recipeFolder = new JTextField(StorageManager.recipeFolder.toString());
		recipeFolder.setBounds(0, 100, 400, 30);
		
		final JLabel chooseFolder = new JLabel(LanguageManager.getText("recipeFolder"));
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
		
		JButton apply = new JButton(LanguageManager.getText("apply"));
		apply.setBounds(frame.getWidth()-200, frame.getHeight()-80, 200, 50);
		apply.setForeground(Color.WHITE);
		apply.setBackground(Color.GREEN);
		apply.setFocusPainted(false);
		apply.setFont(new Font("Tahoma", Font.BOLD, 12));
		apply.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				for(JSONObject json : LanguageManager.getAllAvailableLanguages()){
					if(json.get("LanguageName").equals(lang.getSelectedItem())){
						Configuration.getProperties().setProperty("language", (String) json.get("Abrevation"));
					}
				}
				new LanguageManager();
				Configuration.getProperties().setProperty("recipesFolder", recipeFolder.getText());
				Configuration.saveProperties();
				StorageManager.recipeFolder = new File(recipeFolder.getText());
				frame.setVisible(false);
				if(Reciper.isMaximized){
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
				}else{
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
		});
		
		JButton cancel = new JButton(LanguageManager.getText("cancel"));
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
