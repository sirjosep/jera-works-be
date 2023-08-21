package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.InterviewVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.interviewvacancy.InsertInterviewVacancyReqDto;
import com.lawencon.admin.dto.interviewvacancy.InterviewVacancyResDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.InterviewVacancy;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;

@Service
public class InterviewVacancyService {
	
	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	@Autowired
	private InterviewVacancyDao interviewDao;
	@Autowired
	private SendMailService mailService;

	public InsertResDto insertInterview(InsertInterviewVacancyReqDto data) {
		
		ConnHandler.begin();
		final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(data.getAppliedVacancyId());
		final InterviewVacancy interview = new InterviewVacancy();
		interview.setAppliedVacancy(appliedVacancy);
		interview.setEndDate(DateUtil.dateParse(data.getEndDate()));
		interview.setInterviewLocation(data.getInterviewLocation());
		interview.setNotes(data.getNotes());
		interview.setStartDate(DateUtil.dateParse(data.getStartDate()));
		final InterviewVacancy interviewDb = interviewDao.saveAndFlush(interview);
		
		ConnHandler.commit();
		
//		mailService.sendEmail(appliedVacancy.getCandidate().getCandidateEmail());
		
		final InsertResDto response = new InsertResDto();
		response.setId(interviewDb.getId());
		response.setMessage("Interview has been created!");
		
		return response;
		
	}
	
	public InterviewVacancyResDto getByAppliedId(String appliedVacancyId) {
		final InterviewVacancy interviewVacancy = interviewDao.getByAppliedVacancyId(appliedVacancyId);
		
		final InterviewVacancyResDto response = new InterviewVacancyResDto();
		response.setStartDate(DateUtil.dateFormat(interviewVacancy.getStartDate()));
		response.setEndDate(DateUtil.dateFormat(interviewVacancy.getEndDate()));
		response.setNotes(interviewVacancy.getNotes());
		response.setLocation(interviewVacancy.getInterviewLocation());
		
		return response;
	}
}