package org.dongyao.handlerImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.dongyao.commons.ftp.FTPUtil;
import org.dongyao.commons.ftpImpl.FTPUtilImpl;
import org.dongyao.handler.FileTransferHandler;
import org.quartz.impl.jdbcjobstore.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 该类实现了FileTransferHandler接口，并通过FTP的形式完成文件的传输。
 *
 * @author HONG
 * @date: 2018年11月20日 下午4:31:09
 */
@Component
public class FileTransferHandlerImpl implements FileTransferHandler {

	@Autowired
	private FTPUtil ftpUtil;

	@Override
	public boolean fileIncoming(String name, String downloadPath, String localPath, String checkRules) {
		return fileIncoming(name, downloadPath, localPath);
	}

	@Override
	public boolean fileIncoming(String name, String downloadPath, String localPath) {
		System.out.println("name: " + name);
		System.out.println("downloadPath: " + downloadPath);
		// 1、FTPUtil连接并登陆 AOP
		ftpUtil.getConnect();
		ftpUtil.login();
		HashMap<String, String> variableMap = new HashMap<>();

		// 2、文件扫描（2次） scanFile
		List<FTPFile> scanFTPFiles = scanFile(downloadPath, variableMap);

		// 3、下载扫描后的文件scanFile
		List<FTPFile> downFTPFile = downloadFile(scanFTPFiles, localPath, variableMap);

		Iterator<FTPFile> iterator = downFTPFile.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getName());
		}

		// // 4、文件删除 deleteFile
		// List<FTPFile> deleteFTPFile = deleteFile(downFTPFile, downloadPath,
		// variableMap);
		//
		// // 5、断开连接登陆 AOP
		// ftpUtil.downLoadFile(downloadPath, localPath, name);

		return false;
	}

	public boolean fileOutGoing(String businessName, String uploadPath, String localPath) {
		return false;
	}

	/**
	 * 扫描downloadPath目录下的文件，并将返回List集合
	 * 
	 * @param downloadPath
	 * @return
	 */
	private List<FTPFile> scanFile(String downloadPath) {
		List<FTPFile> ftpFiles = null; // 用于接受扫描到的FTPFile
		try {
			ftpFiles = new ArrayList<>();
			FTPFile[] firstScanftpFiles = ftpUtil.list(downloadPath);
			Thread.sleep(1000L);
			FTPFile[] secondScanftpFiles = ftpUtil.list(downloadPath);

			// 布尔值flag，判断首次扫描和二次扫描结果是否存在及不为零
			boolean exist = firstScanftpFiles != null && firstScanftpFiles.length > 0 && secondScanftpFiles != null
					&& secondScanftpFiles.length > 0;

			if (exist) {
				// 以第二次扫描为基本，逐个对比第一次扫描，若两次扫描都存在的文件则保存到List（ftpFiles）中
				for (FTPFile secondftpFile : secondScanftpFiles) {
					for (FTPFile firstftpFile : firstScanftpFiles) {

						// 比较两份FTPFile的名字和大小是否相等。（这里使用的FTPFile的自身方法）
						boolean same = secondftpFile.getName().equals(firstftpFile.getName())
								&& secondftpFile.getSize() == firstftpFile.getSize();
						if (same) {
							ftpFiles.add(secondftpFile);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ftpFiles;
	}

	/**
	 * 扫描downloadPath目录下的文件，并将返回List集合
	 * 
	 * <p>
	 * 该方法相对于scanFile(String downloadPath)多了日志的相关操作。
	 * 
	 * @param downloadPath
	 *            下载目录
	 * @param variableMap
	 * @return
	 */
	private List<FTPFile> scanFile(String downloadPath, HashMap<String, String> variableMap) {
		List<FTPFile> ftpfiles = null;
		try {
			ftpfiles = scanFile(downloadPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ftpfiles;
	}

	/**
	 * 将FTPFile文件下载到本地localPath
	 * 
	 * @param ftpFiles	待下载的FTP文件
	 * @param localPath	下载到本地的地址
	 * @return
	 */
	private List<FTPFile> downloadFile(List<FTPFile> ftpFiles, String localPath) {
		List<FTPFile> downloadFiles = null;
		try {
			downloadFiles = new ArrayList<>();
			File locaFile = new File("localPath");
			OutputStream outputStream = new FileOutputStream(locaFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return downloadFiles;
	}

	/**
	 * 将FTPFile文件下载到本地localPath
	 * <p>
	 * 该方法与downloadFile(List<FTPFile> ftpFiles, String localPath)多了日志功能
	 * 
	 * @param ftpFiles
	 *            待下载的FTP文件
	 * @param localPath
	 *            下载到本地的地址
	 * @param variableMap
	 *            日志
	 * @return
	 */
	private List<FTPFile> downloadFile(List<FTPFile> ftpFiles, String localPath, HashMap<String, String> variableMap) {
		// HashMap操作
		try {
			return downloadFile(ftpFiles, localPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<FTPFile> deleteFile(List<FTPFile> ftpFiles, String downloadPath, HashMap<String, String> variableMap) {
		return null;
	}
}
