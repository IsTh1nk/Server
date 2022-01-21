package com.think.entity;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Members {
	private final int memberId;
	private final String email;
	private final String password;
	private String profileName;
	private String profileIntro;
	private String profileImage;
	private final int grade;
	private final String dept;
	private final String major;
	private final String minor;
	private final String role;
	private final Date createdDate;
	private final Date modifiedDate;
	private final Boolean enabled;
	
	@Override
	public String toString() {
		return "Members [memberId=" + memberId + ", email=" + email + ", password=" + password + ", profileName="
				+ profileName + ", profileIntro=" + profileIntro + ", profileImage=" + profileImage + ", grade=" + grade
				+ ", dept=" + dept + ", major=" + major + ", minor=" + minor + ", role=" + role + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", enabled=" + enabled + "]";
	}
	
	
}
