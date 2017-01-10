package org.launchcode.librarian.controllers;

import java.util.List;

import org.launchcode.librarian.models.Post;
import org.launchcode.librarian.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LibrarianController extends AbstractController {

	@RequestMapping(value = "/")
	public String index(Model model){
		
		//fetches all users and passes to template
		List<User> allUsers = userDao.findAll();
		model.addAttribute("users", allUsers);		
		return "index";
	}
	
	@RequestMapping(value = "/librarian")
	public String librarianIndex(Model model) {
		
		// TODO - fetch posts and pass to template
		Iterable<Entry> allEntries = entryDao.findAll();
		model.addAttribute("entry", allEntries);		
		return "librarian";
	}
	
}
