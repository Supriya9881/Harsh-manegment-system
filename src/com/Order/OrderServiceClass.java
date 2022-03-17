package com.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.ReturnOrderDetails;
import com.model.ReturnOrderEntryModel;

@Service("OrderServiceInterface")
@Transactional(propagation = Propagation.SUPPORTS)
public class OrderServiceClass implements OrderServiceInterface {

	@Autowired
	OrderDaoInterface orderDaoInterface;

	@Override
	public List<String> SearchCustomerContactDetails(String customerContact) {
		return orderDaoInterface.SearchCustomerContactDetails(customerContact);
	}

	@Override
	public List<String> getCustomerInfo(String customerContact, int ShopNameId) {
		String str = customerContact;
		List<String> result = new ArrayList<String>();
		int l = str.length();
		Boolean flag = Character.isDigit(str.charAt(0));
		if (flag) {
			if (str.length() >= 9) {
				System.out.println(str + " is a mobile Number length is " + l);
				result = orderDaoInterface.searchCustomerContact(customerContact, ShopNameId);
			} else {
				System.out.println("'" + str.charAt(0) + "' is a number");
				System.out.println("Its Invoice No");
				int invoiceNo = Integer.parseInt(customerContact);
				result = orderDaoInterface.searchCustomerByInvoiceNo(invoiceNo, ShopNameId);
			}
		} else {
			Boolean flag2 = Character.isDigit(str.charAt(2));
			if (flag2) {
				System.out.println("'" + str.charAt(2) + "' is a number");
				System.out.println("Its Order Id");
				result = orderDaoInterface.searchCustomerByOrderId(customerContact, ShopNameId);
			} else {
				System.out.println("'" + str.charAt(0) + "' is a letter");
				System.out.println("Its Customer Name");
				result = orderDaoInterface.searchCustomerName(customerContact, ShopNameId);
			}
		}
		return result;
	}

	@Override
	public List<String> getAllOrderDetails(String customerContact) {
		String str = customerContact;
		List<String> result = new ArrayList<String>();
		int l = str.length();
		Boolean flag = Character.isDigit(str.charAt(0));
		if (flag) {
			if (str.length() >= 9) {
				System.out.println(str + " is a mobile Number length is " + l);
				result = orderDaoInterface.getAllOrderDetails(customerContact);
			} else {
				System.out.println("'" + str.charAt(0) + "' is a number");
				System.out.println("Its Invoice No");
				int invoiceNo = Integer.parseInt(customerContact);
				result = orderDaoInterface.getOrderDetailsByInvoiceNo(invoiceNo);
			}
		} else {
			Boolean flag2 = Character.isDigit(str.charAt(2));
			if (flag2) {
				System.out.println("'" + str.charAt(2) + "' is a number");
				System.out.println("Its Order Id");
				result = orderDaoInterface.getOrderDetailsByOrderId(customerContact);
			} else {
				System.out.println("'" + str.charAt(0) + "' is a letter");
				System.out.println("Its Customer Name");
				result = orderDaoInterface.getAllOrderDetailsByName(customerContact);
			}
		}
		/*
		 * SELECT * FROM pearlclean.orderdetails o where o.invoiceNo=10; for (int i = 0;
		 * i < str.length(); i++) { //Boolean flag = Character.isDigit(str.charAt(i));
		 * if (flag) { System.out.println("'" + str.charAt(i) + "' is a number"); result
		 * = orderDaoInterface.getAllOrderDetails( customerContact); } else {
		 * System.out.println("'"+ str.charAt(i)+"' is a letter"); result =
		 * orderDaoInterface.getAllOrderDetailsByName( customerContact); } }
		 */
		return result;
	}

	@Override
	public List<String> getClothListForReturn(int invoiceNo, String orderId, String customerId) {
		return orderDaoInterface.getClothListForReturn(invoiceNo, orderId, customerId);
	}

	@Override
	public List<String> getServiceTypeList(String orderId, String clothType, int cId) {
		return orderDaoInterface.getServiceTypeList(orderId, clothType, cId);
	}

	@Override
	public List<String> getQuantityForReturn(String orderId, String clothTypeID, String serviceTypeID) {
		return orderDaoInterface.getQuantityForReturn(orderId, clothTypeID, serviceTypeID);
	}

	@Override
	public void saveReturnOrderEntry(ReturnOrderEntryModel returnOrderEntryModel) {
		orderDaoInterface.saveReturnOrderEntry(returnOrderEntryModel);
	}

	@Override
	public void saveReturnOrder(ReturnOrderDetails returnOrderDetails) {
		orderDaoInterface.saveReturnOrder(returnOrderDetails);
	}

