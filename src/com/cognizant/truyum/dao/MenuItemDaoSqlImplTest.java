package com.cognizant.truyum.dao;

import java.text.ParseException;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {
	public static void main(String[] args) throws ParseException {
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testModifyMenuItem();
	}
	
	public static void testGetMenuItemListAdmin() throws ParseException {
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		for (MenuItem menuItem : menuItemDao.getMenuItemListAdmin())
			System.out.println(menuItem);
	}
	
	public static void testGetMenuItemListCustomer() throws ParseException {
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		for (MenuItem menuItem : menuItemDao.getMenuItemListCustomer())
			System.out.println(menuItem);
	}
	
	public static void testModifyMenuItem() throws ParseException {
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		menuItemDao.modifyMenuItem(new MenuItem(1, "Ice Cream", 50, true, DateUtil.convertToDate("25/10/2019"), "Dessert", false));
		System.out.println(menuItemDao.getMenuItem(1));
	}
}