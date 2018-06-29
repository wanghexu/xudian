package com.qc.service;

import com.qc.entity.User;

/**
 * 业务接口
 * @author whx
 *
 */
public interface Userservice {

	 public boolean adduser(User user) throws Exception;
	 public boolean registuser(String userkey);
	 public boolean updateusername(String userkey,String username) throws Exception;
	 public int getbackid(String userkey) throws Exception;
	 public  User getuser(int userid);
}
