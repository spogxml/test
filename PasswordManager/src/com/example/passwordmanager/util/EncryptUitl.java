package com.example.passwordmanager.util;

import org.apache.commons.codec.binary.Base64;

public class EncryptUitl {
	/**
	 * 根据Key将指定内容加密
	 * @param entry
	 * @param key 为空则不加密
	 * @return
	 */
	public static String encode(String entry,String key) {  
		if(key!=null && key.length()>0){
			String first = encodeBase64(entry) ;
			String second = first;
			char[] keyChars = key.toCharArray();
			for(char c:keyChars){
				int ch = c;
				int count = ch % 7 ;
				for(int i=0;i<count;i++){
					second=encodeBase64(second);
				}
			}
			
			return second;
		}
		return entry;
    }
	
	/**
	 * 将字符串做base64处理
	 * @param str
	 * @return
	 */
	public static String encodeBase64(String str){
		return new String(Base64.encodeBase64(str.getBytes()));  
    
	}
	

	/**
	 * 将字符串做反base64处理
	 * @param str
	 * @return
	 */
	public static String decodeBase64(String str){
		return new String(Base64.decodeBase64(str.getBytes()));  
    
	}
	//解码
	public static String decode(String entry,String key) {  
		if(key!=null && key.length()>0){
			String second = entry;
			char[] keyChars = key.toCharArray();
			for(char c:keyChars){
				int ch = c;
				int count = ch % 7 ;
				for(int i=0;i<count;i++){
					second=decodeBase64(second);
				}
			}
			return decodeBase64(second);
		}
		return entry;
    }
	

}
