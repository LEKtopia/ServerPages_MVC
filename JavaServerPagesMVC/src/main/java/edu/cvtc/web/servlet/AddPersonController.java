package edu.cvtc.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cvtc.web.dao.PersonDao;
import edu.cvtc.web.dao.impl.PersonDaoImpl;
import edu.cvtc.web.exception.PersonDatabaseException;
import edu.cvtc.web.model.Hobby;
import edu.cvtc.web.model.Person;

/**
 * Servlet implementation class AddPersonController
 */
@WebServlet("/AddPerson")
public class AddPersonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PersonDao personDao = new PersonDaoImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String target = null;
		
		try {
			
			final String firstName = request.getParameter("firstName");
			final String lastName = request.getParameter("lastName");
			final int age = Integer.parseInt(request.getParameter("age"));
			final String favoriteColor = request.getParameter("favoriteColor");
			final String hobby1 = request.getParameter("hobby_1");
			final String hobby2 = request.getParameter("hobby_2");
			
			final List<Hobby> hobbies = new ArrayList<>();
			hobbies.add(new Hobby(hobby1, null));
			hobbies.add(new Hobby(hobby2, null));
			
			final Person person = new Person(firstName, lastName, age, favoriteColor, hobbies);
			
			personDao.insertPerson(person);
			
			request.setAttribute("success", "Success, a new person has been added to the database.");
			target = "success.jsp";
			
		} catch (PersonDatabaseException e) {
			e.printStackTrace();
			request.setAttribute("error", "Sorry, there was a problem adding this person to the database.");
			target = "error.jsp";
		}
		
		request.getRequestDispatcher(target).forward(request, response);
	}

}
