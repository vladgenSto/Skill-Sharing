package domain;

public class UserDetails implements InfoEstudiante{

	private String username;
	private String password;
	private String dniEstudiante;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDniEstudiante() {
		return dniEstudiante;
	}
	public void setDniEstudiante(String dni) {
		this.dniEstudiante = dni;
	}
	
}
