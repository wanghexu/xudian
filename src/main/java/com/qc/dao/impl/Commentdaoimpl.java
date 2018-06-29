package com.qc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.qc.dao.Commentdao;
import com.qc.dao.impl.Commdaoimpl;
import com.qc.entity.Comment;


/**
 * dao实现类
 * @author whx
 *
 */
//自动扫描
@Repository
public class Commentdaoimpl extends Commdaoimpl<Comment> implements Commentdao{

	 @Autowired//注解
	private HibernateTemplate ht;
	 


}
