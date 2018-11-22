package org.dongyao.handler;

import java.util.List;

import org.dongyao.modle.Business;


/**
 * 业务线处理类，主要目的是将[业务线配置文件] 转化为 [业务线对象(Business)]，
 * 并提供业务线(Business)实例对象及相关操作。
 *
 * @author HONG
 * @date: 2018年11月15日 上午11:30:59
 */
public interface BusinessParseHandler {
	
	/**
	 * @return 业务线对象集合
	 */
	List<Business> getBusinesses();
}
