package org.dongyao.ftptest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPTest {
	private String hostname = "192.168.0.126";
	private int port = 21;
	private String username = "ftpuser";
	private String password = "123456";
	private FTPClient ftpClient;

	public void initFTPClient() {
		ftpClient = new FTPClient();
		ftpClient.setControlEncoding("utf-8");
		try {
			ftpClient.connect(hostname, port); // 先连接
			ftpClient.login(username, password);// 再登陆
			System.out.println("+++++++");
//			int replyCode = ftpClient.getReply();
//			if (!FTPReply.isPositiveCompletion(replyCode)) {
//				System.out.println("connect failed...ftp服务器:" + this.hostname + ":" + this.port);
//			}
//			System.out.println("connect successfu...ftp服务器:" + this.hostname + ":" + this.port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean downloadFile(String pathname, String localpath, String filename) {
		boolean flag = false;
		OutputStream outputStream = null;

		try {
			ftpClient.changeWorkingDirectory(pathname); // 切换至文件夹
			FTPFile[] FTPFiles = ftpClient.listFiles(); // 获取该目录下的所有文件
			for (FTPFile file : FTPFiles) { // 不是常规的File
				if (filename.equalsIgnoreCase(file.getName())) {
					File localFile = new File(localpath + "/" + filename);
					outputStream = new FileOutputStream(localFile);
					ftpClient.retrieveFile(file.getName(), outputStream); // 这里获得了
					outputStream.close();
				}
			}

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public void traverse(String pathname) {
		try {
//			ftpClient.changeWorkingDirectory(pathname); // 切换至文件夹
//			FTPFile[] ftpFiles = ftpClient.listFiles(); // 获取该目录下的所有文件
			FTPFile[] ftpFiles = ftpClient.listFiles(pathname);
			for (FTPFile file : ftpFiles) {
				System.out.println(file.getName() + "  ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FTPTest ftpTest = new FTPTest();
		ftpTest.initFTPClient();
		ftpTest.traverse("");
		boolean flag =ftpTest.downloadFile("/files/", "F:/ftpfiletest", "word.doc");
		System.out.println(flag);
	}

}
