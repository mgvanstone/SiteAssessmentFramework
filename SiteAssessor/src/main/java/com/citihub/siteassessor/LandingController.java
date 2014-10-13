package com.citihub.siteassessor;

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

import com.citihub.siteassessor.dao.SelectedSitesDAO;
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
		//logger.info("Inside of addStuffToRequestScope");
		try {
			bean = dao.readSites();
			
			SelectedSitesDAO ssDAO = new SelectedSitesDAO();
			
			String user = "citihub"; //(String)session.getAttribute("logonUser");
			logger.info("user " + user);
			Map<String, SelectedSite> ss = ssDAO.readSelectedSites(user);
			
			Iterator<Site> sites = bean.iterator();
			while (sites.hasNext()) {
				Site site = (Site)sites.next();
				
				if (ss.containsKey(site.getId())) {
					site.setChecked(Boolean.TRUE);
				}
				logger.info("Here" + site);
				

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bean;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "landing", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request,
			HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);

		if (session.getAttribute("logonUser") == null) {
			logger.info("User not logged in");
			return "redirect:logon";
		}

		model.addAttribute("sitesselected", new SitesSelected());

		logger.info("logon user: " + (String) session.getAttribute("logonUser"));

		return "landing";
	}

	@RequestMapping(value = "landing", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute SitesSelected selected, Model m,
			HttpServletRequest request, HttpSession session) {

		if (session.getAttribute("logonUser") == null) {
			logger.info("User not logged in");
			return "redirect:logon";
		}

		int sitecount = selected.siteId.length;
		session.setAttribute("sitescount", new Integer(sitecount));
		logger.info("sitecount = " + sitecount);
		session.setAttribute("sitespos", new Integer(0));
		session.setAttribute("sitesselected", selected);

		String user = (String) session.getAttribute("logonUser");
		for (int i = 0; i < sitecount; i++) {
//			logger.info("Here " + selected.getSiteStatus()[i]);
			//if (!selected.getSiteStatus()[i].equals("")) {

			try {
				SelectedSitesDAO dao = new SelectedSitesDAO();
				dao.saveResults(user, selected.getSiteId()[i], true);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return "error";
			}
		}

		return "redirect:home";
	}
}
