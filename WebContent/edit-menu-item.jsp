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
	<h2>Edit Menu Item</h2>
	<form name="menuItemForm" onsubmit="return validateMenuItemForm()" method="post" action="EditMenuItem?menuItemId=<c:out value='${menuItem.id}'/>">
		<table>
			<tr>
				<td colspan="4">
					<label for="field-title">Name</label><br>
					<input type="text" id="field-title" name="title" value="<c:out value='${menuItem.name}'/>">
				</td>
			</tr>
			<tr>
				<td>
	    			<label for="field-price">Price (Rs.)</label><br>
					<input type="text" id="field-price" name="price"  value="<fmt:formatNumber value='${menuItem.price}' minFractionDigits='0' maxFractionDigits='2' groupingUsed = "false"/>">
				</td>
				<td>
					Active<br>
					<input type="radio" id="field-inStock-yes" name="inStock" value="yes" <c:if test="${menuItem.active}">checked</c:if>><label for="field-inStock-yes"> Yes </label><input type="radio" name="inStock" id="field-inStock-no" value="no" <c:if test="${!menuItem.active}">checked</c:if>><label for="field-inStock-no"> No</label>
				</td>
				<td>
					<label for="field-dateOfLaunch">Date of Launch</label><br>
					<input type="text" id="field-dateOfLaunch" name="dateOfLaunch" value="<fmt:formatDate value='${menuItem.dateOfLaunch}' pattern='dd/MM/yyyy'/>">
				</td>
				<td>
					<label for="field-category">Category</label><br>
					<select id="field-category" name="category">
						<option value=""></option>
						<option value="Starters" ${menuItem.category == "Starters" ? "selected" : ""}>Starters</option>
						<option value="Main Course" ${menuItem.category == "Main Course" ? "selected" : ""}>Main Course</option>
						<option value="Dessert" ${menuItem.category == "Dessert" ? "selected" : ""}>Dessert</option>
						<option value="Drinks" ${menuItem.category == "Drinks" ? "selected" : ""}>Drinks</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<input type="checkbox" id="field-freeDelivery" name="freeDelivery" value="yes"<c:if test="${menuItem.freeDelivery}">checked</c:if>><label for="field-freeDelivery"> Free Delivery</label>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="submit" value="Save">
				</td>
			</tr>
		</table>
	</form>
</section>
<footer>
	<p>Copyright &copy; 2019</p>
</footer>
</body>
</html>