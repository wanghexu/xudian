package com.qc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.qc.dao.Fankuidao;
import com.qc.dao.impl.Commdaoimpl;
import com.qc.entity.Fankui;


/**
 * dao实现类
 * @author whx
 *
 */
//自动扫描
@Repository
public class Fankuidaoimpl extends Commdaoimpl<Fankui> implements Fankuidao{

	 @Autowired//注解
	private HibernateTemplate ht;
	 


}
