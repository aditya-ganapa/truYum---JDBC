<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<a href="home.html" class="customer-logout-link">Logout</a>
		<a href="ShowCart" class="cart-link">Cart<c:if test="${cartNotEmpty}">[${cartSize}]</c:if></a>
		<a href="ShowMenuItemListCustomer" class="customer-menu-link">Menu</a>
	</p>
</nav>
<section>
	<h2>Menu Items</h2>
	<c:if test="${addCartStatus}">
		<p class="notification">${addedCartItemName} added to cart successfully.</p>
	</c:if>
	<table>
		<tr>
			<th>Name</th>
			<th>Free Delivery</th>
			<th>Price</th>
			<th>Category</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${menuItemList}" var="menuItem">
			<tr>
				<td><c:out value="${menuItem.name}"/></td>
				<td><c:if test="${menuItem.freeDelivery}">Yes</c:if><c:if test="${!menuItem.freeDelivery}">No</c:if></td>
				<td><fmt:formatNumber value="${menuItem.price}" type="currency"  currencySymbol="Rs. "/></td>
				<td><c:out value="${menuItem.category}"/></td>
				<td><a href="AddToCart?menuItemId=<c:out value='${menuItem.id}'/>">Add to Cart</a></td>	
			</tr>
		</c:forEach>
	</table>
</section>
<footer>
	<p>Copyright &copy; 2019</p>
</footer>
</body>
</html>