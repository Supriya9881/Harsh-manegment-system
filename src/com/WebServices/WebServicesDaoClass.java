package com.WebServices;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.CustomerDetailModel;

@Repository("WebServicesDaoInterface")
public class WebServicesDaoClass implements WebServicesDaoInterface {

	@Autowired
	SessionFactory sessionFactory;
	Session session;

	@Override
	public List<String> GetCustomerDetailsByJSON() {
		List<String> CustomerList = new ArrayList<String>();
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from CustomerDetailModel c where c.cStatus='Active' order by c.cId desc");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		CustomerList=query.list();
		session.close();
		System.out.println("List is "+CustomerList);
		return CustomerList;
	}

	@Override
	public void SaveCustomerDetailsByJSON(CustomerDetailModel customerDetailModel) {
		System.out.println("Inside Save Customer by JSON, Web Services");
		session = sessionFactory.openSession();
		session.save(customerDetailModel);
		session.beginTransaction().commit();
		session.close();
		System.out.println("Customer Saved...By Web Services...");
	}
}
