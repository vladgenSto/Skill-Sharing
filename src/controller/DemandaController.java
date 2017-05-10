package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import dao.DemandaDAO;
import dao.HabilidadDAO;
import dao.OfertaDAO;
import domain.Demanda;
import domain.Oferta;
import domain.UserDetails;


@Controller
@RequestMapping(value="/demanda")
public class DemandaController {

	private DemandaDAO demandaDao;
	private HabilidadDAO habilidadDao;
	private OfertaDAO ofertaDao;
	
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
	
	@InitBinder     
	 public void initBinder(WebDataBinder binder){
	      binder.registerCustomEditor(       Date.class,     
	                          new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));   
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
		model.addAttribute("fechaFin", formato.format(fecha));
		return "demanda/add";
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("demanda")Demanda demanda,BindingResult bindingResult, HttpSession session){
		DemandaValidator demandaValidator = new DemandaValidator();
		demandaValidator.validate(demanda, bindingResult);
		if(bindingResult.hasErrors()){
			System.out.println(1);
			return "demanda/add";
		}
		demandaDao.addDemanda(demanda);
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
		model.addAttribute("listaOfertasRelacionadas", ofertaDao.getOfertasRelacionadas(demanda.getNombreHabilidad(), demanda.getDniEstudiante()));
		return "demanda/buscar";
	}
	
	@RequestMapping(value="/delete/{codigoDemanda}")
	public String processDelete(@PathVariable int codigoDemanda, HttpSession session){
		demandaDao.deleteDemanda(demandaDao.getDemanda(codigoDemanda));
		this.actualizaListaDemandas(session);
		this.actualizaListaOfertasRelacionadas(session);
		return "redirect:../list.html";
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
}
