package com.admin;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.master.OwnerDetailsModel;

@Service("AdminServiceInterface")
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminServiceInterface {

	@Autowired
	AdminDaoInterface adminDaoInterface;

	@Override
	public void updateReamainingPayment(String cMobile, double remAmt, String orderId, double lastPaid,
			double lastRem) {
		adminDaoInterface.updateReamainingPayment(cMobile,remAmt,orderId,lastPaid,lastRem);
	}

	@Override
	public HashMap<String, String> getAllDetailsList(int shopId) {
		return adminDaoInterface.getAllDetailsList(shopId);
	}

	@Override
	public String updateReamainingPayment(int cId, double payingAmount, double paidAmt) {
		String s = adminDaoInterface.updateReamainingPayment( cId, payingAmount , paidAmt);
		return s;
	}

	@Override
	public OwnerDetailsModel checkOwnerLogin(String userName, String password) {
		return adminDaoInterface.checkOwnerLogin( userName, password);
	}
}
