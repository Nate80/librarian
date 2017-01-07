package org.launchcode.librarian.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.librarian.models.User;
import org.launchcode.librarian.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {
		
		//Requests a user signup
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify"); //uses verification from template "signup.html"
		
		if(!password.equals(verify)){
			model.addAttribute("verify_error", "The passwords do not match.");
			model.addAttribute("username", username);
			return "signup";
		}
		
		else {
			
		if(!User.isValidUsername(username)){  
			  model.addAttribute("username_error", "Invalid username.");
			  model.addAttribute("username", username);
			  return "signup";
		  }
		  else if(!User.isValidPassword(password)){  
			  model.addAttribute("password_error","Invalid password.");
			  model.addAttribute("username", username);
			  return "signup";
		  } else {  //if everything works as it should
		
		User newUser = new User(username, password);
		userDao.save(newUser);
		HttpSession thisSession =  request.getSession();
		this.setUserInSession(thisSession, newUser);
		return "redirect:librarian/newentry";
		}
	}
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		
		//implements login
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.findByUsername(username);
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		//boolean checkUser = username.equals(user);
		
		//if(checkUser != true) 
		
		if(user == null) //changed this from the boolean, seems to work better this way
		{
			model.addAttribute("error", "This is not a valid entry.");
			return "login";
		}
		if (user.isMatchingPassword(password) != true) {
				model.addAttribute("error", "Please enter a valid username and a valid password");
				model.addAttribute("username", username);
				return "login";
			}
		
		
			HttpSession thisSession = request.getSession();
			this.setUserInSession(thisSession, user);
			return "redirect:librarian/newentry";
		}
			
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/";
	}
}
