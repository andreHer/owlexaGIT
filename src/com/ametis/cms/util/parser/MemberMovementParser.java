package com.ametis.cms.util.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.DataImportStage;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberIdAndPolicyId;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.DataImportStageService;
import com.ametis.cms.service.MemberElectronicCardService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberImportService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.util.EmailUtil;
import com.ametis.cms.util.LoggingUtil;
import com.ametis.cms.util.ParsingUtil;

public class MemberMovementParser {	
	private static final String DATE_FORMAT = "EEE MMM d HH:mm:ss zzz YYYY";

	private static final String OUTPUT_DIRECTORY = "/usr/local/document/parser/";
	
	private ClientService clientService;
	private ConfigurationService configurationService;
	private DataImportStageService dataImportStageService;
	private MemberElectronicCardService memberElectronicCardService;
	private MemberGroupService memberGroupService;
	private MemberImportService memberImportService;
	private MemberService memberService;
	private PolicyService policyService;	

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

	public DataImportStageService getDataImportStageService() {
		return dataImportStageService;
	}

	public void setDataImportStageService(
			DataImportStageService dataImportStageService) {
		this.dataImportStageService = dataImportStageService;
	}

	public MemberElectronicCardService getMemberElectronicCardService() {
		return memberElectronicCardService;
	}

