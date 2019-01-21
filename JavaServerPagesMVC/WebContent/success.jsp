<!doctype html>
<html>
<head>
<title>Java Web Programming: Success Page</title>
<meta name="description" content="JSP example success page we can send users to via the RequestDispatcher when the application completes an action successfully." />
<jsp:include page="include/styles.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Success</h1>
	</div>
	<jsp:include page="include/navigation.jsp"></jsp:include>
	<div class="container">
		<p>${success}</p>
	</div>
	<hr />
<jsp:include page="include/footer.jsp"></jsp:include>