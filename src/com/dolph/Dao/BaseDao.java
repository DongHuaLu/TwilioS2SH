package com.dolph.Dao;

import java.util.List;

import com.dolph.vo.PageVo;

public interface BaseDao {
	public void save(Object entity);

	public void saveorUpdate(Object entity);

	public void dalete(Object entity);

	public void update(Object entity);

	public <T> T findById(Class<T> entityClass, int id);

	public <T> List<T> findAll(Class<T> entityClass);

	public PageVo findPaging(String hql, int offset, int pagesize,
			Object... params);

	public PageVo findPaging(String hql, Object... params);
}
