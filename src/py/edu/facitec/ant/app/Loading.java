package py.edu.facitec.ant.app;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import py.edu.facitec.ant.contenedores.LoadigPanel;
import py.edu.facitec.ant.util.Factory;

public class Loading extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoadigPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BeautyEyeLNFHelper. frameBorderStyle = BeautyEyeLNFHelper . FrameBorderStyle . osLookAndFeelDecorated ;   
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		UIManager.put("RootPane.setupButtonVisible", Boolean.FALSE);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loading frame = new Loading();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Loading() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 332);
		contentPane = new LoadigPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(this);		

		abrir.start();
	}
	
	Timer abrir = new Timer(1500, new ActionListener() {	
		public void actionPerformed(ActionEvent e) {
			abrirMenu();
			abrir.stop();
		}
	});
	
	public void abrirMenu(){
		Factory.setUp();
		PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
		pantallaPrincipal.setVisible(true);
		dispose();
	}
	
	
}