	@Override
	public void updateBalance(double balanceAmt, double paidAmt, String customerId) {
		int cId = Integer.parseInt(customerId);
		orderDaoInterface.updateBalance(balanceAmt, paidAmt, cId);
	}

	@Override
	public List<String> getCustomerInfo(int cId) {
		return orderDaoInterface.getCustomerInfo(cId);
	}

	@Override
	public List<String> getAllOrderDetails(int invoiceNo, String orderId) {
		return orderDaoInterface.getAllOrderDetails(invoiceNo, orderId);
	}

	@Override
	public List<String> getOrderChiAllDetail(String orderId, int invoiceNo) {
		return orderDaoInterface.getOrderChiAllDetail(orderId, invoiceNo);
	}

	@Override
	public void updateClothReadyStatus(String[] selectedClothOrderIds, int invoiceNo, String orderId) {
		orderDaoInterface.updateClothReadyStatus(selectedClothOrderIds, invoiceNo, orderId);
	}

	@Override
	public void updateClothDeliveredStatus(String[] selectedClothOrderIds, int invoiceNo, String orderId,
			double balanceAmt, double paidAmt, int cId) {
		orderDaoInterface.updateClothDeliveredStatus(selectedClothOrderIds, invoiceNo, orderId, balanceAmt, paidAmt,
				cId);
	}

	@Override
	public List<String> searchCustomerMobileNo(String keyword) {
		return orderDaoInterface.searchCustomerMobileNo(keyword);
	}

	@Override
	public List<String> searchCustomerContactAutoComplete(String parameter, int shopId) {
		String str = parameter;
		List<String> result = new ArrayList<String>();
		Boolean flag = Character.isDigit(str.charAt(0));
		if (flag) {
			System.out.println("Search for number");
			result = orderDaoInterface.searchCustomerContactNumberAutoComplete(parameter, shopId);
		} else {
			System.out.println("Search for Name");
			result = orderDaoInterface.searchCustomerContactNameAutoComplete(parameter, shopId);
		}
		return result;
	}

	@Override
	public void payRemAmt(int cId, Double nowPaidAmt, String orderId) {
		orderDaoInterface.payRemAmt(cId, nowPaidAmt, orderId);
	}

	@Override
	public List<String> getTodaysDeliveryDetails(int shopId) {
		return orderDaoInterface.getTodaysDeliveryDetails(shopId);
	}

	@Override
	public List<String> getTodaysOrdersDetails(int shopId) {
		return orderDaoInterface.getTodaysOrdersDetails(shopId);
	}

	@Override
	public List<String> getPaidPendingOrdersData(int shopId) {
		return orderDaoInterface.getPaidPendingOrdersData(shopId);
	}

	@Override
	public List<String> getPendingOrdersData(int shopId) {
		return orderDaoInterface.getPendingOrdersData(shopId);
	}

	@Override
	public List<Map> getDataForMsgOfGetOrder(String orderId, int shopNameId) {
		return orderDaoInterface.getDataForMsgOfGetOrder(orderId, shopNameId);
	}

	@Override
	public List<String> getNotReadyOrdersList(int shopNameId) {
		return orderDaoInterface.getNotReadyOrdersList(shopNameId);
	}

	@Override
	public List<String> getReadyOrdersList(int shopNameId) {
		return orderDaoInterface.getReadyOrdersList(shopNameId);
	}

	@Override
	public List<String> getIncompleteOrdersList(int shopNameId) {
		return orderDaoInterface.getIncompleteOrdersList(shopNameId);
	}

	@Override
	public List<String> getDeliveredOrdersList(int shopNameId) {
		return orderDaoInterface.getDeliveredOrdersList(shopNameId);
	}

	@Override
	public List<Map> getOrdersList(int cId, int ShopNameId) {
		return orderDaoInterface.getOrdersList(cId, ShopNameId);
	}

	@Override
	public void updateOrdersAmount(String cId, int oId, Double rem, Double paid, int ShopNameId) {
		orderDaoInterface.updateOrdersAmount(cId, oId, rem, paid, ShopNameId);
	}

	@Override
	public List<String> getOrderChiDetailNew(String orderId, int invoiceNo) {
		return orderDaoInterface.getOrderChiDetailNew(orderId, invoiceNo);
	}

	@Override
	public List<String> getInvoiceNo(int shopNameId) {
		return orderDaoInterface.getInvoiceNoNew(shopNameId);
	}

	@Override
	public List<String> getTodaysCollectionDetails(int shopNameId) {
		return orderDaoInterface.getTodaysCollectionDetails(shopNameId);
	}
}
