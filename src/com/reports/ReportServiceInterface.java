package com.reports;

import java.util.List;

public interface ReportServiceInterface {

	List<String> searchOrderId(String keyword);

	List<String> searchOrderDate(String keyword, String mobileNo);

	List<String> getAllOrderList(int shopNameId);

	void deleteThisOrder(String orderId, String invoiceNo, int shopNameId);

	List<String> getFromToDateList(String fromDate, String toDate, int shopNameId);
}
