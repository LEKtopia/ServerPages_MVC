<%@page import="edu.cvtc.web.dao.impl.PersonDaoImpl"%>
<%@page import="edu.cvtc.web.dao.PersonDao"%>
<%@ page import="edu.cvtc.web.model.Person" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html>
<head>
<title>Java Web Programming: Populate Database</title>
<meta name="description" content="This is a JSP example that demonstrates how to populate a person database." />
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
	final String filePath = session.getServletContext().getRealPath("/assets/spreadsheets/JavaWebProgramming.xlsx");
	
	final PersonDao personSearchDao = new PersonDaoImpl();
	personSearchDao.populateDatabase(filePath);
	%>
	<p>Database populated!</p>
	<%
	
} catch (Exception e) {
	e.printStackTrace();
	%>
	<p>Sorry, we were unable to populate the database at this time.</p>
	<%
}
%>
	</div>
	<hr />
	<p><a href="person-search.jsp">Search Again</a></p>
<%@ include file="include/footer.jsp"  %>