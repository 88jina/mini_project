package com.pay;

public class PayDTO {
	String car_num;
	int discount;
	long sum;
	long received;
	long given;
	public String getCar_num() {
		return car_num;
	}
	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public long getSum() {
		return sum;
	}
	public void setSum(long sum) {
		this.sum = sum;
	}
	public long getReceived() {
		return received;
	}
	public void setReceived(long received) {
		this.received = received;
	}
	public long getGiven() {
		return given;
	}
	public void setGiven(long given) {
		this.given = given;
	}
}
