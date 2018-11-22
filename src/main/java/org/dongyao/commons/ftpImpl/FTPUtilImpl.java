package org.dongyao.commons.ftpImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.dongyao.commons.ftp.FTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FTPUtilImpl implements FTPUtil {
	
	//采用自动装配，参数不方便调节。（若采用xml又过于冗余）
	private String host = "192.168.0.126";

	private int port = 21;

	private String username = "ftpuser";

	private String password = "123456";
	
	@Autowired
	private FTPClient ftpClient;

	public void getConnect() {
		try {
			ftpClient.connect(host, port);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean login() {
		try {
			return ftpClient.login(username, password);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	public FTPFile[] list(String downloadPath) {
		try {
			return ftpClient.listFiles(downloadPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public boolean downLoadFile(FTPFile[] ftpFiles, String localname) {
		
		
		return false;
	}
	
	
	public boolean downLoadFile(String pathname, String localname, String filename) {
		boolean flag = false;
		FTPFile[] ftpFiles;
		OutputStream outputStream;

		try {
			//（1）获得【服务端】文件集合
			ftpFiles = ftpClient.listFiles(pathname);	
			for (FTPFile ftpFile : ftpFiles) {
				if (filename.equalsIgnoreCase(ftpFile.getName())) {
					//（2）再内存创建File对象，但机器是不会随之创建的
					File localFile = new File(localname + "/" + ftpFile.getName());
					//（3）创建输出流（写入到硬盘）
					outputStream = new FileOutputStream(localFile);
					//（4）从根据localname将file写入输出流outputStream
					flag = ftpClient.retrieveFile(localname, outputStream);
					//（5）关闭流
					outputStream.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return flag;
	}

	public boolean uploadFile(String localname, String pathname, String filename) {
		return false;
	}

	@Override
	public boolean retrieveFile(String downloadPath) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	

}
