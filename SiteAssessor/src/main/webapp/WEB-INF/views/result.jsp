<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!-- <html manifest="default.manifest"> -->
<html>
<head>
<meta charset="utf-8">
<title>Citihub Request for Information</title>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="<c:url value="/resources/css/datepicker.css" />">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.1.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>
<style type="text/css">
.bs-example {
	margin: 20px;
}
</style>
</head>
<body>
	<nav id="myNavbar" class="navbar navbar-default navbar-fixed-top"
		role="navigation"> <!-- Brand and toggle get grouped for better mobile display -->
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbarCollapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" id="home" href="http://www.citihub.com"><img
				src="./resources/images/logo.png"></a> <a class="navbar-brand"
				href="landing">Citihub Request for Information</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="nav navbar-nav">
				<li><a href="landing">Site List</a></li>			
				<li><a href="logout">Logout</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="well">Thank you. The RFI has been submitted.</div>
		<div class="panel panel-primary">
			<div class="panel-heading" id="electricalStatusLabel">Summary of RFI responses
 				submitted:</div>
			<div class="panel-body">
				<table class="table  table-striped">
					<thead>
						<tr>
							<th width="20%">Site</th>
							<th width="20%">Demand</th>							
							<th width="20%">Status</th>
							<th width="40%">Commercials</th>							
						</tr>
					</thead>
					<c:if test="${not empty siteList}">
						<div>
							<tbody>
								<c:forEach var="site" items="${siteList}">
									<tr>
										<td><c:out value="${site.name}" /></td>
										<td><c:out value="${site.demand}" /></td>
										<td><c:out value="${site.status}" /></td>
										<td><c:out value="${site.pricing}" /></td>
									</tr>
								</c:forEach>
							</tbody>
					</c:if>
				</table>
			</div>
		</div>
		<div>
			<form:form class="form-signin" id="myForm">
				<button type="button" id="new-assessment-btn"
					class="btn btn-primary">Logout</button>
			</form:form>
		</div>
		<br> <br> <br> <br>
		<!-- 	<footer>
	<p>&copy; Copyright 2014 Citihub Consulting</p>
	</footer> -->
	</div>
	<script>
		$('#new-assessment-btn').click(function() {
			$(location).attr('href', "/SiteAssessor/logout");
		});	
	</script>
</body>
</html>