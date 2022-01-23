/**
 * 
 */
package com.musala.enums;

/**
 * @author aeltayary
 *
 */
public enum ModelEnum {

	LIGHTWEIGHT(1), MIDDLEWEIGHT(2), CRUSIEWEIGHTT(3), HEAVEYWEIGHT(4);

	private Integer id;


	ModelEnum(Integer id) {
		this.id = id;

	}

	public Integer getId() {
		return id;
	}

}
