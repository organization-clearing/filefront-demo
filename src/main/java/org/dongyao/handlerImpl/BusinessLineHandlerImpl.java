package org.dongyao.handlerImpl;

import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.dongyao.handler.BusinessParseHandler;
import org.dongyao.modle.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;

/**
 * 业务线处理类的实现类。
 * 
 * <p>
 * XML配置业务线，再通过XML解析转化（XStream技术）得到业务线类(Business)实例对象。
 *
 * @author HONG
 * @date: 2018年11月15日 下午12:56:59
 */
@Component
public class BusinessLineHandlerImpl implements BusinessParseHandler {

	private List<Business> businesses;
	
	@Autowired
	private XStream xStream;
	
	//这里应该通过注解来注入
	private String xmlPath = "businessLine.xml";

	/**
	 * 通过XStream方法解析xml并反序列化。
	 * 
	 * <p>	Spring容器启动加载该类实例对象后，便自动调用该方法（init），
	 * 		该方法解析xml并反序列化为一个List<Business>变量，
	 *		最后注入该实例对象的businesses变量中。
	 */
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void xmlParse() {
		InputStream xml = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
		xStream.alias("filefront", List.class);
		xStream.processAnnotations(Business.class);
		this.setBusinesses((List<Business>) xStream.fromXML(xml));
	}

	public List<Business> getBusinesses() {
		return businesses;
	}

	public void setBusinesses(List<Business> businesses) {
		this.businesses = businesses;
	}
	
	


}
