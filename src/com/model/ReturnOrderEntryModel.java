package com.model;

import javax.persistence.*;

@Entity
public class ReturnOrderEntryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int entryId;
	private String ClothTypeID;
	private String ServiceTypeID;
	private int Quantity;
	private int CompleteQty;
	private String orderDate;
	private String orderId;
	private int invoiceNo;
	
	@OneToOne
	@JoinColumn(name="cId")
	CustomerDetailModel customerDetailModel;

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public int getCompleteQty() {
		return CompleteQty;
	}

	public void setCompleteQty(int completeQty) {
		CompleteQty = completeQty;
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

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public CustomerDetailModel getCustomerDetailModel() {
		return customerDetailModel;
	}

	public void setCustomerDetailModel(CustomerDetailModel customerDetailModel) {
		this.customerDetailModel = customerDetailModel;
	}

	public String getClothTypeID() {
		return ClothTypeID;
	}

	public void setClothTypeID(String clothTypeID) {
		ClothTypeID = clothTypeID;
	}

	public String getServiceTypeID() {
		return ServiceTypeID;
	}

	public void setServiceTypeID(String serviceTypeID) {
		ServiceTypeID = serviceTypeID;
	}
	
	
}
