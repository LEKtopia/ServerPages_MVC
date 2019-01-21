package edu.cvtc.web.model;

import java.util.List;

/**
 * @author Project Skeleton
 * 
 */
public class Person {

	private String firstName;
	private String lastName;
	private Integer age;
	private String favoriteColor;
	private List<Hobby> hobbies;

	public Person(final String firstName, final String lastName, final int age,
			final String favoriteColor, final List<Hobby> hobbies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.favoriteColor = favoriteColor;
		this.hobbies = hobbies;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Integer getAge() {
		return age;
	}

	public String getFavoriteColor() {
		return favoriteColor;
	}

	public List<Hobby> getHobbies() {
		return hobbies;
	}

}
