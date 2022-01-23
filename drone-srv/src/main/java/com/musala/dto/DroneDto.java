package com.musala.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.musala.enums.ModelEnum;
import com.musala.enums.StateEnum;


public class DroneDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "serial number can not be blank")
	@Size(max = 100, message = "serial number max length is 100")
	private String serialNumber;

	@NotNull(message = "weightLimit can not be null")
	@Max(value = 500 , message ="max weight limit is 500gr" )
	private Integer weightLimit;


	@NotNull(message = "model can not be null")
	private ModelEnum model;

	@NotNull(message = "state can not be null")
	private StateEnum state;

	@NotNull(message = "battery can not be null")
	private Integer battery;

	private Integer createdBy;

	private Integer modifiedBy;

	

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(Integer weightLimit) {
		this.weightLimit = weightLimit;
	}

	public ModelEnum getModel() {
		return model;
	}

	public void setModel(ModelEnum model) {
		this.model = model;
	}

	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}

	public Integer getBattery() {
		return battery;
	}

	public void setBattery(Integer battery) {
		this.battery = battery;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "DroneDto [serialNumber=" + serialNumber + ", weightLimit=" + weightLimit + ", model=" + model
				+ ", state=" + state + ", battery=" + battery + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + "]";
	}

	
}