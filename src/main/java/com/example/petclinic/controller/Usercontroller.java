package com.example.petclinic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.petclinic.model.Users;
import com.example.petclinic.service.UserService;

@RestController
@RequestMapping("/api/v1/users/")
@CrossOrigin(value = "http://localhost:4200/")
public class Usercontroller {

	@Autowired
	UserService userService;

//	@PostMapping("add")  /*/{id}*/    /*w*/
//	public ResponseEntity<?> create(/*@PathVariable Integer id,*/ @RequestBody Users users) {
//		try {
//			Users user = userService.save(users);
//			return ResponseEntity.ok(user);
//		} catch(Exception e) {
//			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@PostMapping("create")
	public ResponseEntity<?> create(@RequestBody Users users) {
		try {
			Users user = userService.save(users);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}		

	@PostMapping("update") /* w */
	public ResponseEntity<?> update(@RequestBody Users users) {
		try {
			Boolean user = userService.updateUser(users);
			if (user)
				return new ResponseEntity<String>(HttpStatus.OK);
			else
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}") /* w */
	public ResponseEntity<?> getUser(@PathVariable Integer id) {
		Users user = userService.findById(id);
		if (null != user) {
			return ResponseEntity.ok(user);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

//	@GetMapping("pet_owners")                  /*w*/
//	public List<Users> petOwners() {
//		int petOnwerRoleId = 2;
//		return userService.getUsersByRole(petOnwerRoleId);
//	}



	@GetMapping("all") /* w */
	public List<Users> retrieveAllUsers() {
		return userService.findAll();
	}

	@GetMapping("role/{roleId}")
	public ResponseEntity<?> getUsers(@PathVariable int roleId) {
		try {
			List<Users> users = userService.getUsersByRole(roleId);
			return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("delete/{userId}") /* w */
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
		try {
			Boolean isDeleted = userService.deleteById(userId);
			if (isDeleted)
				return new ResponseEntity<String>(HttpStatus.OK);
			else
				return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
