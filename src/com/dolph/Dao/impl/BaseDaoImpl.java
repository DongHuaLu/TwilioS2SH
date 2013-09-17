package com.dolph.Dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dolph.SystemContext;
import com.dolph.Dao.BaseDao;
import com.dolph.vo.PageVo;

public class BaseDaoImpl implements BaseDao {
	private SessionFactory sessionFactroy;

	public Session getSession() {
		return sessionFactroy.getCurrentSession();
	}

	public void save(Object entity) {
		getSession().save(entity);
	}

	public void dalete(Object entity) {
		getSession().delete(entity);
	}

	public void update(Object entity) {
		getSession().update(entity);
	}

	public <T> T findById(Class<T> entityClass, int id) {
		return (T) getSession().load(entityClass, id);
	}

	public <T> List<T> findAll(Class<T> entityClass) {
		return getSession().createCriteria(entityClass).list();
	}

	public PageVo findPaging(String hql, int offset, int pagesize,
			Object... params) {
		String countHql = getCountHql(hql);
		Query query = getSession().createQuery(countHql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		Long total = (Long) query.uniqueResult();
		query = getSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}

		query.setFirstResult(offset);// 从第几条开始
		query.setMaxResults(pagesize);// 最多取几条
		List datas = query.list();

		PageVo pageVo = new PageVo();
		pageVo.setTotal(total);
		pageVo.setDatas(datas);
		return pageVo;
	}

	private String getCountHql(String hql) {
		int index = hql.indexOf("from");
		if (index == -1) {
			throw new RuntimeException("hql语句有误");
		} else
			return "select count(*)" + hql.substring(index);
	}

	@Resource
	public void setSessionFactroy(SessionFactory sessionFactroy) {
		this.sessionFactroy = sessionFactroy;
	}

	public PageVo findPaging(String hql, Object... params) {
		return findPaging(hql, SystemContext.getOffset(),
				SystemContext.getPagesize(), params);
	}

	public void saveorUpdate(Object entity) {
		getSession().saveOrUpdate(entity);
	}

}
