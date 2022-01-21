package com.musala.dto;

import java.io.Serializable;

import com.musala.enums.ModelEnum;
import com.musala.enums.StateEnum;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import org.hibernate.validator.constraints.Length;

@Schema
public class DroneDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Length(max = 100)
	private String serialNumber;

	private Integer weightLimit;

	private ModelEnum model;

	private StateEnum state;

	private Integer battery;

	private Integer createdBy;

	private Integer modifiedBy;

	private List<MedicationDto> cargo;

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

	public List<MedicationDto> getCargo() {
		return cargo;
	}

	public void setCargo(List<MedicationDto> cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "DroneDto [serialNumber=" + serialNumber + ", weightLimit=" + weightLimit + ", model=" + model
				+ ", state=" + state + ", battery=" + battery + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", cargo=" + cargo + "]";
	}

}