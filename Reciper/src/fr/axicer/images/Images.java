package fr.axicer.images;

import javax.swing.ImageIcon;

public enum Images
{
  CLOSE_BUTTON_HOVER(new ImageIcon(Images.class.getResource("resources/closeButton_hover.png"))), 
  CLOSE_BUTTON(new ImageIcon(Images.class.getResource("resources/closeButton.png"))),
  ICON(new ImageIcon(Images.class.getResource("resources/icon.png"))), 
  MAXIMIZE_BUTTON_HOVER(new ImageIcon(Images.class.getResource("resources/maximizeButton_hover.png"))), 
  MAXIMIZE_BUTTON(new ImageIcon(Images.class.getResource("resources/maximizeButton.png"))), 
  MINIMIZE_BUTTON_HOVER(new ImageIcon(Images.class.getResource("resources/minimizeButton_hover.png"))), 
  MINIMIZE_BUTTON(new ImageIcon(Images.class.getResource("resources/minimizeButton.png"))), 
  REDUCE_BUTTON_HOVER(new ImageIcon(Images.class.getResource("resources/reduceButton_hover.png"))), 
  REDUCE_BUTTON(new ImageIcon(Images.class.getResource("resources/reduceButton.png"))),
  STARTING(new ImageIcon(Images.class.getResource("resources/starting.png"))),
  SETTINGS(new ImageIcon(Images.class.getResource("resources/reglages.png"))),
  SETTINGS_HOVER(new ImageIcon(Images.class.getResource("resources/reglages_hover.png"))),
  BROWSE(new ImageIcon(Images.class.getResource("resources/browse.png"))),
  CHOOSE(new ImageIcon(Images.class.getResource("resources/choose.png")));
  
  private ImageIcon icon;
  
  private Images(ImageIcon icon) {
    setIcon(icon);
  }
  
  public ImageIcon getIcon() {
    return icon;
  }
  
  public void setIcon(ImageIcon icon) {
    this.icon = icon;
  }
}
