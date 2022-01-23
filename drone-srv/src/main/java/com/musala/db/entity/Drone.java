package com.musala.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * The persistent class for the drone database table.
 * 
 */
@Entity
@NamedQuery(name="Drone.findAll", query="SELECT d FROM Drone d")
public class Drone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Integer battery;

	@Column(name="created",insertable = false,updatable = false)
	@CreatedDate
	private Timestamp created;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="modified",insertable = false,updatable = false)
	@LastModifiedDate
	private Timestamp modified;

	@Column(name="modified_by")
	private Integer modifiedBy;

	@Column(name="serial_number")
	private String serialNumber;

	@Column(name="weight_limit")
	private Integer weightLimit;

	//bi-directional many-to-one association to Cargo
	@OneToMany(mappedBy="drone")
	private List<Cargo> cargo;

	//bi-directional many-to-one association to Model
	@ManyToOne
	private Model model;

	//bi-directional many-to-one association to State
	@ManyToOne
	private State state;

	public Drone() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBattery() {
		return this.battery;
	}

	public void setBattery(Integer battery) {
		this.battery = battery;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getModified() {
		return this.modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getWeightLimit() {
		return this.weightLimit;
	}

	public void setWeightLimit(Integer weightLimit) {
		this.weightLimit = weightLimit;
	}

	public List<Cargo> getCargo() {
		return this.cargo;
	}

	public void setCargos(List<Cargo> cargo) {
		this.cargo = cargo;
	}

	public Cargo addCargo(Cargo cargo) {
		getCargo().add(cargo);
		cargo.setDrone(this);

		return cargo;
	}

	public Cargo removeCargo(Cargo cargo) {
		getCargo().remove(cargo);
		cargo.setDrone(null);

		return cargo;
	}

	public Model getModel() {
		return this.model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

}