package com.ametis.cms.webservice;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.text.SimpleDateFormat;

import org.apache.commons.httpclient.util.DateParser;
import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;
import org.joda.time.Years;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Benefit;
import com.ametis.cms.datamodel.BenefitUsageType;
import com.ametis.cms.datamodel.CardType;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.DataImportStage;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.EdcTerminal;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyClausul;
import com.ametis.cms.datamodel.PolicyCoverageFund;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.datamodel.ProductTypePoliklinik;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderCategory;
import com.ametis.cms.datamodel.ProviderPoliklinik;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.ClientDto;
import com.ametis.cms.dto.MemberBenefitDto;
import com.ametis.cms.dto.MemberDto;
import com.ametis.cms.dto.MemberGroupDto;
import com.ametis.cms.dto.MemberProductDto;
import com.ametis.cms.dto.PolicyDto;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClientProviderService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.DataImportStageService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.EdcTerminalService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberClausulService;
import com.ametis.cms.service.MemberElectronicCardService;
import com.ametis.cms.service.MemberGroupProviderService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberImportService;
import com.ametis.cms.service.MemberLimitLayerService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberProviderService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyClausulService;
import com.ametis.cms.service.PolicyCoverageFundService;
import com.ametis.cms.service.PolicyProviderService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.PoliklinikService;
import com.ametis.cms.service.ProductTypePoliklinikService;
import com.ametis.cms.service.ProductTypeService;
import com.ametis.cms.service.ProviderPoliklinikService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ProviderTypeService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.StringUtil;
import com.ametis.cms.util.TimeUtils;


@WebService(name = "MemberWebService", 
		endpointInterface = "com.ametis.cms.webservice.IMemberWebService",
		serviceName="MemberWebService")
public class MemberWebServiceImpl  implements IMemberWebService {

	private MemberService memberService;
	private MemberBenefitService memberBenefitService;
	private MemberLimitLayerService memberLimitLayerService;
	private MemberClausulService memberClausulService;
	private CaseCategoryService caseCategoryService;
	private ProviderService providerService;
	private ClientProviderService clientProviderService;
	private MemberGroupProviderService memberGroupProviderService;
	private MemberProviderService memberProviderService;
	private PolicyProviderService policyProviderService;
	private EdcTerminalService edcTerminalService;
	
	
	private MemberElectronicCardService memberCardService;
	private DiagnosisService diagnosisService;
	private MemberImportService memberImportService;

	private MemberProductService memberProductService;
	private MemberGroupService memberGroupService;
	private ClientService clientService;
	private PolicyService policyService;
	
	private PolicyClausulService policyClausulService;
	private CaseService caseService;
	private ProductTypeService productTypeService;
	private ProductTypePoliklinikService productTypePoliklinikService;
	private PoliklinikService poliklinikService;
	private ProviderPoliklinikService providerPoliklinikService;
	private UserService userService;

	private PolicyCoverageFundService policyCoverageFundService;
	private DataImportStageService dataImportStageService;

	
	
	
	public DataImportStageService getDataImportStageService() {
		return dataImportStageService;
	}

	public void setDataImportStageService(
			DataImportStageService dataImportStageService) {
		this.dataImportStageService = dataImportStageService;
	}

	public MemberLimitLayerService getMemberLimitLayerService() {
		return memberLimitLayerService;
	}

	public void setMemberLimitLayerService(
			MemberLimitLayerService memberLimitLayerService) {
		this.memberLimitLayerService = memberLimitLayerService;
	}

	public PolicyCoverageFundService getPolicyCoverageFundService() {
		return policyCoverageFundService;
	}

	public void setPolicyCoverageFundService(
			PolicyCoverageFundService policyCoverageFundService) {
		this.policyCoverageFundService = policyCoverageFundService;
	}

	public EdcTerminalService getEdcTerminalService() {
		return edcTerminalService;
	}

	public void setEdcTerminalService(EdcTerminalService edcTerminalService) {
		this.edcTerminalService = edcTerminalService;
	}

	public MemberImportService getMemberImportService() {
		return memberImportService;
	}

	public void setMemberImportService(MemberImportService memberImportService) {
		this.memberImportService = memberImportService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public ProductTypePoliklinikService getProductTypePoliklinikService() {
		return productTypePoliklinikService;
	}

	public void setProductTypePoliklinikService(
			ProductTypePoliklinikService productTypePoliklinikService) {
		this.productTypePoliklinikService = productTypePoliklinikService;
	}

	public PoliklinikService getPoliklinikService() {
		return poliklinikService;
	}

	public void setPoliklinikService(PoliklinikService poliklinikService) {
		this.poliklinikService = poliklinikService;
	}

	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}

	public PolicyClausulService getPolicyClausulService() {
		return policyClausulService;
	}

	public void setPolicyClausulService(PolicyClausulService policyClausulService) {
		this.policyClausulService = policyClausulService;
	}

	public MemberElectronicCardService getMemberCardService() {
		return memberCardService;
	}

	public void setMemberCardService(MemberElectronicCardService memberCardService) {
		this.memberCardService = memberCardService;
	}

	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

	public PolicyProviderService getPolicyProviderService() {
		return policyProviderService;
	}

