package controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


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
import dao.EstudianteDAO;
import dao.HabilidadDAO;
import dao.OfertaDAO;
import domain.CalculadorEstadisticas;
import domain.Colaboracion;
import domain.Demanda;
import domain.Estudiante;
import domain.Oferta;
import domain.UserDetails;

@Controller
@RequestMapping(value="/colaboracion")
public class ColaboracionController {

	private ColaboracionDAO colaboracionDao;
	private OfertaDAO ofertaDao;
	private DemandaDAO demandaDao;
	private EstudianteDAO estudianteDao;
	private HabilidadDAO habilidadDao;

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
	
	@Autowired
	public void setEstudianteDao(EstudianteDAO estudianteDao){
		this.estudianteDao=estudianteDao;
	}
	
	@Autowired
	public void setHabilidadDao(HabilidadDAO habilidadDao) {
		this.habilidadDao = habilidadDao;
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
	
	@RequestMapping(value="/update/{codigoOferta}, {codigoDemanda}", method=RequestMethod.GET)
	public String editColaboracion(Model model, @PathVariable int codigoOferta, @PathVariable int codigoDemanda, HttpSession session) {
		Colaboracion colaboracion=colaboracionDao.getColaboracion(codigoOferta, codigoDemanda);
		model.addAttribute("colaboracion", colaboracion);
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
		Date fecha=new Date();
		session.setAttribute("fechaInicioColaboracion",formato.format(colaboracion.getFechaInicio()));
		session.setAttribute("fechaFinColaboracion",formato.format(fecha));
		session.setAttribute("horas",colaboracion.getHoras());
		session.setAttribute("puntuacion",colaboracion.getPuntuacion());
		return "colaboracion/update";
	}
	
	@RequestMapping(value="update/{codigoOferta}, {codigoDemanda}", method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable int codigoOferta, @PathVariable int codigoDemanda, @ModelAttribute("colaboracion")Colaboracion colaboracion, BindingResult bindingResult,HttpSession session) {
		ColaboracionValidator colaboracionValidador = new ColaboracionValidator();
		colaboracionValidador.validate(colaboracion, bindingResult);
		if (bindingResult.hasErrors()){
			return "colaboracion/update";
		}
		colaboracionDao.updateColaboracion(colaboracion);
		Oferta ofertaEstudiante=ofertaDao.getOferta(codigoOferta);
		Demanda demandaEstudiante=demandaDao.getDemanda(codigoDemanda);
		Estudiante estDaHoras=estudianteDao.getEstudiante(ofertaEstudiante.getDniEstudiante());
		int horasDadas=estDaHoras.getHorasDadas()+Integer.parseInt(colaboracion.getHoras());
		estDaHoras.setHorasDadas(horasDadas);
		estudianteDao.updateHorasDadasEstudiante(estDaHoras);
		Estudiante estRecibeHoras=estudianteDao.getEstudiante(demandaEstudiante.getDniEstudiante());
		int horasRecibidas=estRecibeHoras.getHorasRecibidas()+Integer.parseInt(colaboracion.getHoras());
		estRecibeHoras.setHorasRecibidas(horasRecibidas);
		estudianteDao.updateHorasRecibidasEstudiante(estRecibeHoras);
		UserDetails user=(UserDetails)session.getAttribute("user");
		Estudiante est=estudianteDao.getEstudiante(user.getDniEstudiante());
		if(user.getDniEstudiante().equals(estDaHoras.getDni()))
			session.setAttribute("horasDadas", horasDadas);
		else if(user.getDniEstudiante().equals(estRecibeHoras.getDni()))
			session.setAttribute("horasRecibidas", horasRecibidas);
		int saldo=est.getHorasDadas()-est.getHorasRecibidas();
		boolean baneoHoras=false;
		session.setAttribute("saldo", saldo);
		if(saldo < -20)
			baneoHoras=true;
		session.setAttribute("baneoHoras", baneoHoras);
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
	
	@RequestMapping(value="/estadisticas")
	public String estadisticas(Model model, HttpSession session) {
			model.addAttribute("colaboraciones", colaboracionDao.getColaboraciones());
			CalculadorEstadisticas calculador=new CalculadorEstadisticas();
			Map<Integer,Integer> colaboracionesPorAnyo=calculador.colaboracionesPorAnyo(colaboracionDao.getColaboraciones());
			model.addAttribute("estadisticasMes",colaboracionesPorAnyo);
			Map<String,Integer> colaboracionesHabilidad = calculador.colaboracionesPorHabilidad(colaboracionDao.getColaboraciones(), habilidadDao.getHabilidadesSinRepeticiones(), ofertaDao);
			model.addAttribute("estadisticasHabilidades", colaboracionesHabilidad);
			return "colaboracion/estadisticas";
			
	}
	
}
