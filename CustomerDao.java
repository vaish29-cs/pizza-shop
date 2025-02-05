package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.utils.DBUtil;

public class CustomerDao implements AutoCloseable {
	private Connection connection;

	public CustomerDao() throws SQLException {
		connection = DBUtil.getConnection();
	}

	@Override
	public void close() throws SQLException {
		if (connection != null)
			connection.close();
	}

	public void insertCustomer(Customer customer) throws SQLException {
		String sql = "INSERT INTO customer(name,email,password,mobile) VALUES (?,?,?,?)";
		PreparedStatement insertStmt = connection.prepareCall(sql);
		insertStmt.setString(1, customer.getName());
		insertStmt.setString(2, customer.getEmail());
		insertStmt.setString(3, customer.getPassword());
		insertStmt.setString(4, customer.getMobile());
		insertStmt.executeUpdate();
	}

	public Customer getCustomer(String email, String password) throws SQLException {
		String sql = "SELECT * FROM Customer WHERE email=? AND password=?";
		PreparedStatement preparedStmt = connection.prepareCall(sql);
		preparedStmt.setString(1, email);
		preparedStmt.setString(2, password);
		ResultSet rs = preparedStmt.executeQuery();
		if (rs.next()) {
			Customer customer = new Customer();
			customer.setCid(rs.getInt(1));
			customer.setName(rs.getString(2));
			customer.setEmail(email);
			customer.setPassword(password);
			customer.setMobile(rs.getString(5));
			return customer;
		}
		return null;
	}

}
