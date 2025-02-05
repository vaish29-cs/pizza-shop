package com.dkte.pizzashop.entities;

import java.util.Scanner;

public class Pizza {
	private int mid;
	private String name;
	private String description;
	private double price;
	private String category;

	public Pizza() {
		// TODO Auto-generated constructor stub
	}

	public Pizza(int mid, String name, String description, double price, String category) {
		this.mid = mid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void display() {
		System.out.printf("|%-3d|%-20s|%-75s|%-3f|%-15s|%n", getMid(), getName(), getDescription(), getPrice(),
				getCategory());
	}

	public void accept(Scanner sc) {
		sc.nextLine();
		System.out.println("Enter name of menu");
		name = sc.nextLine();
		System.out.println("Enter description of menu");
		description = sc.nextLine();
		System.out.println("Enter price of menu");
		price = sc.nextDouble();
		sc.nextLine();
		System.out.println("Enter category of menu");
		category = sc.nextLine();
	}

}
