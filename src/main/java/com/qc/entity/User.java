package com.qc.entity;


import javax.persistence.*;

import lombok.Data;

/**
 * 实体类
 * @author whx
 *
 */
//类级别注解
@Entity
@Table(name = "user")
public class User {
	//属性级别注解,一定要有id，GeneratedValue主键生成，如果是int自增则使用：@GeneratedValue(strategy = GenerationType.AUTO) 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int userid;
	
	@Column(name = "username")
	public String username;
	
	@Column(name = "userkey")
	public String userkey;
	
	@Column(name = "imgurl")
	public String imgurl;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserkey() {
		return userkey;
	}

	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
    

	
    
}
