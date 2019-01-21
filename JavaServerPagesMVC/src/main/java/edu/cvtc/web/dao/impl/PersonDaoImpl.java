package edu.cvtc.web.dao.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

import edu.cvtc.web.dao.PersonDao;
import edu.cvtc.web.exception.PersonDatabaseException;
import edu.cvtc.web.model.Hobby;
import edu.cvtc.web.model.Person;
import edu.cvtc.web.util.DBUtils;
import edu.cvtc.web.util.WorkbookUtility;

/**
 * @author Project Skeleton
 *
 */
public class PersonDaoImpl implements PersonDao {
	
	private static final String DROP_TABLE_PERSON = "drop table if exists person";
	private static final String CREATE_TABLE_PERSON = "create table person (id integer primary key autoincrement, firstName text, lastName text, age integer, favoriteColor text, hobby1 text, hobby2 text);";
	private static final String SELECT_ALL_FROM_PERSON = "SELECT * from person";

	@Override
	public void populateDatabase(final String filePath) throws Exception {
		
		final Connection connection = DBUtils.createConnection(DBUtils.CONNECTION);
		final Statement statement = connection.createStatement();
		
		try {
			
			statement.setQueryTimeout(DBUtils.TIMEOUT);
				
			statement.executeUpdate(DROP_TABLE_PERSON);
			statement.executeUpdate(CREATE_TABLE_PERSON);
			
			final File f = new File(filePath);
			List<Person> people = WorkbookUtility.retrievePeopleFromWorkBook(f);
			
			for (Person person : people) {
				String insertValues = "insert into person (firstName, lastName, age, favoriteColor, hobby1, hobby2) values ('" 
						+ person.getFirstName() + "', '" 
						+ person.getLastName() + "', "
						+ person.getAge() + ", '"
						+ person.getFavoriteColor() + "'";
				
				for (Hobby hobby : person.getHobbies()) {
					final String hobbyName = null != hobby.getName() ? hobby.getName() : "";
					insertValues += ", '" + hobbyName + "'";
				}
				
				if (person.getHobbies().isEmpty()) {
					insertValues += ", '', ''";
				} else if (person.getHobbies().size() == 1) {
					insertValues += ", ''";
				}
				
				insertValues += ");";
				
				System.out.println(insertValues); // Log a message to the Console so we can see the data being added. 
				
				statement.executeUpdate(insertValues);
			}
		} finally {
		    DBUtils.closeConnections(connection, statement);
		}
	}
	
	@Override
	public List<Person> retrievePeopleFromDatabase() throws PersonDatabaseException {
		
		final List<Person> people = new ArrayList<Person>();
		
		Connection connection = null;
		Statement statement = null;

		try {
			
			connection = DBUtils.createConnection(DBUtils.CONNECTION);
			statement = connection.createStatement();
			
			statement.setQueryTimeout(DBUtils.TIMEOUT);
			
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PERSON);
			
			while (resultSet.next()) {
				
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				int age = resultSet.getInt("age");
				String favoriteColor = resultSet.getString("favoriteColor");
				
				List<Hobby> hobbies = new ArrayList<Hobby>();
				String hobby1 = resultSet.getString("hobby1");

				if (!Strings.isNullOrEmpty(hobby1)) {
					hobbies.add(new Hobby(hobby1, null));
				}
				
				String hobby2 = resultSet.getString("hobby2");
				
				if (!Strings.isNullOrEmpty(hobby2)) {
					hobbies.add(new Hobby(hobby2, null));
				}
				Person person = new Person(firstName, lastName, age, favoriteColor, hobbies);
				people.add(person);
			}
			
			resultSet.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersonDatabaseException("Error retrieving people from database.");
		} finally {
			DBUtils.closeConnections(connection, statement);
		}
		
		return people;
	}
	
	@Override
	public Integer insertPerson(Person person) throws PersonDatabaseException {

		Connection connection = null;
		PreparedStatement insertStatement = null;
		
		try {
			connection = DBUtils.createConnection(DBUtils.CONNECTION);
			
			final String insert = "insert into person (firstName, lastName, age, favoriteColor, hobby1, hobby2) values (?,?,?,?,?,?)";
			insertStatement = connection.prepareStatement(insert);
			
			insertStatement.setString(1, person.getFirstName());
			insertStatement.setString(2, person.getLastName());
			insertStatement.setInt(3, person.getAge());
			insertStatement.setString(4, person.getFavoriteColor());
			insertStatement.setString(5, person.getHobbies().get(0).getName());
			insertStatement.setString(6, person.getHobbies().get(1).getName());
			
			insertStatement.setQueryTimeout(DBUtils.TIMEOUT);
			
			return insertStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersonDatabaseException("Error inserting person into database.");
		} finally {
			DBUtils.closeConnections(connection, insertStatement);
		}
		
	}

}
