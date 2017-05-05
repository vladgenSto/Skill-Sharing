package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.ColaboracionDAO;
import domain.Colaboracion;

@Controller
@RequestMapping(value="/colaboracion")
public class ColaboracionController {

	private ColaboracionDAO colaboracionDao;

	@Autowired
	public void setColaboracionDao(ColaboracionDAO colaboracionDao) {
		this.colaboracionDao = colaboracionDao;
	}
	
	@RequestMapping(value="/list")
	public String listColaboracion(Model model) {
		model.addAttribute("colaboraciones", colaboracionDao.getColaboraciones());
		return "colaboracion/list";
	}
	
	@RequestMapping(value="/add")
	public String addColaboracion(Model model) {
		model.addAttribute("colaboracion", new Colaboracion());
		return "colaboracion/add";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("colaboracion")Colaboracion colaboracion, BindingResult bindingResult) {
		ColaboracionValidator cv=new ColaboracionValidator();
		cv.validate(colaboracion, bindingResult);
		if (bindingResult.hasErrors())
			return "colaboracion/add";
		colaboracionDao.addColaboracion(colaboracion);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{codigoOferta}, {codigoDemanda}", method=RequestMethod.GET)
	public String editColaboracion(Model model, @PathVariable String codigoOferta, @PathVariable String codigoDemanda) {
		model.addAttribute("colaboracion", colaboracionDao.getColaboracion(codigoOferta, codigoDemanda));
		return "colaboracion/update";
	}
	
	@RequestMapping(value="update/{codigoOferta}, {codigoDemanda}", method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String codigoOferta, @PathVariable String codigoDemanda, @ModelAttribute("colaboracion")Colaboracion colaboracion, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "colaboracion/update";
		colaboracionDao.updateColaboracion(colaboracion);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{codigoOferta}, {codigoDemanda}")
	public String processDelete(@PathVariable String codigoOferta, @PathVariable String codigoDemanda) {
		colaboracionDao.deleteColaboracion(colaboracionDao.getColaboracion(codigoOferta, codigoDemanda));
		return "redirect:../list.html";
	}
	
}
