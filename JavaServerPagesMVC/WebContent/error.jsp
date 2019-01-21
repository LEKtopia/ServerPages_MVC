<!doctype html>
<html>
<head>
<title>Java Web Programming: Error Page</title>
<meta name="description" content="JSP example error page we can send users to via the RequestDispatcher when the application encounters an error." />
<jsp:include page="include/styles.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Error</h1>
	</div>
	<jsp:include page="include/navigation.jsp"></jsp:include>
	<div class="container">
		<p>${error}</p>
	</div>
	<hr />
<jsp:include page="include/footer.jsp"></jsp:include>