package com.qc.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qc.dao.Commdao;

/**
 * 封装的公共方法
 * @author whx
 *
 * @param <T>
 */
//自动扫描
@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )	
public abstract class Commdaoimpl<T> implements Commdao<T> {

	 @Autowired//注解
		private HibernateTemplate ht;

	// 当前操作的模块
			private Class<T> clazz;

			public Commdaoimpl(){
				System.out.println("当前运行类：" + this);// 具体的子类
				System.out.println("当前运行字节码：" + this.getClass());
				System.out.println("当前运行类父类：" + this.getClass().getSuperclass());
				System.out.println("当前运行类泛型父类：" + this.getClass().getGenericSuperclass()); // 参数化类型
				
				// 获取当前运行类泛型父类类型，即为参数化类型，有所有类型公用的高级接口Type接收！
				Type type = this.getClass().getGenericSuperclass();
				// 强转为“参数化类型”
				ParameterizedType pt = (ParameterizedType) type; // BaseDao<Department>
				// 获取参数化类型中，实际类型的定义
				Type[] ts = pt.getActualTypeArguments();
				// 转换
				this.clazz = (Class<T>) ts[0];
			}
				
			public void save(T t) {
				
				ht.save(t);		
			}
			public T findById(Serializable id) {
				return ht.get(clazz, id);
			}

			
			public void delete(Serializable id) {
				// 先查询
				T t = findById(id);
				// 再删除
				if (t != null) {
					ht.delete(t);
				}
			}

			
			public List<T> getAll() {
				return ht.loadAll(clazz);
			}
			
			public List<T> getAllbytime() {
				return (List<T>) ht.find("from Comment ORDER BY comtime desc");
			}
			
			public List<T> getByCondition(String hql, Object... parmas) {
				return (List<T>) ht.find(hql,parmas);
			}

			public void update(T t) {
				ht.update(t);
			}
}
