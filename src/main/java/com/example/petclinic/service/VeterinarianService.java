package com.example.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petclinic.model.Appointment;
import com.example.petclinic.model.Users;
import com.example.petclinic.model.Veterinarian;
import com.example.petclinic.repository.UsersRepository;
import com.example.petclinic.repository.VeterinarianRepository;

@Service
public class VeterinarianService {

	@Autowired
	VeterinarianRepository veterinarianRepository;
	
	@Autowired
	UsersRepository usersRepository;
	public Veterinarian save(Integer id, Veterinarian veterinarian ) {
		Users loggedUser = usersRepository.getById(id);
		int roleId = loggedUser.getRole().getId();
		if (roleId != 1) {
			throw new IllegalArgumentException("Invalid admin");
		}
		return veterinarianRepository.save(veterinarian);
	}
	
	public Veterinarian findById( Integer id ) {
		return veterinarianRepository.findById( id).get();
	}
	
	public List<Veterinarian> findAll () {
		return veterinarianRepository.findAll();
	}
	
	public void deleteById (Integer id, Integer userId) {
		Users loggedUser = usersRepository.getById(id);
		int roleId = loggedUser.getRole().getId();
		if (roleId != 1) {
			throw new IllegalArgumentException("Invalid admin");
		}
		veterinarianRepository.deleteById(id);
	}
}
