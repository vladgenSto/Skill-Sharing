package domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dao.OfertaDAO;


public class CalculadorEstadisticas {

	public Map<Integer,Integer> colaboracionesPorAnyo(List<Colaboracion> colaboraciones){
		Map<Integer,Integer> colaboracionesPorMes=this.crearDiccionarioMeses();
		Date fechaActual=new Date();
		for(Colaboracion colaboracion:colaboraciones){
			if(colaboracion.getFechaInicio().getYear() == fechaActual.getYear()){
				int numVeces=colaboracionesPorMes.get(colaboracion.getFechaInicio().getMonth()+1) + 1;
				colaboracionesPorMes.put(colaboracion.getFechaInicio().getMonth()+1, numVeces);
			}
		}
		return colaboracionesPorMes;
	}
	
	public Map<String,Integer> colaboracionesPorHabilidad(List<Colaboracion>colaboraciones,List<Habilidad> list,OfertaDAO ofertaDao){
		Map<String,Integer> colaboracionesPorHabilidad=this.crearDiccionarioHabilidades(list);
		for(Colaboracion colaboracion:colaboraciones){
			Oferta oferta=ofertaDao.getOferta(colaboracion.getCodigoOferta());
			int numVeces=colaboracionesPorHabilidad.get(oferta.getNombreHabilidad()) + 1;
			colaboracionesPorHabilidad.put(oferta.getNombreHabilidad(), numVeces);
		}
		return colaboracionesPorHabilidad;
	}
	
	private Map<Integer,Integer> crearDiccionarioMeses(){
		HashMap<Integer,Integer> diccionarioMes=new HashMap<Integer,Integer>();
		for(int i=1;i<=12;i++)
			diccionarioMes.put(i, 0);
		return diccionarioMes;
	}
	
	private Map<String,Integer> crearDiccionarioHabilidades(List<Habilidad> habilidadesDisponibles){
		TreeMap<String,Integer> diccionarioHabilidad=new TreeMap<String,Integer>();
		for(Habilidad habilidad:habilidadesDisponibles)
			diccionarioHabilidad.put(habilidad.getNombre(), 0);
		return diccionarioHabilidad;
	}
}
