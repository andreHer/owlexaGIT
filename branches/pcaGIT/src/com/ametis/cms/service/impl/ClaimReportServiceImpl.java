package com.ametis.cms.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.ametis.cms.dao.ClaimDao;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.TreatmentUpgradeType;
import com.ametis.cms.dto.ClaimStatisticDto;
import com.ametis.cms.dto.PerformanceStatisticDto;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimReportService;
import com.ametis.cms.util.Converter;

public class ClaimReportServiceImpl implements ClaimReportService {

    private ClaimDao claimDao;
    

    public ClaimDao getClaimDao() {
        return claimDao;
    }

    public void setClaimDao(ClaimDao claimDao) {
        this.claimDao = claimDao;
    }
    /*
     * Output :
     *
     * Patient Membership Number Relationship No Claim Claim Charge Benefit Paid
     * % Total
     *
     */

    public Collection<Object[]> generateClaimReport(Integer serviceType,
            Integer memberGroupId, Date start, Date end, Integer totalIndex) throws Exception {
        // TODO Auto-generated method stub
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();
        if (session != null) {


            Query query = session.createQuery("SELECT m.firstName, m.customerPolicyNumber, m.relationship, m.customerNumber, count(c.claimNumber) as totalClaim, sum(c.claimChargeValue) as charge, sum(c.claimApprovedValue) as paid, sum(c.claimPaidValue), m.memberId FROM Claim c, Member m WHERE c.memberId.memberGroupId.memberGroupId = :memberGroupId AND c.claimStatus.caseStatusId = :claimStatus"
                    + " AND c.claimDate >= :startDate AND c.claimDate <= :endDate AND m.memberId = c.memberId.memberId AND c.caseCategoryId.caseCategoryId = :serviceType GROUP BY m.memberId ORDER BY sum(c.claimApprovedValue) DESC");

            query.setInteger("memberGroupId", memberGroupId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("serviceType", serviceType);
            query.setDate("startDate", start);
            query.setDate("endDate", end);

            if (totalIndex != null) {
                query.setMaxResults(totalIndex);
            }

            Collection res = query.list();

            if (res != null) {


                double sumPaid = 0;

                Iterator<Object[]> iterator = res.iterator();

                while (iterator.hasNext()) {
                    Object[] resultList = iterator.next();

                    if (resultList != null) {
                        Double benefitPaid = (Double) resultList[6];

                        System.out.println("BENEFIT PAID : " + benefitPaid);
                        if (benefitPaid != null) {
                            sumPaid += benefitPaid;
                        }
                    }
                }

                Iterator<Object[]> settingPercentage = res.iterator();

                if (settingPercentage != null) {

                    result = new Vector<Object[]>();

                    while (settingPercentage.hasNext()) {
                        Object[] current = settingPercentage.next();

                        current[7] = ((Double) current[6] / sumPaid) * 100;

                        result.add(current);

                    }
                }
            }

        }

        return result;
    }

    public Collection<Object[]> generateDiagnosisReport(Integer serviceType,
            Integer memberGroupId, Date start, Date end, Integer totalIndex) throws Exception {
        // TODO Auto-generated method stub
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();
        if (session != null) {


            Query query = session.createQuery("SELECT d.description, count(DISTINCT c.memberId) as member, count(c.memberId) as totalClaim, sum(c.claimChargeValue) as totalCharge"
                    + " , sum(c.claimApprovedValue) as paid, avg(c.claimChargeValue) as averageCharge, sum(c.claimPaidValue) as paidPercentage FROM Claim c, Diagnosis d WHERE c.memberId.memberGroupId.memberGroupId = :memberGroupId AND c.claimStatus.caseStatusId = :claimStatus"
                    + " AND c.claimDate >= :startDate AND c.claimDate <= :endDate  AND c.caseCategoryId.caseCategoryId = :serviceType AND c.diagnosisId.diagnosisId = d.diagnosisId GROUP BY  d.diagnosisId ORDER BY sum(c.claimApprovedValue) DESC");

            query.setInteger("memberGroupId", memberGroupId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("serviceType", serviceType);
            query.setDate("startDate", start);
            query.setDate("endDate", end);

            if (totalIndex != null) {
                query.setMaxResults(totalIndex);
            }

            Collection res = query.list();

            if (res != null) {


                double sumPaid = 0;

                Iterator<Object[]> iterator = res.iterator();

                while (iterator.hasNext()) {
                    Object[] resultList = iterator.next();

                    if (resultList != null) {
                        Double benefitPaid = (Double) resultList[4];
                        if (benefitPaid != null) {
                            sumPaid += benefitPaid;
                        }
                    }
                }

                Iterator<Object[]> settingPercentage = res.iterator();

                if (settingPercentage != null) {

                    result = new Vector<Object[]>();

                    while (settingPercentage.hasNext()) {
                        Object[] current = settingPercentage.next();

                        current[6] = ((Double) current[4] / sumPaid) * 100;

                        result.add(current);

                    }
                }
            }

        }

        return result;
    }

    public Collection<Object[]> generateProviderReport(Integer serviceType,
            Integer memberGroupId, Date start, Date end, Integer totalIndex) throws Exception {
        // TODO Auto-generated method stub
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();
        if (session != null) {


            Query query = session.createQuery("SELECT p.providerName, count(c.memberId) as totalClaim, sum(c.claimChargeValue) as totalCharge"
                    + " , sum(c.claimApprovedValue) as paid, avg(c.claimChargeValue) as averageCharge, sum(c.claimPaidValue) as paidPercentage FROM Claim c, Provider p WHERE c.memberId.memberGroupId.memberGroupId = :memberGroupId AND c.claimStatus.caseStatusId = :claimStatus"
                    + " AND c.claimDate >= :startDate AND c.claimDate <= :endDate  AND c.caseCategoryId.caseCategoryId = :serviceType AND c.providerId.providerId = p.providerId GROUP BY  p.providerId ORDER BY sum(c.claimApprovedValue) DESC");

            query.setInteger("memberGroupId", memberGroupId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("serviceType", serviceType);
            query.setDate("startDate", start);
            query.setDate("endDate", end);

            if (totalIndex != null) {
                query.setMaxResults(totalIndex);
            }

            Collection res = query.list();

            if (res != null) {


                double sumPaid = 0;

                Iterator<Object[]> iterator = res.iterator();

                while (iterator.hasNext()) {
                    Object[] resultList = iterator.next();

                    if (resultList != null) {
                        Double benefitPaid = (Double) resultList[3];
                        if (benefitPaid != null) {
                            sumPaid += benefitPaid;
                        }
                    }
                }

                Iterator<Object[]> settingPercentage = res.iterator();

                if (settingPercentage != null) {

                    result = new Vector<Object[]>();

                    while (settingPercentage.hasNext()) {
                        Object[] current = settingPercentage.next();

                        current[5] = ((Double) current[3] / sumPaid) * 100;

                        result.add(current);

                    }
                }
            }

        }

        return result;
    }

    public Collection<Object[]> generateTrackReport(Integer serviceType,
            Integer memberGroupId, Integer status, Date start, Date end) throws Exception {
        // TODO Auto-generated method stub
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {

            String queryStr = "SELECT c.claimNumber,c.claimDate, c.memberName,c.memberNumber,c.groupName,c.providerName,c.claimChargeValue"
                    + ",c.claimApprovedValue,c.exGratiaValue,c.claimExcessValue,c.diagnosis1Code,c.paymentGeneratedDate,c.paymentNumber,"
                    + "c.paymentConfirmedDate FROM Claim c ";


            if (serviceType != null || memberGroupId != null || status != null || start != null || end != null) {
                queryStr += " WHERE ";
            }
            int andClause = 0;

            if (serviceType != null || serviceType.intValue() > 0) {
                queryStr += " c.caseCategoryId.caseCategoryId = :caseCategory ";
                andClause += 1;
            }

            if (memberGroupId != null) {
                if (andClause == 1) {
                    queryStr += " AND ";
                }
                queryStr += " c. ";
            }

            Query query = session.createQuery(queryStr);

            query.setInteger("memberGroupId", memberGroupId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("serviceType", serviceType);
            query.setDate("startDate", start);
            query.setDate("endDate", end);



            Collection res = query.list();

            if (res != null) {


                double sumPaid = 0;

                Iterator<Object[]> iterator = res.iterator();

                while (iterator.hasNext()) {
                    Object[] resultList = iterator.next();

                    if (resultList != null) {
                        Double benefitPaid = (Double) resultList[3];
                        if (benefitPaid != null) {
                            sumPaid += benefitPaid;
                        }
                    }
                }

                Iterator<Object[]> settingPercentage = res.iterator();

                if (settingPercentage != null) {

                    result = new Vector<Object[]>();

                    while (settingPercentage.hasNext()) {
                        Object[] current = settingPercentage.next();

                        current[5] = ((Double) current[3] / sumPaid) * 100;

                        result.add(current);

                    }
                }
            }

        }

        return result;
    }

    public Collection<Object[]> generateTrackReportSQL(Integer serviceType,
            Integer memberGroupId, Integer status, Date start, Date end) throws Exception {
        // TODO Auto-generated method stub
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {

            String sqlQueryStr = "SELECT c.claimNumber,c.claimDate, c.memberName,c.memberNumber,c.groupName,c.providerName,c.claimChargeValue"
                    + ",c.claimApprovedValue,c.exGratiaValue,c.claimExcessValue,c.diagnosis1Code,c.paymentGeneratedDate,c.paymentNumber,"
                    + "c.paymentConfirmedDate FROM Claim c WHERE c.claimDate >= :startDate AND c.claimDate <= :endDate ";



            if (serviceType != null || serviceType.intValue() > 0) {
                sqlQueryStr += " AND c.caseCategoryId = :caseCategory ";

            }

            if (memberGroupId != null) {

                sqlQueryStr += " AND c.memberGroupId = :memberGroupId ";
            }

            if (status != null) {
                sqlQueryStr += " AND c.status = :status";
            }

            SQLQuery query = session.createSQLQuery(sqlQueryStr);

            query.setDate("startDate", start);
            query.setDate("endDate", end);

            if (memberGroupId != null) {
                query.setInteger("memberGroupId", memberGroupId);
            }
            if (status != null) {
                query.setInteger("status", status);
            }
            if (serviceType != null) {
                query.setInteger("serviceType", serviceType);
            }




            result = (Collection<Object[]>) query.list();


        }

        return result;
    }

    public Collection<Object[]> generateClaimGroupComparison(Integer year)
            throws Exception {
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {

//            String sqlQueryStr = "SELECT mg.groupName as group, sum(c.claimApprovedValue) as hasil "
//                    + "FROM MemberGroup mg, Claim c WHERE c.memberId.memberGroupId.memberGroupId = mg.memberGroupId"
//                    + " AND c.deletedStatus = 0 AND c.claimDate LIKE :tahun "
//                    + "GROUP BY mg.groupName ";

            String sql = "select mg.group_name, sum(c.claim_approved_value) as hasil FROM member_group mg, " +
            		" claim c, member m WHERE m.member_id = c.member_id AND m.member_group_id = " +
            		" mg.member_group_id AND c.deleted_status = 0 AND  c.claim_date  BETWEEN '"+year+"-01-01' AND '"+year+"-12-31' GROUP BY mg.group_name ORDER BY hasil DESC";

            System.out.println("tahun : " + year);
            Query query = session.createSQLQuery(sql);
            //query.setString("tahun", "%" + year + "%");
            query.setMaxResults(5);

            result = (Collection<Object[]>) query.list();

            System.out.println(query.getQueryString());
            System.out.println("total Result : " + result.size()) ;

        }

        return result;
    }
    
    public Collection<Object[]> generateClaimClientComparison(Integer year, Integer clientId)
            throws Exception {
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {


           // String sql = "select mg.group_name, sum(c.claim_approved_value) as hasil FROM member_group mg, " +
           // 		" claim c, member m WHERE m.member_id = c.member_id AND m.member_group_id = " +
           // 		" mg.member_group_id AND c.deleted_status = 0 AND  c.claim_date  BETWEEN '"+year+"-01-01' AND '"+year+"-12-31' GROUP BY mg.group_name ORDER BY hasil DESC";

           /* String sql = "select  b.client_id, " +
            		"b.client_name, " +
            		"ceil(sum(A.claim_approved_value)) as total_claim_client, " +
            		"ceil((select sum(claim_approved_value) from claim where deleted_status = 0)) as total_claim_keseluruhan " +
            		"from claim as a join client as b on a.client_id = b.client_id " +
            		"join member_group as c on a.member_group_id = c.member_group_id " +
            		"where a.deleted_status = 0 and b.deleted_status = 0 and c.deleted_status = 0 and b.client_id = "+clientId+" and a.claim_status not in (-1,4) AND  a.claim_date  BETWEEN '"+year+"-01-01' AND '"+year+"-12-31' " +
            		" group by b.client_id";
            */
        	
        	String sql = "select c.member_group_id, " +
        			"c.group_name, " +
        			"ceil(sum(a.claim_approved_value)) as total_claim_group, " +
        			"ceil((select sum(aa.claim_approved_value) " +
        			"from claim as aa join client as bb on aa.client_id = bb.client_id " +
        			"join member_group as cc on aa.client_id = cc.client_id " +
        			"where aa.deleted_status = 0 and bb.deleted_status = 0 and cc.deleted_status = 0 and bb.client_id= "+clientId+")) as total_claim_keseluruhan " +
        			"from claim as a join client as b on a.client_id = b.client_id " +
        			"join member_group as c on a.client_id = c.client_id where a.deleted_status = 0 and b.deleted_status = 0 " +
        			"and c.deleted_status = 0 and b.client_id = "+clientId+" and a.claim_status not in (-1,4) and  a.claim_date  BETWEEN '"+year+"-01-01' AND '"+year+"-12-31' " +
        					"group by c.member_group_id";
            System.out.println("tahun : " + year);
            Query query = session.createSQLQuery(sql);
            //query.setString("tahun", "%" + year + "%");
            query.setMaxResults(5);

            result = (Collection<Object[]>) query.list();

            System.out.println(query.getQueryString());
            System.out.println("total Result : " + result.size()) ;

        }

        return result;
    }

    public Collection<Object[]> generateClientPremiumComparison(Integer year)
            throws Exception {

        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {

            String sqlQueryStr = "SELECT c.clientName as cabang, sum(m.currentAnnualPremium) As premi "
                    + "FROM Client c, Member m , MemberGroup mg WHERE mg.clientId = c.clientId AND m.memberGroupId = mg.memberGroupId "
                    + " AND m.deletedStatus = 0 AND m.effectiveDate LIKE :tahun "
                    + "GROUP BY c.clientName ";

            String queryStr = "SELECT c.client_name as cabang, sum(m.current_annual_premium) as premi "
                    + "FROM client c, member m , member_group mg WHERE mg.client_id = c.client_id AND m.member_group_id = mg.member_group_id "
                    + " AND m.deleted_status = 0 AND m.effective_date LIKE :tahun "
                    + " GROUP BY c.client_name ORDER BY premi DESC";


            Query query = session.createQuery(sqlQueryStr);
            query.setString("tahun", "%" + year + "%");
            query.setMaxResults(5);

            result = (Collection<Object[]>) query.list();


        }

        return result;
    }

    public Collection<Object[]> generateClaimStatisticReport(String keyword,
            String keyCategory, Date start, Date end, Integer status,
            Integer jenisLayanan, Integer jenisClaim) throws Exception {
        // TODO Auto-generated method stub

        Collection<Object[]> result = null;

        try {
            Session session = claimDao.getClaimSession();

            String clause = " WHERE deleted_status = 0";
            String dateClause = "";


            if (keyCategory != null) {

                if (keyCategory.equalsIgnoreCase("providerName")) {
                    clause += " AND provider_name LIKE '%" + keyword + "%'";
                }
                if (keyCategory.equalsIgnoreCase("memberName")) {
                    clause += " AND member_name LIKE '%" + keyword + "%'";
                }
                if (keyCategory.equalsIgnoreCase("policyNumber")) {
                    clause += " AND member_number LIKE '%" + keyword + "%'";
                }
                if (keyCategory.equalsIgnoreCase("memberGroupName")) {
                    clause += " AND group_name LIKE '%" + keyword + "%'";
                }

                if (keyCategory.equals("clientName")) {
                    clause += " AND client_name LIKE '%" + keyword + "%'";
                }

                if (keyCategory.equalsIgnoreCase("officer")) {
                    clause += " AND created_by LIKE '%" + keyword + "%'";
                }

                if (keyCategory.equalsIgnoreCase("paymentNumber")) {
                    clause += " AND payment_number LIKE '%" + keyword + "%'";
                }
                if (keyCategory.equalsIgnoreCase("giroNumber")) {
                    clause += " AND payment_id IN (SELECT payment_id FROM payment WHERE giro_number LIKE '%" + keyword + "%')";
                }

            }

            if (!clause.equalsIgnoreCase("") && status != null) {
                clause += " AND claim_status >= '" + status + "'";
            }
            if (clause.equalsIgnoreCase("") && status != null) {
                clause = " WHERE AND claim_status = '" + status + "'";
            }
            if (!clause.equalsIgnoreCase("") && jenisClaim != null) {
                clause += " AND claim_type_id = '" + jenisClaim + "'";
            }
            if (clause.equalsIgnoreCase("") && jenisClaim != null) {
                clause = " WHERE AND claim_type_id = '" + jenisClaim + "'";
            }
            if (!clause.equalsIgnoreCase("") && jenisLayanan != null) {
                clause += " AND case_category_id = '" + jenisLayanan + "'";
            }
            if (clause.equalsIgnoreCase("") && jenisLayanan != null) {
                clause = " WHERE AND case_category_id = '" + jenisLayanan + "'";
            }

            if (!clause.equalsIgnoreCase("") && start != null && end != null) {
                //dateClause = " AND created_time >= '"+start.toString()+"' AND created_time <= '"+end.toString()+"'";
                dateClause = " AND claim_date >= '" + start.toString() + "' AND claim_date <= '" + end.toString() + "'";
            }
            if (clause.equalsIgnoreCase("") && start != null && end != null) {
                dateClause = " WHERE claim_date >= '" + start.toString() + "' AND claim_date <= '" + end.toString() + "'";
                //dateClause = " WHERE created_time >= '"+start.toString()+"' AND created_time <= '"+end.toString()+"'";
            }



            String query = "SELECT * FROM claim " + clause + dateClause;
            System.out.println(query);

            SQLQuery sqlQuery = session.createSQLQuery(query);

            result = sqlQuery.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Collection<Object> generateClaimReport(String keyword,
            String keyCategory, Date start, Date end, Integer status,
            Integer jenisLayanan, Integer jenisClaim) throws Exception {
        // TODO Auto-generated method stub

        Collection<Object> result = null;

        try {
            Session session = claimDao.getClaimSession();

            String clause = " ";
            String dateClause = "";

            System.out.println("Key Category : " + keyCategory);

            if (keyCategory != null) {

                if (keyCategory.equalsIgnoreCase("providerName") || keyCategory.equalsIgnoreCase("Provider Name")) {
                    clause += ",provider p WHERE c.provider_id = p.provider_id AND p.provider_name LIKE '%" + keyword + "%'";
                }
                if (keyCategory.equalsIgnoreCase("memberName") || keyCategory.equalsIgnoreCase("Member Name")) {
                    clause += ",member m WHERE c.member_id = m.member_id AND m.first_name LIKE '%" + keyword + "%'";
                }
                if (keyCategory.equalsIgnoreCase("policyNumber") || keyCategory.equalsIgnoreCase("Policy Number")) {
                    clause += ",member m WHERE c.member_id = m.member_id AND m.customer_policy_number LIKE '%" + keyword + "%'";
                }
                if (keyCategory.equalsIgnoreCase("memberGroupName") || keyCategory.equalsIgnoreCase("Member Group Name")) {
                    clause += ",member m, member_group mg WHERE c.member_id = m.member_id AND m.member_group_id = mg.member_group_id AND mg.group_name LIKE '%" + keyword + "%'";
                }

                if (keyCategory.equals("clientName") || keyCategory.equalsIgnoreCase("Client Name")) {
                    clause += ", member m, client cl WHERE c.member_id = m.member_id AND m.client_id = cl.client_id AND cl.client_name LIKE '%" + keyword + "%'";
                }

                if (keyCategory.equalsIgnoreCase("officer") || keyCategory.equalsIgnoreCase("Officer")) {
                    clause += " WHERE c.created_by LIKE '%" + keyword + "%'";
                }

                if (keyCategory.equalsIgnoreCase("paymentNumber") || keyCategory.equalsIgnoreCase("Payment Number")) {
                    clause += ", payment p WHERE c.payment_id = p.payment_id AND p.payment_number LIKE '%" + keyword + "%'";
                }
                if (keyCategory.equalsIgnoreCase("giroNumber") || keyCategory.equalsIgnoreCase("Giro Number")) {
                    clause += ", payment p WHERE c.payment_id = p.payment_id AND p.giro_number LIKE '%" + keyword + "%'";
                }
            }
            System.out.println("STATUS : " + status + " CLAUSE : " + clause);

            if (!clause.equalsIgnoreCase("") && status != null && !status.equals(0)) {
                clause += " AND c.claim_status = '" + status + "'";
            }
            if (clause.equalsIgnoreCase("") && status != null && !status.equals(0)) {
                clause = " WHERE c.claim_status = '" + status + "'";
            }
            if (!clause.equalsIgnoreCase("") && jenisClaim != null && !jenisClaim.equals(0)) {
                clause += " AND c.claim_type_id = '" + jenisClaim + "'";
            }
            if (clause.equalsIgnoreCase("") && jenisClaim != null && !jenisClaim.equals(0)) {
                clause = " WHERE c.claim_type_id = '" + jenisClaim + "'";
            }
            if (!clause.equalsIgnoreCase("") && jenisLayanan != null && !jenisLayanan.equals(0)) {
                clause += " AND c.case_category_id = '" + jenisLayanan + "'";
            }
            if (clause.equalsIgnoreCase("") && jenisLayanan != null && !jenisLayanan.equals(0)) {
                clause = " WHERE c.case_category_id = '" + jenisLayanan + "'";
            }

            if (!clause.equalsIgnoreCase("") && start != null && end != null) {
                //dateClause = " AND created_time >= '"+start.toString()+"' AND created_time <= '"+end.toString()+"'";
                dateClause = " AND c.claim_date >= '" + start.toString() + "' AND c.claim_date <= '" + end.toString() + "'";
            }
            if (clause.equalsIgnoreCase("") && start != null && end != null) {
                dateClause = " WHERE c.claim_date >= '" + start.toString() + "' AND c.claim_date <= '" + end.toString() + "'";
                //dateClause = " WHERE created_time >= '"+start.toString()+"' AND created_time <= '"+end.toString()+"'";
            }

            clause += " AND c.deleted_status = 0 AND c.tipe = 0";


            String query = "SELECT c.claim_id FROM claim c " + clause + dateClause;
            System.out.println(query);

            SQLQuery sqlQuery = session.createSQLQuery(query);

            result = sqlQuery.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Integer getTotalClaimStatisticReport(String keyword,
            String keyCategory, Date start, Date end, Integer status,
            Integer jenisLayanan, Integer jenisClaim) throws Exception {
        // TODO Auto-generated method stub
        Integer result = null;

        try {
            Session session = claimDao.getClaimSession();

            String clause = " ";
            String dateClause = "";


            if (keyCategory != null) {

                if (keyCategory.equalsIgnoreCase("providerName") || keyCategory.equalsIgnoreCase("Provider Name")) {
                    clause += ",provider p WHERE c.provider_id = p.provider_id AND p.provider_name LIKE '%" + keyword + "%'";
                }
                if (keyCategory.equalsIgnoreCase("memberName") || keyCategory.equalsIgnoreCase("Member Name")) {
                    clause += ",member m WHERE c.member_id = m.member_id AND m.first_name LIKE '%" + keyword + "%'";
                }
                if (keyCategory.equalsIgnoreCase("policyNumber") || keyCategory.equalsIgnoreCase("Policy Number")) {
                    clause += ",member m WHERE c.member_id = m.member_id AND m.customer_policy_number LIKE '%" + keyword + "%'";
                }
                if (keyCategory.equalsIgnoreCase("memberGroupName") || keyCategory.equalsIgnoreCase("Member Group Name")) {
                    clause += ",member m, member_group mg WHERE c.member_id = m.member_id AND m.member_group_id = mg.member_group_id AND mg.group_name LIKE '%" + keyword + "%'";
                }

                if (keyCategory.equals("clientName") || keyCategory.equalsIgnoreCase("Client Name")) {
                    clause += ", member m, client cl WHERE c.member_id = m.member_id AND m.client_id = cl.client_id AND cl.client_name LIKE '%" + keyword + "%'";
                }

                if (keyCategory.equalsIgnoreCase("officer") || keyCategory.equalsIgnoreCase("Officer")) {
                    clause += " WHERE c.created_by LIKE '%" + keyword + "%'";
                }

                if (keyCategory.equalsIgnoreCase("paymentNumber") || keyCategory.equalsIgnoreCase("Payment Number")) {
                    clause += ", payment p WHERE c.payment_id = p.payment_id AND p.payment_number LIKE '%" + keyword + "%'";
                }
                if (keyCategory.equalsIgnoreCase("giroNumber") || keyCategory.equalsIgnoreCase("Giro Number")) {
                    clause += ", payment p WHERE c.payment_id = p.payment_id AND p.giro_number LIKE '%" + keyword + "%'";
                }
            }

            if (!clause.equalsIgnoreCase("") && status != null && !status.equals(0)) {
                clause += " AND c.claim_status = '" + status + "'";
            }
            if (clause.equalsIgnoreCase("") && status != null && !status.equals(0)) {
                clause = " WHERE c.claim_status = '" + status + "'";
            }
            if (!clause.equalsIgnoreCase("") && jenisClaim != null && !jenisClaim.equals(0)) {
                clause += " AND c.claim_type_id = '" + jenisClaim + "'";
            }
            if (clause.equalsIgnoreCase("") && jenisClaim != null && !jenisClaim.equals(0)) {
                clause = " WHERE c.claim_type_id = '" + jenisClaim + "'";
            }
            if (!clause.equalsIgnoreCase("") && jenisLayanan != null && !jenisLayanan.equals(0)) {
                clause += " AND c.case_category_id = '" + jenisLayanan + "'";
            }
            if (clause.equalsIgnoreCase("") && jenisLayanan != null && !jenisLayanan.equals(0)) {
                clause = " WHERE c.case_category_id = '" + jenisLayanan + "'";
            }

            if (!clause.equalsIgnoreCase("") && start != null && end != null) {
                //dateClause = " AND created_time >= '"+start.toString()+"' AND created_time <= '"+end.toString()+"'";
                dateClause = " AND c.claim_date >= '" + start.toString() + "' AND c.claim_date <= '" + end.toString() + "'";
            }
            if (clause.equalsIgnoreCase("") && start != null && end != null) {
                dateClause = " WHERE c.claim_date >= '" + start.toString() + "' AND c.claim_date <= '" + end.toString() + "'";
                //dateClause = " WHERE created_time >= '"+start.toString()+"' AND created_time <= '"+end.toString()+"'";
            }

            clause += " AND c.deleted_status = 0 AND c.tipe = 0";



            String query = "SELECT count(claim_id) as total FROM claim c " + clause + dateClause;
            System.out.println(query);

            SQLQuery sqlQuery = session.createSQLQuery(query);

            List<Object> tmp = sqlQuery.list();

            if (tmp != null && tmp.size() > 0) {
                result = ((BigInteger) tmp.get(0)).intValue();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public HSSFWorkbook generateStatistic(Integer memberGroupId, Date start, Date end, Integer jenisLayanan, Integer jenisClaim) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();

        try {



            Session session = claimDao.getClaimSession();

            if (session != null) {

                HSSFSheet usageSheet = workbook.createSheet("General Statistic");

                String ctype = " c.claim_type_id = " + jenisClaim;

                String query = "SELECT cc.case_category_name, COUNT(c.claim_id), SUM(c.claim_charge_value), SUM(c.claim_approved_value) FROM claim c, case_category cc, member m, member_group mg ";
                query += "WHERE c.case_category_id = cc.case_category_id AND c.member_id = m.member_id AND m.member_group_id = mg.member_group_id "
                        + "AND c.deleted_status = 0 AND mg.member_group_id = " + memberGroupId + " AND "
                        + " c.claim_date >= :start AND c.claim_date <= :end GROUP BY cc.case_category_id ";

                SQLQuery sqlQuery = session.createSQLQuery(query);
                sqlQuery.setDate("start", start);
                sqlQuery.setDate("end", end);

                List<Object[]> hasilGeneralStatistic = sqlQuery.list();

                if (hasilGeneralStatistic != null) {
                    int rowNumber = 3;
                    HSSFRow header = usageSheet.createRow(rowNumber);
                    header.createCell((short) 0).setCellValue("Service Type");
                    header.createCell((short) 1).setCellValue("Total Claim");
                    header.createCell((short) 2).setCellValue("Claim Charge");
                    header.createCell((short) 3).setCellValue("Claim Paid");

                    for (Object[] row : hasilGeneralStatistic) {

                        if (row != null) {
                            rowNumber += 1;
                            HSSFRow hssfRow = usageSheet.createRow(rowNumber);
                            hssfRow.createCell((short) 0).setCellValue(row[0].toString());
                            hssfRow.createCell((short) 1).setCellValue(row[1].toString());
                            hssfRow.createCell((short) 2).setCellValue(row[2].toString());
                            hssfRow.createCell((short) 3).setCellValue(row[3].toString());

                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }

    @Override
    public double[] generateYearlyClaimGrowth(Integer year) throws Exception {
        double[] result = new double[12];

        try {

            for (int i = 0; i < result.length; i++) {
                result[i] = 0;
            }
            Session session = claimDao.getClaimSession();

            if (session != null) {
                String queryString = "SELECT month(c.admissionDate),sum(c.claimApprovedValue) "
                        + "FROM Claim c WHERE year(c.admissionDate) = :year AND c.deletedStatus = 0 "
                        + "GROUP BY month(c.admissionDate)";

                Query query = session.createQuery(queryString);
                query.setInteger("year", year);

                List<Object[]> hasil = query.list();

                System.out.println(query.getQueryString());
                System.out.println("Total Hasil : " + hasil.size());

                if (hasil != null) {
                    Iterator<Object[]> iterator = hasil.iterator();

                    while (iterator.hasNext()) {
                        Object[] tmp = iterator.next();

                        if (tmp != null) {
                            int month = Integer.valueOf(tmp[0].toString());
                            double amount = Double.valueOf(tmp[1].toString());

                            result[month - 1] = amount;
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    
    public Collection<Object[]> generateClaimProviderComparison(Integer year) throws Exception {
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {

            String sqlQueryStr = "SELECT p.providerName as provider, sum(c.claimApprovedValue) as hasil "
                    + "FROM Claim c, Provider p WHERE c.providerId = p.providerId"
                    + " AND c.deletedStatus = 0 AND c.claimDate LIKE :tahun "
                    + "GROUP BY p.providerName ";


            String sql = "SELECT p.provider_name , sum(c.claim_approved_value) as hasil FROM " +
            		" claim c, provider p WHERE c.provider_id = p.provider_id AND c.deleted_status = 0 AND " +
            		" c.claim_date LIKE '%"+year+"%' GROUP BY p.provider_name ORDER BY hasil DESC";
            		
            	
            Query query = session.createSQLQuery(sql);
            //query.setString("tahun", "%" + year + "%");
            query.setMaxResults(5);
            

            result = (Collection<Object[]>) query.list();
            System.out.println(query.getQueryString());
            System.out.println("total Result : " + result.size()) ;


        }

        return result;
    }

    @Override
    public Collection<Object[]> generateClaimDiagnosisComparison(Integer year) throws Exception {
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {
            String sql = "SELECT d.diagnosis_code , sum(c.claim_approved_value) as hasil "
            + "FROM claim c, diagnosis d WHERE c.diagnosis_id = d.diagnosis_id"
            + " AND c.deleted_status = 0 AND c.claim_date LIKE '%"+year+"%' "
            + " GROUP BY d.diagnosis_code ORDER BY hasil DESC";


            Query query = session.createSQLQuery(sql);
            
            query.setMaxResults(5);

            result = (Collection<Object[]>) query.list();
            System.out.println(query.getQueryString());
            System.out.println("total Result : " + result.size()) ;

        }

        return result;
    }

    public String generateClientOutstandingClaim(Integer clientId) throws Exception {
        String result = "";

        Session session = claimDao.getClaimSession();

        if (session != null) {

            String sqlQueryStr = "SELECT client.clientName as name, sum(c.claimApprovedValue) as hasil "
                    + "FROM Claim c, Client client WHERE c.memberId.clientId.clientId = :clientId"
                    + " AND c.deletedStatus = 0 AND c.batchClaimId is NULL" +
                    " group by client.clientName" ;

            Query query = session.createQuery(sqlQueryStr);            
            query.setInteger("clientId", clientId);
            
            Collection<Object[]> hasil = query.list();
            
            if (hasil != null){
            	Iterator<Object[]> iterator = hasil.iterator();
            	
            	if (iterator.hasNext()){
            		Object[] objects = iterator.next();
            		
            		if (objects != null && objects.length == 2){
            			result = Converter.getMoney((Double)objects[1]);
            		}
            	}
            }
            
            


        }

        return result;
    }
    @Override
    public Collection<Object[]> generateClientClaimGroupComparison(Integer year, Integer clientId) throws Exception {
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {

            String sqlQueryStr = "SELECT mg.groupName as name, sum(c.claimApprovedValue) as hasil "
                    + "FROM Claim c, MemberGroup mg WHERE c.memberId.memberGroupId.memberGroupId = mg.memberGroupId"
                    + " AND c.deletedStatus = 0 AND c.claimDate LIKE :tahun AND c.memberId.clientId.clientId = :clientId"
                    + " GROUP BY mg.groupName ORDER BY sum(c.claimApprovedValue) DESC";


            Query query = session.createQuery(sqlQueryStr);
            query.setString("tahun", "%" + year + "%");
            query.setInteger("clientId", clientId);
            query.setMaxResults(5);

            result = (Collection<Object[]>) query.list();


        }

        return result;
    }

    @Override
    public Collection<Object[]> generateClientClaimProviderComparison(Integer year, Integer clientId) throws Exception {
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {

            String sqlQueryStr = "SELECT p.providerName as provider, sum(c.claimApprovedValue) as hasil "
                    + "FROM Claim c, Provider p WHERE c.providerId = p.providerId"
                    + " AND c.deletedStatus = 0 AND  c.claimDate BETWEEN '"+year+"-01-01' AND '"+year+"-12-31' AND c.memberId.clientId.clientId = :clientId"
                    + " GROUP BY p.providerName ORDER BY sum(c.claimApprovedValue) DESC";


            Query query = session.createQuery(sqlQueryStr);
            //query.setString("tahun", "%" + year + "%");
            query.setMaxResults(5);
            query.setInteger("clientId",clientId);

            result = (Collection<Object[]>) query.list();


        }

        return result;
    }
    
    
    public Collection<Object[]> generateClientBranchClaimProviderComparison(Integer year, Integer clientId) throws Exception {
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {

            String sqlQueryStr = "SELECT p.providerName as provider, sum(c.claimApprovedValue) as hasil "
                    + "FROM Claim c, Provider p WHERE c.providerId = p.providerId"
                    + " AND c.deletedStatus = 0 AND  c.claimDate BETWEEN '"+year+"-01-01' AND '"+year+"-12-31' AND c.memberId.clientId.clientId = :clientId"
                    + " GROUP BY p.providerName ORDER BY sum(c.claimApprovedValue) DESC";


            Query query = session.createQuery(sqlQueryStr);
            //query.setString("tahun", "%" + year + "%");
            query.setMaxResults(5);
            query.setInteger("clientId",clientId);

            result = (Collection<Object[]>) query.list();


        }

        return result;
    }

    @Override
    public Collection<Object[]> generateClientClaimDiagnosisComparison(Integer year, Integer clientId) throws Exception {
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {

            String sqlQueryStr = "SELECT d.diagnosisCode as diagnosis, sum(c.claimApprovedValue) as hasil "
                    + "FROM Claim c, Diagnosis d WHERE c.diagnosisId = d.diagnosisId"
                    + " AND c.deletedStatus = 0 AND c.claimDate BETWEEN '"+year+"-01-01' AND '"+year+"-12-31' AND c.memberId.clientId.clientId = :clientId"
                    + " GROUP BY d.diagnosisCode ORDER BY sum(c.claimApprovedValue) DESC";


            Query query = session.createQuery(sqlQueryStr);
            //query.setString("tahun", "%" + year + "%");
            /*query.setString("tahun1", year + "-01-01");
            query.setString("tahun2", year + "-12-31");*/
            query.setMaxResults(5);
            query.setInteger("clientId",clientId);
            

            result = (Collection<Object[]>) query.list();


        }
        

        return result;
    }

    
    @Override
    public double[] generateClientYearlyClaimGrowth(Integer year, Integer clientId) throws Exception {
        double[] result = new double[12];

        try {

            for (int i = 0; i < result.length; i++) {
                result[i] = 0;
            }
            Session session = claimDao.getClaimSession();

            if (session != null) {
                String queryString = "SELECT month(c.admissionDate),sum(c.claimApprovedValue) "
                        + "FROM Claim c WHERE year(c.admissionDate) = :year AND c.deletedStatus = 0 "
                        + "AND c.memberId.clientId.clientId = :clientId"
                        + " GROUP BY month(c.admissionDate)";

                Query query = session.createQuery(queryString);
                query.setInteger("year", year);
                query.setInteger("clientId",clientId);

                List<Object[]> hasil = query.list();


                if (hasil != null) {
                    Iterator<Object[]> iterator = hasil.iterator();

                    while (iterator.hasNext()) {
                        Object[] tmp = iterator.next();

                        if (tmp != null) {
                            int month = Integer.valueOf(tmp[0].toString());
                            double amount = Double.valueOf(tmp[1].toString());

                            result[month - 1] = amount;
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<Object[]> generateGroupClaimProviderComparison(Integer year, Integer groupId) throws Exception {
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {

            String sqlQueryStr = "SELECT p.providerName as provider, sum(c.claimApprovedValue) as hasil "
                    + "FROM Claim c, Provider p WHERE c.providerId = p.providerId"
                    + " AND c.deletedStatus = 0 AND c.claimDate LIKE :tahun AND c.memberId.memberGroupId.memberGroupId = :groupId"
                    + " GROUP BY p.providerName ORDER BY sum(c.claimApprovedValue) DESC";


            Query query = session.createQuery(sqlQueryStr);
            query.setString("tahun", "%" + year + "%");
            query.setMaxResults(5);
            query.setInteger("groupId",groupId);

            result = (Collection<Object[]>) query.list();


        }

        return result;
    }

    @Override
    public Collection<Object[]> generateGroupClaimDiagnosisComparison(Integer year, Integer groupId) throws Exception {
        Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();

        if (session != null) {

            String sqlQueryStr = "SELECT d.diagnosisCode as diagnosis, sum(c.claimApprovedValue) as hasil "
                    + "FROM Claim c, Diagnosis d WHERE c.diagnosisId = d.diagnosisId"
                    + " AND c.deletedStatus = 0 AND c.claimDate LIKE :tahun AND c.memberId.memberGroupId.memberGroupId = :groupId "
                    + "GROUP BY d.diagnosisCode ORDER BY sum(c.claimApprovedValue) DESC";


            Query query = session.createQuery(sqlQueryStr);
            query.setString("tahun", "%" + year + "%");
            query.setMaxResults(5);
            query.setInteger("groupId",groupId);

            result = (Collection<Object[]>) query.list();


        }

        return result;
    }

    @Override
    public double[] generateGroupYearlyClaimGrowth(Integer year, Integer groupId) throws Exception {
        double[] result = new double[12];

        try {

            for (int i = 0; i < result.length; i++) {
                result[i] = 0;
            }
            Session session = claimDao.getClaimSession();

            if (session != null) {
                String queryString = "SELECT month(c.admissionDate),sum(c.claimApprovedValue) "
                        + "FROM Claim c WHERE year(c.admissionDate) = :year AND c.deletedStatus = 0 "
                        + "AND c.memberId.memberGroupId.memberGroupId = :groupId"
                        + " GROUP BY month(c.admissionDate)";

                Query query = session.createQuery(queryString);
                query.setInteger("year", year);
                query.setInteger("groupId",groupId);

                List<Object[]> hasil = query.list();


                if (hasil != null) {
                    Iterator<Object[]> iterator = hasil.iterator();

                    while (iterator.hasNext()) {
                        Object[] tmp = iterator.next();

                        if (tmp != null) {
                            int month = Integer.valueOf(tmp[0].toString());
                            double amount = Double.valueOf(tmp[1].toString());

                            result[month - 1] = amount;
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

	
	public Collection<Object[]> generateClaimReportByClient(
			Integer serviceType, Integer clientId, Date start, Date end,
			Integer totalIndex) throws Exception {
		Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();
        if (session != null) {


            Query query = session.createQuery("SELECT m.firstName, m.customerPolicyNumber, m.relationship, m.customerNumber, count(c.claimNumber) " +
            		"as totalClaim, sum(c.claimChargeValue) as charge, sum(c.claimApprovedValue) as paid, " +
            		"sum(c.claimPaidValue), m.memberId FROM Claim c, Member m WHERE c.memberId.clientId.clientId = :clientId " +
            		"AND c.claimStatus.caseStatusId = :claimStatus"
                    + " AND c.claimDate >= :startDate AND c.claimDate <= :endDate AND m.memberId = c.memberId.memberId AND " +
                    		"c.caseCategoryId.caseCategoryId = :serviceType GROUP BY m.memberId ORDER BY sum(c.claimApprovedValue) DESC");

            query.setInteger("clientId", clientId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("serviceType", serviceType);
            query.setDate("startDate", start);
            query.setDate("endDate", end);

            if (totalIndex != null) {
                query.setMaxResults(totalIndex);
            }

            Collection res = query.list();

            if (res != null) {


                double sumPaid = 0;

                Iterator<Object[]> iterator = res.iterator();

                while (iterator.hasNext()) {
                    Object[] resultList = iterator.next();

                    if (resultList != null) {
                        Double benefitPaid = (Double) resultList[6];

                        System.out.println("BENEFIT PAID : " + benefitPaid);
                        if (benefitPaid != null) {
                            sumPaid += benefitPaid;
                        }
                    }
                }

                Iterator<Object[]> settingPercentage = res.iterator();

                if (settingPercentage != null) {

                    result = new Vector<Object[]>();

                    while (settingPercentage.hasNext()) {
                        Object[] current = settingPercentage.next();

                        current[7] = ((Double) current[6] / sumPaid) * 100;

                        result.add(current);

                    }
                }
            }

        }

        return result;
	}

	
	public Collection<Object[]> generateClaimReportByClientAndGroup(
			Integer serviceType, Integer clientId, Integer groupId, Date start,
			Date end, Integer totalIndex) throws Exception {
		// TODO Auto-generated method stub
		Collection<Object[]> result = null;

        Session session = claimDao.getClaimSession();
        if (session != null) {


            Query query = session.createQuery("SELECT m.firstName, m.customerPolicyNumber, m.relationship, m.customerNumber, count(c.claimNumber) " +
            		"as totalClaim, sum(c.claimChargeValue) as charge, sum(c.claimApprovedValue) as paid, " +
            		"sum(c.claimPaidValue), m.memberId FROM Claim c, Member m WHERE c.memberId.clientId.clientId = :clientId " +
            		" AND c.memberId.memberGroupId.memberGroupId = :memberGroupId AND c.claimStatus.caseStatusId = :claimStatus"
                    + " AND c.claimDate >= :startDate AND c.claimDate <= :endDate AND m.memberId = c.memberId.memberId AND " +
                    		"c.caseCategoryId.caseCategoryId = :serviceType GROUP BY m.memberId ORDER BY sum(c.claimApprovedValue) DESC");

            query.setInteger("memberGroupId", groupId);
            query.setInteger("clientId", clientId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("serviceType", serviceType);
            query.setDate("startDate", start);
            query.setDate("endDate", end);

            if (totalIndex != null) {
                query.setMaxResults(totalIndex);
            }

            Collection res = query.list();

            if (res != null) {


                double sumPaid = 0;

                Iterator<Object[]> iterator = res.iterator();

                while (iterator.hasNext()) {
                    Object[] resultList = iterator.next();

                    if (resultList != null) {
                        Double benefitPaid = (Double) resultList[6];

                        System.out.println("BENEFIT PAID : " + benefitPaid);
                        if (benefitPaid != null) {
                            sumPaid += benefitPaid;
                        }
                    }
                }

                Iterator<Object[]> settingPercentage = res.iterator();

                if (settingPercentage != null) {

                    result = new Vector<Object[]>();

                    while (settingPercentage.hasNext()) {
                        Object[] current = settingPercentage.next();

                        current[7] = ((Double) current[6] / sumPaid) * 100;

                        result.add(current);

                    }
                }
            }

        }

        return result;
	}


	public PerformanceStatisticDto generateClaimMonitor() throws Exception{
		PerformanceStatisticDto result = new PerformanceStatisticDto();
		try {
			Session session = claimDao.getClaimSession();
			
			if (session != null){
				String qBatch = "SELECT sum(b.incompleteClaim) FROM BatchClaim b";
				Query batchQ = session.createQuery(qBatch);
				if (batchQ != null){
					List<Object> batchList = batchQ.list();
					Iterator<Object> batchIterator = batchList.iterator();
					if (batchIterator.hasNext()){
						Object batchTotal = batchIterator.next();
						
						result.setTotalUnregisteredClaim(((Long)batchTotal).intValue());
						
					}
				}
				
				String qClaim = "SELECT count(c.claimId) FROM Claim c WHERE c.claimStatus.caseStatusId = :status AND c.deletedStatus = 0";
				
				String qActiveClaim = "SELECT count(c.claimId) FROM Claim c WHERE c.claimStatus.caseStatusId <> :status AND c.claimTypeId.claimTypeId = :ctype AND c.deletedStatus = :delStatus";
				
				String qOutstandingClaim = "SELECT count(c.claimId) FROM Claim c WHERE c.batchClaimId IS NULL AND c.deletedStatus = 0";
				
				
				Query activeClaimQ = session.createQuery(qActiveClaim);
				
				if (activeClaimQ != null){
					activeClaimQ.setInteger("status", Claim.CLAIM_PAID);
					activeClaimQ.setInteger("delStatus",0);
					activeClaimQ.setInteger("ctype", ClaimType.CASHLESS);
					
					List<Object> claimList = activeClaimQ.list();
					Iterator<Object> claimIterator = claimList.iterator();
					if (claimIterator.hasNext()){
						Object claimTotal = claimIterator.next();
						
						result.setTotalDueCashlessClaim(((Long)claimTotal).intValue());
						
					}
					

					
					activeClaimQ.setInteger("status", Claim.CLAIM_PAID);
					activeClaimQ.setInteger("delStatus",0);
					activeClaimQ.setInteger("ctype", ClaimType.REIMBURESEMENT);
					claimList = activeClaimQ.list();
					
					claimIterator = claimList.iterator();
					if (claimIterator.hasNext()){
						Object claimTotal = claimIterator.next();
						
						result.setTotalDueReimbursementClaim(((Long)claimTotal).intValue());
						
					}
				}
				
				Query claimQ = session.createQuery(qClaim);
				
				if (claimQ != null){
					claimQ.setInteger("status", Claim.CLAIM_OPEN);
					List<Object> claimList = claimQ.list();
					Iterator<Object> claimIterator = claimList.iterator();
					if (claimIterator.hasNext()){
						Object claimTotal = claimIterator.next();
						
						result.setTotalSubmittedClaim(((Long)claimTotal).intValue());
						
					}
					
					claimQ.setInteger("status", Claim.CLAIM_APPROVED);
					claimList = claimQ.list();
					claimIterator = claimList.iterator();
					
					if (claimIterator.hasNext()){
						Object claimTotal = claimIterator.next();
						
							result.setTotalVerifiedClaim(((Long)claimTotal).intValue());
						
					}
					
					claimQ.setInteger("status", Claim.CLAIM_CHECKED);
					claimList = claimQ.list();
					claimIterator = claimList.iterator();
					
					if (claimIterator.hasNext()){
						Object claimTotal = claimIterator.next();
						
							result.setTotalCheckedClaim(((Long)claimTotal).intValue());
						
					}
					
					claimQ.setInteger("status", Claim.CLAIM_PAYMENT_ISSUED);
					
					claimList = claimQ.list();
					claimIterator = claimList.iterator();
					
					if (claimIterator.hasNext()){
						Object claimTotal = claimIterator.next();
						
							result.setTotalPendingPaymentClaim(((Long)claimTotal).intValue());
					
					}
					
					claimQ.setInteger("status", Claim.CLAIM_PENDING);
					claimList = claimQ.list();
					claimIterator = claimList.iterator();
					
					if (claimIterator.hasNext()){
						Object claimTotal = claimIterator.next();
					
							result.setTotalPendingClaim(((Long)claimTotal).intValue());
					
					}
				}
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
			
		}
		return result;
	}
	public double[] generateProviderClaimGrowth(Integer year, Integer providerId) throws Exception {
        double[] result = new double[12];

        try {

            for (int i = 0; i < result.length; i++) {
                result[i] = 0;
            }
            Session session = claimDao.getClaimSession();

            if (session != null) {
                String queryString = "SELECT month(c.admissionDate),sum(c.claimApprovedValue) "
                        + "FROM Claim c WHERE year(c.admissionDate) = :year AND c.deletedStatus = 0 "
                        + "AND c.providerId.providerId = :providerId"
                        + " GROUP BY month(c.admissionDate)";

                Query query = session.createQuery(queryString);
                query.setInteger("year", year);
                query.setInteger("providerId",providerId);

                List<Object[]> hasil = query.list();


                if (hasil != null) {
                    Iterator<Object[]> iterator = hasil.iterator();

                    while (iterator.hasNext()) {
                        Object[] tmp = iterator.next();

                        if (tmp != null) {
                            int month = Integer.valueOf(tmp[0].toString());
                            double amount = Double.valueOf(tmp[1].toString());

                            result[month - 1] = amount;
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	public Collection<ClaimStatisticDto> generateTop10ClaimantReport(
			Integer clientId) throws Exception {
		// TODO Auto-generated method stub
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		try {
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.memberId.firstName, c.memberId.parentMember.firstName," +
	            		"c.memberId.parentMember.customerPolicyNumber," +
	            		"count(c.claimId), sum(c.claimApprovedValue)  FROM Claim c " +
	            		"WHERE c.clientId = :clientId GROUP BY c.memberId.memberId ORDER BY sum(c.claimApprovedValue)  DESC LIMIT 0,10");
	            
	            query.setInteger("clientId", clientId);
	            query.setMaxResults(10);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amountDbl = (Double)objects[4];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setMemberName(objects[0].toString());
					stat.setEmployeeName(objects[1].toString());
					stat.setEmployeeNumber(objects[2].toString());
					stat.setClaimCount(objects[3].toString());
					stat.setClaimAmount(Converter.getMoney(amountDbl));
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;

	}

	
	public Collection<ClaimStatisticDto> generateTop10ClaimantReport(
			Integer clientId, Integer groupId) throws Exception {
		// TODO Auto-generated method stub
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		try {
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.memberId.firstName, c.memberId.parentMember.firstName," +
	            		"c.memberId.parentMember.customerPolicyNumber," +
	            		"count(c.claimId), sum(c.claimApprovedValue)  FROM Claim c " +
	            		"WHERE c.clientId = :clientId AND c.memberGroupId = :groupId GROUP BY c.memberId.memberId ORDER BY sum(c.claimApprovedValue)  DESC LIMIT 0,10");
	            
	            query.setInteger("clientId", clientId);
	            query.setInteger("groupId", groupId);
	            query.setMaxResults(10);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amountDbl = (Double)objects[4];
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setMemberName(objects[0].toString());
					stat.setEmployeeName(objects[1].toString());
					stat.setEmployeeNumber(objects[2].toString());
					stat.setClaimCount(objects[3].toString());
					stat.setClaimAmount(Converter.getMoney(amountDbl));
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	public Collection<ClaimStatisticDto> generateTop10DiagnosisReport(
			Integer clientId) throws Exception {
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		try {
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.diagnosisId.description, c.diagnosis1Code," +	            		
	            		"count(c.claimId), sum(c.claimApprovedValue)  FROM Claim c " +
	            		"WHERE c.clientId = :clientId GROUP BY c.diagnosisId.diagnosisId ORDER BY sum(c.claimApprovedValue)  DESC LIMIT 0,10");
	            
	            query.setInteger("clientId", clientId);
	            query.setMaxResults(10);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amountDbl = (Double)objects[3];
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setDiagnosisName(objects[0].toString());
					stat.setDiagnosisCode(objects[1].toString());
					stat.setClaimCount(objects[2].toString());
					stat.setClaimAmount(Converter.getMoney(amountDbl));
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<ClaimStatisticDto> generateTop10DiagnosisReport(
			Integer clientId, Integer groupId) throws Exception {
		// TODO Auto-generated method stub
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		try {
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.diagnosisId.description, c.diagnosis1Code," +	            		
	            		"count(c.claimId), sum(c.claimApprovedValue)  FROM Claim c " +
	            		"WHERE c.clientId = :clientId AND c.memberGroupId = :groupId GROUP BY c.diagnosisId.diagnosisId ORDER BY sum(c.claimApprovedValue)  DESC LIMIT 0,10");
	            
	            query.setInteger("clientId", clientId);
	            query.setInteger("groupId", groupId);
	            query.setMaxResults(10);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amountDbl = (Double)objects[3];
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setDiagnosisName(objects[0].toString());
					stat.setDiagnosisCode(objects[1].toString());
					stat.setClaimCount(objects[2].toString());
					stat.setClaimAmount(Converter.getMoney(amountDbl));
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<ClaimStatisticDto> generateTop10ProviderReport(
			Integer clientId) throws Exception {
		// TODO Auto-generated method stub
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		try {
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.providerId.providerName, " +	            		
	            		"count(c.claimId), sum(c.claimApprovedValue)  FROM Claim c " +
	            		"WHERE c.clientId = :clientId GROUP BY c.providerId.providerId ORDER BY sum(c.claimApprovedValue)  DESC LIMIT 0,10");
	            
	            query.setInteger("clientId", clientId);
	            query.setMaxResults(10);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
	            	Object[] objects = (Object[]) iterator.next();
					Double amount = (Double)objects[2];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setProviderName(objects[0].toString());
					stat.setClaimCount(objects[1].toString());
					stat.setClaimAmount(Converter.getMoney(amount));
					
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public Collection<ClaimStatisticDto> generateTop10ProviderReport(
			Integer clientId, Integer groupId) throws Exception {
		// TODO Auto-generated method stub
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		try {
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.providerId.providerName, " +	            		
	            		"count(c.claimId), sum(c.claimApprovedValue)  FROM Claim c " +
	            		"WHERE c.clientId = :clientId AND c.memberGroupId = :groupId GROUP BY c.providerId.providerId ORDER BY sum(c.claimApprovedValue)  DESC LIMIT 0,10");
	            
	            query.setInteger("clientId", clientId);
	            query.setInteger("groupId", groupId);
	            query.setMaxResults(10);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amount = (Double)objects[2];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setProviderName(objects[0].toString());
					stat.setClaimCount(objects[1].toString());
					stat.setClaimAmount(Converter.getMoney(amount));
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<ClaimStatisticDto> generateTop10ClaimUpgrade(
			Integer clientId) throws Exception {
		// TODO Auto-generated method stub
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		try {
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.memberId.firstName, c.memberId.parentMember.firstName," +
	            		"c.memberId.parentMember.customerPolicyNumber," +
	            		"count(c.claimId), sum(c.claimApprovedValue) FROM Claim c " +
	            		"WHERE c.clientId = :clientId AND c.roomUpgradeType <> :treatmentType GROUP BY c.memberId.memberId  ORDER BY sum(c.claimApprovedValue)  DESC LIMIT 0,10");
	            
	            query.setInteger("clientId", clientId);
	            query.setInteger("treatmentType", TreatmentUpgradeType.SESUAI_PLAN);
	            query.setMaxResults(10);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amount = (Double) objects[4];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setMemberName(objects[0].toString());
					stat.setEmployeeName(objects[1].toString());
					stat.setEmployeeNumber(objects[2].toString());
					stat.setClaimCount(objects[3].toString());
					stat.setClaimAmount(Converter.getMoney(amount));
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<ClaimStatisticDto> generateTop10ClaimUpgrade(
			Integer clientId, Integer groupId) throws Exception {
		// TODO Auto-generated method stub
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		try {
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.memberId.firstName, c.memberId.parentMember.firstName," +
	            		"c.memberId.parentMember.customerPolicyNumber," +
	            		"count(c.claimId), sum(c.claimApprovedValue) FROM Claim c " +
	            		"WHERE c.clientId = :clientId AND c.roomUpgradeType <> :treatmentType " +
	            		" AND c.memberGroupId = :groupId GROUP BY c.memberId.memberId  ORDER BY sum(c.claimApprovedValue)  DESC LIMIT 0,10");
	            
	            query.setInteger("clientId", clientId);
	            query.setInteger("treatmentType", TreatmentUpgradeType.SESUAI_PLAN);
	            query.setInteger("groupId", groupId);
	            query.setMaxResults(10);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amount = (Double) objects[4];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setMemberName(objects[0].toString());
					stat.setEmployeeName(objects[1].toString());
					stat.setEmployeeNumber(objects[2].toString());
					stat.setClaimCount(objects[3].toString());
					stat.setClaimAmount(Converter.getMoney(amount));
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<ClaimStatisticDto> generateTopXClaimantReport(
			Integer policyId, Integer caseCategoryId, Date start, Date end,
			Integer topX) throws Exception {
		// TODO Auto-generated method stub
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		try {
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.memberName, c.memberNumber, c.relationship, c.memberId.parentMember.firstName," +
	            		"c.memberId.parentMember.customerPolicyNumber," +
	            		"count(c.claimId), sum(c.claimChargeValue), sum(c.claimApprovedValue) FROM Claim c " +
	            		"WHERE c.deletedStatus = 0 AND c.admissionDate >= :start AND c.admissionDate <= :end " +
	            		" AND c.policyId = :policyId AND c.caseCategoryId.caseCategoryId = :cc " +
	            		" GROUP BY c.memberName, c.memberNumber, c.relationship, c.memberId.parentMember.firstName," +
	            		" c.memberId.parentMember.customerPolicyNumber " +
	            		" ORDER BY sum(c.claimChargeValue)  DESC");
	            
	            query.setInteger("policyId", policyId);
	            query.setDate("start", start);
	            query.setDate("end", end);
	            query.setInteger("cc", caseCategoryId);
	            
	            query.setMaxResults(topX);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amount = (Double) objects[6];
					Double benefitPaid = (Double) objects[7];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setMemberName(objects[0] == null ? "" : objects[0].toString());
					stat.setMemberNumber(objects[1] == null ? "" : objects[1].toString());
					stat.setRelationship(objects[2] == null ? "" : objects[2].toString());
					
					stat.setEmployeeName(objects[3] == null ? "" : objects[3].toString());
					stat.setEmployeeNumber(objects[4] == null ? "" : objects[4].toString());
					stat.setClaimCount(objects[5] == null ? "" : objects[5].toString());
					stat.setClaimAmount(Converter.getMoney(amount));
					stat.setClaimPaid(Converter.getMoney(benefitPaid));
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public Collection<ClaimStatisticDto> generateTopXDiagnosisReport(
			Integer policyId, Integer caseCategoryId, Date start, Date end,
			Integer topX) throws Exception {
		// TODO Auto-generated method stub
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		
		try {
			
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.diagnosisId.diagnosisName, " +	            		
	            		"count(c.claimId), count(DISTINCT c.memberId.memberId), sum(c.claimChargeValue), sum(c.claimApprovedValue)," +
	            		" avg(c.claimApprovedValue) FROM Claim c " +
	            		"WHERE c.deletedStatus = 0 AND  c.admissionDate >= :start AND c.admissionDate <= :end " +
	            		" AND c.policyId = :policyId AND c.caseCategoryId.caseCategoryId = :cc " +
	            		"GROUP BY c.diagnosisId.diagnosisName  ORDER BY sum(c.claimChargeValue)  DESC");
	            
	            query.setInteger("policyId", policyId);
	            query.setDate("start", start);
	            query.setDate("end", end);
	            query.setInteger("cc", caseCategoryId);
	            
	            query.setMaxResults(topX);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amount = (Double) objects[3];
					Double benefitPaid = (Double) objects[4];
					Double averageAmount = (Double) objects[5];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setDiagnosisName(objects[0] == null ? "" : objects[0].toString());
					stat.setClaimCount(objects[1] == null ? "" : objects[1].toString());
					stat.setParticipantCount(objects[2] == null ? "" : objects[2].toString());
					
					stat.setClaimAmount(Converter.getMoney(amount));
					stat.setClaimPaid(Converter.getMoney(benefitPaid));
					stat.setClaimAverage(Converter.getMoney(averageAmount));
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public Collection<ClaimStatisticDto> generateTopXFrequencyReport(
			Integer policyId, Integer caseCategoryId, Date start, Date end,
			Integer topX) throws Exception {
		// TODO Auto-generated method stub
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		try {
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.memberName, c.memberNumber, c.relationship, c.memberId.parentMember.firstName," +
	            		"c.memberId.parentMember.customerPolicyNumber," +
	            		"count(c.claimId), sum(c.claimChargeValue), sum(c.claimApprovedValue) FROM Claim c " +
	            		"WHERE c.deletedStatus = 0 AND  c.admissionDate >= :start AND c.admissionDate <= :end " +
	            		" AND c.policyId = :policyId AND c.caseCategoryId.caseCategoryId = :cc " +
	            		" GROUP BY c.memberName, c.memberNumber, c.relationship, c.memberId.parentMember.firstName, " +
	            		" c.memberId.parentMember.customerPolicyNumber  " +
	            		" ORDER BY count(c.claimId)  DESC");
	            
	            query.setInteger("policyId", policyId);
	            query.setDate("start", start);
	            query.setDate("end", end);
	            query.setInteger("cc", caseCategoryId);
	            
	            query.setMaxResults(topX);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amount = (Double) objects[6];
					Double benefitPaid = (Double) objects[7];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setMemberName(objects[0] == null ? "" : objects[0].toString());
					stat.setMemberNumber(objects[1] == null ? "" : objects[1].toString());
					stat.setRelationship(objects[2] == null ? "" : objects[2].toString());
					
					stat.setEmployeeName(objects[3] == null ? "" : objects[3].toString());
					stat.setEmployeeNumber(objects[4] == null ? "" : objects[4].toString());
					stat.setClaimCount(objects[5] == null ? "" : objects[5].toString());
					stat.setClaimAmount(Converter.getMoney(amount));
					stat.setClaimPaid(Converter.getMoney(benefitPaid));
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public Collection<ClaimStatisticDto> generateTopXProviderReport(
			Integer policyId, Integer caseCategoryId, Date start, Date end,
			Integer topX) throws Exception {
		// TODO Auto-generated method stub
		
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		
		try {
			
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.providerId.providerName, " +	            		
	            		"count(c.claimId), sum(c.claimChargeValue), sum(c.claimApprovedValue), avg(c.claimApprovedValue) FROM Claim c " +
	            		"WHERE c.deletedStatus = 0 AND  c.admissionDate >= :start AND c.admissionDate <= :end " +
	            		" AND c.policyId = :policyId AND c.caseCategoryId.caseCategoryId = :cc " +
	            		"GROUP BY c.providerId.providerName  ORDER BY sum(c.claimChargeValue)  DESC");
	            
	            query.setInteger("policyId", policyId);
	            query.setDate("start", start);
	            query.setDate("end", end);
	            query.setInteger("cc", caseCategoryId);
	            
	            query.setMaxResults(topX);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amount = (Double) objects[2];
					Double benefitPaid = (Double) objects[3];
					Double averageAmount = (Double) objects[4];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setProviderName(objects[0] == null ? "" : objects[0].toString());
					stat.setClaimCount(objects[1] == null ? "" : objects[1].toString());
					stat.setClaimAmount(Converter.getMoney(amount));
					stat.setClaimPaid(Converter.getMoney(benefitPaid));
					stat.setClaimAverage(Converter.getMoney(averageAmount));
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;

	}
	

	public Collection<ClaimStatisticDto> generateTopXDiagnosisReport (Integer clientId,Integer groupId, Integer caseCategoryId, 
			Date start, Date end, Integer topX, String sortby) throws Exception {

		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		
		try {
			
			Session session = claimDao.getClaimSession();
	        if (session != null) {

	        	String q = "";
	        	
	        	if (sortby != null){
	        		if (sortby.equalsIgnoreCase("sum")){
	        			q = "SELECT c.diagnosisId.diagnosisName, " +	            		
	            		"count(c.claimId), count(DISTINCT c.memberId.memberId), sum(c.claimChargeValue), sum(c.claimApprovedValue)," +
	            		" avg(c.claimApprovedValue),sum(c.claimExcessValue), avg(c.claimExcessValue)  FROM Claim c " +
	            		"WHERE c.deletedStatus = 0 AND  c.admissionDate >= :start AND c.admissionDate <= :end " +
	            		" AND c.clientId = :clientId AND c.memberGroupId = :groupId AND c.caseCategoryId.caseCategoryId = :cc " +
	            		" AND c.claimStatus.caseStatusId IN (8,13,6,16,17,5) GROUP BY c.diagnosisId.diagnosisName " +
	            		" ORDER BY sum(c.claimChargeValue)  DESC";
	        		}
	        		else if (sortby.equalsIgnoreCase("frequency")){
	        			q = "SELECT c.diagnosisId.diagnosisName, " +	            		
	            		"count(c.claimId), count(DISTINCT c.memberId.memberId), sum(c.claimChargeValue), sum(c.claimApprovedValue)," +
	            		" avg(c.claimApprovedValue),sum(c.claimExcessValue), avg(c.claimExcessValue)  FROM Claim c " +
	            		"WHERE c.deletedStatus = 0 AND  c.admissionDate >= :start AND c.admissionDate <= :end " +
	            		" AND c.clientId = :clientId AND c.memberGroupId = :groupId AND c.caseCategoryId.caseCategoryId = :cc " +
	            		" AND c.claimStatus.caseStatusId IN (8,13,6,16,17,5) GROUP BY c.diagnosisId.diagnosisName " +
	            		" ORDER BY count(c.claimId)  DESC";
	        		}
	        	}
	        	
	            Query query = session.createQuery(q);
	            
	            query.setInteger("clientId", clientId);
	            query.setInteger("groupId", groupId);
	            
	            query.setDate("start", start);
	            query.setDate("end", end);
	            query.setInteger("cc", caseCategoryId);
	            
	            query.setMaxResults(topX);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amount = (Double) objects[3];
					Double benefitPaid = (Double) objects[4];
					Double averageAmount = (Double) objects[5];
					Double sumExcess = (Double) objects[6];
					Double avgExcess = (Double) objects[7];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setDiagnosisName(objects[0] == null ? "" : objects[0].toString());
					stat.setClaimCount(objects[1] == null ? "" : objects[1].toString());
					stat.setClaimCountVal(objects[1] == null ? 0.0 : Double.valueOf(objects[1].toString()));
					
					stat.setParticipantCount(objects[2] == null ? "" : objects[2].toString());
					stat.setParticipantCountVal(objects[2] == null ? 0.0 : Double.valueOf(objects[2].toString()));
					
					stat.setClaimAmount(Converter.getMoney(amount));
					stat.setClaimAmountVal(amount);
					stat.setClaimPaid(Converter.getMoney(benefitPaid));
					stat.setClaimPaidVal(benefitPaid);
					stat.setClaimAverage(Converter.getMoney(averageAmount));
					stat.setClaimAverageVal(averageAmount);
					stat.setClaimExcess(Converter.getMoney(sumExcess));
					stat.setClaimExcessVal(sumExcess);
					stat.setAvgClaimExcess(Converter.getMoney(avgExcess));
					stat.setAvgClaimExcessVal(avgExcess);
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;

	}
	public Collection<ClaimStatisticDto> generateTopXProviderReport (Integer clientId,Integer groupId, Integer caseCategoryId, Date start,
			Date end, Integer topX, String sortby) throws Exception{
		
		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		
		try {
			
			Session session = claimDao.getClaimSession();
	        if (session != null) {
	        	String q = "";
	        	
	        	if (sortby != null){
	        		if (sortby.equalsIgnoreCase("sum")){
	        			q = "SELECT c.providerId.providerName, " +	            		
	            		"count(c.claimId), sum(c.claimChargeValue), sum(c.claimApprovedValue), avg(c.claimApprovedValue)," +
	            		"sum(c.claimExcessValue), avg(c.claimExcessValue) FROM Claim c " +
	            		"WHERE c.deletedStatus = 0 AND  c.admissionDate >= :start AND c.admissionDate <= :end " +
	            		" AND c.memberGroupId = :memberGroupId AND c.clientId = :clientId AND c.caseCategoryId.caseCategoryId = :cc " +
	            		"AND c.claimStatus.caseStatusId IN (8,13,6,16,17,5) GROUP BY c.providerId.providerName  ORDER BY sum(c.claimChargeValue)  DESC";
	        		}
	        		else if (sortby.equalsIgnoreCase("frequency")){
	        			q = "SELECT c.providerId.providerName, " +	            		
	            		"count(c.claimId), sum(c.claimChargeValue), sum(c.claimApprovedValue), avg(c.claimApprovedValue)," +
	            		"sum(c.claimExcessValue), avg(c.claimExcessValue)  FROM Claim c " +
	            		"WHERE c.deletedStatus = 0 AND  c.admissionDate >= :start AND c.admissionDate <= :end " +
	            		" AND c.memberGroupId = :memberGroupId AND c.clientId = :clientId AND c.caseCategoryId.caseCategoryId = :cc " +
	            		" AND c.claimStatus.caseStatusId IN (8,13,6,16,17,5) GROUP BY c.providerId.providerName  ORDER BY count(c.claimId)  DESC";
	        		}
	        	}

	            Query query = session.createQuery(q);
	            
	            query.setInteger("memberGroupId", groupId);
	            query.setInteger("clientId", clientId);
	            query.setDate("start", start);
	            query.setDate("end", end);
	            query.setInteger("cc", caseCategoryId);
	            
	            query.setMaxResults(topX);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amount = (Double) objects[2];
					Double benefitPaid = (Double) objects[3];
					Double averageAmount = (Double) objects[4];
					Double sumExcess = (Double) objects[5];
					Double avgExcess = (Double) objects[6];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setProviderName(objects[0] == null ? "" : objects[0].toString());
					stat.setClaimCount(objects[1] == null ? "" : objects[1].toString());
					stat.setClaimCountVal(objects[1] == null ? 0.0 : Double.valueOf(objects[1].toString()));
					
					stat.setClaimAmount(Converter.getMoney(amount));
					stat.setClaimAmountVal(amount);
					stat.setClaimPaid(Converter.getMoney(benefitPaid));
					stat.setClaimPaidVal(benefitPaid);
					stat.setClaimAverage(Converter.getMoney(averageAmount));
					stat.setClaimAverageVal(averageAmount);
					stat.setClaimExcess(Converter.getMoney(sumExcess));
					stat.setClaimExcessVal(sumExcess);
					stat.setAvgClaimExcess(Converter.getMoney(avgExcess));
					stat.setAvgClaimExcessVal(avgExcess);
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;

	}
	public Collection<ClaimStatisticDto> generateTopXClaimantReport (Integer clientId,Integer groupId, Integer caseCategoryId, Date start, 
			Date end, Integer topX, String sortby) throws Exception {

		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		try {
			Session session = claimDao.getClaimSession();
	        if (session != null) {

	        	String q = "";
	        	
	        	if (sortby != null){
	        		if (sortby.equalsIgnoreCase("sum")){
	        			q = "SELECT c.memberName, c.memberNumber, c.relationship, c.memberId.parentMember.firstName," +
	            		"c.memberId.parentMember.customerPolicyNumber," +
	            		"count(c.claimId), sum(c.claimChargeValue), sum(c.claimApprovedValue),sum(c.claimExcessValue), avg(c.claimExcessValue)  FROM Claim c " +
	            		"WHERE c.deletedStatus = 0 AND c.admissionDate >= :start AND c.admissionDate <= :end " +
	            		" AND c.clientId = :clientId AND c.memberGroupId = :groupId AND c.caseCategoryId.caseCategoryId = :cc " +
	            		" AND c.claimStatus.caseStatusId IN (8,13,6,16,17,5) GROUP BY c.memberName, c.memberNumber, c.relationship, c.memberId.parentMember.firstName," +
	            		" c.memberId.parentMember.customerPolicyNumber " +
	            		" ORDER BY sum(c.claimChargeValue)  DESC";
	        		}
	        		else if (sortby.equalsIgnoreCase("frequency")){
	        			q = "SELECT c.memberName, c.memberNumber, c.relationship, c.memberId.parentMember.firstName," +
	            		"c.memberId.parentMember.customerPolicyNumber," +
	            		"count(c.claimId), sum(c.claimChargeValue), sum(c.claimApprovedValue),sum(c.claimExcessValue), avg(c.claimExcessValue)  FROM Claim c " +
	            		"WHERE c.deletedStatus = 0 AND c.admissionDate >= :start AND c.admissionDate <= :end " +
	            		" AND c.clientId = :clientId AND c.memberGroupId = :groupId AND c.caseCategoryId.caseCategoryId = :cc " +
	            		" AND c.claimStatus.caseStatusId IN (8,13,6,16,17,5) GROUP BY c.memberName, c.memberNumber, c.relationship, c.memberId.parentMember.firstName," +
	            		" c.memberId.parentMember.customerPolicyNumber " +
	            		" ORDER BY count(c.claimId)  DESC";
	        		}
	        	}

	            Query query = session.createQuery(q);
	            
	            query.setInteger("clientId", clientId);
	            query.setInteger("groupId", groupId);
	            
	            query.setDate("start", start);
	            query.setDate("end", end);
	            query.setInteger("cc", caseCategoryId);
	            
	            query.setMaxResults(topX);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amount = (Double) objects[6];
					Double benefitPaid = (Double) objects[7];
					Double sumExcess = (Double) objects[8];
					Double avgExcess = (Double) objects[9];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setMemberName(objects[0] == null ? "" : objects[0].toString());
					stat.setMemberNumber(objects[1] == null ? "" : objects[1].toString());
					stat.setRelationship(objects[2] == null ? "" : objects[2].toString());
					
					stat.setEmployeeName(objects[3] == null ? "" : objects[3].toString());
					stat.setEmployeeNumber(objects[4] == null ? "" : objects[4].toString());
					stat.setClaimCount(objects[5] == null ? "" : objects[5].toString());
					stat.setClaimCountVal(objects[5] == null ? 0.0 : Double.valueOf(objects[5].toString()));
					
					stat.setClaimAmount(Converter.getMoney(amount));
					stat.setClaimAmountVal(amount);
					stat.setClaimPaid(Converter.getMoney(benefitPaid));
					stat.setClaimPaidVal(benefitPaid);
					stat.setClaimExcess(Converter.getMoney(sumExcess));
					stat.setClaimExcessVal(sumExcess);
					stat.setAvgClaimExcess(Converter.getMoney(avgExcess));
					stat.setAvgClaimExcessVal(avgExcess);
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;

	}
	public Collection<ClaimStatisticDto> generateTopXFrequencyReport (Integer clientId,Integer groupId, Integer caseCategoryId, Date start, Date end, Integer topX) throws Exception {

		Collection<ClaimStatisticDto> result = new Vector<ClaimStatisticDto>();
		try {
			Session session = claimDao.getClaimSession();
	        if (session != null) {


	            Query query = session.createQuery("SELECT c.memberName, c.memberNumber, c.relationship, c.memberId.parentMember.firstName," +
	            		"c.memberId.parentMember.customerPolicyNumber," +
	            		"count(c.claimId), sum(c.claimChargeValue), sum(c.claimApprovedValue) FROM Claim c " +
	            		"WHERE c.deletedStatus = 0 AND  c.admissionDate >= :start AND c.admissionDate <= :end " +
	            		" AND c.clientId = :clientId AND c.memberGroupId = :groupId AND c.caseCategoryId.caseCategoryId = :cc " +
	            		" GROUP BY c.memberName, c.memberNumber, c.relationship, c.memberId.parentMember.firstName, " +
	            		" c.memberId.parentMember.customerPolicyNumber  " +
	            		" ORDER BY count(c.claimId)  DESC");
	            
	            query.setInteger("groupId", groupId);
	            query.setInteger("clientId", clientId);
	            query.setDate("start", start);
	            query.setDate("end", end);
	            query.setInteger("cc", caseCategoryId);
	            
	            query.setMaxResults(topX);
	            
	            List<Object[]> list = query.list();
	            
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	            	
					Object[] objects = (Object[]) iterator.next();
					Double amount = (Double) objects[6];
					Double benefitPaid = (Double) objects[7];
					
					ClaimStatisticDto stat = new ClaimStatisticDto();
					stat.setMemberName(objects[0] == null ? "" : objects[0].toString());
					stat.setMemberNumber(objects[1] == null ? "" : objects[1].toString());
					stat.setRelationship(objects[2] == null ? "" : objects[2].toString());
					
					stat.setEmployeeName(objects[3] == null ? "" : objects[3].toString());
					stat.setEmployeeNumber(objects[4] == null ? "" : objects[4].toString());
					stat.setClaimCount(objects[5] == null ? "" : objects[5].toString());
					stat.setClaimAmount(Converter.getMoney(amount));
					stat.setClaimPaid(Converter.getMoney(benefitPaid));
					
					result.add(stat);
				}
	        }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;

	}
}
