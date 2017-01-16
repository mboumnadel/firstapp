<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

ORDRE LIST:
                                                                                      
  <div class="table-responsive">
  <table class="table table-hover table-condensed">
    <thead>
      <tr>
        <th>Order #</th>
        <th>Order Date</th>
        <th>Required Date</th>
        <th>Shipped Date</th>
        <th>Status</th>
        <th>Customer</th>
        <th></th>
      </tr>
    </thead>
    <tbody>      
        <c:forEach var="order" items="${orders}">
        <tr>
			<td><c:out value="${order.number}"/></td>
			<td><c:out value="${order.orderDate}"/></td>
			<td><c:out value="${order.requiredDate}"/></td>
			<td><c:out value="${order.shippedDate}"/></td>
			<td><c:out value="${order.status}"/></td>
			<td><c:out value="${order.customer.name} (${order.customer.number}) "/></td>

			<spring:url value="/order/edit/${order.id}" var="editUrl" htmlEscape="true"/>
			<td><a href="${editUrl}" class="btn btn-link">View/Edit</a></td>
		</tr>
		</c:forEach>
    </tbody>
  </table>
  </div>