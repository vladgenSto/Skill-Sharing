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
import domain.Demanda;

@Repository
public class DemandaDAO {

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
			colaboracion.setDescripcionOferta(rs.getString("descripcionOferta"));
			colaboracion.setDescripcionDemanda(rs.getString("descripcionDemanda"));
			colaboracion.setFechaInicio(rs.getDate("fechaInicio"));
			colaboracion.setFechaFin(rs.getDate("fechaFin"));
			return colaboracion;
		}
	}

	private static final class DemandaMapper implements RowMapper<Demanda> {

		@Override
		public Demanda mapRow(ResultSet rs, int rowNum) throws SQLException {
			Demanda demanda = new Demanda();
			demanda.setCodigoDemanda(rs.getInt("codigoDemanda"));
			demanda.setDescripcion(rs.getString("descripcion"));
			demanda.setFechaInicio(rs.getDate("fechaInicio"));
			demanda.setFechaFin(rs.getDate("fechaFin"));
			demanda.setDniEstudiante(rs.getString("dniEstudiante"));
			demanda.setNombreHabilidad(rs.getString("nombreHabilidad"));
			demanda.setNivelHabilidad(rs.getString("nivelHabilidad"));
			return demanda;
		}
	}

	public List<Demanda> getDemandas() {
		return this.jdbcTemplate.query(
				"select codigoDemanda, descripcion, fechaInicio, fechaFin, dniEstudiante, nombreHabilidad, nivelHabilidad from demanda",
				new DemandaMapper());
	}

	public Demanda getDemanda(int codigoDemanda) {
		return this.jdbcTemplate.queryForObject(
				"select codigoDemanda, descripcion, fechaInicio, fechaFin, dniEstudiante, nombreHabilidad, nivelHabilidad from demanda where codigoDemanda=?",
				new Object[] { codigoDemanda }, new DemandaMapper());
	}

	public void addDemanda(Demanda demanda) {
		demanda.setCodigoDemanda();
		this.jdbcTemplate.update(
				"insert into Demanda(codigoDemanda, descripcion, fechaInicio, fechaFin, dniEstudiante,nombreHabilidad,nivelHabilidad) values(?, ?, ?, ?, ? ,? ,?)",
				demanda.getCodigoDemanda(), demanda.getDescripcion(), demanda.getFechaInicio(), demanda.getFechaFin(),
				demanda.getDniEstudiante(), demanda.getNombreHabilidad(), demanda.getNivelHabilidad());
	}

	public void updateDemanda(Demanda demanda) {
		this.jdbcTemplate.update(
				"update Demanda set descripcion=?, fechaInicio=?, fechaFin=?, dniEstudiante=?, nombreHabilidad=?, nivelHabilidad=? where codigoDemanda = ?",
				demanda.getDescripcion(), demanda.getFechaInicio(), demanda.getFechaFin(), demanda.getDniEstudiante(),
				demanda.getNombreHabilidad(), demanda.getNivelHabilidad(), demanda.getCodigoDemanda());
	}

	public boolean deleteDemanda(Demanda demanda) {
		Colaboracion colaboracion;
		try {
			colaboracion = this.jdbcTemplate.queryForObject("select * from colaboracion where codigoDemanda=?",
					new Object[] { demanda.getCodigoDemanda() }, new ColaboracionMapper());
		} catch (EmptyResultDataAccessException e) {
			colaboracion = null;
		}
		if (colaboracion == null) {
			this.jdbcTemplate.update("delete from demanda where codigoDemanda = ?", demanda.getCodigoDemanda());
			return true;
		}
		return false;
	}
	
	public List<Demanda> getDemandasUsuario(String dniUsuario) {
        return this.jdbcTemplate.query(
                "select codigoDemanda, descripcion, fechaInicio, fechaFin, dniEstudiante, nombreHabilidad, nivelHabilidad from demanda where dniEstudiante='"+dniUsuario+"'",
                new DemandaMapper());
    }
    
    public List<Demanda> getDemandasDisponiblesUsuario(String dniUsuario) {
        return this.jdbcTemplate.query(
                "select codigoDemanda, descripcion, fechaInicio, fechaFin, dniEstudiante, nombreHabilidad, nivelHabilidad from demanda where dniEstudiante<>'"+dniUsuario+"'",
                new DemandaMapper());
    }
    
    public List<Demanda> getDemandasCompatibles(String dniUsuario, String nombreHabilidad, String nivelHabilidad) {
        return this.jdbcTemplate.query(
                "select codigoDemanda, descripcion, fechaInicio, fechaFin, dniEstudiante, nombreHabilidad, nivelHabilidad from demanda where dniEstudiante='"+dniUsuario+"' and nombreHabilidad='"+nombreHabilidad+"' and nivelHabilidad='"+nivelHabilidad+"'",
                new DemandaMapper());
    }
}
