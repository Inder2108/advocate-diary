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
<link rel="stylesheet" type="text/css"
href="//cdn.datatables.net/tabletools/2.2.3/css/dataTables.tableTools.css">
	
<script type="text/javascript"
	src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/tabletools/2.2.3/js/dataTables.tableTools.js"></script>	
	
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
	
	function clearForm() {
	       $("#addEditClientForm input[type='text']").each(function(){
	    	   $(this).val("");
	       });
	       $("#addEditClientForm #id").val(0);
	        $("#addEditClientForm input[type='submit']").attr('value','Add Client');
	        $("h1").html('Add Client');	
	   }
	
	$(document).ready(function() {
		
		// Edit record
	    $('#example').on( 'click', 'a.edit_client', function (e) {
	        e.preventDefault();
	        var row = $(this).parent().parent();
	        $("#addEditClientForm #id").val($(this).attr("data-id"));
	        $("#addEditClientForm input[name='name']").val(row.find('td:eq(0)').text());
	        $("#addEditClientForm #email").val(row.find('td:eq(1)').text());
	        var contacts = row.find('td:eq(2)').html().split("<br>");
	        $("#addEditClientForm #contactNo1").val(contacts[0]);
	        $("#addEditClientForm #contactNo2").val(contacts[1]);
	        $("#addEditClientForm #address").val(row.find('td:eq(3)').text());
	        $("#addEditClientForm input[type='submit']").attr('value','Edit Client');
	        $("h1").html('Edit Client');	
	    } );
		
	 	// Delete record
	    $('#example').on( 'click', 'a.delete_client', function (e) {
	        e.preventDefault();
	        var row = $(this).parent().parent();
			$.ajax({
				url : $(this).attr("href"),
				type : "POST",
				beforeSend: function(xhr) {
		    		xhr.setRequestHeader("Accept", "application/json");
		    		xhr.setRequestHeader("Content-Type", "application/json");
		    	},
				success : function(result) {
					$('#example').DataTable().ajax.reload();
					alert("Client deleted.");
				},
				failure : function(response) {
					alert("Something went wrong.Please try again");
				}
			});
	    } );
		 
	  
		$("#example").dataTable({
			"dom": 'T<"clear">lfrtip',
			"fnInitComplete": function ( oSettings ) {
				/* var pdfButton = $('div.DTTT_container').detach();
				$("#exportDiv").append( pdfButton ); */
				},
			"tableTools": {
				"aButtons":[
				            
                {
                	"sExtends":"copy",
                	"mColumns":[0,1,2,3]
                },
                {
                	"sExtends":"print",
                	"mColumns":[0,1,2,3]
                },
                {
                    "sExtends":    "collection",
                    "sButtonText": "Save",
                    "aButtons":    [ 
                                     {
                	"sExtends":"pdf",
                	"mColumns":[0,1,2,3],
                	"fnCellRender": function ( sValue, iColumn, nTr, iDataIndex ) {
                        if ( iColumn === 2 ) {
                            return sValue.replace("<br/>",",");
                        }
                        return sValue;
                    }
                }, {
                	"sExtends":"xls",
                	"mColumns":[0,1,2,3]
                } ]
                }
                
				],
	            "sSwfPath": "http://cdn.datatables.net/tabletools/2.2.3/swf/copy_csv_xls_pdf.swf"
	        },
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
			"sAjaxSource" : "/AdvocateDiary/clients/get",
			
			 "columns": [
			            { data: "name",sClass : "alignRight" },
			            { data: "email" },
			            {
			            	render: function (data, type, row) {
			            		return row.contactNo1 + "<br/>" + row.contactNo2;
                            }},
			            {data : "address"},
			            {
			            	sortable: false,
			            	render: function (data, type, row) {
                                return '<a class="edit_client" href="" data-id='+row.id + '>Edit</a>'
                                +'/<a class="delete_client" href="/AdvocateDiary/clients/remove/'+row.id + '">Delete</a>';
                                /* return '<form action="/AdvocateDiary/clients/remove/"+row.id>'+
                                '<a href="javascript:deleteClient('+row.id +');">Edit</a></form>'; */                            
                            }}
			            ]
		});
		
		$('#addEditClientForm').submit(function(event) {

			/* alert($('#addEditClientForm').serialize());
 */
			var id = $('#id').val();
			var name = $('#name').val();
			var email = $('#email').val();
			var contact1 = $('#contactNo1').val();
			var contact2 =  $('#contactNo2').val();
			var address = $('#address').val();
			var json = {
				"id" : id,
				"name" : name,
				"email" : email,
				"contactNo1" : contact1,
				"contactNo2" : contact2,
				"address" : address
			};
			
		alert(JSON.stringify(json));
			$.ajax({
				url : $("#addEditClientForm").attr("action"),
				data : JSON.stringify(json),
				type : "POST",
				beforeSend: function(xhr) {
	        		xhr.setRequestHeader("Accept", "application/json");
	        		xhr.setRequestHeader("Content-Type", "application/json");
	        	},
				success : function(result) {
					alert("Client " + (id == 0 ?"added.":"updated."));
					$('#example').DataTable().ajax.reload();
					clearForm();
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
	<h1>Add Client</h1>

	<c:url var="addAction" value="/clients/addEdit"></c:url>

	<form:form id="addEditClientForm" action="${addAction}"
		commandName="client">
		<table>
		<form:hidden path="id" />
			<tr>
				<td><form:label path="name">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input id="name" path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="email">
						<spring:message text="Email" />
					</form:label></td>
				<td><form:input name="email"  path="email" /></td>
			</tr>
			<tr>
				<td><form:label path="contactNo1">
						<spring:message text="Contact No. " />
					</form:label></td>
				<td><form:input path="contactNo1" name="contactNo1"/></td>
			</tr>

			<tr>
				<td></td><td><form:input path="contactNo2" name="contactNo2"/></td>
			</tr>

			<tr>
				<td><form:label path="address">
						<spring:message text="Address" />
					</form:label></td>
				<td><form:input path="address" name="address" /></td>
			</tr>
			<tr>
				<td >		
				</td>
				<td align="right">
				<br/>
				<input type="submit" value="Add Client"/>
				<input type="button" value="Cancel" onClick="javascript:clearForm();"/>
				</td>
			</tr>
		</table>
	</form:form>
	<br>
	<div id="exportDiv"></div>
	<h3>Clients List</h3>
	<table width="70%"
			style="border: 3px; background: rgb(243, 244, 248);">
			<tr>
				<td>
					<table id="example" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>Name</th>
								<th>Email</th>
								<th>Contact</th>
								<th>Address</th>
								<th>Edit/Delete</th>
							</tr>
						</thead>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>