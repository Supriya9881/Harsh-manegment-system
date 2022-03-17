package com.model.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ClothTypeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clothTypeID;
	private String clothType;
	private int flag = 1;
	private int shopId;
	
	
	public int getClothTypeID() {
		return clothTypeID;
	}
	public void setClothTypeID(int clothTypeID) {
		this.clothTypeID = clothTypeID;
	}
	public String getClothType() {
		return clothType;
	}
	public void setClothType(String clothType) {
		this.clothType = clothType;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	
}
