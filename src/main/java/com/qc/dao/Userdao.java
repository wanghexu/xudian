package com.qc.dao;

import com.qc.dao.impl.Commdaoimpl;
import com.qc.entity.User;
/**
 * dao接口
 * @author whx
 *
 */
public interface Userdao extends Commdao<User>{
  
	//查询用户是否存在
	public User registuser(String userkey);
	//更新名字
	public boolean updateusername(String userkey,String username) throws Exception;
	//返回用户id
	public int getbackid(String userkey) throws Exception;
}
