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
	public String home(Locale locale, Model model, HttpServletRequest request, HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);

//		Map<String, Object> map = model.asMap();
//		logger.info(model.toString());
//
//		  System.out.println("*** Session data ***");
//		  Enumeration<String> e = session.getAttributeNames();
//		  while (e.hasMoreElements()){
//		    String s = e.nextElement();
//		    System.out.println(s);
//		    System.out.println("**" + session.getAttribute(s));
//		    //System.out.println(""+(SitesSelected)session.getAttribute(s));
//		  }
		
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
		
		int sitespos = ((Integer)session.getAttribute("sitespos")).intValue();
		int sitescount = ((Integer)session.getAttribute("sitescount")).intValue();		
		SitesSelected sitesSelected = (SitesSelected)session.getAttribute("sitesselected");
		List<Site> siteList = (List<Site>)session.getAttribute("siteList");
		
		logger.info("countpos = " + sitespos + " sitescount = " + sitescount + " site " + sitesSelected.siteStatus[sitespos]);
		int x = Integer.parseInt(sitesSelected.siteStatus[sitespos]);
		Site site = (Site)siteList.get(x-1);
		logger.info("site: " + site);
		
		model.addAttribute("site", site);
		session.setAttribute("site", site);
		site.setStatus("Completed");
		siteList.set(x-1, site);
		if (sitespos == sitescount - 1) {
			model.addAttribute("action", "Submit");
			session.setAttribute("action", "Submit");
		} else {
			model.addAttribute("action", "Next Facility");			
			session.setAttribute("action", "Next Facility");
		}
		

		QuestionDAO dao = new QuestionDAO();
		try {
			List<Question> questionList = dao.readQuestions();

			model.addAttribute("questionList", questionList);
			
			if (logger.isInfoEnabled()) {
				Iterator<Question> it = questionList.iterator();
				while (it.hasNext()) {
					logger.info(""+it.next());
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception list");
		}
		

		return "home";
	}

	@RequestMapping(value = "home", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Assessment assessment, Model m, HttpServletRequest request, HttpSession session) {
		List<Question> questionList = null;
		AnswerDAO dao = new AnswerDAO();
		QuestionDAO questionDAO = new QuestionDAO();

		int sitespos = ((Integer)session.getAttribute("sitespos")).intValue();
	
			
		
		Site site = (Site)session.getAttribute("site");
		SitesSelected sitesselected = (SitesSelected)session.getAttribute("sitesselected");
		
		AnswerDAO assDAO = new AnswerDAO();
		Assessment ass = new Assessment();
		ass.setName(site.getName());
		ass.setSubmitter(sitesselected.getSubmitter());
		try {
			assDAO.saveResults(ass);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		try {
			questionList = questionDAO.readQuestions();
		} catch (Exception e) {
			logger.error(e.toString());
		}

		Iterator<Question> it = questionList.iterator();
		int i = 0;
		
		while (it.hasNext()) {
			Question q = it.next();
			if (q.getSubcategory().equals("Commericals")) {
				logger.info("Found " + i);
				break;
			}
			i++;			
		}

		logger.info("Answer list");
		Iterator<String> it2 = assessment.getAnswer().iterator();
		int i2 = 0;
		
		while (it2.hasNext()) {
			String q = it2.next();
			logger.info("Answer " + q);
			i2++;			
		}
		

		
		
		
		//int sitespos = ((Integer)session.getAttribute("sitespos")).intValue();
		int sitescount = ((Integer)session.getAttribute("sitescount")).intValue();		
		SitesSelected sitesSelected = (SitesSelected)session.getAttribute("sitesselected");
		List<Site> siteList = (List<Site>)session.getAttribute("siteList");
		
		logger.info("countpos = " + sitespos + " sitescount = " + sitescount + " site " + sitesSelected.siteStatus[sitespos]);
		int x = Integer.parseInt(sitesSelected.siteStatus[sitespos]);
		Site site2 = (Site)siteList.get(x-1);
		logger.info("site: " + site2);
		
		//m.addAttribute("site", site);
		//session.setAttribute("site", site);
		site.setPricing(assessment.getAnswer().get(i));
		siteList.set(x-1, site);		
		
		sitespos++;
		session.setAttribute("sitespos", new Integer(sitespos));			
		
		String action = (String)session.getAttribute("action");
		if (!action.equals("Submit")) {
			logger.info("go home");
			return "redirect:home";
		}
		
//		
//		HashMap<String, Question> questionMap = new HashMap<String, Question>();
//		while (it.hasNext()) {
//			Question question = (it.next());
//			questionMap.put(question.getId(), question);
//		}

//		List<String> answerQuestionIdList = assessment.getQuestionid();
//		List<String> answerIdList = assessment.getAnswer();
//
//		Iterator<String> answerQuestionIt = answerQuestionIdList.iterator();
		
		
		/**
		int overallTier = 0;
		int electricalTier = 0;
		int mechanicalTier = 0;
		int telecomTier = 0;
		int sitestructureTier = 0;
		int operationsTier = 0;
		int processTier = 0;

		int listPos = 0;
		while (answerQuestionIt.hasNext()) {
			String ansQuestionId = answerQuestionIt.next();
			//System.out.println(ansQuestionId);
			String answer = answerIdList.get(listPos);
			//System.out.println("Here " + answer);
			if (!answer.equals("NOANS")) {

				Question question = questionMap.get(ansQuestionId);
				int answerValue = Integer.parseInt(answer);
				if (question.getCategory().equals("Electrical")) {
					if (electricalTier == 0)
						electricalTier = answerValue;
					if (answerValue < electricalTier) {
						electricalTier = answerValue;
					}
				} else if (question.getCategory().equals("Mechanical")) {
					if (mechanicalTier == 0)
						mechanicalTier = answerValue;
					if (answerValue < mechanicalTier) {
						mechanicalTier = answerValue;
					}
				} else if (question.getCategory().equals("Telecoms")) {
					if (telecomTier == 0)
						telecomTier = answerValue;
					if (answerValue < telecomTier) {
						telecomTier = answerValue;
					}
				} else if (question.getCategory().equals("Operations")) {
					if (operationsTier == 0)
						operationsTier = answerValue;
					if (answerValue < operationsTier) {
						operationsTier = answerValue;
					}
				} else if (question.getCategory().equals("Process")) {
					if (processTier == 0)
						processTier = answerValue;
					if (answerValue < processTier) {
						processTier = answerValue;
					}
				} else if (question.getCategory().equals("SiteStructure")) {
					if (sitestructureTier == 0)
						sitestructureTier = answerValue;
					if (answerValue < sitestructureTier) {
						sitestructureTier = answerValue;
					}
				} else {
					System.out.println("No match");
				}

				if (overallTier == 0)
					overallTier = answerValue;
				if (overallTier > answerValue)
					overallTier = answerValue;
			}

			listPos++;
		}

		assessment.setOverallStatus("Tier " + (++overallTier));
		assessment.setElectricalStatus("Tier " + (++electricalTier));
		assessment.setMechanicalStatus("Tier " + (++mechanicalTier));
		assessment.setTelecomsStatus("Tier " + (++telecomTier));
		assessment.setProcessStatus("Tier " + (++processTier));
		assessment.setSitestructureStatus("Tier " + (++sitestructureTier));
		assessment.setOperationsStatus("Tier " + (++operationsTier));
		m.addAttribute("assessment", assessment);

		try {
			dao.saveResults(assessment);

		} catch (Exception e) {
			logger.error(e.toString());
		}
**/
		//System.out.println(assessment.toString());
		logger.info("redirect to result");
		return "redirect:result";
	}
}
