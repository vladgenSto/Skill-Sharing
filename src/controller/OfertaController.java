package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import dao.EstudianteDAO;
import dao.HabilidadDAO;
import dao.OfertaDAO;
import domain.Estudiante;
import domain.Oferta;
import domain.UserDetails;

@Controller
@RequestMapping(value="/oferta")
public class OfertaController {

	private OfertaDAO ofertaDao;
	private HabilidadDAO habilidadDao;
	private EstudianteDAO estudianteDao;
	
	@Autowired
	public void setOfertaDao(OfertaDAO ofertaDao){
		this.ofertaDao=ofertaDao;
	}
	
	@Autowired
	public void setHabilidadDao(HabilidadDAO habilidadDao) {
		this.habilidadDao = habilidadDao;
	}
	
	@Autowired
	public void setEstudianteDao(EstudianteDAO estudianteDao){
		this.estudianteDao=estudianteDao;
	}
	
	
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));   
	}
	
	@RequestMapping(value="/listAdmin")
	public String listOfertaAdmin(Model model){
			this.filtrarOfertas(new Date(), ofertaDao.getOfertas());
			model.addAttribute("listaOfertas",this.getOfertasByEstudiantes());
		return "oferta/listAdmin";
	}
	
	@RequestMapping(value="/list")
	public String listOferta(Model model, HttpSession session){
		UserDetails user=(UserDetails)session.getAttribute("user");
		if(user != null){
			List<Oferta> ofertasValidasUsuarios=this.filtrarOfertas(new Date(), ofertaDao.getOfertasUsuario(user.getDniEstudiante()));
			model.addAttribute("listaOfertasUsuario",ofertasValidasUsuarios);
		}
		return "oferta/list";
	}
	
	@RequestMapping(value="/buscar")
	public String listarTodasLasOfertas(Model model){
		this.filtrarOfertas(new Date(), ofertaDao.getOfertas());
		model.addAttribute("ofertas",this.getOfertasByEstudiantes());
		return "oferta/buscar";
	}
	
	@RequestMapping(value="/add")
	public String addOferta(Model model){
		SimpleDateFormat formato=new SimpleDateFormat("MM/dd/yyy");
		model.addAttribute("oferta",new Oferta());
		model.addAttribute("listaHabilidades", habilidadDao.getHabilidadesSinRepeticiones());
		Date fecha = new Date();
		model.addAttribute("fechaInicio", formato.format(fecha));
		int year = fecha.getYear() + 1;
		fecha.setYear(year);
		model.addAttribute("fechaFin", formato.format(fecha));
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
	public String editOferta(Model model, @PathVariable int codigoOferta){
		model.addAttribute("oferta",ofertaDao.getOferta(codigoOferta));
		model.addAttribute("listaHabilidades", habilidadDao.getHabilidadesSinRepeticiones());
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
	public String processDelete(@PathVariable int codigoOferta, HttpSession session){
		ofertaDao.deleteOferta(ofertaDao.getOferta(codigoOferta));
		this.actualizaListaOfertas(session);
		return "redirect:../list.html";
	}
	
	private void actualizaListaOfertas(HttpSession session){
        UserDetails user=(UserDetails) session.getAttribute("user");
        List<Oferta>listaOfertas=ofertaDao.getOfertasUsuario(user.getDniEstudiante());
        session.setAttribute("listaOfertasUsuario", listaOfertas);
    }
	
	private List<Oferta> filtrarOfertas(Date fecha,List<Oferta>listaOfertas){
		ArrayList<Oferta>ofertasValidas=new ArrayList<Oferta>();
		if(!listaOfertas.isEmpty()){
		for(Oferta oferta:listaOfertas){
			if(!oferta.getFechaFin().before(fecha))
				ofertasValidas.add(oferta);
			else if(!ofertaDao.deleteOferta(oferta))
				ofertasValidas.add(oferta);
		}
		}
		return ofertasValidas;
	}
	private Map<String, List<Oferta>> getOfertasByEstudiantes() {
		List<Estudiante> listaEstudiantes=estudianteDao.getEstudiantes();
		HashMap<String,List<Oferta>> ofertasPorEstudiante=new HashMap<String,List<Oferta>>();
		for(Estudiante estudiante: listaEstudiantes){
			if(!ofertasPorEstudiante.containsKey(estudiante.getNombre())){
				ofertasPorEstudiante.put(estudiante.getNombre(), ofertaDao.getOfertasUsuario(estudiante.getDni()));
			}
		}
		return ofertasPorEstudiante;
	}
	
	
}
