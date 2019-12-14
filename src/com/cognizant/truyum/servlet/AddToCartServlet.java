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
import com.cognizant.truyum.dao.CartDaoSqlImpl;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.dao.MenuItemDaoSqlImpl;
import com.cognizant.truyum.model.MenuItem;

@WebServlet("/AddToCart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long userId = 1;
		long menuItemId = Long.parseLong(request.getParameter("menuItemId"));
		CartDao cartDao = new CartDaoSqlImpl();
		try {
			cartDao.addCartItem(userId, menuItemId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			MenuItemDao menuItemDao = null;
		menuItemDao = new MenuItemDaoSqlImpl();
		List<MenuItem> menuItemList = menuItemDao.getMenuItemListCustomer();
		request.setAttribute("menuItemList", menuItemList);
		request.setAttribute("addCartStatus", true);
		request.setAttribute("cartNotEmpty", true);
		request.setAttribute("addedCartItemName", menuItemDao.getMenuItem(menuItemId).getName());
		try {
			request.setAttribute("cartSize", cartDao.getAllCartItems(userId).getMenuItemList().size());
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("menu-item-list-customer.jsp");
		requestDispatcher.forward(request, response);
	}
}