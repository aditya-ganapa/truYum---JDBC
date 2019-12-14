package com.cognizant.truyum.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.truyum.dao.CartDao;
import com.cognizant.truyum.dao.CartDaoCollectionImpl;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.Cart;

@WebServlet("/ShowCart")
public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long userId = 1;
		CartDao cartDao = new CartDaoCollectionImpl();
		Cart cart = null;
		boolean empty = false;
		try {
			cart = cartDao.getAllCartItems(userId);
		} catch (CartEmptyException | NullPointerException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart-empty.jsp");
			requestDispatcher.forward(request, response);
			empty = true;
		}
		if(!empty) {
			request.setAttribute("cart", cart);
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