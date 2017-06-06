package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
		SimpleDateFormat formato=new SimpleDateFormat("MM/dd/yyy");
		model.addAttribute("oferta",new Oferta());
		model.addAttribute("listaHabilidades", habilidadDao.getHabilidadesSinRepeticiones());
		Date fecha = new Date();
		model.addAttribute("fechaInicio", formato.format(fecha));
		int year = fecha.getYear() + 1;
		Date fecha2=new Date();
		fecha2.setYear(year);
		model.addAttribute("fechaFin", formato.format(fecha2));
		return "oferta/add";
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("oferta") Oferta oferta,BindingResult bindingResult,Model model,HttpSession session) throws AddressException, MessagingException{
		OfertaValidator ofertaValidator=new OfertaValidator();
		ofertaValidator.validate(oferta, bindingResult);
		if(bindingResult.hasErrors()){
			model.addAttribute("listaHabilidades", habilidadDao.getHabilidadesSinRepeticiones());
			return "oferta/add";
		}
		Date fecha = new Date();
		int year = fecha.getYear() + 1;
		Date fecha2=new Date();
		fecha2.setYear(year);
		oferta.setFechaInicio(fecha);
		oferta.setFechaFin(fecha2);
		ofertaDao.addOferta(oferta);
		Properties props=new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.user", "ei102716ps@gmail.com");
		props.setProperty("mail.smtp.user", "true");
		Session sesion = Session.getInstance(props, new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("ei102716ps@gmail.com", "perisstoyanov");
		    }
		});
		sesion.setDebug(true);
		MimeMessage message=new MimeMessage(sesion);
		message.setFrom(new InternetAddress("ei102716ps@gmail.com"));
		List<Demanda> listaDemandas=demandaDao.getDemandas();
		for(Demanda demanda:listaDemandas){
			if(demanda.getNombreHabilidad().equals(oferta.getNombreHabilidad())){
				Estudiante estudiante=estudianteDao.getEstudiante(demanda.getDniEstudiante());
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(estudiante.getCorreo()));
			}
		}
		message.setSubject("Nueva oferta creada");
		message.setText("Se ha creado una nueva oferta de "+oferta.getNombreHabilidad());
		Transport t=sesion.getTransport("smtp");
		t.connect("ei102716ps@gmail.com","perisstoyanov");
		t.sendMessage(message, message.getAllRecipients());
		t.close();
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
				Colaboracion nuevaColaboracion = new Colaboracion();
				nuevaColaboracion.setCodigoOferta(oferta.getCodigoOferta());
				nuevaColaboracion.setCodigoDemanda(nuevaDemanda.getCodigoDemanda());
				nuevaColaboracion.setDescripcionOferta(oferta.getDescripcion());
				nuevaColaboracion.setDescripcionDemanda(nuevaDemanda.getDescripcion());
				nuevaColaboracion.setHoras("--");
				nuevaColaboracion.setPuntuacion("--");
				nuevaColaboracion.setComentarios("--");
				nuevaColaboracion.setFechaInicio(fecha);
				nuevaColaboracion.setFechaFin(fecha2);
				colaboracionDao.addColaboracion(nuevaColaboracion);
				Properties props=new Properties();
				props.setProperty("mail.smtp.host", "smtp.gmail.com");
				props.setProperty("mail.smtp.starttls.enable", "true");
				props.setProperty("mail.smtp.port", "587");
				props.setProperty("mail.smtp.user", "ei102716ps@gmail.com");
				props.setProperty("mail.smtp.user", "true");
				Session sesion = Session.getInstance(props, new javax.mail.Authenticator() {
				    protected PasswordAuthentication getPasswordAuthentication() {
				        return new PasswordAuthentication("ei102716ps@gmail.com", "perisstoyanov");
				    }
				});
				sesion.setDebug(true);
				MimeMessage message=new MimeMessage(sesion);
				message.setFrom(new InternetAddress("ei102716ps@gmail.com"));
				Estudiante estudianteOferta=estudianteDao.getEstudiante(oferta.getDniEstudiante());
				Estudiante estudianteDemanda=estudianteDao.getEstudiante(nuevaDemanda.getDniEstudiante());
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(estudianteOferta.getCorreo()));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(estudianteDemanda.getCorreo()));
				message.setSubject("Nueva colaboración");
				message.setText("Se ha creado una colaboración con "+estudianteDemanda.getNombre()+" y "+estudianteOferta.getNombre());
				Transport t=sesion.getTransport("smtp");
				t.connect("ei102716ps@gmail.com","perisstoyanov");
				t.sendMessage(message, message.getAllRecipients());
				t.close();
				return "redirect:/colaboracion/list.html";
			} else if (demandasCompatibles.size() == 1) {
				Demanda demanda = demandasCompatibles.get(0);
				Colaboracion nuevaColaboracion = new Colaboracion();
				nuevaColaboracion.setCodigoOferta(oferta.getCodigoOferta());
				nuevaColaboracion.setCodigoDemanda(demanda.getCodigoDemanda());
				nuevaColaboracion.setDescripcionOferta(oferta.getDescripcion());
				nuevaColaboracion.setDescripcionDemanda(demanda.getDescripcion());
				nuevaColaboracion.setHoras("--");
				nuevaColaboracion.setPuntuacion("--");
				nuevaColaboracion.setComentarios("--");
				nuevaColaboracion.setFechaInicio(fecha);
				nuevaColaboracion.setFechaFin(fecha2);
				colaboracionDao.addColaboracion(nuevaColaboracion);
				Properties props=new Properties();
				props.setProperty("mail.smtp.host", "smtp.gmail.com");
				props.setProperty("mail.smtp.starttls.enable", "true");
				props.setProperty("mail.smtp.port", "587");
				props.setProperty("mail.smtp.user", "ei102716ps@gmail.com");
				props.setProperty("mail.smtp.user", "true");
				Session sesion = Session.getInstance(props, new javax.mail.Authenticator() {
				    protected PasswordAuthentication getPasswordAuthentication() {
				        return new PasswordAuthentication("ei102716ps@gmail.com", "perisstoyanov");
				    }
				});
				sesion.setDebug(true);
				MimeMessage message=new MimeMessage(sesion);
				message.setFrom(new InternetAddress("ei102716ps@gmail.com"));
				Estudiante estudianteOferta=estudianteDao.getEstudiante(oferta.getDniEstudiante());
				Estudiante estudianteDemanda=estudianteDao.getEstudiante(demanda.getDniEstudiante());
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(estudianteOferta.getCorreo()));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(estudianteDemanda.getCorreo()));
				message.setSubject("Nueva colaboración");
				message.setText("Se ha creado una colaboración con "+estudianteDemanda.getNombre()+" y "+estudianteOferta.getNombre());
				Transport t=sesion.getTransport("smtp");
				t.connect("ei102716ps@gmail.com","perisstoyanov");
				t.sendMessage(message, message.getAllRecipients());
				t.close();
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
	
	
}
