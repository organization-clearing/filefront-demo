package org.dongyao.commons.ftp;

import java.util.List;


import org.apache.commons.net.ftp.FTPFile;

public interface FTPUtil {

	/**
	 * 获取FTP连接
	 */
	void getConnect();

	/**
	 * 登陆FTP服务器
	 * 
	 * @return	操作结果，ture为操作成功，反之，失败
	 */
	boolean login();
	
	
	/**
	 * 
	 * @param downloadPath	
	 * @return
	 */
	boolean retrieveFile(String downloadPath);
	
	
	/**
	 * 返回downloadPath下的的FTPFile数组
	 * 
	 * @param downloadPath
	 * @return
	 */
	FTPFile[] list(String downloadPath);
	
	
	/**
	 * 
	 * @param ftpFiles
	 * @param localname
	 * @return
	 */
	boolean downLoadFile(FTPFile[] ftpFiles, String localname);
	
	
	/**
	 * 
	 * @param ftpFiles
	 * @param localname
	 * @return
	 */
	default boolean downLoadFile(List<FTPFile> ftpFiles, String localname){
		
		return downLoadFile(ftpFiles.toArray(new FTPFile[0]), localname);
	}
	
	
	/**
	 * 通过FTP的方式，从pathname下载文件filename到本地localname。
	 * 
	 * @param pathname	FTP文件路径
	 * @param localname	本地文件路径
	 * @param filename	文件名
	 * @return
	 */
	boolean downLoadFile(String pathname, String localname, String filename);
	
	/**
	 * 通过FTP的方式，从本地目录localname上传未见filename到pathname
	 * 
	 * @param localname
	 * @param pathname
	 * @param filename
	 * @return
	 */
	boolean uploadFile(String localname, String pathname, String filename);
}
