package domain;

import java.util.Date;

public class Demanda {
	private int codigoDemanda;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private String dniEstudiante;
	private String nombreHabilidad;
	private String nivelHabilidad;
	
	public Demanda() {
		super();
	}

	public Demanda(int codigoDemanda, String descripcion, Date fechaInicio, Date fechaFin, String dniEstudiante,
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

	public int getCodigoDemanda() {
		return codigoDemanda;
	}

	public void setCodigoDemanda(int codigoDemanda) {
		this.codigoDemanda = codigoDemanda;
	}
	public void setCodigoDemanda(){
		this.codigoDemanda=this.hashCode();
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((dniEstudiante == null) ? 0 : dniEstudiante.hashCode());
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((nivelHabilidad == null) ? 0 : nivelHabilidad.hashCode());
		result = prime * result + ((nombreHabilidad == null) ? 0 : nombreHabilidad.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Demanda other = (Demanda) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (dniEstudiante == null) {
			if (other.dniEstudiante != null)
				return false;
		} else if (!dniEstudiante.equals(other.dniEstudiante))
			return false;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (nivelHabilidad == null) {
			if (other.nivelHabilidad != null)
				return false;
		} else if (!nivelHabilidad.equals(other.nivelHabilidad))
			return false;
		if (nombreHabilidad == null) {
			if (other.nombreHabilidad != null)
				return false;
		} else if (!nombreHabilidad.equals(other.nombreHabilidad))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Demanda [codigoDemanda=" + codigoDemanda + ", descripcion=" + descripcion + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", dniEstudiante=" + dniEstudiante + ", nombreHabilidad="
				+ nombreHabilidad + ", nivelHabilidad=" + nivelHabilidad + "]";
	}
	
	
	
	

}
