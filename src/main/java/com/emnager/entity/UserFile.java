package com.emnager.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERFILE")
public class UserFile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int fileId;
	private String fileName;
	private long fileSize;
	private LocalDate createdDate;
	private String filePath;

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public UserFile(String fileName, long fileSize, LocalDate createdDate, String filePath) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.createdDate = createdDate;
		this.filePath = filePath;
	}

	public UserFile() {
		super();
	}

}
