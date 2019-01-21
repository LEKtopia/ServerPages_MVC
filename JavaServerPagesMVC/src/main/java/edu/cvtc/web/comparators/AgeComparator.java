package edu.cvtc.web.comparators;

import java.util.Comparator;

import edu.cvtc.web.model.Person;

/**
 * @author Project Skeleton
 * 
 */
public class AgeComparator implements Comparator<Person> {

	@Override
	public int compare(final Person firstPerson, final Person secondPerson) {
		return firstPerson.getAge().compareTo(secondPerson.getAge());
	}

}
