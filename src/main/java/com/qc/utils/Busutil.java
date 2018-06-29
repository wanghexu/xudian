package com.qc.utils;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import net.sf.json.JSONObject;
/**
 *
    * @ClassName: HttpLogin
    * @Description:  java通过httpclient获取cookie模拟登录
    * @author whx
    * @date 
    *
 */
 
public class Busutil {
 
	static String the_cookies;
	
    public String login(String username,String password) {
        // 登陆 Url
        String loginUrl = "http://bz.gdqy.edu.cn/bus/login.jsp";
       // String loUrl = "http://bz.gdqy.edu.cn/bus/MyJsp.jsp?student_id=2016061002331&password=131113&goto=http://bz.gdqy.edu.cn/bus/MyJsp.jsp";
        String loUrl = "http://bz.gdqy.edu.cn/bus/MyJsp.jsp?student_id="+username+"&password="+password+"&goto=http://bz.gdqy.edu.cn/bus/MyJsp.jsp";
        // 需登陆后访问的 Url
//        String dataUrl = "http://bz.gdqy.edu.cn/bus/mobile.jsp";
        HttpClient httpClient = new HttpClient();
//        CloseableHttpClient mHttpClient =HttpClients.createDefault();
//        HttpGet httpGet=new HttpGet(loginUrl);  
//        CloseableHttpResponse response;
//		try {
//			response = mHttpClient.execute(httpGet);
//			//提交请求获得响应  
//	        String mCookie=response.getFirstHeader("Set-Cookie").getValue();   //获取cookie
//	                   
//	        System.out.println(mCookie);
//		} catch (ClientProtocolException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
        //定义标题，接收用户名字
        String title="";
        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
       // PostMethod postMethod = new PostMethod(loUrl);
        GetMethod  getMethods =new GetMethod(loginUrl);
 
       /* // 设置登陆时要求的信息，用户名和密码
        NameValuePair[] data = { new NameValuePair("student_id", "2016061002331"), new NameValuePair("password", "131113") };
        postMethod.setRequestBody(data)*/;
        try {
            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            int statusCode=httpClient.executeMethod(getMethods);
                    System.out.println(statusCode);         
            // 获得登陆后的 Cookie
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            System.out.println("cookiescookies"+cookies);
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
              //  System.out.println("cookies = "+c.toString());
            }
            //赋值全局cookie
            the_cookies=tmpcookies.toString();
//            if(statusCode==302){//重定向到新的URL
                // 进行登陆后的操作
                GetMethod getMethod = new GetMethod(loUrl);
                // 每次访问需授权的网址时需带上前面的 cookie 作为通行证
                getMethod.setRequestHeader("cookie", tmpcookies.toString());
                // 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据
                // 例如，referer 从哪里来的，UA 像搜索引擎都会表名自己是谁，无良搜索引擎除外
                getMethod.setRequestHeader("Referer", "http://bz.gdqy.edu.cn/bus/login.jsp?p=1&goto=http://bz.gdqy.edu.cn/bus/MyJsp.jsp");
                getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
                httpClient.executeMethod(getMethod);
                // 打印出标题
                 String text = getMethod.getResponseBodyAsString();
                //System.out.println(text);
                 Document doc = Jsoup.parse(text);                                                   //html为内容    
                 Element e_title = doc.getElementsByTag("title").get(0);               //根据标签名找title元素
                  title = e_title.text(); 
               // System.out.println("标题是："+title);
//                String title1="NULL,欢迎你！";
//               System.out.println(title1.length());
//               doPost("http://bz.gdqy.edu.cn/bus/servlet/TrainSelect",tmpcookies.toString());
//            }
//            else {
//                System.out.println("登录失败");
//            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return title;
    }
    
