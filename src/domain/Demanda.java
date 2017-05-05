package domain;

import java.util.Date;

public class Demanda {
	private String codigoDemanda;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private String dniEstudiante;
	private String nombreHabilidad;
	private String nivelHabilidad;
	
	public Demanda() {
		super();
	}

	public Demanda(String codigoDemanda, String descripcion, Date fechaInicio, Date fechaFin, String dniEstudiante,
			String nombreHabilidad, String nivelHabilidad) {
		super();
		this.codigoDemanda = codigoDemanda;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.dniEstudiante = dniEstudiante;
		this.nombreHabilidad = nombreHabilidad;
		this.nivelHabilidad = nivelHabilidad;
	}

	public String getCodigoDemanda() {
		return codigoDemanda;
	}

	public void setCodigoDemanda(String codigoDemanda) {
		this.codigoDemanda = codigoDemanda;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getDniEstudiante() {
		return dniEstudiante;
	}

	public void setDniEstudiante(String dniEstudiante) {
		this.dniEstudiante = dniEstudiante;
	}

	public String getNombreHabilidad() {
		return nombreHabilidad;
	}

	public void setNombreHabilidad(String nombreHabilidad) {
		this.nombreHabilidad = nombreHabilidad;
	}

	public String getNivelHabilidad() {
		return nivelHabilidad;
	}

	public void setNivelHabilidad(String nivelHabilidad) {
		this.nivelHabilidad = nivelHabilidad;
	}

	@Override
	public String toString() {
		return "Demanda [codigoDemanda=" + codigoDemanda + ", descripcion=" + descripcion + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", dniEstudiante=" + dniEstudiante + ", nombreHabilidad="
				+ nombreHabilidad + ", nivelHabilidad=" + nivelHabilidad + "]";
	}
	
	
	
	

}
