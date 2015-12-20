package fr.axicer.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import fr.axicer.lang.EN;
import fr.axicer.lang.FR;
import fr.axicer.main.Main;
import fr.axicer.object.ModifyListRenderer;
import fr.axicer.object.Recipe;
import fr.axicer.util.Configuration;
import fr.axicer.util.StorageManager;

public class selectRecipeGUI extends GUI {
	
	public selectRecipeGUI(String name) {
		super(name);
	}

	public static JLabel select;
	public static JList<String> choice;
	public static ScrollPane scroll;
	public static JButton open;
	public static Map<String, ImageIcon> cellules = new HashMap<String, ImageIcon>();
	
	@SuppressWarnings("deprecation")
	public static void loadGUI(){
		
		ArrayList<File> relatedSearch = new ArrayList<File>();
		for (String folders : StorageManager.recipeFolder.list()){
			if (!relatedSearch.contains(new File(StorageManager.recipeFolder + "/" + folders))) {
				relatedSearch.add(new File(StorageManager.recipeFolder+ "/" + folders));
			}
		}
		final ArrayList<Recipe> r = new ArrayList<>();
		for (File f : relatedSearch) {
			r.add(new Recipe(f));
		}
		
		select = new JLabel();
		if(Configuration.getProperties().getProperty("language").equals("FR")){
			select.setText(FR.chooseRecipe);
		}else{
			select.setText(EN.chooseRecipe);
		}
		
		open = new JButton();
		if(Configuration.getProperties().getProperty("language").equals("FR")){
			open.setText(FR.modifyTheRecipe);
		}else{
			open.setText(EN.modifyTheRecipe);
		}
		open.setForeground(Color.WHITE);
		open.setBackground(Color.GREEN);
		open.setFocusPainted(false);
		open.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		select.setBounds(1, Main.frame.getHeight()/4-50, 300, 50);
		open.setBounds(Main.frame.getWidth()-200, Main.frame.getHeight()/4-50, 200, 50);
		
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Recipe rec : r){
					if(rec.getTitle() == choice.getSelectedValue()){
						if(Main.isMaximized){
							Main.drawFrame(new ModifyRecipeGUI("modifyRecipeGUI"), 0, 0, Main.screenWidth, Main.screeenHeight);
							ModifyRecipeGUI.loadGUI(rec);
							Main.gui.show();
						}else{
							Main.drawFrame(new ModifyRecipeGUI("modifyRecipeGUI"), Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
							ModifyRecipeGUI.loadGUI(rec);
							Main.gui.show();
						}
					}
				}
			}
		});
		
		Main.frame.add(open);
		Main.frame.add(select);
		
		if(r != null){
			
			for(Recipe recipe : r){
				try {
					cellules.put(recipe.getTitle(), new ImageIcon(recipe.getImage().toURL()));
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
			}
			
			ArrayList<String> titlesArray = new ArrayList<String>();
			for(Recipe recipe : r){
				titlesArray.add(recipe.getTitle());
			}
			String[] titles = titlesArray.toArray(new String[titlesArray.size()]);
			
			choice = new JList<String>(titles);
			choice.setDragEnabled(false);
			choice.setCellRenderer(new ModifyListRenderer());
			
			scroll = new ScrollPane();
			scroll.add(choice);
			choice.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			
			scroll.setBounds(1, Main.frame.getHeight() / 4, Main.frame.getWidth()-2, 3*Main.frame.getHeight()/4-2);
			
			choice.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {}
				
				@Override
				public void mousePressed(MouseEvent e) {}
				
				@Override
				public void mouseExited(MouseEvent e) {}
				
				@Override
				public void mouseEntered(MouseEvent e) {}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2){
						for(Recipe rec : r){
							if(rec.getTitle() == choice.getSelectedValue()){
								if(Main.isMaximized){
									Main.drawFrame(new ModifyRecipeGUI("modifyRecipeGUI"), 0, 0, Main.screenWidth, Main.screeenHeight);
									ModifyRecipeGUI.loadGUI(rec);
									Main.gui.show();
								}else{
									Main.drawFrame(new ModifyRecipeGUI("modifyRecipeGUI"), Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
									ModifyRecipeGUI.loadGUI(rec);
									Main.gui.show();
								}
							}
						}
					}
				}
			});
			
			Main.frame.add(scroll);
			
			Main.frame.repaint();
		}
	}
	
}
