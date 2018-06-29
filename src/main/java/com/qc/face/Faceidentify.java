package com.qc.face;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 查询人脸
 * 
 * @author 王和旭
 *
 */
public class Faceidentify {

	public static String faceidentify(String imagePath) throws Exception {
		// 人脸查询识别
		String url = "http://api.youtu.qq.com/youtu/api/faceidentify";
		String sign = Sign.getSign(YouTuAppContants.userQQ, YouTuAppContants.AppID, YouTuAppContants.SecretID,
				YouTuAppContants.SecretKey);
		// 图片二进制编码
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
		String image = Base64Util.encode(imgData);

		JSONObject jsonParam = new JSONObject();
		jsonParam.put("app_id", YouTuAppContants.AppID);
		jsonParam.put("group_ids", "['gdqy']");
		jsonParam.put("image", image);

		System.out.println("dasdasd" + jsonParam.toString().trim());
		String result = HttpUtilYoutu.post(url, jsonParam.toString().trim(), "UTF-8", sign);
		 //取出缩略图
	       JSONObject json= JSONObject.fromObject(result);
	       System.out.println(result);
	       String candidates=json.get("candidates").toString();
	       System.out.println(candidates);
	       String[] s=candidates.split("\"");

	       String ss=s[13];
	       System.out.println(ss);
	       String[] sss=String.valueOf(ss).split(" ");
	       List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
				//不断新建new集合接收，这样就不会出现覆盖
				Map<String, Object>  map = new HashMap<String, Object>();
				System.out.println(sss.length);
				map.put("username",sss[0]);
				map.put("school",sss[1]);
				map.put("classes",sss[2]);
				list1.add(map);
				JSONArray jsonArray = JSONArray.fromObject(list1);

		return jsonArray.toString();
	}

//	public static void main(String[] args) throws Exception {
//		// 人脸查询识别
//		String url = "http://api.youtu.qq.com/youtu/api/faceidentify";
//		String sign = Sign.getSign(YouTuAppContants.userQQ, YouTuAppContants.AppID, YouTuAppContants.SecretID,
//				YouTuAppContants.SecretKey);
//		String imagePath = "E:/whx.jpg";
//		// 图片二进制编码
//		byte[] imgData = FileUtil.readFileByBytes(imagePath);
//		String image = Base64Util.encode(imgData);
//
//		JSONObject jsonParam = new JSONObject();
//		jsonParam.put("app_id", YouTuAppContants.AppID);
//		jsonParam.put("group_ids", "['gdqy']");
//		jsonParam.put("image", image);
//
//		System.out.println("dasdasd" + jsonParam.toString().trim());
//		String result = HttpUtilYoutu.post(url, jsonParam.toString().trim(), "UTF-8", sign);
//		 //取出缩略图
//	       JSONObject json= JSONObject.fromObject(result);
//	       System.out.println(result);
//	       String candidates=json.get("candidates").toString();
//	       System.out.println(candidates);
//	       String[] s=candidates.split("\"");
//
//	       String ss=s[13];
//	       System.out.println(ss);
//	       String[] sss=String.valueOf(ss).split(" ");
//	       List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
//				//不断新建new集合接收，这样就不会出现覆盖
//				Map<String, Object>  map = new HashMap<String, Object>();
//				System.out.println(sss.length);
//				map.put("username",sss[0]);
//				map.put("title",sss[1]);
//				map.put("time",sss[2]);
//				list1.add(map);
//				JSONArray jsonArray = JSONArray.fromObject(list1);
//
//      		System.out.println(jsonArray.toString());
//
//	}
}
