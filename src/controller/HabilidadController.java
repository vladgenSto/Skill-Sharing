package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.HabilidadDAO;
import domain.Habilidad;

@Controller
@RequestMapping(value="/habilidad")
public class HabilidadController {

	private HabilidadDAO habilidadDao;
	
	@Autowired
	public void setHabilidadDao(HabilidadDAO habilidadDao){
		this.habilidadDao=habilidadDao;
	}
	
	@RequestMapping(value="/list")
	public String listHabilidad(Model model){
		model.addAttribute("habilidades",habilidadDao.getHabilidades());
		return "habilidad/list";
	}
	
	@RequestMapping(value="/add")
	public String addHabilidad(Model model){
		model.addAttribute("habilidad",new Habilidad());
		return "habilidad/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("habilidad")Habilidad habilidad,BindingResult bindingResult){
		HabilidadValidator hv=new HabilidadValidator();
		hv.validate(habilidad, bindingResult);
		if(bindingResult.hasErrors())
			return "habilidad/add";
		habilidadDao.addHabilidad(habilidad);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{nombre}_{nivel}",method=RequestMethod.GET)
	public String editHabilidad(Model model, @PathVariable String nombre,@PathVariable String nivel){
		model.addAttribute("habilidad",habilidadDao.getHabilidad(nombre, nivel));
		return "habilidad/update";
	}
	
	@RequestMapping(value="update/{nombre}_{nivel}",method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String nombre,@PathVariable String nivel,@ModelAttribute("habilidad") Habilidad habilidad, BindingResult bindingResult ){
		HabilidadValidator hv=new HabilidadValidator();
		hv.validate(habilidad, bindingResult);
		if(bindingResult.hasErrors())
			return "habilidad/update";
		habilidadDao.updateHabilidad(habilidad);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{nombre}_{nivel}")
	public String processDelete(@PathVariable String nombre,@PathVariable String nivel){
		habilidadDao.deleteHabilidad(habilidadDao.getHabilidad(nombre, nivel));;
		return "redirect:../list.html";
	}
}
