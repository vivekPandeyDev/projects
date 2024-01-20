package com.brd.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.brd.dao.MyFileDao;
import com.brd.dto.FileUploadCustomerDto;
import com.brd.entity.MyFile;
import com.brd.enums.CustomerFlag;
import com.brd.enums.RecordStatus;
import com.brd.exception.CustomMakerException;
import com.brd.utility.BasicUtility;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class FileServiceImpl implements FileService {
	private final MyFileDao fileDao;

	public FileServiceImpl(MyFileDao fileDao) {
		super();
		this.fileDao = fileDao;
	}
	
	

	@Override
	public boolean updateReadStatus(String fileName, String status) {
		return fileDao.updateReadStatus(fileName, status);
	}



	/*
	 * first saving the file and then adding the file to the database
	 */
	@Override
	public boolean save(CommonsMultipartFile file, String path, MyFile myFile) {
		byte[] data = file.getBytes();
		try (FileOutputStream fs = new FileOutputStream(new File(path))) {
			fs.write(data);
		} catch (Exception e) {
			throw new CustomMakerException(e.getMessage());
		}
		fileDao.addFile(myFile);
		return false;
	}

	@Override
	public boolean remove(String path, String fileName) throws FileNotFoundException {
        MyFile file = fileDao.getFile(fileName);
		try {
            Files.deleteIfExists(Paths.get(path));
        }catch(Exception e) {
        	log.error("error removing file: {}",e.getMessage());
        	if(file != null)
        		fileDao.removeFile(file);
        	throw new FileNotFoundException("no file found");
        }
		if(file == null)
			return false;
        fileDao.removeFile(fileDao.getFile(fileName));
		return true;
	}

	@Override
	public MyFile getFileDetails(String fileName) {
		return fileDao.getFile(fileName);
	}

	@Override
	public List<MyFile> getAllFile() {
		return fileDao.getAllFile();
	}

	@Override
	public List<List<Optional<String>>> read(String filePath) {
		List<List<Optional<String>>> lists = new ArrayList<>();
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			while (fileReader.ready()) {
				String customerData = fileReader.readLine();
				List<String> stringList = Arrays.stream(customerData.split("~"))
						.map(val -> (val.length() == 0 ? null : val)).collect(Collectors.toList());
				for (int i = stringList.size(); i < 16; i++) {
					stringList.add(null);
				}
				List<Optional<String>> optionalList = stringList.stream()
						.map(val -> val == null ? Optional.ofNullable(val) : Optional.of(val)).toList();
				lists.add(optionalList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error while reading: {}", e.getMessage());
		}
		return lists;
	}

	public FileUploadCustomerDto generateCustomer(List<Optional<String>> list) {
		String customerCode = list.get(0).orElseThrow(() -> new NullPointerException("customer code cannot be null"));
		String customerName = list.get(1).orElseThrow(() -> new NullPointerException("customer Name cannot be null"));
		String address1 = list.get(2).orElseThrow(() -> new NullPointerException("Address 1 cannot be null"));
		int pinCode = Integer
				.parseInt(list.get(4).orElseThrow(() -> new NullPointerException("pinCode cannot be null")));
		String email = list.get(5).orElseThrow(() -> new NullPointerException("email cannot be null"));
		String contactPerson = list.get(7).orElseThrow(() -> new NullPointerException("contact person cannot be null"));
		RecordStatus status = RecordStatus
				.valueOf(list.get(8).orElseThrow(() -> new NullPointerException("record Status cannot be null")));
		CustomerFlag flag = CustomerFlag
				.valueOf(list.get(9).orElseThrow(() -> new NullPointerException("flag cannot be null")));
		LocalDate createDate = BasicUtility
				.dateParse(list.get(10).orElseThrow(() -> new NullPointerException("create Date cannot be null")));
		String createdBy = list.get(11).orElseThrow(() -> new NullPointerException("created by cannot be null"));
		FileUploadCustomerDto customer = new FileUploadCustomerDto(customerCode, customerName, address1, pinCode, email,
				contactPerson, status, flag, createDate, createdBy);

		String address2 = list.get(3).orElse(null);
		long contactNumber = BasicUtility
				.parseLong(list.get(6).orElseThrow(() -> new NumberFormatException("not a valid contact number")));
		LocalDate modifiedDate = BasicUtility.dateParse(list.get(12).orElse(null));
		String modifiedBy = list.get(13).orElse(null);
		LocalDate authorizedDate = BasicUtility.dateParse(list.get(14).orElse(null));
		String authorizedBy = list.get(15).orElse(null);
		customer.setAddress2(address2);
		customer.setContactNumber(contactNumber);
		customer.setModifiedDate(modifiedDate);
		customer.setModifiedBy(modifiedBy);
		customer.setAuthorizedDate(authorizedDate);
		customer.setAuthorizedBy(authorizedBy);
		return customer;
	}

}
