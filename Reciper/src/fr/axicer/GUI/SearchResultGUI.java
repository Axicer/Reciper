package fr.axicer.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.axicer.Reciper;
import fr.axicer.actions.Search;
import fr.axicer.languages.LanguageManager;
import fr.axicer.object.Recipe;
import fr.axicer.object.SearchListRenderer;

public class SearchResultGUI extends GUI{

	public SearchResultGUI(String name) {
		super(name);
	}

	public static JLabel select;
	public static JList<String> choice;
	public static ScrollPane scroll;
	public static JButton open;
	public static Map<String, ImageIcon> cellules = new HashMap<String, ImageIcon>();
	
	@SuppressWarnings("deprecation")
	public static void loadGUI(final ArrayList<Recipe> r){
		
		select = new JLabel(LanguageManager.getText("chooseRecipe"));
		
		open = new JButton(LanguageManager.getText("openRecipe"));
		open.setForeground(Color.WHITE);
		open.setBackground(Color.GREEN);
		open.setFocusPainted(false);
		open.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		select.setBounds(1, Reciper.frame.getHeight()/4-50, 300, 50);
		open.setBounds(Reciper.frame.getWidth()-200, Reciper.frame.getHeight()/4-50, 200, 50);
		
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Recipe rec : Search.recipes){
					if(rec.getTitle() == choice.getSelectedValue()){
						JFrame f = new JFrame(rec.getTitle());
						f.setBounds(Reciper.screenWidth/4, Reciper.screeenHeight/4, 500, 300);
						JTextArea textField = new JTextArea(rec.getRecipe()
								.replace("<html>", "")
								.replace("</html>", "")
								.replace("<br>", "\n"));
						textField.setEditable(false);
						
					    JScrollPane scrollBar = new JScrollPane(textField);
					   
					    f.add(scrollBar);
						
						f.setVisible(true);
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
			choice.setCellRenderer(new SearchListRenderer());
			
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
						for(Recipe rec : Search.recipes){
							if(rec.getTitle() == choice.getSelectedValue()){
								JFrame f = new JFrame(rec.getTitle());
								f.setBounds(Reciper.screenWidth/4, Reciper.screeenHeight/4, 500, 300);
								JTextArea textField = new JTextArea(rec.getRecipe()
										.replace("<html>", "")
										.replace("</html>", "")
										.replace("<br>", "\n"));
								textField.setEditable(false);

							    JScrollPane scrollBar = new JScrollPane(textField);
							   
							    f.add(scrollBar);
								
								f.setVisible(true);
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
