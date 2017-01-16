<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="alert alert-danger">
	${msg}
</div>

<spring:url value="/order/search" var="formUrl" htmlEscape="true"/>
<form:form class="form-horizontal" modelAttribute="order" action="${formUrl}" method="POST">

	<div class="form-group">
    <label class="control-label col-sm-2" for="number">Order #:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="number" placeholder="Enter Order Number"/>
      <form:errors path="number" />
    </div>
  	</div>

	<div class="form-group">
    <label class="control-label col-sm-2" for="orderDate">Required Date:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="orderDate" placeholder="Enter order Date"/>
      <form:errors path="orderDate" />
    </div>
  	</div>
  	
  	<div class="form-group">
    <label class="control-label col-sm-2" for="requiredDate">Required Date:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="requiredDate" placeholder="Enter required Date"/>
      <form:errors path="requiredDate" />
    </div>
  	</div>

	<div class="form-group">
    <label class="control-label col-sm-2" for="status">Status:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="status" placeholder="Enter Status"/>
      <form:errors path="status" />
    </div>
  	</div>

    <div class="form-group">
    <label class="control-label col-sm-2" for="customer">Customer :</label>
    <div class="col-sm-10">
		<form:select path="customer" title="Select a customer" class="form-control">
			<option value="">Select a customer</option>

			<c:forEach items="${customers}" var="acustomer">
				<form:option value="${acustomer.id}">${acustomer.name} (${acustomer.number})</form:option>
	      	</c:forEach>

         </form:select>
         <form:errors path="customer" /> <!-- CustomerEditor getAsText  -->
    </div>
    </div>

  <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Search</button>
    </div>
  </div>
</form:form>