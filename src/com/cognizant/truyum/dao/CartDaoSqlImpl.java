package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImpl implements CartDao {
	@Override
	public void addCartItem(long userId, long menuItemId) throws ParseException {
		Connection connection = ConnectionHandler.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from user where us_id=?");
			preparedStatement.setInt(1, (int) userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				preparedStatement = connection.prepareStatement("insert into user(us_id) values(?)");
				preparedStatement.setInt(1, (int) userId);
				preparedStatement.executeUpdate();
			}
			preparedStatement = connection.prepareStatement("insert into cart(ct_us_id, ct_me_id) values(?, ?)");
			preparedStatement.setInt(1, (int) userId);
			preparedStatement.setInt(2, (int) menuItemId);
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
	public Cart getAllCartItems(long userId) throws CartEmptyException {
		Connection connection = ConnectionHandler.getConnection();
		List<MenuItem> arrayList = new ArrayList<>();
		double total = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from cart inner join menu_item on ct_me_id=me_id where ct_us_id=?");
			preparedStatement.setInt(1, (int) userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next())
				throw new CartEmptyException();
			MenuItem menuItem = new MenuItem(resultSet.getLong("me_id"), resultSet.getString("me_name"), resultSet.getFloat("me_price"), resultSet.getString("me_active").equals("Yes"), new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("me_date_of_launch")), resultSet.getString("me_category"), resultSet.getString("me_free_delivery").equals("Yes"));
			arrayList.add(menuItem);
			while (resultSet.next()) {
				menuItem = new MenuItem(resultSet.getLong("me_id"), resultSet.getString("me_name"), resultSet.getFloat("me_price"), resultSet.getString("me_active").equals("Yes"), new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("me_date_of_launch")), resultSet.getString("me_category"), resultSet.getString("me_free_delivery").equals("Yes"));
				arrayList.add(menuItem);
			}
			preparedStatement = connection.prepareStatement("select sum(me_price) from cart inner join menu_item on ct_me_id=me_id where ct_us_id=?");
			preparedStatement.setInt(1, (int) userId);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			total = resultSet.getDouble(1);
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
		return new Cart(arrayList, total);
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		Connection connection = ConnectionHandler.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select ct_id from cart where ct_us_id=? and ct_me_id=?");
			preparedStatement.setInt(1, (int) userId);
			preparedStatement.setInt(2, (int) menuItemId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int cartId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement("delete from cart where ct_id=?");
			preparedStatement.setInt(1, cartId);
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