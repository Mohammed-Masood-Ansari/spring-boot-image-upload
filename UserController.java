package com.springboot.simplespringboot.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.simplespringboot.dao.UserDao;
import com.springboot.simplespringboot.dto.User;

import jakarta.servlet.ServletException;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserDao userDao;

	/*
	 * for logging purpose
	 */
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping(value = "/userData")
	public ResponseEntity<?> saveUserData(@RequestParam("file") MultipartFile file, @RequestParam("userData") String userData) throws IOException, ServletException{
		
		this.logger.info("user add request....");
		logger.info("file info {}", file.getOriginalFilename());
		logger.info("user {}",userData);
		
		/*
		 * converting string to object
		 */
		try {
			User user=mapper.readValue(userData, User.class);
	
			
			userDao.saveUser(user, file);
			
			
			
		} catch (JsonProcessingException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
		}
		return ResponseEntity.ok("done");
	}
}
