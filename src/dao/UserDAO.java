package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import domain.UserDetails;

@Repository
public class UserDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static final class UserMapper implements RowMapper<UserDetails>{

		@Override
		public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserDetails user=new UserDetails();
			BasicPasswordEncryptor passwordEncryptor=new BasicPasswordEncryptor();
			user.setUsername(rs.getString("usuario"));
			user.setPassword(rs.getString("pwd"));
			user.setDniEstudiante(rs.getString("dniEstudiante"));
			return user;
		}
		
	}
	
	public UserDetails loadUserByName(String username,String password){
		UserDetails user;
		try {
			user=this.jdbcTemplate.queryForObject("select * from usuarios where usuario=?", new Object[]{username},new UserMapper());

		} catch (EmptyResultDataAccessException ex) {
			user = null;
		}
		if(user == null){
			return null;
		}
		BasicPasswordEncryptor passwordEncryptor=new BasicPasswordEncryptor();
		if(passwordEncryptor.checkPassword(password, user.getPassword())){
			return user;
		}else{
			return null;
		}
	}
	public List<UserDetails> getUsuarios(){
		return this.jdbcTemplate.query("select * from usuarios", new UserMapper());
	}
	
	public UserDetails getUsuario(String usuario){
		return this.jdbcTemplate.queryForObject("select * from usuarios where usuario=?", new Object[]{usuario},new UserMapper());
	}
	public void updateUsuario(UserDetails user) {
		this.jdbcTemplate.update("update usuarios set pwd=? where usuario=?", user.getPassword(),user.getUsername());
	}
}
