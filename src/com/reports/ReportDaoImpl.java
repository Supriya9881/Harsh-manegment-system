package com.reports;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.OrderDetails;
import com.model.master.ServiceTypeModel;

@Repository("reportDaoInterface")
public class ReportDaoImpl implements ReportDaoInterface {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<String> searchOrderId(String keyword) {
		List<String> result = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("select distinct(o.orderId) from CustomerOrderModel o where o.orderId like :keyword ");
		query.setString("keyword", "%" + keyword + "%");
		System.out.println("query: " + query);
		result = query.list();
		System.out.println("result: " + result);
		session.close();
		return result;
	}

	@Override
	public List<String> searchOrderDate(String keyword, String mobileNo) {
		System.out.println("INSIDE Dao serch orderDate " + keyword + " mobileNo " + mobileNo);
		List<String> result = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("select distinct(o.orderDate) from CustomerOrderModel o where o.orderDate like :keyword ");
		/*
		 * Query query = session.
		 * createQuery("select distinct(c.orderDate) from CustomerOrderModel c inner join CustomerDetailModel cd on cd.cId=d.cId where c.orderDate like :keyword and cd.cMobile='"
		 * +mobileNo+"'");
		 */
		query.setString("keyword", "%" + keyword + "%");
		System.out.println("query: " + query);
		result = query.list();
		System.out.println("result: " + result);
		session.close();
		return result;
	}

	@Override
	public List<String> getAllOrderList(int shopNameId) {
		List<String> OrdersList = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"select c.cId,c.cName,c.cWallet,o.oId,o.amountPaid,o.amountRemaining,o.dueDate,o.invoiceNo,o.orderDate,o.orderStatus,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.totalAmount,o.actualPaid,o.totalQuantity,o.orderId from orderdetails o,customerdetailmodel c where o.cId=c.cId and o.shopId="
						+ shopNameId + " and o.oStatus!='Deleted' order by c.cName");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		OrdersList = query.list();
		session.close();
		return OrdersList;
	}

	@Override
	public void deleteThisOrder(String orderId, int invoiceNo, int shopId) {
		System.out.println("inside findServiceTypeiD dao");
		Criteria criteria = null;
		OrderDetails orderDetails = new OrderDetails();
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(OrderDetails.class).add(Restrictions.eq("orderId", orderId))
				.add(Restrictions.eq("shopId", shopId));
		orderDetails = (OrderDetails) criteria.setMaxResults(1).uniqueResult();
		System.out.println("inside findServiceTypeiD dao end ==> " + orderDetails.getAmountPaid());
		session.close();
		SQLQuery query = null;
		session = sessionFactory.openSession();
		query = session.createSQLQuery("UPDATE OrderSeparateEntryModel o set o.Status='Deleted' where o.orderId='"
				+ orderId + "' and o.invoiceNo=" + invoiceNo + " and o.shopId=" + shopId + " ");
		query.executeUpdate();

		query = session.createSQLQuery("UPDATE CustomerOrderModel o set o.Status='Deleted' where o.orderId='" + orderId
				+ "' and o.invoiceNo=" + invoiceNo + " and o.shopId=" + shopId + " ");
		query.executeUpdate();

		query = session.createSQLQuery("UPDATE OrderDetails o set o.oStatus='Deleted' where o.orderId='" + orderId
				+ "' and o.invoiceNo=" + invoiceNo + " and o.shopId=" + shopId + " ");
		query.executeUpdate();

		session.close();
		/*
		 * Double paidAmt = orderDetails.getAmountPaid(); if (paidAmt > 0.0) { int cId =
		 * orderDetails.getCustomerDetailModel().getcId(); Double wallet =
		 * orderDetails.getCustomerDetailModel().getcWallet(); Double balance =
		 * orderDetails.getCustomerDetailModel().getcAmount(); if (wallet > 0) { session
		 * = sessionFactory.openSession(); sqlQuery = session.createSQLQuery("");
		 * sqlQuery.executeUpdate(); session.close(); } else { if (balance > 0) {
		 * session = sessionFactory.openSession(); sqlQuery =
		 * session.createSQLQuery(""); sqlQuery.executeUpdate(); session.close(); } } }
		 */
	}

	@Override
	public List<String> getFromToDateList(String fromDate, String toDate, int shopNameId) {
		List<String> OrdersList = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"select c.cId,c.cName,c.cWallet,o.oId,o.amountPaid,o.amountRemaining,o.dueDate,o.invoiceNo,o.orderDate,o.orderStatus,o.NotReadyQty,o.ReadyQty,o.DeliveredQty,o.totalAmount,o.actualPaid,o.totalQuantity,o.orderId from orderdetails o,customerdetailmodel c where o.cId=c.cId and o.shopId="
						+ shopNameId + " and o.oStatus!='Deleted' and o.orderDate between '" + fromDate + "' and '"
						+ toDate + "' order by c.cName");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		OrdersList = query.list();
		session.close();
		return OrdersList;
	}
}
