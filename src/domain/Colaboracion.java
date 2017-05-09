package domain;

import java.util.Date;

public class Colaboracion {

	private int codigoOferta;
	private int codigoDemanda;
	private String horas;
	private String puntuacion;
	private String comentarios;
	private String descripcionOferta;
	private String descripcionDemanda;
	private Date fechaInicio;
	private Date fechaFin;
	
	
	public Colaboracion() {
		super();
	}

	public Colaboracion(int codigoOferta, int codigoDemanda, String horas, String puntuacion, String comentarios) {
		this.codigoOferta = codigoOferta;
		this.codigoDemanda = codigoDemanda;
		this.horas = horas;
		this.puntuacion = puntuacion;
		this.comentarios = comentarios;
	}

	public int getCodigoOferta() {
		return codigoOferta;
	}

	public void setCodigoOferta(int codigoOferta) {
		this.codigoOferta = codigoOferta;
	}

	public int getCodigoDemanda() {
		return codigoDemanda;
	}

	public void setCodigoDemanda(int codigoDemanda) {
		this.codigoDemanda = codigoDemanda;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}
	
	public String getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	
	public String getDescripcionOferta() {
		return descripcionOferta;
	}

	public void setDescripcionOferta(String decripcionOferta) {
		this.descripcionOferta = decripcionOferta;
	}

	public String getDescripcionDemanda() {
		return descripcionDemanda;
	}

	public void setDescripcionDemanda(String descripcionDemanda) {
		this.descripcionDemanda = descripcionDemanda;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "Colaboracion [codigoOferta=" + codigoOferta + ", codigoDemanda=" + codigoDemanda + ", horas=" + horas + ", puntuacion=" + puntuacion + ", comentarios=" + comentarios + "]";
	}
//bu
}
