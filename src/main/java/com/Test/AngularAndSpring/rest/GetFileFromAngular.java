package com.Test.AngularAndSpring.rest;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class GetFileFromAngular {

	private final String baseFileDestination = "C:\\example\\example2\\Documents";

	@PostMapping(value = "/addFile")
	public ResponseEntity<?> addFile(@RequestParam("file") MultipartFile file) {
			String directoryString = baseFileDestination + "\\" + file.getOriginalFilename();
			File directory = new File(directoryString);
			if (!directory.exists()) {
				directory.mkdir();
			}
			directory = new File(directoryString + "\\" + file.getOriginalFilename());
			try {
				file.transferTo(directory);
			} catch (IllegalStateException | IOException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
			}
			return ResponseEntity.ok("File added");
	}
}
