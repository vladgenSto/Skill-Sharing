package controller;

import java.util.ArrayList;

import org.springframework.validation.Errors;

import domain.Demanda;
import domain.Estudiante;
import domain.Niveles;

public class DemandaValidator {

	private Niveles niveles=new Niveles();
	
	public boolean supports(Class<?> cls) {
		return Estudiante.class.equals(cls);
	}
	
	public void validate(Object obj, Errors errors) {
		Demanda demanda = (Demanda) obj;
		if (demanda.getCodigoDemanda().trim().equals(""))
			errors.rejectValue("codigoDemanda", "obligatori", "Este campo es obligatorio");
		if (demanda.getCodigoDemanda().length() > 9)
			errors.rejectValue("codigoDemanda", "obligatori", "Este campo supera los 9 caracteres");
		
		if (demanda.getDescripcion().length() > 200)
			errors.rejectValue("descripcion", "obligatori", "Este campo supera los 200 caracteres");

		if (demanda.getFechaInicio() == null) {
			errors.rejectValue("fechaInicio", "obligatori", "Este campo es obligatorio");
		} else if (demanda.getFechaFin() == null) {
			errors.rejectValue("fechaFin", "obligatori", "Este campo es obligatorio");
		} else if (demanda.getFechaInicio().after(demanda.getFechaFin())) {
			errors.rejectValue("fechaInicio", "obligatori", "Fecha posterior a la final");
			errors.rejectValue("fechaFin", "obligatori", "Fecha inferior a la inicial");
		}
		if (demanda.getDniEstudiante().trim().equals(""))
			errors.rejectValue("dniEstudiante", "obligatori", "Este campo es obligatorio");
		if (demanda.getDniEstudiante().length() > 9)
			errors.rejectValue("dniEstudiante", "obligatori", "Este campo supera los 9 caracteres");
		
		if (demanda.getNombreHabilidad().trim().equals(""))
			errors.rejectValue("nombreHabilidad", "obligatori", "Este campo es obligatorio");
		if (demanda.getNombreHabilidad().length() > 30)
			errors.rejectValue("nombreHabilidad", "obligatori", "Este campo supera los 30 caracteres");
		
		ArrayList<String> opcionesNivel=niveles.dameNivelesDisponibles();
		if (demanda.getNivelHabilidad().trim().equals(""))
			errors.rejectValue("nivelHabilidad", "obligatori", "Este campo es obligatorio");
		if (demanda.getNivelHabilidad().length() > 15)
			errors.rejectValue("nivelHabilidad", "obligatori", "Este campo supera los 15 caracteres");
		if(!opcionesNivel.contains(demanda.getNivelHabilidad())){
			errors.rejectValue("nivelHabilidad", "obligatorio","Este campo solo puede ser Iniciacion, Intermedio o Experto");
		}
	}
	
}
