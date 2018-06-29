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
@Table(name = "comment")
public class Comment {
	//属性级别注解,一定要有id，GeneratedValue主键生成，如果是int自增则使用：@GeneratedValue(strategy = GenerationType.AUTO) 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int cmid;
	
	@Column(name = "userid")
	public int userid;
	
	@Column(name = "imgpath")
	public String imgpath;
	
	@Column(name = "comtime")
	public String comtime;

	@Column(name = "content")
	public String content;
	
	@Column(name = "imgurl")
	public String imgurl;

	@Column(name = "username")
	public String username;
	
	public String getComtime() {
		return comtime;
	}

	public void setComtime(String comtime) {
		this.comtime = comtime;
	}

	public int getCmid() {
		return cmid;
	}

	public void setCmid(int cmid) {
		this.cmid = cmid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


    

	
    
}
