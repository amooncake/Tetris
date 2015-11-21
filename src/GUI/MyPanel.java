package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Insets getInsets(){
		return new Insets(250,0,0,0);
	}
	public void paintComponent(Graphics g)  
	{  
	    super.paintComponent(g); 
	    Image image = new ImageIcon("resources\\background2.png").getImage();
	    g.drawImage(image,0,0,this.getWidth(), this.getHeight(), null);  
	} 
}