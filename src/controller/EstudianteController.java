package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.EstudianteDAO;
import domain.Estudiante;
//
@Controller
@RequestMapping(value="/estudiante")
public class EstudianteController {
	
	private EstudianteDAO estudianteDao;
	
	@Autowired
	public void setEstudianteDao(EstudianteDAO estudianteDao){
		this.estudianteDao=estudianteDao;
	}
	
	@RequestMapping(value="/list")
	public String listEstudiante(Model model){
		model.addAttribute("estudiantes",estudianteDao.getEstudiantes());
		return "estudiante/list";
	}
	
	@RequestMapping(value="/add")
	public String addEstudiante(Model model){
		model.addAttribute("estudiante",new Estudiante());
		return "estudiante/add";
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("estudiante")Estudiante estudiante,BindingResult bindingResult) {
		EstudianteValidator estudianteValidaor = new EstudianteValidator();
		estudianteValidaor.validate(estudiante, estudianteDao, bindingResult);
		if(bindingResult.hasErrors())
			return "estudiante/add";
		estudianteDao.addEstudiante(estudiante);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{dni}",method=RequestMethod.GET)
	public String editEstudiante(Model model, @PathVariable String dni){
		model.addAttribute("estudiante",estudianteDao.getEstudiante(dni));
		return "estudiante/update";
	}
	
	@RequestMapping(value="update/{dni}",method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String dni,@ModelAttribute("estudiante") Estudiante estudiante, BindingResult bindingResult ){
		EstudianteValidator estudianteValidaor = new EstudianteValidator();
		estudianteValidaor.validate(estudiante, estudianteDao, bindingResult);
		if(bindingResult.hasErrors())
			return "estudiante/update";
		estudianteDao.updateEstudiante(estudiante);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{dni}")
	public String processDelete(@PathVariable String dni){
		estudianteDao.deleteEstudiante(estudianteDao.getEstudiante(dni));
		return "redirect:../list.html";
	}

}
