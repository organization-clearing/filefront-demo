package org.dongyao.ftptest;


import sun.net.ftp.FtpClient;

/**
 * Java自带的API对FTP的擦偶哦
 *
 * @author HONG
 * @date: 2018年11月22日 上午9:36:44
 */
public class FTPTestBySunJar {
	private String localfilename;	//本地文件名
	private String remotefilename;	//远程文件名
	private FtpClient ftpClient;	//FTP客户端
	
	public void connectServer(String ip, int port, String user, String password, String path){
		try {
			ftpClient = new FtpClient(ip);
		} catch (Exception e) {
		}
		//连接
		
		
	}
}
