package com.example.petclinic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.petclinic.model.Roles;
import com.example.petclinic.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

	Users findByEmail(String email);


	List<Users> findByRole(Roles role);


	Users findByEmailAndPassword(String email, String password);

}
