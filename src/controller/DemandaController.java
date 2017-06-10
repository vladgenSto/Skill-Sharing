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
import domain.Mail;
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
	private Mail enviadorMail=new Mail();
	
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
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyy");
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
	public String processAddSubmit(@ModelAttribute("demanda")Demanda demanda,BindingResult bindingResult, HttpSession session) throws AddressException, MessagingException{
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
		List<Oferta> listaOfertas=ofertaDao.getOfertas();
		List<String> destinatarios=new ArrayList<String>();
		for(Oferta oferta:listaOfertas){
			if(demanda.getNombreHabilidad().equals(oferta.getNombreHabilidad())){
				Estudiante estudiante=estudianteDao.getEstudiante(oferta.getDniEstudiante());
				destinatarios.add(estudiante.getCorreo());
			}
		}
		if(!destinatarios.isEmpty()){
			enviadorMail.enviarMensaje("Nueva demanda creada", "Se ha creado una nueva demanda de "+demanda.getNombreHabilidad(), destinatarios);
		}
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
		Colaboracion nuevaColaboracion = this.nuevaColaboración(oferta, demanda);
		if(colaboracionDao.getColaboracion(nuevaColaboracion.getCodigoOferta(), nuevaColaboracion.getCodigoDemanda()) == null)
			colaboracionDao.addColaboracion(nuevaColaboracion);
		Estudiante estudianteOferta=estudianteDao.getEstudiante(oferta.getDniEstudiante());
		Estudiante estudianteDemanda=estudianteDao.getEstudiante(demanda.getDniEstudiante());
		List<String> destinatarios=new ArrayList<String>();
		destinatarios.add(estudianteOferta.getCorreo());
		destinatarios.add(estudianteDemanda.getCorreo());
		enviadorMail.enviarMensaje("Nueva colaboración", "Se ha creado una colaboración con "+estudianteDemanda.getNombre()+" y "+estudianteOferta.getNombre(), destinatarios);
		return "redirect:/colaboracion/list.html";
	}
	private Colaboracion nuevaColaboración(Oferta oferta, Demanda demanda){
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
		return nuevaColaboracion;
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
