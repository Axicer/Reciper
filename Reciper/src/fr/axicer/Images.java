package fr.axicer;

import javax.swing.ImageIcon;

public enum Images
{
  CLOSE_BUTTON_HOVER(new ImageIcon(Images.class.getResource("img/closeButton_hover.png"))), 
  CLOSE_BUTTON(new ImageIcon(Images.class.getResource("img/closeButton.png"))),
  ICON(new ImageIcon(Images.class.getResource("img/icon.png"))), 
  MAXIMIZE_BUTTON_HOVER(new ImageIcon(Images.class.getResource("img/maximizeButton_hover.png"))), 
  MAXIMIZE_BUTTON(new ImageIcon(Images.class.getResource("img/maximizeButton.png"))), 
  MINIMIZE_BUTTON_HOVER(new ImageIcon(Images.class.getResource("img/minimizeButton_hover.png"))), 
  MINIMIZE_BUTTON(new ImageIcon(Images.class.getResource("img/minimizeButton.png"))), 
  REDUCE_BUTTON_HOVER(new ImageIcon(Images.class.getResource("img/reduceButton_hover.png"))), 
  REDUCE_BUTTON(new ImageIcon(Images.class.getResource("img/reduceButton.png"))),
  STARTING(new ImageIcon(Images.class.getResource("img/starting.png"))),
  SETTINGS(new ImageIcon(Images.class.getResource("img/reglages.png"))),
  SETTINGS_HOVER(new ImageIcon(Images.class.getResource("img/reglages_hover.png"))),
  BROWSE(new ImageIcon(Images.class.getResource("img/browse.png"))),
  CHOOSE(new ImageIcon(Images.class.getResource("img/choose.png")));
  
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
