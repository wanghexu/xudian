package com.qc.face;

import net.sf.json.JSONObject;

public class GetPersonInfo {
	
    public static void main(String[] args) throws Exception{
	

	//��������
	String url= "http://api.youtu.qq.com/youtu/api/getinfo";
	//api��������ͷ
	String sign = Sign.getSign(YouTuAppContants.userQQ,  
            YouTuAppContants.AppID, YouTuAppContants.SecretID,  
            YouTuAppContants.SecretKey);
	
      //�������������������
    JSONObject jsonParam = new JSONObject();
     jsonParam.put("app_id", YouTuAppContants.AppID);  
     jsonParam.put("person_id","person3"); 
     //����api��ѯ
     String result = HttpUtilYoutu.post(url, jsonParam.toString(), "UTF-8",sign);
     System.out.println(result);

    }
}
