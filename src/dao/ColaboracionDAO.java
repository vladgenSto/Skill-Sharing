package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import domain.Colaboracion;

@Repository
public class ColaboracionDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static final class ColaboracionMapper implements RowMapper<Colaboracion> {
		
		@Override
		public Colaboracion mapRow(ResultSet rs, int rowNum) throws SQLException {
			Colaboracion colaboracion = new Colaboracion();
			colaboracion.setCodigoOferta(rs.getInt("codigoOferta"));
			colaboracion.setCodigoDemanda(rs.getInt("codigoDemanda"));
			colaboracion.setHoras(rs.getInt("horas"));
			colaboracion.setPuntuacion(rs.getInt("puntuacion"));
			colaboracion.setComentarios(rs.getString("comentarios"));
			return colaboracion;
		}
	}
	
	public List<Colaboracion> getColaboraciones() {
		return this.jdbcTemplate.query("select * from colaboracion", new ColaboracionMapper());
	}
	
	public Colaboracion getColaboracion(String codigoOferta, String codigoDemanda) {
		return this.jdbcTemplate.queryForObject("select * from colaboracion where codigoOferta=? and codigoDemanda=?", new Object[]{codigoOferta, codigoDemanda}, new ColaboracionMapper());
	}
	
	public void addColaboracion(Colaboracion colaboracion) {
		this.jdbcTemplate.update("insert into Colaboracion(codigoOferta,codigoDemanda,horas,puntuacion,comentarios) values(?,?,?,?,?)", colaboracion.getCodigoOferta(), colaboracion.getCodigoDemanda(), colaboracion.getHoras(), colaboracion.getPuntuacion(), colaboracion.getComentarios());
	}
	
	public void updateColaboracion(Colaboracion colaboracion) {
		this.jdbcTemplate.update("update colaboracion set horas=?, puntuacion=?, comentarios=? where codigoOferta=? and codigoDemanda=?", colaboracion.getHoras(), colaboracion.getPuntuacion(), colaboracion.getComentarios(), colaboracion.getCodigoOferta(), colaboracion.getCodigoDemanda());
	}
	
	public void deleteColaboracion(Colaboracion colaboracion) {
		this.jdbcTemplate.update("delete from Colaboracion where codigoOferta=? and codigoDemanda=?", colaboracion.getCodigoOferta(), colaboracion.getCodigoDemanda());
	}
}
