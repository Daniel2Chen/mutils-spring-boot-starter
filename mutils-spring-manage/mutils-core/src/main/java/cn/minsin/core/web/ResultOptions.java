package cn.minsin.core.web;

public interface ResultOptions {
	
	/**
	 * 	返回给前端的code
	 */
    int getCode(); 
    
    /**
     * 	返回给前端的提示消息
     */
    String getMsg();
} 
