package com.brd.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.brd.dto.FileUploadCustomerDto;
import com.brd.entity.MyFile;
import com.brd.entity.RecordDetail;
import com.brd.entity.TempCustomer;
import com.brd.service.FileService;
import com.brd.service.MakerService;
import com.brd.validation.CustomerValidation;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/maker")
@Log4j2
public class FileController {

	private final FileService fileService;
	private final MakerService makerService;
	private final String FILE_TABLE_URL = "redirect:/maker/customer/fileList";
	private final ModelMapper mapper;
	
	public FileController(FileService fileService,MakerService makerService,ModelMapper mapper) {
		super();
		this.fileService = fileService;
		this.makerService = makerService;
		this.mapper = mapper;
	}

	@GetMapping("/customer/fileList")
	public String getUploadFileList(Model model) {
		model.addAttribute("files", fileService.getAllFile());
		return "maker_customer_files";
	}

	@GetMapping("/customer/file/remove/{filename}")
	public String removeFile(@PathVariable("filename") String fileName, HttpSession session, Model model) {
		try {
			fileService.remove(getFilePath(session, fileName), fileName);
		} catch (FileNotFoundException e) {
			model.addAttribute("delete_msg", e.getMessage());
			return FILE_TABLE_URL;
		}
		model.addAttribute("delete_msg", "file is removed successfully");
		return FILE_TABLE_URL;
	}

	@GetMapping("/customer/file/upload/{filename}")
	public String uploadCustomer(@PathVariable("filename") String fileName, HttpSession session, Model model) {
		MyFile file =  fileService.getFileDetails(fileName);
		if(file == null) {
			model.addAttribute("delete_msg", "no file find to update");
			return FILE_TABLE_URL;
		}
		List<List<Optional<String>>> customersData = fileService.read(getFilePath(session, fileName));
		List<FileUploadCustomerDto> customers = new ArrayList<>();
		List<TempCustomer> tempCustomers = new ArrayList<>();
		FileUploadCustomerDto generateCustomer= new FileUploadCustomerDto();
		for (List<Optional<String>> customerData : customersData) {
			try {
				generateCustomer = fileService.generateCustomer(customerData);
				CustomerValidation.validCustomer(generateCustomer);
				TempCustomer tempCustomer = convertGeneratedCustomerToTempCustomer(generateCustomer);
				tempCustomers.add(tempCustomer);
				generateCustomer.setAccepted(true);
				generateCustomer.setMessage("accepted");
			} catch (Exception e) {
				generateCustomer.setAccepted(false);
				generateCustomer.setMessage("rejected: "+e.getMessage());
				log.error("cannot insert customer : {}",e.getMessage());
			}
			customers.add(generateCustomer);
		}
		model.addAttribute("customers",customers);
		if(file.getIsFileRead().equals("F"))
			makerService.insertOrUpdateBatch(tempCustomers, 20);
		fileService.updateReadStatus(fileName, "T");
		
		return "customer_list";
	}

	@PostMapping("/customer/uploadFile")
	public String uploadFile(@RequestParam("myFile") CommonsMultipartFile file,
			@RequestParam("filename") String fileName, HttpSession session, Authentication auth, Model model) {

		String rejectionLevel = getRejectionLevel(file.getOriginalFilename());
		if (rejectionLevel.equals("F")) {
			model.addAttribute("reject_msg", "only file with txt extension allowed");
			return FILE_TABLE_URL;
		} else if (fileService.getFileDetails(fileName) != null) {
			model.addAttribute("reject_msg", "file already exists try with another file name");
			return FILE_TABLE_URL;
		}

		String path = getFilePath(session, fileName);
		log.info("base path : " + path);

		MyFile myFile = new MyFile(fileName, LocalDate.now(), auth.getName(), "R", "F");
		fileService.save(file, path, myFile);
		model.addAttribute("accept_msg", "file uploaded successfully");
		return FILE_TABLE_URL;
	}

	private String getFilePath(HttpSession session, String fileName) {
		String basePath = session.getServletContext().getRealPath("/");
		return basePath + "public" + File.separator + "files" + File.separator + fileName + ".txt";
	}

	private String getRejectionLevel(String fileName) {
		if (fileName == null)
			return "F";
		String extension = "";
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}
		if (extension.equals("txt")) {
			return "R";
		} else {
			return "F";
		}
	}
	
	private TempCustomer convertGeneratedCustomerToTempCustomer(FileUploadCustomerDto generateCustomer) {
		TempCustomer tempCustomer = new TempCustomer();
		mapper.map(generateCustomer,tempCustomer);
		tempCustomer.getAddress().setPincode(generateCustomer.getPinCode());
		tempCustomer.setPrimaryContactNumber(generateCustomer.getContactPerson());
		RecordDetail recordDetail = new RecordDetail(generateCustomer.getCreateDate(), generateCustomer.getCreatedBy(), generateCustomer.getModifiedDate(), generateCustomer.getModifiedBy(), generateCustomer.getAuthorizedDate(), generateCustomer.getAuthorizedBy());
		tempCustomer.setRecordDetail(recordDetail);
		return tempCustomer;
	}

}
