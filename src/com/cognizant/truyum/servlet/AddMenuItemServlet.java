package com.cognizant.truyum.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.dao.MenuItemDaoSqlImpl;
import com.cognizant.truyum.model.MenuItem;

@WebServlet("/AddMenuItem")
public class AddMenuItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuItem menuItem = new MenuItem();
		menuItem.setId(Long.parseLong(request.getParameter("id")));
		menuItem.setName(request.getParameter("title"));
		menuItem.setPrice(Float.parseFloat(request.getParameter("price")));
		menuItem.setActive(request.getParameter("inStock").equals("yes"));
		try {
			menuItem.setDateOfLaunch(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dateOfLaunch")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menuItem.setCategory(request.getParameter("category"));
		menuItem.setFreeDelivery(request.getParameter("freeDelivery") != null);
		MenuItemDao menuItemDao = null;
		menuItemDao = new MenuItemDaoSqlImpl();
		menuItemDao.addMenuItem(menuItem);
		request.setAttribute("title", request.getParameter("title"));
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("add-menu-item-status.jsp");
		requestDispatcher.forward(request, response);
	}
}