	public void setPolicyProviderService(PolicyProviderService policyProviderService) {
		this.policyProviderService = policyProviderService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public ClientProviderService getClientProviderService() {
		return clientProviderService;
	}

	public void setClientProviderService(ClientProviderService clientProviderService) {
		this.clientProviderService = clientProviderService;
	}

	public MemberGroupProviderService getMemberGroupProviderService() {
		return memberGroupProviderService;
	}

	public void setMemberGroupProviderService(
			MemberGroupProviderService memberGroupProviderService) {
		this.memberGroupProviderService = memberGroupProviderService;
	}

	public MemberProviderService getMemberProviderService() {
		return memberProviderService;
	}

	public void setMemberProviderService(MemberProviderService memberProviderService) {
		this.memberProviderService = memberProviderService;
	}

	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(
			MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}

	public void setMemberBenefitService(
			MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}

	public MemberClausulService getMemberClausulService() {
		return memberClausulService;
	}

	public void setMemberClausulService(
			MemberClausulService memberClausulService) {
		this.memberClausulService = memberClausulService;
	}

	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}

	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
	}

	
	public ProviderPoliklinikService getProviderPoliklinikService() {
		return providerPoliklinikService;
	}

	public void setProviderPoliklinikService(
			ProviderPoliklinikService providerPoliklinikService) {
		this.providerPoliklinikService = providerPoliklinikService;
	}

	public boolean activateMember(String memberId) {
		boolean result = false;

		try {
			Member member = memberService.get(Integer.valueOf(memberId));

			if (member != null && member.getStatus() == SubscriptionStatus.PENDING) {
			
				long start = System.currentTimeMillis();
				ActionUser actionUser = new ActionUser();
				User user = new User();
				user.setUsername("system-daemon");
				actionUser.setUser(user);

				memberService.activate(Integer.valueOf(memberId), actionUser);
				
				long end = System.currentTimeMillis();
				
				System.out.println("TOTAL ACTIVATE FOR member : " + memberId + " = " + (end-start)/1000 + " (s) ");

				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Collection<MemberDto> getGroupMember(String memberGroupId,
			String status) {
		Collection<MemberDto> result = null;

		try {

			String param[] = { "memberGroupId.memberGroupId", "deletedStatus","status.statusId" };
			Object[] value = { Integer.valueOf(memberGroupId),Integer.valueOf(0), Integer.valueOf(-1) };

			int total = memberService.getTotal(null, null, param, value);

			Collection<Member> members = memberService.search(null, null,
					param, value, 0, total);

			if (members != null) {
				result = new Vector();

				Iterator<Member> iterator = members.iterator();

				MemberDto dto = null;

				while (iterator.hasNext()) {

					dto = new MemberDto();

					Member member = iterator.next();

					if (member != null) {
						dto.setMemberGroupId(member.getMemberGroupId()
								.getMemberGroupId()
								+ "");
						dto.setFirstName(member.getFirstName());
						dto.setCustomerNumber(member.getCustomerNumber());
						dto.setMemberId(member.getMemberId());

						result.add(dto);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public boolean migrateMember(MemberDto member, String username) {
		boolean result = false;

		try {
			if (member != null) {
				Member currentMember = memberService.getMember(member
						.getCustomerPolicyNumber(), Integer.valueOf(member
						.getMemberGroupId()), Integer.valueOf(member
						.getClientId()));

				if (currentMember == null) {

					Member memberToInsert = new Member();
					memberToInsert.setBirthday(member.getBirthday());

					Client client = new Client();
					client.setClientId(Integer.valueOf(member.getClientId()));

					memberToInsert.setClientId(client);
					memberToInsert.setCurrentProductCode(member
							.getCurrentBenefitCode());

					memberToInsert.setGender(member.getGender());

					memberToInsert.setBankAccount(member.getBankAccount());
					memberToInsert.setBank(member.getBank());
					memberToInsert.setBankAccountName(member
							.getBankAccountName());
					memberToInsert.setBankBranch(member.getBankBranch());

					memberToInsert.setJoinDate(member.getJoinDate());
					System.out.println("JOIN DATE : " + member.getJoinDate());
					memberToInsert.setExpireDate(member.getExpireDate());
					System.out.println("Expire DATE : "
							+ member.getExpireDate());
					memberToInsert.setEffectiveDate(member.getEffectiveDate());
					System.out.println("Effective DATE : "
							+ member.getEffectiveDate());
					memberToInsert.setResignedDate(member.getExpireDate());
					memberToInsert
							.setCustomerNumber(member.getCustomerNumber());

					memberToInsert.setCustomerPolicyNumber(member
							.getCustomerPolicyNumber());
					memberToInsert.setFirstName(member.getFirstName());

					MemberGroup memberGroup = new MemberGroup();
					memberGroup.setMemberGroupId(Integer.valueOf(member
							.getMemberGroupId()));


					memberToInsert.setStatus(Integer.valueOf(member.getStatus()));
					memberToInsert.setMemberGroupId(memberGroup);

					if (member.getAnnualPremium() != null
							&& !member.getAnnualPremium().equalsIgnoreCase("")) {
						try {
							memberToInsert.setCurrentAnnualPremium(Double
									.valueOf(member.getAnnualPremium()));
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						memberToInsert.setCurrentAnnualPremium(Double
								.valueOf(-1));
					}

					ActionUser actionUser = new ActionUser();
					User user = new User();
					user.setUsername(username);

					Member hasil = memberService.create(memberToInsert,
							actionUser);

					if (hasil != null) {
						memberService.activate(hasil.getMemberId(), actionUser);

						result = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public MemberDto getMember(String memberNumber, String memberGroupId,
			String clientId) {
		// TODO Auto-generated method stub
		MemberDto result = null;
		try {

			Member member = memberService.getMember(memberNumber, Integer
					.valueOf(clientId), Integer.valueOf(memberGroupId));

			if (member != null) {

				result = new MemberDto();
				result.setBirthday(member.getBirthday());

				result.setClientName(member.getClientId().getClientName());
				result.setMemberGroupName(member.getMemberGroupId()
						.getGroupName());
				result.setEffectiveDate(member.getEffectiveDate());
				result.setExpireDate(member.getExpireDate());
				result.setStatus(member.getStatus().toString());
				result.setResignedDate(member.getResignedDate());
				result.setFirstName(member.getFirstName());
				result
						.setCustomerPolicyNumber(member
								.getCustomerPolicyNumber());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//Add by aju on 20150910, for getting the member product list fufufu
		@Override
		public Collection<MemberProductDto> getMemberProductByMemberId(
				Integer memberId) {

			Collection<MemberProductDto> result = new Vector<MemberProductDto>();
			try {
				System.out.println("MemberWebServiceImpl::getMemberProductByMemberId...");
				java.io.Serializable mid = memberId;
				
				Member member = memberService.get(mid);
				//CaseCategory cc = caseCategoryService.getCaseCategory(kodeService);

				if (member != null) {
					String[] eqParamMemberProduct = {"memberId.memberId","deletedStatus","memberProductStatus.statusId"};
					Object[] eqValueMemberProduct = {mid, 0, SubscriptionStatus.ACTIVE};
					String[] requiredMemberProduct = {
							// foreign affairs
							"MemberProduct.MemberProductStatus",
							"MemberProduct.MemberId", "MemberProduct.ProductId","MemberProduct.ProductId.CaseCategory","MemberProduct.MemberId.ClientId",
							"MemberProduct.MemberProductStatus",
							"MemberProduct.ProductId.ProductType","MemberProduct.ProductId.ProductType.ProductTypeId",
							"MemberProduct.ProductId.ProductLimitType","MemberProduct.ProductId.ProductLimitType.ProductLimitTypeId",
							"MemberProduct.ProductId.ProductCurrencyId","MemberProduct.ProductId.ProductCurrencyId.CurrencyId",
							"MemberProduct.FamilyProductId"
					// foreign affairs end
					};
					
					System.out.println("Getting memberProduct for memberId : " + mid);
					int totalMemberProduct = memberProductService.getTotal(null, null, eqParamMemberProduct, eqValueMemberProduct);
					System.out.println("memberProduct found for memberId(" + mid + ") : " + totalMemberProduct);
					
					Collection<MemberProduct> memberProductList = memberProductService.search(null, null, eqParamMemberProduct, eqValueMemberProduct, requiredMemberProduct, 0, totalMemberProduct);
					
					if (memberProductList != null){
						for (MemberProduct memberProduct : memberProductList) {
							MemberProductDto dto = memberProduct.exportDto();
							
							result.add(dto);
						}
					}
					
					/*
					System.out.println("MEMBER : " + member);
					System.out.println("CASE CATEGORY : "
							+ cc.getCaseCategoryName());

					String[] vEqParam = { "memberId.memberId",
							"caseCategoryId.caseCategoryId" };
					Object[] vEqValue = { member.getMemberId(),
							cc.getCaseCategoryId() };

					Collection<MemberBenefit> mbCol = memberBenefitService.search(
							null, null, vEqParam, vEqValue, 0, 100);

					if (mbCol != null) {
						System.out.println("CLOLLECTION : " + mbCol);
						Iterator<MemberBenefit> iterator = mbCol.iterator();

						if (iterator != null) {
							while (iterator.hasNext()) {
								MemberBenefit benefit = iterator.next();

								if (benefit != null) {
									MemberBenefitDto benefitDto = new MemberBenefitDto();
									benefitDto.setBenefitItemName(benefit
											.getItemCategoryId()
											.getItemCategoryName());
									benefitDto.setBenefitLimit(benefit
											.getBenefitLimit());
									benefitDto.setMaxPercaseBenefitLimit(benefit
											.getMaxBenefitPerCase());
									benefitDto.setMemberName(benefit.getMemberId()
											.getFirstName());

									result.add(benefitDto);

								}
							}
						}
					}
					*/
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
			
		}

	public Collection<MemberBenefitDto> getMemberBenefit(String memberNumber,
			String kodeService) {
		// TODO Auto-generated method stub

		Collection<MemberBenefitDto> result = new Vector<MemberBenefitDto>();
		try {
			Member member = memberService.getMember(memberNumber);
			CaseCategory cc = caseCategoryService.getCaseCategory(kodeService);

			if (member != null) {

				System.out.println("MEMBER : " + member);
				System.out.println("CASE CATEGORY : "
						+ cc.getCaseCategoryName());

				String[] vEqParam = { "memberId.memberId",
						"caseCategoryId.caseCategoryId" };
				Object[] vEqValue = { member.getMemberId(),
						cc.getCaseCategoryId() };

				//Add by aju on 20150916, requireeeeddddddd
				String[] requiredMemberBenefit = {
						// foreign affairs
						"MemberBenefit.ItemCategoryId",
						"MemberBenefit.MemberId", 
						"MemberBenefit.CaseCategoryId",
						"MemberBenefit.BenefitCalculationMethod"
				// foreign affairs end
				};
				
				//Collection<MemberBenefit> mbCol = memberBenefitService.search(null, null, vEqParam, vEqValue, 0, 100);
				Collection<MemberBenefit> mbCol = memberBenefitService.search(null, null, vEqParam, vEqValue, requiredMemberBenefit, 0, 100);
						
						//(null, null, vEqParam, vEqValue, 0, 100);

				if (mbCol != null) {
					System.out.println("CLOLLECTION : " + mbCol);
					Iterator<MemberBenefit> iterator = mbCol.iterator();

					if (iterator != null) {
						while (iterator.hasNext()) {
							MemberBenefit benefit = iterator.next();

							if (benefit != null) {
								//Modified by aju on 20150916, use the exportDto
								/*
								MemberBenefitDto benefitDto = new MemberBenefitDto();
								benefitDto.setBenefitItemName(benefit
										.getItemCategoryId()
										.getItemCategoryName());
								benefitDto.setBenefitLimit(benefit
										.getBenefitLimit());
								benefitDto.setMaxPercaseBenefitLimit(benefit
										.getMaxBenefitPerCase());
								benefitDto.setMemberName(benefit.getMemberId()
										.getFirstName());
								*/
								MemberBenefitDto benefitDto = benefit.exportDto();
								
								result.add(benefitDto);

							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public String terminateMember(String memberNumber, String resignDate,
			ActionUser actionUser) {
		// TODO Auto-generated method stub
		String result = "failed";
		try {
			Member member = memberService.getMember(memberNumber);

			if (member != null
					&& (member.getStatus().intValue() == SubscriptionStatus.ACTIVE || member
							.getStatus().intValue() == SubscriptionStatus.PENDING)) {
				ActionUser usr = new ActionUser();

				User user = new User();
				user.setUsername("system-daemon");
				usr.setUser(user);
				memberService.terminate(member.getMemberId(), Date
						.valueOf(resignDate), usr);

				result = "success";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String terminateMemberList(Collection<String> memberNumbers,
			Collection<String> resignDates, ActionUser actionUser) {
		// TODO Auto-generated method stub

		try {
			if (memberNumbers != null) {
				Iterator<String> memberNumberIterator = memberNumbers
						.iterator();
				Iterator<String> resignDateIterator = resignDates.iterator();

				String memberNumber = "";
				String resignDate = "";

				while (memberNumberIterator.hasNext()) {
					memberNumber = memberNumberIterator.next();
					resignDate = resignDateIterator.next();

					memberService.terminate(memberNumber, Date
							.valueOf(resignDate), actionUser);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ClientDto getClient(String clientId) {
		// TODO Auto-generated method stub

		ClientDto result = null;
		try {
			if (clientService == null){
				ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
				clientService = (ClientService) ctx.getBean("clientService");
				
				
				System.out.println("Client Service = " + clientService);
			}
			
			Client client = clientService.get(Integer.valueOf(clientId));

			if (client != null) {

				result = new ClientDto();

				result.setClientCode(client.getClientCode());
				result.setClientId(client.getClientId() + "");
				result.setClientName(client.getClientName());
				result
						.setEffectiveDate(client.getRegistrationDate() == null ? ""
								: client.getRegistrationDate().toString());
				result.setJoinDate(client.getRegistrationDate() == null ? ""
						: client.getRegistrationDate().toString());
				result.setExpireDate(client.getExpireDate() == null ? ""
						: client.getExpireDate().toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	public MemberGroupDto getMemberGroup(String memberGroupId) {
		// TODO Auto-generated method stub
		MemberGroupDto result = null;

		try {
			MemberGroup group = memberGroupService.get(Integer
					.valueOf(memberGroupId));

			if (group != null && group.getDeletedStatus().intValue() == 0) {
				result = new MemberGroupDto();
				if (group.getEffectiveDate() != null){
					result.setEffectiveDate(group.getEffectiveDate().toString());
				}
				if (group.getExpireDate() != null){
					result.setExpireDate(group.getExpireDate().toString());
				}
				if (group.getJoinDate() != null){
					result.setJoinDate(group.getJoinDate().toString());
				}
				result.setMemberGroupCode(group.getMemberGroupCode());
				result.setMemberGroupId(group.getMemberGroupId().toString());
				result.setMemberGroupName(group.getGroupName());
				if (group.getRenewalDate() != null){
					result.setRenewalDate(group.getRenewalDate().toString());
				}
				if (group.getStatus() != null){
					result.setStatus(group.getStatus().getStatusId().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Collection<MemberGroupDto> getMemberGroupList() {
		// TODO Auto-generated method stub
		Collection<MemberGroupDto> resultList = null;

		try {
			Collection<MemberGroup> groupList = memberGroupService.getAll();

			if (groupList != null ) {
				resultList = new Vector<MemberGroupDto>();
				Iterator<MemberGroup> iterator = groupList.iterator();

				while (iterator.hasNext()) {
					MemberGroupDto result = new MemberGroupDto();
					MemberGroup group = iterator.next();

					result.setEffectiveDate(group.getEffectiveDate() == null ? "" : group.getEffectiveDate().toString());
					result.setExpireDate(group.getExpireDate() == null ? "" : group.getExpireDate().toString());
					result.setJoinDate(group.getJoinDate() == null ? "" : group.getJoinDate().toString());
					result.setMemberGroupCode(group.getMemberGroupCode() == null ? "" : group.getMemberGroupCode());
					result.setMemberGroupId(group.getMemberGroupId() == null ? "" : group.getMemberGroupId().toString());
					result.setMemberGroupName(group.getGroupName() == null ? "" : group.getGroupName());
					result.setRenewalDate(group.getRenewalDate() == null ? "" : group.getRenewalDate().toString());

					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}

	public boolean updateMemberBankAccount(String memberNumber,
			String bankAccount, String bankName, String bankBranch,
			String bankAccountName, ActionUser actionUser) {
		// TODO Auto-generated method stub

		boolean result = false;
		try {
			Member member = memberService.getMember(memberNumber);
			if (member != null) {
				if (member.getBankAccount() == null
						|| member.getBankAccount().equalsIgnoreCase("")) {

					member.setBank(bankName);
					member.setBankAccount(bankAccount);
					member.setBankAccountName(bankAccountName);
					member.setBankBranch(bankBranch);

					memberService.update(member, actionUser);

					result = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Collection<MemberDto> getDependentList(String memberId) {
		// TODO Auto-generated method stub

		Collection<MemberDto> result = new Vector<MemberDto>();
		try {

			Collection<Member> tmpRes = memberService.getDependentList(Integer
					.valueOf(memberId));

			for (Member member : tmpRes) {
				MemberDto dto = new MemberDto();
				dto.setFirstName(member.getFirstName());
				dto.setCustomerNumber(member.getCustomerNumber());
				dto.setCustomerPolicyNumber(member.getCustomerPolicyNumber());
				dto.setMemberId(member.getMemberId());
				dto.setBirthday(member.getBirthday());
				dto.setCurrentBenefitCode(member.getCurrentProductCode());
				dto.setGender(member.getGender());
				dto.setEffectiveDate(member.getEffectiveDate());
				dto.setJoinDate(member.getJoinDate());
				dto.setExpireDate(member.getExpireDate());
				dto.setResignedDate(member.getResignedDate());

				result.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Collection<MemberDto> getParentList(String memberGroupId) {
		// TODO Auto-generated method stub
		Collection<MemberDto> result = new Vector<MemberDto>();
		try {

			Collection<Member> tmpRes = memberService.getParentList(Integer
					.valueOf(memberGroupId));

			for (Member member : tmpRes) {
				MemberDto dto = new MemberDto();
				dto.setFirstName(member.getFirstName());
				dto.setCustomerNumber(member.getCustomerNumber());
				dto.setCustomerPolicyNumber(member.getCustomerPolicyNumber());
				dto.setMemberId(member.getMemberId());
				dto.setBirthday(member.getBirthday());
				dto.setCurrentBenefitCode(member.getCurrentProductCode());
				dto.setGender(member.getGender());
				dto.setEffectiveDate(member.getEffectiveDate());
				dto.setJoinDate(member.getJoinDate());
				dto.setExpireDate(member.getExpireDate());
				dto.setResignedDate(member.getResignedDate());
				dto.setMemberGroupId(member.getMemberGroupId().getMemberGroupId().toString());

				result.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Collection<MemberDto> getParentListByAlphabet(String memberGroupId,
			String character) {
		// TODO Auto-generated method stub
		Collection<MemberDto> result = new Vector<MemberDto>();
		try {

			Collection<Member> tmpRes = memberService.getParentListByAlphabet(
					Integer.valueOf(memberGroupId), character);

			for (Member member : tmpRes) {
				MemberDto dto = new MemberDto();
				dto.setFirstName(member.getFirstName());
				dto.setCustomerNumber(member.getCustomerNumber());
				dto.setCustomerPolicyNumber(member.getCustomerPolicyNumber());
				dto.setMemberId(member.getMemberId());
				dto.setBirthday(member.getBirthday());
				dto.setCurrentBenefitCode(member.getCurrentProductCode());
				dto.setGender(member.getGender());
				dto.setEffectiveDate(member.getEffectiveDate());
				dto.setJoinDate(member.getJoinDate());
				dto.setExpireDate(member.getExpireDate());
				dto.setResignedDate(member.getResignedDate());

				result.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String isValidProvider (String merchantId, String cardNumber,String caseCategoryCode){
		String result = "FALSE";
		try {
			if (merchantId != null && cardNumber != null){
				Member member = memberService.getMemberByCardNumber(cardNumber);
				
				Provider provider = providerService.getByProviderCode(merchantId);
				Policy policy = null;
				System.out.println("CHECKING MERCHANT ID : " + merchantId + " CARD NUMBER : " + cardNumber);
				
				if (provider.getStatusId().getStatusId().intValue() == SubscriptionStatus.ACTIVE 
						&& provider.getContractEndDate().after(new java.sql.Date(System.currentTimeMillis()))){
					
					if (member != null){
						Client client = member.getClientId();
						MemberGroup group = member.getMemberGroupId();
						
						policy = policyService.getGroupActivePolicy(group.getMemberGroupId());
						
						System.out.println ("CLIENT ID : "+ client.getClientId() + " MEMBER GROUP  : " + group.getMemberGroupId() + " POLICY ID :  " + policy.getPolicyId() + " POLICY ALLOCATION : " + policy.getProviderAllocationType().intValue());
						
						if (policy != null){
							String serviceType = "";
							
							if (caseCategoryCode.equalsIgnoreCase(CaseCategory.INPATIENT_STR)){
								serviceType = "inpatient";
							}
							if (caseCategoryCode.equalsIgnoreCase(CaseCategory.OUTPATIENT_STR)){
								serviceType = "outpatient";
							}
							if (caseCategoryCode.equalsIgnoreCase(CaseCategory.DENTAL_STR)){
								serviceType = "dental";
							}
							if (caseCategoryCode.equalsIgnoreCase(CaseCategory.MATERNITY_STR)){
								serviceType = "maternity";
							}
							if (caseCategoryCode.equalsIgnoreCase(CaseCategory.OPTICAL_STR)){
								serviceType = "optical";
							}
							if (caseCategoryCode.equalsIgnoreCase(CaseCategory.MEDICAL_CHECK_UP_STR)){
								serviceType = "mcuLab";
							}
							
							if (policy.getProviderAllocationType() != null && policy.getProviderAllocationType().intValue() == Policy.PROVIDER_CLIENT_USAGE_TYPE){
								String[] eqParam = {"clientId.clientId","providerId.providerId","deletedStatus",serviceType};
								Object[] eqValue = {client.getClientId(),provider.getProviderId(),Integer.valueOf(0),1};
								
								int total = clientProviderService.getTotal(null,null,eqParam,eqValue);
								
								if (total > 0){
									result = "TRUE";
								}
							}
							else if (policy.getProviderAllocationType() != null && policy.getProviderAllocationType().intValue() == Policy.PROVIDER_POLICY_USAGE_TYPE){
								String[] eqParam = {"policyId.policyId","providerId.providerId","deletedStatus",serviceType};
								Object[] eqValue = {policy.getPolicyId(),provider.getProviderId(),Integer.valueOf(0),1};
								
								int total = policyProviderService.getTotal(null,null,eqParam,eqValue);
								
								if (total > 0){
									result = "TRUE";
								}
							}
							else if (policy.getProviderAllocationType() != null && policy.getProviderAllocationType().intValue() == Policy.PROVIDER_GROUP_USAGE_TYPE){
								String[] eqParam = {"memberGroupId.memberGroupId","providerId.providerId","deletedStatus",serviceType};
								Object[] eqValue = {member.getMemberGroupId().getMemberGroupId(),provider.getProviderId(),Integer.valueOf(0),1};
								
								int total = memberGroupProviderService.getTotal(null,null,eqParam,eqValue);
								
								if (total > 0){
									result = "TRUE";
								}
							}
							else if (policy.getProviderAllocationType() != null && policy.getProviderAllocationType().intValue() == Policy.PROVIDER_MEMBER_USAGE_TYPE){
								String[] eqParam = {"memberId.memberId","providerId.providerId","deletedStatus",serviceType};
								Object[] eqValue = {member.getMemberId(),provider.getProviderId(),Integer.valueOf(0),1};
								
								int total = memberProviderService.getTotal(null,null,eqParam,eqValue);
								
								if (total > 0){
									result = "TRUE";
								}
							}
							else if (policy.getProviderAllocationType() != null && policy.getProviderAllocationType().intValue() == Policy.ALL_PROVIDER_USAGE){
								result = "TRUE";
							}
						}						
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public String movementMember(String memberNumber, MemberDto memberDto) {
		// TODO Auto-generated method stub

		try {
			Member member = memberService.getMember(memberNumber);

			if (member != null && memberDto != null) {
				if (memberDto.getBirthday() != null) {
					member.setBirthday(memberDto.getBirthday());
				}
				if (memberDto.getGender() != null
						&& !memberDto.getGender().equalsIgnoreCase("")) {
					member.setGender(memberDto.getGender());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionResult authorizeMemberEDC(String cardNumber, String serviceType,
			String referenceNumber, String merchantId, String terminalId) {
		// TODO Auto-generated method stub

		ActionResult result = new ActionResult();
		result.setResult(false);

		try {
			// check clausul dulu.. jika tidak ada maka default true;

			Member member = memberService.getMemberByCardNumber(cardNumber);

			Provider provider = providerService.getByProviderCode(merchantId);
			CaseCategory caseCategory = caseCategoryService
					.getCaseCategoryEDC(serviceType);
			
			System.out.println("EDC Terminal Service : " + edcTerminalService);
			
			EdcTerminal edcTerminal = null;
			
			if (edcTerminalService != null){
				edcTerminal = edcTerminalService.getEdcTerminal(merchantId, terminalId);
			}
			
			
			if (member != null && member.getStatus().intValue() == SubscriptionStatus.ACTIVE) {

				int totalCase = 0;
				
				//add by aju on 20150830, check PRE AUTH (preAdmission status), PRE AUTH reject when swipe, PRE PENDING allowed to make new CASE_OPEN, PRE OPEN changed it into OPEN
				//PRE AUTH case
				String[] eqExistingPreAuthParam = {"providerId.providerId","deletedStatus","memberId.memberId"
						,"caseStatusId.caseStatusId","caseCategoryId.caseCategoryId"};
				
				Object[] eqExistingPreAuthValue = {provider.getProviderId(),Integer.valueOf(0),
						member.getMemberId(),CaseStatus.CASE_PRE_AUTH,caseCategory.getCaseCategoryId()};
				
				totalCase = caseService.getTotal(null,null,eqExistingPreAuthParam,eqExistingPreAuthValue);
				
				if (totalCase > 0){
					result.setAdditionalMessage("ALREADYREGISTEREDPRE");
					result.setResult(false);
					return result;
				}
				
				
				//OPEN case
				String[] eqExistingParam = {"providerId.providerId","deletedStatus","caseStartTime","memberId.memberId"
						,"caseStatusId.caseStatusId","caseCategoryId.caseCategoryId"};
				
				Object[] eqExistingValue = {provider.getProviderId(),Integer.valueOf(0),new java.sql.Date(System.currentTimeMillis()),
						member.getMemberId(),CaseStatus.CASE_OPEN,caseCategory.getCaseCategoryId()};
				
				totalCase = caseService.getTotal(null,null,eqExistingParam,eqExistingValue);
				
				if (totalCase > 0){
					result.setAdditionalMessage("ALREADYREGISTERED");
					result.setResult(false);
					return result;
				}
				
				String isValidProvider = isValidProvider(merchantId, cardNumber, caseCategory.getCaseCategoryCode());
				if (isValidProvider != null && !isValidProvider.equalsIgnoreCase("TRUE")){
					result.setAdditionalMessage("NOTPROVIDER");
					result.setResult(false);
					return result;
				}
					
				System.out.println("Membership status => ACTIVE");
				
				MemberProduct product = memberProductService.getMemberActiveProduct(member.getMemberId(), caseCategory.getCaseCategoryId());
				
				
				Collection<MemberBenefit> memberBenefitList = null;
				
				if (product != null){
					memberBenefitList =  memberBenefitService.getMemberBenefitList(member.getMemberId(),
						caseCategory.getCaseCategoryId(),product.getMemberProductId(),0);
				}
				else {
					memberBenefitList =  memberBenefitService.getMemberBenefitByDate(member.getMemberId(),
							caseCategory.getCaseCategoryId(), new java.sql.Date(System.currentTimeMillis()),new java.sql.Date(System.currentTimeMillis()));
				}
				
				boolean isManagedCare = false;
				boolean isIndemnity = false;
				boolean isRefered = false;
				
				boolean isUsingPolicyAsoFund = false;
				boolean isUsingPolicyCoverageFund = false;
				
				Policy policy = policyService.get(member.getCurrentPolicyId().getPolicyId());
				
				System.out.println("POLICY ==> " + policy);
				
				if (policy != null){
					if (policy.getPolicyType().intValue() == 1){
						isIndemnity = true;
					}
					else if (policy.getPolicyType().intValue() == 2){
						isManagedCare = true;
					}
					
					if (policy.getIsUsingFloatingFund() != null){
						if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_COVERAGE){
							isUsingPolicyCoverageFund = true;
						}
						if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_POLICY){
							isUsingPolicyAsoFund = true;
						}
					}
				}	
				
				if (caseCategory != null && caseCategory.getCaseCategoryId().intValue() == CaseCategory.MATERNITY){
					System.out.println("DIDALAM MATERNITY CHECK");
					String[] eqMaxChildParam = {"deletedStatus","iterationType","caseCategoryId.caseCategoryId"};
					Object[] eqMaxChildValue = {0,PolicyClausul.MA_MAX_CHILDREN,caseCategory.getCaseCategoryId()};
					
					Collection<PolicyClausul> maxChildCollection = policyClausulService.search(null,null,eqMaxChildParam,eqMaxChildValue,0,1);
					
					if (maxChildCollection != null && maxChildCollection.size() > 0){
						System.out.println("ADA CLAUSUL CHECK MATERNITY CHECK");
						Iterator<PolicyClausul> iterator= maxChildCollection.iterator();
						
						if (iterator.hasNext()){
							PolicyClausul maxClausul = iterator.next();
							
							if (maxClausul != null && maxClausul.getBenefitIteration() != null){
								
								int totalChildren = maxClausul.getBenefitIteration();
								
								
								
								Integer parentId = member.getParentMember().getMemberId();
								
								String[] eqChildren = {"deletedStatus","parentMember.memberId","relationship"};
								Object[] eqChildrenVal = {0,parentId,"CHILDREN"};
								
								int currentTotalChildren = memberService.getTotal(null,null,eqChildren,eqChildrenVal);
								
								System.out.println("DIDALAM MATERNITY CHECK MAX CHILDER = " +totalChildren + " CURRENT CHILDREN = " + currentTotalChildren);
								
								if (currentTotalChildren >= totalChildren){
									result.setAdditionalMessage("MAXCHILDREN");
									result.setResult(false);
									return result;
								}
								
							}
						}
					}
				}
								
				
				System.out.println("Product = " + product);

				String responseLine = "";
				int index = 0;
				
				if (product == null && memberBenefitList != null){
					// indicate divert benefit
					
					if (memberBenefitList.size() > 0){
						Iterator<MemberBenefit> iterator = memberBenefitList.iterator();
						
						if (iterator.hasNext()){
							MemberBenefit mb = iterator.next();
							
							if (mb != null){
								product = mb.getMemberProductId();
							}
						}
					}					
				}
				
				if (isUsingPolicyAsoFund){
					double currentFund = policy.getCurrentPolicyFund();
					double minFund = policy.getFundBlockingLimit();
					
					if (minFund >= currentFund){

						System.out.println("IS USING POLICY ASO FUND AND SUPPOSED TO BE BLOCKED");
						boolean isExcessCovered = false;
						Product theProduct = product.getProductId();
						
						if (theProduct != null && theProduct.getExcessPaymentType().intValue() == Product.EXCESS_PAID_LATER){
							isExcessCovered = true;
							System.out.println("ASO FUND EMPTY BUT ALLOWED TO REGISTER");
						}
						
						if (!isExcessCovered){
							result.setAdditionalMessage("NOFUND");
							result.setResult(false);
							return result;	
						}
					}
				}
				if (isUsingPolicyCoverageFund){
					String[] eqFundParam = {"policyId.policyId","caseCategoryId.caseCategoryId","deletedStatus"};
					Object[] eqFundValue = {policy.getPolicyId(),caseCategory.getCaseCategoryId(),0};
					
					Collection<PolicyCoverageFund> fundList = policyCoverageFundService.search(null,null,eqFundParam,eqFundValue,0,1);
					boolean isFundApproved = false;
					
					if(fundList != null){
						Iterator<PolicyCoverageFund> fundIterator = fundList.iterator();
						
						if (fundIterator.hasNext()){
							PolicyCoverageFund fund = fundIterator.next();
							
							if(fund != null){
								double currentFund = fund.getCurrentFundValue();
								double minFund = fund.getMinimumAsoValue();
								
								if (minFund < currentFund){
									isFundApproved = true;
								}
								else {
									if (product.getProductId().getExcessPaymentType().intValue() == Product.EXCESS_PAID_LATER){
										isFundApproved = true;
									}
								}
							}
						}
					}
					
					if (!isFundApproved){
						result.setAdditionalMessage("NOFUND");
						result.setResult(false);
						return result;	
					}
				}


				//System.out.println("MEMBER PRODUCT ==> " + product + " LIMIT ==> " + product.getActualBenefitLimit());
				
				if (product != null && product.getActualBenefitLimit() != null && product.getActualBenefitLimit().doubleValue() > 0){

					System.out.println("DIDALAM ACTUAL BENEFIT > 0 ");
					System.out.println("BENEFIT LIST ==> " + memberBenefitList + " SIZE = " + memberBenefitList.size());
					
					if (memberBenefitList != null) {
						Vector<String> sendItemList = new Vector<String>();
						
						for (Iterator iterator = memberBenefitList.iterator(); iterator
								.hasNext();) {
	

							MemberBenefit memberBenefit = (MemberBenefit) iterator
									.next();
	
							if (memberBenefit != null
									&& memberBenefit.getIsEDCEnabled() != null && memberBenefit.getIsEDCEnabled().booleanValue()) {
								String itemCategoryName = "";
								
								if (memberBenefit.getItemCategoryId().getItemCategoryEDCName() != null){
									itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryEDCName();
								}
								else {
									itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryName();
								}
								
								if (index == 0) {
									responseLine += itemCategoryName
											+ ","
											+ memberBenefit.getItemCategoryId()
											.getItemCategoryCode();
									
									if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ",AS CHARGE"; 
											}
											else {											
												String limitSend = StringUtil.convertEDCNumber(memberBenefit.getCurrentBenefitPosition().doubleValue(), 9);											
												responseLine += "," + limitSend;
											}
										}
									}
									else {
										if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;													
												}
											}
										}
										else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
											double currentBenefit = 0;
											double totalBenefit = -1;
											double benefitUsage = 0;
											
											if (memberBenefit.getBenefitLimit() != null){
												totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
											}
											if (memberBenefit.getCurrentBenefitPosition() != null){
												currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
											}
											if (memberBenefit.getBenefitUsage() != null){
												benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
											}
											
											if (totalBenefit == -1){
												responseLine += ",DIJAMIN";
											}
											else {
												currentBenefit = totalBenefit - benefitUsage;
												double percentage = currentBenefit/totalBenefit*100;
												
												
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
													if (percentage < 10){
														responseLine += ",LMT <10%";
													}
													else if (percentage >= 10 && percentage < 30){
														responseLine += ",LMT <30%";
													}
													else if (percentage >= 30 && percentage < 50){
														responseLine += ",LMT <50%";
													}
													else {
														responseLine += ",DIJAMIN";
													}		
												}
												else {
													responseLine += ",DIJAMIN";
												}
											}
										}
									}
								} else {
									responseLine += "|" + itemCategoryName
									+ ","
									+ memberBenefit.getItemCategoryId()
									.getItemCategoryCode();
									
									if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ",AS CHARGE"; 
											}
											else {											
												String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);											
												responseLine += "," + limitSend;
											}
										}
									}
									else {
										if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {												
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;
												}
											}
										}
										else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
											double currentBenefit = 0;
											double totalBenefit = -1;
											double benefitUsage = 0;
											
											if (memberBenefit.getBenefitLimit() != null){
												totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
											}
											if (memberBenefit.getCurrentBenefitPosition() != null){
												currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
											}					
											if (memberBenefit.getBenefitUsage() != null){
												benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
											}
											if (totalBenefit == -1){
												responseLine += ",DIJAMIN";
											}
											else {
												currentBenefit = totalBenefit - benefitUsage;
	
												double percentage = currentBenefit/totalBenefit*100;
												
													
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
													if (percentage < 10){
														responseLine += ",LMT <10%";
													}
													else if (percentage >= 10 && percentage < 30){
														responseLine += ",LMT <30%";
													}
													else if (percentage >= 30 && percentage < 50){
														responseLine += ",LMT <50%";
													}
													else {
														responseLine += ",DIJAMIN";
													}		
												}
												else {
													responseLine += ",DIJAMIN";
												}
											}
										}
									}
								}
							}
							index++;
						}
					}
	
					System.out.println("RESPONSE LINE 1 ==> " + responseLine);
					
					//modified by aju on 20150830, to support PRE AUTH
					//Case theCase = new Case();
					Case theCase = null;
					
					String[] eqExistingPreOpenParam = {"providerId.providerId","deletedStatus","memberId.memberId"
							,"caseStatusId.caseStatusId","caseCategoryId.caseCategoryId"};
					
					Object[] eqExistingPreOpenValue = {provider.getProviderId(),Integer.valueOf(0),
							member.getMemberId(),CaseStatus.CASE_PRE_OPEN,caseCategory.getCaseCategoryId()};
					
					totalCase = caseService.getTotal(null,null,eqExistingPreOpenParam,eqExistingPreOpenValue);
					
					if (totalCase > 0){
						System.out.println("authorizeMemberEDC::PRE OPEN->OPEN");
						
						Collection<Case> thePreOpenCaseList = caseService.getCaseList(eqExistingPreOpenParam, eqExistingPreOpenValue, null);
						
						if (thePreOpenCaseList != null){
							System.out.println("CASE PRE-OPEN LIST NOT NULL!!!");
							Iterator<Case> iterator = thePreOpenCaseList.iterator();
							
							if (iterator.hasNext()){
								theCase = iterator.next();
								System.out.println("GETTING PRE OPEN CASE => CASE NUM = " + theCase.getCaseNumber());
							}
						}
						else{
							System.out.println("CASE PRE-OPEN LIST NULL!!!");
						}
						
						
						
					}
					else{
						theCase = new Case();
					}
					
					theCase.setCaseCategoryId(caseCategory);
					theCase.setMemberId(member);
					theCase.setCaseStartTime(new java.sql.Date(System.currentTimeMillis()));
					
					if (product != null){
						theCase.setMemberProductId(product);
					}
					
					
					if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
						theCase.setCaseEndTime(new java.sql.Date(System.currentTimeMillis()));
					}
					else {
						DateTime dt = new DateTime();
						DateTime endTime = dt.plusDays(4);
						
						theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
					}
					
					if (provider != null){
						theCase.setProviderId(provider);
					}
					
				
					ActionUser actionUser = new ActionUser();
					User user = new User();
					user.setUsername(merchantId);
					actionUser.setUser(user);
					CaseStatus status = new CaseStatus();
					status.setCaseStatusId(CaseStatus.CASE_OPEN);
					theCase.setCaseType(Case.CASE_EDC);
					theCase.setCaseStatusId(status);
					

					if (edcTerminal != null){
						theCase.setTerminalId(edcTerminal);
						
						edcTerminal.setLastTransaction(new java.sql.Date(System.currentTimeMillis()));
						
						//modified by aju on 20150828, sapa yg bikin create zzzz change into UPDATE instead....
						//edcTerminalService.create(edcTerminal, actionUser);
						edcTerminalService.update(edcTerminal, actionUser);
					}
					

					//Add by aju on 20150805, for COB
					if(member.getLinkedCardNumber() != null && !member.getLinkedCardNumber().trim().equalsIgnoreCase("")){
						theCase.setIsLinkedMember(1);
					}
					else{
						theCase.setIsLinkedMember(0);
					}
					

					System.out.println("BEFORE CREATE CASE 1 ");
					//modified by aju on 20150830, to support PRE AUTH
					//Case newCase = caseService.create(theCase, actionUser);
					Case newCase = null;
					
					if(totalCase>0){
						newCase = caseService.update(theCase, actionUser);
					}
					else{
						newCase = caseService.create(theCase, actionUser);
					}
					
					
	
					String caseNum = "";
					
					if (newCase != null){
						caseNum = newCase.getCaseNumber();
					}
	
					responseLine = caseNum+"#"+responseLine;
					
					System.out.println("AFTER CREATE CASE 1");
					result.setAdditionalMessage(responseLine);
					result.setResult(true);
				}
				else if (product != null && product.getActualBenefitLimit() != null && product.getActualBenefitLimit().doubleValue() == -1.0){

					System.out.println("DIDALAM ACTUAL BENEFIT ---> -1 ");
					System.out.println("BENEFIT LIST ==> " + memberBenefitList + " SIZE = " + memberBenefitList.size());
					
					if (memberBenefitList != null) {
						Vector<String> sendItemList = new Vector<String>();
						
						for (Iterator iterator = memberBenefitList.iterator(); iterator
								.hasNext();) {
	
							//System.out.println("ITERATOR ==> " + index);
							MemberBenefit memberBenefit = (MemberBenefit) iterator
									.next();
	
							if (memberBenefit != null
									&& memberBenefit.getIsEDCEnabled() != null && memberBenefit.getIsEDCEnabled().booleanValue()) {
								String itemCategoryName = "";
								
								if (memberBenefit.getItemCategoryId().getItemCategoryEDCName() != null){
									itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryEDCName();
								}
								else {
									itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryName();
								}
								
								if (index == 0) {
									responseLine += itemCategoryName
											+ ","
											+ memberBenefit.getItemCategoryId()
											.getItemCategoryCode();
									
									if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ",AS CHARGE"; 
											}
											else {											
												String limitSend = StringUtil.convertEDCNumber(memberBenefit.getCurrentBenefitPosition().doubleValue(), 9);											
												responseLine += "," + limitSend;
											}
										}
									}
									else {
										if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {												
													
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;
													
												}
											}
										}
										else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
											double currentBenefit = 0;
											double totalBenefit = -1;
											double benefitUsage = 0;
											
											if (memberBenefit.getBenefitLimit() != null){
												totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
											}
											if (memberBenefit.getCurrentBenefitPosition() != null){
												currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
											}
											if (memberBenefit.getBenefitUsage() != null){
												benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
											}
											
											if (totalBenefit == -1){
												responseLine += ",DIJAMIN";
											}
											else {
												currentBenefit = totalBenefit - benefitUsage;
												double percentage = currentBenefit/totalBenefit*100;
												
												
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
													if (percentage < 10){
														responseLine += ",LMT <10%";
													}
													else if (percentage >= 10 && percentage < 30){
														responseLine += ",LMT <30%";
													}
													else if (percentage >= 30 && percentage < 50){
														responseLine += ",LMT <50%";
													}
													else {
														responseLine += ",DIJAMIN";
													}		
												}
												else {
													responseLine += ",DIJAMIN";
												}
											}
										}
									}
								} else {
									responseLine += "|" + itemCategoryName
									+ ","
									+ memberBenefit.getItemCategoryId()
									.getItemCategoryCode();
									
									if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ",AS CHARGE"; 
											}
											else {											
												String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);											
												responseLine += "," + limitSend;
											}
										}
									}
									else {
										if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {												
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;
												}
											}
										}
										else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
											double currentBenefit = 0;
											double totalBenefit = -1;
											double benefitUsage = 0;
											
											if (memberBenefit.getBenefitLimit() != null){
												totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
											}
											if (memberBenefit.getCurrentBenefitPosition() != null){
												currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
											}					
											if (memberBenefit.getBenefitUsage() != null){
												benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
											}
											if (totalBenefit == -1){
												responseLine += ",DIJAMIN";
											}
											else {
												currentBenefit = totalBenefit - benefitUsage;
	
												double percentage = currentBenefit/totalBenefit*100;
												
													
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
													if (percentage < 10){
														responseLine += ",LMT <10%";
													}
													else if (percentage >= 10 && percentage < 30){
														responseLine += ",LMT <30%";
													}
													else if (percentage >= 30 && percentage < 50){
														responseLine += ",LMT <50%";
													}
													else {
														responseLine += ",DIJAMIN";
													}		
												}
												else {
													responseLine += ",DIJAMIN";
												}
											}
										}
									}
								}
							}
							index++;
						}
					}
	
					System.out.println("RESPONSE LINE 1 ==> " + responseLine);
					
					//modified by aju on 20151015, to support PRE AUTH
					//Case theCase = new Case();
					Case theCase = null;
					
					String[] eqExistingPreOpenParam = {"providerId.providerId","deletedStatus","memberId.memberId"
							,"caseStatusId.caseStatusId","caseCategoryId.caseCategoryId"};
					
					Object[] eqExistingPreOpenValue = {provider.getProviderId(),Integer.valueOf(0),
							member.getMemberId(),CaseStatus.CASE_PRE_OPEN,caseCategory.getCaseCategoryId()};
					
					totalCase = caseService.getTotal(null,null,eqExistingPreOpenParam,eqExistingPreOpenValue);
					
					if (totalCase > 0){
						System.out.println("authorizeMemberEDC::PRE OPEN->OPEN");
						
						Collection<Case> thePreOpenCaseList = caseService.getCaseList(eqExistingPreOpenParam, eqExistingPreOpenValue, null);
						
						if (thePreOpenCaseList != null){
							System.out.println("CASE PRE-OPEN LIST NOT NULL!!!");
							Iterator<Case> iterator = thePreOpenCaseList.iterator();
							
							if (iterator.hasNext()){
								theCase = iterator.next();
								System.out.println("GETTING PRE OPEN CASE => CASE NUM = " + theCase.getCaseNumber());
							}
						}
						else{
							System.out.println("CASE PRE-OPEN LIST NULL!!!");
						}
						
					}
					else{
						theCase = new Case();
					}
					
					theCase.setCaseCategoryId(caseCategory);
					theCase.setMemberId(member);
					theCase.setCaseStartTime(new java.sql.Date(System.currentTimeMillis()));
					
					if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
						theCase.setCaseEndTime(new java.sql.Date(System.currentTimeMillis()));
					}
					else {
						DateTime dt = new DateTime();
						DateTime endTime = dt.plusDays(4);
						
						theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
					}
					
					if (provider != null){
						theCase.setProviderId(provider);
					}
					
					if (product != null){
						theCase.setMemberProductId(product);
					}
					
				
					ActionUser actionUser = new ActionUser();
					User user = new User();
					user.setUsername(merchantId);
					actionUser.setUser(user);
					CaseStatus status = new CaseStatus();
					status.setCaseStatusId(CaseStatus.CASE_OPEN);
					theCase.setCaseType(Case.CASE_EDC);
					theCase.setCaseStatusId(status);
					
					if (edcTerminal != null){
						theCase.setTerminalId(edcTerminal);
						
						edcTerminal.setLastTransaction(new java.sql.Date(System.currentTimeMillis()));
						
						//Add by aju on 20151015, di as charge belom ada zzzz
						edcTerminalService.update(edcTerminal, actionUser);
						
					}
					
					//Add by aju on 20151015, for COB
					if(member.getLinkedCardNumber() != null && !member.getLinkedCardNumber().trim().equalsIgnoreCase("")){
						theCase.setIsLinkedMember(1);
					}
					else{
						theCase.setIsLinkedMember(0);
					}
					
					System.out.println("BEFORE CREATE CASE 1 ");
					//modified by aju on 20151015, to support PRE AUTH
					//Case newCase = caseService.create(theCase, actionUser);
					Case newCase = null;
					
					if(totalCase>0){
						newCase = caseService.update(theCase, actionUser);
					}
					else{
						newCase = caseService.create(theCase, actionUser);
					}
	
					String caseNum = "";
					
					if (newCase != null){
						caseNum = newCase.getCaseNumber();
					}
	
					responseLine = caseNum+"#"+responseLine;
					
					System.out.println("AFTER CREATE CASE 1");
					result.setAdditionalMessage(responseLine);
					result.setResult(true);
				}
				else {
					if (product != null && product.getActualBenefitLimit() != null && product.getActualBenefitLimit().doubleValue() == 0){
						System.out.println("BENEFIT HABIS!!");
						
						if (product.getProductId().getExcessPaymentType() != null && product.getProductId().getExcessPaymentType().intValue() == Product.EXCESS_PAID_LATER){

							if (memberBenefitList != null) {
								Vector<String> sendItemList = new Vector<String>();
								
								for (Iterator iterator = memberBenefitList.iterator(); iterator
										.hasNext();) {
			
									MemberBenefit memberBenefit = (MemberBenefit) iterator
											.next();
			
									if (memberBenefit != null
											&& memberBenefit.getIsEDCEnabled() != null && memberBenefit.getIsEDCEnabled().booleanValue()) {
										String itemCategoryName = "";
										
										if (memberBenefit.getItemCategoryId().getItemCategoryEDCName() != null){
											itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryEDCName();
										}
										else {
											itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryName();
										}
										if (index == 0) {
											responseLine += itemCategoryName
													+ ","
													+ memberBenefit.getItemCategoryId()
													.getItemCategoryCode();
											
											if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
												if (memberBenefit.getBenefitLimit() != null){
													if (memberBenefit.getBenefitLimit() == -1){
														responseLine += ",AS CHARGE"; 
													}
													else {											
														String limitSend = StringUtil.convertEDCNumber(memberBenefit.getCurrentBenefitPosition().doubleValue(), 9);											
														responseLine += "," + limitSend;
													}
												}
											}
											else {
												if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
													if (memberBenefit.getBenefitLimit() != null){
														if (memberBenefit.getBenefitLimit() == -1){
															responseLine += ",AS CHARGE"; 
														}
														else {												
															
															String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
															responseLine += "," + limitSend;
															
														}
													}
												}
												else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
													double currentBenefit = 0;
													double totalBenefit = -1;
													double benefitUsage = 0;
													
													if (memberBenefit.getBenefitLimit() != null){
														totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
													}
													if (memberBenefit.getCurrentBenefitPosition() != null){
														currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
													}
													if (memberBenefit.getBenefitUsage() != null){
														benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
													}
													
													if (totalBenefit == -1){
														responseLine += ",DIJAMIN";
													}
													else {
														currentBenefit = totalBenefit - benefitUsage;
														double percentage = currentBenefit/totalBenefit*100;
														
														
														if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
															if (percentage < 10){
																responseLine += ",LMT <10%";
															}
															else if (percentage >= 10 && percentage < 30){
																responseLine += ",LMT <30%";
															}
															else if (percentage >= 30 && percentage < 50){
																responseLine += ",LMT <50%";
															}
															else {
																responseLine += ",DIJAMIN";
															}		
														}
														else {
															responseLine += ",DIJAMIN";
														}
													}
												}
											}
										} else {
											responseLine += "|" + itemCategoryName
											+ ","
											+ memberBenefit.getItemCategoryId()
											.getItemCategoryCode();
											
											if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
												if (memberBenefit.getBenefitLimit() != null){
													if (memberBenefit.getBenefitLimit() == -1){
														responseLine += ",AS CHARGE"; 
													}
													else {											
														String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);											
														responseLine += "," + limitSend;
													}
												}
											}
											else {
												if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
													if (memberBenefit.getBenefitLimit() != null){
														if (memberBenefit.getBenefitLimit() == -1){
															responseLine += ",AS CHARGE"; 
														}
														else {												
															String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
															responseLine += "," + limitSend;
														}
													}
												}
												else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
													double currentBenefit = 0;
													double totalBenefit = -1;
													double benefitUsage = 0;
													
													if (memberBenefit.getBenefitLimit() != null){
														totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
													}
													if (memberBenefit.getCurrentBenefitPosition() != null){
														currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
													}					
													if (memberBenefit.getBenefitUsage() != null){
														benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
													}
													if (totalBenefit == -1){
														responseLine += ",DIJAMIN";
													}
													else {
														currentBenefit = totalBenefit - benefitUsage;
			
														double percentage = currentBenefit/totalBenefit*100;
														
															
														if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
															if (percentage < 10){
																responseLine += ",LMT <10%";
															}
															else if (percentage >= 10 && percentage < 30){
																responseLine += ",LMT <30%";
															}
															else if (percentage >= 30 && percentage < 50){
																responseLine += ",LMT <50%";
															}
															else {
																responseLine += ",DIJAMIN";
															}		
														}
														else {
															responseLine += ",DIJAMIN";
														}
													}
												}
											}
										}
									}
									index++;
								}
							}
			
							System.out.println("AFTER RESPONSE --> " + responseLine);
							Case theCase = new Case();
							theCase.setCaseCategoryId(caseCategory);
							theCase.setMemberId(member);
							theCase.setCaseStartTime(new java.sql.Date(System.currentTimeMillis()));
					
							if (edcTerminal != null){
								theCase.setTerminalId(edcTerminal);
							}
							
							
							if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
								theCase.setCaseEndTime(new java.sql.Date(System.currentTimeMillis()));
							}
							else {
								DateTime dt = new DateTime();
								DateTime endTime = dt.plusDays(4);
								
								theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
							}
							
							if (provider != null){
								theCase.setProviderId(provider);
							}
							
						
							ActionUser actionUser = new ActionUser();
							User user = new User();
							user.setUsername(merchantId);
							actionUser.setUser(user);
							CaseStatus status = new CaseStatus();
							status.setCaseStatusId(CaseStatus.CASE_OPEN);
							theCase.setCaseType(Case.CASE_EDC);
							theCase.setCaseStatusId(status);
							
							System.out.println("BEFORE CREATE CASE 2 ");
							Case newCase = caseService.create(theCase, actionUser);
							String caseNum = "";
							
							if (newCase != null){
								caseNum = newCase.getCaseNumber();
							}
			
							responseLine = caseNum+"#"+responseLine;
							result.setAdditionalMessage(responseLine);
							result.setResult(true);
						}
						else if (product.getProductId().getExcessPaymentType() != null && product.getProductId().getExcessPaymentType().intValue() == Product.EXCESS_PAID_UPFRONT){
							result.setAdditionalMessage("EMPTYBENEFIT");
							result.setResult(false);
						}
						else {
							result.setAdditionalMessage("EMPTYBENEFIT");
							result.setResult(false);
						}
					}
					else {
						System.out.println("TERNYATA TIDAK DICOVER --> EXCESS");
						result.setAdditionalMessage("EMPTYBENEFIT");
						result.setResult(false);
					}
				}
			}
			else {
				System.out.println("ELSE --> FALSE");
				result.setResult(false);
				
				int status = member.getStatus().intValue();
				
				System.out.println("STATUS ===> " + status); 
				if (status == SubscriptionStatus.RESIGNED || status == -2){
					result.setAdditionalMessage("RESIGNED");
				}
				else if (status == SubscriptionStatus.TERMINATED ){
					result.setAdditionalMessage("TERMINATED");
				}
				else if (status == SubscriptionStatus.BLOCKED || status == SubscriptionStatus.INACTIVE){
					result.setAdditionalMessage("BLOCKED");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ActionResult authorizeMemberEDCManagedCare(String cardNumber, String serviceType,
			String referenceNumber, String merchantId, String terminalId) {
		// TODO Auto-generated method stub

		ActionResult result = new ActionResult();
		result.setResult(false);

		try {
			// check clausul dulu.. jika tidak ada maka default true;

			Member member = memberService.getMemberByCardNumber(cardNumber);

			Provider provider = providerService.getByProviderCode(merchantId);
			
			CaseCategory caseCategory = caseCategoryService
					.getCaseCategoryEDC(serviceType);
			
			System.out.println("Service TYPE = " + serviceType);
			Poliklinik poliklinik = poliklinikService.getPoliByEdcCode(serviceType);
			
			Case referedCase = null;
			
			
			if (member != null && member.getStatus().intValue() == SubscriptionStatus.ACTIVE) {

				
				int totalCase = 0;
				Integer ccId = null;
				
				if (caseCategory != null){
					ccId = caseCategory.getCaseCategoryId();
				}
				else {
					ccId = poliklinik.getCaseCategoryId().getCaseCategoryId();
				}
				
				//add by aju on 20150830, check PRE AUTH (preAdmission status), PRE AUTH reject when swipe, PRE PENDING allowed to make new CASE_OPEN, PRE OPEN changed it into OPEN
				//PRE AUTH case
				String[] eqExistingPreAuthParam = {"providerId.providerId","deletedStatus","memberId.memberId"
						,"caseStatusId.caseStatusId","caseCategoryId.caseCategoryId"};
				
				Object[] eqExistingPreAuthValue = {provider.getProviderId(),Integer.valueOf(0),
						member.getMemberId(),CaseStatus.CASE_PRE_AUTH,ccId};
				
				totalCase = caseService.getTotal(null,null,eqExistingPreAuthParam,eqExistingPreAuthValue);
				
				if (totalCase > 0){
					result.setAdditionalMessage("ALREADYREGISTEREDPRE");
					result.setResult(false);
					return result;
				}
				
				//OPEN case
				String[] eqExistingParam = {"providerId.providerId","deletedStatus","caseStartTime","memberId.memberId"
						,"caseStatusId.caseStatusId","caseCategoryId.caseCategoryId"};
				
				Object[] eqExistingValue = {provider.getProviderId(),Integer.valueOf(0),new java.sql.Date(System.currentTimeMillis()),
						member.getMemberId(),CaseStatus.CASE_OPEN,ccId};
				
				totalCase = caseService.getTotal(null,null,eqExistingParam,eqExistingValue);
				
				if (totalCase > 0){
					result.setAdditionalMessage("ALREADYREGISTERED");
					result.setResult(false);
					return result;
				}
				
				System.out.println("Membership status => ACTIVE");
				// check apakah menggunakan RUJUKAN atau tidak
				
				if (caseCategory == null){
					if (poliklinik != null){
						caseCategory = poliklinik.getCaseCategoryId();
					}
				}
				
				Collection<MemberBenefit> memberBenefitList = memberBenefitService.getMemberBenefitList(member.getMemberId(),
						caseCategory.getCaseCategoryId());
				
				if (poliklinik != null && referenceNumber != null && member != null){
					String[] eqRefCaseParam = {"deletedStatus","memberId.memberId","caseReferalNumber",
							"referedPoliklinikId.poliklinikId","referalStatus"};
					System.out.println("GETTING REFERED CASE = " + referenceNumber + " poliklinik = " + poliklinik.getPoliklinikId());
					 
					Object[] eqValueRefCase = {Integer.valueOf(0),member.getMemberId(),referenceNumber,poliklinik.getPoliklinikId(),Integer.valueOf(1)};
					
					Collection<Case> referedCaseList = caseService.search(null,null,eqRefCaseParam,eqValueRefCase,0,1);
					
					if (referedCaseList != null){
						Iterator<Case> iterator = referedCaseList.iterator();
						
						if (iterator.hasNext()){
							referedCase = iterator.next();
							System.out.println("GETTING REFERED CASE = " + referenceNumber + " poliklinik = " + poliklinik.getPoliklinikId() +" CASE NUM = " + referedCase.getCaseNumber());
						}
					}
				}
				
				boolean isRefered = false;			
				
				
				if (provider != null){
					if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.PPK2){
						if (referedCase == null){
							
							if (poliklinik != null && poliklinik.getNotUsingReference() != null){
								if (poliklinik.getNotUsingReference().intValue() == 0){
									result.setResult(false);
									result.setAdditionalMessage("NOTREFERED");
									return result;	
								}
							}
							else {
								result.setResult(false);
								result.setAdditionalMessage("NOTREFERED");
								return result;
							}
						}
					}
					else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.RUMAH_SAKIT){
						// PPK3 dan atau PPK2
						if (referedCase == null){
							if (poliklinik != null && poliklinik.getNotUsingReference() != null){
								if (poliklinik.getNotUsingReference().intValue() == 0){
									result.setResult(false);
									result.setAdditionalMessage("NOTREFERED");
									return result;	
								}
							}
							else {
								result.setResult(false);
								result.setAdditionalMessage("NOTREFERED");
								return result;
							}							
						}						
					}					
					else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.LAB){
						
					}
				}
				
				
				MemberProduct product = memberProductService.getMemberActiveProduct(member.getMemberId(), caseCategory.getCaseCategoryId());

				String responseLine = "";
				int index = 0;
				
				if (product != null){

					System.out.println("MEMBER PRODUCT ==> " + product + " LIMIT ==> " + product.getActualBenefitLimit());
				
				
					System.out.println("DIDALAM ACTUAL BENEFIT > 0 ");
					System.out.println("BENEFIT LIST ==> " + memberBenefitList + " SIZE = " + memberBenefitList.size());
					
					if (memberBenefitList != null) {
						Vector<String> sendItemList = new Vector<String>();
						
						for (Iterator iterator = memberBenefitList.iterator(); iterator
								.hasNext();) {
	
							System.out.println("ITERATOR ==> " + index);
							MemberBenefit memberBenefit = (MemberBenefit) iterator
									.next();
	
							if (memberBenefit != null
									&& memberBenefit.getIsEDCEnabled() != null && memberBenefit.getIsEDCEnabled().booleanValue()) {
								String itemCategoryName = "";
								
								if (memberBenefit.getItemCategoryId().getItemCategoryEDCName() != null){
									itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryEDCName();
								}
								else {
									itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryName();
								}
								
								if (index == 0) {
									responseLine += itemCategoryName
											+ ","
											+ memberBenefit.getItemCategoryId()
											.getItemCategoryCode();
									
									if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ",AS CHARGE"; 
											}
											else {											
												String limitSend = StringUtil.convertEDCNumber(memberBenefit.getCurrentBenefitPosition().doubleValue(), 9);											
												responseLine += "," + limitSend;
											}
										}
									}
									else {
										if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {												
													
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;
													
												}
											}
										}
										else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
											double currentBenefit = 0;
											double totalBenefit = -1;
											double benefitUsage = 0;
											
											if (memberBenefit.getBenefitLimit() != null){
												totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
											}
											if (memberBenefit.getCurrentBenefitPosition() != null){
												currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
											}
											if (memberBenefit.getBenefitUsage() != null){
												benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
											}
											
											if (totalBenefit == -1){
												responseLine += ",DIJAMIN";
											}
											else {
												currentBenefit = totalBenefit - benefitUsage;
												double percentage = currentBenefit/totalBenefit*100;
												
												
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
													if (percentage < 10){
														responseLine += ",LMT <10%";
													}
													else if (percentage >= 10 && percentage < 30){
														responseLine += ",LMT <30%";
													}
													else if (percentage >= 30 && percentage < 50){
														responseLine += ",LMT <50%";
													}
													else {
														responseLine += ",DIJAMIN";
													}		
												}
												else {
													responseLine += ",DIJAMIN";
												}
											}
										}
									}
								} else {
									responseLine += "|" + itemCategoryName
									+ ","
									+ memberBenefit.getItemCategoryId()
									.getItemCategoryCode();
									
									if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ",AS CHARGE"; 
											}
											else {											
												String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);											
												responseLine += "," + limitSend;
											}
										}
									}
									else {
										if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {												
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;
												}
											}
										}
										else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
											double currentBenefit = 0;
											double totalBenefit = -1;
											double benefitUsage = 0;
											
											if (memberBenefit.getBenefitLimit() != null){
												totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
											}
											if (memberBenefit.getCurrentBenefitPosition() != null){
												currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
											}					
											if (memberBenefit.getBenefitUsage() != null){
												benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
											}
											if (totalBenefit == -1){
												responseLine += ",DIJAMIN";
											}
											else {
												currentBenefit = totalBenefit - benefitUsage;
	
												double percentage = currentBenefit/totalBenefit*100;
												
													
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
													if (percentage < 10){
														responseLine += ",LMT <10%";
													}
													else if (percentage >= 10 && percentage < 30){
														responseLine += ",LMT <30%";
													}
													else if (percentage >= 30 && percentage < 50){
														responseLine += ",LMT <50%";
													}
													else {
														responseLine += ",DIJAMIN";
													}		
												}
												else {
													responseLine += ",DIJAMIN";
												}
											}
										}
									}
								}
							}
							index++;
						}
				
	
						System.out.println("RESPONSE LINE 1 ==> " + responseLine);
						//modified by aju on 20150830, to support PRE AUTH
						//Case theCase = new Case();
						Case theCase = null;
						
						String[] eqExistingPreOpenParam = {"providerId.providerId","deletedStatus","memberId.memberId"
								,"caseStatusId.caseStatusId","caseCategoryId.caseCategoryId"};
						
						Object[] eqExistingPreOpenValue = {provider.getProviderId(),Integer.valueOf(0),
								member.getMemberId(),CaseStatus.CASE_PRE_OPEN,caseCategory.getCaseCategoryId()};
						
						totalCase = caseService.getTotal(null,null,eqExistingPreOpenParam,eqExistingPreOpenValue);
						
						if (totalCase > 0){
							System.out.println("authorizeMemberEDCManagedCare::PRE OPEN->OPEN");
							Collection<Case> thePreOpenCaseList = caseService.getCaseList(eqExistingPreOpenParam, eqExistingPreOpenValue, null);
							
							if (thePreOpenCaseList != null){
								System.out.println("CASE PRE-OPEN LIST NOT NULL!!!");
								Iterator<Case> iterator = thePreOpenCaseList.iterator();
								
								if (iterator.hasNext()){
									theCase = iterator.next();
									System.out.println("GETTING PRE OPEN CASE => CASE NUM = " + theCase.getCaseNumber());
								}
							}
							else{
								System.out.println("CASE PRE-OPEN LIST NULL!!!");
							}
							
							
							
						}
						else{
							theCase = new Case();
						}
						
						theCase.setCaseCategoryId(caseCategory);
						theCase.setMemberId(member);
						theCase.setCaseStartTime(new java.sql.Date(System.currentTimeMillis()));
						theCase.setIsGLIssued(1);
						
						if (referedCase != null){
							theCase.setReferedCaseId(referedCase);
						}				
						
						if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
							theCase.setCaseEndTime(new java.sql.Date(System.currentTimeMillis()));
						}
						else {
							DateTime dt = new DateTime();
							DateTime endTime = dt.plusDays(4);
							
							theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
						}
						
						if (provider != null){
							theCase.setProviderId(provider);
						}
						
					
						ActionUser actionUser = new ActionUser();
						User user = new User();
						user.setUsername(merchantId);
						actionUser.setUser(user);
						CaseStatus status = new CaseStatus();
						status.setCaseStatusId(CaseStatus.CASE_OPEN);
						theCase.setCaseType(Case.CASE_EDC);
						theCase.setCaseStatusId(status);
						
						if (terminalId != null){
							String[] eqTerm = {"providerId.providerId","deviceNumber","deletedStatus"};
							Object[] eqValueTerm = {provider.getProviderId(),terminalId,0};							
							
							Collection<EdcTerminal> edcTerminalList = edcTerminalService.search(null,null,eqTerm,eqValueTerm,0,1);
							
							if (edcTerminalList != null && edcTerminalList.size() > 0){
								
								Iterator<EdcTerminal> termIterator= edcTerminalList.iterator();
								
								EdcTerminal edcTerminal = termIterator.next();
								
								if (edcTerminal != null){
									theCase.setTerminalId(edcTerminal);
									theCase.setMerchantId(provider.getEdcCode());
									
									edcTerminal.setLastTransaction(new java.sql.Date(System.currentTimeMillis()));
									edcTerminalService.update(edcTerminal, actionUser);
									
								}
							}						
						}		
						
						//Add by aju on 20150805, for COB :D
						if(member.getLinkedCardNumber() != null && !member.getLinkedCardNumber().trim().equalsIgnoreCase("")){
							theCase.setIsLinkedMember(1);
						}
						else{
							theCase.setIsLinkedMember(0);
						}
						
						System.out.println("BEFORE CREATE CASE 1 ");
						
						//modified by aju on 20150830, to support PRE AUTH
						//Case newCase = caseService.create(theCase, actionUser);
						Case newCase = null;

						if(totalCase>0){
							newCase = caseService.update(theCase, actionUser);
						}
						else{
							newCase = caseService.create(theCase, actionUser);
						}
		
						String caseNum = "";
						
						if (newCase != null){
							caseNum = newCase.getCaseNumber();
						}
		
						responseLine = caseNum+"#"+responseLine;
						
						System.out.println("AFTER CREATE CASE 1");
						result.setAdditionalMessage(responseLine);
						result.setReferenceNumber(referenceNumber);
						result.setResult(true);
					}
					else {
						result.setResult(false);
						result.setAdditionalMessage("NOTCOVERED");
					}
				}
				else {
					result.setResult(false);
					result.setAdditionalMessage("NOTCOVERED");
				}
			}
			else {
				System.out.println("ELSE --> FALSE");
				result.setResult(false);
				
				int status = member.getStatus().intValue();
				
				System.out.println("STATUS ===> " + status); 
				if (status == SubscriptionStatus.RESIGNED || status == -2){
					result.setAdditionalMessage("RESIGNED");
				}
				else if (status == SubscriptionStatus.TERMINATED ){
					result.setAdditionalMessage("TERMINATED");
				}
				else if (status == SubscriptionStatus.BLOCKED || status == SubscriptionStatus.INACTIVE){
					result.setAdditionalMessage("BLOCKED");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	public ActionResult getAvailableMemberEDCBenefit(String cardNumber,
			String memberNumber, String serviceType,String merchantId) {
		ActionResult result = new ActionResult();

		try {
			long start = System.currentTimeMillis();
			Member member = memberService.getMemberByCardNumber(cardNumber);

			CaseCategory caseCategory = caseCategoryService
					.getCaseCategoryEDC(serviceType);
			
			if (member != null) {
				
				boolean isManagedCare = false;
				boolean isIndemnity = false;
				boolean isRefered = false;
				
				Policy policy = null;
				
				if (member.getCurrentPolicyId() != null){
					policy = policyService.get(member.getCurrentPolicyId().getPolicyId());
				}
				else {
					policy = policyService.getGroupActivePolicy(member.getMemberGroupId().getMemberGroupId(), member.getClientId().getClientId());
				}
				
				Provider provider = providerService.getByProviderCode(merchantId);
				
				if (policy != null){
					if (policy.getPolicyType().intValue() == 1){
						isIndemnity = true;
					}
					else if (policy.getPolicyType().intValue() == 2){
						isManagedCare = true;
						

					}
				}

				Integer ccId = null;
				
				if (caseCategory != null){
					ccId = caseCategory.getCaseCategoryId();
				}
				else {
					if (isManagedCare){

						Poliklinik poliklinik = poliklinikService.getPoliByEdcCode(serviceType);
						
						if (poliklinik != null){
							ccId = poliklinik.getCaseCategoryId().getCaseCategoryId();
						}
					}
				}
				String[] required = {"MemberBenefit.ItemCategoryId"};
				
				String[] eqParam = {"memberId.memberId","caseCategoryId.caseCategoryId","isEDCEnabled","memberBenefitStatus"};
				Object[] eqValue = {member.getMemberId(),ccId,Boolean.TRUE,SubscriptionStatus.ACTIVE};
				
				int totalBenefit = memberBenefitService.getTotal(null,null, eqParam, eqValue);
				Collection<MemberBenefit> memberBenefitList = memberBenefitService.search(null,null,eqParam,eqValue,required,0,totalBenefit);				

				String responseLine = "";
				int index = 0;

				if (memberBenefitList != null) {
					for (Iterator iterator = memberBenefitList.iterator(); iterator
							.hasNext();) {

						MemberBenefit memberBenefit = (MemberBenefit) iterator
								.next();

						if (memberBenefit != null
								&& memberBenefit.getIsEDCEnabled()) {
							
							String itemCategoryName = "";
							
							if (memberBenefit.getItemCategoryId().getItemCategoryEDCName() != null){
								itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryEDCName();
							}
							else {
								itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryName();
							}
							if (index == 0) {
								responseLine += itemCategoryName
										+ "#"
										+ memberBenefit.getItemCategoryId()
										.getItemCategoryCode();

							} else {
								responseLine += "|" + itemCategoryName
								+ "#"
								+ memberBenefit.getItemCategoryId()
								.getItemCategoryCode();
							}
						}
						index++;

					}
				}

				result.setAdditionalMessage(responseLine);
				result.setResult(true);
			}
			long end = System.currentTimeMillis();
			
			System.out.println("TOTAL TIME = " + (end-start)/1000 + " (s)");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	
	public ActionResult getAvailableMemberEDCService(String cardNumber,String memberNumber, String merchantId) {
		// TODO Auto-generated method stub

		ActionResult result = new ActionResult();

		try {

			Member member = memberService.getMemberByCardNumber(cardNumber);
			
			
			System.out.println("GETTING MEMBER : " + memberNumber + " CARD NUMBER :" + cardNumber + " MEMBER INSTANCE : " + member);

			if (member != null) {

				boolean isManagedCare = false;
				boolean isIndemnity = false;
				boolean isRefered = false;
				
				Policy policy = policyService.getGroupActivePolicy(member.getMemberGroupId().getMemberGroupId(), member.getClientId().getClientId());
				
				Provider provider = providerService.getByProviderCode(merchantId);
				
				if (policy != null){
					if (policy.getPolicyType().intValue() == 1){
						isIndemnity = true;
					}
					else if (policy.getPolicyType().intValue() == 2){
						isManagedCare = true;
					}
				}
				
				
				if (isIndemnity){
					String responseLine = "";
					int index = 0;
					
					Collection<MemberBenefit> benefitList = memberBenefitService.getMemberBenefitList(member.getMemberId());
					
					Vector<MemberBenefit> responseList = new Vector<MemberBenefit>(); 
					
					if (benefitList != null && benefitList.size() > 0){
						for (Iterator iterator = benefitList.iterator(); iterator.hasNext();) {
							
							MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
							Product product = memberBenefit.getProductId();
							
							if (memberBenefit != null && product != null && product.getEdcEnabled() != null && product.getEdcEnabled().intValue() == 1){
								boolean isExist = isMemberBenefitCategoryExist(responseList, memberBenefit);
								
								if (!isExist){
									responseList.add(memberBenefit);
									
									
									if (index == 0) {
										responseLine += memberBenefit.getCaseCategoryId()
												.getCaseCategoryName()
												+ "#"
												+ memberBenefit.getCaseCategoryId()
														.getCaseCategoryEdcCode();
		
									} else {
										responseLine += "|"
												+ memberBenefit.getCaseCategoryId()
														.getCaseCategoryName()
												+ "#"
												+ memberBenefit.getCaseCategoryId()
														.getCaseCategoryEdcCode();
									}
									
									index++;
								}
							}
							
						}
					}
					/**
					Collection<MemberProduct> memberProductList = memberProductService
							.getMemberActiveProduct(member.getMemberId());
	
					
	
					if (memberProductList != null) {
						for (Iterator iterator = memberProductList.iterator(); iterator
								.hasNext();) {
	
							MemberProduct memberProduct = (MemberProduct) iterator
									.next();
	
							if (memberProduct != null	&& memberProduct.isEDCEnabled()) {
								if (index == 0) {
									responseLine += memberProduct.getProductId()
											.getCaseCategory()
											.getCaseCategoryName()
											+ "#"
											+ memberProduct.getProductId()
													.getCaseCategory()
													.getCaseCategoryEdcCode();
	
								} else {
									responseLine += "|"
											+ memberProduct.getProductId()
													.getCaseCategory()
													.getCaseCategoryName()
											+ "#"
											+ memberProduct.getProductId()
													.getCaseCategory()
													.getCaseCategoryEdcCode();
								}
							}
							index++;
	
						}
					}*/
					
					result.setAdditionalMessage(responseLine);
					result.setResult(true);
				}
				else if (isManagedCare){
					if (provider != null){
						if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.PPK1_UMUM){
							String responseLine = "POLI UMUM"+ "#"+ "1010";
							
							result.setAdditionalMessage(responseLine);
							result.setResult(true);
											
						}
						else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.PPK1_GIGI){
							String responseLine = "POLI GIGI"+ "#"+ "4010";
							
							result.setAdditionalMessage(responseLine);
							result.setResult(true);
						}
						else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.PPK2){
							
							String[] eqParam = {"deletedStatus","productId.caseCategory.caseCategoryId","memberId.memberId"};
							Object[] eqValue = {0,CaseCategory.SPECIALIST,member.getMemberId()};
							
							Collection<MemberProduct> memberProductList = memberProductService.search(null,null,eqParam,eqValue,0,1);
							if (memberProductList != null){
								Iterator<MemberProduct> iterator = memberProductList.iterator();
								
								if (iterator.hasNext()){
									MemberProduct product = iterator.next();
									
									if (product != null){
										ProductType type = product.getProductId().getProductType();
										
										String[] eqProdType = {"deletedStatus","productTypeId.productTypeId","status"};
										Object[] eqProdTypeVal = {0,type.getProductTypeId(),1};
										
										int totalProd = productTypePoliklinikService.getTotal(null,null,eqProdType,eqProdTypeVal);
										Collection<ProductTypePoliklinik> poliList = productTypePoliklinikService.search(null,null,
												eqProdType,eqProdTypeVal,0,totalProd);
										
										if (poliList != null && poliList.size() > 0){
											Iterator<ProductTypePoliklinik> iteratorPoli = poliList.iterator();
											String responseLine = "";
											
											int index = 0;
											while (iteratorPoli.hasNext()){
												ProductTypePoliklinik poli = iteratorPoli.next();
												
												if (poli != null){
													if (index == 0) {
														responseLine += poli.getPoliklinikId().getPoliklinikCode()
																+ "#"
																+ poli.getPoliklinikId().getPoliklinikEdcCode();
						
													} else {
														responseLine += "|"
																+ poli.getPoliklinikId().getPoliklinikCode()
																+ "#"
																+ poli.getPoliklinikId().getPoliklinikEdcCode();
													}
													index++;
												}
											}
											result.setAdditionalMessage(responseLine);
											result.setResult(true);
										}
										else if (poliList == null || poliList.size() == 0){
											String[] eqParamPoli = {"deletedStatus","providerId.providerId"};
											Object[] eqValuePoli = {Integer.valueOf(0),provider.getProviderId()};
											
											String responseLine = "";
											
											int totalPoli = providerPoliklinikService.getTotal(null,null,eqParamPoli,eqValuePoli);
											Collection<ProviderPoliklinik> providerPoliList = providerPoliklinikService.search(null,null,eqParamPoli,eqValuePoli,0,totalPoli);
											
											if (providerPoliList != null){
											
												Iterator<ProviderPoliklinik> iteratorPoli = providerPoliList.iterator();
												
												int index = 0;
												
												while (iteratorPoli.hasNext()){
													ProviderPoliklinik poli = iteratorPoli.next();
													
													if (poli != null){
														if (index == 0) {
															responseLine += poli.getPoliklinikId().getPoliklinikCode()
																	+ "#"
																	+ poli.getPoliklinikId().getPoliklinikEdcCode();
							
														} else {
															responseLine += "|"
																	+ poli.getPoliklinikId().getPoliklinikCode()
																	+ "#"
																	+ poli.getPoliklinikId().getPoliklinikEdcCode();
														}
														index++;
													}
												}
												result.setAdditionalMessage(responseLine);
												result.setResult(true);
											}
											
											
										}
										
										
									}
								}
							}
						}
						else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.RUMAH_SAKIT){
							// PPK3 dan atau PPK2 (Poliklinik)
						
							String[] eqParam = {"deletedStatus","productId.caseCategory.caseCategoryId","memberId.memberId"};
							Object[] eqValue = {0,CaseCategory.SPECIALIST,member.getMemberId()};
							
							Collection<MemberProduct> memberProductList = memberProductService.search(null,null,eqParam,eqValue,0,1);
							if (memberProductList != null){
								Iterator<MemberProduct> iterator = memberProductList.iterator();
								
								if (iterator.hasNext()){
									MemberProduct product = iterator.next();
									
									if (product != null){
										ProductType type = product.getProductId().getProductType();
										
										String[] eqProdType = {"deletedStatus","productTypeId.productTypeId","status"};
										Object[] eqProdTypeVal = {0,type.getProductTypeId(),1};
										
										int totalProd = productTypePoliklinikService.getTotal(null,null,eqProdType,eqProdTypeVal);
										Collection<ProductTypePoliklinik> poliList = productTypePoliklinikService.search(null,null,
												eqProdType,eqProdTypeVal,0,totalProd);
										
										if (poliList != null && poliList.size() > 0){
											Iterator<ProductTypePoliklinik> iteratorPoli = poliList.iterator();
											String responseLine = "";
											
											int index = 0;
											while (iteratorPoli.hasNext()){
												ProductTypePoliklinik poli = iteratorPoli.next();
												
												if (poli != null){
													if (index == 0) {
														responseLine += poli.getPoliklinikId().getPoliklinikCode()
																+ "#"
																+ poli.getPoliklinikId().getPoliklinikEdcCode();
						
													} else {
														responseLine += "|"
																+ poli.getPoliklinikId().getPoliklinikCode()
																+ "#"
																+ poli.getPoliklinikId().getPoliklinikEdcCode();
													}
													index++;
												}
											}
											result.setAdditionalMessage(responseLine);
											
											result.setResult(true);
										}
										else if (poliList == null || poliList.size() == 0){
											String[] eqParamPoli = {"deletedStatus","providerId.providerId"};
											Object[] eqValuePoli = {Integer.valueOf(0),provider.getProviderId()};
											
											String responseLine = "";
											
											int totalPoli = providerPoliklinikService.getTotal(null,null,eqParamPoli,eqValuePoli);
											Collection<ProviderPoliklinik> providerPoliList = providerPoliklinikService.search(null,null,eqParamPoli,eqValuePoli,0,totalPoli);
											
											if (providerPoliList != null){
											
												Iterator<ProviderPoliklinik> iteratorPoli = providerPoliList.iterator();
												
												int index = 0;
												
												while (iteratorPoli.hasNext()){
													ProviderPoliklinik poli = iteratorPoli.next();
													
													if (poli != null){
														if (index == 0) {
															responseLine += poli.getPoliklinikId().getPoliklinikCode()
																	+ "#"
																	+ poli.getPoliklinikId().getPoliklinikEdcCode();
							
														} else {
															responseLine += "|"
																	+ poli.getPoliklinikId().getPoliklinikCode()
																	+ "#"
																	+ poli.getPoliklinikId().getPoliklinikEdcCode();
														}
														index++;
													}
												}
												result.setAdditionalMessage(responseLine);
												result.setResult(true);
											}
										}
									}
								}
							}
							
						}
						else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.APOTEK){
							
						}
						else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.LAB){
							
						}
					}					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private boolean isMemberBenefitCategoryExist (Vector<MemberBenefit> benefitList, MemberBenefit mb){
		boolean result = false;
		
		for (Iterator iterator = benefitList.iterator(); iterator.hasNext();) {
			MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
			
			if (memberBenefit != null && mb != null){
				if (memberBenefit.getCaseCategoryId().getCaseCategoryId().intValue() == mb.getCaseCategoryId().getCaseCategoryId().intValue()){
					return true;
				}
			}
		}
		return result;
	}
	
	public Collection<MemberDto> getMemberList(Date createdTime, Date createdTime2){//buatan luthfi, untuk menampilkan data member berdasarkan minium created time sampai dengan maksimum created time
		Collection<MemberDto> resultList = null;
		
		try{
			String[] betweenColumn = { "createdTime" };
			Object[] minColumn = { createdTime };
			Object[] maxColumn = { createdTime2 };
			
			String[] required = {"Member.ClientId","Member.MemberGroupId","Member.CurrentPolicyId", "Member.ParentMember"};
			
			Collection<Member> memberList = memberService.searchMemberList(betweenColumn, minColumn, maxColumn, required);
			
			if (memberList != null ) {
				resultList = new Vector<MemberDto>();
				Iterator<Member> iterator = memberList.iterator();

				while (iterator.hasNext()) {
					MemberDto result = new MemberDto();
					Member member = iterator.next();
					
					result.setMemberGroupName(member.getMemberGroupId().getGroupName() == null ? "" : (member.getMemberGroupId().getGroupName()));
					result.setMemberGroupCode(member.getMemberGroupId().getMemberGroupCode() == null ? "" : (member.getMemberGroupId().getMemberGroupCode()));
					result.setCustomerPolicyNumber(member.getCustomerPolicyNumber() == null ? "" : (member.getCustomerPolicyNumber()));
					result.setEffectiveDate(member.getEffectiveDate() == null ? null : (member.getEffectiveDate()));
					result.setExpireDate(member.getExpireDate() == null ? null : (member.getExpireDate()));
					result.setFirstName(member.getFullName() == null ? "" : (member.getFullName()));
					result.setClientCode(member.getClientId().getClientCode() == null ? "" : (member.getClientId().getClientCode()));
					result.setClientName(member.getClientId().getClientName() == null ? "" : (member.getClientId().getClientName()));
					result.setStatus(member.getDeletedStatus() == null ? "" : (member.getDeletedStatus().toString()));
					result.setBirthday(member.getBirthday() == null ? null : (member.getBirthday()));
					result.setCardNumber(member.getCurrentCardNumber() == null ? "" : (member.getCurrentCardNumber()));
					result.setCreatedTime(member.getCreatedTime() == null ?  null : (new java.sql.Date(member.getCreatedTime().getTime()).toString()));
					result.setModifiedTime(member.getModifiedTime() == null ?  null : (new java.sql.Date(member.getModifiedTime().getTime()).toString()));

					resultList.add(result);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return resultList;
	}
	
	public Collection<MemberDto> searchMemberByDobClientGroupFullName(String clientName, String groupName, String dob, String name) {//buatan Luthfi, untuk menampilkan data member berdasarkan filteran dob, clientid dan membergroupid 
		// TODO Auto-generated method stub
		Collection<MemberDto> resultList = null;
		
//		System.out.println(dob);
//		new java.sql.Date(member.getModifiedTime().getTime()).toString()
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date date = null;
		try {
			date = formatter.parse(dob);
			System.out.println(date);
		} catch (ParseException e1) {
			dob = "";
			e1.printStackTrace();
		}
		
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();
		Vector vFirstName = new Vector();
		Vector voFirstName = new Vector();


		try {
			vEqP.add("deletedStatus");
			vFirstName.add("fullName");
			vFirstName.add("clientId.clientName");
			vFirstName.add("memberGroupId.groupName");
//			vFirstName.add("birthday");
			vEqQ.add(Integer.valueOf(0));
			voFirstName.add(name);
			voFirstName.add(clientName);
			voFirstName.add(groupName);
//			voFirstName.add(date);
			
			System.out.println("hasil clientName :"+ clientName);
			System.out.println("hasil groupName :"+ groupName);
			System.out.println("hasil name :"+ name);
			System.out.println("hasil dob :"+ dob);
			
			if(dob != null && !dob.equals(""))
			{
				vEqP.add("birthday");
				vEqQ.add(Date.valueOf(dob));
			}

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			
			String sFirstName[] = new String[vFirstName.size()];
			vFirstName.toArray(sFirstName);//done
			Object oFirstName[] = new Object[vFirstName.size()];
			voFirstName.toArray(oFirstName);
			
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			String[] required = {"Member.ClientId","Member.MemberGroupId","Member.CurrentPolicyId", "Member.ParentMember"};
			
			Collection<Member> memberList = memberService.searchMemberByDobClientGroup(sEqP, sFirstName, oFirstName, sEqQ, required);

			if (memberList != null ) {
				resultList = new Vector<MemberDto>();
				Iterator<Member> iterator = memberList.iterator();

				while (iterator.hasNext()) {
					MemberDto result = new MemberDto();
					Member member = iterator.next();
					
					result.setMemberGroupName(member.getMemberGroupId().getGroupName() == null ? "" : (member.getMemberGroupId().getGroupName()));
					result.setMemberGroupCode(member.getMemberGroupId().getMemberGroupCode() == null ? "" : (member.getMemberGroupId().getMemberGroupCode()));
					result.setCustomerPolicyNumber(member.getCustomerPolicyNumber() == null ? "" : (member.getCustomerPolicyNumber()));
					result.setEffectiveDate(member.getEffectiveDate() == null ? null : (member.getEffectiveDate()));
					result.setExpireDate(member.getExpireDate() == null ? null : (member.getExpireDate()));
					result.setFirstName(member.getFullName() == null ? "" : (member.getFullName()));
					result.setClientCode(member.getClientId().getClientCode() == null ? "" : (member.getClientId().getClientCode()));
					result.setClientName(member.getClientId().getClientName() == null ? "" : (member.getClientId().getClientName()));
					result.setStatus(member.getDeletedStatus() == null ? "" : (member.getDeletedStatus().toString()));
					result.setBirthday(member.getBirthday() == null ? null : (member.getBirthday()));
					result.setCardNumber(member.getCurrentCardNumber() == null ? "" : (member.getCurrentCardNumber()));
					result.setClientId(member.getClientId().getClientId() == null ? "" : member.getClientId().getClientId().toString());
					result.setMemberGroupId(member.getMemberGroupId().getMemberGroupId() == null ? "" : (member.getMemberGroupId().getMemberGroupId().toString()));
					result.setMemberId(member.getMemberId() == null ? null : (member.getMemberId()));
//					result.setAnnualPremium(memberElectronicCard.getMemberId().getCurrentAnnualPremium() == null ? "" : memberElectronicCard.getMemberId().getCurrentAnnualPremium().toString());
//					result.setBank(memberElectronicCard.getMemberId().getBank() == null ? "" : memberElectronicCard.getMemberId().getBank());
//					result.setBankAccount(memberElectronicCard.getMemberId().getBankAccount() == null ? "" : memberElectronicCard.getMemberId().getBankAccount());
//					result.setBankAccountName(memberElectronicCard.getMemberId().getBankAccountName() == null ? "" : memberElectronicCard.getMemberId().getBankAccountName());
//					result.setBankBranch(memberElectronicCard.getMemberId().getBankBranch() == null ? "" : memberElectronicCard.getMemberId().getBankBranch());
//					result.setCardStatus(memberElectronicCard.getCardStatus() == null ? null : (memberElectronicCard.getCardStatus().toString()));
//					result.setCurrentBenefitCode(memberElectronicCard.getMemberId().getCurrentProductCode() == null ? "" : (memberElectronicCard.getMemberId().getCurrentProductCode()));
//					result.setCustomerNumber(memberElectronicCard.getMemberId().getCustomerNumber() == null ? "" : (memberElectronicCard.getMemberId().getCustomerNumber()));
//					result.setGender(memberElectronicCard.getMemberId().getGender() == null ? "" : (memberElectronicCard.getMemberId().getGender()));
//					result.setJoinDate(memberElectronicCard.getMemberId().getJoinDate() == null ? null : (memberElectronicCard.getMemberId().getJoinDate()));
//					result.setParentId(memberElectronicCard.getMemberId().getParentMember().getMemberId() == null ? "" : (memberElectronicCard.getMemberId().getParentMember().getMemberId().toString()));
//					result.setParentName(memberElectronicCard.getMemberId().getParentMember().getFullName() == null ? "" : (memberElectronicCard.getMemberId().getParentMember().getFullName()));
//					result.setPolicyNumber(memberElectronicCard.getMemberId().getCurrentPolicyId().getPolicyNumber() == null ? "" : (memberElectronicCard.getMemberId().getCurrentPolicyId().getPolicyNumber()));
//					result.setPolicyType(memberElectronicCard.getMemberId().getCurrentPolicyId().getPolicyType() == null ? "" : (memberElectronicCard.getMemberId().getCurrentPolicyId().getPolicyType().toString()));
//					result.setRenewalDate(memberElectronicCard.getMemberId().getMemberGroupId().getRenewalDate() == null ? null : (memberElectronicCard.getMemberId().getMemberGroupId().getRenewalDate()));
//					result.setResignedDate(memberElectronicCard.getMemberId().getMemberGroupId().getResignedDate() == null ? null : (memberElectronicCard.getMemberId().getMemberGroupId().getResignedDate()));

					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
		
	}

	public Collection<MemberDto> searchMemberByDob(Date dob) {//buatan Luthfi, untuk menampilkan data member berdasarkan filteran dob
		// TODO Auto-generated method stub
		Collection<MemberDto> resultList = null;
		
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();
		Vector vFirstName = new Vector();
		Vector voFirstName = new Vector();


		try {
			vEqP.add("deletedStatus");
			vEqP.add("birthday");
			vEqQ.add(Integer.valueOf(0));
			vEqQ.add(dob);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			String[] required = {"Member.ClientId","Member.MemberGroupId","Member.CurrentPolicyId", "Member.ParentMember"};
			
			Collection<Member> memberList = memberService.searchMemberByDob(sEqP, sEqQ, required);

			if (memberList != null ) {
				resultList = new Vector<MemberDto>();
				Iterator<Member> iterator = memberList.iterator();

				while (iterator.hasNext()) {
					MemberDto result = new MemberDto();
					Member member = iterator.next();
					
					result.setMemberGroupName(member.getMemberGroupId().getGroupName() == null ? "" : (member.getMemberGroupId().getGroupName()));
					result.setMemberGroupCode(member.getMemberGroupId().getMemberGroupCode() == null ? "" : (member.getMemberGroupId().getMemberGroupCode()));
					result.setCustomerPolicyNumber(member.getCustomerPolicyNumber() == null ? "" : (member.getCustomerPolicyNumber()));
					result.setEffectiveDate(member.getEffectiveDate() == null ? null : (member.getEffectiveDate()));
					result.setExpireDate(member.getExpireDate() == null ? null : (member.getExpireDate()));
					result.setFirstName(member.getFullName() == null ? "" : (member.getFullName()));
					result.setClientCode(member.getClientId().getClientCode() == null ? "" : (member.getClientId().getClientCode()));
					result.setClientName(member.getClientId().getClientName() == null ? "" : (member.getClientId().getClientName()));
					result.setStatus(member.getDeletedStatus() == null ? "" : (member.getDeletedStatus().toString()));
					result.setBirthday(member.getBirthday() == null ? null : (member.getBirthday()));
					result.setCardNumber(member.getCurrentCardNumber() == null ? "" : (member.getCurrentCardNumber()));

					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
		
	}

	public Collection<MemberGroupDto> getMemberGroupByClient(String clientId){//buatan Luthfi (untuk menampilkan membergroup dari inputan clientId)
		
		Collection<MemberGroupDto> result = new Vector<MemberGroupDto>();
		
		try 
		{
			Collection<MemberGroup> tmpRes = memberGroupService.getMemberGroupByClient( Integer
					.valueOf(clientId));
			
			for (MemberGroup memberGroup : tmpRes){
				MemberGroupDto dto = new MemberGroupDto();
				dto.setClientId(memberGroup.getClientId().getClientId() == null ? "" : memberGroup.getClientId().getClientId().toString());
				dto.setMemberGroupName(memberGroup.getGroupName() == null ? "" : memberGroup.getGroupName());
				dto.setJoinDate(memberGroup.getJoinDate() == null ? "" : memberGroup.getJoinDate().toString());
				dto.setExpireDate(memberGroup.getExpireDate() == null ? "" : memberGroup.getExpireDate().toString());
				dto.setRenewalDate(memberGroup.getRenewalDate() == null ? "" : memberGroup.getRenewalDate().toString());
				dto.setMemberGroupCode(memberGroup.getMemberGroupCode() == null ? "" : memberGroup.getMemberGroupCode());
				dto.setMemberGroupId(memberGroup.getMemberGroupId() == null ? "" : memberGroup.getMemberGroupId().toString());
				dto.setStatus(memberGroup.getDeletedStatus() == null ? "" : memberGroup.getDeletedStatus().toString());
				
				result.add(dto);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public MemberDto getMemberByCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		MemberDto result = null;
		try {
			
			Member member = memberService.getMemberByCardNumber( cardNumber);
			
			if (member != null){
				
				result = new MemberDto();
				result.setCustomerPolicyNumber(member.getCustomerPolicyNumber());
				result.setCustomerNumber(member.getCustomerNumber());
				result.setMemberId(member.getMemberId());
				if (member.getParentMember() != null){
					result.setParentId(member.getParentMember().getMemberId().toString());
					result.setParentName(member.getParentMember().getFirstName());
				}
				result.setCardNumber(member.getCurrentCardNumber() == null ? "" : member.getCurrentCardNumber());				
				
				if (member.getCurrentPolicyId() != null){
					result.setPolicyNumber(member.getCurrentPolicyId().getPolicyNumber() == null ? "" : member.getCurrentPolicyId().getPolicyNumber());
				}

				
				result.setFirstName(member.getFirstName());
				result.setBirthday(member.getBirthday());
				result.setExpireDate(member.getExpireDate());
				result.setEffectiveDate(member.getEffectiveDate());
				result.setResignedDate(member.getResignedDate());
				
				result.setClientName(member.getClientId().getClientName());
				result.setClientCode(member.getClientId().getClientCode());
				
				if (member.getMemberGroupId() != null){
					result.setMemberGroupCode(member.getMemberGroupId().getMemberGroupCode() == null ? "" : member.getMemberGroupId().getMemberGroupCode());
					result.setMemberGroupId(member.getMemberGroupId().getMemberGroupId().toString());
					
					
					result.setMemberGroupName(member.getMemberGroupId().getGroupName());
					
					PolicyDto activePolicy = getActivePolicy(member.getMemberGroupId().getMemberGroupId().toString(), null);
					
					if (activePolicy != null){
						result.setPolicyType(activePolicy.getPolicyType());
					}
					
				}
				
				
				if (member.getMemberGroupId() != null){
					if (member.getMemberGroupId().getStatus().getStatusId().intValue() == SubscriptionStatus.ACTIVE){
						result.setStatus(member.getStatus().toString());
					}
					else {
						result.setStatus(member.getMemberGroupId().getStatus().getStatusId().toString());
					}
				}
				
				String[] eqCardParam = {"memberId.memberId","cardNumber"};
				Object[] eqCardValue = {member.getMemberId(),cardNumber};
				
				Collection<MemberElectronicCard> cardList = memberCardService.search(null,null,eqCardParam,eqCardValue,0,1);
				
				if (cardList != null){
					Iterator<MemberElectronicCard> iterator = cardList.iterator();
					
					if (iterator.hasNext()){
						MemberElectronicCard card = iterator.next();
						
						if (card != null ){
							result.setCardStatus(card.getCardStatus().toString());
						}
						else {
							result.setCardStatus("0");
						}
					}
				}
				else {
					result.setCardStatus("UNKNOWN");
				}
				
			}
			else {
				String[] eqCardParam = {"deletedStatus","cardNumber"};
				Object[] eqCardValue = {Integer.valueOf(0),cardNumber};
				
				Collection<MemberElectronicCard> cardList = memberCardService.search(null,null,eqCardParam,eqCardValue,0,1);
				if (cardList != null){
					Iterator<MemberElectronicCard> iterator = cardList.iterator();
					if (iterator.hasNext()){
				
						MemberElectronicCard memberCard = iterator.next();
						
						String[] reqMember = {"Member.MemberGroupId","Member.MemberGroupId.ClientId","Member.ClientId"};
						member = memberCard.getMemberId();
						
						result = new MemberDto();
						result.setCustomerPolicyNumber(member.getCustomerPolicyNumber());
						result.setCustomerNumber(member.getCustomerNumber());
						result.setMemberId(member.getMemberId());
						
						
						result.setFirstName(member.getFirstName());
						result.setBirthday(member.getBirthday());
						result.setExpireDate(member.getExpireDate());
						result.setEffectiveDate(member.getEffectiveDate());
						result.setResignedDate(member.getResignedDate());
						result.setClientName(member.getClientId().getClientName());
						result.setClientCode(member.getClientId().getClientCode());
						
						
						if (member.getMemberGroupId() != null){
							result.setMemberGroupId(member.getMemberGroupId().getMemberGroupId().toString());
							result.setPolicyNumber(member.getCurrentPolicyId().getPolicyNumber());
							result.setMemberGroupName(member.getMemberGroupId().getGroupName());
							
							if (member.getMemberGroupId().getStatus().getStatusId().intValue() == SubscriptionStatus.ACTIVE){
								result.setStatus(member.getStatus().toString());
							}
							else {
								result.setStatus(member.getMemberGroupId().getStatus().getStatusId().toString());
							}
						}
						
						result.setCardStatus(memberCard.getCardStatus().toString());
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	//Add by aju on 20150818, for getting member by memberId
	public MemberDto getMemberByMemberId(String memberId) {
		// TODO Auto-generated method stub
		MemberDto result = null;
		try {
			
			//Member member = memberService.getMemberByCardNumber( cardNumber);
			java.io.Serializable mid = Integer.parseInt(memberId);
			String[] required = {"Member.MemberGroupId","Member.ClientId","Member.MemberGroupId.Status",
					             "Member.CurrentPolicyId", "Member.ParentMember"};
			Member member = memberService.get(mid, required);
			
			if (member != null){
				
				result = new MemberDto();
				result.setCustomerPolicyNumber(member.getCustomerPolicyNumber());
				result.setCustomerNumber(member.getCustomerNumber());
				result.setMemberId(member.getMemberId());
				if (member.getParentMember() != null){
					result.setParentId(member.getParentMember().getMemberId().toString());
					result.setParentName(member.getParentMember().getFirstName());
				}
				result.setCardNumber(member.getCurrentCardNumber() == null ? "" : member.getCurrentCardNumber());				
				
				if (member.getCurrentPolicyId() != null){
					result.setPolicyNumber(member.getCurrentPolicyId().getPolicyNumber() == null ? "" : member.getCurrentPolicyId().getPolicyNumber());
				}

				
				result.setFirstName(member.getFirstName());
				result.setBirthday(member.getBirthday());
				result.setExpireDate(member.getExpireDate());
				result.setEffectiveDate(member.getEffectiveDate());
				result.setResignedDate(member.getResignedDate());
				
				result.setClientName(member.getClientId().getClientName());
				result.setClientCode(member.getClientId().getClientCode());
				
				if (member.getMemberGroupId() != null){
					result.setMemberGroupCode(member.getMemberGroupId().getMemberGroupCode() == null ? "" : member.getMemberGroupId().getMemberGroupCode());
					result.setMemberGroupId(member.getMemberGroupId().getMemberGroupId().toString());
					
					
					result.setMemberGroupName(member.getMemberGroupId().getGroupName());
					
					PolicyDto activePolicy = getActivePolicy(member.getMemberGroupId().getMemberGroupId().toString(), null);
					
					if (activePolicy != null){
						result.setPolicyType(activePolicy.getPolicyType());
					}
					
				}
				
				
				if (member.getMemberGroupId() != null){
					if (member.getMemberGroupId().getStatus().getStatusId().intValue() == SubscriptionStatus.ACTIVE){
						result.setStatus(member.getStatus().toString());
					}
					else {
						result.setStatus(member.getMemberGroupId().getStatus().getStatusId().toString());
					}
				}
				
//				String[] eqCardParam = {"memberId.memberId","cardNumber"};
//				Object[] eqCardValue = {member.getMemberId(),cardNumber};
				String[] eqCardParam = {"memberId.memberId"};
				Object[] eqCardValue = {member.getMemberId()};
				
				Collection<MemberElectronicCard> cardList = memberCardService.search(null,null,eqCardParam,eqCardValue,0,1);
				
				if (cardList != null){
					Iterator<MemberElectronicCard> iterator = cardList.iterator();
					
					if (iterator.hasNext()){
						MemberElectronicCard card = iterator.next();
						
						if (card != null ){
							result.setCardStatus(card.getCardStatus().toString());
						}
						else {
							result.setCardStatus("0");
						}
					}
				}
				else {
					result.setCardStatus("UNKNOWN");
				}
				
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public PolicyDto getActivePolicy(String memberGroupId, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub
		PolicyDto result = null;
		
		try {
			Policy policy = policyService.getGroupActivePolicy(Integer.valueOf(memberGroupId));
			
			if (policy != null){
				result = policy.exportDto();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ActionResult getFreeTextEDC(MemberDto memberDto, String serviceCategory)
			throws Exception {
		// TODO Auto-generated method stub
		ActionResult result = new ActionResult();

		String edcText = "";
		CaseCategory cc = caseCategoryService.getCaseCategoryEDC(serviceCategory);
		
		if (cc == null){
			
			Poliklinik poliklinik = poliklinikService.getPoliByEdcCode(serviceCategory);
			
			if (poliklinik != null){
				cc = poliklinik.getCaseCategoryId();
			}
		}
		
		String[] required = {"Member.MemberGroupId","Member.MemberGroupId","Member.MemberGroupId.ClientId"};
		
		Member member = memberService.get(memberDto.getMemberId(),required);
		
		Policy activePolicy = policyService.getGroupActivePolicy(member.getMemberGroupId().getMemberGroupId(), 
				member.getMemberGroupId().getClientId().getClientId());
		
		if (activePolicy != null){
			String[] eqParam = {"policyId.policyId","isEdc","deletedStatus","caseCategoryId.caseCategoryId"};
			Object[] eqValue = {activePolicy.getPolicyId(),Integer.valueOf(1),Integer.valueOf(0),cc.getCaseCategoryId()};
			
			Collection<PolicyClausul> clausulList = policyClausulService.search(null,null,eqParam,eqValue,0,10);
		
			for (Iterator iterator = clausulList.iterator(); iterator.hasNext();) {
				
				PolicyClausul policyClausul = (PolicyClausul) iterator.next();
				
				if (policyClausul != null){
					String line = policyClausul.getDescription();
					if (line != null){
						int len = line.length();
						if (len < 40){
							line = StringUtil.padding(line, 40);
							System.out.println("EDC TEXT = |" + line + "|");
							
						}
						else {
							line = StringUtil.padding(line, 40*(len/40+1));	
							System.out.println("EDC TEXT LONG 40 = |" + line + "|");
						}
						edcText += line;
					}
				}
				
			}
			result.setAdditionalMessage(edcText);
			result.setResult(true);
		}
		return result;
	}

	@Override
	public ActionResult activateMember(Integer memberId,
			String username, String password) {
		// TODO Auto-generated method stub
		
		ActionResult result = new ActionResult();
		result.setResult(false);
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				
				ActionUser actionUser = new ActionUser();
				actionUser.setUser(login);
				
				result = memberService.activate(memberId, actionUser);
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ActionResult activateMemberUpgrade(Integer memberId,
			String username, String password) {
		// TODO Auto-generated method stub
		ActionResult result = new ActionResult();
		result.setResult(false);
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				
				ActionUser actionUser = new ActionUser();
				actionUser.setUser(login);
				
				result = memberService.activateUpgrade(memberId, actionUser);
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Integer> getGetPendingEmployeeList(String username,
			String password) {
		// TODO Auto-generated method stub
		
		Collection<Integer> result = new Vector<Integer>();
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				 Collection<Object> res = memberService.getAllPendingEmployee();
				 if (res != null){
					 for (Iterator iterator = res.iterator(); iterator.hasNext();) {
						Object object = (Object) iterator.next();
						
						if (object instanceof Integer){
							result.add((Integer) object);
						}
					}
				 }
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Integer> getGetPendingMemberList(String username,
			String password) {
		Collection<Integer> result = new Vector<Integer>();
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				 Collection<Object> res = memberService.getAllPendingMember();
				 if (res != null){
					 for (Iterator iterator = res.iterator(); iterator.hasNext();) {
						Object object = (Object) iterator.next();
						
						if (object instanceof Integer){
							result.add((Integer) object);
						}
					}
				 }
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Integer> getGetPendingUpgradeEmployeeList(
			String username, String password) {
		Collection<Integer> result = new Vector<Integer>();
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				System.out.println("LOGIN GetPendingUpgradeEmployeeList = TRUE");
				 Collection<Object> res = memberService.getAllPendingUpgradeEmployee();
				 if (res != null){
					 System.out.println("memberService.getAllPendingUpgradeEmployee(); ==> NOT NULL LENGTH : " + res.size());
					 for (Iterator iterator = res.iterator(); iterator.hasNext();) {
						Object object = (Object) iterator.next();
						
						if (object instanceof Integer){
							result.add((Integer) object);
						}
					}
				 }
				 else {
					 System.out.println("memberService.getAllPendingUpgradeEmployee(); ==> NULL LENGTH ");
				 }
			}
			else {
				System.out.println("LOGIN GetPendingUpgradeEmployeeList = FALSE");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public Collection<Integer> getGetPendingUpgradeList(String username,
			String password) {
		Collection<Integer> result = new Vector<Integer>();
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				System.out.println("LOGIN GetPendingUpgradeList = TRUE");
				 Collection<Object> res = memberService.getAllPendingUpgradeMember();
				 if (res != null){
					 System.out.println("memberService.getAllPendingUpgradeMember(); ==> NOT NULL LENGTH : " + res.size());
					 for (Iterator iterator = res.iterator(); iterator.hasNext();) {
						Object object = (Object) iterator.next();
						
						if (object instanceof Integer){
							result.add((Integer) object);
						}
					}
				 }
				 else {
					 System.out.println("memberService.getAllPendingUpgradeMember(); ==> NULL");
				 }
			}
			else {
				System.out.println("LOGIN GetPendingUpgradeList = FALSE");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ActionResult activateMemberRenewal(Integer memberId,
			String username, String password) {

		ActionResult result = new ActionResult();
		result.setResult(false);
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				
				ActionUser actionUser = new ActionUser();
				actionUser.setUser(login);
				
				result = memberService.activateRenewal(memberId, actionUser);
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Integer> getGetPendingRenewalEmployeeList(
			String username, String password) {

		Collection<Integer> result = new Vector<Integer>();
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				 Collection<Object> res = memberService.getAllPendingRenewEmployee();
				 if (res != null){
					 for (Iterator iterator = res.iterator(); iterator.hasNext();) {
						Object object = (Object) iterator.next();
						
						if (object instanceof Integer){
							result.add((Integer) object);
						}
					}
				 }
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Integer> getGetPendingRenewalList(String username,
			String password) {
		// TODO Auto-generated method stub
		Collection<Integer> result = new Vector<Integer>();
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				 Collection<Object> res = memberService.getAllPendingRenewMember();
				 if (res != null){
					 for (Iterator iterator = res.iterator(); iterator.hasNext();) {
						Object object = (Object) iterator.next();
						
						if (object instanceof Integer){
							result.add((Integer) object);
						}
					}
				 }
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public Collection<Integer> getBlockedList(String username, String password) {
		// TODO Auto-generated method stub
		Collection<Integer> result = new Vector();
		
		try {
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	

	@Override
	public Collection<Integer> getMonthlyRenewalList(String username,
			String password) {
		// TODO Auto-generated method stub
		Collection<Integer> result = new Vector();
		
		try {
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Collection<Integer> getAutoRenewalNomineeList(String username,
			String password) {
		// TODO Auto-generated method stub
		Collection<Integer> result = new Vector<Integer>();
		
		try {
			User login = userService.loginMobile(username, password, "", "");			
			if (login != null){				
				result = memberService.getAutoRenewalNomineeList();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean requestAutoRenewalNominee(Integer memberId, String username, String password) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				Member member = memberService.get(memberId);
				ActionUser actionUser = new ActionUser();
				actionUser.setUser(login);
				
				if (member != null && member.getPolicyExpireDate() != null){
					if (member.getPolicyExpireDate().after(member.getExpireDate()) && 
							member.getExpireDate().before(new java.sql.Date(System.currentTimeMillis()))){
						
						// jika policy expire date nya masih jauh dari current expire date
						
						String paymentMethod = member.getPaymentPeriodeMethod() == null ? "" : member.getPaymentPeriodeMethod();
						
						boolean isRenewal = true;
						
						DateTime nextExpireDate = null;
						DateTime nextEffectiveDate = null;
						
						nextEffectiveDate = new DateTime(member.getExpireDate()).plusDays(1);
						
						if (paymentMethod.equalsIgnoreCase("A")){						
							nextExpireDate = new DateTime(member.getExpireDate()).plusYears(1);
						}
						else if (paymentMethod.equalsIgnoreCase("SM")){							
							nextExpireDate = new DateTime(member.getExpireDate()).plusMonths(6);							
						}
						else if (paymentMethod.equalsIgnoreCase("M")){
							nextExpireDate = new DateTime(member.getExpireDate()).plusMonths(1);
						}
						else if (paymentMethod.equalsIgnoreCase("Q")){
							nextExpireDate = new DateTime(member.getExpireDate()).plusMonths(3);
						}
							
						member.setNextEffectiveDate(new Date(nextEffectiveDate.getMillis()));
						member.setNextExpireDate(new Date(nextExpireDate.getMillis()));
						
						member.setNextCardNumber(member.getCurrentCardNumber());
						member.setNextPolicyNumber(member.getCurrentPolicyNumber());
						member.setNextCustomerNumber(member.getCustomerPolicyNumber());
						
						member.setStatus(SubscriptionStatus.PENDING_RENEWAL);
						
						memberService.update(member, actionUser);							
						
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Integer> getMutationList(String username, String password) {
		// TODO Auto-generated method stub
		Collection<Integer> result = new Vector<Integer>();
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				String[] eqParam = {"deletedStatus","status"};
				Object[] eqValue = {0,SubscriptionStatus.PENDING_MUTATION};
				
				int total = memberService.getTotal(null,null,eqParam,eqValue);
				Collection<Member> memberList = memberService.search(null,null,eqParam,eqValue,0,total);
				
				if (memberList != null){
					for (Iterator iterator = memberList.iterator(); iterator
							.hasNext();) {
						
						Member member = (Member) iterator.next();
						
						if (member != null){
							result.add(member.getMemberId());
						}						
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean processMutation(Integer requestId, String username,
			String password) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){		
				ActionUser actionUser = new ActionUser();
				actionUser.setUser(login);
				
				Member member = memberService.get(requestId);
				
				if (member != null && member.getStatus().intValue() == SubscriptionStatus.PENDING_MUTATION){
					ActionResult res = memberService.activateMutation(member.getMemberId(), member.getNextPolicyNumber(), 
							"", member.getNextEffectiveDate(), member.getNextExpireDate(), actionUser);
					
					if (res != null){
						result = res.isResult();
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ActionResult releaseSuspend() throws Exception {
		// TODO Auto-generated method stub
		ActionResult result = new ActionResult();
		
		try {
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			

			ActionUser user = new ActionUser();
			User theUser = new User();
			theUser.setUsername("system-daemon");
			user.setUser(theUser);
			
			System.out.println ("RELEASE SUSPEND");
			String[] eqParam = {"status","deletedStatus"};
			Object[] eqValue = {SubscriptionStatus.BLOCKED,Integer.valueOf(0)};
			
			int totalSuspend = memberService.getTotal(null,null,eqParam,eqValue);
			Collection<Member> memberList = memberService.search(null,null,eqParam,eqValue,0,totalSuspend);
			
			
			user.setUser(theUser);
			
			if (memberList != null){
				for (Iterator iterator = memberList.iterator(); iterator
						.hasNext();) {
					
					
					Member member = (Member) iterator.next();
					
					if (member != null){
						if (member.getSuspendEndDate() != null){
							if (currentDate.after(member.getSuspendEndDate())){
								member.setStatus(SubscriptionStatus.ACTIVE);
								
								memberService.update(member, user);
							}
						}
					}
					
				}
			}
			
			System.out.println("RELEASE GRACE");
			Object[] eqGraceValue = {SubscriptionStatus.GRACE,Integer.valueOf(0)};
			
			int totalGrace = memberService.getTotal(null,null,eqParam,eqGraceValue);
			memberList = memberService.search(null,null,eqParam,eqGraceValue,0,totalGrace);			
			
			if (memberList != null){
				for (Iterator iterator = memberList.iterator(); iterator
						.hasNext();) {
					
					
					Member member = (Member) iterator.next();
					
					if (member != null){
						if (member.getSuspendEndDate() != null){
							if (currentDate.after(member.getSuspendEndDate())){
								member.setStatus(SubscriptionStatus.ACTIVE);
								
								memberService.update(member, user);
							}
						}
					}
					
				}
			}
	
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ActionResult activateBenefitSynchronize(Integer memberId,
			String username, String password) {
		// TODO Auto-generated method stub
		ActionResult result = new ActionResult();
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				ActionUser user = new ActionUser();
				user.setUser(login);
				
				System.out.println("SYNCHRONIZE BENEFIT FOR MEMBER ID = " + memberId);
				result = memberService.activateSynchronize(memberId, user);
			}
			else {
				System.out.println("FAILED LOGIN FOR SYNCHRONIZE BENEFIT : " + username + " PASSWORD = " + password + "MEMBER ID = " + memberId);
			}
		}
		catch (Exception e){
			e.printStackTrace();
			
		}
		return result;
	}

	@Override
	public Collection<Integer> getGetPendingSynchronizeDependentList(
			Integer policyId, String username, String password) {
		Collection<Integer> result = new Vector<Integer>();
		 
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				 Collection<Object> res = memberService.getAllPendingSynchronizeMember(policyId);
				 
				 if (res != null){
					 for (Iterator iterator = res.iterator(); iterator.hasNext();) {
						Object object = (Object) iterator.next();
						System.out.println(object);
						if (object instanceof Member){
							result.add(((Member) object).getMemberId());
						}
					}
				 }
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Integer> getGetPendingSynchronizeEmployeeList(
			Integer policyId, String username, String password) {
		// TODO Auto-generated method stub
		Collection<Integer> result = new Vector<Integer>();
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			
			if (login != null){
				 Collection<Object> res = memberService.getAllPendingSynchronizeEmployee(policyId);
				 
				 if (res != null){
					 for (Iterator iterator = res.iterator(); iterator.hasNext();) {
						Object object = (Object) iterator.next();
						System.out.println(object);
						if (object instanceof Member){
							result.add(((Member) object).getMemberId());
						}
					}
				 }
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ActionResult authorizeMemberEDCSys(String cardNumber,
			String memberNumber, String serviceType, String merchantId,
			String terminalId) {

		ActionResult result = new ActionResult();
		result.setResult(false);

		try {
			// check clausul dulu.. jika tidak ada maka default true;

			
			Provider provider = providerService.getByProviderCode(merchantId);
			Member member = memberService.getMemberByCardNumber(cardNumber);

			CaseCategory caseCategory = caseCategoryService.getCaseCategory(serviceType);
			
			
			
			if (member != null && member.getStatus().intValue() == SubscriptionStatus.ACTIVE) {
				Policy activePolicy = policyService.getGroupActivePolicy(member.getMemberGroupId().getMemberGroupId(), member.getMemberGroupId().getClientId().getClientId());
				
				// CHECK RC 28
				if (activePolicy != null ){
					if (activePolicy.getCardTypeId() != null && activePolicy.getCardTypeId().getCardTypeId().intValue() == CardType.SHOW_CARD){
						result.setResult(false);
						result.setAdditionalMessage("NONSWIPE");
						return result;
					}

					// PASS RC 28
					
					// CHECK RC 29
					
					if (activePolicy.getCardTypeId() != null && (activePolicy.getCardTypeId().getCardTypeId().intValue() == CardType.SMART_CARD || 
							activePolicy.getCardTypeId().getCardTypeId().intValue() == CardType.SWIPE_CARD)){
						
						if (activePolicy.getProviderAllocationType().intValue() == Policy.PROVIDER_CLIENT_USAGE_TYPE){
//							String[] eqProviderParam = {"clientId.clientId","providerId.providerId"};
//							Object[] eqProviderValue = {member.getClientId().getClientId(),provider.getProviderId()};
//							
//							int total = clientProviderService.getTotal(null,null,eqProviderParam,eqProviderValue);
//							
//							if (total == 0){
//								result.setResult(false);
//								result.setAdditionalMessage("NOTPROVIDER");
//								return result;
//							}
						}
						else if (activePolicy.getProviderAllocationType().intValue() == Policy.PROVIDER_GROUP_USAGE_TYPE){
							String[] eqProviderParam = {"memberGroupId.memberGroupId","providerId.providerId"};
							Object[] eqProviderValue = {member.getMemberGroupId().getMemberGroupId(),provider.getProviderId()};
							
							int total = memberGroupProviderService.getTotal(null,null,eqProviderParam,eqProviderValue);
							
							if (total == 0){
								result.setResult(false);
								result.setAdditionalMessage("NOTPROVIDER");
								return result;
							}
						}
						else if (activePolicy.getProviderAllocationType().intValue() == Policy.PROVIDER_MEMBER_USAGE_TYPE){
							String[] eqProviderParam = {"memberId.memberId","providerId.providerId"};
							Object[] eqProviderValue = {member.getMemberId(),provider.getProviderId()};
							
							int total = memberProviderService.getTotal(null,null,eqProviderParam,eqProviderValue);
							
							if (total == 0){
								result.setResult(false);
								result.setAdditionalMessage("NOTPROVIDER");
								return result;
							}
						}
					}
				}

				

				
				

				Collection<MemberBenefit> memberBenefitList = memberBenefitService
						.getMemberBenefitList(member.getMemberId(),
								caseCategory.getCaseCategoryId());

				String responseLine = "";
				int index = 0;
				
				String[] eqProductParam = {"memberId.memberId","productId.caseCategory.caseCategoryId","deletedStatus","memberProductStatus.statusId"};
				Object[] eqProductValue = {member.getMemberId(),caseCategory.getCaseCategoryId(),Integer.valueOf(0),SubscriptionStatus.ACTIVE};
				
				Collection<MemberProduct> productList = memberProductService.search(null,null,eqProductParam,eqProductValue,0,1);
				
				if (productList != null){
					Iterator<MemberProduct> iterator = productList.iterator();
					if (iterator.hasNext()){
						MemberProduct memberProduct = iterator.next();
						if (memberProduct != null && memberProduct.getAnnualBenefit() != null && memberProduct.getActualBenefitLimit() != null){
							if (memberProduct.getAnnualBenefit().intValue() != -1 && memberProduct.getActualBenefitLimit().doubleValue() == 0.0){
								responseLine = "EMPTYBENEFIT";
								result.setResult(false);
								result.setAdditionalMessage(responseLine);
								
								return result;
								
							}
						}
					}
				}
				
				

				if (memberBenefitList != null && memberBenefitList.size() > 0) {
					Vector<String> sendItemList = new Vector<String>();
					
					for (Iterator iterator = memberBenefitList.iterator(); iterator
							.hasNext();) {

						MemberBenefit memberBenefit = (MemberBenefit) iterator
								.next();

						if (memberBenefit != null
								&& memberBenefit.getIsEDCEnabled() != null && memberBenefit.getIsEDCEnabled().booleanValue()) {
							String itemCategoryName = "";
							
							if (memberBenefit.getItemCategoryId().getItemCategoryEDCName() != null){
								itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryEDCName();
							}
							else {
								itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryName();
							}
							if (index == 0) {
								responseLine += itemCategoryName
										+ ","
										+ memberBenefit.getItemCategoryId()
										.getItemCategoryCode();
								
								if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
									if (memberBenefit.getBenefitLimit() != null){
										if (memberBenefit.getBenefitLimit() == -1){
											responseLine += ", Sesuai Tgh"; 
										}
										else {											
											String limitSend = StringUtil.convertEDCNumberWithDecimal(memberBenefit.getCurrentBenefitPosition().doubleValue(), 9);											
											responseLine += "," + limitSend;
										}
									}
								}
								else {
									if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ", Sesuai Tgh"; 
											}
											else {												
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == BenefitUsageType.ANNUAL){
													String limitSend = StringUtil.convertEDCNumberWithDecimal(memberBenefit.getCurrentBenefitPosition().doubleValue(), 9);												
													responseLine += "," + limitSend;
												}
												else {
													String limitSend = StringUtil.convertEDCNumberWithDecimal(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;
												}
												
											}
										}
									}
									else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
										double currentBenefit = 0;
										double totalBenefit = -1;
										double benefitUsage = 0;
										
										if (memberBenefit.getBenefitLimit() != null){
											totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
										}
										if (memberBenefit.getCurrentBenefitPosition() != null){
											currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
										}
										if (memberBenefit.getBenefitUsage() != null){
											benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
										}
										
										if (totalBenefit == -1){
											responseLine += ", Sesuai Bnf";
										}
										else {
											currentBenefit = totalBenefit - benefitUsage;
											double percentage = currentBenefit/totalBenefit*100;
											
											
											
											if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
												if (percentage < 10){
													responseLine += ",LMT <10%";
												}
												else if (percentage >= 10 && percentage < 30){
													responseLine += ",LMT <30%";
												}
												else if (percentage >= 30 && percentage < 50){
													responseLine += ",LMT <50%";
												}
												else {
													responseLine += ", Sesuai Bnf";
												}		
											}
											else {
												responseLine += ", Sesuai Bnf";
											}
										}
									}
								}
							} else {
								responseLine += "|" + itemCategoryName
								+ ","
								+ memberBenefit.getItemCategoryId()
								.getItemCategoryCode();
								
								if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
									if (memberBenefit.getBenefitLimit() != null){
										if (memberBenefit.getBenefitLimit() == -1){
											responseLine += ", Sesuai Tgh"; 
										}
										else {											
											String limitSend = StringUtil.convertEDCNumberWithDecimal(memberBenefit.getBenefitLimit().doubleValue(), 9);											
											responseLine += "," + limitSend;
										}
									}
								}
								else {
									if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ", Sesuai Tgh"; 
											}
											else {												
												String limitSend = StringUtil.convertEDCNumberWithDecimal(memberBenefit.getBenefitLimit().doubleValue(), 9);												
												responseLine += "," + limitSend;
											}
										}
									}
									else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
										double currentBenefit = 0;
										double totalBenefit = -1;
										double benefitUsage = 0;
										
										if (memberBenefit.getBenefitLimit() != null){
											totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
										}
										if (memberBenefit.getCurrentBenefitPosition() != null){
											currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
										}					
										if (memberBenefit.getBenefitUsage() != null){
											benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
										}
										if (totalBenefit == -1){
											responseLine += ", Sesuai Bnf";
										}
										else {
											currentBenefit = totalBenefit - benefitUsage;

											double percentage = currentBenefit/totalBenefit*100;
											
											if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
												if (percentage < 10){
													responseLine += ",LMT <10%";
												}
												else if (percentage >= 10 && percentage < 30){
													responseLine += ",LMT <30%";
												}
												else if (percentage >= 30 && percentage < 50){
													responseLine += ",LMT <50%";
												}
												else if (percentage == 0){
													responseLine += ", Bnf Habis";													
												}
												else {
													responseLine += ", Sesuai Bnf";
												}		
											}
											else {
												responseLine += ", Sesuai Bnf";
											}
										}
									}
								}
							}
						}
						index++;
					}
					
					Case theCase = new Case();
					theCase.setCaseCategoryId(caseCategory);
					theCase.setMemberId(member);
					theCase.setCaseStartTime(new java.sql.Date(System.currentTimeMillis()));
					if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
						theCase.setCaseEndTime(new java.sql.Date(System.currentTimeMillis()));
					}
					else {
						DateTime dt = new DateTime();
						DateTime endTime = dt.plusDays(4);
						
						theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
					}
					
					if (provider != null){
						theCase.setProviderId(provider);
					}
				
					ActionUser actionUser = new ActionUser();
					User user = new User();
					user.setUsername(merchantId);
					actionUser.setUser(user);
					CaseStatus status = new CaseStatus();
					status.setCaseStatusId(CaseStatus.CASE_OPEN);
					theCase.setCaseType(Case.CASE_EDC);
					theCase.setCaseStatusId(status);
					
					Case newCase = caseService.create(theCase, actionUser);
					responseLine = newCase.getCaseNumber()+"#"+responseLine;
					
					//theCase.setProviderId(pro)
					result.setResult(true);
				}
				else {
					//
					result.setResult(false);
					responseLine = "NOTCOVERED";
				}

				result.setAdditionalMessage(responseLine);
				
			}
			else {
				result.setResult(false);
				int status = member.getStatus().intValue();
				if (status == SubscriptionStatus.RESIGNED || status == -2){
					result.setAdditionalMessage("RESIGNED");
				}
				else if (status == SubscriptionStatus.TERMINATED ){
					result.setAdditionalMessage("TERMINATED");
				}
				else if (status == SubscriptionStatus.BLOCKED || status == SubscriptionStatus.INACTIVE){
					result.setAdditionalMessage("BLOCKED");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Collection<Integer> getPendingDataImportList(String username,
			String password) {
		// TODO Auto-generated method stub
		
		Collection<Integer> result = new Vector<Integer>();
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			if (login != null){
				String[] eqParam = {"status", "deletedStatus"};
				Object[] eqValue = {DataImportStage.STATUS_OPEN,0};
				
				int total = dataImportStageService.getTotal(null,null,eqParam,eqValue);
				
				Collection<DataImportStage> res = dataImportStageService.search(null,null,eqParam,eqValue,0,total);
				
				for (Iterator iterator = res.iterator(); iterator.hasNext();) {
					DataImportStage dataImportStage = (DataImportStage) iterator
							.next();
					if (dataImportStage != null){
						result.add(dataImportStage.getId());
					}					
				}
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String processDataImport(Integer id, String username, String password) {
		// TODO Auto-generated method stub
		String result = "";
		
		try {
			User login = userService.loginMobile(username, password, "", "");
			if (login != null){
				ActionUser user = new ActionUser();
				user.setUser(login);
				boolean res = dataImportStageService.processUpload(id, user);
			
				if (res){
					result = "TRUE";
				}
				else {
					result = "FALSE";
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateExpireStatus() throws Exception {
		// TODO Auto-generated method stub
		boolean result = memberService.updateExpiredMember();		
		
		return result;
	}

	

}
