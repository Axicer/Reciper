package fr.axicer.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.axicer.actions.Search;
import fr.axicer.lang.EN;
import fr.axicer.lang.FR;
import fr.axicer.main.Main;
import fr.axicer.util.Configuration;

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
		
		wordlabel = new JLabel();
		if(Configuration.getProperties().getProperty("language").equals("FR")){
			wordlabel.setText(FR.enterWordToSearch);
		}else{
			wordlabel.setText(EN.enterWordToSearch);
		}
		
		word = new JTextField();
		word.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		search = new JButton();
		if(Configuration.getProperties().getProperty("language").equals("FR")){
			search.setText(FR.search);
		}else{
			search.setText(EN.search);
		}
		search.setForeground(Color.WHITE);
		search.setBackground(Color.GRAY);
		search.setFocusPainted(false);
		search.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		wordlabel.setBounds(1, Main.frame.getHeight()/9, 200, 40);
		word.setBounds(1, Main.frame.getHeight()/9+42, 300, 40);
		search.setBounds(Main.frame.getWidth()-204, Main.frame.getHeight()/9+42, 200, 40);
		
		search.addActionListener(new Search());
		
		Main.frame.add(wordlabel);
		Main.frame.add(word);
		Main.frame.add(search);
		
		comps.add(wordlabel);
		comps.add(word);
		comps.add(search);
	}
}
