package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import dao.OfertaDAO;
import dao.UserDAO;
import domain.Demanda;
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
	private ColaboracionDAO colaboracionDao;
	
	@RequestMapping("/login")
	public String login(Model model){
		model.addAttribute("user",new UserDetails());
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") UserDetails user, BindingResult bindingResult,HttpSession session){
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors())
            return "login";
        
        user=userDao.loadUserByName(user.getUsername(), user.getPassword());
        if(user == null){
            bindingResult.rejectValue("password", "badpw","Usuario o contraseña incorrectos");
            return "login";
        }
        session.setAttribute("user", user);
        if(user.getUsername().trim().equals("admin"))
            return "redirect:indexAdmin.jsp";
        else{
            List<Demanda> listaDemandas=demandaDao.getDemandasUsuario(user.getDniEstudiante());
            session.setAttribute("listaOfertasUsuario", ofertaDao.getOfertasUsuario(user.getDniEstudiante()));
            session.setAttribute("listaDemandasUsuario", demandaDao.getDemandasUsuario(user.getDniEstudiante()));
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
            session.setAttribute("listaColaboracionesUsuario", colaboracionDao.getColaboracionesUsuario(user.getDniEstudiante()));
            return "redirect:indexUsuario.jsp";
            }
    }
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:indexAdmin.jsp";
	}
}
