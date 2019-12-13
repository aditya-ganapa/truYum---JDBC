package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.Scanner;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class TestMain {

	public static void main(String[] args) throws ParseException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		CartDao cartDao = new CartDaoCollectionImpl();
		while (true) {
			System.out.print("\n1.Admin 2.Customer\nEnter User Type:");
			switch (scanner.nextInt()) {
			case 1:
				boolean admin = true;
				while (admin) {
					System.out.print("\n1.View Menu Item List\n2.Edit Menu Item Details\n3.View Details of Particular Menu Item\n4.Add Menu Item\n5.Remove Menu Item\n6.Change User Type\nEnter Choice:");
					switch (scanner.nextInt()) {
					case 1:
						System.out.println("\nMenu Items:");
						for (MenuItem menuItem : menuItemDao.getMenuItemListAdmin())
							System.out.println(menuItem);
						break;
					case 2:
						System.out.print("Enter id of menu item to be edited:");
						long id = scanner.nextLong();
						System.out.print("Enter name:");
						scanner.nextLine();
						String name = scanner.nextLine();
						System.out.print("Enter price:");
						float price = scanner.nextFloat();
						System.out.print("Enter active status:");
						boolean active = scanner.nextBoolean();
						System.out.print("Enter date of launch:");
						scanner.nextLine();
						String dateOfLaunch = scanner.nextLine();
						System.out.print("Enter cateogory:");
						String category = scanner.nextLine();
						System.out.print("Enter free delivery status:");
						boolean freeDelivery = scanner.nextBoolean();
						menuItemDao.modifyMenuItem(new MenuItem(id, name, price, active, DateUtil.convertToDate(dateOfLaunch), category, freeDelivery));
						System.out.println("\nItem details edited succesfully.");
						break;
					case 3:
						System.out.print("Enter id of menu item to be viewed:");
						System.out.println("\n"+menuItemDao.getMenuItem(scanner.nextLong()));
						break;
					case 4:
						System.out.print("Enter id of menu item to be added:");
						long id1 = scanner.nextLong();
						System.out.print("Enter name:");
						scanner.nextLine();
						String name1 = scanner.nextLine();
						System.out.print("Enter price:");
						float price1 = scanner.nextFloat();
						System.out.print("Enter active status:");
						boolean active1 = scanner.nextBoolean();
						System.out.print("Enter date of launch:");
						scanner.nextLine();
						String dateOfLaunch1 = scanner.nextLine();
						System.out.print("Enter cateogory:");
						String category1 = scanner.nextLine();
						System.out.print("Enter free delivery status:");
						boolean freeDelivery1 = scanner.nextBoolean();
						menuItemDao.addMenuItem(new MenuItem(id1, name1, price1, active1, DateUtil.convertToDate(dateOfLaunch1), category1, freeDelivery1));
						System.out.println("\nItem added to list succesfully.");
						break;
					case 5:
						System.out.print("Enter id of menu item to be removed:");
						menuItemDao.removeMenuItem(scanner.nextLong());
						System.out.println("\nItem removed from list succesfully.");
						break;
					case 6:
						admin = false;
						break;
					default:
						System.out.println("\nInvalid Input");
						break;
					}
				}
				break;
			case 2:
				boolean customer = true;
				while (customer) {
					System.out.print("\n1.View Menu Item List\n2.Add Item to Cart\n3.View Cart Items\n4.Remove Item from Cart\n5.Change User Type\nEnter Choice:");
					switch (scanner.nextInt()) {
					case 1:
						for (MenuItem menuItem : menuItemDao.getMenuItemListCustomer())
							System.out.println(menuItem);
						break;
					case 2:
						System.out.print("Enter user id:");
						long userId = scanner.nextLong();
						System.out.print("Enter id of menu item to be added:");
						long menuItemId = scanner.nextLong();
						cartDao.addCartItem(userId, menuItemId);
						System.out.println("\nItem added to cart succesfully.");
						break;
					case 3:
						System.out.print("Enter user id:");
						long userId1 = scanner.nextLong();
						try {
							System.out.println(cartDao.getAllCartItems(userId1));
						} catch (Exception e) {
							System.out.println("\nCart is empty.");
						}
						break;
					case 4:
						System.out.print("Enter user id:");
						long userId11 = scanner.nextLong();
						System.out.print("Enter id of menu item to be removed:");
						long menuItemId1 = scanner.nextLong();
						cartDao.removeCartItem(userId11, menuItemId1);
						System.out.println("\nItem removed from cart succesfully.");
						break;
					case 5:
						customer = false;
						break;
					default:
						System.out.println("\nInvalid Input");
						break;
					}
				}
				break;
			default:
				System.out.println("\nInvalid Input");
				break;
			}
		}
	}
}