package com.ametis.cms.util.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.DiagnosisSet;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberDiagnosisExclusion;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.DiagnosisSetService;
import com.ametis.cms.service.MemberDiagnosisExclusionService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.util.ParsingUtil;

public class MemberDiagnosisExclusionParser {

	private MemberService memberService;
	private PolicyService policyService;
	private DiagnosisService diagnosisService;
	private DiagnosisSetService diagnosisSetService;
	private MemberDiagnosisExclusionService memberDiagnosisExclusionService;

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

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

	public MemberDiagnosisExclusionService getMemberDiagnosisExclusionService() {
		return memberDiagnosisExclusionService;
	}

	public void setMemberDiagnosisExclusionService(MemberDiagnosisExclusionService memberDiagnosisExclusionService) {
		this.memberDiagnosisExclusionService = memberDiagnosisExclusionService;
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

				Cell cellFullname = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
				Cell cellMemberId = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
				Cell cellPolicyNumber = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
				Cell cellIcd10 = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
				Cell cellIcdSetId = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
				Cell cellPreExDate = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
				Cell cellDuration = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
				Cell cellAgeParameter = row.getCell(7, Row.CREATE_NULL_AS_BLANK);
				Cell cellAgeConstraint = row.getCell(8, Row.CREATE_NULL_AS_BLANK);
				Cell cellAction = row.getCell(9, Row.CREATE_NULL_AS_BLANK);

				String[] param = { "memberId", "policyId", "deletedStatus" };
				Object[] value = { ParsingUtil.getCellValueAsString(cellMemberId),
						ParsingUtil.getCellValueAsString(cellPolicyNumber), Integer.valueOf(0) };

				Member memberIdAndPolicyId = memberService.searchUnique(param, value, 0, 1);
				if (memberIdAndPolicyId == null) {
					String uploadNote = "Unable to Find Member / Policy "
							+ ParsingUtil.getCellValueAsString(cellMemberId) + " / "
							+ ParsingUtil.getCellValueAsString(cellPolicyNumber);
					System.out.println("[Member Diagnosis Exclusion Parser] " + uploadNote);

					errorList.add(uploadNote);
				} else {
					Member member = memberService.get(memberIdAndPolicyId.getMemberId());
					Policy policy = policyService.get(memberIdAndPolicyId.getCurrentPolicyId().getPolicyId());

					Diagnosis diagnosis = null;
					if (cellIcd10 != null) {
						diagnosis = diagnosisService.getDiagnosisByCode(ParsingUtil.getCellValueAsString(cellIcd10));
					}
					DiagnosisSet diagnosisSet = null;
					if (cellIcdSetId != null) {
						diagnosisSet = diagnosisSetService.searchUnique("diagnosisSetCode",
								ParsingUtil.getCellValueAsString(cellIcdSetId));
					}

					if (diagnosis == null && diagnosisSet == null) {
						String msg = "Unable to find Diagnosis / Diagnosis Set: "
								+ ParsingUtil.getCellValueAsString(cellIcd10) + " / "
								+ ParsingUtil.getCellValueAsString(cellIcdSetId);
						
						System.out.println("[Member Diagnosis Exclusion Parser] " + msg);
						errorList.add(msg);
					} else {
						// 
						if (diagnosis != null) {

							String[] paramMember = { "memberId.memberId", "policyId.policyId",
									"diagnosisId.diagnosisId", "deletedStatus" };
							
							Object[] valueMember = { member.getMemberId(), policy.getPolicyId(),
									diagnosis.getDiagnosisId(), Integer.valueOf(0) };
						
							MemberDiagnosisExclusion memberDiagnosisExclusion = memberDiagnosisExclusionService
									.searchUnique(paramMember, valueMember, 0, 1);

							if (memberDiagnosisExclusion == null) {
								memberDiagnosisExclusion = new MemberDiagnosisExclusion();

								memberDiagnosisExclusion.setMemberId(member);
								memberDiagnosisExclusion.setPolicyId(policy);
								memberDiagnosisExclusion.setDiagnosisId(diagnosis);
								memberDiagnosisExclusion.setExclusionStatus(0);
								memberDiagnosisExclusion.setAgeParameter(ParsingUtil.getCellValueAsString(cellAgeParameter));
							
								if (ParsingUtil.getCellValueAsNumeric(cellAgeConstraint) != null) {
									memberDiagnosisExclusion.setAgeConstraint(ParsingUtil.getCellValueAsNumeric(cellAgeConstraint).intValue());
								}
								/*
								 * fungsi untuk mengisi pre-exDate dari (joinDate + hari)
								 */
								String dt = "";
								System.out.println("join date = " + member.getJoinDate());
								int PreExDate = 0;
								try {
									PreExDate = Integer.parseInt(ParsingUtil.getCellValueAsString(cellPreExDate));
									
								} catch (Exception e) {
									PreExDate = Double.valueOf(ParsingUtil.getCellValueAsString(cellPreExDate)).intValue();
								}

								System.out.println("PreExDate = " + PreExDate);
								DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Calendar c = Calendar.getInstance();
								c.setTime(member.getJoinDate());
								c.add(Calendar.DATE, PreExDate - 1); // Number of days to add from  EXCEL
								dt = sdf.format(c.getTime()); // dt is now the new date
								System.out.println("Pre-Ex Date new = " + dt); // Check dt

								java.util.Date utilDate = sdf.parse(dt);
								java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

								if (sqlDate != null){
									memberDiagnosisExclusion.setExpireDate(sqlDate);
								}
								if (ParsingUtil.getCellValueAsNumeric(cellDuration) != null){
									memberDiagnosisExclusion.setPreExistingDays(ParsingUtil.getCellValueAsNumeric(cellDuration).intValue());
								}

								memberDiagnosisExclusionService.create(memberDiagnosisExclusion, user);
							} else {
								String msg = "MDE Already Exists";
								System.out.println("[Member Diagnosis Exclusion Parser] " + msg );
								errorList.add(msg);

								java.sql.Date sqlDate = new java.sql.Date(
										ParsingUtil.getCellValueAsDate(cellPreExDate).getTime());

								memberDiagnosisExclusion.setExpireDate(sqlDate);
								memberDiagnosisExclusion.setAgeParameter(ParsingUtil.getCellValueAsString(cellAgeParameter));
								
								if (ParsingUtil.getCellValueAsNumeric(cellAgeConstraint) != null) {
									memberDiagnosisExclusion.setAgeConstraint(ParsingUtil.getCellValueAsNumeric(cellAgeConstraint).intValue());
								}
								if (ParsingUtil.getCellValueAsNumeric(cellDuration) != null){
									memberDiagnosisExclusion.setPreExistingDays(ParsingUtil.getCellValueAsNumeric(cellDuration).intValue());
								}
								memberDiagnosisExclusionService.update(memberDiagnosisExclusion, user);
							}

							if (diagnosisSet != null) {
								memberDiagnosisExclusion = memberDiagnosisExclusionService.searchUnique(paramMember,
										valueMember, 0, 1);
								if (memberDiagnosisExclusion == null) {
									memberDiagnosisExclusion = new MemberDiagnosisExclusion();

									memberDiagnosisExclusion.setMemberId(member);
									memberDiagnosisExclusion.setPolicyId(policy);
									memberDiagnosisExclusion.setDiagnosisSetId(diagnosisSet);
									memberDiagnosisExclusion.setExclusionStatus(0);

									java.sql.Date sqlDate = new java.sql.Date(
											ParsingUtil.getCellValueAsDate(cellPreExDate).getTime());

									memberDiagnosisExclusion.setExpireDate(sqlDate);
									memberDiagnosisExclusion
											.setAgeParameter(ParsingUtil.getCellValueAsString(cellAgeParameter));
									if (ParsingUtil.getCellValueAsNumeric(cellAgeConstraint) != null) {
										memberDiagnosisExclusion.setAgeConstraint(
												ParsingUtil.getCellValueAsNumeric(cellAgeConstraint).intValue());
									}

									if (ParsingUtil.getCellValueAsNumeric(cellDuration) != null){
										memberDiagnosisExclusion.setPreExistingDays(ParsingUtil.getCellValueAsNumeric(cellDuration).intValue());
									}
									
									memberDiagnosisExclusionService.create(memberDiagnosisExclusion, user);
								} else {
									String msg = "MDE Already Exists";
									System.out.println("[Member Diagnosis Exclusion Parser] " + msg );
									errorList.add(msg);

									java.sql.Date sqlDate = new java.sql.Date(ParsingUtil.getCellValueAsDate(cellPreExDate).getTime());

									memberDiagnosisExclusion.setExpireDate(sqlDate);
									memberDiagnosisExclusion.setAgeParameter(ParsingUtil.getCellValueAsString(cellAgeParameter));
									if (ParsingUtil.getCellValueAsNumeric(cellAgeConstraint) != null) {
										memberDiagnosisExclusion.setAgeConstraint(ParsingUtil.getCellValueAsNumeric(cellAgeConstraint).intValue());
									}
									if (ParsingUtil.getCellValueAsNumeric(cellDuration) != null){
										memberDiagnosisExclusion.setPreExistingDays(ParsingUtil.getCellValueAsNumeric(cellDuration).intValue());
									}
									memberDiagnosisExclusionService.update(memberDiagnosisExclusion, user);
								}
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("[Member Diagnosis Exclusion Parser] " + e.getMessage() );
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println("[Member Diagnosis Exclusion Parser] " + e.getMessage() );
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println("[Member Diagnosis Exclusion Parser] " + e.getMessage() );
			errorList.add(e.getMessage());
		} catch (ParseException e) {
			System.out.println("[Member Diagnosis Exclusion Parser] " + e.getMessage() );
			errorList.add(e.getMessage());
		} catch (Exception e) {
			System.out.println("[Member Diagnosis Exclusion Parser] " + e.getMessage() );
			errorList.add(e.getMessage());
		}
	}

}
