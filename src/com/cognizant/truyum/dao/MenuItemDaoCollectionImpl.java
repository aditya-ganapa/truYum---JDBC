package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {
	private static List<MenuItem> menuItemList;

	public MenuItemDaoCollectionImpl() throws ParseException {
		if (menuItemList == null) {
			menuItemList = new ArrayList<>();
			menuItemList.add(new MenuItem(0, "Sandwich", 99, true, DateUtil.convertToDate("15/03/2017"), "Main Course", true));
			menuItemList.add(new MenuItem(1, "Burger", 129, true, DateUtil.convertToDate("23/12/2017"), "Main Course", false));
			menuItemList.add(new MenuItem(2, "Pizza", 149, true, DateUtil.convertToDate("21/08/2017"), "Main Course", false));
			menuItemList.add(new MenuItem(3, "French Fries", 57, false, DateUtil.convertToDate("02/07/2017"), "Starters", true));
			menuItemList.add(new MenuItem(4, "Chocolate Brownie", 32, true, DateUtil.convertToDate("02/11/2022"), "Dessert", true));
		}
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> menuItemListCustomer = new ArrayList<>();
		for (MenuItem menuItem : menuItemList)
			if ((menuItem.getDateOfLaunch().compareTo(new Date()) <= 0) && menuItem.isActive())
				menuItemListCustomer.add(menuItem);
		return menuItemListCustomer;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		for (int i = 0; i < menuItemList.size(); i++)
			if (menuItemList.get(i).getId() == menuItem.getId()) {
				menuItemList.set(i, menuItem);
				break;
			}
	}
	
	@Override
	public MenuItem getMenuItem(long menuItemId) {
		for (MenuItem menuItem : menuItemList)
			if (menuItem.getId() == menuItemId)
				return menuItem;
		return null;
	}

	@Override
	public void addMenuItem(MenuItem menuItem) {
		menuItemList.add(menuItem);
	}

	@Override
	public void removeMenuItem(long menuItemId) {
		for (int i = 0; i < menuItemList.size(); i++)
			if (menuItemList.get(i).getId() == menuItemId)
				menuItemList.remove(i);
	}
}