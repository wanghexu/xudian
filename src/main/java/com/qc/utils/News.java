package com.qc.utils;

import java.io.IOException;
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
 * 爬虫新闻
 * @author whx
 *
 */
public class News {

	public  String getnews(int PageNum) throws IOException  {
		// TODO Auto-generated method stub
		//定义官网新闻路径
		String url;
		if(PageNum==1){
			 url="http://www.gdqy.edu.cn/viscms/xiaoyuanxinwen2538/index.html";
		}else{
			url="http://www.gdqy.edu.cn/viscms/xiaoyuanxinwen2538/index_"+PageNum+".html";
		}
//		集合接收数据
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		//定义json转换
		 JSONArray jsonArray;
		//模拟谷歌浏览器
		Document document=Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36").get();
		Elements titles=document.getElementById("mainc").select("div.mainl ul li a");
		Elements times=document.getElementById("mainc").select("div.mainl ul li span");
		for(Element title:titles){
	             
			String ti=title.text();
			list2.add(ti);
			list4.add(title.attr("href"));
			//System.out.println(ti);
		}
		for(Element time:times){
            
			String tim=time.text();
			String[] tim2=tim.split(" ");
			list3.add(tim2[1]);
			//System.out.println(tim2[1]);
		}
		for(int i=0;i<list2.size();i++)
		{
			//不断新建new集合接收，这样就不会出现覆盖
			Map<String, Object>  map = new HashMap<String, Object>();
			map.put("news_id", i+1);
			map.put("title", list2.get(i));
			map.put("time", list3.get(i));
			map.put("href", list4.get(i));
			list1.add(map);

		}
//		System.out.println(list1);
//		System.out.println(list2.get(0));
//		System.out.println(list3);
		 jsonArray = JSONArray.fromObject(list1);
	//	System.out.println(jsonArray.toString());
     
		return jsonArray.toString();
	}

}
