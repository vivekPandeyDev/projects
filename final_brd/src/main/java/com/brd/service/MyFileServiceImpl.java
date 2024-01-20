package com.brd.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.brd.dao.MyFileDao;
import com.brd.entity.MyFile;

@Service
@Transactional
public class MyFileServiceImpl implements MyFileService {
	private final MyFileDao fileDao;
	
	
	public MyFileServiceImpl(MyFileDao fileDao) {
		super();
		this.fileDao = fileDao;
	}

	@Override
	public void addFile(MyFile file) {
		fileDao.addFile(file);
	}

	@Override
	public boolean removeFile(String fileId) {
		MyFile file = fileDao.getFile(fileId);
		if(file == null)
			return false;
		fileDao.removeFile(file);
		return true;
	}

}
