package org.dongyao.job;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.dongyao.handler.BusinessParseHandler;
import org.dongyao.handler.FileTransferHandler;
import org.dongyao.modle.Business;
import org.dongyao.modle.Business.MerchantFile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 目标文件从前部（front）到中部（middle）
 * 
 * @author HONG
 */
public class FrontToMiddleJob {
	@Autowired
	FileTransferHandler fileTransferHandler;

	@Autowired
	BusinessParseHandler businessParseHandler;

	/**
	 * 从前端获取若干种文件并保存在中端（本机）
	 */
	public void execute() {
		List<Business> businesses = businessParseHandler.getBusinesses();
		 // 遍历每条业务线
		for (Business business : businesses) {
			// 遍历一条业务线的每种文件类型
			for (MerchantFile merchantFile : business.getIncomingFiles()) {
				fileTransferHandler.fileIncoming(fileTransferHandler.FRONT, merchantFile);
			}
		}
		
		System.out.println("【Job execute over】");

	}
}
