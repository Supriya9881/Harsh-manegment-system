package com.Developer;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.master.OwnerDetailsModel;
import com.model.master.ShopDetailsModel;

@Repository("DeveloperDaoInterface")
public class DeveloperDaoClass implements DeveloperDaoInterface {

	@Autowired
	SessionFactory sessionFactory;
	Session session;
	
	@Override
	public List<String> getShopNameList() {
		List<String> ShopList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from ShopDetailsModel s");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		ShopList = query.list();
		session.close();
		return ShopList;
	}

	@Override
	public ShopDetailsModel findShopNameID(int shopId) {
		System.out.println("Inside find Shop Name Id");
		ShopDetailsModel shopDetailsModel = new ShopDetailsModel();
		session = sessionFactory.openSession();
		shopDetailsModel = (ShopDetailsModel) session.get(ShopDetailsModel.class, shopId);
		session.close();
		return shopDetailsModel;
	}

	@Override
	public void saveShopDetails(ShopDetailsModel shopDetailsModel) {
		System.out.println("Inside Save Shop Details DAO");
		session = sessionFactory.openSession();
		session.save(shopDetailsModel);
		session.beginTransaction().commit();
		session.close();
		System.out.println("Shop Datails Saved..");
	}

	@Override
	public void saveOwnerDetails(OwnerDetailsModel ownerDetailsModel) {
		System.out.println("Inside Save Owners Details DAO");
		session = sessionFactory.openSession();
		session.save(ownerDetailsModel);
		session.beginTransaction().commit();
		session.close();
		System.out.println("Owner Datails Saved..");
	}

	@Override
	public List<String> getOwnerNameList() {
		List<String> ownerList = new ArrayList<String>();
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from OwnerDetailsModel o");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		ownerList = query.list();
		session.close();
		return ownerList;
	}
}
