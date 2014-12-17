<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Client Page</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('#addEditClientForm').submit(function(event) {

			/* alert($('#addEditClientForm').serialize());
 */
			var id = $('#id').val();
			var name = $('#name').val();
			var email = $('#email').val();
			var contacts = [$('#c1').val(),$('#c2').val()];
			var address = $('#address').val();
			var json = {
				"id" : id,
				"name" : name,
				"email" : email,
				"contacts" : contacts,
				"address" : address
			};
		alert(contacts);
			$.ajax({
				url : $("#addEditClientForm").attr("action"),
				data : $("#addEditClientForm").serialize(),
				type : "POST",

				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				},
				success : function(result) {
					alert("Client added.");

				},
				failure : function(response) {
					alert("Something went wrong.Please try again");
				}
			});

			event.preventDefault();
		});

	});
</script>

</head>
<body>
	<h1>Add a Client</h1>

	<c:url var="addAction" value="/clients/add"></c:url>

	<form:form id="addEditClientForm" action="${addAction}"
		commandName="client">
		<table>
			<c:if test="${!empty client.name}">
				<tr>
					<td><form:label path="id">
							<spring:message text="ID" />
						</form:label></td>
					<td><form:input path="id" readonly="true" size="8"
							disabled="true" /> <form:hidden path="id" /></td>
				</tr>
			</c:if>
			<tr>
				<td><form:label path="name">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="email">
						<spring:message text="Email" />
					</form:label></td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td><form:label path="contactNo1">
						<spring:message text="Contact No. " />
					</form:label></td>
				<td><form:input path="contactNo1" /></td>
			</tr>

			<tr>
				<td></td><td><form:input path="contactNo2" /></td>
			</tr>

			<tr>
				<td><form:label path="address">
						<spring:message text="Address" />
					</form:label></td>
				<td><form:input path="address" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:if test="${!empty client.name}">
						<input type="submit" value="<spring:message text="Edit Client"/>" />
					</c:if> <c:if test="${empty client.name}">
						<input type="submit" value="<spring:message text="Add Client"/>" />
					</c:if></td>
			</tr>
		</table>
	</form:form>
	<br>
	<h3>Clients List</h3>
	<c:if test="${!empty listClients}">
		<table class="tg">
			<tr>
				<th width="80">ID</th>
				<th width="120">Name</th>
				<th width="120">Email</th>
				<th width="120">Contact</th>
				<th width="120">Address</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${listClients}" var="client">
				<tr>
					<td>${client.id}</td>
					<td>${client.name}</td>
					<td>${client.email}</td>
					<td>${client.contactNo1}<br/>${client.contactNo2}</td>
					<td>${client.address}</td>
					<td><a href="<c:url value='/clients/edit/${client.id}' />">Edit</a></td>
					<td><a href="<c:url value='/clients/remove/${client.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>