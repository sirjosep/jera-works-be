package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.JobType;

@Repository
@Profile(value = { "native-query" })
public class JobTypeDao extends AbstractJpaDao {
	
	public JobType getById(final Object id) {
		return super.getById(JobType.class, id);
	}

	public JobType getByIdRef(final Object id) {
		return super.getByIdRef(JobType.class, id);
	}

	public JobType getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(JobType.class, id);
	}
	
	public List<JobType> getAll() {
		return super.getAll(JobType.class);
	}

	public JobType save(JobType data) {
		return super.save(data);
	}

	public JobType saveAndFlush(JobType entity) {
		return super.saveAndFlush(entity);
	}

	public JobType saveNoLogin(JobType entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(JobType.class, entityId);
	}

}