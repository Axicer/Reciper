package fr.axicer.GUI;

import java.awt.Component;
import java.util.ArrayList;

public class GUI {
	
	public ArrayList<Component> components = new ArrayList<>();
	public String name;
	
	public GUI(String name) {
		this.name = name;
	}
	
	public void loadComponents(ArrayList<Component> components){
		this.components = components;
	}
	
	public void hide(){
		for(Component c : components){
			c.setVisible(false);
		}
	}
	
	public void show(){
		for(Component c : components){
			c.setVisible(true);
		}
	}
}
