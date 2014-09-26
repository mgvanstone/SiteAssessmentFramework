package com.citihub.siteassessor;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
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

	@RequestMapping(value = "/logon", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Logon logon, Model m) {

		logger.info("Submitted logon username=" + logon.getUsername() + " password " + logon.getPassword());
		
		if (!logon.getUsername().equals("citihub") || !logon.getPassword().equals("password")) {
			return "logon";
		}
		
		m.addAttribute("assessment", new Assessment());
		QuestionDAO dao = new QuestionDAO();
		try {
			List<Question> questionList = dao.readQuestions();

			m.addAttribute("questionList", questionList);
		} catch (Exception e) {
			System.out.println("Exception list");
		}		
		
		return "redirect:form";
	}
}
