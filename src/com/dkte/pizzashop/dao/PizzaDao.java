package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.utils.DBUtil;

public class PizzaDao implements AutoCloseable {
	private Connection connection;

	public PizzaDao() throws SQLException {
		connection = DBUtil.getConnection();
	}

	@Override
	public void close() throws Exception {
		if (connection != null)
			connection.close();
	}

	public List<Pizza> getAllPizza() throws SQLException {
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		Pizza pizza = new Pizza();
		String sql = "SELECT * FROM menu";
		PreparedStatement selectStmt = connection.prepareCall(sql);
		ResultSet rs = selectStmt.executeQuery();
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

	public List<Pizza> vegPizza() throws SQLException {
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		Pizza pizza = new Pizza();
		String sql = "SELECT * FROM menu WHERE category='Veg pizza'";
		PreparedStatement selectStmt = connection.prepareCall(sql);
		ResultSet rs = selectStmt.executeQuery();
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

	public List<Pizza> nonVegPizza() throws SQLException {
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		Pizza pizza = new Pizza();
		String sql = "SELECT * FROM menu WHERE category='Non veg pizza'";
		PreparedStatement selectStmt = connection.prepareCall(sql);
		ResultSet rs = selectStmt.executeQuery();
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

	public List<Pizza> garlicBread() throws SQLException {
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		Pizza pizza = new Pizza();
		String sql = "SELECT * FROM menu WHERE category='Garlic Bread'";
		PreparedStatement selectStmt = connection.prepareCall(sql);
		ResultSet rs = selectStmt.executeQuery();
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

	public List<Pizza> drinks() throws SQLException {
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		Pizza pizza = new Pizza();
		String sql = "SELECT * FROM menu WHERE category='Drinks'";
		PreparedStatement selectStmt = connection.prepareCall(sql);
		ResultSet rs = selectStmt.executeQuery();
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

	public double bill(int orderId) throws SQLException {
		String sql = "SELECT price FROM menu WHERE mid=?";
		PreparedStatement selectStmt = connection.prepareCall(sql);
		selectStmt.setInt(1, orderId);
		Pizza pizza = new Pizza();
		ResultSet rs = selectStmt.executeQuery();
		if (rs.next()) {
			pizza = new Pizza();
			pizza.setPrice(rs.getDouble("price"));
		}
		return pizza.getPrice();
	}
}
