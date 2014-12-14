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
</head>
<body>
	<h1>Add a Client</h1>

	<c:url var="addAction" value="/client/add"></c:url>

	<form:form action="${addAction}" commandName="client">
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
				<th width="80">Client ID</th>
				<th width="120">Client Name</th>
				<th width="120">Client Address</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${listClients}" var="client">
				<tr>
					<td>${client.id}</td>
					<td>${client.name}</td>
					<td>${client.address}</td>
					<td><a href="<c:url value='/edit/${client.id}' />">Edit</a></td>
					<td><a href="<c:url value='/remove/${client.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>