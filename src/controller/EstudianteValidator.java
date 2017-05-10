package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import domain.Estudiante;

public class EstudianteValidator implements Validator {

	public boolean supports(Class<?> cls) {
		return Estudiante.class.equals(cls);
	}
	
	public void validate(Object obj, Errors errors) {
		Estudiante estudiante = (Estudiante) obj;
		if (estudiante.getDniEstudiante().trim().equals(""))
			errors.rejectValue("dni", "obligatori", "Este campo es obligatorio");
		if (estudiante.getDniEstudiante().length() > 9)
			errors.rejectValue("dni", "obligatori", "Este campo supera los 9 caracteres");
		
		if (estudiante.getNombre().trim().equals(""))
			errors.rejectValue("nombre", "obligatori", "Este campo es obligatorio");
		if (estudiante.getNombre().length() > 30)
			errors.rejectValue("nombre", "obligatori", "Este campo supera los 30 caracteres");
		
		if (estudiante.getLicenciatura().trim().equals(""))
			errors.rejectValue("licenciatura", "obligatori", "Este campo es obligatorio");
		if (estudiante.getNombre().length() > 50)
			errors.rejectValue("licenciatura", "obligatori", "Este campo supera los 50 caracteres");
		
		if (estudiante.getCurso().trim().equals(""))
			errors.rejectValue("curso", "obligatori", "Este campo es obligatorio");
		if (estudiante.getCurso().length() > 10)
			errors.rejectValue("curso", "obligatori", "Este campo supera los 10 caracteres");
		
		if (estudiante.getCorreo().trim().equals(""))
			errors.rejectValue("correo", "obligatori", "Este campo es obligatorio");
		if (estudiante.getCorreo().length() > 30)
			errors.rejectValue("correo", "obligatori", "Este campo supera los 30 caracteres");
	}
}
