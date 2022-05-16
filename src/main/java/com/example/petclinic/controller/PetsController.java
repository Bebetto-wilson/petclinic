package com.example.petclinic.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.petclinic.model.Pets;
import com.example.petclinic.model.PetsTypes;
import com.example.petclinic.service.PetsService;

@RestController
@RequestMapping("/api/v1/pets/")
public class PetsController {
	
	
	@Autowired
	PetsService  petsService;
	
	
	@GetMapping("types")
	public ResponseEntity<?> petTypes() {
		try {
		List<PetsTypes> petTypes =  petsService.getPetTypes();
		return new ResponseEntity<List<PetsTypes>>(petTypes, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping("create")
	public ResponseEntity<?> addPet(@RequestBody Pets pet) {
		try {
			pet = petsService.createPet(pet);
			return ResponseEntity.ok(pet);
		} catch(Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("update/{id}")
	public void updatePet(@PathVariable Integer id, @RequestParam("name") String name, @RequestParam("DOB")Date DOB) {
		petsService.update(id, name,DOB);
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<?> getPet(@PathVariable Integer id) {
		try {
		Pets pet =  petsService.findById(id);
		return new ResponseEntity<Pets>(pet, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("petowner/{petOwnerId}")
	public ResponseEntity<?> retrieveAlaPets(@PathVariable Integer petOwnerId) {
		try {
			List<Pets> pets =  petsService.getPetsByOwner(petOwnerId);
			return new ResponseEntity<List<Pets>>(pets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("{id}/delete/{petId}")
	public void deleteUser(@PathVariable Integer id, @PathVariable Integer petId) {
		petsService.deleteById(id);
	}
	
}
