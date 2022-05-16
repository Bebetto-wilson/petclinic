package com.example.petclinic.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "appointment")
public class Appointment {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "appoinment_date")
	private Date appointmentDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_id")
	private Pets pets;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "veterinarian_id")
	private Veterinarian veterinarian;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "is_approved")
	private boolean isApproved;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descriptionId) {
		this.description = descriptionId;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Appointment() {
	}

	
}
