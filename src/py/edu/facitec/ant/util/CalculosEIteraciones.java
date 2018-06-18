package py.edu.facitec.ant.util;

import java.util.List;

import py.edu.facitec.ant.entidades.Materia;
import py.edu.facitec.ant.entidades.Salon;

public class CalculosEIteraciones  {
	private int c = 0;
	
	//Metodo para crar la matriz de feromonas
	public double[][] creacionDeMatrizFeromonas(int materia, int salon) {
		double[][] matrizFeromonas = new double[materia][salon];
		for (int i = 0; i < materia; i++) {
			for (int j = 0; j < salon; j++) {
				matrizFeromonas[i][j] = 0.1;
			}
		}
		return matrizFeromonas;
	}

	//metodo que crea la matriz de salones posibles
	public  double[][] creacionDeMatrizPosibilidades(List<Materia> materias, List<Salon> salones){
		double [][] matrizPosibilidades2 = new double[materias.size()][salones.size()+2];
		int i = 0, j = 0;
		while (i < materias.size()) {
			j= 0;
			matrizPosibilidades2[i][j] = 0;
			while (j < salones.size()) {
				c++;
				if (materias.get(i).getCantAlumnos()<= salones.get(j).getAuditorioMaximo()) {
					if (materias.get(i).isHerramientas() == salones.get(j).isHerramientas() || materias.get(i).isHerramientas() == false) {
						if (materias.get(i).isInternet() == salones.get(j).isInternet() || materias.get(i).isInternet() == false) {
							if (materias.get(i).isMaquinas() == salones.get(j).isMaquinas() || materias.get(i).isMaquinas() == false) {
								if (materias.get(i).isPizarra() == salones.get(j).isPizarra() || materias.get(i).isPizarra() == false) {
									if (materias.get(i).isProyector() == salones.get(j).isProyector() || materias.get(i).isProyector() == false) {
										if (materias.get(i).isSpeaker() == salones.get(j).isSpeaker() || materias.get(i).isSpeaker() == false) {
											matrizPosibilidades2[i][j] = 1;
										}
									}
								}
							}
						}
					}
				}
				j++;
			}
			i++;
		}
		return matrizPosibilidades2;
	}
	
	//Ayuda en la creación de las cantidades de eventos que puede tener cada salon
	public int[] crearEventosSalon(int salon, double[][] matrizPosibilidades, int materia) {
		int [] cantidadEventos = new int[salon];
		for (int i = 0; i < cantidadEventos.length; i++) {
			for (int j = 0; j < materia; j++) {
					if (matrizPosibilidades[j][i]==1) {
						cantidadEventos[i]++;
					}
			}
		}
		return cantidadEventos;
	}
	
	//realiza las iteraciones
	public double[][] calcularIteraciones(int[] cantidadEventos, double[][] matrizPosibilidades, double[][] matrizFeromonas, List<Materia> materia, List<Salon> salon) {
		for (int i = 0; i < materia.size(); i++) {
			double sumProb = 0;
			int eventos = 1;
			for (int j = 0; j < salon.size(); j++) {
				eventos=1;
				for (int j2 = 0; j2 < i; j2++) {
					if(matrizPosibilidades[j2][j]>0) eventos++;
				}
				c++;
				sumProb = (sumProb)+(matrizFeromonas[i][j]*Math.pow(0.999, eventos));
			}
			for (int j = 0; j < salon.size(); j++) {
				eventos=1;
				for (int j2 = 0; j2 < i; j2++) {
					if(matrizPosibilidades[j2][j]>0) eventos++;
				}
				c++;
				if (matrizPosibilidades[i][j] != 0) {
					matrizPosibilidades[i][j] =(matrizFeromonas[i][j]*Math.pow(0.999, eventos))/(sumProb);
				}
			}
		}
		return matrizPosibilidades;
	}
	
	// Actualiza las feromonas a cada iteracion
	public double[][] actualizarFeromonas(double[][] matrizPosibilidades, double[][] matrizFeromonas, List<Materia> materia, List<Salon> salon){
		int restriccion = 0;
		for (int i = 0; i < materia.size(); i++) {
			for (int j = 0; j < salon.size(); j++) {
				c++;
				if (matrizPosibilidades[i][j]== 0) {
					matrizFeromonas[i][j] = 0;
				}else{
					if (materia.get(i).getCantAlumnos() == salon.get(j).getAuditorioRecomendado()) { restriccion =2; }else{ restriccion = 1; }
					matrizFeromonas[i][j] = (matrizFeromonas[i][j])*Math.pow(0.99, restriccion);
				}
			}
		}
		return matrizFeromonas;
	}
	
	public int cantidadIteraciones(){
		return c;
	}

}
