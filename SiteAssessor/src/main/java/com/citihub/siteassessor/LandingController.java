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

import com.citihub.siteassessor.dao.DAO;
import com.citihub.siteassessor.dao.SelectedSitesDAO;
import com.citihub.siteassessor.dao.SitesDAO;

/**
 * Handles requests for the application landing page, i.e. the page that displays a 
 * list of sites to select from.
 */
@Controller
@SessionAttributes("siteList")
public class LandingController {

	private static final Logger logger = LoggerFactory
			.getLogger(LandingController.class);

	@ModelAttribute("siteList")
	public List<Site> addSiteListToRequestScope(HttpSession session) {
		SitesDAO dao = new SitesDAO();
		List<Site> bean = null;

		try {
			bean = dao.readSites();

			SelectedSitesDAO ssDAO = new SelectedSitesDAO();

			String user = (String)session.getAttribute("logonUser");
			logger.info("user " + (String)session.getAttribute("logonUser"));
			logger.info("Retriving list of sites for " + user);
			
			Map<String, SelectedSite> ss = ssDAO.readSelectedSites(user);

			Iterator<Site> sites = bean.iterator();
			while (sites.hasNext()) {
				Site site = (Site) sites.next();

				if (ss.containsKey(site.getId())) {
					site.setChecked(Boolean.TRUE);
				}
				logger.info("Landing site: " + site);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bean;
	}

	/**
	 * Used for the landing page view
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

	// Handle the form posted back
	@RequestMapping(value = "landing", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute SitesSelected selected, Model m,
			HttpServletRequest request, HttpSession session) {

		if (session.getAttribute("logonUser") == null) {
			logger.info("User not logged in");
			return "redirect:logon";
		}

		String user = (String) session.getAttribute("logonUser");

		int sitecount = selected.siteId.length;
		session.setAttribute("sitescount", new Integer(sitecount));
		logger.info("sitecount = " + sitecount);
		session.setAttribute("sitespos", new Integer(0));
		session.setAttribute("sitesselected", selected);

		try {
			SelectedSitesDAO dao = new SelectedSitesDAO();
			
			// delete any existing sites previously selected
			dao.deleteSelectedSites(user);
			
			// Store the selected sites
			for (int i = 0; i < sitecount; i++) {
				dao.saveResults(user, selected.getSiteId()[i], true);
				logger.info("Saved site " + selected.getSiteId()[i]);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "error";
		}

		return "redirect:home";
	}
}
