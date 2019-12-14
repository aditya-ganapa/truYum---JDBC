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

@WebServlet("/DeleteMenuItem")
public class DeleteMenuItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuItemDao menuItemDao = null;
		menuItemDao = new MenuItemDaoSqlImpl();
		long menuItemId = Long.parseLong(request.getParameter("menuItemId"));
		request.setAttribute("removedMenuItemName", menuItemDao.getMenuItem(menuItemId).getName());
		menuItemDao.removeMenuItem(menuItemId);
		request.setAttribute("removeMenuItemStatus", true);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ShowMenuItemListAdmin");
		requestDispatcher.forward(request, response);
	}
}