package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.utils.DBUtil;

public class OrderDao {
	private Connection connection;

	public OrderDao() throws SQLException {
		connection = DBUtil.getConnection();
	}

	public void orderPizza(int mid, int cid) throws SQLException {
		String sql = "INSERT INTO orders (cid,mid) VALUES (?,?)";
		try (PreparedStatement insertStmt = connection.prepareCall(sql)) {
			insertStmt.setInt(1, cid);
			insertStmt.setInt(2, mid);
			insertStmt.executeUpdate();
		}
	}

	public List<Pizza> history(Customer customer) throws SQLException {
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		String sql = "SELECT m.* FROM orders o INNER JOIN menu m ON o.mid=m.mid WHERE o.cid = ?";
		try (PreparedStatement selectStmt = connection.prepareCall(sql)) {
			selectStmt.setInt(1, customer.getCid());
			ResultSet rs = selectStmt.executeQuery();
			while (rs.next()) {
				Pizza pizza = new Pizza();
				pizza.setMid(rs.getInt(1));
				pizza.setName(rs.getString(2));
				pizza.setDescription(rs.getString(3));
				pizza.setPrice(rs.getDouble(4));
				pizza.setCategory(rs.getString(5));
				pizzaList.add(pizza);
			}
			return pizzaList;
		}

	}

}
