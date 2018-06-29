package com.qc.utils;

public class Random {

	public  String random() {
		// TODO Auto-generated method stub
//		for (int i = 0; i <= 200; i++)  
//		{  
		    int intFlag = (int)(Math.random() * 1000000);  
		  
		    String flag = String.valueOf(intFlag);  
		    if (flag.length() == 6 && flag.substring(0, 1).equals("9"))  
		    {  
		        System.out.println(intFlag);  
		    }  
		    else  
		    {  
		        intFlag = intFlag + 100000;  
		        System.out.println(String.valueOf(intFlag));  
		    }   
		    
		    return String.valueOf(intFlag);
//		}        
	}
//	public static void main(String[] args) {
//		random();
//	}
}
