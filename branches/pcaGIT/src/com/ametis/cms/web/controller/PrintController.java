package com.ametis.cms.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CaseInvestigation;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.InvestigationCategory;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.service.CaseInvestigationService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.util.WebUtil;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Zaki Rahman <zaki@pusilkom.ui.ac.id>
 */
public class PrintController implements Controller {

	// case investigation service
	private CaseInvestigationService caseInvestigationService;
	private MemberService memberService;
	private ClientService clientService;

	// message sources
	private ResourceBundleMessageSource alertProperties;
	private ConfigurationService configurationService;

	// beans
	private SecurityService securityService;

	
	
	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	/**
	 * Setter and Getter START
	 */
	public CaseInvestigationService getCaseInvestigationService() {
		return caseInvestigationService;
	}

	public void setCaseInvestigationService(CaseInvestigationService caseInvestigationService) {
		this.caseInvestigationService = caseInvestigationService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public ResourceBundleMessageSource getAlertProperties() {
		return alertProperties;
	}

	public void setAlertProperties(ResourceBundleMessageSource alertProperties) {
		this.alertProperties = alertProperties;
	}

	/**
	 * Setter and Getter END
	 */

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String navigation = request.getParameter("navigation") == null ? "welcome" : request.getParameter("navigation");

		ModelAndView result = null;

		try {
			if (navigation.equalsIgnoreCase("kronologis")) {
				result = kronologisPerformed(request, response, "kronologis");
			} else if (navigation.equalsIgnoreCase("cedera")) {
				result = cederaPerformed(request, response, "cedera");
			} else if (navigation.equalsIgnoreCase("general")) {
				result = generalPerformed(request, response, "general");
			} else if (navigation.equalsIgnoreCase("kemotherapy")) {
				result = kemotherapyPerformed(request, response, "kemotherapy");
			} else if (navigation.equalsIgnoreCase("lanjutan")) {
				result = lanjutanPerformed(request, response, "lanjutan");
			} else if (navigation.equalsIgnoreCase("maternityKurate")) {
				result = maternityKuratePerformed(request, response, "maternityKurate");
			} else if (navigation.equalsIgnoreCase("maternityPartusNormal")) {
				result = maternityPartusNormalPerformed(request, response, "maternityPartusNormal");
			} else if (navigation.equalsIgnoreCase("maternitySectioCaesaria")) {
				result = maternitySectioCaesariaPerformed(request, response, "maternitySectioCaesaria");
			} else if (navigation.equalsIgnoreCase("odc")) {
				result = odcPerformed(request, response, "odc");
			} else if (navigation.equalsIgnoreCase("pembedahan")) {
				result = pembedahanPerformed(request, response, "pembedahan");
			}
			else if (navigation.equalsIgnoreCase("print")){
				result = printPerformed(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private ModelAndView cederaPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			ActionUser user = securityService.getActionUser(request);
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "PRINTCEDERA");
			// TODO: fix quick hack setelah mengerti security
			isUserAuthorized = true;

			if (!isUserAuthorized) {
				result = new ModelAndView(new RedirectView("errorpage"));
				result.addObject("errorType", "accessDenied");
				result.addObject("errorMessage", "You Are Not Authorized for PRINTCEDERA access");

				return result;
			}

			Integer caseInvestigationId = WebUtil.getParameterInteger(request, "caseInvestigationId");
			String navigation = WebUtil.getParameterString(request, "navigation", "");

			

			Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage(
								"not.found.caseinvestigation",
								null,
								"fail",
								Locale.getDefault()
						)
				);
			}
			else {
				Integer memberId = object.getCaseId().getMemberId().getMemberId();
				Member member = memberService.get(memberId);
				if (member != null){
					Client client = clientService.get(member.getClientId().getClientId());
					
					Configuration config = configurationService.getClientConfiguration(client.getClientId());
					
					if (config == null){
						config = configurationService.getSystemConfiguration();
					}
					
					location = location+"_"+config.getCompanyCode().toLowerCase();
				}
			}

			result = new ModelAndView(location);
			result.addObject("caseInvestigation", object);
			result.addObject("navigation", navigation);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
					"alert",
					alertProperties.getMessage(
							"system.error " + e.getMessage(),
							null,
							"fail",
							Locale.getDefault()
					)
			);

