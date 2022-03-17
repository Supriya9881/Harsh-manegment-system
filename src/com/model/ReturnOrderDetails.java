package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ReturnOrderDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int returnOrderId;
	private String orderId;
	private String ReturnOrderDate;
	private int invoiceNo;
	private int TotalQuantity;
	private int totalCompleteQty;
	
	@OneToOne
	@JoinColumn(name="cId")
	CustomerDetailModel customerDetailModel;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public CustomerDetailModel getCustomerDetailModel() {
		return customerDetailModel;
	}

	public void setCustomerDetailModel(CustomerDetailModel customerDetailModel) {
		this.customerDetailModel = customerDetailModel;
	}

	public int getReturnOrderId() {
		return returnOrderId;
	}

	public void setReturnOrderId(int returnOrderId) {
		this.returnOrderId = returnOrderId;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getReturnOrderDate() {
		return ReturnOrderDate;
	}

	public void setReturnOrderDate(String returnOrderDate) {
		ReturnOrderDate = returnOrderDate;
	}

	public int getTotalQuantity() {
		return TotalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		TotalQuantity = totalQuantity;
	}

	public int getTotalCompleteQty() {
		return totalCompleteQty;
	}

	public void setTotalCompleteQty(int totalCompleteQty) {
		this.totalCompleteQty = totalCompleteQty;
	}
	
	
}
