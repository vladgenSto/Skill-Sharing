package controller;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Habilidad;

public class HabilidadValidator implements Validator{
	
	
	@Override
	public boolean supports(Class<?> cls) {
		return Habilidad.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Habilidad habilidad=(Habilidad)obj;
		if(habilidad.getNombre().trim().equals(""))
			errors.rejectValue("nombre", "obligatorio","Este campo es obligatorio");
		if(habilidad.getNombre().length() > 30)
			errors.rejectValue("nombre", "obligatorio","Este campo supera los 30 caracteres");
		if(habilidad.getDescripcion().trim().equals(""))
			errors.rejectValue("descripcion", "obligatorio","Este campo es obligatorio");
		else if(habilidad.getDescripcion().length() > 100)
			errors.rejectValue("descripcion", "obligatorio","Este campo supera los 100 caracteres");
		else if (!habilidad.getDescripcion().contains(" "))
			errors.rejectValue("descripcion", "obligatorio", "Descripcion no valida");
		if (!habilidad.getDescripcion().contains(" "))
			errors.rejectValue("descripcion", "obligatori", "Descripcion no valida");
	}
	
}
