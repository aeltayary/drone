package com.musala.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

/**
 * The persistent class for the drone_battery_audit database table.
 * 
 */
@Entity
@Table(name = "drone_audit")
@NamedQuery(name = "DroneAudit.findAll", query = "SELECT d FROM DroneAudit d")
public class DroneAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "activity_id")
	private int activityId;

	@Column(name = "created", insertable = false, updatable = false)
	@CreatedDate
	private Timestamp created;

	@Column(name = "old_value")
	private String oldVlaue;

	@Column(name = "new_value")
	private String newValue;

	@ManyToOne
	private Drone drone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getOldVlaue() {
		return oldVlaue;
	}

	public void setOldVlaue(String oldVlaue) {
		this.oldVlaue = oldVlaue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}


}