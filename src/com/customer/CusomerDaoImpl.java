package com.customer;

import java.util.ArrayList;
import java.util.List;

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

import com.model.CustomerDetailModel;
import com.model.CustomerOrderModel;
import com.model.CustomerOrderModel_2;
import com.model.OrderDetails;
import com.model.OrderPaymentModel;
import com.model.OrderSeparateEntryModel;
import com.model.master.PreferanceModel;
import com.model.master.ServiceTypeModel;

@Repository("customerDaoInterface")
public class CusomerDaoImpl implements CustomerDaoInterface {

	@Autowired
	SessionFactory sessionFactory;
	Session session;

	@Override
	public void saveCustomerDetails(CustomerDetailModel customerDetailModel) {
		System.out.println("Inside save customer Dao..");
		session = sessionFactory.openSession();
		session.save(customerDetailModel);
		session.beginTransaction().commit();
		session.close();
		System.out.println("Customer Saved...");
	}

	@Override
	public void updateCustomerDetails(int customerId, String customerMobile, String customerName,
			String customerAddress) {

	}

	@Override
	public void deleteCustomerDetails(int customerId, int shopNameId) {
		System.out.println("Inside delete Customer Dao");
		session = sessionFactory.openSession();
		// SQLQuery query = session.createSQLQuery("update CustomerDetailModel set
		// cStatus='Deactive' where cId="+customerId+"");
		SQLQuery query = session.createSQLQuery(
				"DELETE from CustomerDetailModel where cId=" + customerId + " and shopId=" + shopNameId + "");
		query.executeUpdate();
		session.close();
		System.out.println("Customer Deleted...");
	}

	@Override
	public List<String> searchCustomerContact(String keyword) {
		List<String> result = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(
				"select distinct(s.cMobile) from CustomerDetailModel s where s.cMobile like :keyword and s.cStatus='Active'");
		query.setString("keyword", "%" + keyword + "%");
		System.out.println("query: " + query);
		result = query.list();
		System.out.println("result: " + result);
		session.close();
		return result;
	}

	@Override
	public List<String> searchCustomerName(String keyword) {
		List<String> result = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(
				"select distinct(s.cName) from CustomerDetailModel s where s.cName like :keyword and s.cStatus='Active'");
		query.setString("keyword", "%" + keyword + "%");
		System.out.println("query: " + query);
		result = query.list();
		System.out.println("result: " + result);
		session.close();
		return result;
	}

