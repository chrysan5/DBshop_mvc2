package com.dbshop;

public class OrderDto {
	int orderNum;
	String id;
	int prodnum;
	int quantity;
	String result; //result=1인 경우 '배송 준비중', result=2인 경우 '배송 완료' 이다.
	String orderDate;
	
	public OrderDto() {
		super();
	}
	
	
	public OrderDto(int orderNum, String id, int prodnum, int quantity, String result, String orderDate) {
		this.orderNum = orderNum;
		this.id = id;
		this.prodnum = prodnum;
		this.quantity = quantity;
		this.result = result;
		this.orderDate = orderDate;
	}
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getProdnum() {
		return prodnum;
	}
	public void setProdnum(int prodnum) {
		this.prodnum = prodnum;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	
}
