
package com.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.model.CustomerDetailModel;
import com.model.master.OwnerDetailsModel;

@Repository("AdminDaoInterface")
public class AdminDaoImpl implements AdminDaoInterface {

	@Autowired
	SessionFactory sessionFactory;

	Session session;

	public void updateCustomerReamainingPayment(String cMobile, double remAmt) {
		System.out.println("Inside update Reamaining Payment");
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"update customerdetailmodel c set c.cAmount='" + remAmt + "' where c.cMobile='" + cMobile + "'");
		query.executeUpdate();
		session.close();
		System.out.println("Reamaining Payment Updated...");
	}

	@Override
	public void updateReamainingPayment(String cMobile, double remAmt, String orderId, double lastPaid,
			double lastRem) {
		System.out.println("Inside update Order");
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update orderdetails o set o.amountPaid='" + lastPaid
				+ "', o.amountRemaining='" + lastRem + "' where o.qrOrderId='" + orderId + "'");
		query.executeUpdate();
		session.close();
		System.out.println("Order Updated...& goes to Reamaining Payment Updating...");
		updateCustomerReamainingPayment(cMobile, remAmt);
	}

	@Override
	public HashMap<String, String> getAllDetailsList(int shopId) {
		String pattern = "dd-MM-yyyy";
		String dateInString = new SimpleDateFormat(pattern).format(new Date());

		List<HashMap> list = new ArrayList<HashMap>();
		HashMap mapOfAllDetails = new HashMap();
		Session session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("SELECT count(o.orderId) as Todays_Deliverys FROM orderdetails o WHERE o.dueDate = '"
						+ dateInString + "' and o.shopId=" + shopId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		for (HashMap map : list) {
			mapOfAllDetails.put("Todays_Deliverys", map.get("Todays_Deliverys"));
		}

		query = session
				.createSQLQuery("SELECT count(o.orderId) as Todays_Orders FROM orderdetails o WHERE o.orderDate = '"
						+ dateInString + "' and o.shopId=" + shopId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		for (HashMap map : list) {
			mapOfAllDetails.put("Todays_Orders", map.get("Todays_Orders"));
		}

		query = session.createSQLQuery("SELECT sum(o.totalQuantity) as QTY FROM orderdetails o WHERE o.orderDate = '"
				+ dateInString + "' and o.shopId=" + shopId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		for (HashMap map : list) {
			mapOfAllDetails.put("Todays_Cloths", map.get("QTY"));
		}

		query = session
				.createSQLQuery("SELECT sum(o.totalAmount) as todays_Amount FROM orderdetails o WHERE o.orderDate = '"
						+ dateInString + "' and o.shopId=" + shopId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		for (HashMap map : list) {
			mapOfAllDetails.put("todays_Amount", map.get("todays_Amount"));
		}

		query = session.createSQLQuery(
				"SELECT sum(o.totalAmount) as months_Amount FROM orderdetails o WHERE MONTH(o.oDate) = MONTH(curdate()) AND YEAR(o.oDate) = YEAR(curdate()) and o.shopId="
						+ shopId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		for (HashMap map : list) {
			mapOfAllDetails.put("months_Amount", map.get("months_Amount"));
		}

		query = session
				.createSQLQuery("SELECT sum(o.amountPaid) as todays_collection FROM orderdetails o WHERE o.orderDate='"
						+ dateInString + "' and o.shopId=" + shopId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		for (HashMap map : list) {
			mapOfAllDetails.put("todays_collection", map.get("todays_collection"));
		}

		session.close();
		System.out.println("mapOfAllDetails is => " + mapOfAllDetails);
		return mapOfAllDetails;
	}

	@Override
	public String updateReamainingPayment(int cId, double payingAmount, double paidAmt) {
		double balance;
		double extra;
		if ((payingAmount - paidAmt) < 0) {
			balance = 0;
			extra = paidAmt - payingAmount;
			String str = "update customerdetailmodel c set c.cAmount=0, c.cWallet=" + extra + " where c.cId=" + cId
					+ "";
			justQuery(str);
		} else {
			balance = payingAmount - paidAmt;
			String str = "update customerdetailmodel c set c.cAmount=" + balance + " where c.cId=" + cId + "";
			justQuery(str);
		}

		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(CustomerDetailModel.class);
		Criterion cr1 = Restrictions.eq("cId", cId);
		criteria.add(cr1);
		CustomerDetailModel customerDetailModel = (CustomerDetailModel) criteria.setMaxResults(1).uniqueResult();
		return customerDetailModel.getcName();
	}

	void justQuery(String str) {
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(str);
		query.executeUpdate();
		session.close();
	}

	@Override
	public OwnerDetailsModel checkOwnerLogin(String ownerUserName, String ownerPassword) {
		System.out.println("Inside Check Owner Login Dao");
		Criteria criteria;
		OwnerDetailsModel ownerDetailsModel = new OwnerDetailsModel();
		session = sessionFactory.openSession();
		criteria = session.createCriteria(OwnerDetailsModel.class).add(Restrictions.eq("ownerUserName", ownerUserName));
		criteria = session.createCriteria(OwnerDetailsModel.class).add(Restrictions.eq("ownerPassword", ownerPassword));
		ownerDetailsModel = (OwnerDetailsModel) criteria.setMaxResults(1).uniqueResult();
		session.close();
		return ownerDetailsModel;
	}
}
