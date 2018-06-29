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
@Table(name = "fankui")
public class Fankui {
	//属性级别注解,一定要有id，GeneratedValue主键生成，如果是int自增则使用：@GeneratedValue(strategy = GenerationType.AUTO) 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int fkid;
	
	@Column(name = "fktime")
	public String fktime;

	@Column(name = "content")
	public String content;

	public int getFkid() {
		return fkid;
	}

	public void setFkid(int fkid) {
		this.fkid = fkid;
	}

	public String getFktime() {
		return fktime;
	}

	public void setFktime(String fktime) {
		this.fktime = fktime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

    

	
    
}
