package com.example.demo.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileModel;
import com.example.demo.model.FileResponse;

@Service
public interface FileService {
		
	public FileResponse store(MultipartFile file) throws IOException, SerialException, SQLException;
	public FileResponse getFileById(String id)throws IOException, SQLException;
	public List<FileResponse> getFileList();
	public FileResponse mapToFileResponse(FileModel filemodel) throws IOException, SQLException;
	public void deletefile(String id);
	
	
}
