package com.model.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class RateMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rateId;
	private double rate;
	private int flag = 1;
	private int shopId;

	@OneToOne
	@JoinColumn(name = "clothTypeID")
	private ClothTypeModel clothTypeModel;

	@OneToOne
	@JoinColumn(name = "serviceTypeID")
	private ServiceTypeModel serviceTypeModel;

	public int getRateId() {
		return rateId;
	}

	public void setRateId(int rateId) {
		this.rateId = rateId;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public ClothTypeModel getClothTypeModel() {
		return clothTypeModel;
	}

	public void setClothTypeModel(ClothTypeModel clothTypeModel) {
		this.clothTypeModel = clothTypeModel;
	}

	public ServiceTypeModel getServiceTypeModel() {
		return serviceTypeModel;
	}

	public void setServiceTypeModel(ServiceTypeModel serviceTypeModel) {
		this.serviceTypeModel = serviceTypeModel;
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
