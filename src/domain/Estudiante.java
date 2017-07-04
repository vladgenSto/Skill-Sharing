package domain;

public class Estudiante{
	private String dni;
	private String nombre;
	private String licenciatura;
	private String curso;
	private String correo;
	private int horasDadas;
	private int horasRecibidas;
	private boolean baneado;
	
	public Estudiante(){
		this.dni="";
		this.nombre="";
		this.licenciatura="";
		this.curso="";
		this.correo="";
		this.horasDadas=0;
		this.horasRecibidas=0;
		this.baneado = false;
	}
	
	public Estudiante(String dni, String nombre, String licenciatura, String curso,
			String correo,int horasDadas,int horasRecibidas) {
		this.dni = dni;
		this.nombre = nombre;
		this.licenciatura = licenciatura;
		this.curso = curso;
		this.correo = correo;
		this.horasDadas=horasDadas;
		this.horasRecibidas=horasRecibidas;
		this.baneado = false;
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

	
	public int getHorasDadas() {
		return horasDadas;
	}

	public void setHorasDadas(int horasDadas) {
		this.horasDadas = horasDadas;
	}

	public int getHorasRecibidas() {
		return horasRecibidas;
	}

	public void setHorasRecibidas(int horasRecibidas) {
		this.horasRecibidas = horasRecibidas;
	}
	
	public boolean getBaneado() {
		return baneado;
	}
	
	public void setBaneado(boolean banear) {
		this.baneado = banear;
	}

	@Override
	public String toString() {
		return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", licenciatura=" + licenciatura + ", curso=" + curso + ", correo=" + correo + "]";
	}
	
}
