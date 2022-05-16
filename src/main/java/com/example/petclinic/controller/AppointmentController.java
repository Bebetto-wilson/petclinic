package com.example.petclinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.petclinic.model.Appointment;
import com.example.petclinic.model.Users;
import com.example.petclinic.service.AppointmentService;
@RestController
@RequestMapping("/appointment/")
public class AppointmentController {

	@Autowired
	AppointmentService  appointmentService ; 
	
	
//	public ResponseEntity<?> addAppointment(@RequestBody Appointment appointment) {
//		//appointmentService.save(appointment);
//		try {
//			Appointment appointment2 = appointmentService.save(appointment);
//			if (null == appointment2) return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//			return ResponseEntity.ok(appointment2);
//		} catch(Exception e) {
//			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@PostMapping("add")
	public ResponseEntity<?> addAppointment(@RequestBody Appointment appointment) {
		//appointmentService.save(appointment);
		try {
			Appointment appointment2 = appointmentService.save(appointment);
			if (null == appointment2) return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.ok(appointment2);
		} catch(Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}")
	public Appointment getAppointment(@PathVariable Integer id) {
		return appointmentService.findById(id);
	}

	@GetMapping("vetId/{id}")
	public List<Appointment> getAppointmentByVetId(@PathVariable Integer id) {
		return appointmentService.findByVetId(id);
	}

	
	@GetMapping("allusers")
	public List<Appointment> retrieveAllAppointment() {
		return appointmentService.findAll();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteAppointment(@PathVariable Integer id) {
		try {
			Boolean isDeleted = appointmentService.deleteById(id);
			if (isDeleted)
				return new ResponseEntity<String>(HttpStatus.OK);
			else
				return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
}
	
	@PostMapping("update")
	public ResponseEntity<?> update(@RequestBody Appointment appointments) {
		try {
			Boolean appointment = appointmentService.updateUser(appointments);
			if(appointment)
				return new ResponseEntity<String>(HttpStatus.OK);
			else
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
					
		} catch(Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
