package fr.axicer.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import fr.axicer.Reciper;
import fr.axicer.actions.createRecipe;
import fr.axicer.images.Images;
import fr.axicer.languages.LanguageManager;

public class CreateRecipeGUI extends GUI{

	public static JTextField title;
	public static JTextArea recette;
	public static JButton submit;
	public static JButton cancel;
	public static JLabel titleLabel;
	public static JLabel recetteLabel;
	public static JScrollPane scrollbar;
	public static JTextField imageField;
	public static JButton chooseImageButton;
	public static JLabel chooseImageLabel;
	public static ArrayList<Component> comps = new ArrayList<>();
	
	public CreateRecipeGUI(String name) {
		super(name);
		loadGUI("", "");
		loadComponents(comps);
	}
	
	public static void loadGUI(String t, String r){
		
		title = new JTextField(t);
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		recette = new JTextArea(r);
		recette.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		submit = new JButton(LanguageManager.getText("create"));
		submit.setForeground(Color.WHITE);
		submit.setBackground(Color.GREEN);
		submit.setFocusPainted(false);
		submit.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		cancel = new JButton(LanguageManager.getText("cancel"));
		cancel.setForeground(Color.WHITE);
		cancel.setBackground(Color.RED);
		cancel.setFocusPainted(false);
		cancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		titleLabel = new JLabel(LanguageManager.getText("enterRecipeName"));
		
		recetteLabel = new JLabel(LanguageManager.getText("enterRecipe"));
		
		scrollbar = new JScrollPane(recette);
		
		imageField = new JTextField();
		imageField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		chooseImageLabel = new JLabel("Image:");
		
		chooseImageButton = new JButton();
		chooseImageButton.setFocusPainted(false);
		chooseImageButton.setIcon(Images.BROWSE.getIcon());
		chooseImageButton.setBorderPainted(false);
		chooseImageButton.setContentAreaFilled(false);
		
		titleLabel.setBounds(1, Reciper.frame.getHeight()/9, 200, 40);
		title.setBounds(1, Reciper.frame.getHeight()/9+42, 400, 40);
		recetteLabel.setBounds(1, Reciper.frame.getHeight()/9+84, 200, 40);
		scrollbar.setBounds(1, Reciper.frame.getHeight()/9+126, Reciper.frame.getWidth(), Reciper.frame.getHeight()/2);
		cancel.setBounds(1, Reciper.frame.getHeight()-42, 100, 40);
		submit.setBounds(105, Reciper.frame.getHeight()-42, 100, 40);
		imageField.setBounds(Reciper.frame.getWidth()-300, Reciper.frame.getHeight()/9+42, 200, 40);
		chooseImageButton.setBounds(Reciper.frame.getWidth()-99, Reciper.frame.getHeight()/9+42, 40, 40);
		chooseImageLabel.setBounds(Reciper.frame.getWidth()-300, Reciper.frame.getHeight()/9, 200, 40);
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Reciper.isMaximized){
					Reciper.drawFrame(null, 0, 0, Reciper.screenWidth, Reciper.screeenHeight);
				}else{
					Reciper.drawFrame(null, Reciper.screenWidth/5, Reciper.screeenHeight/8, 800, 600);
				}
			}
		});
		submit.addActionListener(new createRecipe());
		chooseImageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.addChoosableFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						return "Images (.png;.jpg;.jpeg;.bmp)";
					}
					
					@Override
					public boolean accept(File f) {
						if(f.isFile()){
							if(f.getName().toLowerCase().endsWith(".png") || f.getName().toLowerCase().endsWith(".jpg") || f.getName().toLowerCase().endsWith(".jpeg") || f.getName().toLowerCase().endsWith(".bmp")){
								return true;
							}
							return false;
						}else{
							return true;
						}
					}
				});
				chooser.setBounds(0, 0, 600, 450);
				int returnVal = chooser.showOpenDialog(chooseImageButton);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selected = chooser.getSelectedFile();
					imageField.setText(selected.toString());
				}
			}
		});
		
		Reciper.frame.add(titleLabel);
		Reciper.frame.add(title);
		Reciper.frame.add(recetteLabel);
		Reciper.frame.add(scrollbar);
		Reciper.frame.add(cancel);
		Reciper.frame.add(submit);
		Reciper.frame.add(imageField);
		Reciper.frame.add(chooseImageButton);
		Reciper.frame.add(chooseImageLabel);
		
		comps.add(titleLabel);
		comps.add(title);
		comps.add(recetteLabel);
		comps.add(scrollbar);
		comps.add(cancel);
		comps.add(submit);
		comps.add(imageField);
		comps.add(chooseImageButton);
		comps.add(chooseImageLabel);
	}
}
