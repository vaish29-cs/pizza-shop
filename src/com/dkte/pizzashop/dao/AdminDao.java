package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkte.pizzashop.entities.Admin;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.utils.DBUtil;

public class AdminDao implements AutoCloseable {
	private Connection connection;

	public AdminDao() throws SQLException {
		connection = DBUtil.getConnection();
	}

	@Override
	public void close() throws SQLException {
		if (connection != null)
			connection.close();
	}

	public Admin accesInfo(String name, String password) throws SQLException {
		String sql = "SELECT * FROM Admin WHERE name=? AND password=?";
		PreparedStatement preparedStmt = connection.prepareCall(sql);
		preparedStmt.setString(1, name);
		preparedStmt.setString(2, password);
		ResultSet rs = preparedStmt.executeQuery();
		if (rs.next()) {
			Admin admin = new Admin();
			admin.setName(name);
			admin.setPassword(password);
			if (admin.getName().equals(rs.getObject(1)) && admin.getPassword().equals(rs.getObject(2)))
				;
			return admin;
		}
		return null;
	}

	public void addPizza(Pizza pizza) throws SQLException {
		String sql = "INSERT INTO menu (name,description,price,category) VALUES (?,?,?,?)";
		PreparedStatement insertStmt = connection.prepareCall(sql);
		insertStmt.setString(1, pizza.getName());
		insertStmt.setString(2, pizza.getDescription());
		insertStmt.setDouble(3, pizza.getPrice());
		insertStmt.setString(4, pizza.getCategory());
		insertStmt.executeUpdate();

		String str = "SELECT mid FROM menu WHERE name=?";
		PreparedStatement stmt = connection.prepareCall(str);
		stmt.setString(1, pizza.getName());
		ResultSet rs = stmt.executeQuery();
		if (rs.next())
			pizza.setMid(rs.getInt(1));

		String modification = "INSERT INTO modifications (modified_changes,pizza_id,pizza_name,other) VALUES ('New menu added',?,?,?)";
		PreparedStatement insert = connection.prepareCall(modification);
		insert.setInt(1, pizza.getMid());
		insert.setString(2, pizza.getName());
		insert.setDouble(3, pizza.getPrice());
		insert.executeUpdate();
	}

	public void updatePizza(int mid, double price, Pizza pizza) throws SQLException {
		String sql = "UPDATE menu SET price=? WHERE mid=?";
		PreparedStatement updateStmt = connection.prepareCall(sql);
		updateStmt.setDouble(1, price);
		updateStmt.setInt(2, mid);
		updateStmt.executeUpdate();

		String str = "SELECT mid,name FROM menu WHERE mid=?";
		PreparedStatement stmt = connection.prepareCall(str);
		stmt.setInt(1, mid);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			pizza.setMid(rs.getInt(1));
			pizza.setName(rs.getString(2));
		}

		String modification = "INSERT INTO modifications (modified_changes,pizza_id,pizza_name,other) VALUES ('Menu price updated',?,?,?)";
		PreparedStatement insert = connection.prepareCall(modification);
		insert.setInt(1, mid);
		insert.setString(2, pizza.getName());
		insert.setDouble(3, price);
		insert.executeUpdate();
	}

	public void deletePizza(int mid, Pizza pizza) throws SQLException {
		String str = "SELECT mid,name FROM menu WHERE mid=?";
		PreparedStatement stmt = connection.prepareCall(str);
		stmt.setInt(1, mid);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			pizza.setMid(rs.getInt(1));
			pizza.setName(rs.getString(2));
		}

		String sql = "DELETE FROM menu WHERE mid=?";
		PreparedStatement deleteStmt = connection.prepareCall(sql);
		deleteStmt.setInt(1, mid);
		deleteStmt.executeUpdate();

		String modification = "INSERT INTO modifications (modified_changes,pizza_id,pizza_name) VALUES ('Menu deleted',?,?)";
		PreparedStatement insert = connection.prepareCall(modification);
		insert.setInt(1, mid);
		insert.setString(2, pizza.getName());
		insert.executeUpdate();
	}

	public List<Pizza> display() throws SQLException {
		String modification = "INSERT INTO modifications (modified_changes) VALUES ('All menu displayed')";
		PreparedStatement insert = connection.prepareCall(modification);
		insert.executeUpdate();

		String sql = "SELECT * FROM menu";
		Pizza pizza = new Pizza();
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		PreparedStatement preparedStmt = connection.prepareCall(sql);
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next()) {
			pizza = new Pizza();
			pizza.setMid(rs.getInt(1));
			pizza.setName(rs.getString(2));
			pizza.setDescription(rs.getString(3));
			pizza.setPrice(rs.getDouble(4));
			pizza.setCategory(rs.getString(5));
			pizzaList.add(pizza);
		}
		return pizzaList;
	}

	public List<Double> calculateProfit() throws SQLException {
		String modification = "INSERT INTO modifications (modified_changes) VALUES ('Profit calculated')";
		PreparedStatement insert = connection.prepareCall(modification);
		insert.executeUpdate();

		List<Double> priceList = new ArrayList<Double>();
		String sql = "SELECT price FROM menu";
		PreparedStatement preparedStmt = connection.prepareCall(sql);
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next()) {
			double price = rs.getDouble("price");
			priceList.add(price);
		}
		return priceList;
	}

	public List<Pizza> orderDisplay() throws SQLException {

		String modification = "INSERT INTO modifications (modified_changes) VALUES ('All orders displayed')";
		PreparedStatement insert = connection.prepareCall(modification);
		insert.executeUpdate();

		List<Pizza> orderList = new ArrayList<Pizza>();
		Pizza pizza;
		String sql = "SELECT m.* FROM orders o INNER JOIN menu m ON o.mid=m.mid";
		PreparedStatement preparedStmt = connection.prepareCall(sql);
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next()) {
			pizza = new Pizza();
			pizza.setMid(rs.getInt(1));
			pizza.setName(rs.getString(2));
			pizza.setDescription(rs.getString(3));
			pizza.setPrice(rs.getDouble(4));
			pizza.setCategory(rs.getString(5));
			orderList.add(pizza);
		}
		return orderList;

	}

	public List<Customer> customerDisplay() throws SQLException {

		String modification = "INSERT INTO modifications (modified_changes) VALUES ('All customers displayed')";
		PreparedStatement insert = connection.prepareCall(modification);
		insert.executeUpdate();

		List<Customer> customerList = new ArrayList<Customer>();
		String sql = "SELECT * FROM customer";
		PreparedStatement preparedStmt = connection.prepareCall(sql);
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next()) {
			Customer customer = new Customer();
			customer.setCid(rs.getInt(1));
			customer.setName(rs.getString(2));
			customer.setEmail(rs.getString(3));
			customer.setPassword(rs.getString(4));
			customer.setMobile(rs.getString(5));
			customerList.add(customer);
		}
		return customerList;
	}
}
