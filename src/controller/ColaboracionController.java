package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.ColaboracionDAO;
import dao.DemandaDAO;
import dao.OfertaDAO;
import domain.Colaboracion;
import domain.UserDetails;

@Controller
@RequestMapping(value="/colaboracion")
public class ColaboracionController {

	private ColaboracionDAO colaboracionDao;
	private OfertaDAO ofertaDao;
	private DemandaDAO demandaDao;

	@Autowired
	public void setColaboracionDao(ColaboracionDAO colaboracionDao) {
		this.colaboracionDao = colaboracionDao;
	}
	
	@Autowired
	public void setOfertaDao(OfertaDAO ofertaDao){
		this.ofertaDao=ofertaDao;
	}
	
	@Autowired
	public void setDemandaDao(DemandaDAO demandaDao){
		this.demandaDao=demandaDao;
	}
	
	@RequestMapping(value="/listAdmin")
	public String listColaboracion(Model model) {
			model.addAttribute("colaboraciones", colaboracionDao.getColaboraciones());
		return "colaboracion/listAdmin";
	}
	
	@RequestMapping(value="/list")
	public String listColaboracion(Model model, HttpSession session) {
		UserDetails user=(UserDetails)session.getAttribute("user");
		if(user != null)
			model.addAttribute("colaboraciones", colaboracionDao.getColaboracionesUsuario(user.getDniEstudiante()));
		
		return "colaboracion/list";
	}
	
	@RequestMapping(value="/add/{codigoOferta}, {codigoDemanda}", method=RequestMethod.GET)
	public String addColaboracion(Model model,@PathVariable int codigoOferta, @PathVariable int codigoDemanda) {
		model.addAttribute("colaboracion", new Colaboracion());
		model.addAttribute("oferta",ofertaDao.getOferta(codigoOferta));
		model.addAttribute("demanda",demandaDao.getDemanda(codigoDemanda));
		SimpleDateFormat formato=new SimpleDateFormat("MM/dd/yyy");
		Date fecha=new Date();
		model.addAttribute("fechaInicio",formato.format(fecha));
		int year=fecha.getYear();
		year+=1;
		fecha.setYear(year);
		model.addAttribute("fechaFin",formato.format(fecha));
		return "colaboracion/add";
	}
	
	@RequestMapping(value="add/{codigoOferta}, {codigoDemanda}", method=RequestMethod.POST)
	public String processAddSubmit(@PathVariable int codigoOferta, @PathVariable int codigoDemanda,@ModelAttribute("colaboracion")Colaboracion colaboracion, BindingResult bindingResult, HttpSession session) {
		ColaboracionValidator cv=new ColaboracionValidator();
		cv.validate(colaboracion, bindingResult);
		if (bindingResult.hasErrors())
			return "colaboracion/add";
		Colaboracion comprobarColaboracion=colaboracionDao.getColaboracion(codigoOferta, codigoDemanda);
		if(comprobarColaboracion == null) {
			UserDetails user = (UserDetails) session.getAttribute("user");
			colaboracionDao.addColaboracion(colaboracion);
			session.setAttribute("numColaboraciones", colaboracionDao.getColaboracionesUsuario(user.getDniEstudiante()).size());
		}
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/update/{codigoOferta}, {codigoDemanda}", method=RequestMethod.GET)
	public String editColaboracion(Model model, @PathVariable int codigoOferta, @PathVariable int codigoDemanda) {
		model.addAttribute("colaboracion", colaboracionDao.getColaboracion(codigoOferta, codigoDemanda));
		SimpleDateFormat formato=new SimpleDateFormat("MM/dd/yyy");
		Date fecha=new Date();
		model.addAttribute("fechaFin",formato.format(fecha));
		return "colaboracion/update";
	}
	
	@RequestMapping(value="update/{codigoOferta}, {codigoDemanda}", method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable int codigoOferta, @PathVariable int codigoDemanda, @ModelAttribute("colaboracion")Colaboracion colaboracion, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "colaboracion/update";
		colaboracionDao.updateColaboracion(colaboracion);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{codigoOferta}, {codigoDemanda}")
	public String processDelete(@PathVariable int codigoOferta, @PathVariable int codigoDemanda, HttpSession session) {
		UserDetails user = (UserDetails) session.getAttribute("user");
		Colaboracion colaboracion = colaboracionDao.getColaboracion(codigoOferta, codigoDemanda);
		colaboracionDao.deleteColaboracion(colaboracion);
		session.setAttribute("numColaboraciones", colaboracionDao.getColaboracionesUsuario(user.getDniEstudiante()).size());
		return "redirect:../list.html";
	}
	
}