    //访问获取全部车票
    public String getallbus(){
    	
    	String list=doPost("http://bz.gdqy.edu.cn/bus/servlet/TrainSelect",the_cookies,"");
    	return list;
    }
    //访问订票
    public String takebus(String bus_id,String phone){
//    	//定义数组参数
//		JSONObject jsonParam = new JSONObject();  
//        jsonParam.put("train_id",bus_id );  
//        jsonParam.put("phone",phone);
    	//接收参数，表单参数
    	String param="";
		try {
			param = "train_id=" + URLEncoder.encode(bus_id, "UTF-8")+"&phone="+URLEncoder.encode(phone, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println("参数"+param);
    	String list=doPost("http://bz.gdqy.edu.cn/bus/servlet/TrainBooking",the_cookies,param);

    	System.out.println("cookie是:"+the_cookies);
    	return list;
    }
    
    //退票
    public String canclebus(String bus_id){
    	//接收参数，表单参数
    	String param="";
		try {
			param = "booking_id=" + URLEncoder.encode(bus_id, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println("参数"+param);
    	String list=doPost("http://bz.gdqy.edu.cn/bus/servlet/CancleBooking",the_cookies,param);

    	System.out.println("cookie是:"+the_cookies);
    	return list;
    }
    
    //已经预定记录
    public String havebus(String stu_id){
    	//接收参数，表单参数
    	String param="";
		try {
			param = "student_id=" + URLEncoder.encode(stu_id, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println("参数"+param);
    	String list=doPost("http://bz.gdqy.edu.cn/bus/servlet/MyBookig",the_cookies,param);

    	System.out.println("cookie是:"+the_cookies);
    	return list;
    }
    
    //无参自动预定记录
    public String havebus_noparam(){
    	
//    	String list=doPost("http://bz.gdqy.edu.cn/bus/servlet/GetTikets",the_cookies,"");
    	String list=doPost("http://bz.gdqy.edu.cn/bus/servlet/BookigSelect",the_cookies,"");
    	return list;
    }
    
    //自动抢票系统
    public String getaotubus(){
    	
    	String list=doPost("http://bz.gdqy.edu.cn/bus/servlet/TrainSelect",the_cookies,"");
    	return list;
    }
    
    //获取cookie
    
    public String getcookie(){
     	  // 登陆 Url
        String loginUrl = "http://bz.gdqy.edu.cn/bus/login.jsp";
        HttpClient httpClient = new HttpClient();
    	  GetMethod  getMethods =new GetMethod(loginUrl);
           String thecookies="";            
		try {
			 // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
			httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			 int statusCode = httpClient.executeMethod(getMethods);
	        System.out.println(statusCode);         
	          // 获得登陆后的 Cookie
	          Cookie[] cookies = httpClient.getState().getCookies();
	          StringBuffer tmpcookies = new StringBuffer();
	          for (Cookie c : cookies) {
	              tmpcookies.append(c.toString() + ";");
	              System.out.println("cookies = "+c.toString());
	          }
	          //赋值cookie
	           the_cookies=tmpcookies.toString();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
    	  return thecookies;
    }
    
    /**  
     * 数据流post请求  
     * @param urlStr  
     * @param xmlInfo  servlet/TrainSelect
     */  
    public static String doPost(String urlStr,String cookie,String Param) {  
        String reStr="";  
        String strInfo=Param;
        
        try {  
            URL url = new URL(urlStr);  
            //URLConnection con = url.openConnection();  
            // 建立http连接  
            HttpURLConnection con = (HttpURLConnection) url.openConnection();  
            con.setDoOutput(true);  
            con.setRequestProperty("cookie", cookie); 
            // 设置传递方式  
            con.setRequestMethod("POST");
            con.setRequestProperty("Cache-Control", "no-cache");
            //con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
         // 设置文件类型:  
            con.setRequestProperty("contentType", "application/x-www-form-urlencoded"); 
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            //发送参数
            out.write(strInfo.getBytes("UTF-8"));             
            out.flush();  
            out.close();  
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));  
            String line = "";  
            for (line = br.readLine(); line != null; line = br.readLine()) {  
                reStr += line;  
            }  
//            br.close();  
//            //网站连接断了  
//            con.disconnect();
            System.out.println(reStr);
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
         
        return reStr;  
    }  
}
