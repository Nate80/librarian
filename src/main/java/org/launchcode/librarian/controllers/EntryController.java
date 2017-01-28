package org.launchcode.librarian.controllers;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.librarian.models.Entry;
import org.launchcode.librarian.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EntryController extends AbstractController {

	@RequestMapping(value = "/librarian/newentry", method = RequestMethod.GET)
	public String newEntryForm() {
		return "newentry";
	}
	
	@RequestMapping(value = "/librarian/newentry", method = RequestMethod.POST)
	public String newEntry(HttpServletRequest request, Model model) {
		String body = request.getParameter("body");
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String creator = request.getParameter("creator");
		HttpSession thisSession = request.getSession();
		User author = this.getUserFromSession(thisSession);
        
		//if title of the entry is missing, then error message is displayed
		if(title == "" || title == null){
			model.addAttribute("error", "Title required.");
			return "newentry";
		}
		
		//need to rename body, should this refer to author, or summarization of the work?
		else if(body == "" || body == null){
			model.addAttribute("error", "Content required.");
			model.addAttribute("title", title);
			return "newentry";
		}
		
		else if(genre =="" || genre == null) {
			model.addAttribute("error", "Genre required.");
			model.addAttribute("genre", genre);
			return "newentry";
		}
		
		else if(creator == "" || creator == null) {
			model.addAttribute("error", "Author required.");
			model.addAttribute("creator", creator);
			return "newentry";
		}
		else{//if title and body != null & != ""
			Entry newEntry = new Entry(title, body, title, title, author);
			entryDao.save(newEntry);
			int entryUid = newEntry.getUid();
			return "redirect:" + newEntry.getAuthor().getUsername() + "/" + entryUid;		
		}
	}
	
	@RequestMapping(value = "/librarian/{username}/{uid}", method = RequestMethod.GET)
	public String singleEntry(@PathVariable String username, @PathVariable int uid, Model model) {
		Entry currentEntry = entryDao.findByUid(uid);
		model.addAttribute("entry", currentEntry);
		return "entry";
	}
	
	@RequestMapping(value = "/librarian/{username}", method = RequestMethod.GET)
	public String userEntries(@PathVariable String username, Model model) {
		User currentUser = userDao.findByUsername(username);
		List<Entry> userEntries = currentUser.getEntries();
		model.addAttribute("entries", userEntries);
		return "librarian";
	}
}

