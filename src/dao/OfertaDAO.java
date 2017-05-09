package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import domain.Colaboracion;
import domain.Oferta;

@Repository
public class OfertaDAO {

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
			colaboracion.setHoras(rs.getString("horas"));
			colaboracion.setPuntuacion(rs.getString("puntuacion"));
			colaboracion.setComentarios(rs.getString("comentarios"));
			return colaboracion;
		}
	}

	private static final class OfertaMapper implements RowMapper<Oferta> {

		@Override
		public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
			Oferta oferta = new Oferta();
			oferta.setCodigoOferta(rs.getInt("CodigoOferta"));
			oferta.setDescripcion(rs.getString("descripcion"));
			oferta.setFechaInicio(rs.getDate("fechaInicio"));
			oferta.setFechaFin(rs.getDate("fechaFin"));
			oferta.setDniEstudiante(rs.getString("dniEstudiante"));
			oferta.setNombreHabilidad(rs.getString("nombreHabilidad"));
			oferta.setNivelHabilidad(rs.getString("nivelHabilidad"));
//			oferta.setCodigoOferta();
			return oferta;
		}
	}

	public List<Oferta> getOfertas() {
		return this.jdbcTemplate.query(
				"select codigoOferta, descripcion, fechaInicio, fechaFin, dniEstudiante, nombreHabilidad, nivelHabilidad from oferta",
				new OfertaMapper());
	}

	public List<Oferta> getOfertasUsuario(String dniUsuario){
        return this.jdbcTemplate.query(
                "select codigoOferta, descripcion, fechaInicio, fechaFin, dniEstudiante, nombreHabilidad, nivelHabilidad from oferta where dniEstudiante = '"+dniUsuario+"'",
                new OfertaMapper());
    }
	
	public List<Oferta> getOfertasDisponibles(String dniUsuario){
        return this.jdbcTemplate.query(
                "select codigoOferta, descripcion, fechaInicio, fechaFin, dniEstudiante, nombreHabilidad, nivelHabilidad from oferta where dniEstudiante <> '"+dniUsuario+"'",
                new OfertaMapper());
    }
	
	public List<Oferta> getOfertasRelacionadas(String nombreHabilidad,String dniUsuario){
        return this.jdbcTemplate.query(
                "select codigoOferta, descripcion, fechaInicio, fechaFin, dniEstudiante, nombreHabilidad, nivelHabilidad from oferta where nombreHabilidad= '"+nombreHabilidad+"' and dniEstudiante <> '"+dniUsuario+"'",
                new OfertaMapper());
    }
	
	public Oferta getOferta(int codigoOferta) {
		return this.jdbcTemplate.queryForObject(
				"select codigoOferta, descripcion, fechaInicio, fechaFin, dniEstudiante, nombreHabilidad, nivelHabilidad from oferta where codigoOferta=?",
				new Object[] { codigoOferta }, new OfertaMapper());
	}

	public void addOferta(Oferta oferta) {
		oferta.setCodigoOferta();
		this.jdbcTemplate.update(
				"insert into Oferta(descripcion, fechaInicio, fechaFin, dniEstudiante,nombreHabilidad,nivelHabilidad, codigoOferta) values(?, ?, ?, ?, ? ,? ,?)",
				oferta.getDescripcion(), oferta.getFechaInicio(), oferta.getFechaFin(),
				oferta.getDniEstudiante(), oferta.getNombreHabilidad(), oferta.getNivelHabilidad(), oferta.getCodigoOferta());
	}

	public void updateOferta(Oferta oferta) {
//		oferta.setCodigoOferta();
		this.jdbcTemplate.update(
				"update Oferta set descripcion=?, fechaInicio=?, fechaFin=?, dniEstudiante=?, nombreHabilidad=?, nivelHabilidad=? where codigoOferta = ?",
				oferta.getDescripcion(), oferta.getFechaInicio(), oferta.getFechaFin(), oferta.getDniEstudiante(),
				oferta.getNombreHabilidad(), oferta.getNivelHabilidad(), oferta.getCodigoOferta());
	}

	public void deleteOferta(Oferta oferta) {
		Colaboracion colaboracion;
		try{
		colaboracion = this.jdbcTemplate.queryForObject("select * from colaboracion where codigoOferta=?",
				new Object[] { oferta.getCodigoOferta()}, new ColaboracionMapper());
		}catch (EmptyResultDataAccessException e) {
			colaboracion=null;
		}
		if (colaboracion == null) {
			System.out.println("La oferta no esta en una colaboracion");
			this.jdbcTemplate.update("delete from oferta where codigoOferta = ?", oferta.getCodigoOferta());
		}else
			System.out.println("La oferta esta en una colaboracion");
	}
}
