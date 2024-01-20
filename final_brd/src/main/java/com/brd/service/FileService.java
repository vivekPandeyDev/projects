package com.brd.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.brd.dto.FileUploadCustomerDto;
import com.brd.entity.MyFile;

public interface FileService {
	boolean save(CommonsMultipartFile file, String path, MyFile myFile);
	boolean remove(String path,String fileName) throws FileNotFoundException;
	MyFile getFileDetails(String fileName);
	List<List<Optional<String>>> read(String filePath);
	FileUploadCustomerDto generateCustomer(List<Optional<String>> list);
	List<MyFile> getAllFile();
	boolean updateReadStatus(String fileName,String status);
}
