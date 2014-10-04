package com.citihub.siteassessor;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.citihub.siteassessor.dao.SitesDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("siteList")
public class LandingController {

	private static final Logger logger = LoggerFactory
			.getLogger(LandingController.class);
	
    @ModelAttribute("siteList")
    public List<Site> addSiteListToRequestScope() {
    	SitesDAO dao = new SitesDAO();
    	List<Site> bean = null;
        logger.info("Inside of addStuffToRequestScope");
        try {
        	bean = dao.readSites();
        } catch (Exception e) {
        	logger.error(e.getMessage());
        }
        
        return bean;
    }   

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "landing", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request, HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		if (session.getAttribute("logonUser") == null) {
			logger.info("User not logged in");
			return "redirect:logon";
		}
		
		model.addAttribute("sitesselected", new SitesSelected());	
		
		logger.info("logon user: " + (String)session.getAttribute("logonUser"));		
			
		return "landing";
	}
	 
	@RequestMapping(value = "landing", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute SitesSelected selected, Model m, HttpServletRequest request, HttpSession session) {

		if (session.getAttribute("logonUser") == null) {
			logger.info("User not logged in");
			return "redirect:logon";
		}
		
		int sitecount = selected.siteStatus.length;
		session.setAttribute("sitescount",new Integer(sitecount));
		logger.info("sitecount = " + sitecount);
		session.setAttribute("sitespos",new Integer(0));		
		session.setAttribute("sitesselected",selected);		
		
		return "redirect:home";
	}
}
