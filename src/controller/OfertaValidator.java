package controller;

import java.util.ArrayList;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Niveles;
import domain.Oferta;

public class OfertaValidator implements Validator{

	private Niveles niveles=new Niveles();
	@Override
	public boolean supports(Class<?> cls) {
		return Oferta.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Oferta oferta=(Oferta) obj;
		if(oferta.getDescripcion().trim().equals("")){
			errors.rejectValue("descripcion", "obligatorio","Este campo es obligatorio");
		}else if (oferta.getDescripcion().length() > 200)
			errors.rejectValue("descripcion", "obligatori", "Este campo supera los 200 caracteres");
		
		if(oferta.getFechaInicio()==null){
			errors.rejectValue("fechaInicio", "obligatorio","Este campo es obligatorio");
		}
		if(oferta.getFechaFin()==null){
			errors.rejectValue("fechaFin", "obligatorio","Este campo es obligatorio");
		}else if(oferta.getFechaFin().before(oferta.getFechaInicio())){
			errors.rejectValue("fechaFin", "obligatorio","La fecha de inicio debe ser ANTERIOR a la fecha de finalizacion");
		}
		if(oferta.getDniEstudiante().trim().equals("")){
			errors.rejectValue("dniEstudiante", "obligatorio","Este campo es obligatorio");
		}else if(oferta.getDniEstudiante().length()>9){
			errors.rejectValue("dniEstudiante", "obligatorio","Este campo supera los 9 caracteres");
		}
		if(oferta.getNombreHabilidad().trim().equals("---Elige---")){
			errors.rejectValue("nombreHabilidad", "obligatorio","Este campo es obligatorio");
		}else if (oferta.getNombreHabilidad().length() > 30)
			errors.rejectValue("nombreHabilidad", "obligatori", "Este campo supera los 30 caracteres");
		
		ArrayList<String> opcionesNivel=niveles.dameNivelesDisponibles();
		if(!opcionesNivel.contains(oferta.getNivelHabilidad())){
			errors.rejectValue("nivelHabilidad", "obligatorio","Este campo solo puede ser Iniciacion, Intermedio o Experto");
		}else if (oferta.getNivelHabilidad().length() > 15)
			errors.rejectValue("nivelHabilidad", "obligatori", "Este campo supera los 15 caracteres");
		
	}

}
