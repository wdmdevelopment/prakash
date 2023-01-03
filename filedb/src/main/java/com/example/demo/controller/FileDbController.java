package com.example.demo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.NotValidFormatException;
import com.example.demo.model.FileResponse;
import com.example.demo.service.FileService;


@RestController
@RequestMapping("/file")
public class FileDbController {
	
	@Autowired
	private FileService fileDbService;
	
	@PostMapping(consumes = MediaType.ALL_VALUE)
	public FileResponse uploadFile(@RequestPart MultipartFile  file) 
					throws IOException, SerialException, SQLException, NotValidFormatException {
		
		if(file.isEmpty()) {
			throw new NotValidFormatException("Please upload  any file");
		}
		
		else {
		return fileDbService.store(file);
		}
		}
	
	
	
	
	@GetMapping("/{id}")
	public FileResponse getFile(@PathVariable("id") String id) throws IOException, SQLException {
		
		return fileDbService.getFileById(id);
		
	}
	
	@GetMapping("/get")
	public List<FileResponse> getFileList(){
		return fileDbService.getFileList();
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteById(@PathVariable("id") String id) {
		fileDbService.deletefile(id);
	}

}
