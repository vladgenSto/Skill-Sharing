package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Colaboracion;

public class ColaboracionValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return Colaboracion.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Colaboracion colaboracion=(Colaboracion) obj;
		if(!colaboracion.getPuntuacion().equals("--")){
			if (Integer.parseInt(colaboracion.getPuntuacion()) < 1 || Integer.parseInt(colaboracion.getPuntuacion())> 5) {
				errors.rejectValue("puntuacion", "obligatorio","Este campo tiene que estar entre 1 y 5");
			}
		}
		if(colaboracion.getHoras().trim().equals("--"))
			errors.rejectValue("horas", "obligatorio","Este campo es obligatorio");
		else if(colaboracion.getHoras().contains("-"))
			errors.rejectValue("horas", "obligatorio","Las horas no pueden ser negativas");
		if(colaboracion.getComentarios().trim().equals(""))
			errors.rejectValue("comentarios", "obligatorio","Este campo es obligatorio");
		if(colaboracion.getComentarios().length() > 200)
			errors.rejectValue("comentarios", "obligatorio","Este campo supera los 200 caracteres");
		if (colaboracion.getDescripcionDemanda().trim().equals(""))
			errors.rejectValue("descripcionDemanda", "obligatori", "Este campo es obligatorio");
		if (colaboracion.getDescripcionDemanda().length() > 200)
			errors.rejectValue("descripcionDemanda", "obligatori", "Este campo supera los 200 caracteres");
		if (colaboracion.getDescripcionOferta().trim().equals(""))
			errors.rejectValue("descripcionOferta", "obligatori", "Este campo es obligatorio");
		if (colaboracion.getDescripcionOferta().length() > 200)
			errors.rejectValue("descripcionOferta", "obligatori", "Este campo supera los 200 caracteres");
		
	}

}
