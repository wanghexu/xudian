package com.qc.action;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qc.utils.Content;
import com.qc.utils.News;


/**
 * 获取新闻
 * @author 王和旭
 *
 */
@Controller
public class NewsAction {

	@RequestMapping(value="getnews",produces={"text/html;charset=UTF-8"},method = RequestMethod.POST)
	public @ResponseBody String getnews(@RequestParam("PageNum") int PageNum){
		
		News news =new News();
		String newslist="";
		try {
			  newslist=news.getnews(PageNum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newslist;
	}
	
	//获取新闻内容
	@RequestMapping(value="getcons",produces={"text/html;charset=UTF-8"},method = RequestMethod.POST)
	public @ResponseBody String getcons(@RequestParam("herf") String herf){
			
		String conslist="";
		try {
			conslist=Content.getcon(herf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conslist;
	}
}
