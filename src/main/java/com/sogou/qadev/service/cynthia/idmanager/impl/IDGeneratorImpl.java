package com.sogou.qadev.service.cynthia.idmanager.impl;

import java.util.UUID;

import com.sogou.qadev.service.cynthia.idmanager.IDGenerator;


/**
 * 全局唯一标识生成器
 * 
 * @author zhangjianke
 * 
 */
public class IDGeneratorImpl implements IDGenerator  {



	/**
	 * 获取全局唯一ID（包含补位、前缀、后缀）
	 * 
	 * @return
	 */
	public String nextId() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
