package com.example.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petclinic.model.PetsTypes;
import com.example.petclinic.model.Users;
import com.example.petclinic.repository.PetsTypesRepository;
import com.example.petclinic.repository.UsersRepository;

@Service
public class PetsTypesService {
	
		@Autowired
		PetsTypesRepository petsTypesRepository ;
		
		
//		public void save(  PetsTypes user ) {
//			petsTypesRepository.save(user);
//		}
//		
		public PetsTypes findById( Integer id ) {
			return petsTypesRepository.findById( id).get();
		}
		
		public List<PetsTypes> findAll () {
			return petsTypesRepository.findAll();
		}
		
		public void deleteById (Integer id) {
			petsTypesRepository.deleteById(id);
		}
	}