			result = new ModelAndView("error");
		}

		return result;
	}

	private ModelAndView generalPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			ActionUser user = securityService.getActionUser(request);
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "PRINTGENERAL");
			// TODO: fix quick hack setelah mengerti security
			isUserAuthorized = true;

			if (!isUserAuthorized) {
				result = new ModelAndView(new RedirectView("errorpage"));
				result.addObject("errorType", "accessDenied");
				result.addObject("errorMessage", "You Are Not Authorized for PRINTGENERAL access");

				return result;
			}

			Long caseInvestigationId = WebUtil.getParameterLong(request, "caseInvestigationId");
			String navigation = WebUtil.getParameterString(request, "navigation", "");

			

			Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage(
								"not.found.caseinvestigation",
								null,
								"fail",
								Locale.getDefault()
						)
				);
			}
			else {
				Integer memberId = object.getCaseId().getMemberId().getMemberId();
				Member member = memberService.get(memberId);
				if (member != null){
					Client client = clientService.get(member.getClientId().getClientId());
					
					Configuration config = configurationService.getClientConfiguration(client.getClientId());
					
					if (config == null){
						config = configurationService.getSystemConfiguration();
					}
					
					location = location+"_"+config.getCompanyCode().toLowerCase();
				}
				
			}

			result = new ModelAndView(location);
			
			result.addObject("caseInvestigation", object);
			result.addObject("navigation", navigation);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
					"alert",
					alertProperties.getMessage(
							"system.error " + e.getMessage(),
							null,
							"fail",
							Locale.getDefault()
					)
			);

			result = new ModelAndView("error");
		}

		return result;
	}

	private ModelAndView kemotherapyPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			ActionUser user = securityService.getActionUser(request);
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "PRINTKEMOTHERAPY");
			// TODO: fix quick hack setelah mengerti security
			isUserAuthorized = true;

			if (!isUserAuthorized) {
				result = new ModelAndView(new RedirectView("errorpage"));
				result.addObject("errorType", "accessDenied");
				result.addObject("errorMessage", "You Are Not Authorized for PRINTKEMOTHERAPY access");

				return result;
			}

			
			Integer caseInvestigationId = WebUtil.getParameterInteger(request, "caseInvestigationId");
			String navigation = WebUtil.getParameterString(request, "navigation", "");


			Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage(
								"not.found.caseinvestigation",
								null,
								"fail",
								Locale.getDefault()
						)
				);
			}
			else {
				Integer memberId = object.getCaseId().getMemberId().getMemberId();
				Member member = memberService.get(memberId);
				if (member != null){
					Client client = clientService.get(member.getClientId().getClientId());
					
					Configuration config = configurationService.getClientConfiguration(client.getClientId());
					
					if (config == null){
						config = configurationService.getSystemConfiguration();
					}
					
					location = location+"_"+config.getCompanyCode().toLowerCase();
				}
			}

			result = new ModelAndView(location);

			result.addObject("caseInvestigation", object);
			result.addObject("navigation", navigation);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
					"alert",
					alertProperties.getMessage(
							"system.error " + e.getMessage(),
							null,
							"fail",
							Locale.getDefault()
					)
			);

			result = new ModelAndView("error");
		}

		return result;
	}

	private ModelAndView kronologisPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			ActionUser user = securityService.getActionUser(request);
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "PRINTKRONOLOGIS");
			// TODO: fix quick hack setelah mengerti security
			isUserAuthorized = true;

			if (!isUserAuthorized) {
				result = new ModelAndView(new RedirectView("errorpage"));
				result.addObject("errorType", "accessDenied");
				result.addObject("errorMessage", "You Are Not Authorized for PRINTKRONOLOGIS access");

				return result;
			}

			Integer caseInvestigationId = WebUtil.getParameterInteger(request, "caseInvestigationId");
			String navigation = WebUtil.getParameterString(request, "navigation", "");

			Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage(
								"not.found.caseinvestigation",
								null,
								"fail",
								Locale.getDefault()
						)
				);
			}
			else {
				Integer memberId = object.getCaseId().getMemberId().getMemberId();
				Member member = memberService.get(memberId);
				if (member != null){
					Client client = clientService.get(member.getClientId().getClientId());
					
					Configuration config = configurationService.getClientConfiguration(client.getClientId());
					
					if (config == null){
						config = configurationService.getSystemConfiguration();
					}
					
					location = location+"_"+config.getCompanyCode().toLowerCase();
				}
			}

			result = new ModelAndView(location);

			result.addObject("caseInvestigation", object);
			result.addObject("navigation", navigation);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
					"alert",
					alertProperties.getMessage(
							"system.error " + e.getMessage(),
							null,
							"fail",
							Locale.getDefault()
					)
			);

			result = new ModelAndView("error");
		}

		return result;
	}

	private ModelAndView lanjutanPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			ActionUser user = securityService.getActionUser(request);
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "PRINTLANJUTAN");
			// TODO: fix quick hack setelah mengerti security
			isUserAuthorized = true;

			if (!isUserAuthorized) {
				result = new ModelAndView(new RedirectView("errorpage"));
				result.addObject("errorType", "accessDenied");
				result.addObject("errorMessage", "You Are Not Authorized for PRINTLANJUTAN access");

				return result;
			}

			Integer caseInvestigationId = WebUtil.getParameterInteger(request, "caseInvestigationId");
			String navigation = WebUtil.getParameterString(request, "navigation", "");



			Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage(
								"not.found.caseinvestigation",
								null,
								"fail",
								Locale.getDefault()
						)
				);
			}
			else {
				Integer memberId = object.getCaseId().getMemberId().getMemberId();
				Member member = memberService.get(memberId);
				if (member != null){
					Client client = clientService.get(member.getClientId().getClientId());
					
					Configuration config = configurationService.getClientConfiguration(client.getClientId());
					
					if (config == null){
						config = configurationService.getSystemConfiguration();
					}
					
					location = location+"_"+config.getCompanyCode().toLowerCase();
				}
			}

			result = new ModelAndView(location);

			result.addObject("caseInvestigation", object);
			result.addObject("navigation", navigation);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
					"alert",
					alertProperties.getMessage(
							"system.error " + e.getMessage(),
							null,
							"fail",
							Locale.getDefault()
					)
			);

			result = new ModelAndView("error");
		}

		return result;
	}

	private ModelAndView maternityKuratePerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			ActionUser user = securityService.getActionUser(request);
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "PRINTMATERNITYKURATE");
			// TODO: fix quick hack setelah mengerti security
			isUserAuthorized = true;

			if (!isUserAuthorized) {
				result = new ModelAndView(new RedirectView("errorpage"));
				result.addObject("errorType", "accessDenied");
				result.addObject("errorMessage", "You Are Not Authorized for PRINTMATERNITYKURATE access");

				return result;
			}

			Integer caseInvestigationId = WebUtil.getParameterInteger(request, "caseInvestigationId");
			String navigation = WebUtil.getParameterString(request, "navigation", "");

			

			Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage(
								"not.found.caseinvestigation",
								null,
								"fail",
								Locale.getDefault()
						)
				);
			}
			else {
				Integer memberId = object.getCaseId().getMemberId().getMemberId();
				Member member = memberService.get(memberId);
				if (member != null){
					Client client = clientService.get(member.getClientId().getClientId());
					
					Configuration config = configurationService.getClientConfiguration(client.getClientId());
					
					if (config == null){
						config = configurationService.getSystemConfiguration();
					}
					
					location = location+"_"+config.getCompanyCode().toLowerCase();
				}
			}

			result = new ModelAndView(location);

			result.addObject("caseInvestigation", object);
			result.addObject("navigation", navigation);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
					"alert",
					alertProperties.getMessage(
							"system.error " + e.getMessage(),
							null,
							"fail",
							Locale.getDefault()
					)
			);

			result = new ModelAndView("error");
		}

		return result;
	}

	private ModelAndView maternityPartusNormalPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			ActionUser user = securityService.getActionUser(request);
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "PRINTMATERNITYPARTUSNORMAL");
			// TODO: fix quick hack setelah mengerti security
			isUserAuthorized = true;

			if (!isUserAuthorized) {
				result = new ModelAndView(new RedirectView("errorpage"));
				result.addObject("errorType", "accessDenied");
				result.addObject("errorMessage", "You Are Not Authorized for PRINTMATERNITYPARTUSNORMAL access");

				return result;
			}

			Integer caseInvestigationId = WebUtil.getParameterInteger(request, "caseInvestigationId");
			String navigation = WebUtil.getParameterString(request, "navigation", "");


			Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage(
								"not.found.caseinvestigation",
								null,
								"fail",
								Locale.getDefault()
						)
				);
			}
			else {
				Integer memberId = object.getCaseId().getMemberId().getMemberId();
				Member member = memberService.get(memberId);
				if (member != null){
					Client client = clientService.get(member.getClientId().getClientId());
					
					Configuration config = configurationService.getClientConfiguration(client.getClientId());
					
					if (config == null){
						config = configurationService.getSystemConfiguration();
					}
					
					location = location+"_"+config.getCompanyCode().toLowerCase();
				}
			}

			result = new ModelAndView(location);

			result.addObject("caseInvestigation", object);
			result.addObject("navigation", navigation);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
					"alert",
					alertProperties.getMessage(
							"system.error " + e.getMessage(),
							null,
							"fail",
							Locale.getDefault()
					)
			);

			result = new ModelAndView("error");
		}

		return result;
	}

	private ModelAndView maternitySectioCaesariaPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			ActionUser user = securityService.getActionUser(request);
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "PRINTMATERNITYSECTIOCAESARIA");
			// TODO: fix quick hack setelah mengerti security
			isUserAuthorized = true;

			if (!isUserAuthorized) {
				result = new ModelAndView(new RedirectView("errorpage"));
				result.addObject("errorType", "accessDenied");
				result.addObject("errorMessage", "You Are Not Authorized for PRINTMATERNITYSECTIOCAESARIA access");

				return result;
			}

			Integer caseInvestigationId = WebUtil.getParameterInteger(request, "caseInvestigationId");
			String navigation = WebUtil.getParameterString(request, "navigation", "");


			Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage(
								"not.found.caseinvestigation",
								null,
								"fail",
								Locale.getDefault()
						)
				);
			}
			else {
				Integer memberId = object.getCaseId().getMemberId().getMemberId();
				Member member = memberService.get(memberId);
				if (member != null){
					Client client = clientService.get(member.getClientId().getClientId());
					
					Configuration config = configurationService.getClientConfiguration(client.getClientId());
					
					if (config == null){
						config = configurationService.getSystemConfiguration();
					}
					
					location = location+"_"+config.getCompanyCode().toLowerCase();
				}
			}

			result = new ModelAndView(location);

			result.addObject("caseInvestigation", object);
			result.addObject("navigation", navigation);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
					"alert",
					alertProperties.getMessage(
							"system.error " + e.getMessage(),
							null,
							"fail",
							Locale.getDefault()
					)
			);

			result = new ModelAndView("error");
		}

		return result;
	}

	private ModelAndView odcPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			ActionUser user = securityService.getActionUser(request);
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "PRINTODC");
			// TODO: fix quick hack setelah mengerti security
			isUserAuthorized = true;

			if (!isUserAuthorized) {
				result = new ModelAndView(new RedirectView("errorpage"));
				result.addObject("errorType", "accessDenied");
				result.addObject("errorMessage", "You Are Not Authorized for PRINTODC access");

				return result;
			}

			Integer caseInvestigationId = WebUtil.getParameterInteger(request, "caseInvestigationId");
			String navigation = WebUtil.getParameterString(request, "navigation", "");


			Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage(
								"not.found.caseinvestigation",
								null,
								"fail",
								Locale.getDefault()
						)
				);
			}
			else {
				Integer memberId = object.getCaseId().getMemberId().getMemberId();
				Member member = memberService.get(memberId);
				if (member != null){
					Client client = clientService.get(member.getClientId().getClientId());
					
					Configuration config = configurationService.getClientConfiguration(client.getClientId());
					
					if (config == null){
						config = configurationService.getSystemConfiguration();
					}
					
					location = location+"_"+config.getCompanyCode().toLowerCase();
				}
			}

			result = new ModelAndView(location);

			result.addObject("caseInvestigation", object);
			result.addObject("navigation", navigation);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
					"alert",
					alertProperties.getMessage(
							"system.error " + e.getMessage(),
							null,
							"fail",
							Locale.getDefault()
					)
			);

			result = new ModelAndView("error");
		}

		return result;
	}

	private ModelAndView pembedahanPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			ActionUser user = securityService.getActionUser(request);
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "PRINTPEMBEDAHAN");
			// TODO: fix quick hack setelah mengerti security
			isUserAuthorized = true;

			if (!isUserAuthorized) {
				result = new ModelAndView(new RedirectView("errorpage"));
				result.addObject("errorType", "accessDenied");
				result.addObject("errorMessage", "You Are Not Authorized for PRINTPEMBEDAHAN access");

				return result;
			}

			Integer caseInvestigationId = WebUtil.getParameterInteger(request, "caseInvestigationId");
			String navigation = WebUtil.getParameterString(request, "navigation", "");

			Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage(
								"not.found.caseinvestigation",
								null,
								"fail",
								Locale.getDefault()
						)
				);
			}
			else {
				Integer memberId = object.getCaseId().getMemberId().getMemberId();
				Member member = memberService.get(memberId);
				if (member != null){
					Client client = clientService.get(member.getClientId().getClientId());
					
					Configuration config = configurationService.getClientConfiguration(client.getClientId());
					
					if (config == null){
						config = configurationService.getSystemConfiguration();
					}
					
					location = location+"_"+config.getCompanyCode().toLowerCase();
				}
			}

			result = new ModelAndView(location);

			result.addObject("caseInvestigation", object);
			result.addObject("navigation", navigation);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
					"alert",
					alertProperties.getMessage(
							"system.error " + e.getMessage(),
							null,
							"fail",
							Locale.getDefault()
					)
			);

			result = new ModelAndView("error");
		}

		return result;
	}
	private ModelAndView printPerformed(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			ActionUser user = securityService.getActionUser(request);
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "PRINTPEMBEDAHAN");
			// TODO: fix quick hack setelah mengerti security
			isUserAuthorized = true;

			if (!isUserAuthorized) {
				result = new ModelAndView(new RedirectView("errorpage"));
				result.addObject("errorType", "accessDenied");
				result.addObject("errorMessage", "You Are Not Authorized for PRINTPEMBEDAHAN access");

				return result;
			}
			String location = "";
			Long caseInvestigationId = WebUtil.getParameterLong(request, "caseInvestigationId");
			String navigation = WebUtil.getParameterString(request, "navigation", "");

			

			Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage(
								"not.found.caseinvestigation",
								null,
								"fail",
								Locale.getDefault()
						)
				);
			}
			else {
				InvestigationCategory category = object.getInvestigationCategoryId();
				if (category != null && category.getPrintOutTemplate() != null){
					location = category.getPrintOutTemplate();
				}
			}

			result = new ModelAndView(location);
			result.addObject("caseInvestigation", object);
			result.addObject("navigation", navigation);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
					"alert",
					alertProperties.getMessage(
							"system.error " + e.getMessage(),
							null,
							"fail",
							Locale.getDefault()
					)
			);

			result = new ModelAndView("error");
		}

		return result;
	}
}
