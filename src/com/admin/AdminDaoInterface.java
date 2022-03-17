package com.admin;

import java.util.HashMap;
import java.util.List;

import com.model.master.ClothTypeModel;
import com.model.master.OwnerDetailsModel;
import com.model.master.PreferanceModel;
import com.model.master.RateMasterModel;
import com.model.master.ServiceTypeModel;

public interface AdminDaoInterface {
	void updateReamainingPayment(String cMobile, double remAmt, String orderId, double lastPaid, double lastRem);
	HashMap<String, String> getAllDetailsList(int shopId);
	String updateReamainingPayment(int cId, double payingAmount, double paidAmt);
	OwnerDetailsModel checkOwnerLogin(String userName, String password);
}
