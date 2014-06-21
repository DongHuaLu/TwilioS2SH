package com.dolph.Dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dolph.Dao.RecordDao;
import com.dolph.model.Record;
import com.dolph.model.User;

@Repository("recordDao")
public class RecordDaoImpl extends BaseDaoImpl implements RecordDao {

	@Override
	public List<Record> findAllRecordByUser(User currentUser) {
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.DATE, -14);
		Date startDate = cl.getTime();
		String hql = "select r from Record r where r.owner=? and r.startTime > ? order by r.startTime desc";
		return getSession().createQuery(hql).setParameter(0, currentUser)
				.setParameter(1, startDate).list();
	}

	@Override
	public boolean deleteRecord(int recordId, User currentUser) {
		String hql = "delete from Record r where r.id=? and r.owner=?";
		try {
			getSession().createQuery(hql).setParameter(0, recordId)
					.setParameter(1, currentUser).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
