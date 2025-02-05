package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;

public class PizzaSubMenu {

	public static void vegPizza() {
		try {
			List<Pizza> pizzaList = new ArrayList<Pizza>();
			PizzaDao pizzaDao;
			pizzaDao = new PizzaDao();
			pizzaList = pizzaDao.vegPizza();
			for (Pizza pizza : pizzaList) {
				pizza.display();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void nonVegPizza() {
		try {
			List<Pizza> pizzaList = new ArrayList<Pizza>();
			PizzaDao pizzaDao;
			pizzaDao = new PizzaDao();
			pizzaList = pizzaDao.nonVegPizza();
			for (Pizza pizza : pizzaList) {
				pizza.display();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void garlicBread() {
		try {
			List<Pizza> pizzaList = new ArrayList<Pizza>();
			PizzaDao pizzaDao;
			pizzaDao = new PizzaDao();
			pizzaList = pizzaDao.garlicBread();
			for (Pizza pizza : pizzaList) {
				pizza.display();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void drinks() {
		try {
			List<Pizza> pizzaList = new ArrayList<Pizza>();
			PizzaDao pizzaDao;
			pizzaDao = new PizzaDao();
			pizzaList = pizzaDao.drinks();
			for (Pizza pizza : pizzaList) {
				pizza.display();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int pizzaSubMenu(Scanner sc, Customer customer) {
		System.out.println("-----------------------------------------");
		System.out.println("0.Exit");
		System.out.println("1.Veg Pizza");
		System.out.println("2.Non veg Pizza");
		System.out.println("3.Garlic Bread");
		System.out.println("4.Drinks");
		System.out.println("Enter your choice -");
		int ch = sc.nextInt();
		System.out.println("-----------------------------------------");
		return ch;
	}

	public void pizzaSubMain(Scanner sc, Customer customer) {
		int ch;
		while ((ch = pizzaSubMenu(sc, customer)) != 0) {
			switch (ch) {
			case 1:
				vegPizza();
				break;
			case 2:
				nonVegPizza();
				break;
			case 3:
				garlicBread();
				break;
			case 4:
				drinks();
				break;
			default:
				System.out.println("Wrong choice..... :(");
				break;
			}
		}
	}
}
