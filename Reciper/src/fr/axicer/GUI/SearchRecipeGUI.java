package fr.axicer.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.axicer.Reciper;
import fr.axicer.actions.Search;
import fr.axicer.languages.LanguageManager;

public class SearchRecipeGUI extends GUI {
	
	public static ArrayList<Component> comps = new ArrayList<>();

	public SearchRecipeGUI(String name) {
		super(name);
		loadGUI();
		loadComponents(comps);
	}
	
	public static JLabel wordlabel;
	public static JTextField word;
	public static JButton search;
	
	public static void loadGUI(){
		
		wordlabel = new JLabel(LanguageManager.getText("enterWordToSearch"));
		
		word = new JTextField();
		word.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		search = new JButton(LanguageManager.getText("search"));
		search.setForeground(Color.WHITE);
		search.setBackground(Color.GRAY);
		search.setFocusPainted(false);
		search.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		wordlabel.setBounds(1, Reciper.frame.getHeight()/9, 200, 40);
		word.setBounds(1, Reciper.frame.getHeight()/9+42, 300, 40);
		search.setBounds(Reciper.frame.getWidth()-204, Reciper.frame.getHeight()/9+42, 200, 40);
		
		search.addActionListener(new Search());
		
		Reciper.frame.add(wordlabel);
		Reciper.frame.add(word);
		Reciper.frame.add(search);
		
		comps.add(wordlabel);
		comps.add(word);
		comps.add(search);
	}
}
