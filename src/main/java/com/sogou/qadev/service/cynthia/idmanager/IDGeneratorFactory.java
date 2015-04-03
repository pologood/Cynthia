package com.sogou.qadev.service.cynthia.idmanager;

import java.util.UUID;

import com.sogou.qadev.service.cynthia.idmanager.impl.IDGeneratorImpl;


/**
 * 全局唯一标识生成器
 * 
 * @author zhangjianke
 * 
 */
public class IDGeneratorFactory {

	private static IDGenerator instance;

	/**
	 * 获取全局唯一ID（包含补位、前缀、后缀）
	 * 
	 * @return
	 */
	public static String nextId() {
		return instance.nextId();
	}
	
	
	public synchronized static IDGenerator getInstance() {
		if (instance == null)
			instance = new IDGeneratorImpl();
		return instance;
	}
}
