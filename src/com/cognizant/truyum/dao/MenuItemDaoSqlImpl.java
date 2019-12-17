package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class MenuItemDaoSqlImpl implements MenuItemDao {
	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		Connection connection = ConnectionHandler.getConnection();
		List<MenuItem> menuItemListAdmin = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from menu_item");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MenuItem menuItem = new MenuItem(resultSet.getLong(1), resultSet.getString(2), resultSet.getFloat(3), resultSet.getString(4).equals("Yes"), new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(5)), resultSet.getString(6), resultSet.getString(7).equals("Yes"));
				menuItemListAdmin.add(menuItem);
			}
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return menuItemListAdmin;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		Connection connection = ConnectionHandler.getConnection();
		List<MenuItem> menuItemListCustomer = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from menu_item where me_active='Yes' and datediff(curdate(), me_date_of_launch)>=0");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MenuItem menuItem = new MenuItem(resultSet.getLong(1), resultSet.getString(2), resultSet.getFloat(3), resultSet.getString(4).equals("Yes"), new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(5)), resultSet.getString(6), resultSet.getString(7).equals("Yes"));
				menuItemListCustomer.add(menuItem);
			}
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return menuItemListCustomer;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		Connection connection = ConnectionHandler.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update menu_item set me_name=?, me_price=?, me_active=?, me_date_of_launch=?, me_category=?, me_free_delivery=? where me_id=?");
			preparedStatement.setString(1, menuItem.getName());
			preparedStatement.setFloat(2, menuItem.getPrice());
			preparedStatement.setString(3, menuItem.isActive() ? "Yes" : "No");
			preparedStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(menuItem.getDateOfLaunch()));
			preparedStatement.setString(5, menuItem.getCategory());
			preparedStatement.setString(6, menuItem.isFreeDelivery() ? "Yes": "No");
			preparedStatement.setInt(7, (int) menuItem.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		Connection connection = ConnectionHandler.getConnection();
		MenuItem menuItem = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from menu_item where me_id=?");
			preparedStatement.setInt(1, (int) menuItemId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			menuItem = new MenuItem(resultSet.getLong(1), resultSet.getString(2), resultSet.getFloat(3), resultSet.getString(4).equals("Yes"), new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(5)), resultSet.getString(6), resultSet.getString(7).equals("Yes"));
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return menuItem;
	}

	@Override
	public void addMenuItem(MenuItem menuItem) {
		Connection connection = ConnectionHandler.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into menu_item values(?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, (int) menuItem.getId());
			preparedStatement.setString(2, menuItem.getName());
			preparedStatement.setFloat(3, menuItem.getPrice());
			preparedStatement.setString(4, menuItem.isActive() ? "Yes" : "No");
			preparedStatement.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(menuItem.getDateOfLaunch()));
			preparedStatement.setString(6, menuItem.getCategory());
			preparedStatement.setString(7, menuItem.isFreeDelivery() ? "Yes" : "No");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void removeMenuItem(long menuItemId) {
		Connection connection = ConnectionHandler.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from cart where ct_me_id=?");
			preparedStatement.setInt(1, (int) menuItemId);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement("delete from menu_item where me_id=?");
			preparedStatement.setInt(1, (int) menuItemId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}