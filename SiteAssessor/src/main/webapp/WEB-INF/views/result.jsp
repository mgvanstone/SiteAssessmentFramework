<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!-- <html manifest="default.manifest"> -->
<html>
<head>
<meta charset="utf-8">
<title>Citihub Site Survey</title>

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
			<a class="navbar-brand" id="result" href="/home"><img
				src="./resources/images/logo.png"></a> <a class="navbar-brand"
				href="/home">Citihub Site Survey</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="nav navbar-nav">
				<li><a href="#" target="_blank">Home</a></li>
				<li><a href="#" target="_blank">About</a></li>
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
		<div class="well">Thank you. The survey has been submitted.</div>
		<div>
			<form:form class="form-signin" id="myForm">
				<button type="button" id="new-assessment-btn"
					class="btn btn-primary">Start New Assessment</button>
			</form:form>
		</div>
	</div>
	<!-- 	<div class="container">
		<div class="well">
			Overall Assessment is
			<c:out value="${assessment.overallStatus}" />
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">Summary of Site Assessment Results</div>
			<div class="panel-body">
				<table class="table  table-striped">
					<thead>
						<tr>
							<th>Category</th>
							<th>Tier Rating</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Electrical</td>
							<td>
								<div id="electricalstatusbtn" class="bg-primary">
									<c:out value="${assessment.electricalStatus}" />
								</div>
							</td>
						</tr>
						<tr>
							<td>Mechanical</td>
							<td>
								<div id="mechanicalstatusbtn" class="bg-primary">
									<c:out value="${assessment.mechanicalStatus}" />
								</div>
							</td>
						</tr>
						<tr>
							<td>Telecoms</td>
							<td>
								<div id="telecomsstatusbtn" class="bg-primary">
									<c:out value="${assessment.telecomsStatus}" />
								</div>
							</td>
						</tr>
						<tr>
							<td>Site & Structure</td>
							<td>
								<div id="sitestructurestatusbtn" class="bg-primary">
									<c:out value="${assessment.sitestructureStatus}" />
								</div>
							</td>
						</tr>
						<tr>
							<td>Operations</td>
							<td>
								<div id="operationsstatusbtn" class="bg-primary">
									<c:out value="${assessment.operationsStatus}" />
								</div>
							</td>
						</tr>
						<tr>
							<td>Process and Procedures</td>
							<td>
								<div id="processstatusbtn" class="bg-primary">Not
									applicable</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div> -->

	<br>
	<br>
	<br>
	<br>
<!-- 	<footer>
	<p>&copy; Copyright 2014 Citihub Consulting</p>
	</footer> -->
	</div>
	<script>
		$('#new-assessment-btn').click(function() {

			$(location).attr('href',"/siteassessor/form");
		});
	</script>
</body>
</html>