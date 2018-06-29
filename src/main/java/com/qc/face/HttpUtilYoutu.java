package com.qc.face;
import java.io.BufferedReader;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.HttpURLConnection;  
import java.net.URL;  
import java.security.KeyManagementException;  
import java.security.NoSuchAlgorithmException;  
import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  
   
import javax.net.ssl.HostnameVerifier;  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.SSLSession;  
import javax.net.ssl.TrustManager;  
import javax.net.ssl.X509TrustManager;  
  
/** 
 * http ������ 
 */  
public class HttpUtilYoutu {  
     private static class TrustAnyTrustManager implements X509TrustManager {  
           
            public void checkClientTrusted(X509Certificate[] chain, String authType)  
                    throws CertificateException {  
            }  
       
            public void checkServerTrusted(X509Certificate[] chain, String authType)  
                    throws CertificateException {  
            }  
       
            public X509Certificate[] getAcceptedIssuers() {  
                return new X509Certificate[] {};  
            }  
        }  
       
        private static class TrustAnyHostnameVerifier implements HostnameVerifier {  
            public boolean verify(String hostname, SSLSession session) {  
                return true;  
            }  
        }  
       
        /** 
         * post��ʽ���������(httpsЭ��) 
         *  
         * @param url 
         *            �����ַ 
         * @param content 
         *            ���� 
         * @param charset 
         *            ���� 
         * @return 
         * @throws NoSuchAlgorithmException 
         * @throws KeyManagementException 
         * @throws IOException 
         */  
        public static String post(String url, String content,String charset,String sign)  
                throws NoSuchAlgorithmException, KeyManagementException,  
                IOException {  
            SSLContext sc = SSLContext.getInstance("SSL");  
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },  
                    new java.security.SecureRandom());  
            URL console = new URL(url);  
            Integer length = content.length();  
            HttpURLConnection conn = (HttpURLConnection) console.openConnection();  
            //�ĵ�Ҫ����д��Header����                  
                conn.setRequestProperty("Host", "api.youtu.qq.com");  
            conn.setRequestProperty("Content-Length",length.toString());  
            System.out.println(length.toString());
            conn.setRequestProperty("Content-Type", "text/json");  
            conn.setRequestProperty("Authorization", sign);  
                //�ĵ�Ҫ����д��Header����  
               conn.setDoOutput(true);  
            conn.connect();  
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());  
            out.write(content.getBytes(charset));  
            // ˢ�¡��ر�  
            out.flush();  
            out.close();  
            BufferedReader in = null;  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),charset));  
            String result = "";  
            String getLine;  
            while ((getLine = in.readLine()) != null) {  
                result += getLine;  
            }  
            in.close();  
            //System.err.println("result:" + result);  
            return result;  
        }  
  
}  
