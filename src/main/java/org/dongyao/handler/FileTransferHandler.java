package org.dongyao.handler;


import org.dongyao.modle.Business.MerchantFile;

/**
 * 文件传输类，负责不同端点的文件传输功能
 *
 * @author HONG
 * @date: 2018年11月15日 上午11:07:41
 */
public interface FileTransferHandler {

	/**
	 * 【front】 <——> 【middle】 <——> 【core】 
	 * （1）incoming：【front】 传入到 【middle】
	 * （2）incoming：【core】 传入到 【middle】 
	 * （3）outGoing：【middle】 传出到 【front】
	 * （4）outGoing：【middle】 传出到 【core】
	 * 
	 * 将以上4个具体方向的分为两个大致方向：输出 和 输出。 再由不同的源头（目的地）调用相关的具体方法（重写）。
	 */
	// public static final int NO_REFERENCES = 1001;
	public static final int FRONT = 1; // 1表示方向front（输出 / 输入）
	public static final int CORE = 0; // 0表示方向core （输出 / 输入）

	/**
	 * 历史版本：
	 * 
	 * v:1.0 boolean FrontToFilecore(String source,String name, String
	 * uploadPath,String localPath, String checkRules);
	 * Job调用统一方法，明确从front到filecore。（过于固定）
	 * 
	 * v:2.0 boolean fileIncoming(int source, MerchantFile file)
	 * Job调用的时候参数过于麻烦，直接将通过source来判断是什么操作，再交由private方法来操作。
	 * 
	 * v:3.0 default boolean fileIncoming(int source, MerchantFile file) { ..}
	 * 使用接口默认方法，因为标识常量也是再接口定义的，不应该交由其实现类来实现。
	 */
	default boolean fileIncoming(int source, MerchantFile file) {
		switch (source) {
		case 0: // come from front
			return fileIncoming(file.getName(), file.getDownloadPath(), file.getLocalPath(), file.getCheckRules());

		case 1:// come from core
			return fileIncoming(file.getName(), file.getDownloadPath(), file.getLocalPath());

		default:
			break;
		}
		return false;
	}

	/**
	 * 
	 * @param name
	 * @param downloadPath
	 * @param localPath
	 * @param checkRules
	 * @return
	 */
	boolean fileIncoming(String name, String downloadPath, String localPath, String checkRules);

	/**
	 * 
	 * @param name
	 * @param downloadPath
	 * @param localPath
	 * @return
	 */
	boolean fileIncoming(String name, String downloadPath, String localPath);

	/**
	 * 
	 * @param source
	 * @param file
	 * @return
	 */
	default boolean fileOutGoing(int source, MerchantFile file) {
		switch (source) {
		case FRONT:
			fileOutGoing(file.getName(), file.getUploadPath(), file.getLocalPath());

		case CORE:
			//
			break;
		default:
			break;
		}

		return false;
	}

	/**
	 * 
	 * @param businessName
	 * @param uploadPath
	 * @param localPath
	 * @return
	 */
	boolean fileOutGoing(String businessName, String uploadPath, String localPath);

}
