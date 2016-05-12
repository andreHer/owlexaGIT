package com.ametis.cms.web.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jofc2.model.Chart;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.YAxis;
import jofc2.model.elements.BarChart;
import jofc2.model.elements.PieChart;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.dao.CaseDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Role;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.ClaimStatisticDto;
import com.ametis.cms.dto.PerformanceStatisticDto;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimReportService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ExcessChargeService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.Converter;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.SecurityUtil;
import java.io.BufferedReader;//adq
import java.io.File;
import java.io.FileReader;//adq
//add adq 02092015
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
//end add 02092015

public class DashboardController implements Controller {

    private PolicyService policyService;
    private MemberGroupService memberGroupService;
    private ClaimService claimService;
    private PaymentService paymentService;
    private ExcessChargeService excessChargeService;
    private MemberService memberService;
    private ClientService clientService;
    private ProviderService providerService;
    private ClaimReportService claimReportService;
    private ActivityLogService logService;
    
    private SecurityService securityService;
    
    private UserService userService;
    private CaseDao  myCaseDao;//adq
    private CaseService myCaseService;//adq
    
	
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
    public ClaimReportService getClaimReportService() {
        return claimReportService;
    }

    public void setClaimReportService(ClaimReportService claimReportService) {
        this.claimReportService = claimReportService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public ProviderService getProviderService() {
        return providerService;
    }

    public void setProviderService(ProviderService providerService) {
        this.providerService = providerService;
    }

    public PolicyService getPolicyService() {
        return policyService;
    }

    public void setPolicyService(PolicyService policyService) {
        this.policyService = policyService;
    }

    public MemberGroupService getMemberGroupService() {
        return memberGroupService;
    }

    public void setMemberGroupService(MemberGroupService memberGroupService) {
        this.memberGroupService = memberGroupService;
    }

    public ClaimService getClaimService() {
        return claimService;
    }

    public void setClaimService(ClaimService claimService) {
        this.claimService = claimService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public ExcessChargeService getExcessChargeService() {
        return excessChargeService;
    }

    public void setExcessChargeService(ExcessChargeService excessChargeService) {
        this.excessChargeService = excessChargeService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public ModelAndView dashboardPusatPerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("dashboardPusat");

        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //add adq
        int count = 0;
        int countTotalClaim = 0;
        int countUser = 0;
        float countSLA = 0;
        int countChecked = 0;
        int countPending = 0;
        int countReject = 0;
        int countCDV = 0;
        
        int statusChecked = Case.CASE_CHECKED;
        int statusPending = Case.CASE_PENDING;
        int statusReject = Case.CASE_REJECT;
        int statusVerified = Case.CASE_EDC_MANUAL;
        int statusCdvIssue = Case.CASE_TERMINATED;
        
        
         countUser = claimService.getTotalClaimDaily(statusVerified);
         countChecked = claimService.getTotalClaimDaily(statusChecked);
         countPending = claimService.getTotalClaimDaily(statusPending);
         countReject = claimService.getTotalClaimDaily(statusReject);
         countCDV = 	claimService.getTotalClaimDaily(statusCdvIssue);	
         countTotalClaim = countChecked+countPending+countReject;
         
        if(countTotalClaim<=0){
          countSLA=0;
        }
        else{
        	countSLA=(countPending+countReject+countCDV);
            countSLA /= countTotalClaim;	
        }
        
        request.setAttribute("count", new Integer(count));
        request.setAttribute("countTotalClaim", new Integer(countTotalClaim));
        request.setAttribute("countUser", new Integer(countUser));
        request.setAttribute("countChecked", new Integer(countChecked));
        request.setAttribute("countPending", new Integer(countPending));
        request.setAttribute("countReject", new Integer(countReject));
        request.setAttribute("countCDV", new Integer(countCDV));
        request.setAttribute("countSLA", new Float(countSLA));
        //end add adq
        
        ClassLoader loader = DashboardController.class.getClassLoader();
        System.out.print("file : ");
        System.out.println(loader.getResource("src/com/ametis/cms/web/controller/DashboardController.class"));
        
                             
        return result;
    }

    public ModelAndView getClaimGrowthPerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("chartData");

        try {
        	
        	ActionUser user = securityService.getActionUser(request);

        	String navigation = WebUtil.getParameterString(request, "navigation", "");
            NumberFormat nf = NumberFormat.getInstance(Locale.US);
            
            String[] navstr = navigation.split("-");
            String tahun = "2013";
            
            if (navstr.length == 1){
            	navigation = navstr[0];
            }
            else {
            	navigation = navstr[0];
            	tahun = navstr[1];
            }
            Chart chart = new Chart("Claim Growth Chart (Dalam Jutaan Rupiah)");
            
            System.out.println("REPORT YEAR DI CLAIM GROWTH : " + tahun);

            String[] labels = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
            
            double[] values = null;
            
            if (user != null && user.getUser().getUserType().intValue() == User.TPA){
            	values = claimReportService.generateYearlyClaimGrowth(Integer.valueOf(tahun));
            }
            else if (user != null && user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            	values = claimReportService.generateGroupYearlyClaimGrowth(Integer.valueOf(tahun),user.getUser().getMemberGroupId().getMemberGroupId());
            }
            else if (user != null && user.getUser().getUserType().intValue() == User.CLIENT){
            	values = claimReportService.generateClientYearlyClaimGrowth(Integer.valueOf(tahun),user.getUser().getClientId().getClientId());
            }

            
            double max = 0.0;
            BarChart barChart = new BarChart(BarChart.Style.GLASS);

            for (int i = 0; i < labels.length; i++) {
            	double cur = values[i];
            	if (cur/1000000 > max){
            		max = values[i] / 1000000;
            	}
                System.out.println("values at " + i + " --> "  + values[i]);
                BarChart.Bar bar = new BarChart.Bar(values[i]/1000000);
                bar.setTooltip(nf.format(values[i]));
                barChart.addBars(bar);
            }

            chart.addElements(barChart);

            XAxis xaxis = new XAxis();
            xaxis.addLabels(labels);
            chart.setXAxis(xaxis);

            YAxis yaxis = new YAxis();
            yaxis.setMax(max+0.1*max);
            yaxis.setSteps(100);
            chart.setYAxis(yaxis);

            result.addObject("chart", chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ModelAndView getPremiumGrowthPerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("chartData");

        try {

            Chart chart = new Chart("Premium Growth Chart (dalam ribuan)");

            double[] values = memberService.getYearlyMemberPremiumGrowth(2010);
            String[] labels = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};


            BarChart barChart = new BarChart(BarChart.Style.GLASS);

            for (int i = 0; i < labels.length; i++) {
                BarChart.Bar bar = new BarChart.Bar(values[i] / 1000);
                bar.setTooltip("Rp.   " + Converter.getMoney(values[i]) + "");
                barChart.addBars(bar);
            }
            chart.addElements(barChart);

            chart.setThousandSeparatorDisabled(false);

            XAxis xaxis = new XAxis();
            xaxis.addLabels(labels);
            chart.setXAxis(xaxis);

            YAxis yaxis = new YAxis();
            yaxis.setMax(Double.valueOf(5000000000.0 / 1000));
            yaxis.setSteps(500000000.0 / 1000);
            chart.setYAxis(yaxis);

            result.addObject("chart", chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private double getMaxArrayValueDouble(double[] array) {
        double result = 0;


        try {

            for (int i = 0; i < array.length; i++) {
                if (result < array[i]) {
                    result = array[i];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private int getMaxArrayValueInt(int[] array) {
        int result = 0;

        try {
            for (int i = 0; i < array.length; i++) {


                if (result < array[i]) {
                    result = array[i];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ModelAndView getMemberGrowthPerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("chartData");

        try {

        	ActionUser user = securityService.getActionUser(request);
        	

        	String navigation = WebUtil.getParameterString(request, "navigation", "");
            
            String[] navstr = navigation.split("-");
            String tahun = "2013";
            
            if (navstr.length == 1){
            	navigation = navstr[0];
            }
            else {
            	navigation = navstr[0];
            	tahun = navstr[1];
            }
            Chart chart = new Chart("Member Growth Chart");

            System.out.println("REPORT YEAR DI MemberGrowth : " + tahun);
            int[] values = null;
            if (user != null && user.getUser().getUserType().intValue() == User.TPA){
            	values = memberService.getYearlyMemberGrowth(Integer.valueOf(tahun));
            }
            else if (user != null && user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            	values = memberService.getYearlyGroupMemberGrowth(Integer.valueOf(tahun),user.getUser().getMemberGroupId().getMemberGroupId());
            }
            else if (user != null && user.getUser().getUserType().intValue() == User.CLIENT){
            	values = memberService.getYearlyBranchMemberGrowth(Integer.valueOf(tahun),user.getUser().getClientId().getClientId());
            }
            
            String[] labels = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};


            BarChart barChart = new BarChart(BarChart.Style.GLASS);

            for (int i = 0; i < labels.length; i++) {
                BarChart.Bar bar = new BarChart.Bar(values[i]);
                bar.setTooltip(Converter.getMoney(values[i]) + "");
                barChart.addBars(bar);
            }
            chart.addElements(barChart);

            XAxis xaxis = new XAxis();
            xaxis.addLabels(labels);
            chart.setXAxis(xaxis);

            int batasAtas = getMaxArrayValueInt(values);

            YAxis yaxis = new YAxis();
            yaxis.setMax((batasAtas * 20 / 100) + batasAtas);
            yaxis.setSteps(((batasAtas * 20 / 100) + batasAtas) / 10);
            chart.setYAxis(yaxis);

            result.addObject("chart", chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ModelAndView getClaimPerformancePerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("chartData");

        try {
        	
        	ActionUser user = securityService.getActionUser(request);        	

        	PerformanceStatisticDto stat = claimReportService.generateClaimMonitor();
        	
            Chart chart = new Chart("Claim Monitoring");
            
            
            String[] labels = {"Outstanding ", "Submitted ", "Verified ", "Checked ", 
            		"Pending Payment ", "Pending ", "Excess", "Cashless", "Reimbursement",};


            BarChart barChart = new BarChart(BarChart.Style.GLASS);
            
            if (stat.getTotalUnregisteredClaim() != null){
            	BarChart.Bar bar = new BarChart.Bar(stat.getTotalUnregisteredClaim());
                bar.setTooltip("Total Unregistered Claim : " + stat.getTotalUnregisteredClaim() );
                barChart.addBars(bar);
            }
            if (stat.getTotalSubmittedClaim() != null){
            	BarChart.Bar bar = new BarChart.Bar(stat.getTotalSubmittedClaim());
                bar.setTooltip("Total Submitted Claim : " + stat.getTotalSubmittedClaim() );
                barChart.addBars(bar);
            }
            
            if (stat.getTotalVerifiedClaim() != null){
            	BarChart.Bar bar = new BarChart.Bar(stat.getTotalVerifiedClaim());
                bar.setTooltip("Total Verified Claim : " + stat.getTotalVerifiedClaim() );
                barChart.addBars(bar);
            }
            if (stat.getTotalCheckedClaim() != null){
            	BarChart.Bar bar = new BarChart.Bar(stat.getTotalCheckedClaim());
                bar.setTooltip("Total Checked Claim : " + stat.getTotalCheckedClaim() );
                barChart.addBars(bar);
            }
            if (stat.getTotalPendingPaymentClaim() != null){
            	BarChart.Bar bar = new BarChart.Bar(stat.getTotalPendingPaymentClaim());
                bar.setTooltip("Total Pending Payment Claim : " + stat.getTotalPendingPaymentClaim() );
                barChart.addBars(bar);
            }
            if (stat.getTotalPendingClaim() != null){
            	BarChart.Bar bar = new BarChart.Bar(stat.getTotalPendingClaim());
                bar.setTooltip("Total Pending Claim : " + stat.getTotalPendingClaim() );
                barChart.addBars(bar);
            }
            if (stat.getTotalPendingExcess() != null){
            	BarChart.Bar bar = new BarChart.Bar(stat.getTotalPendingExcess());
                bar.setTooltip("Total Pending Excess : " + stat.getTotalPendingExcess() );
                barChart.addBars(bar);
            }
            if (stat.getTotalDueCashlessClaim()!= null){
            	BarChart.Bar bar = new BarChart.Bar(stat.getTotalDueCashlessClaim());
                bar.setTooltip("Total Due Cashless Claim : " + stat.getTotalDueCashlessClaim() );
                barChart.addBars(bar);
            }
            if (stat.getTotalDueReimbursementClaim() != null){
            	BarChart.Bar bar = new BarChart.Bar(stat.getTotalDueReimbursementClaim());
                bar.setTooltip("Total Due Reimbursement Claim : " + stat.getTotalDueReimbursementClaim());
                barChart.addBars(bar);
                
            }
            chart.addElements(barChart);

            XAxis xaxis = new XAxis();
            xaxis.addLabels(labels);
            chart.setXAxis(xaxis);

            int batasAtas = 2000;

            
            YAxis yaxis = new YAxis();
            
            yaxis.setMax((batasAtas * 20 / 100) + batasAtas);
            yaxis.setSteps(((batasAtas * 20 / 100) + batasAtas) / 10);
            chart.setYAxis(yaxis);

            result.addObject("chart", chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ModelAndView getClaimPercentagePerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("chartData");

        try {

        	ActionUser user = securityService.getActionUser(request);
        	
        	String navigation = WebUtil.getParameterString(request, "navigation", "");
            
            String[] navstr = navigation.split("-");
            String tahun = "2013";
            
            if (navstr.length == 1){
            	navigation = navstr[0];
            }
            else {
            	navigation = navstr[0];
            	tahun = navstr[1];
            }
            
            Chart chart = new Chart("Persentase Klaim dari Total Klaim (Dalam Ribuan)");
            Collection<Object[]> charts = new ArrayList<Object[]>();
            
            if(user.getUser().getUserType().intValue() == User.CLIENT && 
            		user.getUser().getClientId() !=null){
            	charts = claimReportService.generateClaimClientComparison(Integer.valueOf(tahun), user.getUser().getClientId().getClientId());
            }
            else{
            	charts = claimReportService.generateClaimGroupComparison(Integer.valueOf(tahun));
            }


            PieChart pieChart = new PieChart();           
            
            pieChart.setAnimate(true);
            
            pieChart.setColours("9999FF", "336699", "99CC33", "FFCC66", "9933FF");

            
            if (charts != null) {
                Iterator<Object[]> iterator = charts.iterator();

            
                while (iterator.hasNext()) {
                    
                    Object[] komparasi = iterator.next();
                    if(user.getUser().getRoleId().getRoleId() == Role.BRANCH && 
                    		user.getUser().getClientId() !=null){
                    	System.out.println("komparasi[2] : " + komparasi[1] + " komparasi [1] : " + komparasi[0]);
                        //pieChart.addSlice(Double.valueOf(komparasi[2] + "") / 1000,komparasi[1]+"");
                    	pieChart.addSlice(Double.valueOf(komparasi[2] + "") / 1000,komparasi[1]+"");
                    }else{
                    	System.out.println("komparasi[1] : " + komparasi[1] + " komparasi [0] : " + komparasi[0]);
                        pieChart.addSlice(Double.valueOf(komparasi[1] + "") / 1000,komparasi[0]+"");
                    }
                   
                 
                }
            }
            chart.addElements(pieChart);



            result.addObject("chart", chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ModelAndView getClientPremiumPercentagePerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("chartData");

        try {
        	
        	ActionUser user= securityService.getActionUser(request);
        	
        	String navigation = WebUtil.getParameterString(request, "navigation", "");
            
            String[] navstr = navigation.split("-");
            String tahun = "2013";
            
            if (navstr.length == 1){
            	navigation = navstr[0];
            }
            else {
            	navigation = navstr[0];
            	tahun = navstr[1];
            }

            Chart chart = new Chart("Persentase Premi dari Total Premi (Dalam Ribuan)");
            Collection<Object[]> charts = claimReportService.generateClientPremiumComparison(2010);


            PieChart pieChart = new PieChart();
            pieChart.setAnimate(true);
            pieChart.setColours("9999FF", "336699", "99CC33", "FFCC66", "9933FF");


            if (charts != null) {
                Iterator<Object[]> iterator = charts.iterator();

                while (iterator.hasNext()) {
                    Object[] komparasi = iterator.next();
                    pieChart.addSlice(Double.valueOf(komparasi[1] + "") / 1000, komparasi[0].toString());
                }
            }
            chart.addElements(pieChart);



            result.addObject("chart", chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public ModelAndView getClaimProviderPerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("chartData");

        try {
        	
        	ActionUser user = securityService.getActionUser(request);
        	
        	String navigation = WebUtil.getParameterString(request, "navigation", "");
            
            String[] navstr = navigation.split("-");
            String tahun = "2013";
            
            if (navstr.length == 1){
            	navigation = navstr[0];
            }
            else {
            	navigation = navstr[0];
            	tahun = navstr[1];
            }
            

            Chart chart = new Chart("Top 5 Persentase Klaim - Provider (Dalam Ribuan)");
            

            Collection<Object[]> charts = null;
            
            if (user != null && user.getUser().getUserType().intValue() == User.TPA){
            	charts = claimReportService.generateClaimProviderComparison(Integer.valueOf(tahun));
            }
            else if (user != null && user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            	charts = claimReportService.generateGroupClaimProviderComparison(Integer.valueOf(tahun),user.getUser().getMemberGroupId().getMemberGroupId());
            }
            else if (user != null && user.getUser().getUserType().intValue() == User.CLIENT){
            	if(user.getUser().getRoleId().getRoleId() == Role.BRANCH){
            		charts = claimReportService.generateClientClaimProviderComparison(Integer.valueOf(tahun),user.getUser().getClientId().getClientId());
            	}else{
            		charts = claimReportService.generateClientClaimProviderComparison(Integer.valueOf(tahun),user.getUser().getClientId().getClientId());
            	}
            }


            PieChart pieChart = new PieChart();
            pieChart.setAnimate(true);
            pieChart.setColours("9999FF", "336699", "99CC33", "FFCC66", "9933FF");


            if (charts != null) {
                Iterator<Object[]> iterator = charts.iterator();

                while (iterator.hasNext()) {
                    Object[] komparasi = iterator.next();
                    System.out.println("komparasi[1] : " + komparasi[1] + " komparasi [0] : " + komparasi[0]);
                    pieChart.addSlice(Double.valueOf(komparasi[1] + "") / 1000, komparasi[0].toString());
                }
            }
            chart.addElements(pieChart);



            result.addObject("chart", chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public ModelAndView getClaimDiagnosisPerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("chartData");

        try {

        	ActionUser user = securityService.getActionUser(request);
        	
        	String navigation = WebUtil.getParameterString(request, "navigation", "");
            
            String[] navstr = navigation.split("-");
            String tahun = "2013";
            
            if (navstr.length == 1){
            	navigation = navstr[0];
            }
            else {
            	navigation = navstr[0];
            	tahun = navstr[1];
            }
            
            Collection<Object[]> charts = null;
            
            if (user != null && user.getUser().getUserType().intValue() == User.TPA){
            	charts =  claimReportService.generateClaimDiagnosisComparison(Integer.valueOf(tahun));
            }
            else if (user != null && user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            	charts =  claimReportService.generateGroupClaimDiagnosisComparison(Integer.valueOf(tahun),user.getUser().getMemberGroupId().getMemberGroupId());
            }
            else if (user != null && user.getUser().getUserType().intValue() == User.CLIENT){
            	charts =  claimReportService.generateClientClaimDiagnosisComparison(Integer.valueOf(tahun),user.getUser().getClientId().getClientId());
            }

            Chart chart = new Chart("Top 5 Persentase Klaim - Diagnosis (Dalam Ribuan)");
            
            


            PieChart pieChart = new PieChart();
            pieChart.setAnimate(true);
            pieChart.setColours("9999FF", "336699", "99CC33", "FFCC66", "9933FF");


            if (charts != null) {
                System.out.println("Total Chart : " + charts.size());
                Iterator<Object[]> iterator = charts.iterator();

                while (iterator.hasNext()) {
                    Object[] komparasi = iterator.next();
                    System.out.println("komparasi[1] : " + komparasi[1] + " komparasi [0] : " + komparasi[0]);
                    pieChart.addSlice(Double.valueOf(komparasi[1] + "") / 1000, komparasi[0].toString());
                }
            }
            chart.addElements(pieChart);



            result.addObject("chart", chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ModelAndView dashboardPerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("dashboardClient");

        try {
            
            Calendar cal = Calendar.getInstance();
            String dashboardURL = "dashboard";
            String tipe = WebUtil.getParameterString(request, "tipe", "");
            String tahunStr = WebUtil.getParameterString(request, "tahun","");
            int tahun = cal.get(Calendar.YEAR);
            
            if (!tahunStr.equalsIgnoreCase("")){
                tahun = Integer.valueOf(tahunStr);
            }
            
            System.out.println("TAHUN NYA : " +tahun);
            
            result.addObject("tahun",tahun);                   
            
            
            if (tipe.equalsIgnoreCase("claimgrowth")){
                dashboardURL = "claimgrowth";
            }
            if (tipe.equalsIgnoreCase("membergrowth")){
                dashboardURL = "membergrowth";
            }
            if (tipe.equalsIgnoreCase("claimgroup")){
                dashboardURL = "groupclaim";
            }
            if (tipe.equalsIgnoreCase("claimprovider")){
                dashboardURL = "claimprovider";
            }
            if (tipe.equalsIgnoreCase("claimdiagnosis")){
                dashboardURL = "claimdiagnosis";
            }
            
            System.out.println("Dashboard URL : " + dashboardURL);
            result.addObject("tipe",tipe);
            result.addObject("reportYear", tahun);
            result.addObject("nav",dashboardURL);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public ModelAndView dashboardClaimPerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
//        ModelAndView result = new ModelAndView("dashboardClaimPerformance");
    	ModelAndView result = new ModelAndView("dashboardClaimMonitoringPerformance");

        try {            
            
            String dashboardURL = "dashboard";
                        
            System.out.println("Dashboard URL : " + dashboardURL);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public ModelAndView dashboardClaimMonitoringPerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("dashboardClaimMonitoringPerformance");

        try {            
            
            String dashboardURL = "dashboard";
                        
            System.out.println("Dashboard URL : " + dashboardURL);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public ModelAndView statisticPerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("dashboardStatistic");

        try {            
            
        	User currentUser = securityService.getCurrentUser(request);
        	
        	if (currentUser.getUserType().intValue() == User.CLIENT){
        		Integer clientId = currentUser.getClientId().getClientId();
        		
        		Collection<ClaimStatisticDto> claimants = claimReportService.generateTop10ClaimantReport(clientId);
        		Collection<ClaimStatisticDto> upgrades = claimReportService.generateTop10ClaimUpgrade(clientId);
        		
        		Collection<ClaimStatisticDto> providers = claimReportService.generateTop10ProviderReport(clientId);
        		Collection<ClaimStatisticDto> diagnosis = claimReportService.generateTop10DiagnosisReport(clientId);
        		
        		result.addObject("Top10Claimants", claimants);
        		result.addObject("Top10Providers", providers);
        		result.addObject("Top10Diagnosis", diagnosis);
        		result.addObject("Top10Upgrades", upgrades);
        	}
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public ModelAndView queryPerformed(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("queryReport");

        try {
            
            String dashboardURL = "dashboard";
            String tipe = WebUtil.getParameterString(request, "reportFormat", "");
            String serviceType = WebUtil.getParameterString(request, "serviceType", "");
            String claimType = WebUtil.getParameterString(request, "claimTypeType", "");
            
            result.addObject("dashboardURL",dashboardURL);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        String navigation = request.getParameter("navigation") == null ? "welcome"
                : request.getParameter("navigation");

        String subnavigation = WebUtil.getParameterString(request, "subnavigation", "");

        String[] navstr = navigation.split("-");
        String reportYear = "";
        
        if (navstr.length == 1){
        	navigation = navstr[0];
        }
        else {
        	navigation = navstr[0];
        	reportYear = navstr[1];
        }
        Object user = null;

        ModelAndView result = null;
        HttpSession session = request.getSession(false);
        
        //--
        
      //Add by aju on 20150410, for client iFrame support :D
      		String usingIFrame = WebUtil.getParameterString(request, "iframe", "no");
      		//modified by aju on 20150417, for security purpose, use toket eeh token :D
//      		String iFrameUser = WebUtil.getParameterString(request, "uid", "");
//      		String iFramePass = WebUtil.getParameterString(request, "pid", "");
//      		String iFrameLevelLogin = WebUtil.getParameterString(request, "level", "");
      		String iFrameUser = "", iFramePass = "", iFrameLevelLogin = "";
      		
      		//Add by aju on 20150417, read the token :D
      		String iFrameToken = WebUtil.getParameterString(request, "token", "");
      		if(iFrameToken.length()>0){
      			String decodedToken = SecurityUtil.saltDecrypt(iFrameToken);
      			System.out.println("Decoded Token : " + decodedToken);
      			if(decodedToken!=iFrameToken){
      				String[] splitToken = decodedToken.split("&");
      				if(splitToken.length>0 && splitToken.length<5){
      					iFrameUser = splitToken[0].replace("uid=", "");
      					iFramePass = splitToken[1].replace("pid=", "");
      					iFrameLevelLogin = splitToken[2].replace("level=", "");
      					System.out.println("splitDecode=>uid="+iFrameUser+"&pid="+iFrameUser+"&level="+iFrameLevelLogin);
      				}
      			}
      		}
      		
      		//check if there's session for iframe
      		boolean isIFrameSession = false;
      		if(session != null){
      			if(session.getAttribute("iframe") != null){
      				isIFrameSession = (session.getAttribute("iframe").toString().equalsIgnoreCase("yes")?true:false);
      			}
      		}

      		if(isIFrameSession){
      			//get the iFrame saved parameter from session
      			usingIFrame = session.getAttribute("iframe").toString();
      			
      			if(session.getAttribute("theMember")!=null){
      				isIFrameSession=false;

      				session.removeAttribute("theMember");
      				session.removeAttribute("clientMemberId");
      			}
      			
      		}
      		
      		if(usingIFrame.equalsIgnoreCase("yes")){
      			//check if there's iframe session
      			if(!isIFrameSession){
      				//make the dummy login
      				String sessId = session == null ? "" : session.getId();
      				//Check if they can do login
      				boolean res = userService.login(iFrameUser, iFramePass, sessId,	request.getRemoteAddr());
      				
      				if(res){
      					System.out.println("IFRAME ("+ iFrameLevelLogin +") login success");
      					
      					User theUser = userService.getUser(iFrameUser);
      					
      					theUser.setPassword("");
      					
      					session.setAttribute("theUser", theUser);
      					session.setAttribute("iframe", usingIFrame);
      					//Add by aju on 20150410, add level login filtering :D
      					session.setAttribute("levelLogin", iFrameLevelLogin);
      					
      					isIFrameSession = true;
      				}
      				else{
      					//If login failed, redirect to error  page
      					System.out.println("IFRAME ("+ iFrameLevelLogin +") login failed!");
      					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
      					errorResult.addObject("errorType","iFrameAccessDenied");			
      					errorResult.addObject("errorMessage", "You Are Not Authorized for IFRAME access");
      					return errorResult;
      				}
      			}
      			
      		}
		
		

        //--
        System.out.println(request.getRequestURI() + " ATAU " + request.getRequestURL().toString());
        ModelAndView model = null;
        
        String breadcrumb = "";
        try {
            if (navigation.equalsIgnoreCase("pusat")) {
                model = dashboardPusatPerformed(request, response);
            } else if (navigation.equalsIgnoreCase("membergrowth")) {
            	request.setAttribute("reportYear", reportYear);
                model = getMemberGrowthPerformed(request, response);
                
            } else if (navigation.equalsIgnoreCase("premigrowth")) {
            	request.setAttribute("reportYear", reportYear);
                model = getPremiumGrowthPerformed(request, response);
                
            } else if (navigation.equalsIgnoreCase("claimgrowth")) {
            	request.setAttribute("reportYear", reportYear);
                model = getClaimGrowthPerformed(request, response);
                
            } else if (navigation.equalsIgnoreCase("groupclaim")) {
            	request.setAttribute("reportYear", reportYear);
                model = getClaimPercentagePerformed(request, response);
                
            } else if (navigation.equalsIgnoreCase("clientpremium")) {
                model = getClientPremiumPercentagePerformed(request, response);
                
            } 
            else if (navigation.equalsIgnoreCase("queryclaimstat")) {
                model = getClaimPerformancePerformed(request, response);
                
            }
            else if (navigation.equalsIgnoreCase("claimdiagnosis")) {
                model = getClaimDiagnosisPerformed(request, response);
                
            } else if (navigation.equalsIgnoreCase("claimprovider")) {
                model = getClaimProviderPerformed(request, response);
                
            } else if (navigation.equalsIgnoreCase("dashboard")){
                model = dashboardPerformed(request, response);
                String nav = request.getParameter("navigation");
                String tipe = request.getParameter("tipe");
                breadcrumb = "<a href=\"dashboard?navigation="+nav+"&tipe="+tipe+"&searchby=&index=&searchtext=\" class=\"linkbreadcrumb\">Claim Monitoring Dashboard";
            }
            else if (navigation.equalsIgnoreCase("dashboardclaimstat")){
                model = dashboardClaimPerformed(request, response);
                String nav = request.getParameter("navigation");
                breadcrumb = "<a href=\"dashboard?navigation="+nav+"\" class=\"linkbreadcrumb\">Claim Monitoring Dashboard";
            }
            else if (navigation.equalsIgnoreCase("dashboardclaimmonitoring")){
                model = dashboardClaimMonitoringPerformed(request, response);
                String nav = request.getParameter("navigation");
                breadcrumb = "<a href=\"dashboard?navigation="+nav+"\" class=\"linkbreadcrumb\">Claim Monitoring Dashboard";
            }
            else if (navigation.equalsIgnoreCase("statistic")){
                model = statisticPerformed(request, response);
            }
            else {
                model = queryPerformed(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("breadcrumb", breadcrumb);
        return model;
    }
   }
