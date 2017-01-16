<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

EMPLOYEE LIST:
                                                                                      
  <div class="table-responsive">
  <table class="table table-hover table-condensed">
    <thead>
      <tr>
        <th>Employee #</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Extension</th>
        <th>Email</th>
        <th>Job Title</th>
        <th>Office Code</th>
        <th></th>
      </tr>
    </thead>
    <tbody>      
        <c:forEach var="employee" items="${employees}">
        <tr>
			<td><c:out value="${employee.number}"/></td>
			<td><c:out value="${employee.firstName}"/></td>
			<td><c:out value="${employee.lastName}"/></td>
			<td><c:out value="${employee.extension}"/></td>
			<td><c:out value="${employee.email}"/></td>
			<td><c:out value="${employee.jobTitle}"/></td>
			<td><c:out value="${employee.officeCode}"/></td>
			<spring:url value="/employee/edit" var="editUrl" htmlEscape="true"/>
			<td><a href="${editUrl}" class="btn btn-link">Edit</a></td>
		</tr>
		</c:forEach>
    </tbody>
  </table>
  </div>

