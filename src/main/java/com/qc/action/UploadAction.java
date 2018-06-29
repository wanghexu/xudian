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
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;

/**
 * 文件上传
 * @author 王和旭
 *
 */
@Controller
public class UploadAction {
        
	//文件路径
	//static String imgpath="https://wanghexu.cn/qingchunimg/";
	@RequestMapping("uploadimg")
	public @ResponseBody String uploadimg (@RequestParam(value="file") MultipartFile file,HttpServletRequest request) throws Exception{
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
        //返回路径
 //       JSONObject jsonParam= new JSONObject();
//        jsonParam.put("imgurl", request.getContextPath()+"/qingchunimg/"+filename);
 //       jsonParam.put("imgurl", imgpath+"/"+filename);
        
//        return jsonParam.toString();
        return "/"+filename;
    }
	
}
