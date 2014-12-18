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
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
<script type="text/javascript"
	src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>
<script type="text/javascript">
	//Plug-in to fetch page data 
	jQuery.fn.dataTableExt.oApi.fnPagingInfo = function(oSettings) {
		return {
			"iStart" : oSettings._iDisplayStart,
			"iEnd" : oSettings.fnDisplayEnd(),
			"iLength" : oSettings._iDisplayLength,
			"iTotal" : oSettings.fnRecordsTotal(),
			"iFilteredTotal" : oSettings.fnRecordsDisplay(),
			"iPage" : oSettings._iDisplayLength === -1 ? 0 : Math
					.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
			"iTotalPages" : oSettings._iDisplayLength === -1 ? 0 : Math
					.ceil(oSettings.fnRecordsDisplay()
							/ oSettings._iDisplayLength)
		};
	};
	$(document).ready(function() {

		
		$("#example").dataTable({
			"bProcessing" : true,
			"bServerSide" : true,
			"sort" : "position",
			//bStateSave variable you can use to save state on client cookies: set value "true" 
			"bStateSave" : false,
			//Default: Page display length
			"iDisplayLength" : 10,
			//We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
			"iDisplayStart" : 0,
			"fnDrawCallback" : function() {
				//Get page numer on client. Please note: number start from 0 So
				//for the first page you will see 0 second page 1 third page 2...
				//Un-comment below alert to see page number
				//alert("Current page number: "+this.fnPagingInfo().iPage);    
			},
			"sAjaxSource" : "/AdvocateDiary/clients/springPaginationDataTables.web",
			"aoColumns" : [ {
				"mData" : "name"
			}, {
				"mData" : "position"
			}, {
				"mData" : "office"
			}, {
				"mData" : "phone"
			}, {
				"mData" : "start_date"
			}, {
				"mData" : "salary"
			},

			]
		});

		
		
		$('#addEditClientForm').submit(function(event) {

			/* alert($('#addEditClientForm').serialize());
 */
			var id = $('#id').val();
			var name = $('#name').val();
			var email = $('#email').val();
			var contacts = [$('#contactNo1').val(),$('#contactNo2').val()];
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
				data : JSON.stringify(json),
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
	<table width="70%"
			style="border: 3px; background: rgb(243, 244, 248);">
			<tr>
				<td>
					<table id="example" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>Name</th>
								<th>Position</th>
								<th>Office</th>
								<th>Phone</th>
								<th>Start Date</th>
								<th>Salary</th>
							</tr>
						</thead>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>