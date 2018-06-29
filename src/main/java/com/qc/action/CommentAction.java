package com.qc.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qc.entity.Comment;
import com.qc.entity.Fankui;
import com.qc.service.Commentservice;
import com.qc.utils.DateUtils;

import net.sf.json.JSONArray;

/**
 * 说说动态控制层
 *  whx
 */

@Controller
public class CommentAction{

	@Autowired
	Commentservice commentservice;
	/**
	 * 增加说说动态
	 * @return
	 */
	@RequestMapping(value="addcom",produces={"text/html;charset=UTF-8"},method=RequestMethod.POST)
	public @ResponseBody String addcom(Comment com){  
		
		com.setComtime(DateUtils.getNowDate());
		//保存到数据库
		try {
			if(commentservice.addcom(com))
			{
				return "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 增加反馈动态
	 * @return
	 */
	@RequestMapping(value="addfankui",produces={"text/html;charset=UTF-8"},method=RequestMethod.POST)
	public @ResponseBody String addfankui(Fankui fk){  
		
		fk.setFktime(DateUtils.getNowDate());
		//保存到数据库
		try {
			if(commentservice.addfankui(fk))
			{
				return "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	 /**
      * 通过一个bean来接收
      * @param user
      * @return
      */
//    @RequestMapping("/addUser")
//    public String addUser(User user) {
//        System.out.println("username is:"+user.getUsername());
//        System.out.println("password is:"+user.getPassword());
//        return "index";
//    }
	
	/**
	 * 查询全部说说
	 * @param com
	 * @return
	 */
	
	
	@RequestMapping(value="getallcom",produces={"text/html;charset=UTF-8"},method=RequestMethod.POST)
	public @ResponseBody String getallcom(){  
		//接收全部说说集合
		String coms ="";
		//查询
		try {
			List<Comment> List =commentservice.getallcom();
			coms =JSONArray.fromObject(List).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return coms;
	}
	
	/**
	 * 查询个人说说
	 * @param com
	 * @return
	 */
	
	
	@RequestMapping(value="getcom",produces={"text/html;charset=UTF-8"},method=RequestMethod.POST)
	public @ResponseBody String getcom(@RequestParam("userid") int userid){  
		
		//根据userid查询
		
		
		return null;
	}



}
