package com.brd.dao;

import java.util.List;

import com.brd.entity.MyFile;

public interface MyFileDao {
	void addFile(MyFile file);
	void removeFile(MyFile file);
	boolean updateReadStatus(String fileName,String status);
	MyFile getFile(String fileName);
	List<MyFile> getAllFile();
}
