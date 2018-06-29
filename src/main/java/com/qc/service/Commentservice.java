package com.qc.service;

import java.util.List;

import com.qc.entity.Comment;
import com.qc.entity.Fankui;

/**
 * 说说业务接口
 * @author whx
 *
 */

public interface Commentservice {
     
	 public boolean addcom(Comment com) throws Exception;
	 public boolean addfankui(Fankui fk) throws Exception;
	 public List<Comment> getallcom() throws Exception;
}
