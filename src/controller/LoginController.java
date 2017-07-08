package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.ColaboracionDAO;
import dao.DemandaDAO;
import dao.EstudianteDAO;
import dao.OfertaDAO;
import dao.UserDAO;
import domain.Demanda;
import domain.Estudiante;
import domain.MetodosFecha;
import domain.Oferta;
import domain.UserDetails;

class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return UserDetails.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UserDetails user=(UserDetails) obj;
		if(user.getUsername().trim().equals(""))
			errors.rejectValue("username", "obligatorio","Este campo es obligatorio");
		if(user.getPassword().trim().equals(""))
			errors.rejectValue("password", "obligatorio","Este campo es obligatorio");		
	}
	
}

@Controller
public class LoginController {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private OfertaDAO ofertaDao;
	
	@Autowired
	private DemandaDAO demandaDao;
	
	@Autowired
	private EstudianteDAO estudianteDao;
	
	@Autowired
	private ColaboracionDAO colaboracionDao;
	
	
	@RequestMapping("/login")
	public String login(Model model){
		model.addAttribute("user",new UserDetails());
        this.ecriptarContrasenyas();
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") UserDetails user,BindingResult bindingResult,HttpSession session){
		UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors())
            return "login";
        
//		BasicPasswordEncryptor passwordEncryptor=new BasicPasswordEncryptor();
        user=userDao.loadUserByName(user.getUsername(), user.getPassword());
        if(user == null){
            bindingResult.rejectValue("password", "badpw","Usuario o contrasenya incorrectos");
            return "login";
        }
        session.setAttribute("user", user);
        if(user.getUsername().trim().equals("admin")) {
        	session.setAttribute("estudiante", estudianteDao.getEstudiante(user.getDniEstudiante()));
        	return "redirect:perfilAdmin.jsp";
        }else{
        	Estudiante est=estudianteDao.getEstudiante(user.getDniEstudiante());
        	if (est.getBaneado()){
        		return "redirect:paginaBaneado.jsp";
        	} else {
        	session.setAttribute("estudiante", est);
        	session.setAttribute("horasDadas", est.getHorasDadas());
        	session.setAttribute("horasRecibidas", est.getHorasRecibidas());
        	int saldo=est.getHorasDadas()-est.getHorasRecibidas();
        	session.setAttribute("saldo", saldo);
        	session.setAttribute("numColaboraciones", colaboracionDao.getColaboracionesUsuario(user.getDniEstudiante()).size());
            List<Demanda> listaDemandas=demandaDao.getDemandasUsuario(user.getDniEstudiante());//this.filtro(new Date(),demandaDao.getDemandasUsuario(user.getDniEstudiante()));
            List<Oferta> listaOfertas=ofertaDao.getOfertasUsuario(user.getDniEstudiante());//this.filtro(new Date(),  ofertaDao.getOfertasUsuario(user.getDniEstudiante()));
            session.setAttribute("listaOfertasUsuario", listaOfertas);
            session.setAttribute("numOfertas", listaOfertas.size());
            session.setAttribute("numDemandas", listaDemandas.size());
            if(!listaDemandas.isEmpty()){
                ArrayList<Oferta>listaOfertasRelacionadas=new ArrayList<Oferta>();
                for(Demanda demanda:listaDemandas){
                    List<Oferta>lOferta=this.ofertaDao.getOfertasRelacionadas(demanda.getNombreHabilidad(), user.getDniEstudiante());
                    for(Oferta oferta: lOferta){
                        listaOfertasRelacionadas.add(oferta);
                    }
                }
                session.setAttribute("listaOfertasRelacionadas", listaOfertasRelacionadas);
            }
            boolean baneoHoras=false;
            if (saldo < -20){
            	baneoHoras=true;
            	session.setAttribute("baneoHoras", baneoHoras);
            	return "redirect:perfilUsuarioBaneado.jsp";
            }else
            	return "redirect:perfilUsuario.jsp";
            }
        }
    }
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:index.jsp";
	}
	
//	private <T extends MetodosFecha> List<T> filtro(Date fecha,List<T> lista){
//		ArrayList<T> objetosValidos=new ArrayList<T>();
//		if(!lista.isEmpty()){
//			for(T elem:lista){
//				if(!elem.getFechaFin().before(fecha))
//					objetosValidos.add(elem);
//				else if(elem.getClass().equals(Oferta.class)){
//					if(!ofertaDao.deleteOferta((Oferta)elem))
//							objetosValidos.add(elem);
//				}else if(elem.getClass().equals(Demanda.class)){
//					if(!demandaDao.deleteDemanda((Demanda)elem))
//						objetosValidos.add(elem);
//				}
//			}
//		}
//		return objetosValidos;
//	}
	private void ecriptarContrasenyas(){
		BasicPasswordEncryptor passwordEncryptor=new BasicPasswordEncryptor();
		List<UserDetails> usuarios=userDao.getUsuarios();
		for(UserDetails usuario:usuarios){
			if(usuario.getPassword().length()<32){
				usuario.setPassword(passwordEncryptor.encryptPassword(usuario.getPassword()));
				userDao.updateUsuario(usuario);
			}
		}
	}
}
