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

import fr.axicer.Reciper;
import fr.axicer.languages.LanguageManager;
import fr.axicer.object.ModifyListRenderer;
import fr.axicer.object.Recipe;
import fr.axicer.util.StorageManager;

public class SelectModificationRecipeGUI extends GUI {
	
	public SelectModificationRecipeGUI(String name) {
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
		
		select = new JLabel(LanguageManager.getText("chooseRecipe"));
		
		open = new JButton(LanguageManager.getText("modifyTheRecipe"));
		open.setForeground(Color.WHITE);
		open.setBackground(Color.GREEN);
		open.setFocusPainted(false);
		open.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		select.setBounds(1, Reciper.frame.getHeight()/4-50, 300, 50);
		open.setBounds(Reciper.frame.getWidth()-200, Reciper.frame.getHeight()/4-50, 200, 50);
		
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Recipe rec : r){
					if(rec.getTitle() == choice.getSelectedValue()){
						if(Reciper.isMaximized){
							Reciper.drawFrame(new ModifyRecipeGUI("modifyRecipeGUI"), 0, 0, Reciper.screenWidth, Reciper.screeenHeight);
							ModifyRecipeGUI.loadGUI(rec);
							Reciper.gui.show();
						}else{
							Reciper.drawFrame(new ModifyRecipeGUI("modifyRecipeGUI"), Reciper.screenWidth/5, Reciper.screeenHeight/8, 800, 600);
							ModifyRecipeGUI.loadGUI(rec);
							Reciper.gui.show();
						}
					}
				}
			}
		});
		
		Reciper.frame.add(open);
		Reciper.frame.add(select);
		
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
			
			scroll.setBounds(1, Reciper.frame.getHeight() / 4, Reciper.frame.getWidth()-2, 3*Reciper.frame.getHeight()/4-2);
			
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
								if(Reciper.isMaximized){
									Reciper.drawFrame(new ModifyRecipeGUI("modifyRecipeGUI"), 0, 0, Reciper.screenWidth, Reciper.screeenHeight);
									ModifyRecipeGUI.loadGUI(rec);
									Reciper.gui.show();
								}else{
									Reciper.drawFrame(new ModifyRecipeGUI("modifyRecipeGUI"), Reciper.screenWidth/5, Reciper.screeenHeight/8, 800, 600);
									ModifyRecipeGUI.loadGUI(rec);
									Reciper.gui.show();
								}
							}
						}
					}
				}
			});
			
			Reciper.frame.add(scroll);
			
			Reciper.frame.repaint();
		}
	}
	
}
