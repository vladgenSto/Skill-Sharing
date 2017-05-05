package domain;

public class Habilidad {
	private String nombre;
	private String nivel;
	private String descripcion;
	
	public Habilidad(){
		this.nombre="";
		this.nivel="";
		this.descripcion="";
	}

	public Habilidad(String nombre, String nivel, String descripcion) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Habilidad [nombre=" + nombre + ", nivel=" + nivel + ", descripcion=" + descripcion + "]";
	}
	
}
