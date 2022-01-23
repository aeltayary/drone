package com.musala.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;




public class MedicationDto implements Serializable {
	private static final long serialVersionUID = 1L;


	@NotBlank(message = "medicationCode can not be null or empty ")
	@Pattern(regexp = "^[A-Z0-9_-]*$", message = "upper case letters, underscore and numbers are only allowed for this field ")
	private String medicationCode;;

	@NotBlank(message = "medicationName can not be null or empty")
	@Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "letters, underscore and numbers only allowed for this field ")
	private String medicationName;;

	@NotNull(message = "medicationWeight can not be null")
	private Integer medicationWeight;

	private String medicationImage;;

	private Integer createdBy;

	private Integer modifiedBy;

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

	public String getMedicationImage() {
		return medicationImage;
	}

	public void setMedicationImage(String medicationImage) {
		this.medicationImage = medicationImage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		return "MedicationDto [medicationCode=" + medicationCode + ", medicationName=" + medicationName
				+ ", medicationWeight=" + medicationWeight + ", medicationImage=" + medicationImage + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + "]";
	}

}