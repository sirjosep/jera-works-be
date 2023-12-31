package com.lawencon.admin.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_job_vacancy")
public class JobVacancy extends BaseEntity {

	@Column(name = "vacancy_code", nullable = false, unique = true, length = 8)
	private String vacancyCode;

	@Column(name = "vacancy_title", nullable = false, length = 36)
	private String vacancyTitle;

	@OneToOne
	@JoinColumn(name = "pic_user_id", nullable = false)
	private User picUser;

	@OneToOne
	@JoinColumn(name = "pic_hr_id", nullable = false)
	private User picHr;

	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;

	@Column(name = "end_date", nullable = false)
	private LocalDateTime endDate;

	@OneToOne
	@JoinColumn(name = "exp_level_id", nullable = false)
	private ExperienceLevel expLevel;

	@OneToOne
	@JoinColumn(name = "available_status_id", nullable = false)
	private AvailableStatus availableStatus;

	@OneToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@OneToOne
	@JoinColumn(name = "vacancy_description_id", nullable = false)
	private VacancyDescription vacancyDescription;

	public String getVacancyCode() {
		return vacancyCode;
	}

	public void setVacancyCode(String vacancyCode) {
		this.vacancyCode = vacancyCode;
	}

	public String getVacancyTitle() {
		return vacancyTitle;
	}

	public void setVacancyTitle(String vacancyTitle) {
		this.vacancyTitle = vacancyTitle;
	}

	public User getPicUser() {
		return picUser;
	}

	public void setPicUser(User picUser) {
		this.picUser = picUser;
	}

	public User getPicHr() {
		return picHr;
	}

	public void setPicHr(User picHr) {
		this.picHr = picHr;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public ExperienceLevel getExpLevel() {
		return expLevel;
	}

	public void setExpLevel(ExperienceLevel expLevel) {
		this.expLevel = expLevel;
	}

	public AvailableStatus getAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(AvailableStatus availableStatus) {
		this.availableStatus = availableStatus;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public VacancyDescription getVacancyDescription() {
		return vacancyDescription;
	}

	public void setVacancyDescription(VacancyDescription vacancyDescription) {
		this.vacancyDescription = vacancyDescription;
	}

}
