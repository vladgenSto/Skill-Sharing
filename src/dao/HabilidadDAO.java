package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import domain.Habilidad;
import domain.Niveles;


@Repository
public class HabilidadDAO {
	
	private JdbcTemplate jdbcTemplate;
	private Niveles niveles=new Niveles();

	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	private static final class HabilidadMapper implements RowMapper<Habilidad>{

		@Override
		public Habilidad mapRow(ResultSet rs, int rowNum) throws SQLException {
			Habilidad habilidad=new Habilidad();
			habilidad.setNombre(rs.getString("nombre"));
			habilidad.setNivel(rs.getString("nivel"));
			habilidad.setDescripcion(rs.getString("descripcion"));
			return habilidad;
		}
		
	}
	
	public List<Habilidad> getHabilidades(){
		return this.jdbcTemplate.query("SELECT * FROM habilidad", new HabilidadMapper());
	}
	
	public Habilidad getHabilidad(String nombre,String nivel){
		Habilidad habilidad;
		try{
			habilidad=this.jdbcTemplate.queryForObject("SELECT * FROM habilidad WHERE nivel=? AND nombre=?", new Object[]{nivel,nombre},new HabilidadMapper());
		}catch(EmptyResultDataAccessException e){
			habilidad=null;
		}
		return habilidad;
	}
	
	public void addHabilidad(Habilidad habilidad){
		for (int i = 0; i < niveles.dameNivelesDisponibles().size(); i++) {
			this.jdbcTemplate.update("INSERT INTO habilidad(nombre,nivel,descripcion)VALUES(?,?,?)",habilidad.getNombre(),niveles.dameNivelesDisponibles().get(i),habilidad.getDescripcion());
		}
		
	}
	public void addHabilidadIndividual(Habilidad habilidad){
		this.jdbcTemplate.update("INSERT INTO habilidad(nombre,nivel,descripcion)VALUES(?,?,?)",habilidad.getNombre(),habilidad.getNivel(),habilidad.getDescripcion());
	}
	
	public List<Habilidad> getHabilidadesSinRepeticiones(){
        List<Habilidad>listaHabilidades=this.jdbcTemplate.query("SELECT * FROM habilidad", new HabilidadMapper());
        ArrayList<Habilidad>listaSinRepeticiones=new ArrayList<Habilidad>();
        ArrayList<String> habilidades=new ArrayList<String>();
        for(int i=0;i<listaHabilidades.size();i++){
            if(!habilidades.contains(listaHabilidades.get(i).getNombre())){
                listaSinRepeticiones.add(listaHabilidades.get(i));
                habilidades.add(listaHabilidades.get(i).getNombre());
            }
        }
        return listaSinRepeticiones;
    }
	
	public void updateHabilidad(Habilidad habilidad){
		this.jdbcTemplate.update("UPDATE habilidad SET descripcion=? WHERE nombre=? AND nivel=?",habilidad.getDescripcion(),habilidad.getNombre(),habilidad.getNivel());
	}
	
	public void deleteHabilidad(Habilidad habilidad){
		this.jdbcTemplate.update("DELETE FROM habilidad WHERE nombre=? AND nivel=?",habilidad.getNombre(),habilidad.getNivel());
	}
	
}
