<%@ page import="edu.cvtc.web.exception.PersonSearchException"%>
<%@ page import="edu.cvtc.web.search.PersonSearch" %>
<%@ page import="edu.cvtc.web.search.impl.PersonSearchImpl" %>
<%@ page import="edu.cvtc.web.model.Person" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<title>Java Web Programming: Person Details</title>
<meta name="description" content="This is a JSP example that demonstrates how to output a filtered list of Persons to a web page." />
<%@ include file="include/styles.jsp"  %>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Person Search</h1>
	</div>
	<%@ include file="include/navigation.jsp"  %>
	<div class="container">
<%
try {
	final String firstName = request.getParameter("firstName");
	
	final PersonSearch personSearch = new PersonSearchImpl();
	final List<Person> people = personSearch.findPeopleByFirstName(firstName);
	request.setAttribute("people", people);
	
	%>
	<c:choose>
		<c:when test="empty ${people}">
			<p>Sorry, the list of people is empty.</p>
		</c:when>
		<c:otherwise>
			<c:forEach var="person" items="${people}">
				<div class="span4">
					<h2>${person.firstName}</h2>
					<p>${person.firstName} ${person.lastName} is ${person.age} years old. ${person.firstName}'s 
					favorite color is ${person.favoriteColor}. When ${person.firstName} is not too busy with life,
					${person.firstName} likes to spend their free time with: 
					<c:forEach var="hobby" items="${person.hobbies}" varStatus="loopCount">
						${hobby.name}<c:if test="${!loopCount.last}"> and </c:if></c:forEach>.</p>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<%
	
} catch (PersonSearchException e) {
	e.printStackTrace();
	%>
	<p>Sorry, we were unable to find a person with that first name.</p>
	<%
}
%>
	</div>
	<hr />
	<p><a href="person-search.jsp">Search Again</a></p>
<%@ include file="include/footer.jsp"  %>