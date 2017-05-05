package domain;

public class Estudiante {
	private String dni;
	private String nombre;
	private String licenciatura;
	private String curso;
	private String correo;
	
	public Estudiante(){
		this.dni="";
		this.nombre="";
		this.licenciatura="";
		this.curso="";
		this.correo="";
	}
	
	public Estudiante(String dni, String nombre, String licenciatura, String curso,
			String correo) {
		this.dni = dni;
		this.nombre = nombre;
		this.licenciatura = licenciatura;
		this.curso = curso;
		this.correo = correo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLicenciatura() {
		return licenciatura;
	}

	public void setLicenciatura(String licenciatura) {
		this.licenciatura = licenciatura;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", licenciatura=" + licenciatura + ", curso=" + curso + ", correo=" + correo + "]";
	}
	
}
