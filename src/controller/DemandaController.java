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
@RequestMapping(value="/demanda")
public class DemandaController {

	private DemandaDAO demandaDao;
	private HabilidadDAO habilidadDao;
	private OfertaDAO ofertaDao;
	private EstudianteDAO estudianteDao;
	private ColaboracionDAO colaboracionDao;
	
	@Autowired
	public void setDemandaDao(DemandaDAO demandaDao){
		this.demandaDao=demandaDao;
	}
	
	@Autowired
	public void setHabilidadDao(HabilidadDAO habilidadDao){
		this.habilidadDao = habilidadDao;
	}
	
	@Autowired
	public void setOfertaDao(OfertaDAO ofertaDao) {
		this.ofertaDao = ofertaDao;
	}
	
	@Autowired
	public void setEstudianteDao(EstudianteDAO estudianteDao){
		this.estudianteDao=estudianteDao;
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
	public String listDemanda(Model model){
			this.filtrarDemandas(new Date(), demandaDao.getDemandas());
			model.addAttribute("listaDemandas",this.getOfertasByEstudiantes());
		return "demanda/listAdmin";
	}
	
	@RequestMapping(value="/list")
	public String listDemanda(Model model,HttpSession session){
		UserDetails user=(UserDetails)session.getAttribute("user");
		if(user != null){
			List<Demanda> demandasValidasUsuario=this.filtrarDemandas(new Date(), demandaDao.getDemandasUsuario(user.getDniEstudiante()));
			model.addAttribute("listaDemandasUsuario",demandasValidasUsuario);
		}
		return "demanda/list";
	}
	
	@RequestMapping(value="/add")
	public String addDemanda(Model model){
		SimpleDateFormat formato=new SimpleDateFormat("MM/dd/yyy");
		model.addAttribute("demanda",new Demanda());
		model.addAttribute("listaHabilidades", habilidadDao.getHabilidadesSinRepeticiones());
		Date fecha = new Date();
		model.addAttribute("fechaInicio", formato.format(fecha));
		int year = fecha.getYear() + 1;
		fecha.setYear(year);
		Date fecha2=new Date();
		fecha2.setYear(year);
		model.addAttribute("fechaFin", formato.format(fecha));
		return "demanda/add";
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("demanda")Demanda demanda,BindingResult bindingResult, HttpSession session){
		DemandaValidator demandaValidator = new DemandaValidator();
		demandaValidator.validate(demanda, bindingResult);
		if(bindingResult.hasErrors()){
			return "demanda/add";
		}
		Date fecha = new Date();
		int year = fecha.getYear() + 1;
		Date fecha2=new Date();
		fecha2.setYear(year);
		demanda.setFechaInicio(fecha);
		demanda.setFechaFin(fecha2);
		demandaDao.addDemanda(demanda);
		session.setAttribute("numDemandas", demandaDao.getDemandasUsuario(demanda.getDniEstudiante()).size());
		this.actualizaListaDemandas(session);
		this.actualizaListaOfertasRelacionadas(session);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{codigoDemanda}",method=RequestMethod.GET)
	public String editDemanda(Model model, @PathVariable int codigoDemanda){
		model.addAttribute("demanda",demandaDao.getDemanda(codigoDemanda));
		model.addAttribute("listaHabilidades", habilidadDao.getHabilidadesSinRepeticiones());
		return "demanda/update";
	}
	
	@RequestMapping(value="update/{codigoDemanda}",method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable int codigoDemanda,@ModelAttribute("demanda") Demanda demanda, BindingResult bindingResult, HttpSession session){
		if(bindingResult.hasErrors())
			return "demanda/update";
		demandaDao.updateDemanda(demanda);
		this.actualizaListaDemandas(session);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/buscar/{codigoDemanda}",method=RequestMethod.GET)
	public String buscarOfertas(Model model, @PathVariable int codigoDemanda){
		Demanda demanda=demandaDao.getDemanda(codigoDemanda);
		model.addAttribute("demanda",demanda);
		model.addAttribute("listaOfertasRelacionadas", this.getOfertasByEstudiante(demanda.getDniEstudiante(), demanda.getNombreHabilidad()));
		return "demanda/buscar";
	}
	
	@RequestMapping(value="/delete/{codigoDemanda}")
	public String processDelete(@PathVariable int codigoDemanda, HttpSession session){
		Demanda demanda = demandaDao.getDemanda(codigoDemanda);
		demandaDao.deleteDemanda(demanda);
		session.setAttribute("numDemandas", demandaDao.getDemandasUsuario(demanda.getDniEstudiante()).size());
		this.actualizaListaDemandas(session);
		this.actualizaListaOfertasRelacionadas(session);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/demandasCompatibles")
	public String listDemandaCompatible(Model model,HttpSession session){
		return "demanda/demandasCompatibles";
	}
	
	@RequestMapping(value="/crearColaboracion/{codigoOferta}, {codigoDemanda}")
	public String crearColaboracion(@PathVariable int codigoOferta, @PathVariable int codigoDemanda, HttpSession session) throws AddressException, MessagingException{
		Demanda demanda = demandaDao.getDemanda(codigoDemanda);
		Oferta oferta = ofertaDao.getOferta(codigoOferta);
		Colaboracion nuevaColaboracion = new Colaboracion();
		nuevaColaboracion.setCodigoOferta(oferta.getCodigoOferta());
		nuevaColaboracion.setCodigoDemanda(demanda.getCodigoDemanda());
		nuevaColaboracion.setDescripcionOferta(oferta.getDescripcion());
		nuevaColaboracion.setDescripcionDemanda(demanda.getDescripcion());
		nuevaColaboracion.setHoras("--");
		nuevaColaboracion.setPuntuacion("--");
		nuevaColaboracion.setComentarios("--");
		Date fecha = new Date();
		int year = fecha.getYear() + 1;
		Date fecha2=new Date();
		fecha2.setYear(year);
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
	}
	
	private void actualizaListaDemandas(HttpSession session){
        UserDetails user=(UserDetails)session.getAttribute("user");
        if(user != null){
            List<Demanda>listaDemandas=demandaDao.getDemandasUsuario(user.getDniEstudiante());
            session.setAttribute("listaDemandasUsuario", listaDemandas);
        }
    }
	
	private void actualizaListaOfertasRelacionadas(HttpSession session){
        UserDetails user=(UserDetails) session.getAttribute("user");
        if(user != null){
            List<Demanda> listaDemandas = demandaDao.getDemandasUsuario(user.getDniEstudiante());
            if (!listaDemandas.isEmpty()) {
                ArrayList<Oferta> listaOfertasRelacionadas = new ArrayList<Oferta>();
                ArrayList<String> listaHabilidadesYaBuscadas = new ArrayList<String>();
                for (Demanda demanda : listaDemandas) {
                    if (!listaHabilidadesYaBuscadas.contains(demanda.getNombreHabilidad())) {
                        listaHabilidadesYaBuscadas.add(demanda.getNombreHabilidad());
                        List<Oferta> lOferta = this.ofertaDao.getOfertasRelacionadas(demanda.getNombreHabilidad(),
                                user.getDniEstudiante());
                        for (Oferta oferta : lOferta) {
                            listaOfertasRelacionadas.add(oferta);
                        }
                    }
                }
                session.setAttribute("listaOfertasRelacionadas", listaOfertasRelacionadas);
            }else{
                session.setAttribute("listaOfertasRelacionadas", new ArrayList<Oferta>());

            }
        }
    }
	private List<Demanda> filtrarDemandas(Date fecha,List<Demanda>listaDemandas){
		ArrayList<Demanda>demandasValidas=new ArrayList<Demanda>();
		if(!listaDemandas.isEmpty()){
		for(Demanda demanda:listaDemandas){
			if(!demanda.getFechaFin().before(fecha))
				demandasValidas.add(demanda);
			else if(!demandaDao.deleteDemanda(demanda))
				demandasValidas.add(demanda);
		}
		}
		return demandasValidas;
	}
	private Map<String, List<Oferta>> getOfertasByEstudiante(String dni,String habilidad) {
		List<Estudiante> listaEstudiantes=estudianteDao.getEstudiantesDistintos(dni);
		HashMap<String,List<Oferta>> ofertasPorEstudiante=new HashMap<String,List<Oferta>>();
		for(Estudiante estudiante: listaEstudiantes){
			if(!ofertasPorEstudiante.containsKey(estudiante.getNombre())){
				ofertasPorEstudiante.put(estudiante.getNombre(), ofertaDao.getOfertasRelacionadas(habilidad, estudiante.getDni()));
			}
		}
		return ofertasPorEstudiante;
	}
	private Map<String, List<Demanda>> getOfertasByEstudiantes() {
		List<Estudiante> listaEstudiantes=estudianteDao.getEstudiantes();
		HashMap<String,List<Demanda>> ofertasPorEstudiante=new HashMap<String,List<Demanda>>();
		for(Estudiante estudiante: listaEstudiantes){
			if(!ofertasPorEstudiante.containsKey(estudiante.getNombre())){
				ofertasPorEstudiante.put(estudiante.getNombre(), demandaDao.getDemandasUsuario(estudiante.getDni()));
			}
		}
		return ofertasPorEstudiante;
	}
}
