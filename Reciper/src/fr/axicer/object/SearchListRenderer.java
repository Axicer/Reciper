package fr.axicer.object;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import fr.axicer.GUI.SearchResultGUI;

public class SearchListRenderer extends DefaultListCellRenderer {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("helvitica", Font.BOLD, 24);

    @SuppressWarnings("rawtypes")
	@Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        ImageIcon icon = SearchResultGUI.cellules.get(value);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(64, 64,  Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        
        label.setIcon(icon);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);
        return label;
    }
}
