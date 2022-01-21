/**
 * 
 */
package com.musala.enums;

/**
 * @author aeltayary
 *
 */
public enum ModelEnum {
	IDLE(1), LOADING(2), LOADED(3), DELIVERING(4), DELIVERED(5),
	RETURNING(6);
	private Integer id;

	ModelEnum(int id) {
		this.id = id;
		
	}
	public Integer getId() {
		return id;
	}
}
