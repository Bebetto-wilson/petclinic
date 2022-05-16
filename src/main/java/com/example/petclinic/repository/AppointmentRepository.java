package com.example.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.petclinic.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	@Query("SELECT a FROM Appointment a WHERE a.veterinarian.id = :id")
	List<Appointment> findByVeterinarianId(Integer id);
}
