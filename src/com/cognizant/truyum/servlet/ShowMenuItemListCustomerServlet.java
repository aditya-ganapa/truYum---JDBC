package com.cognizant.truyum.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.truyum.dao.CartDao;
import com.cognizant.truyum.dao.CartDaoCollectionImpl;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.dao.MenuItemDaoCollectionImpl;
import com.cognizant.truyum.model.MenuItem;

@WebServlet("/ShowMenuItemListCustomer")
public class ShowMenuItemListCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuItemDao menuItemDao = null;
		try {
			menuItemDao = new MenuItemDaoCollectionImpl();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<MenuItem> menuItemList = menuItemDao.getMenuItemListCustomer();
		request.setAttribute("menuItemList", menuItemList);
		long userId = 1;
		long cartSize = 0;
		boolean empty = false;
		CartDao cartDao = new CartDaoCollectionImpl();
		try {
			cartSize = cartDao.getAllCartItems(userId).getMenuItemList().size();
		} catch (CartEmptyException | NullPointerException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("menu-item-list-customer.jsp");
			requestDispatcher.forward(request, response);
			empty = true;
		}
		if(!empty) {
			request.setAttribute("cartNotEmpty", true);
			request.setAttribute("cartSize", cartSize);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("menu-item-list-customer.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}