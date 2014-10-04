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
				href="www.citihub.com">Citihub Request for Information</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="nav navbar-nav">
				<li><a href="logout">Logout</a></li>						
			</ul>
		</div>
	</div>
	</nav>
	<br>
	<br>
	<br>
	<div class="container">
		<form:form modelAttribute="assessment" id="myForm" action="home">
			<table class="table table-condensed">
				<thead>
					<th>Site Name</th>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
				<tbody>
					<tr class="active">
						<td>${site.name}&nbsp;:&nbsp;${site.demand}</td>
						<td>&nbsp;</td>
						<td>

						</td>
					</tr>
				</tbody>
			</table>
			<ul class="nav nav-tabs">
			</ul>
			
			<div class="tab-content">
				<div class="tab-pane  active" id="electrical">
					<div class="panel panel-primary">
						<div class="panel-heading" id="electricalStatusLabel">Questions regarding <b>${site.name}</b> facility:</div>
						<form:input path="electricalStatus" id="electricalStatus"
							placeholder="electrical status" size="50" maxlength="50"
							readonly="" type="hidden"></form:input>
						<div class="panel-body">
							<table class="table  table-striped">
								<thead>
									<tr>
										<th width="20%">Category</th>
										<th width="30%">Question</th>
										<th width="1%"></th>										
										<th width="25%">Answer</th>
										<th width="24%">Further Comments</th>
									</tr>
								</thead>
								<c:if test="${not empty questionList}">
									<div>
										<tbody>
											<c:forEach var="question" items="${questionList}">
												<c:if test="${question.category=='Electrical'}">
													<tr>
														<td><c:out value="${question.subcategory}" /></td>
														<td><c:out value="${question.question}" /></td>
														<td><span class="glyphicon glyphicon-info-sign"
															rel="popover" data-trigger="hover" data-container="body"
															data-placement="auto right"
															data-content="${question.helptext}"
															data-original-title="Question Help"></span>
														</td>														
														<td>
															<form:input path="questionid"
																value="${question.id}" readonly="" type="hidden" />
															<c:choose>
																<c:when test="${empty question.referenceList}">
																	<form:textarea path="answer" class="form-control" rows="3"/>
																</c:when>
															<c:otherwise>
			  													<form:select
																	class="form-control" path="answer">
																	<option value="NOANS" label="No Answer"
																		class="form-control" path="answer" selected="selected" />																	
																	<c:forEach var="refList" varStatus="i"
																		items="${question.referenceList}">
																		<option value="<c:out value="${i.index}"/>">
																			<c:out value="${refList.requirement}" />
																		</option>
																	</c:forEach>
																</form:select>
																</c:otherwise>
															</c:choose>
														</td>
														<td>
															<form:textarea path="comment" class="form-control" id="comment" rows="3"></form:textarea>
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</div>
								</c:if>
							</table>
						</div>
					</div>
				</div>
			</div>
	<!-- 		<span class="btn btn-primary btn-file">
		  			Upload supplementary documentation <input type="file">
			</span>-->			
			<button type="button" id="loading-example-btn"
				class="btn btn-primary">${action}</button>
			<br><br>
		</form:form>
		<div class="row">
			<div class="col-xs-12">
				<footer>
				<p>&copy; Copyright 2014 Citihub Consulting</p>
				</footer>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Saved Assessment</h4>
				</div>
				<div class="modal-body">The assessment has been saved</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Modal -->
	<div class="modal fade" id="myHelp" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Help</h4>
				</div>
				<div class="modal-body">To be completed</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$("[rel=popover]").popover({
				html : true
			});
		});
	</script>
	<script>
		$('#loading-example-btn').click(function() {

			$('#myForm').submit();
		});

		function changeFunc() {
			//alert("hi");
		}
	</script>
</body>
</html>