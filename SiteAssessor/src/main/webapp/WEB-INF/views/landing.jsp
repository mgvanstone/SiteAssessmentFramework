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
				href="http://www.citihub.com">Citihub Request for Information</a>
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
	<br>
	<br>
	<br>
	<div class="container">
		<div class="well">
			<p>
				<u><b>Background:</b></u> <br> <br> Citihub has been
				engaged to deliver a Data Centre strategy for the Asia Pacific
				region, the strategy will look at multiple metropolitan locations
				within 12 countries. <br>
				<br> The client is a long-established global financial
				institution with operations in over 50 countries and tens of
				thousands of employees. The client has four main objectives:<br>
				<br>
			<ul>
				<li>Consolidate footprint where possible</li>
				<li>Reduce the operating expenditure and minimise any capital
					expenditure</li>
				<li>Separate people and technology where practical</li>
				<li>Improve flexibility</li>
			</ul>
			<br> The engagement is currently at the information gathering
			stage, the client would like to understand:<br>
			<br>
			<ul>
				<li>Which providers cover the client's footprint</li>
				<li>What the specifications of the providers facilities are</li>
				<li>Indicative costs for each location based upon the demand
					projected by the client</li>
				<li>How the provider pricing model works</li>
			</ul>
			<br> Please note this is not a request for a formal proposal,
			therefore the data requested has been kept at a reasonably high
			level. At a later date the client or Citihub may approach some
			providers for formal quotations depending on the outcome of the
			strategy and direction from the client team. <br>
			<br> Whilst we are in the early stages of developing the
			strategy, the ball park costs provided will be used to compare the
			existing costs vs. moving to another location/provider, therefore we
			recommend you provide pricing that makes the decision to relocate
			compelling for the client. <br>
			<br> <b><u>Instructions for using the Citihub data
					gathering tool:</u></b><br>
			<br> This tool has been designed to speed up the data gathering
			process for Data Centre engagements. Each provider shall login
			securely and any information provided will not be visible to anybody
			outside of Citihub and the client teams.<br>
			<br> The tool is intended to make life easier for the providers,
			rather than submitting information that is not required it specifies
			exactly what data is required. <br>
			<br> As mentioned above the strategy covers 12 countries and
			multiple metropolitan locations, however we recognise that providers
			may not have a presence in all of them, therefore the check-boxes
			below allow the providers to select the locations that they wish to
			submit a response for. Once the provider selects the locations they
			will only see forms relating to those places, thus speeding up the
			process.<br>
			<br> Each location has a single form to be completed, there is a
			standard set of questions on each form and as well as a place to
			provide a response there is a space provided for additional
			commentary. The provider can also attach supplementary information
			for each location as desired.<br>
			<br> The tool allows providers to save progress and revisit the
			site at a later time to complete the submission. <br>
			<br> Once the provider is happy the information provided is
			complete and accurate they can press 'next location' and proceed to a
			new form.<br>
			<br> The deadline for submissions is 5:00 pm (Singapore Time)
			17th October 2014<br>
			<br> For any queries or support relating to the engagement or
			the Citihub tool please contact trevor.hawkes@citihub.com
			</p>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading" id="electricalStatusLabel">Select
				sites for RFI:</div>
			<div class="panel-body">
				<form:form modelAttribute="sitesselected" id="myForm"
					action="landing">
					<form:input path="submitter" id="submitter"
							value="${logonUser}" size="50" maxlength="50" readonly="" type="hidden" />
					<table class="table  table-striped">
						<thead>
							<tr>
								<th width="30%">Site</th>
								<th width="30%">Demand</th>
								<th width="5%">Proposal?</th>
								<th width="35%"></th>
							</tr>
						</thead>
						<c:if test="${not empty siteList}">
							<div>
								<tbody>
									<c:forEach var="site" items="${siteList}">
										<tr>
											<td><c:out value="${site.name}" /></td>
											<td><c:out value="${site.demand}" />
											<input id="selected${site.id}" value="${site.checked}"  readonly="" type="hidden"/></td>											
											<td><form:checkbox	path="siteId" id="${site.id}" value="${site.id}" /></td>
											<td>&nbsp;</td>
										</tr>
									</c:forEach>
								</tbody>
							</div>
						</c:if>
					</table>
				</form:form>
			</div>
		</div>
		<div>
			<button type="button" id="submit-btn" class="btn btn-primary">Start
				RFI</button>
		</div>
		<br>
		<br>

	</div>
	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		id="myModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">Submitter is a required field</div>
		</div>
	</div>
	<script>	 		
	    //$('#myForm input[type="checkbox"]').is(':checked')
	 	$("input:checkbox").each(function(){
   		 	var $this = $(this);
   		 	var $id = $this.attr("id");
   		 	var $line = "selected" + $id;
   		 	//console.log($this.attr("id"));
   		 	var $isChecked = $('#' + $line).val();
   		 	//console.log($isChecked);
   		 	if ($isChecked == "true") {
   		 		$this.attr('checked', 'checked');
   		 	} 
		});   
	    
		$('#submit-btn').click(function() {

		//	if ($("#submitter").val().length < 1) {
	//			alert("Organisation is required field");
		//	} else {
				$('#myForm').submit();
	//		}
		});
		$('#myForm').submit(function() {
			if (!$('#myForm input[type="checkbox"]').is(':checked')) {
				alert("Please check at least one.");
				return false;
			}
		});
	</script>
</body>
</html>