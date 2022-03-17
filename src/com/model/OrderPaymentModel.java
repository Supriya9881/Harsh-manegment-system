package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OrderPaymentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderPaymentId;
	private double totalAmount;
	private double amountPaid;
	private double nowPaid;
	private double amountRemaining;
	private String paymentDate;
	private int shopId;
	
	@OneToOne
	@JoinColumn(name="oId")
	OrderDetails orderDetails;

	public int getOrderPaymentId() {
		return orderPaymentId;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public void setOrderPaymentId(int orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getAmountRemaining() {
		return amountRemaining;
	}

	public void setAmountRemaining(double amountRemaining) {
		this.amountRemaining = amountRemaining;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	public double getNowPaid() {
		return nowPaid;
	}

	public void setNowPaid(double nowPaid) {
		this.nowPaid = nowPaid;
	}
	
	
}
