package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberProvider;
import com.ametis.cms.datamodel.Questionaire;
import com.ametis.cms.datamodel.QuestionaireItem;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.MemberProviderService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.ProviderReviewQuestionaireService;
import com.ametis.cms.service.ProviderReviewService;
import com.ametis.cms.service.QuestionaireItemService;
import com.ametis.cms.service.QuestionaireService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+

// imports-

/**
 * MemberProvider is a servlet controller for member_provider Table. All you
 * have to do is to convert necessary data field to the named parameter
 */
public class MemberProviderController implements Controller

// extends+

// extends-

{

	private MemberProviderService memberProviderService;
	private ProviderReviewService providerReviewService;
	private QuestionaireService questionaireService;
	private ProviderReviewQuestionaireService providerReviewQuestionaireService;
	private QuestionaireItemService questionaireItemService;
	private SecurityService securityService;

	private UserService actionuserService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	private MemberService memberService;

	
	
	
	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public ProviderReviewService getProviderReviewService() {
		return providerReviewService;
	}

	public void setProviderReviewService(ProviderReviewService providerReviewService) {
		this.providerReviewService = providerReviewService;
	}

	public QuestionaireService getQuestionaireService() {
		return questionaireService;
	}

	public void setQuestionaireService(QuestionaireService questionaireService) {
		this.questionaireService = questionaireService;
	}

	public ProviderReviewQuestionaireService getProviderReviewQuestionaireService() {
		return providerReviewQuestionaireService;
	}

	public void setProviderReviewQuestionaireService(
			ProviderReviewQuestionaireService providerReviewQuestionaireService) {
		this.providerReviewQuestionaireService = providerReviewQuestionaireService;
	}

	public QuestionaireItemService getQuestionaireItemService() {
		return questionaireItemService;
	}

	public void setQuestionaireItemService(
			QuestionaireItemService questionaireItemService) {
		this.questionaireItemService = questionaireItemService;
	}

	public void setUserService(UserService userService) {
		this.actionuserService = userService;
	}

	public UserService getUserService() {
		return actionuserService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public SecurityService getSecurityService() {
		return this.securityService;
	}

	public void setCountSet(Integer countSet) {
		this.countSet = countSet;
	}

	public Integer getCountSet() {
		return this.countSet;
	}

	public void setMaxPercountSet(Integer maxCount) {
		this.maxPercountSet = maxCount;
	}

	public Integer getMaxPercountSet() {
		return this.maxPercountSet;
	}

	public void setAlertProperties(ResourceBundleMessageSource alert) {
		this.alertProperties = alert;
	}

	public ResourceBundleMessageSource getAlertProperties() {
		return alertProperties;
	}

	public void setMemberProviderService(
			MemberProviderService memberProviderService) {
		this.memberProviderService = memberProviderService;
	}

	public MemberProviderService getMemberProviderService() {
		return this.memberProviderService;
	}

	public ModelAndView surveyPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			result = new ModelAndView("surveyMemberProvider");
			
			Integer memberProviderId = WebUtil.getParameterInteger(request,
					"memberProviderId");

			java.io.Serializable pkey = memberProviderId;

			ActionUser user = securityService.getActionUser(request);

			MemberProvider res = memberProviderService.get(pkey);

			result.addObject("memberProviderId", memberProviderId);
			

			if (navigation.equalsIgnoreCase("presurvey")){
				
				String[] eqPQ = {"status","deletedStatus"};
				Object[] eqVQ = {Integer.valueOf(1),Integer.valueOf(0)};
				Collection<Questionaire> qList = questionaireService.search(null,null,eqPQ,eqVQ,0,1);
				
				if (qList != null){
					Iterator<Questionaire> iteratorQ = qList.iterator();
					
					if (iteratorQ.hasNext()){
						Questionaire q = iteratorQ.next();
						
						if (q != null){
							String[] eqPQI = {"questionaireId.questionaireId","deletedStatus"};
							Object[] eqVQI = {q.getQuestionaireId(),0};
							
							int total = questionaireItemService.getTotal(null,null,eqPQI,eqVQI);
							Collection<QuestionaireItem> qItemList = questionaireItemService.search(null,null,eqPQI,eqVQI,0,total);
							
							result.addObject("qItemList", qItemList);
							
						}
					}
				}
			}
			else {
				Integer rating = WebUtil.getParameterInteger(request, "rating");
			}
			
			
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberProviderId = WebUtil.getParameterInteger(request,
					"memberProviderId");

			java.io.Serializable pkey = memberProviderId;

			ActionUser user = securityService.getActionUser(request);

			MemberProvider res = memberProviderService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.memberprovider", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.memberprovider", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchMemberProvider");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	public ModelAndView detailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberProviderId = WebUtil.getParameterInteger(request,
					"memberProviderId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
			java.io.Serializable pkey = memberProviderId;
			MemberProvider object = memberProviderService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.memberprovider", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("memberProvider", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			if (navigation.equals("gosearch") || navigation.equals("golookup")) {

				if (searchby != null) {
					
				}

			}

			vEqP.add("memberId.memberId");
			vEqQ.add(memberId);
			
			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = memberProviderService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			String arah = WebUtil.getParameterString(request, "arah", "");

			if (index == null)
				index = new Integer(1);

			if (arah.equals("kanan"))
				index = new Integer(index.intValue() + 1);
			else if (arah.equals("kiri"))
				index = new Integer(index.intValue() - 1);
			else if (arah.equals("kiribgt"))
				index = new Integer(1);
			else if (arah.equals("kananbgt"))
				index = new Integer(count / countSet.intValue() + 1);

			if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
				index = new Integer(1);
			else if (index.compareTo(new Integer(count / countSet.intValue()
					+ 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1)
					* countSet.intValue())).intValue();
			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
			// foreign affairs
					"MemberProvider.MemberId", "MemberProvider.ProviderId",
			// foreign affairs end
			};
			collection = memberProviderService.search(sLikeP, sLikeQ, sEqP,
					sEqQ, required, rowsetint, countSet.intValue());

			if (collection.size() <= 0) {
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1)
						.intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count
						/ countSet.intValue() + 1)) == new Integer(1)
						.intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1)
						* countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue()));
				}
				collection = memberProviderService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("memberId", memberId);

			

			result.addObject("MemberProviders", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView searchMyPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			ActionUser user = securityService.getActionUser(request);
			
			if (user != null && user.getUser().getUserType().intValue() == User.MEMBER){
				memberId = user.getUser().getMemberId().getMemberId();
			}

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			if (navigation.equals("gosearchmyprovider") || navigation.equals("myprovider") ) {

				if (searchby != null) {
					
					if (searchby.equalsIgnoreCase("providerName")){
						vLikeP.add("providerId.providerName");
						vLikeQ.add(searchtext);
					}
					else if (searchby.equalsIgnoreCase("province")){
						vLikeP.add("providerId.province");
						vLikeQ.add(searchtext);
					}
					else if (searchby.equalsIgnoreCase("city")){
						vLikeP.add("providerId.city");
						vLikeQ.add(searchtext);
					}
					
				}

			}

			vEqP.add("memberId.memberId");
			vEqQ.add(memberId);
			
			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = memberProviderService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			String arah = WebUtil.getParameterString(request, "arah", "");

			if (index == null)
				index = new Integer(1);

			if (arah.equals("kanan"))
				index = new Integer(index.intValue() + 1);
			else if (arah.equals("kiri"))
				index = new Integer(index.intValue() - 1);
			else if (arah.equals("kiribgt"))
				index = new Integer(1);
			else if (arah.equals("kananbgt"))
				index = new Integer(count / countSet.intValue() + 1);

			if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
				index = new Integer(1);
			else if (index.compareTo(new Integer(count / countSet.intValue()
					+ 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1)
					* countSet.intValue())).intValue();
			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
			// foreign affairs
					"MemberProvider.MemberId", "MemberProvider.ProviderId",
			// foreign affairs end
			};
			collection = memberProviderService.search(sLikeP, sLikeQ, sEqP,
					sEqQ, required, rowsetint, countSet.intValue());

			if (collection.size() <= 0) {
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1)
						.intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count
						/ countSet.intValue() + 1)) == new Integer(1)
						.intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1)
						* countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue()));
				}
				collection = memberProviderService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("memberId", memberId);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("MemberProviders", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	/**
	 * Action Service
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Get paramater navigation
		String navigation = request.getParameter("navigation") == null ? "welcome"
				: request.getParameter("navigation");

		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);
		ActionUser theUser = securityService.getActionUser(request);

		/*
		 * if (session == null) {
		 * 
		 * request.setAttribute("alert", "<b>" +
		 * alertProperties.getValue("not.login") + "</b>");
		 * request.setAttribute("content", "/jsp/login.jsp");
		 * forward("/main.jsp", request, response);
		 * 
		 * 
		 * result = new ModelAndView ("login"); } else {
		 *  }
		 */
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailMemberProvider");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("presurvey") || navigation.equalsIgnoreCase("savesurvey")) {
					result = surveyPerformed(request, response);
			}else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchMemberProvider");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupMemberProvider");
			}
			 else if (navigation.equalsIgnoreCase("myprovider")
						|| navigation.equals("gosearchmyprovider")) {
					result = searchMyPerformed(request, response,
							"myMemberProvider");
			}
			else {
				if (theUser != null && theUser.getUser().getUserType().intValue() == User.MEMBER){
					result = searchMyPerformed(request, response, "myMemberProvider");
				}
				else {
					result = searchPerformed(request, response,
						"searchMemberProvider");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	// class+

	// class-

}
