<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>

<title>Customer Form</title>
</head>
<body>

<header style="color:white">
 
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Customer Management</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="<%= request.getContextPath() %>/table">Customer</a>
        </li>
      
     </ul>
    </div>
   </div>
  </nav>
 </header>
<br>


  <div class="container" style="width:50%">
  
  <c:if test="${customer == null}">
  
 	 <form action="add" method="post">
  
	  <h2>Add Customer</h2>
  
  </c:if>
  
  <c:if test="${customer != null}">
	
	  <form action="edit" method="post">
  
	  <h2>Edit Customer</h2>
  
  </c:if>
 
    <div class="mb-3" hidden="hidden">
	   <input value="<c:out value="${customer.cid}"></c:out>"type="text" class="form-control" id="exampleFormControlInput" name="tbId">
	</div>
	
    <div class="mb-3">
  	  <label for="exampleFormControlInput1" class="form-label">Name</label>
	   <input value="<c:out value="${customer.name}"></c:out>" type="text" class="form-control" id="exampleFormControlInput1" name="tbName" placeholder="Enter Your name" requried="requried">
	</div>
	
	<div class="mb-3">
      <label for="exampleFormControlInput2" class="form-label">Email</label>
      <input value="<c:out value="${customer.email}"></c:out>" type="email" class="form-control" id="exampleFormControlInput2" name="tbEmail" placeholder="Enter your Email" requried="requried">
	</div>
	
	<div class="mb-3">
      <label for="exampleFormControlInput3" class="form-label">Mobile</label>
      <input value="<c:out value="${customer.mobile}"></c:out>" type="tel" class="form-control" id="exampleFormControlInput3" name="tbMobile" placeholder="Enter your Mobile Number" requried="requried">
	</div>      	
	<div>
	   			<button  class="btn btn-success">SAVE</button>
	</div>
	</form>	
  </div>

</body>
</html>