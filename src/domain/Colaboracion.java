package domain;

public class Colaboracion {

	private String codigoOferta;
	private String codigoDemanda;
	private int horas;
	private int puntuacion;
	private String comentarios;
	
	
	public Colaboracion() {
		super();
	}

	public Colaboracion(String codigoOferta, String codigoDemanda, int horas, int puntuacion, String comentarios) {
		this.codigoOferta = codigoOferta;
		this.codigoDemanda = codigoDemanda;
		this.horas = horas;
		this.puntuacion = puntuacion;
		this.comentarios = comentarios;
	}

	public String getCodigoOferta() {
		return codigoOferta;
	}

	public void setCodigoOferta(String codigoOferta) {
		this.codigoOferta = codigoOferta;
	}

	public String getCodigoDemanda() {
		return codigoDemanda;
	}

	public void setCodigoDemanda(String codigoDemanda) {
		this.codigoDemanda = codigoDemanda;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "Colaboracion [codigoOferta=" + codigoOferta + ", codigoDemanda=" + codigoDemanda + ", horas=" + horas + ", puntuacion=" + puntuacion + ", comentarios=" + comentarios + "]";
	}
	
}
