package py.edu.facitec.ant.app;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.EmptyBorder;

import py.edu.facitec.ant.contenedores.PanelFondo;
import py.edu.facitec.ant.vista.AlgoritmoAnt;
import py.edu.facitec.ant.contenedores.BotonPersonalizado;
import javax.swing.ImageIcon;

public class PantallaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelFondo contentPane;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
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
	public PantallaPrincipal() {
		setTitle("Ant Colony System 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCalculos = new JMenu("Calculos");
		mnCalculos.setAlignmentX(Component.RIGHT_ALIGNMENT);
		menuBar.add(mnCalculos);
		
		JMenu mnNewMenu = new JMenu("Datos de Materias");
		mnNewMenu.setAlignmentX(Component.RIGHT_ALIGNMENT);
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Datos de Salones");
		mnNewMenu_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		menuBar.add(mnNewMenu_1);
		
		JMenu mnConfiguraciones = new JMenu("Configuraciones");
		mnConfiguraciones.setAlignmentX(Component.RIGHT_ALIGNMENT);
		menuBar.add(mnConfiguraciones);
		contentPane = new PanelFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BotonPersonalizado btnprsnlzdAntColony = new BotonPersonalizado();
		btnprsnlzdAntColony.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlgoritmoAnt algoritmoAnt = new AlgoritmoAnt();
				algoritmoAnt.setVisible(true);
			}
		});
		btnprsnlzdAntColony.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/ant/img/antman.png")));
		btnprsnlzdAntColony.setText("Ant Colony");
		btnprsnlzdAntColony.setBounds(29, 23, 112, 110);
		contentPane.add(btnprsnlzdAntColony);
	}
}
