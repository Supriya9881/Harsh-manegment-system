package com.reports;

import java.util.List;

public interface ReportDaoInterface {

	List<String> searchOrderId(String keyword);

	List<String> searchOrderDate(String keyword, String mobileNo);

	List<String> getAllOrderList(int shopNameId);

	void deleteThisOrder(String orderId, int parseInt, int shopNameId);

	List<String> getFromToDateList(String fromDate, String toDate, int shopNameId);
}
