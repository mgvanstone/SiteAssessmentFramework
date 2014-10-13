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

import com.citihub.siteassessor.dao.AnswerDAO;
import com.citihub.siteassessor.dao.QuestionDAO;
import com.citihub.siteassessor.dao.SelectedSitesDAO;
import com.citihub.siteassessor.Constants;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request,
			HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);

		if (session.getAttribute("logonUser") == null) {
			logger.info("User not logged in");
			return "redirect:logon";
		}

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("assessment", new Assessment());
		model.addAttribute("serverTime", formattedDate);

		int sitespos = ((Integer) session.getAttribute("sitespos")).intValue();
		int sitescount = ((Integer) session.getAttribute("sitescount"))
				.intValue();
		SitesSelected sitesSelected = (SitesSelected) session
				.getAttribute("sitesselected");
		List<Site> siteList = (List<Site>) session.getAttribute("siteList");

		logger.info("countpos = " + sitespos + " sitescount = " + sitescount
				+ " site " + sitesSelected.siteId[sitespos]);
		int x = Integer.parseInt(sitesSelected.siteId[sitespos]);
		Site site = (Site) siteList.get(x - 1);
		logger.info("site: " + site);

		model.addAttribute("site", site);
		session.setAttribute("site", site);
		site.setStatus("Completed");
		siteList.set(x - 1, site);
		if (sitespos == sitescount - 1) {
			model.addAttribute(Constants.SESSION_VAR_ACTION, Constants.ACTION_SUBMIT);
			session.setAttribute(Constants.SESSION_VAR_ACTION, Constants.ACTION_SUBMIT);
		} else {
			model.addAttribute(Constants.SESSION_VAR_ACTION, Constants.ACTION_NEXT);
			session.setAttribute(Constants.SESSION_VAR_ACTION, Constants.ACTION_NEXT);
		}

		QuestionDAO dao = new QuestionDAO();
		try {
			List<Question> questionList = dao.readQuestions();

			model.addAttribute("questionList", questionList);

			if (logger.isInfoEnabled()) {
				Iterator<Question> it = questionList.iterator();
				while (it.hasNext()) {
					logger.info("" + it.next());
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception list");
		}

		return "home";
	}

	@RequestMapping(value = "home", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Assessment assessment, Model m,
			HttpServletRequest request, HttpSession session) {

		if (session.getAttribute("logonUser") == null) {
			logger.info("User not logged in");
			return "redirect:logon";
		}

		List<Question> questionList = null;
		QuestionDAO questionDAO = new QuestionDAO();

		int sitespos = ((Integer) session.getAttribute("sitespos")).intValue();

		Site site = (Site) session.getAttribute("site");
		SitesSelected sitesselected = (SitesSelected) session
				.getAttribute("sitesselected");

		SelectedSitesDAO assDAO = new SelectedSitesDAO();
		assessment.setName(site.getName());
		assessment.setSubmitter((String) session.getAttribute("logonUser"));

		logger.info(assessment.toString());

		AnswerDAO ansDAO = new AnswerDAO();
		try {
			questionList = questionDAO.readQuestions();
			ansDAO.saveResults(questionList, assessment);
		} catch (Exception e) {
			logger.error(e.toString());
			return "error";
		}

		// Find the commericals question
		Iterator<Question> it = questionList.iterator();
		int i = 0;
		boolean found = false;

		while (it.hasNext()) {
			Question q = it.next();
			if (q.getSubcategory().equals(Constants.COMMERICALS_QUESTION)) {
				logger.info("Found " + i);
				found = true;
				break;
			}
			i++;
		}

		// int sitespos =
		// ((Integer)session.getAttribute("sitespos")).intValue();
		int sitescount = ((Integer) session.getAttribute("sitescount"))
				.intValue();
		SitesSelected sitesSelected = (SitesSelected) session
				.getAttribute("sitesselected");
		List<Site> siteList = (List<Site>) session.getAttribute("siteList");

		// logger.info("countpos = " + sitespos + " sitescount = " + sitescount
		// + " site " + sitesSelected.siteStatus[sitespos]);
		int x = Integer.parseInt(sitesSelected.siteId[sitespos]);
		// Site site2 = (Site)siteList.get(x-1);
		// logger.info("site: " + site2);

		// m.addAttribute("site", site);
		// session.setAttribute("site", site);
		if (found) {		
			site.setPricing(assessment.getAnswer().get(i-1));
			siteList.set(x - 1, site);
		}

		// move forward to the next facility
		sitespos++;
		session.setAttribute("sitespos", new Integer(sitespos));
		
		String action = (String) session.getAttribute(Constants.SESSION_VAR_ACTION);
		if (!action.equals(Constants.ACTION_SUBMIT)) {
			// Go to the next facility
			logger.info("Submit pressed");
			return "redirect:home";
		}

		logger.info("redirect to result");
		return "redirect:result";
	}
}
