package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.AppliedVacancy;

@Repository
public class AppliedVacancyDao extends AbstractJpaDao {
	
	public AppliedVacancy getById(final Object id) {
		return super.getById(AppliedVacancy.class, id);
	}

	public AppliedVacancy getByIdRef(final Object id) {
		return super.getByIdRef(AppliedVacancy.class, id);
	}

	public AppliedVacancy getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(AppliedVacancy.class, id);
	}
	
	public List<AppliedVacancy> getAll() {
		return super.getAll(AppliedVacancy.class);
	}

	public AppliedVacancy save(AppliedVacancy data) {
		return super.save(data);
	}

	public AppliedVacancy saveAndFlush(AppliedVacancy entity) {
		return super.saveAndFlush(entity);
	}

	public AppliedVacancy saveNoLogin(AppliedVacancy entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(AppliedVacancy.class, entityId);
	}

	public AppliedVacancy getByJobVacancyAndCandidate(String jobId, String candidateId){
		final String sql = "SELECT "
				+ "	tav.id "
				+ "FROM "
				+ "	t_applied_vacancy tav "
				+ "INNER JOIN "
				+ "	t_candidate tc ON tav.candidate_id = tc.id "
				+ "INNER JOIN "
				+ "	t_job_vacancy tjv ON tav.job_vacancy_id = tjv.id "
				+ "WHERE "
				+ "	tc.id LIKE :candidate_id AND tjv.id LIKE :job_id ";
		
		try {
			final Object appJobObj = ConnHandler.getManager().createNativeQuery(sql)
					.setParameter("job_id", jobId)
					.setParameter("candidate_id", candidateId)
					.getSingleResult();

				AppliedVacancy appliedVacancy = new AppliedVacancy();
				appliedVacancy.setId(appJobObj.toString());
			return appliedVacancy;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}