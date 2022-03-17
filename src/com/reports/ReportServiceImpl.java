package com.reports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("reportServiceInterface")
@Transactional(propagation = Propagation.SUPPORTS)
public class ReportServiceImpl implements ReportServiceInterface {

	@Autowired
	ReportDaoInterface reportDaoInterface;

	@Override
	public List<String> searchOrderId(String keyword) {
		return reportDaoInterface.searchOrderId(keyword);
	}

	@Override
	public List<String> searchOrderDate(String keyword, String mobileNo) {
		System.out.println("Inside Service implemrnt mobileNo " + mobileNo + " keyword " + keyword);
		return reportDaoInterface.searchOrderDate(keyword, mobileNo);
	}

	@Override
	public List<String> getAllOrderList(int shopNameId) {
		return reportDaoInterface.getAllOrderList(shopNameId);
	}

	@Override
	public void deleteThisOrder(String orderId, String invoiceNo, int shopNameId) {
		reportDaoInterface.deleteThisOrder(orderId, Integer.parseInt(invoiceNo), shopNameId);
	}

	@Override
	public List<String> getFromToDateList(String fromDate, String toDate, int shopNameId) {
		return reportDaoInterface.getFromToDateList( fromDate, toDate, shopNameId);
	}
}
