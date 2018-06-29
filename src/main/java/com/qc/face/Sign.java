package com.qc.face;

import java.util.Date;

public class Sign {
	 /** 
     * Authorization���� 
     * @param userQQ �����ߴ���Ӧ��ʱ��QQ�� 
     * @param AppID �����ߴ���Ӧ�ú��AppID 
     * @param SecretID �����ߴ���Ӧ�ú��SecretID 
     * @param SecretKey �����ߴ���Ӧ�ú��SecretKey 
     * @return sign 
     * @throws Exception 
     */  
    public static String getSign(String userQQ,String AppID,String SecretID,String SecretKey) throws Exception{  
        long tnowTimes = new Date().getTime()/1000;  
        long enowTimes = tnowTimes+2592000;  
        String rRandomNum = HMACSHA1.genRandomNum(10);  
        String param = "u=" + userQQ + "&a=" + AppID + "&k=" + SecretID + "&e="  
                + enowTimes + "&t=" + tnowTimes + "&r=" + rRandomNum + "&f=";  
        byte [] hmacSign = HMACSHA1.getSignature(param, SecretKey);  
        byte[] all = new byte[hmacSign.length+param.getBytes().length];  
        System.arraycopy(hmacSign, 0, all, 0, hmacSign.length);  
        System.arraycopy(param.getBytes(), 0, all, hmacSign.length, param.getBytes().length);  
        String sign = Base64Util.encode(all);  
        return sign;  
    }
}
