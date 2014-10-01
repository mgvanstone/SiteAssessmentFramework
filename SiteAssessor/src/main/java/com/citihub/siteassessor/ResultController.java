package com.citihub.siteassessor;

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
	@RequestMapping(value = "result", method = RequestMethod.GET)
	public String result(Locale locale, Model model, HttpServletRequest request, HttpSession session) {
		logger.info("Welcome results! The client locale is {}.", locale);
		
		List<Site> siteList = (List<Site>)session.getAttribute("siteList");
		model.addAttribute("siteList", siteList);
		
		SitesSelected sitesselected = (SitesSelected)session.getAttribute("sitesselected");
		model.addAttribute("sitesselected", sitesselected);		


		return "result";
	}

	@RequestMapping(value = "result", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Result result, Model m) {

		return "redirect:form";
	}
}
