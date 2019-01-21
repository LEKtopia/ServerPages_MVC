package edu.cvtc.web.search.impl;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import edu.cvtc.web.comparators.AgeComparator;
import edu.cvtc.web.comparators.LastNameComparator;
import edu.cvtc.web.comparators.SortBy;
import edu.cvtc.web.dao.PersonDao;
import edu.cvtc.web.dao.impl.PersonDaoImpl;
import edu.cvtc.web.exception.PersonSearchException;
import edu.cvtc.web.model.Person;
import edu.cvtc.web.predicates.FirstNamePredicate;
import edu.cvtc.web.predicates.LastNamePredicate;
import edu.cvtc.web.search.PersonSearch;

/**
 * @author Project Skeleton
 *
 */
public class PersonSearchImpl implements PersonSearch {
	
	private PersonDao personDao = new PersonDaoImpl();

	@Override
	public List<Person> retrievePeople(final String sortType) throws PersonSearchException {
		try {
			final List<Person> people = personDao.retrievePeopleFromDatabase();
			if (null != sortType) {
				sortPeople(people, sortType);
			}
			return people;
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersonSearchException("List of people could not be retrieved from the worksheet.");
		}
	}

	private void sortPeople(final List<Person> people, final String sortType) {
		switch (sortType) {
		case SortBy.AGE:
			Collections.sort(people, new AgeComparator());
			break;
		case SortBy.LASTNAME:
			Collections.sort(people, new LastNameComparator());
			break;
		default:
			break;
		}
	}

	@Override
	public List<Person> findPeopleByLastName(final String lastName) throws PersonSearchException {
		try {
			final List<Person> people = personDao.retrievePeopleFromDatabase();
			return Lists.newArrayList(Collections2.filter(people, new LastNamePredicate(lastName)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersonSearchException("Could not find person(s) by last name.");
		}
	}

	@Override
	public List<Person> findPeopleByFirstName(final String firstName) throws PersonSearchException {
		try {
			final List<Person> people = personDao.retrievePeopleFromDatabase();
			return Lists.newArrayList(Collections2.filter(people, new FirstNamePredicate(firstName)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersonSearchException("Could not find person(s) by last name.");
		}
	}

}
