package com.qc.face;


import net.sf.json.JSONObject;

public class Addpersonface {

	public static void main(String[] args) throws Exception {
		//增加人脸
		String url= "http://api.youtu.qq.com/youtu/api/addface";
		//api所需请求头
		String sign = Sign.getSign(YouTuAppContants.userQQ,  
	            YouTuAppContants.AppID, YouTuAppContants.SecretID,  
	            YouTuAppContants.SecretKey);
		
		String imagePath = "E:/whx1.jpg";
		  byte[] imgData = FileUtil.readFileByBytes(imagePath);  
	        String image =  Base64Util.encode(imgData);
	        //定义数组增加备注信息，用于存储用户信息
	        JSONObject jsonParam1 = new JSONObject();
	        jsonParam1.put("name", "王和旭");
	        jsonParam1.put("age", "21");
	        jsonParam1.put("school", "广东轻院信息技术学院");
	        jsonParam1.put("class", "数字媒体应用技术162班");
	        System.out.println("dasdasd"+jsonParam1.toString());
	      //定义数组增加请求参数
      JSONObject jsonParam = new JSONObject();
      jsonParam.put("app_id", YouTuAppContants.AppID);  
      jsonParam.put("person_id","person1"); 
     jsonParam.put("images","['"+image+"']");
//      jsonParam.put("urls", "['E:/whx1.jpg']");
      jsonParam.put("tag", "whx1"); 
      
      System.out.println("dasdasd"+jsonParam.toString());
      //请求urlapi
//      String result =Newperson.getJsonData(jsonParam, url, sign);
      String result = HttpUtilYoutu.post(url, jsonParam.toString(), "UTF-8",sign);
      System.out.println(result);
	}
}
