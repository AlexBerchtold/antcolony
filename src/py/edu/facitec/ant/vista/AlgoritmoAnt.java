package py.edu.facitec.ant.vista;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import py.edu.facitec.ant.dao.MateriaDao;
import py.edu.facitec.ant.dao.SalonDao;
import py.edu.facitec.ant.entidades.Materia;
import py.edu.facitec.ant.entidades.Salon;
import py.edu.facitec.ant.util.CalculosEIteraciones;

public class AlgoritmoAnt extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private List<Salon> salones;
	private List<Materia> materias;
	private int[] cantidadSalon;
	private SalonDao salonDao;
	private MateriaDao materiaDao;
	private double[][] matrizPosibilidades;
	private double[][] matrizFeromonas;
	private DecimalFormat formato1 = new DecimalFormat("0.0000"), formato2 = new DecimalFormat("0.00");
	private CalculosEIteraciones iteraciones;
	private JComboBox<Object> cbDia;
	int c = 1;
	private JTextField tfMateria1, txtFeromonas, tfMateria2, txtFeromonas_1, tfMateria3, txtFeromonas_2, tfMateria4,
			txtFeromonas_6, tfMateria5, txtFeromonas_5, tfMateria6, txtFeromonas_4, tfMateria7, txtFeromonas_3, txtAula,
			txtAula_1, txtAula_2, txtAula_3, txtAula_4, txtLab, txtLab_1, txtLab_2, txtLab_3, txtAltoRendimiento,
			tfPos00, tfFero00, tfPos10, tfFero10, tfPos20, tfFero20, tfPos30,
			tfFero30, tfPos40, tfFero40, tfPos50, tfFero50, tfPos60, tfFero60,
			tfPos01, tfFero01, tfPos11, tfFero11, tfPos21, tfFero21, tfPos31,
			tfFero31, tfPos41, tfFero41, tfPos51, tfFero51, tfPos61, tfFero61,
			tfPos02, tfFero02, tfPos12, tfFero12, tfPos22, tfFero22, tfPos32,
			tfFero32, tfPos42, tfFero42, tfPos52, tfFero52, tfPos62, tfFero62,
			tfPos03, tfFero03, tfPos13, tfFero13, tfPos23, tfFero23, tfPos33,
			tfFero33, tfPos43, tfFero43, tfPos53, tfFero53, tfPos63, tfFero63,
			tfPos04, tfFero04, tfPos14, tfFero14, tfPos24, tfFero24, tfPos34,
			tfFero34, tfPos44, tfFero44, tfPos54, tfFero54, tfPos64, tfFero64,
			tfPos05, tfFero05, tfPos15, tfFero15, tfPos25, tfFero25, tfPos35,
			tfFero35, tfPos45, tfFero45, tfPos55, tfFero55, tfPos65, tfFero65,
			tfPos06, tfFero06, tfPos16, tfFero16, tfPos26, tfFero26, tfPos36,
			tfFero36, tfPos46, tfFero46, tfPos56, tfFero56, tfPos66, tfFero66,
			tfPos07, tfFero07, tfPos17, tfFero17, tfPos27, tfFero27, tfPos37,
			tfFero37, tfPos47, tfFero47, tfPos57, tfFero57, tfPos67, tfFero67,
			tfPos08, tfFero08, tfPos18, tfFero18, tfPos28, tfFero28, tfPos38,
			tfFero38, tfPos48, tfFero48, tfPos58, tfFero58, tfPos68, tfFero68,
			tfPos09, tfFero09, tfPos19, tfFero19, tfPos29, tfFero29, tfPos39,
			tfFero39, tfPos49, tfFero49, tfPos59, tfFero59, tfPos69, tfFero69;
	private JButton btnIteraciones;
	private JButton btnImprimir;
	private JLabel lblInformacion;
	private JButton btnRecuperar;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		try {
			AlgoritmoAnt dialog = new AlgoritmoAnt();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AlgoritmoAnt() {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		setBounds(100, 100, 880, 550);
		getContentPane().setLayout(null);

		btnRecuperar = new JButton("Recuperar");
		btnRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recuperar();
			}
		});
		btnRecuperar.setBounds(10, 24, 105, 23);
		getContentPane().add(btnRecuperar);

		cbDia = new JComboBox<Object>();
		cbDia.setModel(
				new DefaultComboBoxModel<Object>(new String[] {"Lunes", "Martes", "Miercoles", "Juevez", "Viernes"}));
		cbDia.setBounds(138, 25, 98, 20);
		getContentPane().add(cbDia);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 63, 846, 8);
		getContentPane().add(separator);

		btnImprimir = new JButton("Crear Hormigas");
		btnImprimir.setEnabled(false);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimir();
			}
		});
		btnImprimir.setBounds(260, 24, 132, 23);
		getContentPane().add(btnImprimir);

		tfMateria1 = new JTextField();
		tfMateria1.setHorizontalAlignment(SwingConstants.CENTER);
		tfMateria1.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfMateria1.setEditable(false);
		tfMateria1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfMateria1.setBounds(10, 124, 86, 20);
		getContentPane().add(tfMateria1);
		tfMateria1.setColumns(10);

		txtFeromonas = new JTextField();
		txtFeromonas.setHorizontalAlignment(SwingConstants.CENTER);
		txtFeromonas.setText("Feromonas");
		txtFeromonas.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFeromonas.setEditable(false);
		txtFeromonas.setColumns(10);
		txtFeromonas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtFeromonas.setBounds(10, 144, 86, 20);
		getContentPane().add(txtFeromonas);

		tfMateria2 = new JTextField();
		tfMateria2.setHorizontalAlignment(SwingConstants.CENTER);
		tfMateria2.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfMateria2.setEditable(false);
		tfMateria2.setColumns(10);
		tfMateria2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfMateria2.setBounds(10, 179, 86, 20);
		getContentPane().add(tfMateria2);

		txtFeromonas_1 = new JTextField();
		txtFeromonas_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtFeromonas_1.setText("Feromonas");
		txtFeromonas_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFeromonas_1.setEditable(false);
		txtFeromonas_1.setColumns(10);
		txtFeromonas_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtFeromonas_1.setBounds(10, 199, 86, 20);
		getContentPane().add(txtFeromonas_1);

		tfMateria3 = new JTextField();
		tfMateria3.setHorizontalAlignment(SwingConstants.CENTER);
		tfMateria3.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfMateria3.setEditable(false);
		tfMateria3.setColumns(10);
		tfMateria3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfMateria3.setBounds(10, 231, 86, 20);
		getContentPane().add(tfMateria3);

		txtFeromonas_2 = new JTextField();
		txtFeromonas_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtFeromonas_2.setText("Feromonas");
		txtFeromonas_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFeromonas_2.setEditable(false);
		txtFeromonas_2.setColumns(10);
		txtFeromonas_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtFeromonas_2.setBounds(10, 251, 86, 20);
		getContentPane().add(txtFeromonas_2);

		tfMateria4 = new JTextField();
		tfMateria4.setHorizontalAlignment(SwingConstants.CENTER);
		tfMateria4.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfMateria4.setEditable(false);
		tfMateria4.setColumns(10);
		tfMateria4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfMateria4.setBounds(10, 288, 86, 20);
		getContentPane().add(tfMateria4);

		txtFeromonas_6 = new JTextField();
		txtFeromonas_6.setHorizontalAlignment(SwingConstants.CENTER);
		txtFeromonas_6.setText("Feromonas");
		txtFeromonas_6.setToolTipText("");
		txtFeromonas_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFeromonas_6.setEditable(false);
		txtFeromonas_6.setColumns(10);
		txtFeromonas_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtFeromonas_6.setBounds(10, 308, 86, 20);
		getContentPane().add(txtFeromonas_6);

		tfMateria5 = new JTextField();
		tfMateria5.setHorizontalAlignment(SwingConstants.CENTER);
		tfMateria5.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfMateria5.setEditable(false);
		tfMateria5.setColumns(10);
		tfMateria5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfMateria5.setBounds(10, 339, 86, 20);
		getContentPane().add(tfMateria5);

		txtFeromonas_5 = new JTextField();
		txtFeromonas_5.setHorizontalAlignment(SwingConstants.CENTER);
		txtFeromonas_5.setText("Feromonas");
		txtFeromonas_5.setToolTipText("");
		txtFeromonas_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFeromonas_5.setEditable(false);
		txtFeromonas_5.setColumns(10);
		txtFeromonas_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtFeromonas_5.setBounds(10, 359, 86, 20);
		getContentPane().add(txtFeromonas_5);

		tfMateria6 = new JTextField();
		tfMateria6.setHorizontalAlignment(SwingConstants.CENTER);
		tfMateria6.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfMateria6.setEditable(false);
		tfMateria6.setColumns(10);
		tfMateria6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfMateria6.setBounds(10, 390, 86, 20);
		getContentPane().add(tfMateria6);

		txtFeromonas_4 = new JTextField();
		txtFeromonas_4.setHorizontalAlignment(SwingConstants.CENTER);
		txtFeromonas_4.setText("Feromonas");
		txtFeromonas_4.setToolTipText("");
		txtFeromonas_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFeromonas_4.setEditable(false);
		txtFeromonas_4.setColumns(10);
		txtFeromonas_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtFeromonas_4.setBounds(10, 410, 86, 20);
		getContentPane().add(txtFeromonas_4);

		tfMateria7 = new JTextField();
		tfMateria7.setHorizontalAlignment(SwingConstants.CENTER);
		tfMateria7.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfMateria7.setEditable(false);
		tfMateria7.setColumns(10);
		tfMateria7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfMateria7.setBounds(10, 441, 86, 20);
		getContentPane().add(tfMateria7);

		txtFeromonas_3 = new JTextField();
		txtFeromonas_3.setHorizontalAlignment(SwingConstants.CENTER);
		txtFeromonas_3.setText("Feromonas");
		txtFeromonas_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFeromonas_3.setEditable(false);
		txtFeromonas_3.setColumns(10);
		txtFeromonas_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtFeromonas_3.setBounds(10, 461, 86, 20);
		getContentPane().add(txtFeromonas_3);

		txtAula = new JTextField();
		txtAula.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAula.setHorizontalAlignment(SwingConstants.CENTER);
		txtAula.setText("Aula 1");
		txtAula.setEditable(false);
		txtAula.setColumns(10);
		txtAula.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAula.setBounds(113, 82, 54, 20);
		getContentPane().add(txtAula);

		txtAula_1 = new JTextField();
		txtAula_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAula_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtAula_1.setText("Aula 2");
		txtAula_1.setEditable(false);
		txtAula_1.setColumns(10);
		txtAula_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAula_1.setBounds(182, 82, 54, 20);
		getContentPane().add(txtAula_1);

		txtAula_2 = new JTextField();
		txtAula_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAula_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtAula_2.setText("Aula 3");
		txtAula_2.setEditable(false);
		txtAula_2.setColumns(10);
		txtAula_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAula_2.setBounds(247, 82, 54, 20);
		getContentPane().add(txtAula_2);

		txtAula_3 = new JTextField();
		txtAula_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAula_3.setHorizontalAlignment(SwingConstants.CENTER);
		txtAula_3.setText("Aula 4");
		txtAula_3.setEditable(false);
		txtAula_3.setColumns(10);
		txtAula_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAula_3.setBounds(311, 82, 54, 20);
		getContentPane().add(txtAula_3);

		txtAula_4 = new JTextField();
		txtAula_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAula_4.setHorizontalAlignment(SwingConstants.CENTER);
		txtAula_4.setText("Aula 5");
		txtAula_4.setEditable(false);
		txtAula_4.setColumns(10);
		txtAula_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAula_4.setBounds(375, 82, 54, 20);
		getContentPane().add(txtAula_4);

		txtLab = new JTextField();
		txtLab.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLab.setHorizontalAlignment(SwingConstants.CENTER);
		txtLab.setText("Lab. 1");
		txtLab.setEditable(false);
		txtLab.setColumns(10);
		txtLab.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtLab.setBounds(439, 82, 54, 20);
		getContentPane().add(txtLab);

		txtLab_1 = new JTextField();
		txtLab_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLab_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtLab_1.setText("Lab 2");
		txtLab_1.setEditable(false);
		txtLab_1.setColumns(10);
		txtLab_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtLab_1.setBounds(503, 82, 54, 20);
		getContentPane().add(txtLab_1);

		txtLab_2 = new JTextField();
		txtLab_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLab_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtLab_2.setText("Lab 3");
		txtLab_2.setEditable(false);
		txtLab_2.setColumns(10);
		txtLab_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtLab_2.setBounds(567, 82, 54, 20);
		getContentPane().add(txtLab_2);

		txtLab_3 = new JTextField();
		txtLab_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLab_3.setHorizontalAlignment(SwingConstants.CENTER);
		txtLab_3.setText("Hard. y Soft.");
		txtLab_3.setEditable(false);
		txtLab_3.setColumns(10);
		txtLab_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtLab_3.setBounds(631, 82, 95, 20);
		getContentPane().add(txtLab_3);

		txtAltoRendimiento = new JTextField();
		txtAltoRendimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAltoRendimiento.setHorizontalAlignment(SwingConstants.CENTER);
		txtAltoRendimiento.setText("Alto Rendimiento");
		txtAltoRendimiento.setEditable(false);
		txtAltoRendimiento.setColumns(10);
		txtAltoRendimiento.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAltoRendimiento.setBounds(736, 82, 105, 20);
		getContentPane().add(txtAltoRendimiento);

		tfPos00 = new JTextField();
		tfPos00.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos00.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos00.setEditable(false);
		tfPos00.setColumns(10);
		tfPos00.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos00.setBounds(113, 124, 54, 20);
		getContentPane().add(tfPos00);

		tfFero00 = new JTextField();
		tfFero00.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero00.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero00.setEditable(false);
		tfFero00.setColumns(10);
		tfFero00.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero00.setBounds(113, 144, 54, 20);
		getContentPane().add(tfFero00);

		tfPos10 = new JTextField();
		tfPos10.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos10.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos10.setEditable(false);
		tfPos10.setColumns(10);
		tfPos10.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos10.setBounds(113, 179, 54, 20);
		getContentPane().add(tfPos10);

		tfFero10 = new JTextField();
		tfFero10.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero10.setEditable(false);
		tfFero10.setColumns(10);
		tfFero10.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero10.setBounds(113, 199, 54, 20);
		getContentPane().add(tfFero10);

		tfPos20 = new JTextField();
		tfPos20.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos20.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos20.setEditable(false);
		tfPos20.setColumns(10);
		tfPos20.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos20.setBounds(113, 231, 54, 20);
		getContentPane().add(tfPos20);

		tfFero20 = new JTextField();
		tfFero20.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero20.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero20.setEditable(false);
		tfFero20.setColumns(10);
		tfFero20.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero20.setBounds(113, 251, 54, 20);
		getContentPane().add(tfFero20);

		tfPos30 = new JTextField();
		tfPos30.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos30.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos30.setEditable(false);
		tfPos30.setColumns(10);
		tfPos30.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos30.setBounds(113, 288, 54, 20);
		getContentPane().add(tfPos30);

		tfFero30 = new JTextField();
		tfFero30.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero30.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero30.setEditable(false);
		tfFero30.setColumns(10);
		tfFero30.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero30.setBounds(113, 308, 54, 20);
		getContentPane().add(tfFero30);

		tfPos40 = new JTextField();
		tfPos40.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos40.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos40.setEditable(false);
		tfPos40.setColumns(10);
		tfPos40.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos40.setBounds(113, 339, 54, 20);
		getContentPane().add(tfPos40);

		tfFero40 = new JTextField();
		tfFero40.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero40.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero40.setEditable(false);
		tfFero40.setColumns(10);
		tfFero40.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero40.setBounds(113, 359, 54, 20);
		getContentPane().add(tfFero40);

		tfPos50 = new JTextField();
		tfPos50.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos50.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos50.setEditable(false);
		tfPos50.setColumns(10);
		tfPos50.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos50.setBounds(113, 390, 54, 20);
		getContentPane().add(tfPos50);

		tfFero50 = new JTextField();
		tfFero50.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero50.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero50.setEditable(false);
		tfFero50.setColumns(10);
		tfFero50.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero50.setBounds(113, 410, 54, 20);
		getContentPane().add(tfFero50);

		tfPos60 = new JTextField();
		tfPos60.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos60.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos60.setEditable(false);
		tfPos60.setColumns(10);
		tfPos60.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos60.setBounds(113, 441, 54, 20);
		getContentPane().add(tfPos60);

		tfFero60 = new JTextField();
		tfFero60.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero60.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero60.setEditable(false);
		tfFero60.setColumns(10);
		tfFero60.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero60.setBounds(113, 461, 54, 20);
		getContentPane().add(tfFero60);

		tfPos01 = new JTextField();
		tfPos01.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos01.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos01.setEditable(false);
		tfPos01.setColumns(10);
		tfPos01.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos01.setBounds(182, 124, 54, 20);
		getContentPane().add(tfPos01);

		tfFero01 = new JTextField();
		tfFero01.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero01.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero01.setEditable(false);
		tfFero01.setColumns(10);
		tfFero01.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero01.setBounds(182, 144, 54, 20);
		getContentPane().add(tfFero01);

		tfPos11 = new JTextField();
		tfPos11.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos11.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos11.setEditable(false);
		tfPos11.setColumns(10);
		tfPos11.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos11.setBounds(182, 179, 54, 20);
		getContentPane().add(tfPos11);

		tfFero11 = new JTextField();
		tfFero11.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero11.setEditable(false);
		tfFero11.setColumns(10);
		tfFero11.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero11.setBounds(182, 199, 54, 20);
		getContentPane().add(tfFero11);

		tfPos21 = new JTextField();
		tfPos21.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos21.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos21.setEditable(false);
		tfPos21.setColumns(10);
		tfPos21.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos21.setBounds(182, 231, 54, 20);
		getContentPane().add(tfPos21);

		tfFero21 = new JTextField();
		tfFero21.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero21.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero21.setEditable(false);
		tfFero21.setColumns(10);
		tfFero21.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero21.setBounds(182, 251, 54, 20);
		getContentPane().add(tfFero21);

		tfPos31 = new JTextField();
		tfPos31.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos31.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos31.setEditable(false);
		tfPos31.setColumns(10);
		tfPos31.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos31.setBounds(182, 288, 54, 20);
		getContentPane().add(tfPos31);

		tfFero31 = new JTextField();
		tfFero31.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero31.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero31.setEditable(false);
		tfFero31.setColumns(10);
		tfFero31.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero31.setBounds(182, 308, 54, 20);
		getContentPane().add(tfFero31);

		tfPos41 = new JTextField();
		tfPos41.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos41.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos41.setEditable(false);
		tfPos41.setColumns(10);
		tfPos41.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos41.setBounds(182, 339, 54, 20);
		getContentPane().add(tfPos41);

		tfFero41 = new JTextField();
		tfFero41.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero41.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero41.setEditable(false);
		tfFero41.setColumns(10);
		tfFero41.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero41.setBounds(182, 359, 54, 20);
		getContentPane().add(tfFero41);

		tfPos51 = new JTextField();
		tfPos51.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos51.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos51.setEditable(false);
		tfPos51.setColumns(10);
		tfPos51.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos51.setBounds(182, 390, 54, 20);
		getContentPane().add(tfPos51);

		tfFero51 = new JTextField();
		tfFero51.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero51.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero51.setEditable(false);
		tfFero51.setColumns(10);
		tfFero51.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero51.setBounds(182, 410, 54, 20);
		getContentPane().add(tfFero51);

		tfPos61 = new JTextField();
		tfPos61.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos61.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos61.setEditable(false);
		tfPos61.setColumns(10);
		tfPos61.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos61.setBounds(182, 441, 54, 20);
		getContentPane().add(tfPos61);

		tfFero61 = new JTextField();
		tfFero61.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero61.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero61.setEditable(false);
		tfFero61.setColumns(10);
		tfFero61.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero61.setBounds(182, 461, 54, 20);
		getContentPane().add(tfFero61);

		tfPos02 = new JTextField();
		tfPos02.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos02.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos02.setEditable(false);
		tfPos02.setColumns(10);
		tfPos02.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos02.setBounds(247, 124, 54, 20);
		getContentPane().add(tfPos02);

		tfFero02 = new JTextField();
		tfFero02.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero02.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero02.setEditable(false);
		tfFero02.setColumns(10);
		tfFero02.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero02.setBounds(247, 144, 54, 20);
		getContentPane().add(tfFero02);

		tfPos12 = new JTextField();
		tfPos12.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos12.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos12.setEditable(false);
		tfPos12.setColumns(10);
		tfPos12.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos12.setBounds(247, 179, 54, 20);
		getContentPane().add(tfPos12);

		tfFero12 = new JTextField();
		tfFero12.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero12.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero12.setEditable(false);
		tfFero12.setColumns(10);
		tfFero12.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero12.setBounds(247, 199, 54, 20);
		getContentPane().add(tfFero12);

		tfPos22 = new JTextField();
		tfPos22.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos22.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos22.setEditable(false);
		tfPos22.setColumns(10);
		tfPos22.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos22.setBounds(247, 231, 54, 20);
		getContentPane().add(tfPos22);

		tfFero22 = new JTextField();
		tfFero22.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero22.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero22.setEditable(false);
		tfFero22.setColumns(10);
		tfFero22.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero22.setBounds(247, 251, 54, 20);
		getContentPane().add(tfFero22);

		tfPos32 = new JTextField();
		tfPos32.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos32.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos32.setEditable(false);
		tfPos32.setColumns(10);
		tfPos32.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos32.setBounds(247, 288, 54, 20);
		getContentPane().add(tfPos32);

		tfFero32 = new JTextField();
		tfFero32.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero32.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero32.setEditable(false);
		tfFero32.setColumns(10);
		tfFero32.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero32.setBounds(247, 308, 54, 20);
		getContentPane().add(tfFero32);

		tfPos42 = new JTextField();
		tfPos42.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos42.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos42.setEditable(false);
		tfPos42.setColumns(10);
		tfPos42.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos42.setBounds(247, 339, 54, 20);
		getContentPane().add(tfPos42);

		tfFero42 = new JTextField();
		tfFero42.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero42.setEditable(false);
		tfFero42.setColumns(10);
		tfFero42.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero42.setBounds(247, 359, 54, 20);
		getContentPane().add(tfFero42);

		tfPos52 = new JTextField();
		tfPos52.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos52.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos52.setEditable(false);
		tfPos52.setColumns(10);
		tfPos52.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos52.setBounds(247, 390, 54, 20);
		getContentPane().add(tfPos52);

		tfFero52 = new JTextField();
		tfFero52.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero52.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero52.setEditable(false);
		tfFero52.setColumns(10);
		tfFero52.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero52.setBounds(247, 410, 54, 20);
		getContentPane().add(tfFero52);

		tfPos62 = new JTextField();
		tfPos62.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos62.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos62.setEditable(false);
		tfPos62.setColumns(10);
		tfPos62.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos62.setBounds(247, 441, 54, 20);
		getContentPane().add(tfPos62);

		tfFero62 = new JTextField();
		tfFero62.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero62.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero62.setEditable(false);
		tfFero62.setColumns(10);
		tfFero62.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero62.setBounds(247, 461, 54, 20);
		getContentPane().add(tfFero62);

		tfPos03 = new JTextField();
		tfPos03.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos03.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos03.setEditable(false);
		tfPos03.setColumns(10);
		tfPos03.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos03.setBounds(311, 124, 54, 20);
		getContentPane().add(tfPos03);

		tfFero03 = new JTextField();
		tfFero03.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero03.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero03.setEditable(false);
		tfFero03.setColumns(10);
		tfFero03.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero03.setBounds(311, 144, 54, 20);
		getContentPane().add(tfFero03);

		tfPos13 = new JTextField();
		tfPos13.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos13.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos13.setEditable(false);
		tfPos13.setColumns(10);
		tfPos13.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos13.setBounds(311, 179, 54, 20);
		getContentPane().add(tfPos13);

		tfFero13 = new JTextField();
		tfFero13.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero13.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero13.setEditable(false);
		tfFero13.setColumns(10);
		tfFero13.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero13.setBounds(311, 199, 54, 20);
		getContentPane().add(tfFero13);

		tfPos23 = new JTextField();
		tfPos23.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos23.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos23.setEditable(false);
		tfPos23.setColumns(10);
		tfPos23.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos23.setBounds(311, 231, 54, 20);
		getContentPane().add(tfPos23);

		tfFero23 = new JTextField();
		tfFero23.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero23.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero23.setEditable(false);
		tfFero23.setColumns(10);
		tfFero23.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero23.setBounds(311, 251, 54, 20);
		getContentPane().add(tfFero23);

		tfPos33 = new JTextField();
		tfPos33.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos33.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos33.setEditable(false);
		tfPos33.setColumns(10);
		tfPos33.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos33.setBounds(311, 288, 54, 20);
		getContentPane().add(tfPos33);

		tfFero33 = new JTextField();
		tfFero33.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero33.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero33.setEditable(false);
		tfFero33.setColumns(10);
		tfFero33.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero33.setBounds(311, 308, 54, 20);
		getContentPane().add(tfFero33);

		tfPos43 = new JTextField();
		tfPos43.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos43.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos43.setEditable(false);
		tfPos43.setColumns(10);
		tfPos43.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos43.setBounds(311, 339, 54, 20);
		getContentPane().add(tfPos43);

		tfFero43 = new JTextField();
		tfFero43.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero43.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero43.setEditable(false);
		tfFero43.setColumns(10);
		tfFero43.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero43.setBounds(311, 359, 54, 20);
		getContentPane().add(tfFero43);

		tfPos53 = new JTextField();
		tfPos53.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos53.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos53.setEditable(false);
		tfPos53.setColumns(10);
		tfPos53.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos53.setBounds(311, 390, 54, 20);
		getContentPane().add(tfPos53);

		tfFero53 = new JTextField();
		tfFero53.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero53.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero53.setEditable(false);
		tfFero53.setColumns(10);
		tfFero53.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero53.setBounds(311, 410, 54, 20);
		getContentPane().add(tfFero53);

		tfPos63 = new JTextField();
		tfPos63.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos63.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos63.setEditable(false);
		tfPos63.setColumns(10);
		tfPos63.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos63.setBounds(311, 441, 54, 20);
		getContentPane().add(tfPos63);

		tfFero63 = new JTextField();
		tfFero63.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero63.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero63.setEditable(false);
		tfFero63.setColumns(10);
		tfFero63.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero63.setBounds(311, 461, 54, 20);
		getContentPane().add(tfFero63);

		tfPos04 = new JTextField();
		tfPos04.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos04.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos04.setEditable(false);
		tfPos04.setColumns(10);
		tfPos04.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos04.setBounds(375, 124, 54, 20);
		getContentPane().add(tfPos04);

		tfFero04 = new JTextField();
		tfFero04.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero04.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero04.setEditable(false);
		tfFero04.setColumns(10);
		tfFero04.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero04.setBounds(375, 144, 54, 20);
		getContentPane().add(tfFero04);

		tfPos14 = new JTextField();
		tfPos14.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos14.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos14.setEditable(false);
		tfPos14.setColumns(10);
		tfPos14.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos14.setBounds(375, 179, 54, 20);
		getContentPane().add(tfPos14);

		tfFero14 = new JTextField();
		tfFero14.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero14.setEditable(false);
		tfFero14.setColumns(10);
		tfFero14.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero14.setBounds(375, 199, 54, 20);
		getContentPane().add(tfFero14);

		tfPos24 = new JTextField();
		tfPos24.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos24.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos24.setEditable(false);
		tfPos24.setColumns(10);
		tfPos24.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos24.setBounds(375, 231, 54, 20);
		getContentPane().add(tfPos24);

		tfFero24 = new JTextField();
		tfFero24.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero24.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero24.setEditable(false);
		tfFero24.setColumns(10);
		tfFero24.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero24.setBounds(375, 251, 54, 20);
		getContentPane().add(tfFero24);

		tfPos34 = new JTextField();
		tfPos34.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos34.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos34.setEditable(false);
		tfPos34.setColumns(10);
		tfPos34.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos34.setBounds(375, 288, 54, 20);
		getContentPane().add(tfPos34);

		tfFero34 = new JTextField();
		tfFero34.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero34.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero34.setEditable(false);
		tfFero34.setColumns(10);
		tfFero34.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero34.setBounds(375, 308, 54, 20);
		getContentPane().add(tfFero34);

		tfPos44 = new JTextField();
		tfPos44.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos44.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos44.setEditable(false);
		tfPos44.setColumns(10);
		tfPos44.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos44.setBounds(375, 339, 54, 20);
		getContentPane().add(tfPos44);

		tfFero44 = new JTextField();
		tfFero44.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero44.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero44.setEditable(false);
		tfFero44.setColumns(10);
		tfFero44.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero44.setBounds(375, 359, 54, 20);
		getContentPane().add(tfFero44);

		tfPos54 = new JTextField();
		tfPos54.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos54.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos54.setEditable(false);
		tfPos54.setColumns(10);
		tfPos54.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos54.setBounds(375, 390, 54, 20);
		getContentPane().add(tfPos54);

		tfFero54 = new JTextField();
		tfFero54.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero54.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero54.setEditable(false);
		tfFero54.setColumns(10);
		tfFero54.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero54.setBounds(375, 410, 54, 20);
		getContentPane().add(tfFero54);

		tfPos64 = new JTextField();
		tfPos64.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos64.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos64.setEditable(false);
		tfPos64.setColumns(10);
		tfPos64.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos64.setBounds(375, 441, 54, 20);
		getContentPane().add(tfPos64);

		tfFero64 = new JTextField();
		tfFero64.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero64.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero64.setEditable(false);
		tfFero64.setColumns(10);
		tfFero64.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero64.setBounds(375, 461, 54, 20);
		getContentPane().add(tfFero64);

		tfPos05 = new JTextField();
		tfPos05.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos05.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos05.setEditable(false);
		tfPos05.setColumns(10);
		tfPos05.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos05.setBounds(439, 124, 54, 20);
		getContentPane().add(tfPos05);

		tfFero05 = new JTextField();
		tfFero05.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero05.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero05.setEditable(false);
		tfFero05.setColumns(10);
		tfFero05.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero05.setBounds(439, 144, 54, 20);
		getContentPane().add(tfFero05);

		tfPos15 = new JTextField();
		tfPos15.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos15.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos15.setEditable(false);
		tfPos15.setColumns(10);
		tfPos15.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos15.setBounds(439, 179, 54, 20);
		getContentPane().add(tfPos15);

		tfFero15 = new JTextField();
		tfFero15.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero15.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero15.setEditable(false);
		tfFero15.setColumns(10);
		tfFero15.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero15.setBounds(439, 199, 54, 20);
		getContentPane().add(tfFero15);

		tfPos25 = new JTextField();
		tfPos25.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos25.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos25.setEditable(false);
		tfPos25.setColumns(10);
		tfPos25.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos25.setBounds(439, 231, 54, 20);
		getContentPane().add(tfPos25);

		tfFero25 = new JTextField();
		tfFero25.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero25.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero25.setEditable(false);
		tfFero25.setColumns(10);
		tfFero25.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero25.setBounds(439, 251, 54, 20);
		getContentPane().add(tfFero25);

		tfPos35 = new JTextField();
		tfPos35.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos35.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos35.setEditable(false);
		tfPos35.setColumns(10);
		tfPos35.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos35.setBounds(439, 288, 54, 20);
		getContentPane().add(tfPos35);

		tfFero35 = new JTextField();
		tfFero35.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero35.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero35.setEditable(false);
		tfFero35.setColumns(10);
		tfFero35.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero35.setBounds(439, 308, 54, 20);
		getContentPane().add(tfFero35);

		tfPos45 = new JTextField();
		tfPos45.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos45.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos45.setEditable(false);
		tfPos45.setColumns(10);
		tfPos45.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos45.setBounds(439, 339, 54, 20);
		getContentPane().add(tfPos45);

		tfFero45 = new JTextField();
		tfFero45.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero45.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero45.setEditable(false);
		tfFero45.setColumns(10);
		tfFero45.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero45.setBounds(439, 359, 54, 20);
		getContentPane().add(tfFero45);

		tfPos55 = new JTextField();
		tfPos55.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos55.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos55.setEditable(false);
		tfPos55.setColumns(10);
		tfPos55.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos55.setBounds(439, 390, 54, 20);
		getContentPane().add(tfPos55);

		tfFero55 = new JTextField();
		tfFero55.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero55.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero55.setEditable(false);
		tfFero55.setColumns(10);
		tfFero55.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero55.setBounds(439, 410, 54, 20);
		getContentPane().add(tfFero55);

		tfPos65 = new JTextField();
		tfPos65.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos65.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos65.setEditable(false);
		tfPos65.setColumns(10);
		tfPos65.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos65.setBounds(439, 441, 54, 20);
		getContentPane().add(tfPos65);

		tfFero65 = new JTextField();
		tfFero65.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero65.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero65.setEditable(false);
		tfFero65.setColumns(10);
		tfFero65.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero65.setBounds(439, 461, 54, 20);
		getContentPane().add(tfFero65);

		tfPos06 = new JTextField();
		tfPos06.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos06.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos06.setEditable(false);
		tfPos06.setColumns(10);
		tfPos06.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos06.setBounds(503, 124, 54, 20);
		getContentPane().add(tfPos06);

		tfFero06 = new JTextField();
		tfFero06.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero06.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero06.setEditable(false);
		tfFero06.setColumns(10);
		tfFero06.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero06.setBounds(503, 144, 54, 20);
		getContentPane().add(tfFero06);

		tfPos16 = new JTextField();
		tfPos16.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos16.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos16.setEditable(false);
		tfPos16.setColumns(10);
		tfPos16.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos16.setBounds(503, 179, 54, 20);
		getContentPane().add(tfPos16);

		tfFero16 = new JTextField();
		tfFero16.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero16.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero16.setEditable(false);
		tfFero16.setColumns(10);
		tfFero16.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero16.setBounds(503, 199, 54, 20);
		getContentPane().add(tfFero16);

		tfPos26 = new JTextField();
		tfPos26.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos26.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos26.setEditable(false);
		tfPos26.setColumns(10);
		tfPos26.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos26.setBounds(503, 231, 54, 20);
		getContentPane().add(tfPos26);

		tfFero26 = new JTextField();
		tfFero26.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero26.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero26.setEditable(false);
		tfFero26.setColumns(10);
		tfFero26.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero26.setBounds(503, 251, 54, 20);
		getContentPane().add(tfFero26);

		tfPos36 = new JTextField();
		tfPos36.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos36.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos36.setEditable(false);
		tfPos36.setColumns(10);
		tfPos36.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos36.setBounds(503, 288, 54, 20);
		getContentPane().add(tfPos36);

		tfFero36 = new JTextField();
		tfFero36.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero36.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero36.setEditable(false);
		tfFero36.setColumns(10);
		tfFero36.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero36.setBounds(503, 308, 54, 20);
		getContentPane().add(tfFero36);

		tfPos46 = new JTextField();
		tfPos46.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos46.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos46.setEditable(false);
		tfPos46.setColumns(10);
		tfPos46.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos46.setBounds(503, 339, 54, 20);
		getContentPane().add(tfPos46);

		tfFero46 = new JTextField();
		tfFero46.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero46.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero46.setEditable(false);
		tfFero46.setColumns(10);
		tfFero46.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero46.setBounds(503, 359, 54, 20);
		getContentPane().add(tfFero46);

		tfPos56 = new JTextField();
		tfPos56.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos56.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos56.setEditable(false);
		tfPos56.setColumns(10);
		tfPos56.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos56.setBounds(503, 390, 54, 20);
		getContentPane().add(tfPos56);

		tfFero56 = new JTextField();
		tfFero56.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero56.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero56.setEditable(false);
		tfFero56.setColumns(10);
		tfFero56.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero56.setBounds(503, 410, 54, 20);
		getContentPane().add(tfFero56);

		tfPos66 = new JTextField();
		tfPos66.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos66.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos66.setEditable(false);
		tfPos66.setColumns(10);
		tfPos66.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos66.setBounds(503, 441, 54, 20);
		getContentPane().add(tfPos66);

		tfFero66 = new JTextField();
		tfFero66.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero66.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero66.setEditable(false);
		tfFero66.setColumns(10);
		tfFero66.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero66.setBounds(503, 461, 54, 20);
		getContentPane().add(tfFero66);

		tfPos07 = new JTextField();
		tfPos07.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos07.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos07.setEditable(false);
		tfPos07.setColumns(10);
		tfPos07.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos07.setBounds(567, 124, 54, 20);
		getContentPane().add(tfPos07);

		tfFero07 = new JTextField();
		tfFero07.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero07.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero07.setEditable(false);
		tfFero07.setColumns(10);
		tfFero07.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero07.setBounds(567, 144, 54, 20);
		getContentPane().add(tfFero07);

		tfPos17 = new JTextField();
		tfPos17.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos17.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos17.setEditable(false);
		tfPos17.setColumns(10);
		tfPos17.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos17.setBounds(567, 179, 54, 20);
		getContentPane().add(tfPos17);

		tfFero17 = new JTextField();
		tfFero17.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero17.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero17.setEditable(false);
		tfFero17.setColumns(10);
		tfFero17.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero17.setBounds(567, 199, 54, 20);
		getContentPane().add(tfFero17);

		tfPos27 = new JTextField();
		tfPos27.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos27.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos27.setEditable(false);
		tfPos27.setColumns(10);
		tfPos27.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos27.setBounds(567, 231, 54, 20);
		getContentPane().add(tfPos27);

		tfFero27 = new JTextField();
		tfFero27.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero27.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero27.setEditable(false);
		tfFero27.setColumns(10);
		tfFero27.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero27.setBounds(567, 251, 54, 20);
		getContentPane().add(tfFero27);

		tfPos37 = new JTextField();
		tfPos37.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos37.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos37.setEditable(false);
		tfPos37.setColumns(10);
		tfPos37.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos37.setBounds(567, 288, 54, 20);
		getContentPane().add(tfPos37);

		tfFero37 = new JTextField();
		tfFero37.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero37.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero37.setEditable(false);
		tfFero37.setColumns(10);
		tfFero37.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero37.setBounds(567, 308, 54, 20);
		getContentPane().add(tfFero37);

		tfPos47 = new JTextField();
		tfPos47.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos47.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos47.setEditable(false);
		tfPos47.setColumns(10);
		tfPos47.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos47.setBounds(567, 339, 54, 20);
		getContentPane().add(tfPos47);

		tfFero47 = new JTextField();
		tfFero47.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero47.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero47.setEditable(false);
		tfFero47.setColumns(10);
		tfFero47.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero47.setBounds(567, 359, 54, 20);
		getContentPane().add(tfFero47);

		tfPos57 = new JTextField();
		tfPos57.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos57.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos57.setEditable(false);
		tfPos57.setColumns(10);
		tfPos57.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos57.setBounds(567, 390, 54, 20);
		getContentPane().add(tfPos57);

		tfFero57 = new JTextField();
		tfFero57.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero57.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero57.setEditable(false);
		tfFero57.setColumns(10);
		tfFero57.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero57.setBounds(567, 410, 54, 20);
		getContentPane().add(tfFero57);

		tfPos67 = new JTextField();
		tfPos67.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos67.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos67.setEditable(false);
		tfPos67.setColumns(10);
		tfPos67.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos67.setBounds(567, 441, 54, 20);
		getContentPane().add(tfPos67);

		tfFero67 = new JTextField();
		tfFero67.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero67.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero67.setEditable(false);
		tfFero67.setColumns(10);
		tfFero67.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero67.setBounds(567, 461, 54, 20);
		getContentPane().add(tfFero67);

		tfPos08 = new JTextField();
		tfPos08.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos08.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos08.setEditable(false);
		tfPos08.setColumns(10);
		tfPos08.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos08.setBounds(653, 124, 54, 20);
		getContentPane().add(tfPos08);

		tfFero08 = new JTextField();
		tfFero08.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero08.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero08.setEditable(false);
		tfFero08.setColumns(10);
		tfFero08.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero08.setBounds(653, 144, 54, 20);
		getContentPane().add(tfFero08);

		tfPos18 = new JTextField();
		tfPos18.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos18.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos18.setEditable(false);
		tfPos18.setColumns(10);
		tfPos18.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos18.setBounds(653, 179, 54, 20);
		getContentPane().add(tfPos18);

		tfFero18 = new JTextField();
		tfFero18.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero18.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero18.setEditable(false);
		tfFero18.setColumns(10);
		tfFero18.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero18.setBounds(653, 199, 54, 20);
		getContentPane().add(tfFero18);

		tfPos28 = new JTextField();
		tfPos28.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos28.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos28.setEditable(false);
		tfPos28.setColumns(10);
		tfPos28.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos28.setBounds(653, 231, 54, 20);
		getContentPane().add(tfPos28);

		tfFero28 = new JTextField();
		tfFero28.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero28.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero28.setEditable(false);
		tfFero28.setColumns(10);
		tfFero28.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero28.setBounds(653, 251, 54, 20);
		getContentPane().add(tfFero28);

		tfPos38 = new JTextField();
		tfPos38.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos38.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos38.setEditable(false);
		tfPos38.setColumns(10);
		tfPos38.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos38.setBounds(653, 288, 54, 20);
		getContentPane().add(tfPos38);

		tfFero38 = new JTextField();
		tfFero38.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero38.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero38.setEditable(false);
		tfFero38.setColumns(10);
		tfFero38.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero38.setBounds(653, 308, 54, 20);
		getContentPane().add(tfFero38);

		tfPos48 = new JTextField();
		tfPos48.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos48.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos48.setEditable(false);
		tfPos48.setColumns(10);
		tfPos48.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos48.setBounds(653, 339, 54, 20);
		getContentPane().add(tfPos48);

		tfFero48 = new JTextField();
		tfFero48.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero48.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero48.setEditable(false);
		tfFero48.setColumns(10);
		tfFero48.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero48.setBounds(653, 359, 54, 20);
		getContentPane().add(tfFero48);

		tfPos58 = new JTextField();
		tfPos58.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos58.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos58.setEditable(false);
		tfPos58.setColumns(10);
		tfPos58.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos58.setBounds(653, 390, 54, 20);
		getContentPane().add(tfPos58);

		tfFero58 = new JTextField();
		tfFero58.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero58.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero58.setEditable(false);
		tfFero58.setColumns(10);
		tfFero58.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero58.setBounds(653, 410, 54, 20);
		getContentPane().add(tfFero58);

		tfPos68 = new JTextField();
		tfPos68.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos68.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos68.setEditable(false);
		tfPos68.setColumns(10);
		tfPos68.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos68.setBounds(653, 441, 54, 20);
		getContentPane().add(tfPos68);

		tfFero68 = new JTextField();
		tfFero68.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero68.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero68.setEditable(false);
		tfFero68.setColumns(10);
		tfFero68.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero68.setBounds(653, 461, 54, 20);
		getContentPane().add(tfFero68);

		tfPos09 = new JTextField();
		tfPos09.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos09.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos09.setEditable(false);
		tfPos09.setColumns(10);
		tfPos09.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos09.setBounds(759, 124, 54, 20);
		getContentPane().add(tfPos09);

		tfFero09 = new JTextField();
		tfFero09.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero09.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero09.setEditable(false);
		tfFero09.setColumns(10);
		tfFero09.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero09.setBounds(759, 144, 54, 20);
		getContentPane().add(tfFero09);

		tfPos19 = new JTextField();
		tfPos19.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos19.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos19.setEditable(false);
		tfPos19.setColumns(10);
		tfPos19.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos19.setBounds(759, 179, 54, 20);
		getContentPane().add(tfPos19);

		tfFero19 = new JTextField();
		tfFero19.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero19.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero19.setEditable(false);
		tfFero19.setColumns(10);
		tfFero19.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero19.setBounds(759, 199, 54, 20);
		getContentPane().add(tfFero19);

		tfPos29 = new JTextField();
		tfPos29.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos29.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos29.setEditable(false);
		tfPos29.setColumns(10);
		tfPos29.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos29.setBounds(759, 231, 54, 20);
		getContentPane().add(tfPos29);

		tfFero29 = new JTextField();
		tfFero29.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero29.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero29.setEditable(false);
		tfFero29.setColumns(10);
		tfFero29.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero29.setBounds(759, 251, 54, 20);
		getContentPane().add(tfFero29);

		tfPos39 = new JTextField();
		tfPos39.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos39.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos39.setEditable(false);
		tfPos39.setColumns(10);
		tfPos39.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos39.setBounds(759, 288, 54, 20);
		getContentPane().add(tfPos39);

		tfFero39 = new JTextField();
		tfFero39.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero39.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero39.setEditable(false);
		tfFero39.setColumns(10);
		tfFero39.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero39.setBounds(759, 308, 54, 20);
		getContentPane().add(tfFero39);

		tfPos49 = new JTextField();
		tfPos49.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos49.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos49.setEditable(false);
		tfPos49.setColumns(10);
		tfPos49.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos49.setBounds(759, 339, 54, 20);
		getContentPane().add(tfPos49);

		tfFero49 = new JTextField();
		tfFero49.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero49.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero49.setEditable(false);
		tfFero49.setColumns(10);
		tfFero49.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero49.setBounds(759, 359, 54, 20);
		getContentPane().add(tfFero49);

		tfPos59 = new JTextField();
		tfPos59.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos59.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos59.setEditable(false);
		tfPos59.setColumns(10);
		tfPos59.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos59.setBounds(759, 390, 54, 20);
		getContentPane().add(tfPos59);

		tfFero59 = new JTextField();
		tfFero59.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero59.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero59.setEditable(false);
		tfFero59.setColumns(10);
		tfFero59.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero59.setBounds(759, 410, 54, 20);
		getContentPane().add(tfFero59);

		tfPos69 = new JTextField();
		tfPos69.setHorizontalAlignment(SwingConstants.CENTER);
		tfPos69.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfPos69.setEditable(false);
		tfPos69.setColumns(10);
		tfPos69.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPos69.setBounds(759, 441, 54, 20);
		getContentPane().add(tfPos69);

		tfFero69 = new JTextField();
		tfFero69.setHorizontalAlignment(SwingConstants.CENTER);
		tfFero69.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFero69.setEditable(false);
		tfFero69.setColumns(10);
		tfFero69.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfFero69.setBounds(759, 461, 54, 20);
		getContentPane().add(tfFero69);
		
		btnIteraciones = new JButton("Iteraci\u00F3n 1");
		btnIteraciones.setEnabled(false);
		btnIteraciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iteraciones();
			}
		});
		btnIteraciones.setBounds(424, 24, 110, 23);
		getContentPane().add(btnIteraciones);
		
		lblInformacion = new JLabel("");
		lblInformacion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblInformacion.setFont(new Font("Arial", Font.BOLD, 12));
		lblInformacion.setBounds(597, 27, 259, 20);
		getContentPane().add(lblInformacion);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(this);
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

	}

	private void recuperar() {
		iteraciones = new CalculosEIteraciones();
		salonDao = new SalonDao();
		salones = salonDao.recuperarTodo();
		materiaDao = new MateriaDao();
		materias = materiaDao.recuperarPorFiltro(cbDia.getSelectedItem() + "");
		cargarMaterias();
		btnImprimir.setEnabled(true);
		lblInformacion.setText("Datos de Salones recuperados");
	}

	private void cargarMaterias() {
		tfMateria1.setText(materias.get(0).getDescripcion()); tfMateria2.setText(materias.get(1).getDescripcion());
		tfMateria3.setText(materias.get(2).getDescripcion()); tfMateria4.setText(materias.get(3).getDescripcion());
		tfMateria5.setText(materias.get(4).getDescripcion()); tfMateria6.setText(materias.get(5).getDescripcion());
		tfMateria7.setText(materias.get(6).getDescripcion());
	}

	private void imprimir() {
		iteraciones = new CalculosEIteraciones();
		matrizPosibilidades = iteraciones.creacionDeMatrizPosibilidades(materias, salones);
		matrizFeromonas = iteraciones.creacionDeMatrizFeromonas(materias.size(), salones.size());
		cantidadSalon = iteraciones.crearEventosSalon(salones.size(), matrizPosibilidades, materias.size());
		rellenarMatrizGrafica();
		btnIteraciones.setEnabled(true);
		btnImprimir.setEnabled(false);
		lblInformacion.setText(" Cantidad de Hormigas Creadas  "+iteraciones.cantidadIteraciones());
	}
	
	private void rellenarMatrizGrafica() {
		tfFero00.setText(formato1.format(matrizFeromonas[0][0])); tfPos00.setText(formato2.format(matrizPosibilidades[0][0]*100)+"%");
		tfFero01.setText(formato1.format(matrizFeromonas[0][1])); tfPos01.setText(formato2.format(matrizPosibilidades[0][1]*100)+"%");
		tfFero02.setText(formato1.format(matrizFeromonas[0][2])); tfPos02.setText(formato2.format(matrizPosibilidades[0][2]*100)+"%");
		tfFero03.setText(formato1.format(matrizFeromonas[0][3])); tfPos03.setText(formato2.format(matrizPosibilidades[0][3]*100)+"%");
		tfFero04.setText(formato1.format(matrizFeromonas[0][4])); tfPos04.setText(formato2.format(matrizPosibilidades[0][4]*100)+"%");
		tfFero05.setText(formato1.format(matrizFeromonas[0][5])); tfPos05.setText(formato2.format(matrizPosibilidades[0][5]*100)+"%");
		tfFero06.setText(formato1.format(matrizFeromonas[0][6])); tfPos06.setText(formato2.format(matrizPosibilidades[0][6]*100)+"%");
		tfFero07.setText(formato1.format(matrizFeromonas[0][7])); tfPos07.setText(formato2.format(matrizPosibilidades[0][7]*100)+"%");
		tfFero08.setText(formato1.format(matrizFeromonas[0][8])); tfPos08.setText(formato2.format(matrizPosibilidades[0][8]*100)+"%");
		tfFero09.setText(formato1.format(matrizFeromonas[0][9])); tfPos09.setText(formato2.format(matrizPosibilidades[0][9]*100)+"%");
		tfFero10.setText(formato1.format(matrizFeromonas[1][0])); tfPos10.setText(formato2.format(matrizPosibilidades[1][0]*100)+"%");
		tfFero11.setText(formato1.format(matrizFeromonas[1][1])); tfPos11.setText(formato2.format(matrizPosibilidades[1][1]*100)+"%");
		tfFero12.setText(formato1.format(matrizFeromonas[1][2])); tfPos12.setText(formato2.format(matrizPosibilidades[1][2]*100)+"%");
		tfFero13.setText(formato1.format(matrizFeromonas[1][3])); tfPos13.setText(formato2.format(matrizPosibilidades[1][3]*100)+"%");
		tfFero14.setText(formato1.format(matrizFeromonas[1][4])); tfPos14.setText(formato2.format(matrizPosibilidades[1][4]*100)+"%");
		tfFero15.setText(formato1.format(matrizFeromonas[1][5])); tfPos15.setText(formato2.format(matrizPosibilidades[1][5]*100)+"%");
		tfFero16.setText(formato1.format(matrizFeromonas[1][6])); tfPos16.setText(formato2.format(matrizPosibilidades[1][6]*100)+"%");
		tfFero17.setText(formato1.format(matrizFeromonas[1][7])); tfPos17.setText(formato2.format(matrizPosibilidades[1][7]*100)+"%");
		tfFero18.setText(formato1.format(matrizFeromonas[1][8])); tfPos18.setText(formato2.format(matrizPosibilidades[1][8]*100)+"%");
		tfFero19.setText(formato1.format(matrizFeromonas[1][9])); tfPos19.setText(formato2.format(matrizPosibilidades[1][9]*100)+"%");
		tfFero20.setText(formato1.format(matrizFeromonas[2][0])); tfPos20.setText(formato2.format(matrizPosibilidades[2][0]*100)+"%");
		tfFero21.setText(formato1.format(matrizFeromonas[2][1])); tfPos21.setText(formato2.format(matrizPosibilidades[2][1]*100)+"%");
		tfFero22.setText(formato1.format(matrizFeromonas[2][2])); tfPos22.setText(formato2.format(matrizPosibilidades[2][2]*100)+"%");
		tfFero23.setText(formato1.format(matrizFeromonas[2][3])); tfPos23.setText(formato2.format(matrizPosibilidades[2][3]*100)+"%");
		tfFero24.setText(formato1.format(matrizFeromonas[2][4])); tfPos24.setText(formato2.format(matrizPosibilidades[2][4]*100)+"%");
		tfFero25.setText(formato1.format(matrizFeromonas[2][5])); tfPos25.setText(formato2.format(matrizPosibilidades[2][5]*100)+"%");
		tfFero26.setText(formato1.format(matrizFeromonas[2][6])); tfPos26.setText(formato2.format(matrizPosibilidades[2][6]*100)+"%");
		tfFero27.setText(formato1.format(matrizFeromonas[2][7])); tfPos27.setText(formato2.format(matrizPosibilidades[2][7]*100)+"%");
		tfFero28.setText(formato1.format(matrizFeromonas[2][8])); tfPos28.setText(formato2.format(matrizPosibilidades[2][8]*100)+"%");
		tfFero29.setText(formato1.format(matrizFeromonas[2][9])); tfPos29.setText(formato2.format(matrizPosibilidades[2][9]*100)+"%");
		tfFero30.setText(formato1.format(matrizFeromonas[3][0])); tfPos30.setText(formato2.format(matrizPosibilidades[3][0]*100)+"%");
		tfFero31.setText(formato1.format(matrizFeromonas[3][1])); tfPos31.setText(formato2.format(matrizPosibilidades[3][1]*100)+"%");
		tfFero32.setText(formato1.format(matrizFeromonas[3][2])); tfPos32.setText(formato2.format(matrizPosibilidades[3][2]*100)+"%");
		tfFero33.setText(formato1.format(matrizFeromonas[3][3])); tfPos33.setText(formato2.format(matrizPosibilidades[3][3]*100)+"%");
		tfFero34.setText(formato1.format(matrizFeromonas[3][4])); tfPos34.setText(formato2.format(matrizPosibilidades[3][4]*100)+"%");
		tfFero35.setText(formato1.format(matrizFeromonas[3][5])); tfPos35.setText(formato2.format(matrizPosibilidades[3][5]*100)+"%");
		tfFero36.setText(formato1.format(matrizFeromonas[3][6])); tfPos36.setText(formato2.format(matrizPosibilidades[3][6]*100)+"%");
		tfFero37.setText(formato1.format(matrizFeromonas[3][7])); tfPos37.setText(formato2.format(matrizPosibilidades[3][7]*100)+"%");
		tfFero38.setText(formato1.format(matrizFeromonas[3][8])); tfPos38.setText(formato2.format(matrizPosibilidades[3][8]*100)+"%");
		tfFero39.setText(formato1.format(matrizFeromonas[3][9])); tfPos39.setText(formato2.format(matrizPosibilidades[3][9]*100)+"%");
		tfFero40.setText(formato1.format(matrizFeromonas[4][0])); tfPos40.setText(formato2.format(matrizPosibilidades[4][0]*100)+"%");
		tfFero41.setText(formato1.format(matrizFeromonas[4][1])); tfPos41.setText(formato2.format(matrizPosibilidades[4][1]*100)+"%");
		tfFero42.setText(formato1.format(matrizFeromonas[4][2])); tfPos42.setText(formato2.format(matrizPosibilidades[4][2]*100)+"%");
		tfFero43.setText(formato1.format(matrizFeromonas[4][3])); tfPos43.setText(formato2.format(matrizPosibilidades[4][3]*100)+"%");
		tfFero44.setText(formato1.format(matrizFeromonas[4][4])); tfPos44.setText(formato2.format(matrizPosibilidades[4][4]*100)+"%");
		tfFero45.setText(formato1.format(matrizFeromonas[4][5])); tfPos45.setText(formato2.format(matrizPosibilidades[4][5]*100)+"%");
		tfFero46.setText(formato1.format(matrizFeromonas[4][6])); tfPos46.setText(formato2.format(matrizPosibilidades[4][6]*100)+"%");
		tfFero47.setText(formato1.format(matrizFeromonas[4][7])); tfPos47.setText(formato2.format(matrizPosibilidades[4][7]*100)+"%");
		tfFero48.setText(formato1.format(matrizFeromonas[4][8])); tfPos48.setText(formato2.format(matrizPosibilidades[4][8]*100)+"%");
		tfFero49.setText(formato1.format(matrizFeromonas[4][9])); tfPos49.setText(formato2.format(matrizPosibilidades[4][9]*100)+"%");
		tfFero50.setText(formato1.format(matrizFeromonas[5][0])); tfPos50.setText(formato2.format(matrizPosibilidades[5][0]*100)+"%");
		tfFero51.setText(formato1.format(matrizFeromonas[5][1])); tfPos51.setText(formato2.format(matrizPosibilidades[5][1]*100)+"%");
		tfFero52.setText(formato1.format(matrizFeromonas[5][2])); tfPos52.setText(formato2.format(matrizPosibilidades[5][2]*100)+"%");
		tfFero53.setText(formato1.format(matrizFeromonas[5][3])); tfPos53.setText(formato2.format(matrizPosibilidades[5][3]*100)+"%");
		tfFero54.setText(formato1.format(matrizFeromonas[5][4])); tfPos54.setText(formato2.format(matrizPosibilidades[5][4]*100)+"%");
		tfFero55.setText(formato1.format(matrizFeromonas[5][5])); tfPos55.setText(formato2.format(matrizPosibilidades[5][5]*100)+"%");
		tfFero56.setText(formato1.format(matrizFeromonas[5][6])); tfPos56.setText(formato2.format(matrizPosibilidades[5][6]*100)+"%");
		tfFero57.setText(formato1.format(matrizFeromonas[5][7])); tfPos57.setText(formato2.format(matrizPosibilidades[5][7]*100)+"%");
		tfFero58.setText(formato1.format(matrizFeromonas[5][8])); tfPos58.setText(formato2.format(matrizPosibilidades[5][8]*100)+"%");
		tfFero59.setText(formato1.format(matrizFeromonas[5][9])); tfPos59.setText(formato2.format(matrizPosibilidades[5][9]*100)+"%");
		tfFero60.setText(formato1.format(matrizFeromonas[6][0])); tfPos60.setText(formato2.format(matrizPosibilidades[6][0]*100)+"%");
		tfFero61.setText(formato1.format(matrizFeromonas[6][1])); tfPos61.setText(formato2.format(matrizPosibilidades[6][1]*100)+"%");
		tfFero62.setText(formato1.format(matrizFeromonas[6][2])); tfPos62.setText(formato2.format(matrizPosibilidades[6][2]*100)+"%");
		tfFero63.setText(formato1.format(matrizFeromonas[6][3])); tfPos63.setText(formato2.format(matrizPosibilidades[6][3]*100)+"%");
		tfFero64.setText(formato1.format(matrizFeromonas[6][4])); tfPos64.setText(formato2.format(matrizPosibilidades[6][4]*100)+"%");
		tfFero65.setText(formato1.format(matrizFeromonas[6][5])); tfPos65.setText(formato2.format(matrizPosibilidades[6][5]*100)+"%");
		tfFero66.setText(formato1.format(matrizFeromonas[6][6])); tfPos66.setText(formato2.format(matrizPosibilidades[6][6]*100)+"%");
		tfFero67.setText(formato1.format(matrizFeromonas[6][7])); tfPos67.setText(formato2.format(matrizPosibilidades[6][7]*100)+"%");
		tfFero68.setText(formato1.format(matrizFeromonas[6][8])); tfPos68.setText(formato2.format(matrizPosibilidades[6][8]*100)+"%");
		tfFero69.setText(formato1.format(matrizFeromonas[6][9])); tfPos69.setText(formato2.format(matrizPosibilidades[6][9]*100)+"%");
	}
	
	private void iteraciones(){
		matrizPosibilidades = iteraciones.calcularIteraciones(cantidadSalon, matrizPosibilidades, matrizFeromonas, materias, salones);
		matrizFeromonas = iteraciones.actualizarFeromonas(matrizPosibilidades, matrizFeromonas, materias, salones);
		rellenarMatrizGrafica();
		c++;
		btnIteraciones.setText("Iteración "+c);
		lblInformacion.setText(" Numero de Sub Iteraciones  "+iteraciones.cantidadIteraciones());
	}
}
