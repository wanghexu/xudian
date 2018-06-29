package com.qc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sf.json.JSONArray;

/**
 * 爬虫新闻内容
 * @author whx
 *
 */
public class Content {
	
//	public static void main(String[] args) throws IOException{
////		// TODO Auto-generated method stub
////		//定义官网新闻路径
////		String url="http://www.gdqy.edu.cn/viscms/xuexiaoyaowen2183/20180606/224811.html";
//////		集合接收数据
////		List<String> list1 = new ArrayList<String>();
////		//定义json转换		 
////		//模拟谷歌浏览器
////		Document document=Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36").get();
////		Elements cons=document.getElementById("mainc").select("div.content p");
////		for(Element con:cons){
////	             
////			String ti=con.text();
////			if(ti.length()>=30)
////			{
////			  list1.add(ti);
////			}
////			//System.out.println(ti);
////		}
////
////	  JSONArray jsonArray = JSONArray.fromObject(list1);
////	  System.out.println(jsonArray.toString());
////		String filePath ="C:/qingchunimg";
////		String imageUrl="http://shp.qpic.cn/tu_act_pic/0/WMdt5Z6C1kvl/200";
////		downImages(filePath,imageUrl);
//     
//	}
	
	//获取内容
	public static String getcon(String url)throws IOException{
		// TODO Auto-generated method stub
		//定义官网新闻路径
//		String url="http://www.gdqy.edu.cn/viscms/xuexiaoyaowen2183/20180606/224811.html";
//		集合接收数据
		List<String> list1 = new ArrayList<String>();
		//定义json转换		 
		//模拟谷歌浏览器
		Document document=Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36").get();
		Elements cons=document.getElementById("mainc").select("div.content p");
		for(Element con:cons){
	             
			String ti=con.text();
			if(ti.length()>=30)
			{
			  list1.add(ti);
			}
			//System.out.println(ti);
		}

	  JSONArray jsonArray = JSONArray.fromObject(list1);
	  System.out.println(jsonArray.toString());
	  
      return jsonArray.toString();
	}
	
	//下载图片并保存
    public static String downImages(String filePath,String imageUrl){
        // 截取图片的名称
    	Random Rando=new Random();
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/"))+Rando.random()+".jpg";
        
        //创建文件的目录结构
        File files = new File(filePath);
        if(!files.exists()){// 判断文件夹是否存在，如果不存在就创建一个文件夹
            files.mkdirs();
        }
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            // 创建文件
            File file = new File(filePath+fileName);
            FileOutputStream out = new FileOutputStream(file);
            int i = 0;
            while((i = is.read()) != -1){
                out.write(i);
            }
            is.close();
            out.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return fileName;
        
    }

}
