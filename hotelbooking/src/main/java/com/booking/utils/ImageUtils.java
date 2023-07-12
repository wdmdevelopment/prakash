package com.booking.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class ImageUtils {
	public static String uploadFile(MultipartFile file, String folderPath) {
		Path fileUploadDirPath = Paths.get(folderPath).toAbsolutePath().normalize();
		try {
			Files.createDirectories(fileUploadDirPath);
			String fileName = generateFileName(file.getOriginalFilename());
			if (fileName == null)
				return null;
			Path pathFile = fileUploadDirPath.resolve(fileName);
			Files.copy(file.getInputStream(), pathFile, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (IOException exception) {
			System.out.println("Error while creating upload directory path - " + exception.getMessage());
			return null;
		}
	}

	public static String generateFileName(String originalFilename) {
		if (originalFilename == null)
			return null;
		int dotIndex = originalFilename.lastIndexOf(".");
		if (!(dotIndex > 0 && dotIndex < originalFilename.length() - 1))
			return null;
		UUID fileUUID = UUID.randomUUID();
		return fileUUID + "." + originalFilename.substring(dotIndex + 1);
	}
}
