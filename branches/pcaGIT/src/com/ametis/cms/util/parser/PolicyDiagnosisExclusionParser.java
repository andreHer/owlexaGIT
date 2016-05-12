package com.ametis.cms.util.parser;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.DiagnosisSet;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyDiagnosisExclusion;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.DiagnosisSetService;
import com.ametis.cms.service.PolicyDiagnosisExclusionService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.util.LoggingUtil;
import com.ametis.cms.util.ParsingUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * User: ZaQ <zaki.rahman@gmail.com>
 */
public class PolicyDiagnosisExclusionParser {

	private PolicyService policyService;
	private DiagnosisService diagnosisService;
	private DiagnosisSetService diagnosisSetService;
	private PolicyDiagnosisExclusionService diagnosisExclusionService;

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}

	public DiagnosisSetService getDiagnosisSetService() {
		return diagnosisSetService;
	}

	public void setDiagnosisSetService(DiagnosisSetService diagnosisSetService) {
		this.diagnosisSetService = diagnosisSetService;
	}

	public PolicyDiagnosisExclusionService getDiagnosisExclusionService() {
		return diagnosisExclusionService;
	}

	public void setDiagnosisExclusionService(PolicyDiagnosisExclusionService diagnosisExclusionService) {
		this.diagnosisExclusionService = diagnosisExclusionService;
	}

	public void parseFile(File theFile, ArrayList<String> errorList) {

		ActionUser user = new ActionUser();
		User theUser = new User();
		theUser.setUsername("parser");
		user.setUser(theUser);

	
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(theFile);
			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				Cell cellPolicyNumber = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
				Cell cellIcd10 = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
				Cell cellIcdSetId = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
				Cell cellPreExDate = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
				Cell cellDuration = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
				Cell cellAgeParameter = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
				Cell cellAgeConstraint = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
				Cell cellAction = row.getCell(7, Row.CREATE_NULL_AS_BLANK);

				// PolicyService policyService = new PolicyService();
				Policy policy = policyService
						.getActivePolicyByNumber(ParsingUtil.getCellValueAsString(cellPolicyNumber));
				if (policy == null) {
					String uploadNote = "Unable to Find Policy " + ParsingUtil.getCellValueAsString(cellPolicyNumber);
					System.out.println("[PolicyDiagnosisExclusion] " + uploadNote);
					
					errorList.add(uploadNote);
				} else {
					Diagnosis diagnosis = null;
					if (cellIcd10 != null) {
						diagnosis = diagnosisService.getDiagnosisByCode(ParsingUtil.getCellValueAsString(cellIcd10));
					}

					DiagnosisSet diagnosisSet = null;
					if (cellIcdSetId.equals("")) {
						diagnosisSet = diagnosisSetService.searchUnique("diagnosisSetCode",ParsingUtil.getCellValueAsString(cellIcdSetId));
					}
					System.out.println("diagnosisSetId =" + diagnosisSet);
					
					if (diagnosis == null && diagnosisSet == null) {
						String msg = "Unable to find Diagnosis / Diagnosis Set: "
								+ ParsingUtil.getCellValueAsString(cellIcd10) + " / "
								+ ParsingUtil.getCellValueAsString(cellIcdSetId);
						System.out.println("[PolicyDiagnosisExclusion] " + msg);
						errorList.add(msg);
					} else {
						if (diagnosis != null) {

							String[] param = { "policyId.policyId", "diagnosisId.diagnosisId", "deletedStatus" };
							Object[] value = { policy.getPolicyId(), diagnosis.getDiagnosisId(), Integer.valueOf(0) };

							PolicyDiagnosisExclusion policyDiagnosisExclusion = diagnosisExclusionService.searchUnique(param, value, 0, 1);

							if (policyDiagnosisExclusion == null) {
								policyDiagnosisExclusion = new PolicyDiagnosisExclusion();

								policyDiagnosisExclusion.setPolicyId(policy.getPolicyId());
								policyDiagnosisExclusion.setDiagnosisId(diagnosis);
								
								if (ParsingUtil.getCellValueAsNumeric(cellAgeConstraint) != null) {
									policyDiagnosisExclusion.setAgeConstraint(ParsingUtil.getCellValueAsNumeric(cellAgeConstraint).intValue());
								}
								policyDiagnosisExclusion.setAgeParameter(ParsingUtil.getCellValueAsString(cellAgeParameter));
								
								if (cellPreExDate != null){
									policyDiagnosisExclusion.setExpireDate(ParsingUtil.getCellValueAsDate(cellPreExDate));
								}
								if (cellDuration != null){
									if (ParsingUtil.getCellValueAsNumeric(cellDuration) != null){
										policyDiagnosisExclusion.setPreExistingDays(ParsingUtil.getCellValueAsNumeric(cellAgeConstraint).intValue());

									}
								}

								diagnosisExclusionService.create(policyDiagnosisExclusion, user);
							} else {
								String msg = "PDE Already Exists";
								System.out.println("[PolicyDiagnosisExclusion] " + msg);
								errorList.add(msg);

								if (ParsingUtil.getCellValueAsNumeric(cellAgeConstraint) != null) {
									policyDiagnosisExclusion.setAgeConstraint(
											ParsingUtil.getCellValueAsNumeric(cellAgeConstraint).intValue());
								}
								policyDiagnosisExclusion.setAgeParameter(ParsingUtil.getCellValueAsString(cellAgeParameter));
								policyDiagnosisExclusion.setExpireDate(ParsingUtil.getCellValueAsDate(cellPreExDate));

								if (cellDuration != null){
									if (ParsingUtil.getCellValueAsNumeric(cellDuration) != null){
										policyDiagnosisExclusion.setPreExistingDays(ParsingUtil.getCellValueAsNumeric(cellAgeConstraint).intValue());

									}
								}


								diagnosisExclusionService.update(policyDiagnosisExclusion, user);
							}

							if (diagnosisSet != null) {

								policyDiagnosisExclusion = diagnosisExclusionService.searchUnique(param, value, 0, 1);
								if (policyDiagnosisExclusion == null) {
									policyDiagnosisExclusion = new PolicyDiagnosisExclusion();

									policyDiagnosisExclusion.setPolicyId(policy.getPolicyId());
									policyDiagnosisExclusion.setDiagnosisId(diagnosis);
									if (ParsingUtil.getCellValueAsNumeric(cellAgeConstraint) != null) {
										policyDiagnosisExclusion.setAgeConstraint(
												ParsingUtil.getCellValueAsNumeric(cellAgeConstraint).intValue());
									}
									policyDiagnosisExclusion.setAgeParameter(ParsingUtil.getCellValueAsString(cellAgeParameter));

									if (cellPreExDate != null){
										policyDiagnosisExclusion.setExpireDate(ParsingUtil.getCellValueAsDate(cellPreExDate));
									}

									
									if (cellDuration != null){
										if (ParsingUtil.getCellValueAsNumeric(cellDuration) != null){
											policyDiagnosisExclusion.setPreExistingDays(ParsingUtil.getCellValueAsNumeric(cellAgeConstraint).intValue());

										}
									}


									diagnosisExclusionService.create(policyDiagnosisExclusion, user);
								} else {
									String msg = "PDE Already Exists";
									System.out.println("[PolicyDiagnosisExclusion] " + msg);
									errorList.add(msg);

									if (ParsingUtil.getCellValueAsNumeric(cellAgeConstraint) != null) {
										policyDiagnosisExclusion.setAgeConstraint(
												ParsingUtil.getCellValueAsNumeric(cellAgeConstraint).intValue());
									}
									policyDiagnosisExclusion
											.setAgeParameter(ParsingUtil.getCellValueAsString(cellAgeParameter));

									if (cellPreExDate != null){
										policyDiagnosisExclusion.setExpireDate(ParsingUtil.getCellValueAsDate(cellPreExDate));
									}

									
									if (cellDuration != null){
										if (ParsingUtil.getCellValueAsNumeric(cellDuration) != null){
											policyDiagnosisExclusion.setPreExistingDays(ParsingUtil.getCellValueAsNumeric(cellAgeConstraint).intValue());

										}
									}

									diagnosisExclusionService.update(policyDiagnosisExclusion, user);
								}
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("[PolicyDiagnosisExclusion] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println("[PolicyDiagnosisExclusion] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println("[PolicyDiagnosisExclusion] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (Exception e) {
			System.out.println("[PolicyDiagnosisExclusion] " + e.getMessage());
			errorList.add(e.getMessage());
		}
	}
}