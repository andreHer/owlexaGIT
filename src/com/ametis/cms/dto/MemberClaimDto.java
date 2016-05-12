package com.ametis.cms.dto;

import java.io.Serializable;
import java.util.Collection;

import com.ametis.cms.datamodel.Member;

public class MemberClaimDto implements Serializable{

	private Member member;
	private Collection<ClaimDto> claimDto;
	
	public MemberClaimDto(){}
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Collection<ClaimDto> getClaimDto() {
		return claimDto;
	}
	public void setClaimDto(Collection<ClaimDto> claimDto) {
		this.claimDto = claimDto;
	}
	
	
}
