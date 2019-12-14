package com.cognizant.truyum.dao;

import java.text.ParseException;

public class CartDaoSqlImplTest {
	public static void main(String[] args) throws CartEmptyException, ParseException {
		testAddCartItem();
		testRemoveCartItem();
	}
	
	public static void testAddCartItem() throws CartEmptyException, ParseException {
		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.addCartItem(1, 2);
		System.out.println(cartDao.getAllCartItems(1));
	}
	
	public static void testRemoveCartItem() {
		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.removeCartItem(1, 2);
		try {
			System.out.println(cartDao.getAllCartItems(1));
		} catch (CartEmptyException e) {
			System.out.println("\nCart is empty.");
		}
	}
}