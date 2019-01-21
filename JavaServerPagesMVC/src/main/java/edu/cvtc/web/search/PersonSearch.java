package edu.cvtc.web.search;

import java.util.List;

import edu.cvtc.web.exception.PersonSearchException;
import edu.cvtc.web.model.Person;

/**
 * @author Project Skeleton
 *
 */
public interface PersonSearch {

	List<Person> retrievePeople(String sortType) throws PersonSearchException;

	List<Person> findPeopleByLastName(String lastName) throws PersonSearchException;

	List<Person> findPeopleByFirstName(String firstName) throws PersonSearchException;

}
