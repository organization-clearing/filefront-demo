package org.dongyao.modle;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 业务线的实体类
 *
 * @author HONG
 * @date: 2018年11月15日 下午2:35:09
 */
@XStreamAlias("business")
public class Business {
	
	@XStreamAsAttribute
	private String name;
	
	@XStreamAlias("incoming")
	private List<MerchantFile> incomingFiles;
	
	@XStreamAlias("outgoing")
	private List<MerchantFile> outgoingFiles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<MerchantFile> getIncomingFiles() {
		return incomingFiles;
	}

	public void setIncomingFiles(List<MerchantFile> incomingFiles) {
		this.incomingFiles = incomingFiles;
	}

	public List<MerchantFile> getOutgoingFiles() {
		return outgoingFiles;
	}

	public void setOutgoingFiles(List<MerchantFile> outgoingFiles) {
		this.outgoingFiles = outgoingFiles;
	}

	


	@Override
	public String toString() {
		return "Business [name=" + name + ", incomingFiles=" + incomingFiles + ", outgoingFiles=" + outgoingFiles + "]";
	}




	/*
	 * 设计存在疑问：
	 * 		（1）MerchantFile是否应该作为内部类存在，或说作为内部类的目的
	 * 		（2）MerchantFile内部内public的目的
	 */
	@XStreamAlias("MerchantFile")
	public class MerchantFile {

		@XStreamAsAttribute
		private String name;
		@XStreamAsAttribute
		private String downloadPath;
		@XStreamAsAttribute
		private String uploadPath;
		@XStreamAsAttribute
		private String fileCoreBackupPath;
		@XStreamAsAttribute
		private String localPath;
		@XStreamAsAttribute
		private String checkRules;
		@XStreamAsAttribute
		private String backupPath;
		@XStreamAsAttribute
		private String isCompress;

		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDownloadPath() {
			return downloadPath;
		}

		public void setDownloadPath(String downloadPath) {
			this.downloadPath = downloadPath;
		}

		public String getUploadPath() {
			return uploadPath;
		}

		public void setUploadPath(String uploadPath) {
			this.uploadPath = uploadPath;
		}

		public String getFileCoreBackupPath() {
			return fileCoreBackupPath;
		}

		public void setFileCoreBackupPath(String fileCoreBackupPath) {
			this.fileCoreBackupPath = fileCoreBackupPath;
		}

		public String getLocalPath() {
			return localPath;
		}

		public void setLocalPath(String localPath) {
			this.localPath = localPath;
		}

		public String getCheckRules() {
			return checkRules;
		}

		public void setCheckRules(String checkRules) {
			this.checkRules = checkRules;
		}


		public String getIsCompress() {
			return isCompress;
		}

		public void setIsCompress(String isCompress) {
			this.isCompress = isCompress;
		}

		public String getBackupPath() {
			return backupPath;
		}

		public void setBackupPath(String backupPath) {
			this.backupPath = backupPath;
		}

		@Override
		public String toString() {
			return "MerchantFile [name=" + name + ", downloadPath=" + downloadPath + ", uploadPath=" + uploadPath
					+ ", fileCoreBackupPath=" + fileCoreBackupPath + ", localPath=" + localPath + ", checkRules="
					+ checkRules + ", backupPath=" + backupPath + ", isCompress=" + isCompress + "]";
		}
		

	}
}
