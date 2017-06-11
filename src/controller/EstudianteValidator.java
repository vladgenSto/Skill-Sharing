package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dao.EstudianteDAO;
import domain.Estudiante;

public class EstudianteValidator {

	public boolean supports(Class<?> cls) {
		return Estudiante.class.equals(cls);
	}
	
	public void validate(Object obj, EstudianteDAO estudianteDao, Errors errors) {
		Estudiante estudiante = (Estudiante) obj;
		Estudiante otroEstudiante = estudianteDao.getEstudiante(estudiante.getDni());
		if (otroEstudiante != null) {
			errors.rejectValue("dni", "obligatorio", "Este estudiante ya esta en la base de datos");
		}
		if (estudiante.getDni().trim().equals(""))
			errors.rejectValue("dni", "obligatori", "Este campo es obligatorio");
		if (estudiante.getDni().length() > 9)
			errors.rejectValue("dni", "obligatori", "Este campo supera los 9 caracteres");
		
		if (estudiante.getNombre().trim().equals(""))
			errors.rejectValue("nombre", "obligatori", "Este campo es obligatorio");
		if (estudiante.getNombre().length() > 30)
			errors.rejectValue("nombre", "obligatori", "Este campo supera los 30 caracteres");
		
		if (estudiante.getLicenciatura().trim().equals(""))
			errors.rejectValue("licenciatura", "obligatori", "Este campo es obligatorio");
		if (estudiante.getLicenciatura().length() > 50)
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
