package com.qc.face;

import net.sf.json.JSONObject;

public class Ronghe {

	public static String ronghe(String model,String imagePath) throws Exception {
		
		//增加人脸
	     String url= "http://api.youtu.qq.com/cgi-bin/pitu_open_access_for_youtu.fcg";
		// String imagePath="E:/whx.jpg";			
	     String sign = Sign.getSign(YouTuAppContants.userQQ,  
		            YouTuAppContants.AppID, YouTuAppContants.SecretID,  
		            YouTuAppContants.SecretKey);

		 byte[] imgData = FileUtil.readFileByBytes(imagePath);  
		 String image =  Base64Util.encode(imgData); 
		 
			
			JSONObject jsonParam = new JSONObject();  
	        jsonParam.put("img_data",image);  
	        jsonParam.put("rsp_img_type","url"); 
	        jsonParam.put("opdata", "[{'cmd': 'doFaceMerge','params':{'model_id': '"+model+"'}}]");
	       // jsonParam.put("opdata", "[{'cmd': 'doFaceMerge','params':{'model_id': 'cf_yuren_jirou'}}]");
	        System.out.println("参数字符串："+jsonParam.toString().trim());
			String result = HttpUtilYoutu.post(url,jsonParam.toString().trim(),"UTF-8",sign); 
	       System.out.println(result);
	       //取出缩略图
	       JSONObject json= JSONObject.fromObject(result);
			String ss=json.get("img_url_thumb").toString();
	       return ss;
	}
//	public static void main(String[] args)throws Exception  {
//		JSONObject json= JSONObject.fromObject(ronghe("cf_yuren_jirou"));
//		String ss=json.get("img_url_thumb").toString();
//		System.out.println(ss);
//	}
}
