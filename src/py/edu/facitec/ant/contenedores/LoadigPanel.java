package py.edu.facitec.ant.contenedores;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LoadigPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	URL url = getClass().getResource("/py/edu/facitec/ant/img/loading.jpg");
	Image image = new ImageIcon(url).getImage();
	
	
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paintComponent(g);
	}

	
}
