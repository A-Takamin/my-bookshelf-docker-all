package com.example.mybookshelf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mybookshelf.entity.User;
import com.example.mybookshelf.form.UserForm;
import com.example.mybookshelf.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	/**
	 * ユーザの新規作成
	 * @param uid: firebaseのToken
	 */
	public void createUser(String uid, UserForm userForm) {
		User user = new User();
		user.setUid(uid);
		user.setName(userform.name);
		userRepo.save(user);
	}
	

	
}
