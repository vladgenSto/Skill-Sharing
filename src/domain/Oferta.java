package domain;

import java.util.Date;

public class Oferta {

	private int codigoOferta;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private String dniEstudiante;
	private String nombreHabilidad;
	private String nivelHabilidad;
	
	public Oferta() {
		super();
	}
	public Oferta(int codigoOferta, String descripcion, Date fechaInicio, Date fechaFin, String dniEstudiante,
			String nombreHabilidad, String nivelHabilidad) {
		super();
		this.codigoOferta = codigoOferta;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.dniEstudiante = dniEstudiante;
		this.nombreHabilidad = nombreHabilidad;
		this.nivelHabilidad = nivelHabilidad;
	}

	@Override
	public String toString() {
		return "Oferta [codigoOferta=" + codigoOferta + ", descripcion=" + descripcion + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", dniEstudiante=" + dniEstudiante + ", nombreHabilidad=" + nombreHabilidad
				+ ", nivelHabilidad=" + nivelHabilidad + "]";
	}

	public int getCodigoOferta() {
		return codigoOferta;
	}

	public void setCodigoOferta() {
		this.codigoOferta = this.hashCode();
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
	public int hashCode() {
		int numero = (int) Math.random();
		System.out.println(numero);
		return numero;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oferta other = (Oferta) obj;
		if (codigoOferta != other.codigoOferta)
			return false;
		return true;
	}
	
}
