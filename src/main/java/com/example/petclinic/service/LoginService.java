package com.example.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petclinic.model.Users;
import com.example.petclinic.repository.UsersRepository;

@Service
public class LoginService {
	@Autowired
	UsersRepository usersRepository;
	
	public Users checkLogin(Users user) {
		Users logUser = usersRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		return logUser;

	} 
}
