package com.qc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qc.dao.Commentdao;
import com.qc.dao.Fankuidao;
import com.qc.entity.Comment;
import com.qc.entity.Fankui;
import com.qc.service.Commentservice;


/**
 * 新闻业务层
 * @author whx
 *
 */
@Service
@Transactional(readOnly = false)
public class Commenterviceimpl implements Commentservice{

	@Autowired
	Commentdao commentdao;
	@Autowired
	Fankuidao fankuidao;
	//增加说说动态
	public boolean addcom(Comment com) throws Exception {
		// TODO Auto-generated method stub
		commentdao.save(com);
		return true;
	}
	
	public List<Comment> getallcom() throws Exception {
		// TODO Auto-generated method stub
		List<Comment> List =commentdao.getAllbytime();
		return List;
	}

	public boolean addfankui(Fankui fk) throws Exception {
		// TODO Auto-generated method stub
		fankuidao.save(fk);
		return true;
	}

}
