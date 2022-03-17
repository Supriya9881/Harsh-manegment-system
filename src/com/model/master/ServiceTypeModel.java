package com.model.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ServiceTypeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceTypeID;
	private String serviceTypeName;
	private String serviceType;
	private int flag = 1;
	private int shopId;
	
	
	public int getServiceTypeID() {
		return serviceTypeID;
	}
	public void setServiceTypeID(int serviceTypeID) {
		this.serviceTypeID = serviceTypeID;
	}
	public String getServiceTypeName() {
		return serviceTypeName;
	}
	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	
	
}
