package org.dongyao.test;

import java.util.List;

import org.dongyao.handlerImpl.BusinessLineHandlerImpl;
import org.dongyao.modle.Business;
import org.dongyao.modle.Business.MerchantFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SpringTest {

	@Autowired
	BusinessLineHandlerImpl businessLineHandlerImpl;

	@Test
	public void testFTP() {
		List<Business> businesses = businessLineHandlerImpl.getBusinesses();
		for (Business business : businesses) {

			System.out.println("Business Name:  " + business.getName());
			List<MerchantFile> merchantFiles1 = business.getIncomingFiles();
			for (MerchantFile merchantFile : merchantFiles1) {
				System.out.println(merchantFile);
			}
			
			List<MerchantFile> merchantFiles2 = business.getOutgoingFiles();
			for (MerchantFile merchantFile : merchantFiles2) {
				System.out.println(merchantFile);
			}

		}

	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
	}
}
