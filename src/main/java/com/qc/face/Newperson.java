package com.qc.face;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 增加个体
 * @author Administrator
 *
 */
public class Newperson {

	public static String newperson(String imagePath) throws Exception{
		//增加人脸
		String url= "http://api.youtu.qq.com/youtu/api/newperson";
			
			String sign = Sign.getSign(YouTuAppContants.userQQ,  
			            YouTuAppContants.AppID, YouTuAppContants.SecretID,  
			            YouTuAppContants.SecretKey);

			  byte[] imgData = FileUtil.readFileByBytes(imagePath);  
		        String image =  Base64Util.encode(imgData); 
			
		JSONObject jsonParam = new JSONObject();  
        jsonParam.put("app_id", YouTuAppContants.AppID);  
        jsonParam.put("group_ids","['gdqy']"); 
        jsonParam.put("person_id", "person3");
        jsonParam.put("person_name", "whx"); 
        jsonParam.put("tag","备注"); 
        jsonParam.put("image",image);
		
        System.out.println("dasdasd"+jsonParam.toString().trim());
			String result = HttpUtilYoutu.post(url,jsonParam.toString().trim(),"UTF-8",sign); 
       System.out.println(result);
       
       return result;
	}
	
	//删除个体
	public static String delperson(String userid) throws Exception{
		//删除人脸
		String url= "http://api.youtu.qq.com/youtu/api/delperson";
			
			String sign = Sign.getSign(YouTuAppContants.userQQ,  
			            YouTuAppContants.AppID, YouTuAppContants.SecretID,  
			            YouTuAppContants.SecretKey);
			
		JSONObject jsonParam = new JSONObject();  
        jsonParam.put("app_id", YouTuAppContants.AppID);  
        jsonParam.put("person_id", "person"+userid);
   		
        System.out.println("dasdasd"+jsonParam.toString().trim());
			String result = HttpUtilYoutu.post(url,jsonParam.toString().trim(),"UTF-8",sign); 
       System.out.println(result);
       
       return result;
	}
	
	
	public static void main(String[] args) throws Exception {
		
//		//增加人脸
		String url= "http://api.youtu.qq.com/youtu/api/newperson";
			
			String sign = Sign.getSign(YouTuAppContants.userQQ,  
			            YouTuAppContants.AppID, YouTuAppContants.SecretID,  
			            YouTuAppContants.SecretKey);
                String imagePath="E:/2016061002331.JPG";
			  byte[] imgData = FileUtil.readFileByBytes(imagePath);  
		        String image =  Base64Util.encode(imgData); 
			
		JSONObject jsonParam = new JSONObject();  
        jsonParam.put("app_id", YouTuAppContants.AppID);  
        jsonParam.put("group_ids","['gdqy']"); 
        jsonParam.put("person_id", "person1");
        jsonParam.put("person_name", "王和旭"); 
        jsonParam.put("tag","王和旭 信息技术学院 数媒162学生"); 
        jsonParam.put("image",image);
		
        System.out.println("dasdasd"+jsonParam.toString().trim());
			String result = HttpUtilYoutu.post(url,jsonParam.toString().trim(),"UTF-8",sign); 
       System.out.println(result);   
		
		//删除人脸
//		String url= "http://api.youtu.qq.com/youtu/api/delperson";
//			
//			String sign = Sign.getSign(YouTuAppContants.userQQ,  
//			            YouTuAppContants.AppID, YouTuAppContants.SecretID,  
//			            YouTuAppContants.SecretKey);
//			
//		JSONObject jsonParam = new JSONObject();  
//        jsonParam.put("app_id", YouTuAppContants.AppID);  
//        jsonParam.put("person_id", "person1");
//   		
//        System.out.println("dasdasd"+jsonParam.toString().trim());
//			String result = HttpUtilYoutu.post(url,jsonParam.toString().trim(),"UTF-8",sign); 
//       System.out.println(result);

	}
   
	
}
