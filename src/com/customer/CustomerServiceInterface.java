package com.customer;

import java.util.List;

import com.model.CustomerDetailModel;
import com.model.CustomerOrderModel;
import com.model.CustomerOrderModel_2;
import com.model.OrderDetails;

public interface CustomerServiceInterface {

	List<String> getRateDetails(String serviceTypeID, String clothTypeID);

	void saveCustomerDetails(CustomerDetailModel customerDetailModel);

	List<String> searchCustomerContact(String keyword); //AutoComplete

	List<String> getCustomerContactDetails(String customerContact, int shopNameId); //jSON
	
	List<String> getCustomerContactAllDetails(String customerContact); //jSON

	void deleteCustomerDetails(int customerId, int shopNameId);

	CustomerDetailModel findCustomerID(String cId,int shopId);

	void saveCustomerOrder(CustomerOrderModel customerOrderModel);
	
	void saveCustomerOrder2(CustomerOrderModel_2 customerOrderModel_2);

	void saveOrder(OrderDetails orderDetails);

	List<String> getRateFromCustomerOrder(String customerContact, String serviceTypeID, String clothTypeID);

	void deleteOrder(int cId, String orderId, String clothTypeID, String serviceTypeID, int shopNameId);
	
//======= Return Order ============
	List<String> getClothTypeDetails(String orderId);
	List<String> getServiceTypeList(String orderId,String clothTypeID);
	List<String> getQuantity(String orderId, String clothTypeID, String serviceTypeID);
	
	void updateReturnOrder(int oId, int retrunQuatity);

	List<String> getRemainingAmount(String orderId);

	void updatePayment(String orderId, double amountPaid, double amountRemaining, int qrQuantity,double allRemainingAmt);

	List<String> getAllOrdersList();

	void updateOrderStatus(String orderId);

	void updateAllReamaingAmount(int cId, double qAmountRemainingAll, double NewWallet, int shopNameId);

	List<String> getCustomerList(int shopNameId);

	CustomerDetailModel checkDuplicateCustomer(String getcMobile, int shopNameId);

	void updateCustomerDetails(int customerId, String customerMobile, String customerName, String customerAddress,
			int shopNameId);
}
