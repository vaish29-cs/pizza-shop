package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dkte.pizzashop.dao.AdminDao;
import com.dkte.pizzashop.dao.OrderDao;
import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entities.Admin;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;

public class SubMenu {
	public int orderId;
	public double sum;

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public static void PizzaMenu() {
		try {
			PizzaDao pizza = new PizzaDao();
			List<Pizza> pizzaList;
			pizzaList = pizza.getAllPizza();

			for (Pizza ele : pizzaList) {
				pizzaList = pizza.getAllPizza();
				ele.display();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void placeOrder(Scanner sc, Customer customer, int mid) {
		try {
			OrderDao orderDao = new OrderDao();
			orderDao.orderPizza(mid, customer.getCid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void orderHistory(Customer customer) {
		try {
			OrderDao orderDao = new OrderDao();
			List<Pizza> pizzaList;
			pizzaList = orderDao.history(customer);
			for (Pizza pizza : pizzaList) {
				pizza.display();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void totalBill(Customer customer, int orderId, SubMenu sub) {
		try {
			PizzaDao pizza = new PizzaDao();
			double price = pizza.bill(orderId);
			double sum;
			sum = sub.getSum() + price;
			sub.setSum(sum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int subMenu(Scanner sc, Customer customer) {

		System.out.println("******Welcome " + customer.getName() + "**********");
		System.out.println("0.Logout");
		System.out.println("1.Display Menu");
		System.out.println("2.Order a Menu");
		System.out.println("3.Total Bill");
		System.out.println("4.Order History");
		System.out.print("Enter choice -");
		int ch = sc.nextInt();
		System.out.println("**************************");
		return ch;
	}

	public static void submain(Scanner sc, Customer customer) {
		int ch;
		SubMenu sub = new SubMenu();
		while ((ch = subMenu(sc, customer)) != 0) {
			switch (ch) {
			case 1:
				PizzaSubMenu pm = new PizzaSubMenu();
				pm.pizzaSubMain(sc, customer);
				break;
			case 2:
				System.out.println("Enter menu id -");
				int mid = sc.nextInt();
				placeOrder(sc, customer, mid);
				totalBill(customer, mid, sub);
				System.out.println("Order Placed.....:)");
				break;
			case 3:
				System.out.println("Total Bill : " + sub.getSum());
				break;
			case 4:
				orderHistory(customer);
				break;
			default:
				System.out.println("Wrong choice..... :(");
				break;
			}
		}
	}

	public static int adminSubMenu(Scanner sc, Admin admin) {
		System.out.println("************Welcome " + admin.getName() + "**************");
		System.out.println("0.Exit");
		System.out.println("1.Add new menu");
		System.out.println("2.Update Price of existing menu");
		System.out.println("3.Delete menu");
		System.out.println("4.Display all exixting menu");
		System.out.println("5.Calculate Total profit");
		System.out.println("6.Display all orders");
		System.out.println("7.Display all customer");
		System.out.print("Enter choice -");
		int ch = sc.nextInt();
		System.out.println("*********************************************");
		return ch;
	}

	public static void addPizza(Scanner sc, AdminDao adminDao) {
		Pizza pizza = new Pizza();
		try {
			pizza.accept(sc);
			adminDao.addPizza(pizza);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updatePizza(Scanner sc, AdminDao adminDao) {
		System.out.println("Enter menu id you want to update");
		int mid = sc.nextInt();
		System.out.println("Enter new price");
		double price = sc.nextDouble();
		try {
			Pizza pizza = new Pizza();
			adminDao.updatePizza(mid, price, pizza);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deletePizza(Scanner sc, AdminDao adminDao) {
		System.out.println("Enter menu id you want to delete");
		int mid = sc.nextInt();
		try {
			Pizza pizza = new Pizza();
			adminDao.deletePizza(mid, pizza);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void display(AdminDao adminDao) {
		try {
			List<Pizza> pizzaList;
			pizzaList = adminDao.display();
			for (Pizza pizza : pizzaList) {
				pizza.display();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void calculateProfit(AdminDao adminDao) {
		try {
			List<Double> priceList = new ArrayList<Double>();
			priceList = adminDao.calculateProfit();
			double sum = 0;
			for (Double double1 : priceList) {
				sum += double1;
			}
			System.out.println("Total profit = " + sum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void orderDisplay(AdminDao adminDao) {
		try {
			List<Pizza> orderList = new ArrayList<Pizza>();
			orderList = adminDao.orderDisplay();
			for (Pizza order : orderList) {
				order.display();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void customerDisplay(AdminDao adminDao) {
		try {
			List<Customer> customerList = new ArrayList<Customer>();
			customerList = adminDao.customerDisplay();
			for (Customer customer : customerList) {
				customer.display();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void adminSubMain(Admin admin, Scanner sc) throws SQLException {
		AdminDao adminDao = new AdminDao();
		int ch;
		while ((ch = adminSubMenu(sc, admin)) != 0) {
			switch (ch) {
			case 1:
				addPizza(sc, adminDao);
				System.out.println("Added successfully");
				break;
			case 2:
				updatePizza(sc, adminDao);
				System.out.println("Price updated successfully");
				break;
			case 3:
				deletePizza(sc, adminDao);
				System.out.println("Menu deleted successfully");
				break;
			case 4:
				display(adminDao);
				break;
			case 5:
				calculateProfit(adminDao);
				break;
			case 6:
				adminDao.orderDisplay();
				orderDisplay(adminDao);
				break;
			case 7:
				adminDao.customerDisplay();
				customerDisplay(adminDao);
				break;
			default:
				System.out.println("Wrong choice..... :(");
				break;
			}
		}
	}

}