	public void setMemberElectronicCardService(
			MemberElectronicCardService memberElectronicCardService) {
		this.memberElectronicCardService = memberElectronicCardService;
	}

	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}

	public MemberImportService getMemberImportService() {
		return memberImportService;
	}

	public void setMemberImportService(MemberImportService memberImportService) {
		this.memberImportService = memberImportService;
	}

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

	public static String getCellValueAsString(Cell cell) {
		String returnValue = null;

		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				returnValue = cell.getDateCellValue().toString();
			} else {
				returnValue = cell.getNumericCellValue() + "";
			}
		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			returnValue = cell.getRichStringCellValue().getString();
		}

		return returnValue;
	}

	public static java.sql.Date getCellValueAsDate(Cell cell) {
		java.sql.Date returnValue = null;
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				returnValue = new java.sql.Date(cell.getDateCellValue().getTime());
			}
		}
		return returnValue;
	}

	public void parseFile(File theFile, ArrayList<String> errorList) throws Exception {
		
		ActionUser user = new ActionUser();
		User theUser = new User();
		theUser.setUsername("parser");
		user.setUser(theUser);
		
		Logger logger = Logger.getLogger("membermovement-App");
		String outputDirectory = OUTPUT_DIRECTORY;

		FileHandler logFile;
		SimpleFormatter simpleFormatter = new SimpleFormatter();
		try {
			logFile = new FileHandler(LoggingUtil.getLogDirectory()
					+ "membermovement-parser-%g.log", 5242880, 10, true);
			logFile.setFormatter(simpleFormatter);
			logger.addHandler(logFile);
		} catch (IOException e) {
			String msg = "Failure Initializing Logging";
			logger.severe(msg);
			errorList.add(msg);
		}
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(theFile);
			Sheet sheet = workbook.getSheetAt(0);

			/* Service */
//			ClientService clientService = new ClientService();
//			ConfigurationService configurationService = new ConfigurationService();
//			DataImportStageService dataImportStageService = new DataImportStageService();
//			MemberElectronicCardService memberElectronicCardService = new MemberElectronicCardService();
//			MemberGroupService memberGroupService = new MemberGroupService();
//			MemberImportService memberImportService = new MemberImportService();
//			MemberService memberService = new MemberService();
//			PolicyService policyService = new PolicyService();

			Client client = null;

			String importNumber = "";
			String filename = "";
			String clientCode = "";
			String groupCode = "";

//			DataImportStage dataImportStage = dataImportStageService
//					.getOneByImportReadyFile(theFile.getName());
			String[] eqParam = {"importReadyFile", "deletedStatus"};
			Object[] eqValue = {theFile.getName(), 0};
			
			DataImportStage dataImportStage = null;
			
			Collection<DataImportStage> dataImportStageList = dataImportStageService.search(null, null, eqParam, eqValue, false, "id", 0, 1);
			
			if (dataImportStageList != null){
	        	Iterator<DataImportStage> iterator = dataImportStageList.iterator();
	        	
	        	if (iterator.hasNext()){
	        		dataImportStage = iterator.next();
	        	}
	        }
			
			
			if (dataImportStage == null) {
				// TODO: insert to dataImportStage with uuid as filename
				filename = UUID.randomUUID().toString();

				// Configuration configuration =
				// configurationService.getOneByUploadStorageDirectory("");

				dataImportStage = new DataImportStage();

				dataImportStage.setCreatedBy("MM-PARSER");

				java.sql.Date sqlDate = new java.sql.Date(new Timestamp(System.currentTimeMillis()).getTime());
				dataImportStage.setImportDate(sqlDate);
				
				dataImportStage.setImportReadyFile(filename);
				dataImportStage.setImportRawFile(theFile.getName());
				dataImportStage.setImportNumber(importNumber);

//				dataImportStageService.insertOneSelective(dataImportStage);
				dataImportStageService.create(dataImportStage, null, user);
				
			} else {
				// todo: get client name
				filename += theFile.getName();
			}

			/* Report */
			HSSFWorkbook workbookSuccess = new HSSFWorkbook();
			HSSFWorkbook workbookFailed = new HSSFWorkbook();
			HSSFSheet sheetSuccess = workbookSuccess.createSheet("Success");
			HSSFSheet sheetFailed = workbookFailed.createSheet("Failed");
			Row rowHeaderSuccess = sheetSuccess.createRow(0);
			Row rowHeaderFailed = sheetFailed.createRow(0);
			prepareHeader(rowHeaderSuccess, workbookSuccess);
			prepareHeader(rowHeaderFailed, workbookFailed);

			int rowSuccess = 1;
			int rowFailed = 1;

			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				boolean success = true;
				String uploadNote = "";

				Cell cellFullname = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
				Cell cellMemberId = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
				Cell cellPolicyNumber = row.getCell(34, Row.CREATE_NULL_AS_BLANK);
				Cell cellEmployeeNumber = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
				Cell cellEmployeeName = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
				Cell cellRelationship = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
				Cell cellVIP = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
				Cell cellWarning = row.getCell(7, Row.CREATE_NULL_AS_BLANK);
				Cell cellBlacklist = row.getCell(8, Row.CREATE_NULL_AS_BLANK);
				Cell cellSalary = row.getCell(9, Row.CREATE_NULL_AS_BLANK);
				Cell cellSwipeCardNo = row.getCell(10, Row.CREATE_NULL_AS_BLANK);
				Cell cellCardTemplate = row.getCell(11, Row.CREATE_NULL_AS_BLANK);
				Cell cellBirthdate = row.getCell(12, Row.CREATE_NULL_AS_BLANK);
				Cell cellDept = row.getCell(13, Row.CREATE_NULL_AS_BLANK);
				Cell cellJobFunction = row.getCell(14, Row.CREATE_NULL_AS_BLANK);
				Cell cellJoin = row.getCell(15, Row.CREATE_NULL_AS_BLANK);
				Cell cellEffective = row.getCell(16, Row.CREATE_NULL_AS_BLANK);
				Cell cellSex = row.getCell(17, Row.CREATE_NULL_AS_BLANK);
				Cell cellBank = row.getCell(20, Row.CREATE_NULL_AS_BLANK);
				Cell cellAcc = row.getCell(19, Row.CREATE_NULL_AS_BLANK);
				Cell cellBName = row.getCell(18, Row.CREATE_NULL_AS_BLANK);
				Cell cellBBranch = row.getCell(21, Row.CREATE_NULL_AS_BLANK);
				Cell cellSwiftCode = row.getCell(22, Row.CREATE_NULL_AS_BLANK);
				Cell cellExpireDate = row.getCell(23, Row.CREATE_NULL_AS_BLANK);
				Cell cellHandphone = row.getCell(24, Row.CREATE_NULL_AS_BLANK);
				Cell cellEmail = row.getCell(25, Row.CREATE_NULL_AS_BLANK);
				Cell cellPlanId = row.getCell(26, Row.CREATE_NULL_AS_BLANK);
				Cell cellIsProRate = row.getCell(27, Row.CREATE_NULL_AS_BLANK);
				Cell cellBpjsNo = row.getCell(28, Row.CREATE_NULL_AS_BLANK);
				Cell cellAddInfo = row.getCell(29, Row.CREATE_NULL_AS_BLANK);
				Cell cellSuspendStartDate = row.getCell(30, Row.CREATE_NULL_AS_BLANK);
				Cell cellSuspendEndDate = row.getCell(31, Row.CREATE_NULL_AS_BLANK);
				Cell cellNoClaim = row.getCell(32, Row.CREATE_NULL_AS_BLANK);
				Cell cellPrintCard = row.getCell(33, Row.CREATE_NULL_AS_BLANK);
				Cell cellCurrentPolicyNumber = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
				Cell cellActionType = row.getCell(51, Row.CREATE_NULL_AS_BLANK);

				Cell cellAnnualLimit = row.getCell(35, Row.CREATE_NULL_AS_BLANK);
				Cell cellGraceStartDate = row.getCell(36, Row.CREATE_NULL_AS_BLANK);
				Cell cellGraceEndDate = row.getCell(37, Row.CREATE_NULL_AS_BLANK);

				Cell cellImportBatchNumber = row.getCell(38, Row.CREATE_NULL_AS_BLANK);
				Cell cellSubPolicyNo = row.getCell(39, Row.CREATE_NULL_AS_BLANK);
				Cell cellLinkedCardNo = row.getCell(40, Row.CREATE_NULL_AS_BLANK);

				Cell cellGrupCode = row.getCell(27, Row.CREATE_NULL_AS_BLANK);
				Cell cellClientCode = row.getCell(41, Row.CREATE_NULL_AS_BLANK);
				Cell cellClientName = row.getCell(42, Row.CREATE_NULL_AS_BLANK);
				Cell cellPolicyExpireDate = row.getCell(43, Row.CREATE_NULL_AS_BLANK);
				Cell cellPaymentMethod = row.getCell(44, Row.CREATE_NULL_AS_BLANK);
				Cell cellNextMemberNumber = row.getCell(45, Row.CREATE_NULL_AS_BLANK);
				Cell cellNextPolicyNumber = row.getCell(46, Row.CREATE_NULL_AS_BLANK);

				Cell cellNewCardNumber = row.getCell(47, Row.CREATE_NULL_AS_BLANK);

				Cell cellNextNewGroupId = row.getCell(48, Row.CREATE_NULL_AS_BLANK);
				Cell cellNextNewRelationship = row.getCell(49, Row.CREATE_NULL_AS_BLANK);
				Cell cellNextEmployeeNumber = row.getCell(50, Row.CREATE_NULL_AS_BLANK);

				if (cellClientCode != null) {
					cellClientCode.setCellType(Cell.CELL_TYPE_STRING);
				}
				if (cellGrupCode != null) {
					cellGrupCode.setCellType(Cell.CELL_TYPE_STRING);
				}

				String strActionType = cellActionType.getStringCellValue();

				String strClientCode = ParsingUtil.getCellValueAsString(cellClientCode);
				String strGrupCode = ParsingUtil.getCellValueAsString(cellGrupCode);
				if (cellClientCode != null
						|| !strClientCode.trim().equalsIgnoreCase("")) {
					cellClientCode.setCellType(Cell.CELL_TYPE_STRING);
					Client oldClient = client;
//					client = clientService.getOneByCode(strClientCode);
					String[] eqClientParam = {"clientCode", "deletedStatus"};
					Object[] eqClientValue = {strClientCode, 0};
					
					Collection<Client> clientList = clientService.search(null, null, eqClientParam, eqClientValue, false, "clientId", 0, 1);
					
					if (clientList != null && !clientList.isEmpty()){
			        	  for (Iterator iterator = clientList.iterator(); iterator
								.hasNext();) {
			        		  
			        		client = (Client) iterator.next();
							break;
							
						}
			          }
					
					if (client == null) {
						client = oldClient;
					}
				}

				if (getCellValueAsString(cellMemberId) != null) {
					MemberImport memberImport = new MemberImport();
					
					memberImport.setMemberName(getCellValueAsString(cellFullname));
					memberImport.setMemberNumber(getCellValueAsString(cellMemberId));

					memberImport.setPolicyNumber(getCellValueAsString(cellCurrentPolicyNumber));
					memberImport.setParentNumber(getCellValueAsString(cellEmployeeNumber));
					memberImport.setParentName(getCellValueAsString(cellEmployeeName));
					if (getCellValueAsString(cellRelationship) != null) {
						memberImport.setRelationship(getCellValueAsString(
								cellRelationship).toUpperCase());
					}

					memberImport.setIsVIP(getCellValueAsString(cellVIP));

					memberImport.setIsWarning(getCellValueAsString(cellWarning));
					memberImport.setIsBlacklist(getCellValueAsString(cellBlacklist));
					memberImport.setSalary(getCellValueAsString(cellSalary));
					memberImport.setSwipeCardNumber(getCellValueAsString(cellSwipeCardNo));
					memberImport.setCardTemplate(getCellValueAsString(cellCardTemplate));
					// memberImport.setBirthdate(getCellValueAsString(cellBirthdate));
					SimpleDateFormat strBirthDate = new SimpleDateFormat(
							"yyyy-MM-dd");
					memberImport.setBirthdate(strBirthDate.format(cellBirthdate.getDateCellValue()));
					memberImport.setDepartment(getCellValueAsString(cellDept));
					memberImport.setJobFunction(getCellValueAsString(cellJobFunction));
					
					java.sql.Date sqlDateJoinDate = new java.sql.Date(cellJoin.getDateCellValue().getTime());
					memberImport.setJoinDate(sqlDateJoinDate);
					
					java.sql.Date sqlDateEffectiveDate = new java.sql.Date(getCellValueAsDate(cellEffective).getTime());
					memberImport.setEffectiveDate(sqlDateEffectiveDate);
					
					memberImport.setSex(getCellValueAsString(cellSex));
					
					memberImport.setBankName(getCellValueAsString(cellBank));
					memberImport.setAccountNo(getCellValueAsString(cellAcc));
					memberImport.setBankAccount(getCellValueAsString(cellBName));
					
					memberImport.setBankBranch(getCellValueAsString(cellBBranch));
					memberImport.setSwiftCode(getCellValueAsString(cellSwiftCode));
					
					java.sql.Date sqlDateExpireDate = new java.sql.Date(cellExpireDate.getDateCellValue().getTime());
					memberImport.setExpireDate(sqlDateExpireDate);
					
					memberImport.setHandphone(getCellValueAsString(cellHandphone));
					memberImport.setEmail(getCellValueAsString(cellEmail));
					memberImport.setProduct(getCellValueAsString(cellPlanId));
					memberImport.setIsProRate(getCellValueAsString(cellIsProRate));
					memberImport.setBpjsNumber(getCellValueAsString(cellBpjsNo));
					memberImport.setClientName(getCellValueAsString(cellClientName));
					// TODO: add info?
					memberImport.setSuspendStart(getCellValueAsString(cellSuspendStartDate));
					memberImport.setSuspendEnd(getCellValueAsString(cellSuspendEndDate));
					memberImport.setNoClaimFlag(getCellValueAsString(cellNoClaim));
					memberImport.setPrintCard(getCellValueAsString(cellPrintCard));
					if (memberImport.getPrintCard() != null) {
						memberImport.setPrintCard(memberImport.getPrintCard().toUpperCase());
					}
					memberImport.setActionType(getCellValueAsString(cellActionType));

					memberImport.setAnnualLimit(getCellValueAsString(cellAnnualLimit));
					
					java.sql.Date sqlDateGraceStartDate = new java.sql.Date(getCellValueAsDate(cellGraceStartDate).getTime());
					memberImport.setGraceStartDate(sqlDateGraceStartDate);
					
					java.sql.Date sqlDateGraceEndDate = new java.sql.Date(getCellValueAsDate(cellGraceEndDate).getTime());
					memberImport.setGraceEndDate(sqlDateGraceEndDate);

					memberImport.setImportBatchNumber(ParsingUtil.getCellValueAsString(cellImportBatchNumber));
					memberImport.setSubPolicyNumber(ParsingUtil.getCellValueAsString(cellSubPolicyNo));
					memberImport.setLinkedCardNumber(ParsingUtil.getCellValueAsString(cellLinkedCardNo));
					
					memberImport.setImportSessionId(dataImportStage); //dataImportStage.getId()
					memberImport.setGroupCode(strGrupCode);

					memberImport.setNewPolicyNumber(ParsingUtil.getCellValueAsString(cellPolicyNumber));
					// addition 2015-06-28
					
					memberImport.setNextMemberNumber(ParsingUtil.getCellValueAsString(cellNextMemberNumber));
					
					memberImport.setNextPolicyNumber(ParsingUtil.getCellValueAsString(cellNextPolicyNumber));
					
					// addition 2015-09-02
					memberImport.setNextNewGroupCode(ParsingUtil.getCellValueAsString(cellNextNewGroupId));					
					memberImport.setNextNewRelationship(ParsingUtil.getCellValueAsString(cellNextNewRelationship));
					memberImport.setNextNewEmployeeNumber(ParsingUtil.getCellValueAsString(cellNextEmployeeNumber));
					
					

					memberImport.setAdditionalInfo(ParsingUtil.getCellValueAsString(cellAddInfo));

					// sebelumnya pake ini tapi birthdate nya tidak sama
					// convert birthdate
					// DateFormat dateFormat = new
					// SimpleDateFormat(DATE_FORMAT);
					// Calendar calendar = new GregorianCalendar();
					// try {
					// calendar.setTime(dateFormat.parse(memberImport.getBirthdate()));
					// String strBirthDate = new
					// SimpleDateFormat("yyyy-MM-dd").format(dateFormat.parse(memberImport.getBirthdate()));
					// memberImport.setBirthdate(strBirthDate);
					// } catch (ParseException e) {
					// String msg = "Failure to properly format birthdate";
					// logger.severe(msg);
					// errorList.add(msg);
					// } catch (NullPointerException npe) {
					//
					// }

					importNumber = memberImport.getImportBatchNumber();

					if (cellClientCode != null) {
						// todo: more elegant than this?
						cellClientCode.setCellType(Cell.CELL_TYPE_STRING);
						memberImport.setClientCode(strClientCode);
					}

					if (memberImport.getGroupCode() != null) {
//						MemberGroup memberGroup = memberGroupService.getOneByCode(memberImport.getGroupCode());
						String[] eqMemberGroupParam = {"memberGroupCode", "deletedStatus"};
						Object[] eqMemberGroupValue = {memberImport.getGroupCode(), 0};
						
						MemberGroup memberGroup = null;
						
						Collection<MemberGroup> memberGroupList = memberGroupService.search(null, null, eqMemberGroupParam, eqMemberGroupValue, false, "memberGroupId", 0, 1);
						
						if (memberGroupList != null && !memberGroupList.isEmpty()){
				        	  for (Iterator iterator = memberGroupList.iterator(); iterator
									.hasNext();) {
				        		  
				        		memberGroup = (MemberGroup) iterator.next();
								break;
								
							}
				          }
						
						memberImport.setGroupName(memberGroup.getGroupName());
					}

					if (strActionType.equalsIgnoreCase("deletion")) {
						logger.info("Member Movement Deletion");

						MemberIdAndPolicyId memberIdAndPolicyId = null;

						if (memberImport.getGroupCode() != null) {
//							MemberGroup memberGroup = memberGroupService.getOneByCode(memberImport.getGroupCode());
							String[] eqMemberGroupParam = {"memberGroupCode", "deletedStatus"};
							Object[] eqMemberGroupValue = {memberImport.getGroupCode(), 0};
							
							MemberGroup memberGroup = null;
							
							Collection<MemberGroup> memberGroupList = memberGroupService.search(null, null, eqMemberGroupParam, eqMemberGroupValue, false, "memberGroupId", 0, 1);
							
							if (memberGroupList != null && !memberGroupList.isEmpty()){
					        	  for (Iterator iterator = memberGroupList.iterator(); iterator
										.hasNext();) {
					        		  
					        		memberGroup = (MemberGroup) iterator.next();
									break;
									
								}
					          }
							
							if (memberGroup != null
									&& memberGroup.getTipe() != null) {
								if (memberGroup.getTipe().trim().equalsIgnoreCase("I")) {
//									memberIdAndPolicyId = memberService.getMemberByNumberAndPolicy(memberImport.getCustomerNumber(), memberImport.getSubPolicyNumber());
									String[] eqMemberIdAndPolicyIdParam = {"deletedStatus"};
									Object[] eqMemberIdAndPolicyIdValue = {0}; //ga ada method .getCustomerNumber() And .getSubPolicyNumber()
									
									Collection<MemberIdAndPolicyId> memberIdAndPolicyIdList = memberService.search(null, null, eqMemberIdAndPolicyIdParam, eqMemberIdAndPolicyIdValue, false, "memberIdAndPolicyId", 0, 1);
									
									if (memberIdAndPolicyIdList != null && !memberIdAndPolicyIdList.isEmpty()){
							        	  for (Iterator iterator = memberIdAndPolicyIdList.iterator(); iterator
												.hasNext();) {
							        		  
							        		memberIdAndPolicyId = (MemberIdAndPolicyId) iterator.next();
											break;
											
										}
							          }
									
								}

							}
						} else {
//							memberIdAndPolicyId = memberService.getMemberByNumberAndPolicy(memberImport.getCustomerNumber(), memberImport.getPolicyNumber());
							String[] eqMemberIdAndPolicyIdParam = {"deletedStatus"};
							Object[] eqMemberIdAndPolicyIdValue = {0}; //ga ada method .getCustomerNumber() And .getSubPolicyNumber()
							
							Collection<MemberIdAndPolicyId> memberIdAndPolicyIdList = memberService.search(null, null, eqMemberIdAndPolicyIdParam, eqMemberIdAndPolicyIdValue, false, "memberIdAndPolicyId", 0, 1);
							
							if (memberIdAndPolicyIdList != null && !memberIdAndPolicyIdList.isEmpty()){
					        	  for (Iterator iterator = memberIdAndPolicyIdList.iterator(); iterator
										.hasNext();) {
					        		  
					        		memberIdAndPolicyId = (MemberIdAndPolicyId) iterator.next();
									break;
									
								}
					          }
							
						}

						if (memberIdAndPolicyId == null) {
							uploadNote = "Existing Member/Policy Not Found "
									+ cellMemberId.getStringCellValue()
									+ " / "
									+ cellCurrentPolicyNumber
											.getStringCellValue();
							logger.severe(uploadNote);
							errorList.add(uploadNote);
							success = false;
						} else {
//							Member member = memberService.getMemberById(memberIdAndPolicyId.getMemberId());
							String[] eqMemberParam = {"memberId", "deletedStatus"};
							Object[] eqMemberValue = {memberIdAndPolicyId.getMemberId(), 0};
							
							Member member = null;
							
							Collection<Member> memberList = memberService.search(null, null, eqMemberParam, eqMemberValue, false, "memberId", 0, 1);
							
							if (memberList != null && !memberList.isEmpty()){
					        	  for (Iterator iterator = memberList.iterator(); iterator
										.hasNext();) {
					        		  
					        		member = (Member) iterator.next();
									break;
									
								}
					          }
							
//							Policy policy = policyService.getByPK(memberIdAndPolicyId.getPolicyId());
							String[] eqPolicyParam = {"policyId", "deletedStatus"};
							Object[] eqPolicyValue = {memberIdAndPolicyId.getPolicyId(), 0};
							
							Policy policy = null;
							
							Collection<Policy> policyList = policyService.search(null, null, eqPolicyParam, eqPolicyValue, false, "policyId", 0, 1);
							if (policyList != null && !policyList.isEmpty()){
					        	  for (Iterator iterator = policyList.iterator(); iterator
										.hasNext();) {
					        		  
					        		policy = (Policy) iterator.next();
									break;
									
								}
					          }

							if (member == null) {
								uploadNote = "Unable to Find Member "
										+ cellMemberId.getStringCellValue();
								logger.warning(uploadNote);
								errorList.add(uploadNote);
								success = false;
							} else {
								java.sql.Date sqlDateResignedDate = new java.sql.Date(cellExpireDate.getDateCellValue().getTime());
								member.setResignedDate(sqlDateResignedDate);
								
								member.setStatus(2);
//								memberService.updateMemberSelectiveByPrimaryKey(member);
								memberService.update(member, user);

								if (member.getRelationship().equalsIgnoreCase("employee")) {
//									List<Member> memberList = memberService.getByParentId(member.getMemberId());
									String[] eqMemberRelationshipParam = {"memberId.memberId", "deletedStatus"};
									Object[] eqMemberRelationshipValue = {member.getMemberId(), 0};
									
									Collection<Member> memberRelationshipList = memberService.search(null, null, eqMemberRelationshipParam, eqMemberRelationshipValue, false, "memberId", 0, 1);
									
									if (memberRelationshipList != null && !memberRelationshipList.isEmpty()){
							        	  for (Iterator iterator = memberRelationshipList.iterator(); iterator
												.hasNext();) {
							        		  
							        		member = (Member) iterator.next();
											break;
											
										}
							          }
									
									for (Member item : memberList) {
										item.setResignedDate(sqlDateResignedDate);
										item.setStatus(2);
//										memberService.updateMemberSelectiveByPrimaryKey(item);
										memberService.update(item, user);
									}
								}
							}
						}
					} else if (strActionType.equalsIgnoreCase("addition")) {
//						MemberIdAndPolicyId memberIdAndPolicyId = memberService.getMemberByNumberAndPolicy(memberImport.getCustomerNumber(), memberImport.getPolicyNumber());
						String[] eqMemberIdAndPolicyIdParam = {"deletedStatus"};
						Object[] eqMemberIdAndPolicyIdValue = {0}; //ga ada method .getCustomerNumber() And .getSubPolicyNumber()
						
						MemberIdAndPolicyId memberIdAndPolicyId = null;
						
						Collection<MemberIdAndPolicyId> memberIdAndPolicyIdList = memberService.search(null, null, eqMemberIdAndPolicyIdParam, eqMemberIdAndPolicyIdValue, false, "memberIdAndPolicyId", 0, 1);
						
						if (memberIdAndPolicyIdList != null && !memberIdAndPolicyIdList.isEmpty()){
				        	  for (Iterator iterator = memberIdAndPolicyIdList.iterator(); iterator
									.hasNext();) {
				        		  
				        		memberIdAndPolicyId = (MemberIdAndPolicyId) iterator.next();
								break;
								
							}
				          }
						
						if (memberIdAndPolicyId != null) {
							uploadNote = "Member With Customer Number AND Policy Number Exists: "
//									+ memberImport.getCustomerNumber()
									+ memberImport.getMemberNumber()
									+ " AND " + memberImport.getPolicyNumber();
							logger.severe(uploadNote);
							errorList.add(uploadNote);
							success = false;
						} else {
							String newCardNumber = "";
							boolean checkDuplicate = true;
							if (memberImport.getPrintCard() != null
									&& memberImport.getPrintCard()
											.equalsIgnoreCase("y")
									&& memberImport.getSwipeCardNumber() == null) {
								//
								newCardNumber = memberService.generateCardNumber("100062", memberImport.getClientCode(), logger, errorList);
								memberImport.setSwipeCardNumber(newCardNumber);
//								memberImportService.updateMemberImportSelectiveByPrimaryKey(memberImport);
								memberImportService.update(memberImport, user);
							}
//							List<Member> sameCardList = memberService.getBySwipeCardNumber(memberImport.getSwipeCardNumber());
							String[] eqMemberParam = {"swipeCardNumber", "deletedStatus"};
							Object[] eqMemberValue = {memberImport.getSwipeCardNumber(), 0};
							
							Member member = null;
							
							Collection<Member> sameCardList = memberService.search(null, null, eqMemberParam, eqMemberValue, false, "memberId", 0, 1);
														
							if (sameCardList != null && !sameCardList.isEmpty()){
					        	  for (Iterator iterator = sameCardList.iterator(); iterator
										.hasNext();) {
					        		  
									member = (Member) iterator.next();
									break;
									
								}
					          }
							
							if (sameCardList.size() > 0) {
								uploadNote = "Member With Swipe Card Number Exists: "
										+ memberImport.getSwipeCardNumber();
								logger.severe(uploadNote);
								errorList.add(uploadNote);
								success = false;
							} else {
//								Member member = new Member();
//								Policy policy = policyService.getByNumber(memberImport.getPolicyNumber());
								
								String[] eqPolicyParam = {"policyNumber", "deletedStatus"};
								Object[] eqPolicyValue = {memberImport.getPolicyNumber(), 0};
								
								Policy policy = null;
								
								Collection<Policy> policyList = policyService.search(null, null, eqPolicyParam, eqPolicyValue, false, "policyId", 0, 1);
								
								if (policyList != null && !policyList.isEmpty()){
						        	  for (Iterator iterator = policyList.iterator(); iterator
											.hasNext();) {
						        		  
										policy = (Policy) iterator.next();
										break;
										
									}
						          }
								
								if (policy != null) {
									member.setCurrentPolicyId(policy); //policy.getPolicyId()
									member.setClientId(policy.getClientId());
									member.setMemberGroupId(policy
											.getMemberGroupId());
								} else {
									// bisa jadi individu
									if (memberImport.getGroupCode() != null) {
//										MemberGroup memberGroup = memberGroupService.getOneByCode(memberImport.getGroupCode());
										String[] eqMemberGroupParam = {"memberGroupCode", "deletedStatus"};
										Object[] eqMemberGroupValue = {memberImport.getGroupCode(), 0};
										
										MemberGroup memberGroup = null;
										
										Collection<MemberGroup> memberGroupList = memberGroupService.search(null, null, eqMemberGroupParam, eqMemberGroupValue, false, "memberGroupId", 0, 1);
										
										if (memberGroupList != null && !memberGroupList.isEmpty()){
								        	  for (Iterator iterator = memberGroupList.iterator(); iterator
													.hasNext();) {
								        		  
								        		memberGroup = (MemberGroup) iterator.next();
												break;
												
											}
								          }
										
										if (memberGroup != null
												&& memberGroup.getTipe() != null) {
											if (memberGroup.getTipe().trim().equalsIgnoreCase("I")) {
												
												String[] eqPolicyParamInd = {"policyNumber", "deletedStatus"};
												Object[] eqPolicyValueInd = {memberImport.getSubPolicyNumber(), 0};
												
												Policy policyIndividu = null;
												
												Collection<Policy> policyIndividuList = policyService.search(null, null, eqPolicyParamInd, eqPolicyValueInd, false, "policyId", 0, 1);
												
												if (policyIndividuList != null && !policyIndividuList.isEmpty()){
										        	  for (Iterator iterator = policyIndividuList.iterator(); iterator
															.hasNext();) {
										        		  
										        		policyIndividu = (Policy) iterator.next();
														break;
														
													}
										          }
												
												
//												client = clientService.getOneByCode(strClientCode);
												String[] eqClientParam = {"clientCode", "deletedStatus"};
												Object[] eqClientValue = {strClientCode, 0};
												
												Collection<Client> clientList = clientService.search(null, null, eqClientParam, eqClientValue, false, "clientId", 0, 1);
												
												if (clientList != null && !clientList.isEmpty()){
										        	  for (Iterator iterator = clientList.iterator(); iterator
															.hasNext();) {
										        		  
										        		client = (Client) iterator.next();
														break;
														
													}
										          }
												
//												memberGroup = memberGroupService.getOneByCode(strGrupCode);
												String[] eqMemberGroupTipeParam = {"memberGroupCode", "deletedStatus"};
												Object[] eqMemberGroupTipeValue = {strGrupCode, 0};
												
												Collection<MemberGroup> memberGroupTipeList = memberGroupService.search(null, null, eqMemberGroupTipeParam, eqMemberGroupValue, false, "memberGroupId", 0, 1);
												
												if (memberGroupTipeList != null && !memberGroupTipeList.isEmpty()){
										        	  for (Iterator iterator = memberGroupTipeList.iterator(); iterator
															.hasNext();) {
										        		  
										        		memberGroup = (MemberGroup) iterator.next();
														break;
														
													}
										          }

												
												if(policyIndividu != null){
													member.setCurrentPolicyId(policyIndividu);
												}
									
												if (client != null) {
													member.setClientId(client); //client.getClientId()
													member.setClientName(client.getClientName()); //client.getClientName()
												}

												if (memberGroup != null) {
													member.setMemberGroupId(memberGroup); //memberGroup.getMemberGroupId()
													member.setGroupName(memberGroup.getGroupName());
												}
											} else {
												uploadNote = "The Group Code is not for Individual: "
														+ memberImport
																.getGroupCode();
												logger.severe(uploadNote);
												errorList.add(uploadNote);
												success = false;
											}
										} else {
											uploadNote = "The Group Code has no Type: "
													+ memberImport
															.getGroupCode();
											logger.severe(uploadNote);
											errorList.add(uploadNote);
											success = false;
										}
									} else {
										success = false;
										uploadNote = "Group Code not Uploaded";
										logger.severe(uploadNote);
										errorList.add(uploadNote);
									}
								}
								if (success) {

									member.setFirstName(memberImport.getMemberName());

									member.setCustomerNumber(memberImport.getMemberNumber());
									member.setCurrentPolicyNumber(memberImport.getPolicyNumber());
									member.setParentNumber(memberImport.getParentNumber());
									member.setParentName(memberImport.getParentName());
									member.setRelationship(memberImport.getRelationship().toUpperCase());
									Integer isVip = 0;

									if (memberImport.getIsVIP() != null
											&& memberImport.getIsVIP().trim().equalsIgnoreCase("y")) {
										isVip = 1;
									}
									member.setIsVIP(isVip);

									if (memberImport.getSalary() != null) {
										member.setCurrentSalary(Double.parseDouble(memberImport.getSalary()));
									}
									java.sql.Date sqlDateBirthday = new java.sql.Date(cellBirthdate.getDateCellValue().getTime());
									member.setBirthday(sqlDateBirthday);
									
									member.setDepartment(memberImport.getDepartment());
									member.setJobPosition(memberImport.getJobFunction());
									member.setJoinDate(memberImport.getJoinDate());
									member.setEffectiveDate(memberImport.getEffectiveDate());

									member.setBank(memberImport.getBankName());
									member.setBankAccount(memberImport.getAccountNo());
//									member.setBank(memberImport.getBank());
									member.setBank(memberImport.getBankBranch());
//									member.setBankAccount(memberImport.getAccountNumber());
									member.setBankAccount(memberImport.getAccountNo());
									member.setBankBranch(memberImport.getBankBranch());
									member.setBankAccountName(memberImport.getBankAccount());

									member.setSwiftCode(memberImport.getSwiftCode());
									member.setExpireDate(memberImport.getExpireDate());
									member.setEmail(memberImport.getEmail());
									member.setCurrentProductCode(memberImport.getProduct());
									member.setGender(memberImport.getSex());
//									member.setCustomerPolicyNumber(memberImport.getCustomerNumber());
									member.setCustomerPolicyNumber(memberImport.getMemberNumber());
									member.setCurrentCardNumber(memberImport.getSwipeCardNumber());
									member.setOtherMemberNumber(memberImport.getBpjsNumber());
									member.setStatus(-1);
									if (memberImport.getAnnualLimit() != null) {
										try {
											Double annualLimit = Double
													.parseDouble(memberImport
															.getAnnualLimit());
											member.setActualCustomerLimit(annualLimit);
											member.setCustomerLimit(annualLimit);
										} catch (NumberFormatException e) {
											uploadNote = "Unable to Parse Annual Limit: "
													+ memberImport
															.getAnnualLimit();
											logger.severe(uploadNote);
											errorList.add(uploadNote);
											success = false;
										}
									}

									member.setLinkedCardNumber(memberImport
											.getLinkedCardNumber());
									member.setSubPolicyNumber(memberImport
											.getSubPolicyNumber());
									if (memberImport.getNoClaimFlag() != null
											&& memberImport.getNoClaimFlag()
													.equalsIgnoreCase("y")) {
										java.sql.Date sqlDateNoClaimEndDate = new java.sql.Date(ParsingUtil.getCellValueAsDate(cellSuspendEndDate).getTime());
										member.setNoClaimEndDate(sqlDateNoClaimEndDate);
										
										java.sql.Date sqlDateNoClaimStartDate = new java.sql.Date(ParsingUtil.getCellValueAsDate(cellSuspendStartDate).getTime());
										member.setNoClaimStartDate(sqlDateNoClaimStartDate);
										
										member.setSubjectToNoClaim(1);
									} else if (memberImport.getNoClaimFlag() != null
											&& memberImport.getNoClaimFlag()
													.equalsIgnoreCase("n")) {
										member.setSubjectToNoClaim(0);
									}

									Integer isBlacklist = 0;
									if (memberImport.getIsBlacklist() != null
											&& memberImport.getIsBlacklist()
													.trim()
													.equalsIgnoreCase("y")) {
										isBlacklist = 1;
									}
									member.setIsBlacklist(isBlacklist);

									Integer isWarning = 0;
									if (memberImport.getIsWarning() != null
											&& memberImport.getIsWarning()
													.trim()
													.equalsIgnoreCase("y")) {
										isWarning = 1;
									}
									member.setIsWarning(isWarning);

									if (cellPolicyExpireDate != null) {
										java.sql.Date sqlDatePolicyExpireDate = new java.sql.Date(ParsingUtil.getCellValueAsDate(cellPolicyExpireDate).getTime());
										member.setPolicyExpireDate(sqlDatePolicyExpireDate);
									}
									if (cellPaymentMethod != null) {
										member.setPaymentPeriodeMethod(cellPaymentMethod.getStringCellValue());
									}

//									memberService.insertMemberSelective(member);
									memberService.create(member, user);
								}
							}
						}

					} else if (strActionType.equalsIgnoreCase("update")) {

						MemberIdAndPolicyId memberIdAndPolicyId = null;

						if (memberImport.getGroupCode() != null) {
//							MemberGroup memberGroup = memberGroupService.getOneByCode(memberImport.getGroupCode());
							String[] eqMemberGroupParam = {"memberGroupCode", "deletedStatus"};
							Object[] eqMemberGroupValue = {memberImport.getGroupCode(), 0};
							
							MemberGroup memberGroup = null;
							
							Collection<MemberGroup> memberGroupList = memberGroupService.search(null, null, eqMemberGroupParam, eqMemberGroupValue, false, "memberGroupId", 0, 1);
							
							if (memberGroupList != null && !memberGroupList.isEmpty()){
					        	  for (Iterator iterator = memberGroupList.iterator(); iterator
										.hasNext();) {
					        		  
					        		memberGroup = (MemberGroup) iterator.next();
									break;
									
								}
					          }
							
							
							if (memberGroup != null && memberGroup.getTipe() != null) {
								if (memberGroup.getTipe().trim().equalsIgnoreCase("I")) {
//									memberIdAndPolicyId = memberService.getMemberByNumberAndPolicy(memberImport.getCustomerNumber(), memberImport.getSubPolicyNumber());
									String[] eqMemberIdAndPolicyIdParam = {"deletedStatus"};
									Object[] eqMemberIdAndPolicyIdValue = {0}; //ga ada method .getCustomerNumber() And .getSubPolicyNumber()
									
									Collection<MemberIdAndPolicyId> memberIdAndPolicyIdList = memberService.search(null, null, eqMemberIdAndPolicyIdParam, eqMemberIdAndPolicyIdValue, false, "memberIdAndPolicyId", 0, 1);
									
									if (memberIdAndPolicyIdList != null && !memberIdAndPolicyIdList.isEmpty()){
							        	  for (Iterator iterator = memberIdAndPolicyIdList.iterator(); iterator
												.hasNext();) {
							        		  
							        		memberIdAndPolicyId = (MemberIdAndPolicyId) iterator.next();
											break;
											
										}
							          }
								}
							}
						} else {
//							memberIdAndPolicyId = memberService.getMemberByNumberAndPolicy(memberImport.getCustomerNumber(), memberImport.getPolicyNumber());
							String[] eqMemberIdAndPolicyIdParam = {"deletedStatus"};
							Object[] eqMemberIdAndPolicyIdValue = {0}; //ga ada method .getCustomerNumber() And .getSubPolicyNumber()
							
							Collection<MemberIdAndPolicyId> memberIdAndPolicyIdList = memberService.search(null, null, eqMemberIdAndPolicyIdParam, eqMemberIdAndPolicyIdValue, false, "memberIdAndPolicyId", 0, 1);
							
							if (memberIdAndPolicyIdList != null && !memberIdAndPolicyIdList.isEmpty()){
					        	  for (Iterator iterator = memberIdAndPolicyIdList.iterator(); iterator
										.hasNext();) {
					        		  
					        		memberIdAndPolicyId = (MemberIdAndPolicyId) iterator.next();
									break;
									
								}
					          }
						}

						if (memberIdAndPolicyId == null) {
							uploadNote = "Existing Member/Policy Not Found  "
									+ cellMemberId.getStringCellValue()
									+ " / "
									+ cellCurrentPolicyNumber
											.getStringCellValue();
							logger.severe(uploadNote);
							errorList.add(uploadNote);
							success = false;
						} else {
//							Member member = memberService.getMemberById(memberIdAndPolicyId.getMemberId());
							String[] eqMemberParam = {"memberId.memberId", "deletedStatus"};
							Object[] eqMemberValue = {memberIdAndPolicyId.getMemberId(), 0};
							
							Member member = null;
							
							Collection<Member> memberList = memberService.search(null, null, eqMemberParam, eqMemberValue, false, "memberId", 0, 1);
							
							if (memberList != null && !memberList.isEmpty()){
					        	  for (Iterator iterator = memberList.iterator(); iterator
										.hasNext();) {
					        		  
					        		member = (Member) iterator.next();
									break;
									
								}
					          }
							
							member.setFirstName(memberImport.getMemberName());
							
							java.sql.Date sqlDateBirthday = new java.sql.Date(getCellValueAsDate(cellBirthdate).getTime());
							member.setBirthday(sqlDateBirthday);
							
							member.setBank(memberImport.getBankName());
							member.setBankAccountName(memberImport.getBankAccount());
							member.setBankAccount(memberImport.getAccountNo());
							member.setBank(memberImport.getBankName());

							member.setBankAccount(memberImport.getAccountNo());

							member.setBankBranch(memberImport.getBankBranch());
							member.setDepartment(memberImport.getDepartment());
							member.setJobPosition(memberImport.getJobFunction());
							member.setMobilePhone(memberImport.getHandphone());
							member.setEmail(memberImport.getEmail());
							member.setEffectiveDate(memberImport
									.getEffectiveDate());
							member.setJoinDate(memberImport.getJoinDate());
							if (memberImport.getNoClaimFlag() != null && memberImport.getNoClaimFlag().equalsIgnoreCase("y")) {
								java.sql.Date sqlDateNoClaimEndDate = new java.sql.Date(ParsingUtil.getCellValueAsDate(cellSuspendEndDate).getTime());
								member.setNoClaimEndDate(sqlDateNoClaimEndDate);
								
								java.sql.Date sqlDateNoClaimStartDate = new java.sql.Date(ParsingUtil.getCellValueAsDate(cellSuspendStartDate).getTime());
								member.setNoClaimStartDate(sqlDateNoClaimStartDate);
							} else if (memberImport.getNoClaimFlag() != null
									&& memberImport.getNoClaimFlag()
											.equalsIgnoreCase("n")) {
								member.setSubjectToNoClaim(0);
							}
							Integer isVip = 0;
							if (memberImport.getIsVIP() != null && memberImport.getIsVIP().trim().equalsIgnoreCase("y")) {
								isVip = 1;
							}

							member.setIsVIP(isVip);

							Integer isBlacklist = 0;
							if (memberImport.getIsBlacklist() != null
									&& memberImport.getIsBlacklist().trim()
											.equalsIgnoreCase("y")) {
								isBlacklist = 1;
							}
							member.setIsBlacklist(isBlacklist);
							Integer isWarning = 0;
							if (memberImport.getIsWarning() != null
									&& memberImport.getIsWarning().trim()
											.equalsIgnoreCase("y")) {
								isWarning = 1;
							}
							if (memberImport.getSalary() != null) {
								member.setCurrentSalary(Double
										.parseDouble(memberImport.getSalary()));
							}
							member.setIsWarning(isWarning);
							member.setLinkedCardNumber(memberImport
									.getLinkedCardNumber());
							member.setSubPolicyNumber(memberImport
									.getSubPolicyNumber());
							member.setExpireDate(memberImport.getExpireDate());
							member.setJoinDate(memberImport.getJoinDate());
							member.setOtherMemberNumber(memberImport
									.getBpjsNumber());
							if (memberImport.getAnnualLimit() != null) {
								try {
									Double annualLimit = Double
											.parseDouble(memberImport
													.getAnnualLimit());
									member.setActualCustomerLimit(annualLimit);
									member.setCustomerLimit(annualLimit);
								} catch (NumberFormatException e) {
									uploadNote = "Unable to Parse Annual Limit: "
											+ memberImport.getAnnualLimit();
									logger.severe(uploadNote);
									errorList.add(uploadNote);
									success = false;
								}
							}

							// 2015_02_16: Update menampilkan current card
							// number
							memberImport.setSwipeCardNumber(member.getCurrentCardNumber());
//							memberImportService.updateMemberImportSelectiveByPrimaryKey(memberImport);
							memberImportService.update(memberImport, user);
							
							// 2015_02_17: Tambah 2 column
							if (cellPolicyExpireDate != null) {
								java.sql.Date sqlDatePolicyExpireDate = new java.sql.Date(ParsingUtil.getCellValueAsDate(cellPolicyExpireDate).getTime());
								member.setPolicyExpireDate(sqlDatePolicyExpireDate);
							}
							if (cellPaymentMethod != null) {
								member.setPaymentPeriodeMethod(cellPaymentMethod.getStringCellValue());
							}

//							memberService.updateMemberSelectiveByPrimaryKey(member);
							memberService.update(member, user);
						}
					} else if (strActionType.equalsIgnoreCase("suspend")) {
//						MemberIdAndPolicyId memberIdAndPolicyId = memberService.getMemberByNumberAndPolicy(memberImport.getCustomerNumber(), memberImport.getPolicyNumber());
						String[] eqMemberIdAndPolicyIdParam = {"deletedStatus"};
						Object[] eqMemberIdAndPolicyIdValue = {0}; //ga ada method .getCustomerNumber() And .getSubPolicyNumber()
						
						MemberIdAndPolicyId memberIdAndPolicyId = null;
						
						Collection<MemberIdAndPolicyId> memberIdAndPolicyIdList = memberService.search(null, null, eqMemberIdAndPolicyIdParam, eqMemberIdAndPolicyIdValue, false, "memberIdAndPolicyId", 0, 1);
						
						if (memberIdAndPolicyIdList != null && !memberIdAndPolicyIdList.isEmpty()){
				        	  for (Iterator iterator = memberIdAndPolicyIdList.iterator(); iterator
									.hasNext();) {
				        		  
				        		memberIdAndPolicyId = (MemberIdAndPolicyId) iterator.next();
								break;
								
							}
				          }
						
						if (memberIdAndPolicyId == null) {
							uploadNote = "Existing Member/Policy Not Found "
									+ cellMemberId.getStringCellValue()
									+ " / "
									+ cellCurrentPolicyNumber
											.getStringCellValue();
							logger.severe(uploadNote);
							errorList.add(uploadNote);
							success = false;
						} else {
//							Member member = memberService.getMemberById(memberIdAndPolicyId.getMemberId());
							String[] eqMemberParam = {"memberId.memberId", "deletedStatus"};
							Object[] eqMemberValue = {memberIdAndPolicyId.getMemberId(), 0};
							
							Member member = null;
							
							Collection<Member> memberList = memberService.search(null, null, eqMemberParam, eqMemberValue, false, "memberId", 0, 1);
							
							if (memberList != null && !memberList.isEmpty()){
					        	  for (Iterator iterator = memberList.iterator(); iterator
										.hasNext();) {
					        		  
					        		member = (Member) iterator.next();
									break;
									
								}
					          }
							
							member.setStatus(-2);
							
							java.sql.Date sqlDateSuspendEndDate = new java.sql.Date(getCellValueAsDate(cellSuspendEndDate).getTime());
							member.setSuspendEndDate(sqlDateSuspendEndDate);
							
							java.sql.Date sqlDateSuspendStartDate = new java.sql.Date(getCellValueAsDate(cellSuspendStartDate).getTime());
							member.setSuspendStartDate(sqlDateSuspendStartDate);

//							memberService.updateMemberSelectiveByPrimaryKey(member);
							memberService.update(member, user);

							if (member.getRelationship().equalsIgnoreCase("employee")) {
//								List<Member> memberList = memberService.getByParentId(member.getMemberId());
								
								Collection<Member> memberRelationshipList = memberService.search(null, null, eqMemberParam, eqMemberValue, false, "memberId", 0, 1);
								
								if (memberRelationshipList != null && !memberRelationshipList.isEmpty()){
						        	  for (Iterator iterator = memberRelationshipList.iterator(); iterator
											.hasNext();) {
						        		  
						        		member = (Member) iterator.next();
										break;
										
									}
						          }
								
								for (Member item : memberList) {
									item.setStatus(-2);
									
									item.setSuspendEndDate(sqlDateSuspendEndDate);
									item.setSuspendStartDate(sqlDateSuspendStartDate);
//									memberService.updateMemberSelectiveByPrimaryKey(item);
									memberService.update(item, user);
								}
							}
						}
					} else if (strActionType.equalsIgnoreCase("changeplan")) {

						MemberIdAndPolicyId memberIdAndPolicyId = null;

						if (memberImport.getGroupCode() != null) {
//							MemberGroup memberGroup = memberGroupService.getOneByCode(memberImport.getGroupCode());
							String[] eqMemberGroupParam = {"memberGroupCode", "deletedStatus"};
							Object[] eqMemberGroupValue = {memberImport.getGroupCode(), 0};
							
							MemberGroup memberGroup = null;
							
							Collection<MemberGroup> memberGroupList = memberGroupService.search(null, null, eqMemberGroupParam, eqMemberGroupValue, false, "memberGroupId", 0, 1);
							
							if (memberGroupList != null && !memberGroupList.isEmpty()){
					        	  for (Iterator iterator = memberGroupList.iterator(); iterator
										.hasNext();) {
					        		  
					        		memberGroup = (MemberGroup) iterator.next();
									break;
									
								}
					          }
							
							if (memberGroup != null
									&& memberGroup.getTipe() != null) {
								if (memberGroup.getTipe().trim()
										.equalsIgnoreCase("I")) {
//									memberIdAndPolicyId = memberService.getMemberByNumberAndPolicy(memberImport.getCustomerNumber(), memberImport.getSubPolicyNumber());
									String[] eqMemberIdAndPolicyIdParam = {"deletedStatus"};
									Object[] eqMemberIdAndPolicyIdValue = {0}; //ga ada method .getCustomerNumber() And .getSubPolicyNumber()
									
									Collection<MemberIdAndPolicyId> memberIdAndPolicyIdList = memberService.search(null, null, eqMemberIdAndPolicyIdParam, eqMemberIdAndPolicyIdValue, false, "memberIdAndPolicyId", 0, 1);
									
									if (memberIdAndPolicyIdList != null && !memberIdAndPolicyIdList.isEmpty()){
							        	  for (Iterator iterator = memberIdAndPolicyIdList.iterator(); iterator
												.hasNext();) {
							        		  
							        		memberIdAndPolicyId = (MemberIdAndPolicyId) iterator.next();
											break;
											
										}
							          }
								}

							}
						} else {
//							memberIdAndPolicyId = memberService.getMemberByNumberAndPolicy(memberImport.getCustomerNumber(), memberImport.getPolicyNumber());
							String[] eqMemberIdAndPolicyIdParam = {"deletedStatus"};
							Object[] eqMemberIdAndPolicyIdValue = {0}; //ga ada method .getCustomerNumber() And .getSubPolicyNumber()
							
							Collection<MemberIdAndPolicyId> memberIdAndPolicyIdList = memberService.search(null, null, eqMemberIdAndPolicyIdParam, eqMemberIdAndPolicyIdValue, false, "memberIdAndPolicyId", 0, 1);
							
							if (memberIdAndPolicyIdList != null && !memberIdAndPolicyIdList.isEmpty()){
					        	  for (Iterator iterator = memberIdAndPolicyIdList.iterator(); iterator
										.hasNext();) {
					        		  
					        		memberIdAndPolicyId = (MemberIdAndPolicyId) iterator.next();
									break;
									
								}
					          }
						}

						if (memberIdAndPolicyId == null) {
							uploadNote = "Existing Member/Policy Not Found "
									+ cellMemberId.getStringCellValue()
									+ " / "
									+ cellCurrentPolicyNumber
											.getStringCellValue();
							logger.severe(uploadNote);
							errorList.add(uploadNote);
							success = false;
						} else {
//							Member member = memberService.getMemberById(memberIdAndPolicyId.getMemberId());
							String[] eqMemberParam = {"memberId", "deletedStatus"};
							Object[] eqMemberValue = {memberIdAndPolicyId.getMemberId(), 0};
							
							Member member = null;
							
							Collection<Member> memberList = memberService.search(null, null, eqMemberParam, eqMemberValue, false, "memberId", 0, 1);
							
							if (memberList != null && !memberList.isEmpty()){
					        	  for (Iterator iterator = memberList.iterator(); iterator
										.hasNext();) {
					        		  
					        		member = (Member) iterator.next();
									break;
									
								}
					          }
							
//							Policy policy = policyService.getByPK(memberIdAndPolicyId.getPolicyId());
							String[] eqPolicyParam = {"policyId", "deletedStatus"};
							Object[] eqPolicyValue = {memberIdAndPolicyId.getPolicyId(), 0};
							
							Policy policy = null;
							
							Collection<Policy> policyList = policyService.search(null, null, eqPolicyParam, eqPolicyValue, false, "policyId", 0, 1);
							if (policyList != null && !policyList.isEmpty()){
					        	  for (Iterator iterator = policyList.iterator(); iterator
										.hasNext();) {
					        		  
					        		policy = (Policy) iterator.next();
									break;
									
								}
					          }
							
							member.setCurrentProductCode(memberImport
									.getProduct());
							if (memberImport.getAnnualLimit() != null) {
								try {
									Double annualLimit = Double
											.parseDouble(memberImport
													.getAnnualLimit());
									member.setActualCustomerLimit(annualLimit);
									member.setCustomerLimit(annualLimit);
								} catch (NumberFormatException e) {
									uploadNote = "Unable to Parse Annual Limit: "
											+ memberImport.getAnnualLimit();
									logger.severe(uploadNote);
									errorList.add(uploadNote);
									success = false;
								}
							}
							member.setStatus(-3);
							member.setProductUpgradeEffectiveDate(memberImport
									.getEffectiveDate());

//							memberService.updateMemberSelectiveByPrimaryKey(member);
							memberService.update(member, user);
						}
					} else if (strActionType.equalsIgnoreCase("lostcard")) {
//						MemberIdAndPolicyId memberIdAndPolicyId = memberService.getMemberByNumberAndPolicy(memberImport.getCustomerNumber(), memberImport.getPolicyNumber());
						String[] eqMemberIdAndPolicyIdParam = {"deletedStatus"};
						Object[] eqMemberIdAndPolicyIdValue = {0}; //ga ada method .getCustomerNumber() And .getSubPolicyNumber()
						
						MemberIdAndPolicyId memberIdAndPolicyId = null;
						
						Collection<MemberIdAndPolicyId> memberIdAndPolicyIdList = memberService.search(null, null, eqMemberIdAndPolicyIdParam, eqMemberIdAndPolicyIdValue, false, "memberIdAndPolicyId", 0, 1);
						
						if (memberIdAndPolicyIdList != null && !memberIdAndPolicyIdList.isEmpty()){
				        	  for (Iterator iterator = memberIdAndPolicyIdList.iterator(); iterator
									.hasNext();) {
				        		  
				        		memberIdAndPolicyId = (MemberIdAndPolicyId) iterator.next();
								break;
								
							}
				          }
						
//						Policy policy = policyService.getByPK(memberIdAndPolicyId.getPolicyId());
						String[] eqPolicyParam = {"policyId", "deletedStatus"};
						Object[] eqPolicyValue = {memberIdAndPolicyId.getPolicyId(), 0};
						
						Policy policy = null;
						
						Collection<Policy> policyList = policyService.search(null, null, eqPolicyParam, eqPolicyValue, false, "policyId", 0, 1);
						if (policyList != null && !policyList.isEmpty()){
				        	  for (Iterator iterator = policyList.iterator(); iterator
									.hasNext();) {
				        		  
				        		policy = (Policy) iterator.next();
								break;
								
							}
				          }

						if (memberIdAndPolicyId == null) {
							uploadNote = "Existing Member/Policy Not Found "
									+ cellMemberId.getStringCellValue()
									+ " / "
									+ cellCurrentPolicyNumber
											.getStringCellValue();
							logger.severe(uploadNote);
							errorList.add(uploadNote);
							success = false;
						} else {
//							Member member = memberService.getMemberById(memberIdAndPolicyId.getMemberId());
							String[] eqMemberParam = {"memberId", "deletedStatus"};
							Object[] eqMemberValue = {memberIdAndPolicyId.getMemberId(), 0};
							
							Member member = null;
							
							Collection<Member> memberList = memberService.search(null, null, eqMemberParam, eqMemberValue, false, "memberId", 0, 1);
							
							if (memberList != null && !memberList.isEmpty()){
					        	  for (Iterator iterator = memberList.iterator(); iterator
										.hasNext();) {
					        		  
					        		member = (Member) iterator.next();
									break;
									
								}
					          }
							
							MemberElectronicCard card = memberElectronicCardService.getMemberCardByNumber(member.getMemberId(), member.getCurrentCardNumber());
							card.setCardStatus(MemberElectronicCard.CARD_EXPIRED);
							memberElectronicCardService.update(card, user);
							
							String newCardNumber = "";
							if (memberImport.getPrintCard().equalsIgnoreCase("y")
									&& memberImport.getSwipeCardNumber() == null) {
								newCardNumber = memberService.generateCardNumber("100062", memberImport.getClientCode(), logger, errorList);
								memberImport.setSwipeCardNumber(newCardNumber);
								memberImportService.update(memberImport, user);

								MemberElectronicCard currentCard = memberElectronicCardService.getMemberCardByStatus(member.getMemberId(), MemberElectronicCard.CARD_ACTIVE);

								MemberElectronicCard newCard = new MemberElectronicCard();

								newCard.setMemberId(member);
								newCard.setPolicyId(policy);
								
								newCard.setMemberNumber(member.getCustomerNumber());
								
								if (currentCard != null) {
									newCard.setReplacedCardId(currentCard);
								}
								newCard.setExpireDate(memberImport.getExpireDate());
								newCard.setCardNumber(memberImport.getSwipeCardNumber());
								newCard.setEffectiveDate(memberImport.getEffectiveDate());

								memberElectronicCardService.create(newCard, user);
								
							} else if (memberImport.getSwipeCardNumber() != null) {
								newCardNumber = memberImport.getSwipeCardNumber();
							}
							member.setCurrentCardNumber(newCardNumber);
							member.setEffectiveDate(memberImport.getEffectiveDate());

//							memberService.updateMemberSelectiveByPrimaryKey(member);
							memberService.update(member, user);
						}
					} else if (strActionType.equalsIgnoreCase("reactivecard")) {
						
						Member memberIdAndPolicyId = memberService
								.getMemberByPolicyNumber(
										memberImport.getMemberNumber(),
										memberImport.getPolicyNumber());

						if (memberIdAndPolicyId == null) {
							uploadNote = "Existing Member/Policy Not Found "
									+ cellMemberId.getStringCellValue()
									+ " / "
									+ cellCurrentPolicyNumber
											.getStringCellValue();
							logger.severe(uploadNote);
							errorList.add(uploadNote);
							success = false;
						} else {
							Member member = memberService
									.get(memberIdAndPolicyId
											.getMemberId());
							Policy policy = policyService
									.get(memberIdAndPolicyId.getCurrentPolicyId().getPolicyId());

							MemberElectronicCard currentCard = memberElectronicCardService.getMemberCardByStatus(member.getMemberId(), MemberElectronicCard.CARD_ACTIVE);
							
							if (currentCard != null
									&& currentCard.getCardNumber().equals(
											memberImport.getSwipeCardNumber())) {
								
								MemberElectronicCard card = memberElectronicCardService.getMemberCardByNumber(member.getMemberId(),
										member.getCurrentCardNumber());
								
								
								card.setCardStatus(MemberElectronicCard.CARD_ACTIVE);
								
								memberElectronicCardService.update(card, user);
								
							} else {
								// todo: ganti kartu, lost dulu card-nya perlu?
								MemberElectronicCard card = memberElectronicCardService.getMemberCardByNumber(member.getMemberId(), member.getCurrentCardNumber());
								card.setCardStatus(MemberElectronicCard.CARD_EXPIRED);
								memberElectronicCardService.update(card, user);
								
								// kalau card number tidak ada, generate
								String newCardNumber = "";
								if (memberImport.getPrintCard()
										.equalsIgnoreCase("y")
										&& memberImport.getSwipeCardNumber() == null) {
									newCardNumber = memberService
											.generateCardNumber("100062",
													memberImport
															.getClientCode(),
													logger, errorList);
									memberImport
											.setSwipeCardNumber(newCardNumber);
									memberImportService
											.update(memberImport,user);
								} else if (memberImport.getSwipeCardNumber() != null) {
									newCardNumber = memberImport
											.getSwipeCardNumber();
								}
								member.setCurrentCardNumber(newCardNumber);

								MemberElectronicCard newCard = new MemberElectronicCard();
								newCard.setMemberId(member);
								newCard.setPolicyId(policy);
								newCard.setMemberNumber(member
										.getCustomerNumber());
								if (currentCard != null) {
									newCard.setReplacedCardId(currentCard);
								}
								newCard.setExpireDate(memberImport
										.getExpireDate());
								newCard.setCardNumber(memberImport
										.getSwipeCardNumber());
								newCard.setEffectiveDate(memberImport
										.getEffectiveDate());

								memberElectronicCardService
										.create(newCard,user);
							}
							member.setEffectiveDate(memberImport
									.getEffectiveDate());
							member.setResignedDate(member.getExpireDate());
							member.setCurrentCardNumber(memberImport
									.getSwipeCardNumber());

							memberService
									.update(member,user);

						}
					} else if (strActionType.equalsIgnoreCase("reactive")) {

						Member memberIdAndPolicyId = null;

						if (memberImport.getGroupCode() != null) {
							MemberGroup memberGroup = memberGroupService.getMemberGroupByCode(memberImport.getGroupCode());
									
									//.getOneByCode(memberImport.getGroupCode());
							if (memberGroup != null
									&& memberGroup.getTipe() != null) {
								if (memberGroup.getTipe().trim()
										.equalsIgnoreCase("I")) {
									memberIdAndPolicyId = memberService
											.getMemberByPolicyNumber(
													memberImport
															.getMemberNumber(),
													memberImport
															.getSubPolicyNumber());
								}

							}
						} else {
							memberIdAndPolicyId = memberService
									.getMemberByPolicyNumber(
											memberImport.getMemberNumber(),
											memberImport.getPolicyNumber());
						}

						if (memberIdAndPolicyId == null) {
							uploadNote = "Existing Member/Policy Not Found "
									+ cellMemberId.getStringCellValue()
									+ " / "
									+ cellCurrentPolicyNumber
											.getStringCellValue();
							logger.severe(uploadNote);
							errorList.add(uploadNote);
							success = false;
						} else {
							Member member = memberService
									.get(memberIdAndPolicyId
											.getMemberId());
							member.setStatus(1);

							memberService
									.update(member,user);
							
							MemberElectronicCard card = memberElectronicCardService.getMemberCardByNumber(member.getMemberId(),
									member.getCurrentCardNumber());
							
							
							card.setCardStatus(MemberElectronicCard.CARD_ACTIVE);
							
							memberElectronicCardService.update(card, user);

							if (member.getRelationship().equalsIgnoreCase(
									"employee")) {
								Collection<Member> memberList = memberService.getDependentList(member.getMemberId());
								
								for (Member item : memberList) {
									item.setStatus(1);
									memberService.update(item,user);

									card = memberElectronicCardService.getMemberCardByNumber(item.getMemberId(),
											item.getCurrentCardNumber());
									
									
									card.setCardStatus(MemberElectronicCard.CARD_ACTIVE);
									
									memberElectronicCardService.update(card, user);
								}
							}
						}
					} else if (strActionType.equalsIgnoreCase("reprint")) {
						// todo: reprint
					} else if (strActionType.equalsIgnoreCase("mutation")) {
						Policy policy = policyService
								.getActivePolicyByNumber(cellPolicyNumber
										.getStringCellValue());

						if (policy == null) {
							uploadNote = "New Policy "
									+ cellPolicyNumber.getStringCellValue()
									+ " not found";
							logger.severe(uploadNote);
							errorList.add(uploadNote);
							success = false;
						} else {
							Member memberIdAndPolicyId = memberService
									.getMemberByPolicyNumber(
											memberImport.getMemberNumber(),
											memberImport.getPolicyNumber());

							if (memberIdAndPolicyId == null) {
								uploadNote = "Existing Member/Policy Not Found "
										+ cellMemberId.getStringCellValue()
										+ " / "
										+ cellCurrentPolicyNumber
												.getStringCellValue();
								logger.severe(uploadNote);
								errorList.add(uploadNote);
								success = false;
							} else {
								Member member = memberService
										.get(memberIdAndPolicyId
												.getMemberId());
								String policyNumber = ParsingUtil
										.getCellValueAsString(cellPolicyNumber);
								member.setCurrentPolicyId(policy);
								member.setCurrentPolicyNumber(policyNumber);

								// addition 2015-09-02
								member.setNextNewGroupCode(memberImport
										.getNextNewGroupCode());
								member.setNextNewRelationship(memberImport
										.getNextNewRelationship());
								
								member.setNextEmployeeNumber(memberImport
										.getNextNewEmployeeNumber());

								memberService
										.update(member,user);

								Collection<Member> memberList = memberService.getDependentList(member.getMemberId());
								
								for (Member item : memberList) {
									item.setCurrentPolicyId(policy);
									item.setCurrentPolicyNumber(policyNumber);
									memberService
											.update(item,user);
								}
							}
						}
					} else if (strActionType.equalsIgnoreCase("renewal")) {
						Member memberIdAndPolicyId = memberService
								.getMemberByPolicyNumber(
										memberImport.getMemberNumber(),
										memberImport.getPolicyNumber());

						if (memberIdAndPolicyId == null) {
							uploadNote = "Existing Member/Policy Not Found "
									+ cellMemberId.getStringCellValue()
									+ " / "
									+ cellCurrentPolicyNumber
											.getStringCellValue();
							logger.severe(uploadNote);
							errorList.add(uploadNote);
							success = false;
						} else {
							Member member = memberService
									.get(memberIdAndPolicyId
											.getMemberId());
							member.setStatus(-4);
							member.setCurrentProductCode(memberImport
									.getProduct());
							if (member.getExpireDate().before(
									Calendar.getInstance().getTime())) {
								member.setEffectiveDate(memberImport
										.getEffectiveDate());
								member.setExpireDate(memberImport
										.getExpireDate());
							} else {
								
								member.setPendingRenewalEffectiveDate(memberImport
										.getEffectiveDate());
								member.setPendingRenewalExpireDate(memberImport
										.getExpireDate());
							}
							member.setRenewalDate(getCellValueAsDate(cellAddInfo));
							/**
							 * Penambahan 2015 - 06 - 28 Ada 3 Field di DB Next
							 * Customer Number Next Policy Number Next Card
							 * Number ada 2 field tambahan di excel Next
							 * Customer Number (isi ke db) Next Policy Number
							 * (isi ke db) Jika swipe card number kosong maka
							 * generate nomor kartu dan isi ke next card number
							 * Jika swipe card number berisi, copy ke next card
							 * number
							 * 
							 * Kalau generate nomor kartu ataupun insert
							 * langsung, update juga ke member import
							 */
							member.setNextCustomerNumber(memberImport
									.getNextMemberNumber());
							member.setNextPolicyNumber(memberImport
									.getNextPolicyNumber());
							/**
							 * Penambahan 2015 - 08 -08 Tambah flag new card
							 * number, apabila swipe card number sudah ada, maka
							 * diisi N Apabila new card number isinya Y, generate
							 * number baru terlepas dari swipe card number ada
							 * isi atau tidak
							 */
							String flagNewCardNumber = ParsingUtil
									.getCellValueAsString(cellNewCardNumber)
									.trim();
							String newCardNumber = "";
							if (flagNewCardNumber.equalsIgnoreCase("y")) {
								newCardNumber = memberService
										.generateCardNumber("100062",
												memberImport.getClientCode(),
												logger, errorList);
							} else {
								newCardNumber = memberImport
										.getSwipeCardNumber();
							}
							/**
							 * Penambahan 2015 - 08 - 10 di member import tetap
							 * pakai nomoer yang lama sehingga baris
							 * memberImport.setSwipeCardNumber(newCardNumber);
							 * dihilangkan addendum ternyata
							 * setSwipeCardNumber-nya kalau kosong diambil dari
							 * current card number
							 */
							memberImport.setSwipeCardNumber(member
									.getCurrentCardNumber());
							memberImport.setNextCardNumber(newCardNumber);
							member.setNextCardNumber(newCardNumber);

							memberImport.setNextEffectiveDate(memberImport
									.getEffectiveDate());
							memberImport.setNextExpireDate(memberImport
									.getExpireDate());
							member.setNextEffectiveDate(memberImport
									.getEffectiveDate());
							member.setNextExpireDate(memberImport
									.getExpireDate());

							memberImportService.update(memberImport,user);
							memberService.update(member,user);
						}
					} else if (strActionType.equalsIgnoreCase("grace")) {
						Member memberIdAndPolicyId = memberService
								.getMemberByPolicyNumber(
										memberImport.getMemberNumber(),
										memberImport.getPolicyNumber());
						if (memberIdAndPolicyId == null) {
							uploadNote = "Existing Member/Policy Not Found "
									+ cellMemberId.getStringCellValue()
									+ " / "
									+ cellCurrentPolicyNumber
											.getStringCellValue();
							logger.severe(uploadNote);
							errorList.add(uploadNote);
							success = false;
						} else {
							Member member = memberService.get(memberIdAndPolicyId
											.getMemberId());
							member.setStatus(7);

							member.setSuspendStartDate(memberImport
									.getGraceStartDate());
							member.setSuspendEndDate(memberImport
									.getGraceEndDate());

							memberService
									.update(member,user);

							if (member.getRelationship().equalsIgnoreCase(
									"employee")) {
								Collection<Member> memberList = memberService.getDependentList(member.getMemberId());
								
								for (Member item : memberList) {
									item.setStatus(7);

									item.setSuspendStartDate(memberImport
											.getGraceStartDate());
									item.setSuspendEndDate(memberImport
											.getGraceEndDate());
									memberService
											.update(item,user);
								}
							}
						}
					} else {
						uploadNote = "Unknown Action "
								+ ParsingUtil
										.getCellValueAsString(cellActionType);
						logger.severe(uploadNote);
						errorList.add(uploadNote);
						success = false;
					}

					if (!success)
						memberImport.setUploadNote(uploadNote);

					memberImportService.create(memberImport,user);

					if (success) {
						Row newRow = workbookSuccess.getSheet("Success")
								.createRow(rowSuccess++);
						prepareRow(newRow, memberImport, uploadNote,
								cellAddInfo, cellPrintCard, cellPolicyNumber,
								workbookSuccess, cellClientName,
								cellPolicyExpireDate, cellPaymentMethod);
					} else {
						Row newRow = workbookFailed.getSheet("Failed")
								.createRow(rowFailed++);
						prepareRow(newRow, memberImport, uploadNote,
								cellAddInfo, cellPrintCard, cellPolicyNumber,
								workbookFailed, cellClientName,
								cellPolicyExpireDate, cellPaymentMethod);
					}

					clientCode = ParsingUtil
							.getCellValueAsString(cellClientCode);
					if (clientCode == null || clientCode.equals("")) {
						clientCode = "UNKNOWN";
					}
					groupCode = memberImport.getGroupCode();
					if (groupCode == null || groupCode.equals("")) {
						groupCode = "UNKNOWN";
					}
					if (importNumber == null || importNumber.equals("")) {
						importNumber = "UNKNOWN";
					}
				}
			}
			if (dataImportStage.getImportNumber() == null) {
				dataImportStage.setImportNumber(importNumber);
				dataImportStageService.update(dataImportStage, user);
			}

			outputDirectory += clientCode + "/";
			Date currentDate = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
			outputDirectory += simpleDateFormat.format(currentDate) + "/";

			outputDirectory += groupCode + "/";

			simpleDateFormat = new SimpleDateFormat("MM");
			outputDirectory += simpleDateFormat.format(currentDate) + "/";
			simpleDateFormat = new SimpleDateFormat("dd");
			outputDirectory += simpleDateFormat.format(currentDate) + "/";
			outputDirectory += importNumber + "/";

			try {
				Files.createDirectories(Paths.get(outputDirectory));
			} catch (FileAlreadyExistsException faee) {
				String msg = outputDirectory + "already exists";
			} catch (IOException e) {
				String msg = "Error creating " + outputDirectory.toString();
				logger.warning(msg);
				errorList.add(msg);
			}

			String successFilename = outputDirectory + filename
					+ "-success.xls";
			FileOutputStream outputStream = new FileOutputStream(new File(
					successFilename));
			workbookSuccess.write(outputStream);
			outputStream.close();
			String failedFilename = outputDirectory + filename + "-failed.xls";
			outputStream = new FileOutputStream(new File(failedFilename));
			workbookFailed.write(outputStream);
			outputStream.close();

			// sending email
			if (client != null && client.getEmail() != null) {
				String toAddress = client.getEmail();
				String fromAddress = EmailUtil.getSenderEmailAddress();
				String host = EmailUtil.getHost();
				Properties properties = System.getProperties();
				properties.setProperty("mail.smtp.host", host);
				Session session = Session.getDefaultInstance(properties);
				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(fromAddress));
					message.addRecipient(Message.RecipientType.TO,
							new InternetAddress(toAddress));
					message.addRecipient(
							Message.RecipientType.BCC,
							new InternetAddress("notification.upload@gmail.com"));
					message.setSubject(EmailUtil.getSubject());

					BodyPart bodyPart = new MimeBodyPart();
					bodyPart.setText(EmailUtil.getEmailText());
					Multipart multipart = new MimeMultipart();
					multipart.addBodyPart(bodyPart);

					bodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(successFilename);
					bodyPart.setDataHandler(new DataHandler(source));
					bodyPart.setFileName(successFilename);
					multipart.addBodyPart(bodyPart);

					bodyPart = new MimeBodyPart();
					source = new FileDataSource(failedFilename);
					bodyPart.setDataHandler(new DataHandler(source));
					bodyPart.setFileName(failedFilename);
					multipart.addBodyPart(bodyPart);

					message.setContent(multipart);
					logger.info("Multipart " + multipart.toString());

					Transport.send(message);
					logger.info("Successful Sending Mail");
				} catch (MessagingException mex) {
					logger.warning(mex.getMessage());
					errorList.add(mex.getMessage());
				}
			} else {
				String msg = "Client Failure: " + client;
				logger.warning(msg);
				errorList.add(msg);
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			errorList.add(e.getMessage());
		} catch (IllegalStateException e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			errorList.add(e.getMessage());
		} catch (FileNotFoundException e) {
			logger.severe(e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			logger.severe(e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			logger.severe(e.getMessage());
			errorList.add(e.getMessage());
		}
	}

	private void prepareRow(Row row, MemberImport memberImport, String note,
			Cell cellAddInfo, Cell cellPrintCard, Cell cellPolicyNumber,
			Workbook workbook, Cell cellClientName, Cell cellPolicyExpireDate,
			Cell cellPaymentPeriod) {
		prepareRow(row, memberImport, note, cellAddInfo, cellPrintCard,
				cellPolicyNumber, workbook);
		CreationHelper creationHelper = workbook.getCreationHelper();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(
				"yyyy/mm/dd"));
		row.createCell(42).setCellValue(
				ParsingUtil.getCellValueAsString(cellClientName));
		row.createCell(43).setCellStyle(cellStyle);
		if (cellPolicyExpireDate != null) {
			Date date = ParsingUtil.getCellValueAsDate(cellPolicyExpireDate);
			if (date != null) {
				row.getCell(43).setCellValue(
						ParsingUtil.getCellValueAsDate(cellPolicyExpireDate));
			}
		}
		row.createCell(44).setCellValue(
				ParsingUtil.getCellValueAsString(cellPaymentPeriod));
	}

	private void prepareRow(Row row, MemberImport memberImport, String note,
			Cell cellAddInfo, Cell cellPrintCard, Cell cellPolicyNumber,
			Workbook workbook) {
		CreationHelper creationHelper = workbook.getCreationHelper();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(
				"yyyy/mm/dd"));

		row.createCell(0).setCellValue(memberImport.getMemberName());
		row.createCell(1).setCellValue(memberImport.getMemberNumber());
		row.createCell(2).setCellValue(memberImport.getPolicyNumber());
		row.createCell(3).setCellValue(memberImport.getParentNumber());
		row.createCell(4).setCellValue(memberImport.getParentName());
		row.createCell(5).setCellValue(memberImport.getRelationship());
		row.createCell(6).setCellValue(memberImport.getIsVIP());
		row.createCell(7).setCellValue(memberImport.getIsWarning());
		row.createCell(8).setCellValue(memberImport.getIsBlacklist());
		row.createCell(9).setCellValue(memberImport.getSalary());
		row.createCell(10).setCellValue(memberImport.getSwipeCardNumber());
		row.createCell(11).setCellValue(memberImport.getCardTemplate());
		row.createCell(12).setCellValue(memberImport.getBirthdate());
		// if (memberImport.getBirthdate() != null) {
		// Cell cellBirthDate = row.createCell(12);
		// cellBirthDate.setCellStyle(cellStyle);
		// cellBirthDate.setCellValue(memberImport.getBirthdate());
		// }
		row.createCell(13).setCellValue(memberImport.getDepartment());
		row.createCell(14).setCellValue(memberImport.getJobFunction());
		if (memberImport.getJoinDate() != null) {
			Cell cellJoinDate = row.createCell(15);
			cellJoinDate.setCellStyle(cellStyle);
			cellJoinDate.setCellValue(memberImport.getJoinDate());
		}
		if (memberImport.getEffectiveDate() != null) {
			Cell cellEffectiveDate = row.createCell(16);
			cellEffectiveDate.setCellStyle(cellStyle);
			cellEffectiveDate.setCellValue(memberImport.getEffectiveDate());
		}
		row.createCell(17).setCellValue(memberImport.getSex());
		row.createCell(18).setCellValue(memberImport.getBankAccount());
		row.createCell(19).setCellValue(memberImport.getAccountNo());
		row.createCell(20).setCellValue(memberImport.getBankName());
		row.createCell(21).setCellValue(memberImport.getBankBranch());
		row.createCell(22).setCellValue(memberImport.getSwiftCode());
		if (memberImport.getExpireDate() != null) {
			Cell cellExpireDate = row.createCell(23);
			cellExpireDate.setCellStyle(cellStyle);
			cellExpireDate.setCellValue(memberImport.getExpireDate());
		}
		row.createCell(24).setCellValue(memberImport.getHandphone());
		row.createCell(25).setCellValue(memberImport.getEmail());
		row.createCell(26).setCellValue(memberImport.getProduct());
		row.createCell(27).setCellValue(memberImport.getIsProRate());
		row.createCell(28).setCellValue(memberImport.getBpjsNumber());
		row.createCell(29).setCellValue(getCellValueAsString(cellAddInfo));
		row.createCell(30).setCellValue(memberImport.getSuspendStart());
		row.createCell(31).setCellValue(memberImport.getSuspendEnd());
		row.createCell(32).setCellValue(memberImport.getNoClaimFlag());
		row.createCell(33).setCellValue(getCellValueAsString(cellPrintCard));
		row.createCell(34).setCellValue(getCellValueAsString(cellPolicyNumber));
		row.createCell(35).setCellValue(memberImport.getAnnualLimit());
		if (memberImport.getGraceStartDate() != null) {
			Cell cellGraceStartDate = row.createCell(36);
			cellGraceStartDate.setCellStyle(cellStyle);
			cellGraceStartDate.setCellValue(memberImport.getGraceStartDate());
		}
		if (memberImport.getGraceEndDate() != null) {
			Cell cellGraceEndDate = row.createCell(37);
			cellGraceEndDate.setCellStyle(cellStyle);
			cellGraceEndDate.setCellValue(memberImport.getGraceEndDate());
		}
		row.createCell(38).setCellValue(memberImport.getImportBatchNumber());
		row.createCell(39).setCellValue(memberImport.getSubPolicyNumber());
		row.createCell(40).setCellValue(memberImport.getLinkedCardNumber());
		row.createCell(41).setCellValue(memberImport.getClientCode());
		// todo fix
		row.createCell(42).setCellValue("");
		row.createCell(43).setCellValue("");
		row.createCell(44).setCellValue("");
		row.createCell(45).setCellValue(memberImport.getActionType());
		row.createCell(46).setCellValue(note);
	}

	private void prepareHeader(Row rowHeaderSuccess, Workbook workbook) {
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);

		rowHeaderSuccess.createCell(0).setCellValue("Full Name");
		rowHeaderSuccess.createCell(1).setCellValue("Member Id");
		rowHeaderSuccess.createCell(2).setCellValue("Current Policy Number");
		rowHeaderSuccess.createCell(3).setCellValue("Employee Number");
		rowHeaderSuccess.createCell(4).setCellValue("Employee Name");
		rowHeaderSuccess.createCell(5).setCellValue("Relationship");
		rowHeaderSuccess.createCell(6).setCellValue("VIP");
		rowHeaderSuccess.createCell(7).setCellValue("Warning");
		rowHeaderSuccess.createCell(8).setCellValue("Blacklist");
		rowHeaderSuccess.createCell(9).setCellValue("Salary");
		rowHeaderSuccess.createCell(10).setCellValue("Swipe Card No");
		rowHeaderSuccess.createCell(11).setCellValue("Card Template");
		rowHeaderSuccess.createCell(12).setCellValue("Birth Date");
		rowHeaderSuccess.createCell(13).setCellValue("Department");
		rowHeaderSuccess.createCell(14).setCellValue("Job Function");
		rowHeaderSuccess.createCell(15).setCellValue("Join Date");
		rowHeaderSuccess.createCell(16).setCellValue("Effective Date");
		rowHeaderSuccess.createCell(17).setCellValue("Sex");
		rowHeaderSuccess.createCell(18).setCellValue("BName");
		rowHeaderSuccess.createCell(19).setCellValue("Acc");
		rowHeaderSuccess.createCell(20).setCellValue("Bank");
		rowHeaderSuccess.createCell(21).setCellValue("BBranch");
		rowHeaderSuccess.createCell(22).setCellValue("Swift Code");
		rowHeaderSuccess.createCell(23).setCellValue("Expire Date");
		rowHeaderSuccess.createCell(24).setCellValue("Handphone");
		rowHeaderSuccess.createCell(25).setCellValue("Email");
		rowHeaderSuccess.createCell(26).setCellValue("Plan Id");
		rowHeaderSuccess.createCell(27).setCellValue("Grup Code");
		rowHeaderSuccess.createCell(28).setCellValue("Cob No");
		rowHeaderSuccess.createCell(29).setCellValue("Add Info");
		rowHeaderSuccess.createCell(30).setCellValue("Suspend Start Date");
		rowHeaderSuccess.createCell(31).setCellValue("Suspend End Date");
		rowHeaderSuccess.createCell(32).setCellValue("No Claim");
		rowHeaderSuccess.createCell(33).setCellValue("Print Card");
		rowHeaderSuccess.createCell(34).setCellValue("New Policy Number");
		rowHeaderSuccess.createCell(35).setCellValue("Annual Limit");
		rowHeaderSuccess.createCell(36).setCellValue("Grace Start");
		rowHeaderSuccess.createCell(37).setCellValue("Grace End");
		rowHeaderSuccess.createCell(38).setCellValue("Import Batch Number");
		rowHeaderSuccess.createCell(39).setCellValue("Sub Policy Number");
		rowHeaderSuccess.createCell(40).setCellValue("Linked Card Number");
		rowHeaderSuccess.createCell(41).setCellValue("Client Code");
		rowHeaderSuccess.createCell(42).setCellValue("Client Name");
		rowHeaderSuccess.createCell(43).setCellValue("Policy ExpDate");
		rowHeaderSuccess.createCell(44).setCellValue("Payment method");
		rowHeaderSuccess.createCell(45).setCellValue("Action Type");
		rowHeaderSuccess.createCell(46).setCellValue("Notes");
	}
}
