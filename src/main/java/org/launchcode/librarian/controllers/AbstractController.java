package org.launchcode.librarian.controllers;

import javax.servlet.http.HttpSession;

import org.launchcode.librarian.models.User;
import org.launchcode.librarian.models.dao.EntryDao;
import org.launchcode.librarian.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {
		
	//allows us to query the database for user objects and entry objects
	
	@Autowired
	protected UserDao userDao;
	
	@Autowired
	protected entryDao entryDao;

	//sets a universal key that can be used to determine whether a user is logged in
	static final String userSessionKey = "user_id";

		//retrieves a user currently in session
	    protected User getUserFromSession(HttpSession session) {  
	    	
	        Integer userId = (Integer) session.getAttribute(userSessionKey);
	        return userId == null ? null : userDao.findByUid(userId);
	    }
	    
	    //used to set a user in a session
	    protected void setUserInSession(HttpSession session, User user) {  
	    	session.setAttribute(userSessionKey, user.getUid());
	    }
		
	}
