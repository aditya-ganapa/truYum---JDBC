package com.cognizant.truyum.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.dao.MenuItemDaoSqlImpl;
import com.cognizant.truyum.model.MenuItem;

@WebServlet("/ShowEditMenuItem")
public class ShowEditMenuItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuItemDao menuItemDao = null;
		menuItemDao = new MenuItemDaoSqlImpl();
		long menuItemId = Long.parseLong(request.getParameter("menuItemId"));
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		request.setAttribute("menuItem", menuItem);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-menu-item.jsp");
		requestDispatcher.forward(request, response);
	}
}