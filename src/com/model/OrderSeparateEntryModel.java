package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OrderSeparateEntryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int saparateId;
	private int invoiceNo;
	private String clothType;
	private String serviceType;
	private String preferanceType;
	private String orderDate;
	private String orderId;
	private String Status="Getting";
	private String deleveredDate="";
	private int shopId;
	
	@OneToOne
	@JoinColumn(name="cId")
	CustomerDetailModel customerDetailModel;
	
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name="id") CustomerOrderModel customerOrderModel;
	 */

	public int getSaparateId() {
		return saparateId;
	}

	public void setSaparateId(int saparateId) {
		this.saparateId = saparateId;
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

	public String getDeleveredDate() {
		return deleveredDate;
	}

	public void setDeleveredDate(String deleveredDate) {
		this.deleveredDate = deleveredDate;
	}

	public CustomerDetailModel getCustomerDetailModel() {
		return customerDetailModel;
	}

	public void setCustomerDetailModel(CustomerDetailModel customerDetailModel) {
		this.customerDetailModel = customerDetailModel;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	
}
