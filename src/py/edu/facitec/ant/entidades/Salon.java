package py.edu.facitec.ant.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Salon {
	@Id
	@GenericGenerator(name="sal_generator",strategy="increment")
	@GeneratedValue(generator="sal_generator")
	@Column
	private int id;
	@Column
	private String descripcion;
	@Column
	private int auditorioRecomendado;
	@Column
	private int auditorioMaximo;
	@Column
	private boolean maquinas;
	@Column
	private boolean proyector;
	@Column
	private boolean internet;
	@Column
	private boolean pizarra;
	@Column
	private boolean herramientas;
	@Column
	private boolean speaker;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSalon() {
		return descripcion;
	}
	public void setSalon(String salon) {
		descripcion = salon;
	}
	public int getAuditorioRecomendado() {
		return auditorioRecomendado;
	}
	public void setAuditorioRecomendado(int auditorioRecomendado) {
		this.auditorioRecomendado = auditorioRecomendado;
	}
	public int getAuditorioMaximo() {
		return auditorioMaximo;
	}
	public void setAuditorioMaximo(int auditorioMaximo) {
		this.auditorioMaximo = auditorioMaximo;
	}
	public boolean isMaquinas() {
		return maquinas;
	}
	public void setMaquinas(boolean maquinas) {
		this.maquinas = maquinas;
	}
	public boolean isProyector() {
		return proyector;
	}
	public void setProyector(boolean proyector) {
		this.proyector = proyector;
	}
	public boolean isInternet() {
		return internet;
	}
	public void setInternet(boolean internet) {
		this.internet = internet;
	}
	public boolean isPizarra() {
		return pizarra;
	}
	public void setPizarra(boolean pizarra) {
		this.pizarra = pizarra;
	}
	public boolean isHerramientas() {
		return herramientas;
	}
	public void setHerramientas(boolean herramientas) {
		this.herramientas = herramientas;
	}
	public boolean isSpeaker() {
		return speaker;
	}
	public void setSpeaker(boolean speaker) {
		this.speaker = speaker;
	}
	
	

}
