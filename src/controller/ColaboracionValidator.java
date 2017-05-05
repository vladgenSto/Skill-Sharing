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
		if(colaboracion.getCodigoOferta().trim().equals("")){
			errors.rejectValue("codigoOferta", "obligatorio","Este campo es obligatorio");
		}
		if(colaboracion.getCodigoDemanda().trim().equals("")){
			errors.rejectValue("codigoDemanda", "obligatorio","Este campo es obligatorio");
		}
		if (colaboracion.getPuntuacion() < 1 || colaboracion.getPuntuacion() > 5) {
			errors.rejectValue("puntuacion", "obligatorio","Este campo tiene que estar entre 1 y 5");
		}
		if(colaboracion.getComentarios().trim().equals("")){
			errors.rejectValue("comentarios", "obligatorio","Este campo es obligatorio");
		}
	}

}
