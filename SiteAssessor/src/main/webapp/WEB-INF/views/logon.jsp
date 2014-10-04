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
<link rel="stylesheet"
	href="<c:url value="/resources/css/citihub.css" />">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.1.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

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
				href="www.citihub.com">Citihub Request for Information</a>
		</div>
	</div>
	</nav>
	<div class="container">
		<br> <br> <br> <br> <br> <br> <br>
		<br>

		<form:form class="form-signin" modelAttribute="logon" id="myForm">
			<h2 class="form-signin-heading">Please sign in</h2>
			<form:input path="username" placeholder="username"
				class="form-control" id="username"></form:input>
			<form:input path="password" type="password" class="form-control"
				placeholder="Password" id="password"></form:input>
			<div class="alert" id="error">
				<a class="close" data-dismiss="alert">×</a> <strong>Invalid
					Logon!</strong> Username or password invalid.
			</div>
			<button id="logon-btn" class="btn btn-lg btn-primary btn-block"
				type="submit">Sign in</button>
		</form:form>
	</div>

	<script>
		if ($("#username").val() == '') {
			$("#error").hide();
		} else {
			$("#error").show();
		}

		$('#logon-btn').click(function() {

			$('#myForm').submit();
		});
	</script>
</body>
</html>