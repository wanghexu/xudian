package com.qc.face;


import net.sf.json.JSONObject;

public class Shibieface {

	public static String detectface(String imagePath) throws Exception {
		//人脸识别魅力值年龄
		String url= "http://api.youtu.qq.com/youtu/api/detectface";
		//api所需请求头
		String sign = Sign.getSign(YouTuAppContants.userQQ,  
	            YouTuAppContants.AppID, YouTuAppContants.SecretID,  
	            YouTuAppContants.SecretKey);
	
		  byte[] imgData = FileUtil.readFileByBytes(imagePath);  
	        String image =  Base64Util.encode(imgData);
	      //定义数组增加请求参数
      JSONObject jsonParam = new JSONObject();
      jsonParam.put("app_id", YouTuAppContants.AppID);  
      jsonParam.put("image",image);
//      jsonParam.put("url", image);
      
      System.out.println("dasdasd"+jsonParam.toString());
      //请求urlapi
//      String result =Newperson.getJsonData(jsonParam, url, sign);
      String result = HttpUtilYoutu.post(url, jsonParam.toString(), "UTF-8",sign);
      System.out.println(result);
      JSONObject jsons = new JSONObject();
      jsons.put("list", result);
      jsons.put("status", "200");
     
      String r=jsons.toString();
      System.out.println(r);
      return jsons.toString();
	}
}
