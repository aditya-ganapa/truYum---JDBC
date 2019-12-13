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
		List<MenuItem> arrayList = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from menu_item");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MenuItem menuItem = new MenuItem(resultSet.getLong(1), resultSet.getString(2), resultSet.getFloat(3), resultSet.getString(4).equals("Yes"), new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(5)), resultSet.getString(6), resultSet.getString(7).equals("Yes"));
				arrayList.add(menuItem);
			}
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		Connection connection = ConnectionHandler.getConnection();
		List<MenuItem> arrayList = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from menu_item where me_active='Yes' and datediff(curdate(), me_date_of_launch)>=0");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MenuItem menuItem = new MenuItem(resultSet.getLong(1), resultSet.getString(2), resultSet.getFloat(3), resultSet.getString(4).equals("Yes"), new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(5)), resultSet.getString(6), resultSet.getString(7).equals("Yes"));
				arrayList.add(menuItem);
			}
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMenuItem(long menuItemId) {
		// TODO Auto-generated method stub
		
	}

}
