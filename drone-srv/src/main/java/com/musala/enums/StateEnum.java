/**
 * 
 */
package com.musala.enums;

/**
 * @author aeltayary
 *
 */
public enum StateEnum {
	IDLE(1), LOADING(2), LOADED(3), DELIVERING(4), DELIVERED(5), RETURNING(6);
	private Integer id;

	StateEnum(int id) {
		this.id = id;

	}
	public Integer getId() {
		return id;
	}
}
