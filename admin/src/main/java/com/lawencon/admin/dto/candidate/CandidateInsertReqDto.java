package com.lawencon.admin.dto.candidate;

public class CandidateInsertReqDto {

	private String candidateEmail;
	private String candidatePassword;
	private String profileName;
	private String profileCode;
	private String candidateCode;

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public String getCandidateCode() {
		return candidateCode;
	}

	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getCandidatePassword() {
		return candidatePassword;
	}

	public void setCandidatePassword(String candidatePassword) {
		this.candidatePassword = candidatePassword;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

}
