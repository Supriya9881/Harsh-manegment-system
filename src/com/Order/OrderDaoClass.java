package com.Order;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.SendSMS.STCOPSMS;
import com.model.CustomerDetailModel;
import com.model.OrderDetails;
import com.model.OrderPaymentModel;
import com.model.ReturnOrderDetails;
import com.model.ReturnOrderEntryModel;
import com.model.master.PreferanceModel;
import com.model.OrderSeparateEntryModel;

@Repository("OrderDaoInterface")
public class OrderDaoClass implements OrderDaoInterface {

	@Autowired
	SessionFactory sessionFactory;
	Session session;
	String pattern = "dd-MM-yyyy";
	String dateInString = new SimpleDateFormat(pattern).format(new Date());

	@Override
	public List<String> SearchCustomerContactDetails(String keyword) { // and s.cStatus='Active'
		List<String> result = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("select distinct(s.cMobile) from CustomerDetailModel s where s.cMobile like :keyword ");
		query.setString("keyword", "%" + keyword + "%");
		System.out.println("query: " + query);
		result = query.list();
		System.out.println("result: " + result);
		session.close();
		return result;
	}

	@Override
	public List<String> searchCustomerContact(String customerContact, int ShopNameId) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from CustomerDetailModel c where c.cMobile='"
				+ customerContact + "' and c.cStatus='Active' and c.shopId=" + ShopNameId + " ");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		session.close();
		return CustomerList;
	}

	@Override
	public List<String> searchCustomerName(String customerName, int ShopNameId) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from CustomerDetailModel c where c.cName='" + customerName
				+ "' and c.cStatus='Active' and c.shopId=" + ShopNameId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		session.close();
		return CustomerList;
	}

	@Override
	public List<String> getAllOrderDetails(String customerContact) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"select c.cId,o.oId,o.amountPaid,o.actualPaid,o.amountRemaining,o.dueDate,o.invoiceNo,o.orderDate,o.orderStatus,o.totalAmount,o.totalQuantity,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.orderId from orderdetails o,customerdetailmodel c where o.cId=c.cId and c.cMobile='"
						+ customerContact + "' and o.oStatus='New'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		session.close();
		return CustomerList;
	}

	@Override
	public List<String> getAllOrderDetailsByName(String customerName) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"select c.cId,o.oId,o.amountPaid,o.actualPaid,o.amountRemaining,o.dueDate,o.invoiceNo,o.orderDate,o.orderStatus,o.totalAmount,o.totalQuantity,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.orderId from orderdetails o,customerdetailmodel c where o.cId=c.cId and c.cName='"
						+ customerName + "' and o.oStatus='New'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		session.close();
		return CustomerList;
	}

	@Override
	public List<String> getClothListForReturn(int invoiceNo, String orderId, String customerId) {
		List<String> ClothTypeList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("select distinct(clothType) from CustomerOrderModel c where c.invoiceNo=" + invoiceNo
						+ " and c.orderId='" + orderId + "' and c.cId=" + Integer.parseInt(customerId) + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		ClothTypeList = query.list();
		session.close();
		return ClothTypeList;
	}

	@Override
	public List<String> getServiceTypeList(String orderId, String clothType, int cId) {
		List<String> ServiceTypeList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("select distinct(serviceType),orderDate from CustomerOrderModel c where c.orderId='"
						+ orderId + "' and c.clothType='" + clothType + "' and c.cId=" + cId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		ServiceTypeList = query.list();
		session.close();
		return ServiceTypeList;
	}

	@Override
	public List<String> getQuantityForReturn(String orderId, String clothTypeID, String serviceTypeID) {
		System.out.println("inside get Cloth Type json dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("SELECT distinct(quantity),id FROM CustomerOrderModel c where c.orderId='" + orderId
						+ "' and c.clothType='" + clothTypeID + "' and c.serviceType='" + serviceTypeID
						+ "' and c.Status='GOT'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public void saveReturnOrderEntry(ReturnOrderEntryModel returnOrderEntryModel) {
		System.out.println("Inside Save Return Order Entry Dao...");
		session = sessionFactory.openSession();
		session.save(returnOrderEntryModel);
		session.beginTransaction().commit();
		session.close();
		System.out.println("Return Order Entry Saved...");

		updateQuantityInCustomerOrderModel(returnOrderEntryModel);
	}

	void updateQuantityInCustomerOrderModel(ReturnOrderEntryModel returnOrderEntryModel) {
		System.out.println("Inside Update Customer Order's Quantity Dao");
		int newQty = (returnOrderEntryModel.getQuantity() - returnOrderEntryModel.getCompleteQty());
		System.out.println("newQty " + newQty);
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update CustomerOrderModel c set c.quantity=" + newQty
				+ " where c.clothType='" + returnOrderEntryModel.getClothTypeID() + "' and c.serviceType='"
				+ returnOrderEntryModel.getServiceTypeID() + "' and c.orderId='" + returnOrderEntryModel.getOrderId()
				+ "' and c.cId=" + returnOrderEntryModel.getCustomerDetailModel().getcId() + "");
		query.executeUpdate();
		session.close();
		System.out.println("Customer Order's Quantity Updated...");
	}

	@Override
	public void saveReturnOrder(ReturnOrderDetails returnOrderDetails) {
		System.out.println("Inside Save Return Order Entry Dao...");
		session = sessionFactory.openSession();
		session.save(returnOrderDetails);
		session.beginTransaction().commit();
		session.close();
		System.out.println("Return Order Entry Saved...");

		updateQuantityInOrderDetails(returnOrderDetails);
	}

	void updateQuantityInOrderDetails(ReturnOrderDetails returnOrderDetails) {
		System.out.println("Inside Update Customer Order's Quantity Dao");
		int newQty = (int) (returnOrderDetails.getTotalQuantity() - returnOrderDetails.getTotalCompleteQty());
		System.out.println("newQty " + newQty);
		session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("update OrderDetails o set o.totalQuantity=" + newQty + " where o.orderId='"
						+ returnOrderDetails.getOrderId() + "' and o.invoiceNo=" + returnOrderDetails.getInvoiceNo()
						+ " and o.cId=" + returnOrderDetails.getCustomerDetailModel().getcId() + "");
		query.executeUpdate();
		session.close();
		System.out.println("Customer Order's Quantity Updated...");
	}

	@Override
	public void updateBalance(double balanceAmt, double paidAmt, int cId) {
		System.out.println("Inside Update Customer Balance Amount");
		double newBalance = (balanceAmt - paidAmt);
		System.out.println("newBalance " + newBalance);
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"update CustomerDetailModel c set c.cAmount=" + newBalance + " where c.cId=" + cId + "");
		query.executeUpdate();
		session.close();
		System.out.println("Customer Balance Updated...");
	}

	@Override
	public List<String> searchCustomerByInvoiceNo(int invoiceNo, int ShopNameId) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"select c.cId,c.cName,c.cMobile,c.cAddress,c.cAmount,c.gender,c.cStatus,o.invoiceNo from customerdetailmodel c,orderdetails o where c.cId=o.cId and o.invoiceNo="
						+ invoiceNo + " and c.cStatus='Active' and c.shopId=" + ShopNameId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		session.close();
		return CustomerList;
	}

	@Override
	public List<String> getOrderDetailsByInvoiceNo(int invoiceNo) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("SELECT * FROM orderdetails o where o.invoiceNo=" + invoiceNo + " and o.oStatus='New'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		session.close();
		return CustomerList;
	}

	@Override
	public List<String> searchCustomerByOrderId(String orderId, int ShopNameId) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"select c.cName,c.cMobile,c.cAddress,c.cAmount,c.gender,c.cStatus,o.invoiceNo,o.orderId from customerdetailmodel c,orderdetails o where c.cId=o.cId and o.orderId='"
						+ orderId + "' and c.cStatus='Active' and c.shopId=" + ShopNameId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		session.close();
		return CustomerList;
	}

	@Override
	public List<String> getOrderDetailsByOrderId(String orderId) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("SELECT * FROM orderdetails o where o.orderId='" + orderId + "' and o.oStatus='New'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		session.close();
		return CustomerList;
	}

	@Override
	public List<String> getCustomerInfo(int cId) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("select * from CustomerDetailModel c where c.cId=" + cId + " and c.cStatus='Active'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		session.close();
		return CustomerList;
	}

	@Override
	public List<String> getAllOrderDetails(int invoiceNo, String orderId) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from orderdetails o where o.orderId='" + orderId
				+ "' and o.invoiceNo=" + invoiceNo + " and o.oStatus='New'");
		// SQLQuery query = session.createSQLQuery("select
		// c.cId,o.oId,o.amountPaid,o.amountRemaining,o.dueDate,o.invoiceNo,o.orderDate,o.orderStatus,o.totalAmount,o.totalQuantity,o.orderId,o.ReadyQty
		// from orderdetails o,customerdetailmodel c where o.cId=c.cId and
		// o.orderId='"+orderId+"' and o.invoiceNo="+invoiceNo+"");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		session.close();
		return CustomerList;
	}

	@Override
	public List<String> getOrderChiAllDetail(String orderId, int invoiceNo) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from OrderSeparateEntryModel o where o.orderId='" + orderId
				+ "' and o.invoiceNo=" + invoiceNo + " "); // order by o.saparateId desc
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		System.out.println(CustomerList);
		session.close();
		return CustomerList;
	}

	@Override
	public void updateClothReadyStatus(String[] selectedClothOrderIds, int invoiceNo, String orderId) {
		System.out.println("Inside updateClothReadyStatus");
		// OrderDetails orderDetails = new OrderDetails();
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(OrderDetails.class);
		Criterion cr1 = Restrictions.eq("orderId", orderId);
		criteria.add(cr1);
		OrderDetails orderDetails = (OrderDetails) criteria.setMaxResults(1).uniqueResult();
		System.out.println("");
		session.close();

		SQLQuery query = null;
		if (selectedClothOrderIds != null && selectedClothOrderIds.length > 0) {
			for (int i = 0; i < selectedClothOrderIds.length; i++) {
				session = sessionFactory.openSession();
				query = session
						.createSQLQuery("update OrderSeparateEntryModel o set o.Status='Ready' where o.saparateId="
								+ selectedClothOrderIds[i] + " and o.invoiceNo=" + invoiceNo + "");
				query.executeUpdate();
				session.close();
				System.out.println("Cloth order of Id " + selectedClothOrderIds[i] + " Updated as Ready...");
			}

			int readyQty = selectedClothOrderIds.length;
			session = sessionFactory.openSession();
			String str = "UPDATE orderdetails od1 inner join orderdetails od2 on od1.orderId=od2.orderId SET od1.NotReadyQty=(od2.NotReadyQty - "
					+ readyQty + "),od1.ReadyQty=(od2.ReadyQty +" + readyQty + ") where od1.orderId='" + orderId
					+ "' and od2.invoiceNo=" + invoiceNo + "";
			query = session.createSQLQuery(str);
			/*
			 * "update orderdetails od set od.NotReadyQty=((select o.NotReadyQty from orderdetails o where o.invoiceNo="
			 * + invoiceNo + " and o.orderId='" + orderId + "' and o.oStatus='New') - " +
			 * readyQty +
			 * "), od.ReadyQty=((select o.ReadyQty from orderdetails o where o.invoiceNo=" +
			 * invoiceNo + " and o.orderId='" + orderId + "' and o.oStatus='New') + " +
			 * readyQty + ") where od.invoiceNo=" + invoiceNo + " and od.orderId='" +
			 * orderId + "'"
			 */
			query.executeUpdate();
			session.close();
			System.out.println("Ready Qty is Updated...");
			// SendSmsForReadyOrder(selectedClothOrderIds, orderDetails);
		}

		session = sessionFactory.openSession();
		List<String> list = new ArrayList<String>();
		session = sessionFactory.openSession();
		query = session
				.createSQLQuery("select * from orderdetails o where o.totalQuantity = o.ReadyQty and o.invoiceNo="
						+ invoiceNo + " and o.orderId='" + orderId + "'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		if (list.isEmpty()) {
			System.out.println("List is Empty => " + list);
			System.out.println("Its Not Same");
			SQLQuery query1 = session
					.createSQLQuery("update CustomerOrderModel o set o.Status='Incomplete' where o.invoiceNo="
							+ invoiceNo + " and o.orderId='" + orderId + "'");
			query1.executeUpdate();
			System.out.println("Order Status Is Updated as Incomplete...");
			SQLQuery query2 = session
					.createSQLQuery("update orderdetails o set o.orderStatus='Incomplete' where o.invoiceNo="
							+ invoiceNo + " and o.orderId='" + orderId + "'");
			query2.executeUpdate();
			System.out.println("Order Status Is Updated as Incomplete...");
		} else {
			System.out.println("List is => " + list);
			System.out.println("Its Same");
			SQLQuery query1 = session
					.createSQLQuery("update CustomerOrderModel o set o.Status='Ready' where o.invoiceNo=" + invoiceNo
							+ " and o.orderId='" + orderId + "'");
			query1.executeUpdate();
			System.out.println("Order Status Is Updated as Incomplete...");
			SQLQuery query2 = session
					.createSQLQuery("update orderdetails o set o.orderStatus='Ready' where o.invoiceNo=" + invoiceNo
							+ " and o.orderId='" + orderId + "'");
			query2.executeUpdate();
			System.out.println("Order Status Is Updated as Ready...");
		}
		session.close();
	}

	public void SendSmsForReadyOrder(String[] selectedClothOrderIds, OrderDetails orderDetails) {
		String mobile = "";
		String msg = "";
		int readyQty = selectedClothOrderIds.length;
		int readyNow = (orderDetails.getReadyQty() + readyQty) - orderDetails.getDeliveredQty();
		try {
			System.out.println("Sending Order Ready SMS");
			mobile = orderDetails.getCustomerDetailModel().getcMobile();
			msg = "Dear " + orderDetails.getCustomerDetailModel().getcName() + "%0AYour Order of Invoice No :"
					+ orderDetails.getInvoiceNo() + ", " + readyNow + " out of " + orderDetails.getTotalQuantity()
					+ " cloths are Ready to Deliver,%0ASo you can collect your cloths.";

			System.out.println("mobile : " + mobile + " msg " + msg);
			STCOPSMS stcopsms = new STCOPSMS();
			stcopsms.sendSMS(mobile, msg);
			System.out.println("msg send success..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateClothDeliveredStatus(String[] selectedClothOrderIds, int invoiceNo, String orderId,
			double balanceAmt, double paidAmt, int cId) {
		System.out.println("Inside updateClothDeliveredStatus");
		SQLQuery query = null;
		try {
			String pattern = "dd-MM-yyyy";
			String dateInString = new SimpleDateFormat(pattern).format(new Date());
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery("update OrderSeparateEntryModel o set o.Status='Delivered', o.deleveredDate='"
							+ dateInString + "' where o.Status='Ready' and o.invoiceNo=" + invoiceNo
							+ " and o.orderId='" + orderId + "'");
			int r = query.executeUpdate();
			session.close();
			System.out.println("Cloth order Updated as Delivered...");

			// int readyQty = selectedClothOrderIds.length;
			session = sessionFactory.openSession();
			String str = "UPDATE orderdetails od1 inner join orderdetails od2 on od1.orderId=od2.orderId SET od1.DeliveredQty=(od2.DeliveredQty + "
					+ r + ") where od1.orderId='" + orderId + "' and od2.invoiceNo=" + invoiceNo + "";
			query = session.createSQLQuery(str);
			/*
			 * "update orderdetails od set od.DeliveredQty=((select o.DeliveredQty from orderdetails o where o.invoiceNo="
			 * + invoiceNo + " and o.orderId='" + orderId + "' and o.oStatus='New') + " + r
			 * + ") where od.invoiceNo=" + invoiceNo + " and od.orderId='" + orderId + "'"
			 */
			query.executeUpdate();
			session.close();
			System.out.println("Delivered Qty is Updated...");

			session = sessionFactory.openSession();
			List<String> list = new ArrayList<String>();
			session = sessionFactory.openSession();
			query = session.createSQLQuery(
					"select * from orderdetails o where o.totalQuantity = o.DeliveredQty and o.invoiceNo=" + invoiceNo
							+ " and o.orderId='" + orderId + "'");
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = query.list();
			if (list.isEmpty()) {
				System.out.println("List is Empty => " + list);
				System.out.println("Its Not Same");
				SQLQuery query1 = session
						.createSQLQuery("update CustomerOrderModel o set o.Status='Incomplete' where o.invoiceNo="
								+ invoiceNo + " and o.orderId='" + orderId + "'");
				query1.executeUpdate();
				System.out.println("Order Status Is Updated as Incomplete...");
				SQLQuery query2 = session
						.createSQLQuery("update orderdetails o set o.orderStatus='Incomplete' where o.invoiceNo="
								+ invoiceNo + " and o.orderId='" + orderId + "'");
				query2.executeUpdate();
				System.out.println("Order Status Is Updated as Incomplete...");
			} else {
				System.out.println("List is => " + list);
				System.out.println("Its Same");
				SQLQuery query1 = session
						.createSQLQuery("update CustomerOrderModel o set o.Status='Delivered' where o.invoiceNo="
								+ invoiceNo + " and o.orderId='" + orderId + "'");
				query1.executeUpdate();
				System.out.println("Order Status Is Updated as Incomplete...");
				SQLQuery query2 = session
						.createSQLQuery("update orderdetails o set o.orderStatus='Delivered' where o.invoiceNo="
								+ invoiceNo + " and o.orderId='" + orderId + "'");
				query2.executeUpdate();
				System.out.println("Order Status Is Updated as Ready...");
			}
			session.close();

			Criteria criteria = null;
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(OrderDetails.class);
			Criterion cr1 = Restrictions.eq("orderId", orderId);
			criteria.add(cr1);
			OrderDetails orderDetails = (OrderDetails) criteria.setMaxResults(1).uniqueResult();
			System.out.println("orderDetails => " + orderDetails);
			session.close();

			updateBalanceNew(paidAmt, cId, orderDetails);
			SendSmsForDeliverOrder(orderDetails, r, paidAmt);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/// ===============================================================

	public void updateBalanceNew(double paidAmt, int cId, OrderDetails orderDetails) {
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(CustomerDetailModel.class);
		Criterion cr1 = Restrictions.eq("cId", cId);
		criteria.add(cr1);
		CustomerDetailModel customerDetailModel = (CustomerDetailModel) criteria.setMaxResults(1).uniqueResult();
		System.out.println("CustomerDetailModel => " + customerDetailModel);
		session.close();

		System.out.println("Inside Update Customer Balance Amount");
		SQLQuery query = null;
		double extra = 0;
		if (paidAmt > customerDetailModel.getcAmount()) {
			extra = paidAmt - customerDetailModel.getcAmount();
		}
		if (paidAmt > orderDetails.getAmountRemaining()) {

			// extra = paidAmt - orderDetails.getAmountRemaining();
			System.out.println("Extra Amt " + extra + " & customerDetailModel.getcAmount()- paidAmt "
					+ (customerDetailModel.getcAmount() - paidAmt));
			if ((customerDetailModel.getcAmount() - paidAmt) < 0) {
				System.out.println("For Balance Zero...");
				session = sessionFactory.openSession();
				String str = "Update CustomerDetailModel c1 inner join CustomerDetailModel c2 on c1.cId=c2.cId SET c1.cAmount=0, c1.cWallet=(c2.cWallet + "
						+ extra + ") where c1.cId=" + cId + "";
				query = session.createSQLQuery(str);
				query.executeUpdate();
				session.close();
			} else {
				session = sessionFactory.openSession();
				String str = "Update CustomerDetailModel c1 inner join CustomerDetailModel c2 on c1.cId=c2.cId SET c1.cAmount=(c2.cAmount - "
						+ paidAmt + ") where c1.cId=" + cId + ""; // , c1.cWallet=(c2.cWallet + "+extra+")
				query = session.createSQLQuery(str);
				query.executeUpdate();
				session.close();
			}
			System.out.println("Customer Balance Updated...");
		} else {
			session = sessionFactory.openSession();
			String str = "Update CustomerDetailModel c1 inner join CustomerDetailModel c2 on c1.cId=c2.cId SET c1.cAmount=(c2.cAmount - "
					+ paidAmt + ") where c1.cId=" + cId + "";
			query = session.createSQLQuery(str);
			query.executeUpdate();
			session.close();
			System.out.println("Customer Balance Updated...");
		}

		double r, p;
		if (paidAmt <= orderDetails.getAmountRemaining()) {
			r = orderDetails.getAmountRemaining() - paidAmt;
			System.out.println("r " + r);
		} else {
			r = 0;
			System.out.println("r " + r);
		}

		if (paidAmt <= orderDetails.getAmountPaid()) {
			p = orderDetails.getAmountPaid() + paidAmt;
			System.out.println("p " + p);
		} else {
			if (paidAmt <= orderDetails.getAmountRemaining()) {
				p = paidAmt + orderDetails.getAmountPaid();
				System.out.println("2nd if p = " + p);
			} else {
				p = orderDetails.getTotalAmount();
				System.out.println("p " + p);
			}
		}
		/*
		 * session = sessionFactory.openSession(); query = session.createSQLQuery(
		 * "update OrderDetails o set o.amountRemaining=" + r + ",o.amountPaid=" + p +
		 * " where o.orderId='" + orderDetails.getOrderId() + "' and o.invoiceNo=" +
		 * orderDetails.getInvoiceNo() + ""); query.executeUpdate(); session.close();
		 * 
		 * session = sessionFactory.openSession(); List<String> list = new
		 * ArrayList<String>(); session = sessionFactory.openSession(); query = session
		 * .createSQLQuery("select * from OrderDetails o where o.amountPaid > o.totalAmount and o.invoiceNo="
		 * + orderDetails.getInvoiceNo() + " and o.orderId='" +
		 * orderDetails.getOrderId() + "'");
		 * query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); list =
		 * query.list(); if (list.isEmpty()) {
		 * System.out.println("List is Empty Its Okk"); } else {
		 * System.out.println("List is Not Empty i.e. Paid > totalAmt=> " + list); query
		 * = session.createSQLQuery(
		 * "update OrderDetails o set o.amountPaid=(select od.totalAmount from OrderDetails od where od.orderId='"
		 * + orderDetails.getOrderId() + "' and od.invoiceNo=" +
		 * orderDetails.getInvoiceNo() + ") where o.orderId='" +
		 * orderDetails.getOrderId() + "' and o.invoiceNo=" +
		 * orderDetails.getInvoiceNo() + ""); query.executeUpdate(); } session.close();
		 */
		LocalDate d = java.time.LocalDate.now();
		String dd = d.toString();
		OrderDetails orderDetails2 = new OrderDetails();
		orderDetails2 = findOrderDetailsId(orderDetails.getoId());
		OrderPaymentModel orderPaymentModel = new OrderPaymentModel();
		orderPaymentModel.setOrderDetails(orderDetails2);
		orderPaymentModel.setTotalAmount(orderDetails.getTotalAmount());
		orderPaymentModel.setAmountRemaining(r);
		orderPaymentModel.setAmountPaid(p);
		orderPaymentModel.setNowPaid(paidAmt);
		orderPaymentModel.setPaymentDate(dd);

		session = sessionFactory.openSession();
		session.save(orderPaymentModel);
		session.beginTransaction().commit();
		session.close();
	}

	//////////////////////////////////////////
	public OrderDetails findOrderDetailsId(int oId) {
		System.out.println("Inside findOrderDetailsID Dao...");
		OrderDetails od = new OrderDetails();
		Session session = sessionFactory.openSession();
		od = (OrderDetails) session.get(OrderDetails.class, oId);
		session.close();
		return od;
	}

	///////////////////////////////////////
	public void SendSmsForDeliverOrder(OrderDetails orderDetails, int r, double paidAmt) {
		String mobile = "";
		String msg = "";
		String msg1 = "";
		int deliverQty = orderDetails.getDeliveredQty() + r;
		int deliverNow = r;
		double paid = orderDetails.getAmountPaid() + paidAmt;
		double amt = orderDetails.getCustomerDetailModel().getcAmount() - paidAmt;
		String gender_name = " "+orderDetails.getCustomerDetailModel().getGender() + " "
				+ orderDetails.getCustomerDetailModel().getcName();
		try {
			System.out.println("Sending Order Deliver SMS");
			mobile = orderDetails.getCustomerDetailModel().getcMobile();
			/*msg = "Perclean Laundry.%0AHi " + gender_name + " Your In. No " + orderDetails.getInvoiceNo()
					+ " " + deliverNow + " out of " + orderDetails.getTotalQuantity() + " is Delivered Now,%0ATot Qty:"
					+ orderDetails.getTotalQuantity() + " %0ATot Delivered Qty:" + orderDetails.getDeliveredQty()
					+ " %0AAmt Paid:" + paid + " out of " + orderDetails.getTotalAmount()
					+ "%0ADo visite us again%0AThank You"; */
			
			msg1 = "Perclean Laundry.%0AHi" + gender_name +" Your In.No "+ orderDetails.getInvoiceNo() +" "+ deliverNow + " Cloths are Delivered,Total Quantity "+ orderDetails.getTotalQuantity() +" Amount Paid "+ paid +"  and Balance is "+orderDetails.getCustomerDetailModel().getcAmount()+".Do Visit Again.%0AThank You.";
			
			System.out.println("mobile : " + mobile + " msg " + msg1);
			STCOPSMS stcopsms = new STCOPSMS();
			stcopsms.sendSMS(mobile, msg1);
			System.out.println("msg send success..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Mobile No autocomplete
	@Override
	public List<String> searchCustomerMobileNo(String keyword) {
		List<String> result = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(
				"select distinct(c.cMobile) from CustomerDetailModel c where c.cMobile like :keyword  and c.cStatus='Active' ");
		query.setString("keyword", "%" + keyword + "%");
		System.out.println("query: " + query);
		result = query.list();
		System.out.println("result: " + result);
		session.close();
		return result;
	}

	@Override
	public List<String> searchCustomerContactNumberAutoComplete(String keyword, int shopId) {
		List<String> result = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(
				"select distinct(c.cMobile) from CustomerDetailModel c where c.cMobile like :keyword  and c.cStatus='Active' and shopId="
						+ shopId + " ");
		query.setString("keyword", "%" + keyword + "%");
		System.out.println("query: " + query);
		result = query.list();
		System.out.println("result: " + result);
		session.close();
		return result;
	}

	@Override
	public List<String> searchCustomerContactNameAutoComplete(String keyword, int shopId) {
		List<String> result = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(
				"select distinct(c.cName) from CustomerDetailModel c where c.cName like :keyword  and c.cStatus='Active' and shopId="
						+ shopId + "");
		query.setString("keyword", "%" + keyword + "%");
		System.out.println("query: " + query);
		result = query.list();
		System.out.println("result: " + result);
		session.close();
		return result;
	}

	@Override
	public void payRemAmt(int cId, Double nowPaidAmt, String orderId) {
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(OrderDetails.class);
		Criterion cr1 = Restrictions.eq("orderId", orderId);
		criteria.add(cr1);
		OrderDetails orderDetails = (OrderDetails) criteria.setMaxResults(1).uniqueResult();
		System.out.println("orderDetails => " + orderDetails);
		session.close();
		updateBalanceNew(nowPaidAmt, cId, orderDetails);
		System.out.println("Updated From All orders");
	}

	@Override
	public List<String> getTodaysDeliveryDetails(int shopId) {
		System.out.println("inside get Cloth Type json dao");

		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT c.cId,c.cName,c.cMobile,c.cAmount,o.orderDate,o.orderId,o.dueDate,o.invoiceNo,o.totalQuantity,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.totalAmount,o.amountPaid,o.actualPaid,o.amountRemaining,o.orderStatus FROM orderdetails o,customerdetailmodel c where c.cId=o.cId and o.dueDate='"
						+ dateInString + "' and o.oStatus='New' and o.shopId=" + shopId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public List<String> getTodaysOrdersDetails(int shopId) {
		System.out.println("inside get Cloth Type json dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT c.cId,c.cName,c.cMobile,c.cAmount,o.orderDate,o.orderId,o.dueDate,o.invoiceNo,o.totalQuantity,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.totalAmount,o.amountPaid,o.actualPaid,o.amountRemaining,o.orderStatus FROM orderdetails o,customerdetailmodel c where c.cId=o.cId and o.orderDate='"
						+ dateInString + "' and o.oStatus='New' and o.shopId=" + shopId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public List<String> getPaidPendingOrdersData(int shopId) {
		System.out.println("inside get Cloth Type json dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT c.cId,c.cName,c.cMobile,c.cAmount,o.orderDate,o.orderId,o.dueDate,o.invoiceNo,o.totalQuantity,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.totalAmount,o.amountPaid,o.actualPaid,o.amountRemaining,o.orderStatus FROM orderdetails o,customerdetailmodel c where c.cId=o.cId and o.amountRemaining=0 and o.shopId="
						+ shopId + " and o.oStatus='New'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public List<String> getPendingOrdersData(int shopId) {
		System.out.println("inside get Cloth Type json dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT c.cId,c.cName,c.cMobile,c.cAmount,o.orderDate,o.orderId,o.dueDate,o.invoiceNo,o.totalQuantity,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.totalAmount,o.amountPaid,o.actualPaid,o.amountRemaining,o.orderStatus FROM orderdetails o,customerdetailmodel c where c.cId=o.cId and amountRemaining !=0 and o.shopId="
						+ shopId + " and o.oStatus='New'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public List<Map> getDataForMsgOfGetOrder(String orderId, int shopNameId) {
		System.out.println("inside get Cloth Type json dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT c.clothType,c.serviceType,c.quantity FROM customerordermodel c where c.orderId='" + orderId
						+ "' and c.shopId=" + shopNameId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public List<String> getNotReadyOrdersList(int shopNameId) {
		System.out.println("inside getNotReadyOrdersList dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT c.cId,c.cName,c.cMobile,c.cAmount,o.orderDate,o.orderId,o.dueDate,o.invoiceNo,o.totalQuantity,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.totalAmount,o.amountPaid,o.actualPaid,o.amountRemaining,o.orderStatus FROM orderdetails o,customerdetailmodel c where c.cId=o.cId and o.orderStatus='Not Ready' and o.shopId="
						+ shopNameId + " and o.oStatus='New'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public List<String> getReadyOrdersList(int shopNameId) {
		System.out.println("inside getReadyOrdersList dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT c.cId,c.cName,c.cMobile,c.cAmount,o.orderDate,o.orderId,o.dueDate,o.invoiceNo,o.totalQuantity,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.totalAmount,o.amountPaid,o.actualPaid,o.amountRemaining,o.orderStatus FROM orderdetails o,customerdetailmodel c where c.cId=o.cId and o.orderStatus='Ready' and o.shopId="
						+ shopNameId + " and o.oStatus='New'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public List<String> getIncompleteOrdersList(int shopNameId) {
		System.out.println("inside getIncompleteOrdersList dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT c.cId,c.cName,c.cMobile,c.cAmount,o.orderDate,o.orderId,o.dueDate,o.invoiceNo,o.totalQuantity,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.totalAmount,o.amountPaid,o.actualPaid,o.amountRemaining,o.orderStatus FROM orderdetails o,customerdetailmodel c where c.cId=o.cId and o.orderStatus='Incomplete' and o.shopId="
						+ shopNameId + " and o.oStatus='New'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public List<String> getDeliveredOrdersList(int shopNameId) {
		System.out.println("inside getDeliveredOrdersList dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT c.cId,c.cName,c.cMobile,c.cAmount,o.orderDate,o.orderId,o.dueDate,o.invoiceNo,o.totalQuantity,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.totalAmount,o.amountPaid,o.actualPaid,o.amountRemaining,o.orderStatus FROM orderdetails o,customerdetailmodel c where c.cId=o.cId and o.orderStatus='Delivered' and o.shopId="
						+ shopNameId + " and o.oStatus='New'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public List<Map> getOrdersList(int cId, int ShopNameId) {
		System.out.println("inside getDeliveredOrdersList dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"select o.oId,o.cId,o.totalAmount,o.amountPaid,o.amountRemaining from orderdetails o where o.cId=" + cId
						+ " and o.amountRemaining!=0 and o.shopId=" + ShopNameId
						+ " and o.oStatus='New' order by oId desc");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public void updateOrdersAmount(String cId, int oId, Double rem, Double paid, int ShopNameId) {
		Session session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("update orderdetails o1 set o1.amountRemaining=" + rem + ",o1.amountPaid=" + paid
						+ " where o1.cId=" + cId + " and o1.oId=" + oId + " and o1.shopId=" + ShopNameId + " ");
		query.executeUpdate();
		session.close();
		System.out.println("Order New Updated...");
	}

	@Override
	public List<String> getOrderChiDetailNew(String orderId, int invoiceNo) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT c.clothType,c.serviceType,c.quantity,c.rate,c.amount FROM customerordermodel c where c.orderId='"
						+ orderId + "' and c.invoiceNo=" + invoiceNo + " "); // order by o.saparateId desc
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		System.out.println(CustomerList);
		session.close();
		return CustomerList;
	}

	@Override
	public List<String> getInvoiceNoNew(int shopNameId) {
		System.out.println("Inside Get Invoice No");
		List<String> List = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select max(o.invoiceNo) inv from OrderDetails o where o.shopId="
				+ shopNameId + " order by o.oId desc");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List = query.list();
		session.close();
		System.out.println("List " + List);
		return List;
	}

	@Override
	public List<String> getTodaysCollectionDetails(int shopId) {
		System.out.println("inside get Cloth Type json dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT c.cId,c.cName,c.cMobile,c.cAmount,o.orderDate,o.orderId,o.dueDate,o.invoiceNo,o.totalQuantity,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.totalAmount,o.amountPaid,o.actualPaid,o.amountRemaining,o.orderStatus FROM orderdetails o,customerdetailmodel c where c.cId=o.cId and o.orderDate='"
						+ dateInString + "' and o.oStatus='New' and o.shopId=" + shopId + " and o.amountPaid > 0");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}
}
