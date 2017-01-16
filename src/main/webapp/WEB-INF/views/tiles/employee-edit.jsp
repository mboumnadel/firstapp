<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:if test="${not empty saveEmployeeStatus}">

<c:choose>
  <c:when test="${saveEmployeeStatus}">
    <div class="alert alert-success">
  		<strong>Success!</strong> Employee added successfully.
	</div>
  </c:when>
  <c:otherwise>
	<div class="alert alert-danger">
  		<strong>Error!</strong> Employee data not valid.
	</div>    
  </c:otherwise>
</c:choose>
   
</c:if>

<div class="alert alert-danger">
	${msg}
</div>

<spring:url value="/employee/save" var="formUrl" htmlEscape="true"/>
<form:form class="form-horizontal" modelAttribute="employee" action="${formUrl}" method="POST">

	<div class="form-group">
    <label class="control-label col-sm-2" for="id">ID:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="id" />
      <form:errors path="id" />
    </div>
  	</div>

	<spring:bind path="number">
	<div class="form-group">
    <label class="control-label col-sm-2" for="number">Employee #:</label>
    <div class="col-sm-10 ${status.error ? 'has-error' : ''}" >
      <form:input type="text" class="form-control" path="number" placeholder="Enter Employee Number"/>
      <form:errors path="number" />
    </div>
  	</div>
  	</spring:bind>

	<div class="form-group">
    <label class="control-label col-sm-2" for="firstName">First Name:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="firstName" placeholder="Enter First Name"/>
      <form:errors path="firstName" />
    </div>
  	</div>
  	
  	<div class="form-group">
    <label class="control-label col-sm-2" for="lastName">Last Name:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="lastName" placeholder="Enter Last Name"/>
      <form:errors path="lastName" />
    </div>
  	</div>

	<div class="form-group">
    <label class="control-label col-sm-2" for="extension">Extension:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="extension" placeholder="Enter extension"/>
      <form:errors path="extension" />
    </div>
  	</div>

    <div class="form-group">
    <label class="control-label col-sm-2" for="email">Email:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="email" placeholder="Enter email"/>
      <form:errors path="email" />
    </div>
    </div>
  
    <div class="form-group">
    <label class="control-label col-sm-2" for="jobTitle">Job Title:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="jobTitle" placeholder="Enter Job Title"/>
      <div class="has-error">
      	<form:errors path="jobTitle" class="help-inline"/>
      </div>
    </div>
    </div>

    <div class="form-group">
    <label class="control-label col-sm-2" for="office">Office :</label>
    <div class="col-sm-10">
		<form:select path="office" title="Select an office" class="form-control">
			<option value="">Select an office</option>

			<c:forEach items="${offices}" var="anoffice">
	      	<c:choose>
	            <c:when test="${anoffice.id eq employee.office.id}">
	                <form:option value="${anoffice.id}" selected="selected">${anoffice.city}</form:option>
	            </c:when>
	            <c:otherwise>
	            	<form:option value="${anoffice.id}">${anoffice.city}</form:option>    
	            </c:otherwise>
	        </c:choose> 
	      	</c:forEach>
         </form:select>
         <form:errors path="office" /> <!-- OfficeEditor getAsText  -->
    </div>
    </div>

	<div class="form-group">
    <label class="control-label col-sm-2" for="reportsTo">Reports To :</label>
    <div class="col-sm-10">

      <form:select type="text" class="form-control" path="reportsTo">
      	<option value="">Select a Manager</option>
      	 <c:forEach items="${employees}" var="anEmployee">
      		<c:choose>
            <c:when test="${anEmployee.id eq employee.reportsTo.id}">
                <form:option value="${anEmployee.id}" selected="selected">
                	${anEmployee.id}- ${anEmployee.firstName} ${anEmployee.lastName} (${anEmployee.number})
                </form:option>
            </c:when>
            <c:otherwise>
            	<form:option value="${anEmployee.id}">
            		${anEmployee.id}- ${anEmployee.firstName} ${anEmployee.lastName} (${anEmployee.number})
            	</form:option>
            </c:otherwise>
        </c:choose> 
      	</c:forEach>
	  </form:select>
      <form:errors path="reportsTo" />  <!-- EmployeeEditor getAsText  -->
    </div>
    </div>


    <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label><input type="checkbox" id="rememberme" name="rememberme"> Remember me</label>
      </div>
    </div>
   </div>
  <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Submit</button>
    </div>
  </div>
</form:form>