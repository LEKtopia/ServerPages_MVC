<!doctype html>
<html>
<head>
<title>Java Web Programming: Person Search</title>
<meta name="description" content="This is a JSP example that demonstrates how to use a form to search for a Person from our Spreadsheet." />
<%@ include file="include/styles.jsp"  %>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Person Search</h1>
	</div>
	<%@ include file="include/navigation.jsp"  %>
	<div class="container">
		<form action="person-filter-lastname.jsp" method="post">
			<div class="form-group">
				<label for="lastName"><strong>Search by Last Name:</strong></label>
				<input name="lastName" />
				<input class="btn btn-primary btn-lg" type="submit" value="Search" />
			</div>
		</form>
	</div>
	<div class="container">
		<form action="person-filter-firstname.jsp" method="post">
			<div class="form-group">
				<label for="firstName"><strong>Search by First Name:</strong></label>
				<input name="firstName" />
				<input class="btn btn-primary btn-lg" type="submit" value="Search" />
			</div>
		</form>
	</div>
	<hr />
<%@ include file="include/footer.jsp"  %>