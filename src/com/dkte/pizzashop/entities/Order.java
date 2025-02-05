package com.dkte.pizzashop.entities;

public class Order {
	private int oid;
	private int mid;
	private int cid;

	public Order() {

	}

	public Order(int oid, int mid, int cid) {
		this.oid = oid;
		this.mid = mid;
		this.cid = cid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", mid=" + mid + ", cid=" + cid + "]";
	}

}
