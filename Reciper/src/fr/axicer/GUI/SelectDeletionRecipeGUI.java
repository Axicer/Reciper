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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import fr.axicer.Images;
import fr.axicer.lang.LanguageManager;
import fr.axicer.main.Main;
import fr.axicer.object.DeletionListRenderer;
import fr.axicer.object.Recipe;
import fr.axicer.util.StorageManager;

public class SelectDeletionRecipeGUI extends GUI {

	public SelectDeletionRecipeGUI(String name) {
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
		
		open = new JButton(LanguageManager.getText("delete"));
		open.setForeground(Color.WHITE);
		open.setBackground(Color.GREEN);
		open.setFocusPainted(false);
		open.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		select.setBounds(1, Main.frame.getHeight()/4-50, 300, 50);
		open.setBounds(Main.frame.getWidth()-200, Main.frame.getHeight()/4-50, 200, 50);
		
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(final Recipe rec : r){
					if(rec.getTitle() == choice.getSelectedValue()){
						final JFrame frame = new JFrame(LanguageManager.getText("confirmation"));
						frame.setLayout(null);
						frame.setBounds(Main.screenWidth/2-150, Main.screeenHeight/2-75, 300, 150);
						frame.setResizable(false);
						frame.setIconImage(Images.ICON.getIcon().getImage());
						frame.setAlwaysOnTop(true);
						
						JLabel confirm = new JLabel(LanguageManager.getText("areyousure"));
						confirm.setBounds(0, -60, 300, 150);
						
						JButton continuer = new JButton(LanguageManager.getText("continuer"));
						continuer.setBounds(frame.getWidth()-100, frame.getHeight()-70, 100, 40);
						continuer.setForeground(Color.WHITE);
						continuer.setBackground(Color.GREEN);
						continuer.setFocusPainted(false);
						continuer.setFont(new Font("Tahoma", Font.BOLD, 12));
						continuer.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								frame.setVisible(false);
								deleteRecipe(rec);
							}
						});
						
						JButton cancel = new JButton(LanguageManager.getText("cancel"));
						cancel.setBounds(0, frame.getHeight()-70, 100, 40);
						cancel.setForeground(Color.WHITE);
						cancel.setBackground(Color.RED);
						cancel.setFocusPainted(false);
						cancel.setFont(new Font("Tahoma", Font.BOLD, 12));
						cancel.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								frame.setVisible(false);
							}
						});
						
						frame.add(continuer);
						frame.add(cancel);
						frame.add(confirm);
						
						frame.setVisible(true);
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
			choice.setCellRenderer(new DeletionListRenderer());
			
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
						for(final Recipe rec : r){
							if(rec.getTitle() == choice.getSelectedValue()){
								final JFrame frame = new JFrame(LanguageManager.getText("confirmation"));
								frame.setLayout(null);
								frame.setBounds(Main.screenWidth/2-150, Main.screeenHeight/2-75, 300, 150);
								frame.setResizable(false);
								frame.setIconImage(Images.ICON.getIcon().getImage());
								frame.setAlwaysOnTop(true);
								
								JLabel confirm = new JLabel(LanguageManager.getText("areyousure"));
								confirm.setBounds(0, -60, 300, 150);
								
								JButton continuer = new JButton(LanguageManager.getText("continuer"));
								continuer.setBounds(frame.getWidth()-100, frame.getHeight()-70, 100, 40);
								continuer.setForeground(Color.WHITE);
								continuer.setBackground(Color.GREEN);
								continuer.setFocusPainted(false);
								continuer.setFont(new Font("Tahoma", Font.BOLD, 12));
								continuer.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent arg0) {
										frame.setVisible(false);
										deleteRecipe(rec);
									}
								});
								
								JButton cancel = new JButton(LanguageManager.getText("cancel"));
								cancel.setBounds(0, frame.getHeight()-70, 100, 40);
								cancel.setForeground(Color.WHITE);
								cancel.setBackground(Color.RED);
								cancel.setFocusPainted(false);
								cancel.setFont(new Font("Tahoma", Font.BOLD, 12));
								cancel.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent arg0) {
										frame.setVisible(false);
									}
								});
								
								frame.add(continuer);
								frame.add(cancel);
								frame.add(confirm);
								
								frame.setVisible(true);
							}
						}
					}
				}
			});
			
			Main.frame.add(scroll);
			
			Main.frame.repaint();
		}
	}
	public static void deleteRecipe(Recipe r){
		for(File f : r.getFolder().listFiles()){
			f.delete();
		}
		r.getFolder().delete();
		
		if(Main.isMaximized){
			Main.drawFrame(new SelectDeletionRecipeGUI("selectDeletionRecipeGUI"), 0, 0, Main.screenWidth, Main.screeenHeight);
		}else{
			Main.drawFrame(new SelectDeletionRecipeGUI("selectDeletionRecipeGUI"), Main.screenWidth/5, Main.screeenHeight/8, 800, 600);
		}
		SelectDeletionRecipeGUI.loadGUI();
		Main.gui.show();
		
		JFrame frame = new JFrame(LanguageManager.getText("deleted"));
		frame.setBounds(Main.screenWidth/2-150, Main.screeenHeight/2-75, 300, 150);
		frame.setIconImage(Images.ICON.getIcon().getImage());
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		
		JLabel label = new JLabel(LanguageManager.getText("recipeDeleted"));
		
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
	}
}
