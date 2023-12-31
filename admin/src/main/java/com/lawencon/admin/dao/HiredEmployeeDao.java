package com.lawencon.admin.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.dto.hiredemployee.HiredAppliedRangeDate;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateProfile;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.HiredEmployee;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class HiredEmployeeDao extends AbstractJpaDao {
	
	public HiredEmployee getById(final Object id) {
		return super.getById(HiredEmployee.class, id);
	}

	public HiredEmployee getByIdRef(final Object id) {
		return super.getByIdRef(HiredEmployee.class, id);
	}

	public HiredEmployee getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(HiredEmployee.class, id);
	}
	
	public List<HiredEmployee> getAll() {
		return super.getAll(HiredEmployee.class);
	}

	public HiredEmployee save(HiredEmployee data) {
		return super.save(data);
	}

	public HiredEmployee saveAndFlush(HiredEmployee entity) {
		return super.saveAndFlush(entity);
	}

	public HiredEmployee saveNoLogin(HiredEmployee entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(HiredEmployee.class, entityId);
	}

	public List<HiredEmployee> getByCompany(String companyId) {
		final String sql = "SELECT "
				+ "	the.id, tc.id as candidateId, tcp.profile_name, tc2.company_name, the.created_at "
				+ "FROM "
				+ "	t_hired_employee the "
				+ "INNER JOIN "
				+ "	t_candidate tc ON the.candidate_id = tc.id "
				+ "INNER JOIN "
				+ "	t_candidate_profile tcp ON tc.candidate_profile_id = tcp.id "
				+ "INNER JOIN "
				+ "	t_company tc2 ON the.company_id = tc2.id "
				+ "WHERE "
				+ "	the.company_id = :companyId";
		
		final List<?> hirObjs = ConnHandler.getManager()
				.createNativeQuery(sql)
				.setParameter("companyId", companyId)
				.getResultList();
		
		final List<HiredEmployee> hiredEmployees = new ArrayList<>();
		
		if (hirObjs.size() > 0) {
			for (Object hirObj:hirObjs) {
				final Object[] hirObjArr = (Object[]) hirObj;
				final HiredEmployee hiredEmployee = new HiredEmployee();
				hiredEmployee.setId(hirObjArr[0].toString());
				hiredEmployee.setCreatedAt(Timestamp.valueOf(hirObjArr[4].toString()).toLocalDateTime());
				
				final Candidate candidate = new Candidate();
				candidate.setId(hirObjArr[1].toString());
				
				final CandidateProfile candidateProfile = new CandidateProfile();
				candidateProfile.setProfileName(hirObjArr[2].toString());
				candidate.setCandidateProfile(candidateProfile);
				
				hiredEmployee.setCandidate(candidate);
				
				final Company company = new Company();
				company.setCompanyName(hirObjArr[3].toString());
				hiredEmployee.setCompany(company);
				
				hiredEmployees.add(hiredEmployee);
			}
		}
		
		return hiredEmployees;
	}
	
	public HiredEmployee getByCandidate(String companyId, String candidateId) {
		final String sql = "SELECT "
				+ "	the.id, the.company_id, the.created_at "
				+ "FROM "
				+ "	t_hired_employee the "
				+ "INNER JOIN "
				+ "	t_candidate tc ON the.candidate_id = tc.id "
				+ "WHERE "
				+ "	the.candidate_id = :candidateId AND the.company_id = :companyId";
		
		try {
			final Object hirObj = ConnHandler.getManager()
					.createNativeQuery(sql)
					.setParameter("candidateId", candidateId)
					.setParameter("companyId", companyId)
					.getSingleResult();

			HiredEmployee employee = null;
			if (hirObj != null) {
				final Object[] hirObjArr = (Object[]) hirObj;

				if(hirObjArr.length > 0) {
					employee = new HiredEmployee();
					employee.setId(hirObjArr[0].toString());
					employee.setCreatedAt(Timestamp.valueOf(hirObjArr[2].toString()).toLocalDateTime());
					
					final Company company = new Company();
					company.setId(hirObjArr[1].toString());
					employee.setCompany(company);
				}
			}
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<HiredAppliedRangeDate> getHiredAppliedRangeDate(String jobVacancyId) {
		final String sql = "SELECT "
				+ "	the.created_at AS hiredAt, tav.created_at AS appliedAt, tcp.profile_name "
				+ "FROM "
				+ "	t_hired_employee the "
				+ "INNER JOIN "
				+ "	t_applied_vacancy tav ON tav.candidate_id = the.candidate_id "
				+ "INNER JOIN "
				+ "	t_candidate tc ON tav.candidate_id = tc.id "
				+ "INNER JOIN "
				+ "	t_candidate_profile tcp ON tc.candidate_profile_id = tcp.id "
				+ "WHERE "
				+ "	tav.job_vacancy_id = :jobVacancyId ";
		
		try {
			final List<?> hiredRangeObjs = ConnHandler.getManager()
											.createNativeQuery(sql)
											.setParameter("jobVacancyId", jobVacancyId)
											.getResultList();
			
			final List<HiredAppliedRangeDate> hiredRanges = new ArrayList<>();
			
			if(hiredRangeObjs.size() > 0) {
				for (Object hiredRangeObj : hiredRangeObjs) {
					final Object[] hiredRangeArr = (Object[]) hiredRangeObj;
					final HiredAppliedRangeDate hiredRange = new HiredAppliedRangeDate();
					
					hiredRange.setAppliedAt(Timestamp.valueOf(hiredRangeArr[0].toString()).toLocalDateTime());
					hiredRange.setHiredAt(Timestamp.valueOf(hiredRangeArr[1].toString()).toLocalDateTime());
					hiredRange.setProfileName(hiredRangeArr[2].toString());
					hiredRanges.add(hiredRange);
				}
			}
			return hiredRanges;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}