package com.brd.service;

import com.brd.entity.MyFile;

public interface MyFileService {
	void addFile(MyFile file);

	boolean removeFile(String fileId);
}
