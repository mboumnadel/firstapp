<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html> 
<html>
<head>
	<title><tiles:getAsString name="title" /></title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<link href="<c:url value="/css/home.css" />" rel="stylesheet">	
</head>


<body>

<tiles:insertAttribute name="header" />
  
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">      
	  <spring:url value="/order/search" var="orderSearchUrl" htmlEscape="true"/>
      <p><a href="${orderSearchUrl}">Order Search</a></p>

      <spring:url value="/employee/list" var="employeeListUrl" htmlEscape="true"/>
      <p><a href="${employeeListUrl}">Employee List</a></p>
      <p><a href="#">Link</a></p>
    </div>
    <div class="col-sm-8 text-left">
    <br/> 

	  <tiles:insertAttribute name="body" />

    </div>
    <div class="col-sm-2 sidenav">
      <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div>
    </div>
  </div>
</div>

<tiles:insertAttribute name="footer" />


</body>


</html>