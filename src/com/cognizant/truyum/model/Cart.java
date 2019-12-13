package com.cognizant.truyum.model;

import java.util.List;

public class Cart {
	private List<MenuItem> menuItemList;
	private double total;
	
	public Cart() {
		super();
	}

	public Cart(List<MenuItem> menuItemList, double total) {
		super();
		this.menuItemList = menuItemList;
		this.total = total;
	}
	
	public List<MenuItem> getMenuItemList() {
		return menuItemList;
	}
	
	public void setMenuItemList(List<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		String str="\nItems in cart are:";
		for (MenuItem menuItem : menuItemList)
			str = str.concat("\n" + menuItem.toString());
		str = str.concat("\ntotal=" + total);
		return str;
	}
}