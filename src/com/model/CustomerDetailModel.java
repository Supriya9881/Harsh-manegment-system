package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.master.ShopDetailsModel;

@Entity
@XmlRootElement
public class CustomerDetailModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cId;
	private String gender;
	private String cName;
	private String cAddress;
	private String cMobile;
	private String cStatus = "Active";
	private double cAmount;
	private double cWallet;
	
	private int shopId;
	private String ShopName;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcAddress() {
		return cAddress;
	}

	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}

	public String getcMobile() {
		return cMobile;
	}

	public void setcMobile(String cMobile) {
		this.cMobile = cMobile;
	}

	public String getcStatus() {
		return cStatus;
	}

	public void setcStatus(String cStatus) {
		this.cStatus = cStatus;
	}

	public double getcAmount() {
		return cAmount;
	}

	public void setcAmount(double cAmount) {
		this.cAmount = cAmount;
	}

	public double getcWallet() {
		return cWallet;
	}

	public void setcWallet(double cWallet) {
		this.cWallet = cWallet;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return ShopName;
	}

	public void setShopName(String shopName) {
		ShopName = shopName;
	}
}
