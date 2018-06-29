package com.qc.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.qc.dao.Userdao;
import com.qc.entity.User;
import com.qc.dao.impl.Commdaoimpl;


/**
 * dao实现类
 * @author whx
 *
 */
//自动扫描
@Repository
public class Userdaoimpl extends Commdaoimpl<User> implements Userdao{

	 @Autowired//注解
	private HibernateTemplate ht;
	 
	 
		public User registuser(String userkey) {
			// TODO Auto-generated method stub
			DetachedCriteria criteria=DetachedCriteria.forClass(User.class);
			//��map��name value����Ҫʵ�ã����߼����������Ҫ��̬��ѯ�ֶε������ֵ�Ĳ����������ѯ�б�ȥ��ѯ
			criteria.add(Restrictions.eq("userkey", userkey));
			List<User> userList=(List<User>)ht.findByCriteria(criteria);
			if(userList.size()>0)
				return userList.get(0);
			else 
				return null;
		}

		
		public boolean updateusername(String userkey, String username) throws Exception {
			// TODO Auto-generated method stub
			List<User> userlist= (List<User>)ht.find("from User user where userkey='"+userkey+"'");
			int id=userlist.get(0).getUserid();
			System.out.println("id��"+id);
			//�ж������Ƿ���ͬ����ͬ�����޸�,��ͬ�޸�
			if(userlist.get(0).getUsername()!=username)
			{
			  User user = ht.get(User.class, id);
			  //�޸�username
			  user.setUsername(username);
			  ht.update(user);
			}
			
			return true;
		}

		
		public int getbackid(String userkey) throws Exception {
			// TODO Auto-generated method stub
			List<User> userlist= (List<User>)ht.find("from User user where userkey='"+userkey+"'");
			int id=userlist.get(0).getUserid();
			return id;
		}


}
