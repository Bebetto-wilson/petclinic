package com.example.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.petclinic.model.Pets;

public interface PetsRepository extends JpaRepository<Pets, Integer> {
	List<Pets> findByUserId(int id);
}
