package com.qc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qc.dao.Userdao;
import com.qc.entity.User;
import com.qc.service.Userservice;

/**
 * 用户业务实现类
 * @author whx
 *
 */
@Service
@Transactional(readOnly = false)
public class Userserviceimpl implements Userservice{

	@Autowired//注解
	private Userdao userdao;

	public boolean adduser(User user) throws Exception {
		// TODO Auto-generated method stub
		userdao.save(user);
		return true;
	}

    /**
	 * ͨ
	 * @param whx
	 * @return
	 */
	public boolean registuser(String userkey){
		if(userdao.registuser(userkey)!=null)
				return false;
		return true;
	}
	/**
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public boolean updateusername(String userkey,String username) throws Exception{
		
		try{
			
			if(this.userdao.updateusername(userkey, username));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//返回用户id
	public int getbackid(String userkey) throws Exception{
		return this.userdao.getbackid(userkey);
	}

	public User getuser(int userid) {
		// TODO Auto-generated method stub
		return this.userdao.findById(userid);
	}

	
}
