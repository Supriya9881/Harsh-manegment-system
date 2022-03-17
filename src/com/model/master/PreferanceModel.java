package com.model.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PreferanceModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String preferanceName;
	private double preferancePrice;
	private int flag = 1;
	private int shopId;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPreferanceName() {
		return preferanceName;
	}
	public void setPreferanceName(String preferanceName) {
		this.preferanceName = preferanceName;
	}
	public double getPreferancePrice() {
		return preferancePrice;
	}
	public void setPreferancePrice(double preferancePrice) {
		this.preferancePrice = preferancePrice;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	
}
