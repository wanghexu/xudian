package com.qc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qc.face.Ronghe;
import com.qc.face.Shibieface;
import com.qc.utils.Content;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 文件上传
 * @author 王和旭
 *
 */
@Controller
public class FaceAction {
        
	//文件路径
	//static String imgpath="https://wanghexu.cn/qingchunimg/";
	//人脸魅力检查
	@RequestMapping("detectface")
	public @ResponseBody String detectface (@RequestParam(value="file") MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//文件路径
//		String imgpath = request.getSession().getServletContext().getRealPath("qcimg");
		String imgpath="C:/apache-tomcat-8.0.47/webapps/qingchunimg/";
		System.out.println(imgpath);
		//获取文件名字
		String filename =file.getOriginalFilename();
		System.out.println("文件原名字"+filename);
		//读取文件,写入磁盘
		InputStream is =file.getInputStream();
		//判断文件是否上传，如果上传的话将会创建该目录  
		File uploadFile=new File(imgpath);  
        if(!uploadFile.exists()){  
        	uploadFile.mkdir(); //创建该目录  
        }
		byte[] bs = new byte[1024];  
        int len;  
        OutputStream os = new FileOutputStream(new File(imgpath+filename));  
        while ((len = is.read(bs)) != -1) {  
            os.write(bs, 0, len);  
        }  
        os.close();  
        is.close();
        //请求api识别人脸
        String re=Shibieface.detectface(imgpath+filename);
       
        //JSONObject json=JSONObject.fromObject(re);
       
      //  JSONArray json=JSONArray.fromObject(re);
        return re;
    }
	
	@RequestMapping("rongheface")
	public @ResponseBody String rongheface(@RequestParam(value="file") MultipartFile file,@RequestParam(value="model") String model, HttpServletResponse response,HttpServletRequest request) throws Exception{
		//文件路径
		//String imgpath = request.getSession().getServletContext().getRealPath("qcimg");
		String imgpath="C:/apache-tomcat-8.0.47/webapps/qingchunimg/";
		System.out.println(imgpath);
		//获取文件名字
		String filename =file.getOriginalFilename();
		System.out.println("文件原名字"+filename);
		//读取文件,写入磁盘
		InputStream is =file.getInputStream();
		//判断文件是否上传，如果上传的话将会创建该目录  
		File uploadFile=new File(imgpath);  
        if(!uploadFile.exists()){  
        	uploadFile.mkdir(); //创建该目录  
        }
		byte[] bs = new byte[1024];  
        int len;  
        OutputStream os = new FileOutputStream(new File(imgpath+filename));  
        while ((len = is.read(bs)) != -1) {  
            os.write(bs, 0, len);  
        }  
        os.close();  
        is.close();
        //请求api识别人脸
        String re=Ronghe.ronghe(model, imgpath+filename);
          String s=Content.downImages(imgpath, re);           
        //JSONObject json=JSONObject.fromObject(re);
       
      //  JSONArray json=JSONArray.fromObject(re);
        return s;
    }
	
	//人脸识别
	@RequestMapping(value="Faceidentify",produces={"text/html;charset=UTF-8"})
	public @ResponseBody String Faceidentify(@RequestParam(value="file") MultipartFile file, HttpServletResponse response,HttpServletRequest request) throws Exception{
		//文件路径
		//String imgpath = request.getSession().getServletContext().getRealPath("qcimg");
		String imgpath="C:/apache-tomcat-8.0.47/webapps/qingchunimg/";
		System.out.println(imgpath);
		//获取文件名字
		String filename =file.getOriginalFilename();
		System.out.println("文件原名字"+filename);
		//读取文件,写入磁盘
		InputStream is =file.getInputStream();
		//判断文件是否上传，如果上传的话将会创建该目录  
		File uploadFile=new File(imgpath);  
        if(!uploadFile.exists()){  
        	uploadFile.mkdir(); //创建该目录  
        }
		byte[] bs = new byte[1024];  
        int len;  
        OutputStream os = new FileOutputStream(new File(imgpath+filename));  
        while ((len = is.read(bs)) != -1) {  
            os.write(bs, 0, len);  
        }  
        os.close();  
        is.close();
        //请求api识别人脸
        String re=com.qc.face.Faceidentify.faceidentify(imgpath+filename); 
        return re;
    }
	
	//人脸增加
	@RequestMapping("newperson")
	public @ResponseBody String newperson (@RequestParam(value="file") MultipartFile file, HttpServletResponse response,HttpServletRequest request) throws Exception{
		//文件路径
		//String imgpath = request.getSession().getServletContext().getRealPath("qcimg");
		String imgpath="C:/apache-tomcat-8.0.47/webapps/qingchunimg/";
		System.out.println(imgpath);
		//获取文件名字
		String filename =file.getOriginalFilename();
		System.out.println("文件原名字"+filename);
		//读取文件,写入磁盘
		InputStream is =file.getInputStream();
		//判断文件是否上传，如果上传的话将会创建该目录  
		File uploadFile=new File(imgpath);  
        if(!uploadFile.exists()){  
        	uploadFile.mkdir(); //创建该目录  
        }
		byte[] bs = new byte[1024];  
        int len;  
        OutputStream os = new FileOutputStream(new File(imgpath,filename));  
        while ((len = is.read(bs)) != -1) {  
            os.write(bs, 0, len);  
        }  
        os.close();  
        is.close();
        //请求api识别人脸
        String re=Shibieface.detectface(imgpath+filename);
  
  
        return re;
    }
	
}
