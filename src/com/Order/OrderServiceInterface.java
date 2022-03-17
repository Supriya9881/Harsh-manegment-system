package com.Order;

import java.util.List;
import java.util.Map;

import com.model.ReturnOrderDetails;
import com.model.ReturnOrderEntryModel;

public interface OrderServiceInterface {

	List<String> SearchCustomerContactDetails(String customerContact);

	List<String> getCustomerInfo(String customerContact, int shopNameId);

	List<String> getAllOrderDetails(String customerContact);

	List<String> getClothListForReturn(int invoiceNo, String orderId, String customerId);

	List<String> getServiceTypeList(String orderId, String clothType, int cId);

	List<String> getQuantityForReturn(String orderId, String clothTypeID, String serviceTypeID);

	void saveReturnOrderEntry(ReturnOrderEntryModel returnOrderEntryModel);

	void saveReturnOrder(ReturnOrderDetails returnOrderDetails);

	void updateBalance(double balanceAmt, double paidAmt, String cId);

	List<String> getCustomerInfo(int cId);

	List<String> getAllOrderDetails(int invoiceNo, String orderId);

	List<String> getOrderChiAllDetail(String orderId, int invoiceNo);

	void updateClothReadyStatus(String[] selectedClothOrderIds, int invoiceNo, String orderId);

	void updateClothDeliveredStatus(String[] selectedClothOrderIds, int invoiceNo, String orderId, double balanceAmt,
			double paidAmt, int cId);
	
	public List<String> searchCustomerMobileNo(String keyword);

	public List<String> searchCustomerContactAutoComplete(String parameter, int shopNameId);

	void payRemAmt(int cId, Double nowPaidAmt, String orderId);

	List<String> getTodaysDeliveryDetails(int shopNameId);

	List<String> getTodaysOrdersDetails(int shopNameId);

	List<String> getPaidPendingOrdersData(int shopNameId);

	List<String> getPendingOrdersData(int shopNameId);

	List<Map> getDataForMsgOfGetOrder(String orderId, int i);

	
	List<String> getNotReadyOrdersList(int shopNameId);

	List<String> getReadyOrdersList(int shopNameId);

	List<String> getIncompleteOrdersList(int shopNameId);

	List<String> getDeliveredOrdersList(int shopNameId);

	List<Map> getOrdersList(int getcId, int shopNameId);

	void updateOrdersAmount(String cId, int oId, Double rem, Double paid, int shopNameId);

	List<String> getOrderChiDetailNew(String orderId, int invoiceNo);

	List<String> getInvoiceNo(int shopNameId);

	List<String> getTodaysCollectionDetails(int shopNameId);

}
