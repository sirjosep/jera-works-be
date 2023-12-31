package com.lawencon.admin.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Offering;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class OfferingDao extends AbstractJpaDao {
	
	public Offering getById(final Object id) {
		return super.getById(Offering.class, id);
	}

	public Offering getByIdRef(final Object id) {
		return super.getByIdRef(Offering.class, id);
	}

	public Offering getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Offering.class, id);
	}
	
	public List<Offering> getAll() {
		return super.getAll(Offering.class);
	}

	public Offering save(Offering data) {
		return super.save(data);
	}

	public Offering saveAndFlush(Offering entity) {
		return super.saveAndFlush(entity);
	}

	public Offering saveNoLogin(Offering entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Offering.class, entityId);
	}
	public Offering getByAppliedVacancyId(String appliedVacancyId) {
		final String sql = "SELECT "
				+ " tof.id, tof.is_approve, tof.start_date, tof.end_date, tof.description, tof.offering_location "
				+ " FROM "
				+ " t_offering tof "
				+ " WHERE "
				+ "	tof.applied_vacancy_id = :appliedVacancyId ";
		
		try {
			final Object offObj = ConnHandler.getManager()
					.createNativeQuery(sql)
					.setParameter("appliedVacancyId", appliedVacancyId)
					.getSingleResult();
			
			final Object[] offObjArr = (Object[]) offObj;
			
			final Offering offering = new Offering();
			offering.setId(offObjArr[0].toString());
			offering.setIsApprove(Boolean.valueOf(offObjArr[1].toString()));
			offering.setStartDate(Timestamp.valueOf(offObjArr[2].toString()).toLocalDateTime());
			offering.setEndDate(Timestamp.valueOf(offObjArr[3].toString()).toLocalDateTime());
			offering.setDescription(offObjArr[4].toString());
			offering.setOfferingLocation(offObjArr[5].toString());
			
			return offering;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
