function validateMenuItemForm() {
	var title = document.forms["menuItemForm"]["title"].value;
	var price = document.forms["menuItemForm"]["price"].value;
	var dateOfLaunch = document.forms["menuItemForm"]["dateOfLaunch"].value;
	var category = document.forms["menuItemForm"]["category"].value;
	if (title == "") {
		alert("Name is required.");
		return false;
	}
	else if (title.length < 2 || title.length > 65) {
		alert("Name should have 2 to 65 characters.");
		return false;
	}			
	else if (price == "") {
		alert("Price is required.");
		return false;
	}
	else if (isNaN(price)) {
		alert("Price has to be a number.");
		return false;
	}
	else if (dateOfLaunch == "") {
		alert("Date of Launch is required.");
		return false;
	}			
	else if (category == "") {
		alert("Select one category.");
		return false;
	}			
	else {
		return true;
	}
}

function validateAddMenuItemForm() {
	var id = document.forms["addMenuItemForm"]["id"].value;
	var title = document.forms["addMenuItemForm"]["title"].value;
	var price = document.forms["addMenuItemForm"]["price"].value;
	var inStock = document.forms["addMenuItemForm"]["inStock"];
	var dateOfLaunch = document.forms["addMenuItemForm"]["dateOfLaunch"].value;
	var category = document.forms["addMenuItemForm"]["category"].value;
	if (id == "") {
		alert("Id is required.");
		return false;
	}
	else if (isNaN(id)) {
		alert("Id has to be a number.");
		return false;
	}
	else if (title == "") {
		alert("Name is required.");
		return false;
	}
	else if (title.length < 2 || title.length > 65) {
		alert("Name should have 2 to 65 characters.");
		return false;
	}			
	else if (price == "") {
		alert("Price is required.");
		return false;
	}
	else if (isNaN(price)) {
		alert("Price has to be a number.");
		return false;
	}
	else if ((!inStock[0].checked) && (!inStock[1].checked)) {
		alert("Active status is required.");
		return false;
	}
	else if (dateOfLaunch == "") {
		alert("Date of Launch is required.");
		return false;
	}			
	else if (category == "") {
		alert("Select one category.");
		return false;
	}			
	else {
		return true;
	}
}

function validateLoginAdminForm() {
	var username = document.forms["loginAdminForm"]["username"].value;
	var password = document.forms["loginAdminForm"]["password"].value;
	if (username == "") {
		alert("Username is required.");
		return false;
	}
	else if (password == "") {
		alert("Password is required.");
		return false;
	}
	else if (username != "aditya") {
		alert("Invalid Username.");
		return false;
	}
	else if (password != "password") {
		alert("Invalid Password.");
		return false;
	}
	else {
		return true;
	}
}

function validateLoginCustomerForm() {
	var username = document.forms["loginCustomerForm"]["username"].value;
	var password = document.forms["loginCustomerForm"]["password"].value;
	if (username == "") {
		alert("Username is required.");
		return false;
	}
	else if (password == "") {
		alert("Password is required.");
		return false;
	}
	else if (username != "aditya") {
		alert("Invalid Username.");
		return false;
	}
	else if (password != "password") {
		alert("Invalid Password.");
		return false;
	}
	else {
		return true;
	}
}