package com.springboot.simplespringboot.dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.simplespringboot.dto.User;
import com.springboot.simplespringboot.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository repository;
	
	public User saveUser(User user, MultipartFile multipartFile) throws IOException {
		
		return repository.save(User.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.image(ImageUtil.compressImage(multipartFile.getBytes())).build());
	}
}
