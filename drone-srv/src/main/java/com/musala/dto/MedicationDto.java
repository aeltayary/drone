package com.musala.dto;

import java.io.Serializable;


public class MedicationDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;

	private String name;

	private Integer weight;

	private String image;

	private Integer createdBy;

	private Integer modifiedBy;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
		return "MedicationDto [code=" + code + ", name=" + name + ", weight=" + weight + ", image=" + image
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + "]";
	}

	
	
	
}