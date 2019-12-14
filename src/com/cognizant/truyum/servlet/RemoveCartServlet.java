package com.cognizant.truyum.servlet;

import java.io.IOException;
import java.text.ParseException;

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
import com.cognizant.truyum.model.Cart;

@WebServlet("/RemoveCart")
public class RemoveCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuItemDao menuItemDao = null;
		try {
			menuItemDao = new MenuItemDaoCollectionImpl();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long menuItemId = Long.parseLong(request.getParameter("menuItemId"));
		CartDao cartDao = new CartDaoCollectionImpl();
		long userId = 1;
		cartDao.removeCartItem(userId, menuItemId);
		request.setAttribute("removeCartItemStatus", true);
		Cart cart = null;
		boolean empty = false;
		try {
			cart = cartDao.getAllCartItems(userId);
		} catch (CartEmptyException e) {
			request.setAttribute("removedCartItemName", menuItemDao.getMenuItem(menuItemId).getName());
			request.setAttribute("removeLastCartItemStatus", true);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
			requestDispatcher.forward(request, response);
			empty = true;
		}
		if (!empty) {
			request.setAttribute("cart", cart);
			request.setAttribute("removedCartItemName", menuItemDao.getMenuItem(menuItemId).getName());
			request.setAttribute("cartNotEmpty", true);
			request.setAttribute("cartSize", cart.getMenuItemList().size());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
	//	try {
			requestDispatcher.forward(request, response);
	//	} catch (IllegalStateException e) {
	//	}
		}
	}
}