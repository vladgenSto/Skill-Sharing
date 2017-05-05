package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.HabilidadDAO;
import dao.OfertaDAO;
import dao.UserDAO;
import domain.Oferta;
import domain.UserDetails;

@Controller
@RequestMapping(value="/oferta")
public class OfertaController {

	private OfertaDAO ofertaDao;
	private HabilidadDAO habilidadDao;
	private UserDAO userDAO;
	
	@Autowired
	public void setOfertaDao(OfertaDAO ofertaDao){
		this.ofertaDao=ofertaDao;
	}
	
	@Autowired
	public void setHabilidadDao(HabilidadDAO habilidadDao) {
		this.habilidadDao = habilidadDao;
	}
	
	@Autowired
	public void setUserDao(UserDAO userDao) {
		this.userDAO = userDao;
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));   
	}
	
	@RequestMapping(value="/list")
	public String listOferta(Model model){
		model.addAttribute("ofertas",ofertaDao.getOfertas());
		return "oferta/list";
	}
	
	@RequestMapping(value="/add")
	public String addOferta(Model model){
		model.addAttribute("oferta",new Oferta());
		model.addAttribute("listaHabilidades", habilidadDao.getHabilidadesSinRepeticiones());
		return "oferta/add";
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("oferta")Oferta oferta,BindingResult bindingResult, HttpSession session){
		OfertaValidator ofertaValidator=new OfertaValidator();
		ofertaValidator.validate(oferta, bindingResult);
		if(bindingResult.hasErrors()){
			return "oferta/add";
		}
		ofertaDao.addOferta(oferta);
		this.actualizaListaOfertas(session);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{codigoOferta}",method=RequestMethod.GET)
	public String editOferta(Model model, @PathVariable String codigoOferta){
		model.addAttribute("oferta",ofertaDao.getOferta(codigoOferta));
		return "oferta/update";
	}
	
	@RequestMapping(value="update/{codigoOferta}",method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String codigoOferta,@ModelAttribute("oferta") Oferta oferta, BindingResult bindingResult, HttpSession session){
		if(bindingResult.hasErrors())
			return "oferta/update";
		ofertaDao.updateOferta(oferta);
		this.actualizaListaOfertas(session);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{codigoOferta}")
	public String processDelete(@PathVariable String codigoOferta, HttpSession session){
		ofertaDao.deleteOferta(ofertaDao.getOferta(codigoOferta));
		this.actualizaListaOfertas(session);
		return "redirect:../list.html";
	}
	
	private void actualizaListaOfertas(HttpSession session){
        UserDetails user=(UserDetails) session.getAttribute("user");
        List<Oferta>listaOfertas=ofertaDao.getOfertasUsuario(user.getDniEstudiante());
        session.setAttribute("listaOfertasUsuario", listaOfertas);
    }
}
