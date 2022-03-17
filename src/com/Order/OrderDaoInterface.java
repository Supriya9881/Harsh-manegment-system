package com.Order;

import java.util.List;
import java.util.Map;

import com.model.ReturnOrderDetails;
import com.model.ReturnOrderEntryModel;

public interface OrderDaoInterface {

	List<String> SearchCustomerContactDetails(String customerContact);

	List<String> searchCustomerContact(String customerContact, int shopNameId);

	List<String> searchCustomerName(String customerContact, int shopNameId);

	List<String> getAllOrderDetails(String customerContact);

	List<String> getAllOrderDetailsByName(String customerContact);

	List<String> getClothListForReturn(int invoiceNo, String orderId, String customerId);

	List<String> getServiceTypeList(String orderId, String clothType, int cId);

	List<String> getQuantityForReturn(String orderId, String clothTypeID, String serviceTypeID);

	void saveReturnOrderEntry(ReturnOrderEntryModel returnOrderEntryModel);

	void saveReturnOrder(ReturnOrderDetails returnOrderDetails);

	void updateBalance(double balanceAmt, double paidAmt, int cId);

	List<String> searchCustomerByInvoiceNo(int invoiceNo, int shopNameId);

	List<String> getOrderDetailsByInvoiceNo(int invoiceNo);

	List<String> searchCustomerByOrderId(String orderId, int shopNameId);

	List<String> getOrderDetailsByOrderId(String orderId);

	List<String> getCustomerInfo(int cId);

	List<String> getAllOrderDetails(int invoiceNo, String orderId);

	List<String> getOrderChiAllDetail(String orderId, int invoiceNo);

	void updateClothReadyStatus(String[] selectedClothOrderIds, int invoiceNo, String orderId);

	void updateClothDeliveredStatus(String[] selectedClothOrderIds, int invoiceNo, String orderId, double balanceAmt,
			double paidAmt, int cId);

	public List<String> searchCustomerMobileNo(String keyword);

	public List<String> searchCustomerContactNumberAutoComplete(String parameter, int shopId);

	public List<String> searchCustomerContactNameAutoComplete(String parameter, int shopId);

	void payRemAmt(int cId, Double nowPaidAmt, String orderId);

	List<String> getTodaysDeliveryDetails(int shopId);

	List<String> getTodaysOrdersDetails(int shopId);

	List<String> getPaidPendingOrdersData(int shopId);

	List<String> getPendingOrdersData(int shopId);

	List<Map> getDataForMsgOfGetOrder(String orderId, int shopNameId);

	List<String> getNotReadyOrdersList(int shopNameId);

	List<String> getReadyOrdersList(int shopNameId);

	List<String> getIncompleteOrdersList(int shopNameId);

	List<String> getDeliveredOrdersList(int shopNameId);

	List<Map> getOrdersList(int cId, int shopNameId);

	void updateOrdersAmount(String cId, int oId, Double rem, Double paid, int shopNameId);

	List<String> getOrderChiDetailNew(String orderId, int invoiceNo);

	List<String> getInvoiceNoNew(int shopNameId);

	List<String> getTodaysCollectionDetails(int shopNameId);
}
