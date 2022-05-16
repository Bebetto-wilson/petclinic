package com.example.petclinic.service;

import java.util.List;
import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.petclinic.model.Pets;
import com.example.petclinic.model.Roles;
import com.example.petclinic.model.Users;
import com.example.petclinic.repository.RolesRepository;
import com.example.petclinic.repository.UsersRepository;

@Service
public class UserService {
	
	@Autowired
	RolesRepository  roleRepository ;
	
	@Autowired
	UsersRepository  userRepository ;
	
	
	public Users save(Users user ) {
		return userRepository.save(user);
	}

	public Users findById( Integer id ) {
		Optional<Users> user = userRepository.findById( id);
		boolean present = user.isPresent();
		if (present) {
			return user.get();
		}
		return null;
	}
		
	public List<Users> findAll () {
		return userRepository.findAll();
	}
	
	public Boolean deleteById ( Integer userId) {
		Boolean isDeleted = false;
		Optional<Users> user = userRepository.findById(userId);
		//int roleId = user.getRoleId();
		if(user.isPresent()) {
			userRepository.deleteById(userId);
			isDeleted = true;
		} else {
			isDeleted = false;
		}		
		return isDeleted;
	}
	
	
	public Boolean updateUser(Users user) {
		Optional<Users> findUser = userRepository.findById(user.getId());
		
		Boolean isUpdated = false;
		if(findUser.isPresent()) {
			userRepository.save(user);
			isUpdated = true;
		}
		else
			isUpdated = false;

		return isUpdated;
	}

	public List<Users> getUsersByRole (int roleId) {
		Roles role = new Roles();
		role.setId(roleId);
		List<Users> users  =  userRepository.findByRole(role);
		return users;
	}
	

//	public Boolean updateUser(Users user) {
//		Optional<Users> findUser = userRepository.findById(user.getId());
//		
//		Boolean isUpdated = false;
//		if(findUser.isPresent()) {
//			userRepository.save(user);
//			isUpdated = true;
//		}
//		else
//			isUpdated = false;
//			
//		return isUpdated;
//	}


	//public List<Users> getUsersByRole(int roleId) {
	//	Users users = userRepository.findByRoleId(roleId);
	//	return (List<Users>) users;
		// TODO Auto-generated method stub
		//return null;
	//}

}


