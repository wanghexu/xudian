package com.qc.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 公共类dao，封装了增删改查
 * @author whx
 *
 */
public interface Commdao<T> {

	// 保存
	public void save(T t);
	// 更新
	public void update(T t);
	// 删除
	public void delete(Serializable id);
	// 主键查询
	public T findById(Serializable id);
	// 查询全部
	public List<T> getAll();
	// 查询全部根据时间
	public List<T> getAllbytime();
	// 条件条件
	public List<T> getByCondition(String hql,Object...parmas);
}
