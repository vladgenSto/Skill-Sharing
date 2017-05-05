package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.UserDAO;
import domain.UserDetails;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserDAO userDao;
	
	@Autowired
	public void setUserDao(UserDAO userDao){
		this.userDao=userDao;
	}
	
	@RequestMapping("/list.html")
	public String listUsuarios(HttpSession session,Model model){
		if(session.getAttribute("user") == null){
			model.addAttribute("user",new UserDetails());
			return "login";
		}
		model.addAttribute("users",userDao.getUsuarios());
		return "user/list";
	}
}
