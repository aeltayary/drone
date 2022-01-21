package com.musala.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the model database table.
 * 
 */
@Entity
@NamedQuery(name="Model.findAll", query="SELECT m FROM Model m")
public class Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Timestamp created;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="model_name")
	private String modelName;

	private Timestamp modified;

	@Column(name="modified_by")
	private Integer modifiedBy;

	//bi-directional many-to-one association to Drone
	@OneToMany(mappedBy="model")
	private List<Drone> drones;

	public Model() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
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

	public List<Drone> getDrones() {
		return this.drones;
	}

	public void setDrones(List<Drone> drones) {
		this.drones = drones;
	}

	public Drone addDrone(Drone drone) {
		getDrones().add(drone);
		drone.setModel(this);

		return drone;
	}

	public Drone removeDrone(Drone drone) {
		getDrones().remove(drone);
		drone.setModel(null);

		return drone;
	}

}