 package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CustomerOrderModel_2 {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int invoiceNo;
	private String clothType;
	private String serviceType;
	private String preferanceType;
	private double rate;
	private int quantity;
	private double amount;
	private String orderDate;
	private String orderId;
	private String Status="Getting";
	private String deleveredDate;
	private int shopId;

	@OneToOne
	@JoinColumn(name="cId")
	CustomerDetailModel customerDetailModel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getClothType() {
		return clothType;
	}

	public void setClothType(String clothType) {
		this.clothType = clothType;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getPreferanceType() {
		return preferanceType;
	}

	public void setPreferanceType(String preferanceType) {
		this.preferanceType = preferanceType;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public CustomerDetailModel getCustomerDetailModel() {
		return customerDetailModel;
	}

	public void setCustomerDetailModel(CustomerDetailModel customerDetailModel) {
		this.customerDetailModel = customerDetailModel;
	}

	public String getDeleveredDate() {
		return deleveredDate;
	}

	public void setDeleveredDate(String deleveredDate) {
		this.deleveredDate = deleveredDate;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

}
