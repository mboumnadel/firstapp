<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:if test="${not empty saveOrderStatus}">

<c:choose>
  <c:when test="${saveOrderStatus}">
    <div class="alert alert-success">
  		<strong>Success!</strong> Order added successfully.
	</div>
  </c:when>
  <c:otherwise>
	<div class="alert alert-danger">
  		<strong>Error!</strong> Order data not valid.
	</div>    
  </c:otherwise>
</c:choose>
   
</c:if>

<div class="alert alert-danger">
	${msg}
</div>

<spring:url value="/order/edit" var="formUrl" htmlEscape="true"/>
<form:form class="form-horizontal" modelAttribute="order" action="${formUrl}" method="POST">

	<div class="form-group">
	    <label class="control-label col-sm-2" for="number">Order #:</label>

	    <label class="control-label col-sm-2" for="orderDate">Order Date:</label>
    
    	<label class="control-label col-sm-2" for="requiredDate">Required Date:</label>
	    
	    <label class="control-label col-sm-2" for="shippedDate">Shipped Date:</label>

		<label class="control-label col-sm-2" for="status">Status:</label>	    
  	</div>
  	<div class="form-group">
  		<div class="col-sm-2">
  		  <form:hidden path="id"/>
	      <form:input type="text" class="form-control" path="number" />
	      <form:errors path="number" />
	    </div>
	    
	    <div class="col-sm-2">
	      <form:input type="text" class="form-control" path="orderDate" placeholder="Enter Order Date"/>
	      <form:errors path="orderDate" />
	    </div>
	    
	    <div class="col-sm-2">
	      <form:input type="text" class="form-control" path="requiredDate" placeholder="Enter Required Date"/>
	      <form:errors path="requiredDate" />
	    </div>
	    
	    <div class="col-sm-2">
	      <form:input type="text" class="form-control" path="shippedDate" placeholder="Enter Shipped Date"/>
	      <form:errors path="shippedDate" />
	    </div>
	    
	    <div class="col-sm-2">
	      <form:input type="text" class="form-control" path="status" placeholder="Enter Status"/>
	      <form:errors path="status" />
	    </div>
  	</div>


	<div class="form-group">
		<label class="control-label col-sm-3" for="customer">Customer:</label>
		
		<label class="control-label col-sm-7" for="comments">Comment:</label>
	</div>
	<div class="form-group">
	    <div class="col-sm-3">
			<form:select path="customer" title="Select a customer" class="form-control">
				<option value="">Select a customer</option>
	
				<c:forEach items="${customers}" var="acustomer">
		      	<c:choose>
		            <c:when test="${acustomer.id eq order.customer.id}">
		                <form:option value="${acustomer.id}" selected="selected">${acustomer.name}</form:option>
		            </c:when>
		            <c:otherwise>
		            	<form:option value="${acustomer.id}">${acustomer.name}</form:option>
		            </c:otherwise>
		        </c:choose> 
		      	</c:forEach>
	         </form:select>
	         <form:errors path="customer" />
	    </div>
	    
	    <div class="col-sm-7">
	      <form:input type="text" class="form-control" path="comments" placeholder="Enter Comment"/>
	      <form:errors path="comments" />
	    </div>
	</div>

	<div class="form-group">
		<label class="control-label col-sm-3">Product:</label>
		<label class="control-label col-sm-3">Quantity Ordered:</label>
		<label class="control-label col-sm-3">Price each:</label>
		<label class="control-label col-sm-3">Order line #:</label>
	</div>
	
	<c:forEach items="${order.orderDetails}" var="anorderDetails" varStatus="loop">
	<div class="form-group">
	
	    <div class="col-sm-3">
	    	<input type="text" class="form-control" name="orderDetails[${loop.index}].order" value="${order.id}" />
	    	<input type="text" class="form-control" name="orderDetails[${loop.index}].id" value="${anorderDetails.id}" />
			<select name="orderDetails[${loop.index}].product" title="Select a product" class="form-control">
				<option value="">Select a product</option>
	
				<c:forEach items="${products}" var="aproduct">
		      	<c:choose>
		            <c:when test="${aproduct.id eq anorderDetails.product.id}">
		                <option value="${aproduct.id}" selected="selected">${aproduct.name}</option>
		            </c:when>
		            <c:otherwise>
		            	<option value="${aproduct.id}">${aproduct.name}</option>
		            </c:otherwise>
		        </c:choose> 
		      	</c:forEach>
	         </select>
	    </div>

  		<div class="col-sm-3">
	      <input type="text" class="form-control" name="orderDetails[${loop.index}].quantityOrdered" value="${anorderDetails.quantityOrdered}" />
	    </div>
    
  		<div class="col-sm-3">
	      <input type="text" class="form-control" name="orderDetails[${loop.index}].priceEach" value="${anorderDetails.priceEach}" />
	    </div>
    
  		<div class="col-sm-3">
	      <input type="text" class="form-control" name="orderDetails[${loop.index}].orderLineNumber" value="${anorderDetails.orderLineNumber}" />
	    </div>
	</div>
	</c:forEach>

    <div class="form-group"> 
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">Save</button>
	    </div>
  	</div>
</form:form>
