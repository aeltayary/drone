package com.musala.db.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

/**
 * The persistent class for the cargo database table.
 * 
 */
@Entity
@NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// bi-directional many-to-one association to Drone
	@ManyToOne
	private Drone drone;

	@Column(name = "medication_code")
	private String medicationCode;

	@Column(name = "medication_name")
	private String medicationName;

	@Column(name = "medication_weight")
	private Integer medicationWeight;

	@Column(name = "medication_image")
	@Lob
	private byte[] image;

	@CreatedDate
	private Timestamp created;

	@Column(name = "created_by")
	private Integer createdBy;

	@LastModifiedDate
	private Timestamp modified;

	@Column(name = "modified_by")
	private Integer modifiedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	public String getMedicationCode() {
		return medicationCode;
	}

	public void setMedicationCode(String medicationCode) {
		this.medicationCode = medicationCode;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public Integer getMedicationWeight() {
		return medicationWeight;
	}

	public void setMedicationWeight(Integer medicationWeight) {
		this.medicationWeight = medicationWeight;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	
}