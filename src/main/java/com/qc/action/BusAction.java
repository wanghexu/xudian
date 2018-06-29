package com.qc.action;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qc.utils.Busutil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 获取校车
 * @author 王和旭
 *
 */
@Controller
public class BusAction {

	//登录
	@RequestMapping(value="buslogin",produces={"text/html;charset=UTF-8"},method = RequestMethod.POST)
	public @ResponseBody String buslogin(@RequestParam("username") String username,@RequestParam("password") String password){
		
		Busutil busutil =new Busutil();
		String title=busutil.login(username, password);
		JSONObject jsonParam= new JSONObject();
		//排查登录失败，登录失败是null,欢迎您！，长度是9
		if(title.length()!=9)
		{
			//定义接收
			 String buslist=busutil.getallbus();
		        jsonParam.put("title", title);
		        jsonParam.put("ticket", buslist);
		}else{
			jsonParam.put("ticket", "0");
		}			
		return jsonParam.toString();
	}
	
	//订票
	@RequestMapping(value="takebus",produces={"text/html;charset=UTF-8"},method = RequestMethod.POST)
	public @ResponseBody String takebus(@RequestParam("train_id") String train_id,@RequestParam("phone") String phone){
		
		Busutil busutil =new Busutil();
		String data=busutil.takebus(train_id, phone);
		System.out.println(data);
//		JSONObject jsonParam= new JSONObject();;
//		//检查返回值
//		if(data=="1")
//		{
//			//定义接收
//		    jsonParam.put("tip","1");
//		}else{
//			//定义接收
//		    jsonParam.put("tip","fail");
//		}			
		return data.trim();
	}
	//取消校车
	@RequestMapping(value="cancelbus",produces={"text/html;charset=UTF-8"},method = RequestMethod.POST)
	public @ResponseBody String cancelbus(@RequestParam("train_id") String train_id){
		
		Busutil busutil =new Busutil();
		String data=busutil.canclebus(train_id);
		System.out.println(data);
			
		return data.trim();
	}
	
	//已经预定记录
	@RequestMapping(value="havebus",produces={"text/html;charset=UTF-8"},method = RequestMethod.POST)
	public @ResponseBody String havebus(@RequestParam("stu_id") String stu_id){
		
		Busutil busutil =new Busutil();
		String data=busutil.havebus(stu_id);
		System.out.println(data);
			
		return data;
	}
	
	//无参自动预定记录
	@RequestMapping(value="havebusnopa",produces={"text/html;charset=UTF-8"},method = RequestMethod.POST)
	public @ResponseBody String havebusnopa(){
		
		Busutil busutil =new Busutil();
		String data=busutil.havebus_noparam();
		System.out.println(data);
			
		return data;
	}
	
}
