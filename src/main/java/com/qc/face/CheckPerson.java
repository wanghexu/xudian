package com.qc.face;

import net.sf.json.JSONObject;

public class CheckPerson {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		//��ѯ��Ա
		String url= "http://api.youtu.qq.com/youtu/api/faceidentify";
		String sign = Sign.getSign(YouTuAppContants.userQQ,  
	            YouTuAppContants.AppID, YouTuAppContants.SecretID,  
	            YouTuAppContants.SecretKey);

	   String imagePath = "E:/whx.jpg";
	    byte[] imgData = FileUtil.readFileByBytes(imagePath);  
        String image =  Base64Util.encode(imgData); 
	
        JSONObject jsonParam = new JSONObject();
		//��ѯ
        jsonParam.put("app_id", YouTuAppContants.AppID);  
        jsonParam.put("group_id","gdqy"); 
          jsonParam.put("image", image);
         // jsonParam.put("url", imagePath);
         System.out.println(jsonParam.toString());
         
         //����api
			String result = HttpUtilYoutu.post(url,jsonParam.toString(),"UTF-8",sign); 
           System.out.println(result);
	}

}
