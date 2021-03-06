<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<script src="js/script.js"></script>
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<c:if test="${removeLastCartItemStatus}">
		<meta http-equiv="refresh" content="3; url = cart-empty.jsp"/>
	</c:if>
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
	<h2>Cart</h2>
	<c:if test="${removeCartItemStatus}">
		<p class="notification">${removedCartItemName} removed from cart successfully.</p>
	</c:if>
	<table>
		<tr>
			<th>Name</th>
			<th>Free Delivery</th>
			<th>Price</th>
			<th></th>
		</tr>
		<c:if test="${!removeLastCartItemStatus}">
			<c:forEach items="${cart.menuItemList}" var="menuItem">
				<tr>
					<td><c:out value="${menuItem.name}"/></td>
					<td><c:if test="${menuItem.freeDelivery}">Yes</c:if><c:if test="${!menuItem.freeDelivery}">No</c:if></td>	
					<td><fmt:formatNumber value="${menuItem.price}" type="currency" currencySymbol="Rs. "/></td>
					<td><a href="RemoveCart?menuItemId=<c:out value='${menuItem.id}'/>">Delete</a></td>	
				</tr>
			</c:forEach>
			<tr>
				<th></th>
				<th>Total</th>
				<th><fmt:formatNumber value="${cart.total}" type="currency" currencySymbol="Rs. "/></th>
				<th></th>
			</tr>
		</c:if>
	</table>
</section>
<footer>
	<p>Copyright &copy; 2019</p>
</footer>
</body>
</html>