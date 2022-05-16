package com.example.petclinic.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;

import com.example.petclinic.model.Pets;
import com.example.petclinic.model.PetsTypes;
import com.example.petclinic.model.Users;
import com.example.petclinic.repository.PetsRepository;
import com.example.petclinic.repository.PetsTypesRepository;
import com.example.petclinic.repository.UsersRepository;


@Service
public class PetsService {
	
	
	@Autowired
	PetsRepository  petsRepository ;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	PetsTypesRepository petsTypesRepository;
	
	public void save(Integer id, Pets user ) {
		Users loggedUser = usersRepository.getById(id);
		int roleId = loggedUser.getRole().getId();
		if (roleId != 2) {
			throw new IllegalArgumentException("Unauthorized user");
		}
		petsRepository.save(user);
	}
	
	public Pets createPet(Pets pet ) {
		pet.setDOB(new Date());
		Pets pet2 = petsRepository.save(pet);
		return pet2;
	}
	
	public List<Pets> getPetsByOwner(Integer petOwnerId) {
		List<Pets> petsList = petsRepository.findByUserId(petOwnerId);
		if (org.springframework.util.CollectionUtils.isEmpty(petsList)) {
			throw new NullPointerException("Invalid petOwner Id");
		} 
		return petsList;
	}
	
	public void update(Integer id, String name, Date DOB) {
		Pets pet = petsRepository.getById(id);
		if (null == pet) {
			throw new NullPointerException("Invalid user Id");
		} 
		pet.setName(name);
		pet.setDOB(DOB);
		
		petsRepository.save(pet);
	}
	
	public Pets findById( Integer id ) {
		return petsRepository.findById( id).get();
	}
	
	public List<Pets> findAll () {
		return petsRepository.findAll();
	}
	
	public void deleteById (Integer id) {
		Users loggedUser = usersRepository.getById(id);
		int roleId = loggedUser.getRole().getId();
		if (roleId != 2) {
			throw new IllegalArgumentException("Unauthorized user");
		}
		petsRepository.deleteById(id);
	}
	
	public List<PetsTypes> getPetTypes() {
		List<PetsTypes> petTypesList = petsTypesRepository.findAll();	
		return petTypesList;
	}
}
