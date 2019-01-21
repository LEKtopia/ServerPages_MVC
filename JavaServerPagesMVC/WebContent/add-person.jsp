<!doctype html>
<html>
<head>
<title>Java Web Programming: Add New Person</title>
<meta name="description" content="This is a JSP example that demonstrates how to use a form to add a new Person to a database." />
<%@ include file="include/styles.jsp"  %>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Add Person</h1>
	</div>
	<%@ include file="include/navigation.jsp"  %>
	<div class="container">
		<form action="AddPerson" method="post">
			<div class="form-group">
				<label for="firstName"><strong>First Name:</strong></label>
				<input name="firstName" />
				
				<label for="lastName"><strong>Last Name:</strong></label>
				<input name="lastName" />
				
				<label for="age"><strong>Age:</strong></label>
				<input name="age" />
				
				<label for="favoriteColor"><strong>Favorite Color:</strong></label>
				<input name="favoriteColor" />
				
				<label for="hobby_1"><strong>Hobby 1:</strong></label>
				<input name="hobby_1" />
				
				<label for="hobby_2"><strong>Hobby 2:</strong></label>
				<input name="hobby_2" />
				
				<input class="btn btn-primary btn-lg" type="submit" value="Add Person" />
			</div>
		</form>
	</div>
	<hr />
<%@ include file="include/footer.jsp"  %>