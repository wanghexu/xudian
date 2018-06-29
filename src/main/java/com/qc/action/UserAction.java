package com.qc.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qc.entity.User;
import com.qc.service.Userservice;

import net.sf.json.JSONObject;

/**
 * 用户控制层
 *  whx
 */

@Controller
public class UserAction{

	 @Autowired
	   private Userservice userservice;
	/**
	 * 验证是否存在该用户
	 * @return
	 */
	@RequestMapping(value="registuser",produces={"text/html;charset=UTF-8"},method=RequestMethod.POST)
	public @ResponseBody String registuser(@RequestParam("code") String code,@RequestParam("imgurl") String imgurl,@RequestParam("username") String username){
		User user=new User();
		String userid="0";
		//获取openid并赋值
		String openid =Getopenid(code);
		user.setUserkey(openid);
		user.setImgurl(imgurl);
		user.setUsername(username);
		if(userservice.registuser(openid)){
			try {
				if(userservice.adduser(user))
				{
					userid=String.valueOf(userservice.getbackid(openid));
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			
			try {
				//返回用户id并更新用户名字
				userid = String.valueOf(userservice.getbackid(openid));
				userservice.updateusername(openid,username);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return userid;
	}
	
	/**
	 * 验证是否存在该用户
	 * @return
	 */
	@RequestMapping(value="gettheuser",produces={"text/html;charset=UTF-8"},method=RequestMethod.POST)
	public @ResponseBody String gettheuser(@RequestParam("theuserid") int theuserid){
		
		User user=userservice.getuser(theuserid);
		String userlist =JSONObject.fromObject(user).toString();
		return userlist;
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
	

	
  //java请求获取userkey openid 微信用户唯一标识符
	 public  String Getopenid(String code) {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";//获取请求数据
	        String openid="";//获取openid
	        String appId = "wx9ceaacff076a255d";//小程序开发id
	        String secret = "0fbf7914946244587ed6a6d35f7289b2";//小程序开发密码
	        
	        System.out.println("测连接成功,密钥是"+code);
	        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+secret+"&js_code="+ code +"&grant_type=authorization_code";
	        try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = realUrl.openConnection();
	            // 设置通用的请求属性 注意Authorization生成
	            // conn.setRequestProperty("Content-Type",
	            // "application/x-www-form-urlencoded");
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"utf-8"));
	            // 发送请求参数
	            //out.print(param);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream(),"utf-8"));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	            JSONObject jsStr = JSONObject.fromObject(result); //将字符串转成json
	             openid = jsStr.getString("openid");//获取openid的值
	            System.out.println("请求成功，openid信息是"+openid);
	            //ServletActionContext.getResponse().getWriter().println(result);
	        } catch (Exception e) {
	            System.out.println("发送 POST 请求出现异常！" + e);
	            e.printStackTrace();
	        }
	        // 使用finally块来关闭输出流、输入流
//	        finally {
//	            try {
//	                if (out != null) {
//	                    out.close();
//	                }
//	                if (in != null) {
//	                    in.close();
//	                }
//	            } catch (IOException ex) {
//	                ex.printStackTrace();
//	            }
//	        }
	        return openid;
	    }


}
