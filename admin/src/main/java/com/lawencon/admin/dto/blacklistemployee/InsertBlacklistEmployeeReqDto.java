package com.lawencon.admin.dto.blacklistemployee;

public class InsertBlacklistEmployeeReqDto {
	
	private String employeeId;
	private String companyId;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
