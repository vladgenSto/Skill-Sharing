package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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

import dao.ColaboracionDAO;
import dao.DemandaDAO;
import dao.EstudianteDAO;
import dao.HabilidadDAO;
import dao.OfertaDAO;
import domain.Colaboracion;
import domain.Demanda;
import domain.Estudiante;
import domain.Habilidad;
import domain.Mail;
import domain.Oferta;
import domain.UserDetails;

@Controller
@RequestMapping(value="/oferta")
public class OfertaController {

	private OfertaDAO ofertaDao;
	private HabilidadDAO habilidadDao;
	private EstudianteDAO estudianteDao;
	private DemandaDAO demandaDao;
	private ColaboracionDAO colaboracionDao;
	private Mail enviadorMail=new Mail();
	
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
	
	@Autowired
	public void setDemandaDao(DemandaDAO demandaDao){
		this.demandaDao=demandaDao;
	}
	
	@Autowired
	public void setColaboracionDao(ColaboracionDAO colaboracionDao){
		this.colaboracionDao=colaboracionDao;
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
	public String listarTodasLasOfertas(Model model,HttpSession session){
		UserDetails user=(UserDetails)session.getAttribute("user");
		if(user!=null){
			this.filtrarOfertas(new Date(), ofertaDao.getOfertas());
			model.addAttribute("ofertas",this.getOfertasPublicadasOtrosEstudiantes(user.getDniEstudiante()));
		}
		return "oferta/buscar";
	}
	
	@RequestMapping(value="/add")
	public String addOferta(Model model){
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyy");
		model.addAttribute("oferta",new Oferta());
		model.addAttribute("listaHabilidades", habilidadDao.getHabilidadesSinRepeticiones());
		Date fecha = new Date();
		model.addAttribute("fechaInicio", formato.format(fecha));
		int year = fecha.getYear() + 1;
		Date fecha2=new Date();
		fecha2.setYear(year);
		model.addAttribute("fechaFin", formato.format(fecha2));
		model.addAttribute("nombreHabilidad","---Elige---");
		model.addAttribute("nivelHabilidad","---Elige---");
		return "oferta/add";
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("oferta") Oferta oferta,BindingResult bindingResult,Model model,HttpSession session) throws AddressException, MessagingException{
		OfertaValidator ofertaValidator=new OfertaValidator();
		ofertaValidator.validate(oferta, bindingResult);
		if(bindingResult.hasErrors()){
			model.addAttribute("nombreHabilidad",oferta.getNombreHabilidad());
			model.addAttribute("nivelHabilidad",oferta.getNivelHabilidad());
			model.addAttribute("listaHabilidades", habilidadDao.getHabilidadesSinRepeticiones());
			return "oferta/add";
		}
		Habilidad existe=habilidadDao.getHabilidad(oferta.getNombreHabilidad(), oferta.getNivelHabilidad());
		if(existe == null){
			existe=new Habilidad();
			existe.setNombre(oferta.getNombreHabilidad());
			existe.setNivel(oferta.getNivelHabilidad());
			existe.setDescripcion(oferta.getNombreHabilidad()+", "+oferta.getNivelHabilidad());
			habilidadDao.addHabilidadIndividual(existe);
		}
		ofertaDao.addOferta(oferta);
		List<Demanda> listaDemandas=demandaDao.getDemandas();
		List<String> destinatarios=new ArrayList<String>();
		for(Demanda demanda:listaDemandas){
			if(demanda.getNombreHabilidad().equals(oferta.getNombreHabilidad())){
				Estudiante estudiante=estudianteDao.getEstudiante(demanda.getDniEstudiante());
				destinatarios.add(estudiante.getCorreo());
			}
		}
		if(!destinatarios.isEmpty()){
			enviadorMail.enviarMensaje("Nueva oferta creada", "Se ha creado una nueva oferta de "+oferta.getNombreHabilidad(), destinatarios);
		}
		session.setAttribute("numOfertas", ofertaDao.getOfertasUsuario(oferta.getDniEstudiante()).size());
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
		OfertaValidator ofertaValidator=new OfertaValidator();
		ofertaValidator.validate(oferta, bindingResult);
		if(bindingResult.hasErrors())
			return "oferta/update";
		ofertaDao.updateOferta(oferta);
		this.actualizaListaOfertas(session);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{codigoOferta}")
	public String processDelete(@PathVariable int codigoOferta, HttpSession session){
		Oferta oferta = ofertaDao.getOferta(codigoOferta);
		ofertaDao.deleteOferta(oferta);
		session.setAttribute("numOfertas", ofertaDao.getOfertasUsuario(oferta.getDniEstudiante()).size());
		this.actualizaListaOfertas(session);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/crearColaboracion/{codigoOferta}")
	public String crearColaboracion(@PathVariable int codigoOferta, Model model, HttpSession session) throws AddressException, MessagingException{
		Oferta oferta = ofertaDao.getOferta(codigoOferta);
		UserDetails user = (UserDetails) session.getAttribute("user");
		if (user != null) {
			Date fecha = new Date();
			int year = fecha.getYear() + 1;
			Date fecha2=new Date();
			fecha2.setYear(year);
			List<String> destinatarios=new ArrayList<String>();
			List<Demanda> demandasCompatibles = demandaDao.getDemandasCompatibles(user.getDniEstudiante(), oferta.getNombreHabilidad(), oferta.getNivelHabilidad());
			if (demandasCompatibles.isEmpty()) {
				Demanda nuevaDemanda = new Demanda();
				nuevaDemanda.setDescripcion(oferta.getDescripcion());
				nuevaDemanda.setDniEstudiante(user.getDniEstudiante());
				nuevaDemanda.setFechaInicio(fecha);
				nuevaDemanda.setFechaFin(fecha2);
				nuevaDemanda.setNombreHabilidad(oferta.getNombreHabilidad());
				nuevaDemanda.setNivelHabilidad(oferta.getNivelHabilidad());
				nuevaDemanda.setCodigoDemanda();
				demandaDao.addDemanda(nuevaDemanda);
				Colaboracion nuevaColaboracion = this.nuevaColaboracion(oferta, nuevaDemanda);
				colaboracionDao.addColaboracion(nuevaColaboracion);
				Estudiante estudianteOferta=estudianteDao.getEstudiante(oferta.getDniEstudiante());
				Estudiante estudianteDemanda=estudianteDao.getEstudiante(nuevaDemanda.getDniEstudiante());
				destinatarios.add(estudianteOferta.getCorreo());
				destinatarios.add(estudianteDemanda.getCorreo());
				enviadorMail.enviarMensaje("Nueva colaboracion", "Se ha creado una colaboracion con "+estudianteDemanda.getNombre()+" y "+estudianteOferta.getNombre(), destinatarios);
				session.setAttribute("numDemandas",demandaDao.getDemandasUsuario(user.getDniEstudiante()).size());
				session.setAttribute("numColaboraciones", colaboracionDao.getColaboracionesUsuario(user.getDniEstudiante()).size());
				return "redirect:/colaboracion/list.html";
			} else if (demandasCompatibles.size() == 1) {
				Demanda demanda = demandasCompatibles.get(0);
				Colaboracion nuevaColaboracion = this.nuevaColaboracion(oferta, demanda);
				if(colaboracionDao.getColaboracion(nuevaColaboracion.getCodigoOferta(), nuevaColaboracion.getCodigoDemanda()) == null)
					colaboracionDao.addColaboracion(nuevaColaboracion);
				Estudiante estudianteOferta=estudianteDao.getEstudiante(oferta.getDniEstudiante());
				Estudiante estudianteDemanda=estudianteDao.getEstudiante(demanda.getDniEstudiante());
				destinatarios.add(estudianteOferta.getCorreo());
				destinatarios.add(estudianteDemanda.getCorreo());
				enviadorMail.enviarMensaje("Nueva colaboracion", "Se ha creado una colaboracion con "+estudianteDemanda.getNombre()+" y "+estudianteOferta.getNombre(), destinatarios);
	        	session.setAttribute("numColaboraciones", colaboracionDao.getColaboracionesUsuario(user.getDniEstudiante()).size());
				return "redirect:/colaboracion/list.html";
			} else {
				session.setAttribute("oferta", oferta);
				session.setAttribute("listaDemandasCompatibles", demandasCompatibles);
				return "redirect:/demanda/demandasCompatibles.html";
			}
		}
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
	private Map<String, List<Oferta>> getOfertasPublicadasOtrosEstudiantes(String dniUsuario){
		List<Estudiante>otrosEstudiantes=estudianteDao.getEstudiantesDistintos(dniUsuario);
		HashMap<String,List<Oferta>> ofertasPublicadas=new HashMap<String,List<Oferta>>();
		for(Estudiante estudiante: otrosEstudiantes){
			if(!ofertasPublicadas.containsKey(estudiante.getNombre())){
				ofertasPublicadas.put(estudiante.getNombre(),ofertaDao.getOfertasUsuario(estudiante.getDni()));
			}
		}
		return ofertasPublicadas;
	}
	private Colaboracion nuevaColaboracion(Oferta oferta, Demanda demanda){
		Colaboracion nuevaColaboracion = new Colaboracion();
		nuevaColaboracion.setCodigoOferta(oferta.getCodigoOferta());
		nuevaColaboracion.setCodigoDemanda(demanda.getCodigoDemanda());
		nuevaColaboracion.setDescripcionOferta(oferta.getDescripcion());
		nuevaColaboracion.setDescripcionDemanda(demanda.getDescripcion());
		nuevaColaboracion.setHoras("--");
		nuevaColaboracion.setPuntuacion("--");
		nuevaColaboracion.setComentarios("--");
		Date fecha;
		if(oferta.getFechaInicio().before(demanda.getFechaInicio())){
			fecha=oferta.getFechaInicio();
		}else{
			fecha=demanda.getFechaInicio();
		}
		int year = fecha.getYear() + 1;
		Date fecha2=new Date();
		fecha2.setYear(year);
		nuevaColaboracion.setFechaInicio(fecha);
		nuevaColaboracion.setFechaFin(fecha2);
		return nuevaColaboracion;
	}
}
