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
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("assessment", new Assessment());
		model.addAttribute("serverTime", formattedDate);

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
		} catch (Exception e) {
			System.out.println("Exception list");
		}
		

		return "home";
	}

	@RequestMapping(value = "form", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Assessment assessment, Model m) {
		List<Question> questionList = null;
		AnswerDAO dao = new AnswerDAO();
		QuestionDAO questionDAO = new QuestionDAO();

		/**	try {
			questionList = questionDAO.readQuestions();
		} catch (Exception e) {
			logger.error(e.toString());
		}

		Iterator<Question> it = questionList.iterator();
		HashMap<String, Question> questionMap = new HashMap<String, Question>();
		while (it.hasNext()) {
			Question question = (it.next());
			questionMap.put(question.getId(), question);
		}

		List<String> answerQuestionIdList = assessment.getQuestionid();
		List<String> answerIdList = assessment.getAnswer();

		Iterator<String> answerQuestionIt = answerQuestionIdList.iterator();
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
		return "result";
	}
}
