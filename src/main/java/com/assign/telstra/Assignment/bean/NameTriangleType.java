package com.assign.telstra.Assignment.bean;
public enum NameTriangleType {
	SCALENE("Scalene"),
	ISOSCELES("Isosceles"),
	EQUILATERAL("Equilateral"),
	ERROR("Error");
	
	private String name;

	/**
	 * 
	 * @param name
	 */
	private NameTriangleType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
