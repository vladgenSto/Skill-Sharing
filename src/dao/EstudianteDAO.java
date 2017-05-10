package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import domain.Estudiante;

@Repository
public class EstudianteDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	private static final class EstudianteMapper implements RowMapper<Estudiante>{

		@Override
		public Estudiante mapRow(ResultSet rs, int rowNum) throws SQLException {
			Estudiante estudiante=new Estudiante();
			estudiante.setDni(rs.getString("dni"));
			estudiante.setNombre(rs.getString("nombre"));
			estudiante.setLicenciatura(rs.getString("licenciatura"));
			estudiante.setCurso(rs.getString("curso"));
			estudiante.setCorreo(rs.getString("correo"));
			return estudiante;
		}
		
	}
	
	public List<Estudiante> getEstudiantes(){
		return this.jdbcTemplate.query("select * from estudiante", new EstudianteMapper());
	}
	
	public Estudiante getEstudiante(String dni){
		return this.jdbcTemplate.queryForObject("select * from estudiante where dni=?", new Object[]{dni},new EstudianteMapper());
	}
	
	public void addEstudiante(Estudiante estudiante){
		this.jdbcTemplate.update("insert into Estudiante(dni,nombre,licenciatura,curso,correo) values(?,?,?,?,?)",estudiante.getDni(),estudiante.getNombre(),estudiante.getLicenciatura(),estudiante.getCurso(),estudiante.getCorreo());
	}
	
	public void updateEstudiante(Estudiante estudiante){
		this.jdbcTemplate.update("update estudiante set nombre=?,licenciatura=?,curso=?,correo=? where dni=?",estudiante.getNombre(),estudiante.getLicenciatura(),estudiante.getCurso(),estudiante.getCorreo(),estudiante.getDni());
	}
	
	public void deleteEstudiante(Estudiante estudiante){
		this.jdbcTemplate.update("delete from Estudiante where dni=?",estudiante.getDni());
	}
}
