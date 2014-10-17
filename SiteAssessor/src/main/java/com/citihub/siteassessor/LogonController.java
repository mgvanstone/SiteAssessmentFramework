package com.citihub.siteassessor;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.citihub.siteassessor.dao.QuestionDAO;
import com.citihub.siteassessor.dao.UsersDAO;

/**
 * Handles requests for the application logon page.
 */
@Controller
public class LogonController {

	private static final Logger logger = LoggerFactory
			.getLogger(LogonController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/logon",  method = RequestMethod.GET)
	public String logon(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("logon", new Logon());
		model.addAttribute("assessment", new Assessment());
		model.addAttribute("serverTime", formattedDate);
		
		return "logon";
	}
	
	/**
	 * Check user and password
	 * @param user
	 * @param password
	 * @return
	 */
	private boolean checkUser(String username, String password) throws Exception {
		boolean result = false;
		
		UsersDAO userDAO = new UsersDAO();
		try {
			User user = userDAO.readUser(username);

			if (user == null) {
				logger.info("Invalid user " + username);
				return false;
			}
			
			if (password.equals(user.getPassword())) {
				logger.info("Valid user");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	

	@RequestMapping(value = "/logon", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Logon logon, Model m, HttpServletRequest request, HttpSession session) {

		logger.info("Submitted logon username=" + logon.getUsername());

		if (logon.getUsername() == null || logon.getPassword() == null) {
			logger.info("User rejected");
			return "logon";
		}
				
		try {
			boolean isUserValid = checkUser(logon.getUsername(), logon.getPassword());
			
			if (!isUserValid) {
				logger.info("User " + logon.getUsername() + " rejected.");
				return "logon";
			}
		} catch (Exception e) {
			logger.error("Systems errror: " + e.getMessage());
			e.printStackTrace();
			return "error";
		}
				
		m.addAttribute("assessment", new Assessment());
		session.setAttribute("logonUser", logon.getUsername());
		
		logger.info("logon user: " + (String)session.getAttribute("logonUser"));
		
		// Retrieve the question list
		// TODO: Do we still need this?
		QuestionDAO dao = new QuestionDAO();
		try {
			List<Question> questionList = dao.readQuestions();

			m.addAttribute("questionList", questionList);
		} catch (Exception e) {
			logger.error("Systems errror: " + e.getMessage());
			
			e.printStackTrace();

			return "error";
		}		

		logger.info("User logged in");
		return "redirect:landing";
	}
}
