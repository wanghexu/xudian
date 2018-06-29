package com.qc.face;

import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/** 
 * �㷨��ʶ 
 */ 
public class HMACSHA1 {

	private static final String HMAC_SHA1 = "HmacSHA1";  
    /** 
     * ���� 
     * @param data Ҫ���ܵ����� 
     * @param key ��Կ  
     * @return 
     * @throws Exception 
     */  
    public static byte[] getSignature(String data, String key) throws Exception {  
        Mac mac = Mac.getInstance(HMAC_SHA1);  
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(),  
                mac.getAlgorithm());  
        mac.init(signingKey);  
        return mac.doFinal(data.getBytes());  
    }  
    /** 
     * ����������� 
     * @param length 
     * @return 
     */  
    public static String genRandomNum(int length){    
        int  maxNum = 62;    
        int i;    
        int count = 0;    
        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};        
        StringBuffer pwd = new StringBuffer("");    
        Random r = new Random();    
        while(count < length){    
         i = Math.abs(r.nextInt(maxNum));       
         if (i >= 0 && i < str.length) {    
          pwd.append(str[i]);    
          count ++;    
         }    
        }    
        return pwd.toString();    
      }   
}