	@Override // jSON to get Customer List
	public List<String> getCustomerContactDetails(String customerContact, int shopId) {
		System.out.println("inside get Customer json dao " + customerContact);
		int CustomerId = Integer.parseInt(customerContact);
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("SELECT * FROM CustomerDetailModel r  where r.cId=" + CustomerId
				+ " and r.cStatus='Active' and r.shopId=" + shopId + " ");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public List<String> getCustomerContactAllDetails(String customerContact) {
		String str = customerContact;
		System.out.println("inside get Customer json dao for : " + customerContact);
		Session session = sessionFactory.openSession();
		String str1;
		Boolean flag = Character.isDigit(str.charAt(0));
		if (flag) {
			System.out.println("'" + str.charAt(0) + "' is a number");
			str1 = "select c.cId,c.cName,c.cMobile,c.cAmount,c.cAddress,o.qrorderId,o.qrQuantity,o.qrAmount,o.amountPaid,o.amountRemaining,o.qrDate,o.AllQuantity,o.dueDate from customerdetailmodel c inner join orderdetails o on c.cId=o.cId where c.cMobile='"
					+ customerContact + "' order by oid  desc;";
		} else {
			System.out.println("'" + str.charAt(0) + "' is a letter");
			str1 = "select c.cId,c.cName,c.cMobile,c.cAmount,c.cAddress,o.qrorderId,o.qrQuantity,o.qrAmount,o.amountPaid,o.amountRemaining,o.qrDate,o.AllQuantity,o.dueDate from customerdetailmodel c inner join orderdetails o on c.cId=o.cId where c.cName='"
					+ customerContact + "' order by oid  desc;";
		}
		SQLQuery query = session.createSQLQuery(str1);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public CustomerDetailModel findCustomerID(int cId, int shopId) {
		System.out.println("inside findServiceTypeiD dao");
		Criteria criteria = null;
		CustomerDetailModel customerDetailModel = new CustomerDetailModel();
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(CustomerDetailModel.class).add(Restrictions.eq("cId", cId))
				.add(Restrictions.eq("shopId", shopId));
		customerDetailModel = (CustomerDetailModel) criteria.setMaxResults(1).uniqueResult();
		System.out.println("inside findServiceTypeiD dao end");
		session.close();
		return customerDetailModel;
	}

	@Override
	public void saveCustomerOrder(CustomerOrderModel customerOrderModel) {
		System.out.println("Inside Order Save Dao...");
		session = sessionFactory.openSession();
		session.save(customerOrderModel);
		session.beginTransaction().commit();
		session.close();
		System.out.println("Order Saved...");
		saveOrderSaperateEntry(customerOrderModel);
	}

	public void saveOrderSaperateEntry(CustomerOrderModel customerOrderModel) {
		System.out.println("Inside save saperate Order Entry");
		OrderSeparateEntryModel orderSeparateEntryModel = new OrderSeparateEntryModel();
		CustomerDetailModel customerDetailModel = new CustomerDetailModel();
		customerDetailModel = findCustomerID(customerOrderModel.getCustomerDetailModel().getcId(),
				customerOrderModel.getShopId());
		orderSeparateEntryModel.setClothType(customerOrderModel.getClothType());
		orderSeparateEntryModel.setServiceType(customerOrderModel.getServiceType());
		orderSeparateEntryModel.setInvoiceNo(customerOrderModel.getInvoiceNo());
		orderSeparateEntryModel.setOrderId(customerOrderModel.getOrderId());
		orderSeparateEntryModel.setOrderDate(customerOrderModel.getOrderDate());
		orderSeparateEntryModel.setPreferanceType(customerOrderModel.getPreferanceType());
		orderSeparateEntryModel.setCustomerDetailModel(customerDetailModel);
		orderSeparateEntryModel.setShopId(customerOrderModel.getShopId());
		for (int i = 0; i < customerOrderModel.getQuantity(); i++) {
			session = sessionFactory.openSession();
			session.save(orderSeparateEntryModel);
			session.beginTransaction().commit();
			session.close();
			System.out.println("Entry " + i + " Saved");
		}
	}

	@Override
	public void saveCustomerOrder2(CustomerOrderModel_2 customerOrderModel_2) {
		System.out.println("Inside Order Save Dao...");
		session = sessionFactory.openSession();
		session.save(customerOrderModel_2);
		session.beginTransaction().commit();
		session.close();
		System.out.println("Order Saved In CustomerOrderModel_2...");
	}

	@Override
	public void saveOrder(OrderDetails orderDetails) {
		System.out.println("inside save order details DAO");
		session = sessionFactory.openSession();
		session.save(orderDetails);
		session.beginTransaction().commit();
		session.close();
		System.out.println("Complete Order Saved...");

		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update CustomerOrderModel c set c.Status='GOT' where c.invoiceNo="
				+ orderDetails.getInvoiceNo() + " and c.orderId='" + orderDetails.getOrderId() + "'");
		query.executeUpdate();
		session.close();
		System.out.println("Customer Order Status Updated...");

		session = sessionFactory.openSession();
		query = session.createSQLQuery("update OrderSeparateEntryModel c set c.Status='Not Ready' where c.invoiceNo="
				+ orderDetails.getInvoiceNo() + " and c.orderId='" + orderDetails.getOrderId() + "'");
		query.executeUpdate();
		session.close();
		System.out.println("Customer Order Saparate Order Status Updated...");

		saveOrdersPaymentDetail(orderDetails);
	}

	public void saveOrdersPaymentDetail(OrderDetails orderDetails) {
		OrderDetails orderDetails1 = new OrderDetails();
		orderDetails1 = findOrderDetailsId(orderDetails.getoId());
		OrderPaymentModel orderPaymentModel = new OrderPaymentModel();
		orderPaymentModel.setOrderDetails(orderDetails1);
		orderPaymentModel.setAmountPaid(orderDetails.getAmountPaid());
		orderPaymentModel.setNowPaid(orderDetails.getAmountPaid());
		orderPaymentModel.setAmountRemaining(orderDetails.getAmountRemaining());
		orderPaymentModel.setTotalAmount(orderDetails.getTotalAmount());
		orderPaymentModel.setPaymentDate(orderDetails.getOrderDate());

		session = sessionFactory.openSession();
		session.save(orderPaymentModel);
		session.beginTransaction().commit();
		session.close();
		System.out.println("Order Payment Details Saved...");
	}

	public OrderDetails findOrderDetailsId(int oId) {
		System.out.println("Inside findOrderDetailsID Dao...");
		OrderDetails od = new OrderDetails();
		Session session = sessionFactory.openSession();
		od = (OrderDetails) session.get(OrderDetails.class, oId);
		session.close();
		return od;
	}

	@Override // jSON to get Rate
	public List<String> getRateDetails(String serviceType, String clothType) {
		System.out.println("Inside get Rate json dao -> serviceType " + serviceType + "clothType " + clothType);
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT r.rate FROM ratemastermodel r,clothtypemodel c,servicetypemodel s where r.clothTypeID=c.clothTypeID and r.serviceTypeID=s.serviceTypeID and s.serviceTypeName='"
						+ serviceType + "' and c.clothType='" + clothType + "'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	/*
	 * SELECT r.rate FROM RateMasterModel r where
	 * r.customerType='"	 + customerType + "' and
	 * r.clothTypeID='" + clothTypeID + "'and
	 * r.serviceTypeID='" + serviceTypeID + "'
	 */

	/*
	 * select r.rate from ratemastermodel r inner join clothtypemodel c on
	 * r.clothTypeID=c.clothTypeID inner join servicetypemodel s on
	 * r.serviceTypeID=s.serviceTypeID where c.clothType='Formal' and
	 * s.serviceTypeName='Wash' and r.customerType='Irregular'
	 */

	@Override
	public List<String> getRateFromCustomerOrder(String customerContact, String serviceType, String clothType) {
		System.out.println("inside get Rate json dao ->customerContact " + customerContact + " serviceType "
				+ serviceType + " clothType " + clothType);
		int CustomerId = Integer.parseInt(customerContact);
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"select com.rate from customerordermodel com inner join customerdetailmodel c on com.cId=c.cId where com.clothType='"
						+ clothType + "' and com.serviceType='" + serviceType + "' and c.cId=" + CustomerId
						+ " order by com.id desc");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public void deleteOrder(int cId, String orderId, String clothTypeID, String serviceTypeID, int shopId) {
		System.out.println("Inside delete Customer Order Dao");
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"delete from CustomerOrderModel where cId=" + cId + " and orderId='" + orderId + "' and clothType='"
						+ clothTypeID + "' and serviceType='" + serviceTypeID + "' and shopId=" + shopId + " ");
		query.executeUpdate();

		query = session.createSQLQuery(
				"delete from CustomerOrderModel_2 where cId=" + cId + " and orderId='" + orderId + "' and clothType='"
						+ clothTypeID + "' and serviceType='" + serviceTypeID + "' and shopId=" + shopId + "");
		query.executeUpdate();

		query = session.createSQLQuery("delete from orderseparateentrymodel where cId=" + cId + " and orderId='"
				+ orderId + "' and clothType='" + clothTypeID + "' and serviceType='" + serviceTypeID + "' and shopId="
				+ shopId + "");
		query.executeUpdate();
		session.close();
		System.out.println("Customer Oreder Deleted...");
	}

	@Override
	public List<String> getClothTypeDetails(String orderId) {
		System.out.println("inside get Cloth Type json dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("SELECT distinct(clothType),cId FROM CustomerOrderModel c  where c.orderId='" + orderId
						+ "' and c.Status='Got'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public List<String> getServiceTypeList(String orderId, String clothTypeID) {
		System.out.println("inside get Cloth Type json dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("SELECT distinct(serviceType) FROM CustomerOrderModel c  where c.orderId='" + orderId
						+ "' and c.clothType='" + clothTypeID + "' and c.Status='Got'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public List<String> getQuantity(String orderId, String clothTypeID, String serviceTypeID) {
		System.out.println("inside get Cloth Type json dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("SELECT distinct(quantity),id FROM CustomerOrderModel c  where c.orderId='" + orderId
						+ "' and c.clothType='" + clothTypeID + "' and c.serviceType='" + serviceTypeID
						+ "' and c.Status='Got'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public void updateReturnOrder(int oId, int retrunQuatity) {
		System.out.println("Inside updateReturnOrder Dao");
		session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("update CustomerOrderModel set quantity='" + retrunQuatity + "' where id=" + oId + "");
		query.executeUpdate();
		session.close();
		System.out.println("updateReturnOrder Updated...");
	}

	@Override
	public List<String> getRemainingAmount(String orderId) {
		System.out.println("inside getRemainingAmount json dao");
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT o.qrAmount,o.amountPaid,o.amountRemaining,o.qrOrderId,o.qrQuantity,c.cAmount FROM orderdetails o inner join customerdetailmodel c on o.cId=c.cId where o.qrOrderId='"
						+ orderId + "'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query);
		List list = query.list();
		System.out.println(list.size() + " and List " + list);
		session.close();
		return list;
	}

	@Override
	public void updatePayment(String orderId, double amountPaid, double amountRemaining, int qrQuantity,
			double allRemainingAmt) {
		System.out.println("Inside updatePayment Dao");
		session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("update orderdetails set qrQuantity='" + qrQuantity + "',amountRemaining='"
						+ amountRemaining + "',amountPaid='" + amountPaid + "' where qrOrderId='" + orderId + "' ");
		query.executeUpdate();
		SQLQuery query1 = session
				.createSQLQuery("update customerdetailmodel c inner join orderdetails o on c.cId=o.cId set c.cAmount='"
						+ allRemainingAmt + "' where o.qrOrderId='" + orderId + "' ");
		query1.executeUpdate();
		session.close();
		System.out.println("updatePayment Updated...");

	}

	@Override
	public List<String> getAllOrdersList() {
		List<String> AllOrdersList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"select c.cId,c.cMobile,c.cName,o.oId,o.qrAmount,o.qrDate,o.qrOrderId,o.qrQuantity,o.amountPaid,o.amountRemaining,o.orderStatus from orderdetails o"
						+ " inner join customerdetailmodel c on o.cId=c.cId where c.cStatus='Active'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		AllOrdersList = query.list();
		session.close();
		return AllOrdersList;
	}

	@Override
	public void updateCustomerRemainingAmount(int cId, double remAmt) {
		session = sessionFactory.openSession();
		String str = "update CustomerDetailModel set cAmount=" + remAmt + " where cId='" + cId + "'";
		SQLQuery q = session.createSQLQuery(str);
		q.executeUpdate();
		session.beginTransaction();
		session.close();
		System.out.println("Remaining Amount Updated Successfully");
	}

	@Override
	public void updateOrderStatus(String orderId) {
		session = sessionFactory.openSession();
		String str = "update OrderDetails set orderStatus='PENDING' where qrOrderId='" + orderId + "'";
		SQLQuery q = session.createSQLQuery(str);
		q.executeUpdate();
		session.beginTransaction();
		session.close();
		System.out.println("Order Status Updated Successfully");
	}

	@Override
	public void updateAllReamaingAmount(int cId, double AmountRemainingAll, double NewWallet, int ShopNameId) {
		session = sessionFactory.openSession();
		String str = "update customerdetailmodel c set c.cAmount=" + AmountRemainingAll + ",c.cWallet=" + NewWallet
				+ " where c.cId=" + cId + " and c.shopId=" + ShopNameId + " ";
		SQLQuery q = session.createSQLQuery(str);
		q.executeUpdate();
		session.beginTransaction();
		session.close();
		System.out.println("AmountRemainingAll & Wallet are Updated Successfully...");
	}

	@Override
	public void updateSingleOrderStatus(String OrderId) {
		System.out.println("Inside update Single OrderStatus Dao");
		session = sessionFactory.openSession();
		SQLQuery query1 = session
				.createSQLQuery("update CustomerOrderModel c1 set c1.Status='Got' where c1.orderId='" + OrderId + "'");
		query1.executeUpdate();
		session.close();
		System.out.println("updateSingleOrderStatus 1 Updated...");
	}

	@Override
	public List<String> getCustomerList(int shopNameId) {
		List<String> CustomerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("select * from CustomerDetailModel c where c.cStatus='Active' and c.shopId="
						+ shopNameId + " order by c.cId desc");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList = query.list();
		session.close();
		System.out.println("List is " + CustomerList);
		return CustomerList;
	}

	@Override
	public CustomerDetailModel checkDuplicateCustomer(String cMobile, int shopId) {
		System.out.println("inside customerDetailModel duplicate dao");
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(CustomerDetailModel.class);
		Criterion cr1 = Restrictions.eq("cMobile", cMobile);
		Criterion cr2 = Restrictions.eq("shopId", shopId);
		criteria.add(cr1);
		criteria.add(cr2);
		CustomerDetailModel customerDetailModel = (CustomerDetailModel) criteria.setMaxResults(1).uniqueResult();
		System.out.println("inside ServiceType duplicate dao end");
		session.close();
		return customerDetailModel;
	}

	@Override
	public void updateCustomerDetails(int customerId, String customerMobile, String customerName,
			String customerAddress, int shopNameId) {
		System.out.println("Inside update Customer Dao");
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update CustomerDetailModel set cMobile='" + customerMobile
				+ "',cName='" + customerName + "',cAddress='" + customerAddress + "' where cId=" + customerId
				+ " and shopId=" + shopNameId + "");
		query.executeUpdate();
		session.close();
		System.out.println("Customer Updated...");
	}
}
