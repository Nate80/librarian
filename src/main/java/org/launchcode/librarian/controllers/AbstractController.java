package org.launchcode.librarian.controllers;

import javax.servlet.http.HttpSession;

import org.launchcode.librarian.models.User;
import org.launchcode.librarian.models.dao.EntryDao;
import org.launchcode.librarian.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {
	
	@Autowired
	protected UserDao userDao;
	
	@Autowired
	protected entryDao entryDao;
	

}
