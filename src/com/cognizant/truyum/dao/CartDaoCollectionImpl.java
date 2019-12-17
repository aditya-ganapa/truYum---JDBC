package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao {
	private static HashMap<Long, Cart> userCarts;
	
	public CartDaoCollectionImpl() {
		if (userCarts == null)
			userCarts = new HashMap<>();
	}

	@Override
	public void addCartItem(long userId, long menuItemId) throws ParseException {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		if (userCarts.containsKey(userId)) {
			List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
			menuItemList.add(menuItem);
		}
		else {
			Cart cart = new Cart();
			List<MenuItem> menuItemList = new ArrayList<>();
			menuItemList.add(menuItem);
			cart.setMenuItemList(menuItemList);
			userCarts.put(userId, cart);
		}
	}

	@Override
	public Cart getAllCartItems(long userId) throws CartEmptyException {
		List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
		if (menuItemList.isEmpty())
			throw new CartEmptyException();
		double total=0;
		for (MenuItem menuItem : menuItemList)
			total += menuItem.getPrice();
		return new Cart(menuItemList, total);
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
		for (int i = 0; i < menuItemList.size(); i++)
			if (menuItemList.get(i).getId() == menuItemId) {
				menuItemList.remove(i);
				break;
			}
	}
}