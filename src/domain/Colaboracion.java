package domain;

public class Colaboracion {

	private int codigoOferta;
	private int codigoDemanda;
	private int horas;
	private int puntuacion;
	private String comentarios;
	
	
	public Colaboracion() {
		super();
	}

	public Colaboracion(int codigoOferta, int codigoDemanda, int horas, int puntuacion, String comentarios) {
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
