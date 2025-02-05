package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.dkte.pizzashop.dao.AdminDao;
import com.dkte.pizzashop.dao.CustomerDao;
import com.dkte.pizzashop.entities.Admin;
import com.dkte.pizzashop.entities.Customer;

public class MainMenu {

	public static void registerCustomer(Scanner sc) {
		Customer customer = new Customer();
		customer.accept(sc);
		try (CustomerDao customerDao = new CustomerDao()) {
			customerDao.insertCustomer(customer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Customer loginCustomer(Scanner sc) {
		try (CustomerDao customerDao = new CustomerDao()) {
			System.out.println("Enter Email -");
			String email = sc.next();
			System.out.println("Enter password -");
			String password = sc.next();
			Customer customer = customerDao.getCustomer(email, password);
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Admin adminAccess(Scanner sc) {
		try {
			AdminDao adminDao = new AdminDao();
			Admin admin = new Admin();
			System.out.println("Enter your name");
			String name = sc.next();
			System.out.println("Enter Password");
			String password = sc.next();
			admin = adminDao.accesInfo(name, password);
			return admin;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int menu(Scanner sc) {
		System.out.println("****Welcome to pizza shop****");
		System.out.println("0.Exit");
		System.out.println("1.Login");
		System.out.println("2.Register");
		System.out.println("3.Admin");
		System.out.println("Enter your choice - ");
		int choice = sc.nextInt();
		System.out.println("*****************************");
		return choice;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice;
		while ((choice = menu(sc)) != 0) {
			switch (choice) {
			case 1:
				Customer customer = loginCustomer(sc);
				if (customer != null)
					SubMenu.submain(sc, customer);
				else
					System.out.println("Invalid Credentials...:(");
				break;
			case 2:
				registerCustomer(sc);
				System.out.println("Registered Successfully");
				break;
			case 3:
				try {
					Admin admin = adminAccess(sc);
					if (admin != null)
						SubMenu.adminSubMain(admin, sc);
					else
						System.out.println("Invalid Credentials...:(");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;

			default:
				System.out.println("Wrong choice....:(");
				break;
			}
		}
		System.out.println("Thank you for visiting our application");

	}

}
