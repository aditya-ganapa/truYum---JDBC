<!DOCTYPE html>
<html>
<head>
	<script src="js/script.js"></script>
	<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body>
<nav>
	<p>
		<a href="home.html" class="home-link">
			truYum
			<img src="images/logo.jpg" alt="logo">
		</a>
		<a href="ShowMenuItemListAdmin" class="admin-menu-link">Menu</a>
	</p>
</nav>
<section>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<h2>Menu Items</h2>
	<c:if test="${removeMenuItemStatus}">
		<p class="notification">${removedMenuItemName} removed from menu successfully.</p>
	</c:if>
	<table>
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Active</th>
			<th>Date of Launch</th>
			<th>Category</th>
			<th>Free Delivery</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${menuItemList}" var="menuItem">
			<tr>
				<td><c:out value="${menuItem.name}"/></td>
				<td><fmt:formatNumber value="${menuItem.price}" type="currency" currencySymbol="Rs. "/></td>
				<td><c:if test="${menuItem.active}">Yes</c:if><c:if test="${!menuItem.active}">No</c:if></td>
				<td><fmt:formatDate value="${menuItem.dateOfLaunch}" pattern="dd/MM/yyyy"/></td>	
				<td><c:out value="${menuItem.category}"/></td>		
				<td><c:if test="${menuItem.freeDelivery}">Yes</c:if><c:if test="${!menuItem.freeDelivery}">No</c:if></td>		
				<td><a href="ShowEditMenuItem?menuItemId=<c:out value='${menuItem.id}'/>">Edit</a> | <a href="DeleteMenuItem?menuItemId=<c:out value='${menuItem.id}'/>">Delete</a></td>	
			</tr>
		</c:forEach>
	</table>
	<p><a href="add-menu-item.html"><button>Add New Menu Item</button></a></p>
</section>
<footer>
	<p>Copyright &copy; 2019</p>
</footer>
</body>
</html>