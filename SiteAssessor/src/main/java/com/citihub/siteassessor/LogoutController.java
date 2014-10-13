package com.citihub.siteassessor;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application logout page.
 */
@Controller
public class LogoutController {

	private static final Logger logger = LoggerFactory
			.getLogger(LogoutController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request, HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);

		if (session.getAttribute("logonUser") == null) {
			logger.info("User not logged in");
			return "redirect:logon";
		}

	    if(session != null) {
	        session.removeAttribute("logonUser");
	    }

	    session.invalidate();
	    return "redirect:/logon";

	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Assessment assessment, Model m, HttpServletRequest request, HttpSession session) {

		return "redirect:logon";
	}
}
