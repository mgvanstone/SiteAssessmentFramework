package com.citihub.siteassessor;

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
 * Handles requests for the application result page.
 */
@Controller
public class ResultController {

	private static final Logger logger = LoggerFactory
			.getLogger(ResultController.class);

	/**
	 * Simply selects the result view to render by returning its name.
	 */
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result(Locale locale, Model model) {
		logger.info("Welcome results! The client locale is {}.", locale);

		// Date date = new Date();
		// DateFormat dateFormat =
		// DateFormat.getDateTimeInstance(DateFormat.LONG,
		// DateFormat.LONG, locale);
		//
		// String formattedDate = dateFormat.format(date);
		//
		// model.addAttribute("assessment", new Assessment());
		// model.addAttribute("serverTime", formattedDate);
		//
		// QuestionDAO dao = new QuestionDAO();
		// try {
		// List<Question> questionList = dao.readQuestions();
		//
		// model.addAttribute("questionList", questionList);
		// } catch (Exception e) {
		// System.out.println("Exception list");
		// }

		return "result";
	}

	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Result result, Model m) {

		return "redirect:form";
	}
}
