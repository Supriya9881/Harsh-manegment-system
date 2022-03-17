package com.Masters;

import java.util.ArrayList;
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

import com.model.master.ClothTypeModel;
import com.model.master.OwnerDetailsModel;
import com.model.master.PreferanceModel;
import com.model.master.RateMasterModel;
import com.model.master.ServiceTypeModel;

@Repository("MasterDaoInterface")
public class MasterDaoClass implements MasterDaoInterface {

	@Autowired
	SessionFactory sessionFactory;
	Session session;
	@Override
	public List<String> getPreferanceList(int shopNameId) {
		List<String> PreferanceList= new ArrayList<String>();
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from PreferanceModel p where p.flag=1 and p.shopId="+shopNameId+"");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		PreferanceList=query.list();
		session.close();
		return PreferanceList;
	}
	@Override
	public void savePreferance(PreferanceModel preferanceModel) {
		session=sessionFactory.openSession();
		session.save(preferanceModel);
		session.beginTransaction().commit();
		session.close();
	}
	@Override
	public void updatePreferanceType(int preferanceId, String preferanceName, double preferancePrice, int shopNameId) {
		System.out.println("Inside updatePreferanceType Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update PreferanceModel p set p.preferanceName='"+preferanceName+"',p.preferancePrice="+preferancePrice+" where p.id="+preferanceId+" and p.shopId="+shopNameId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Preferance Type Updated...");
	}
	@Override
	public void deletePreferanceType(int preferanceId, int shopNameId) {
		System.out.println("Inside deletePreferanceType Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("delete from PreferanceModel p where p.id="+preferanceId+" and p.shopId="+shopNameId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Preferance Type Deleted...");
	}
	@Override
	public PreferanceModel getDuplicatePreferance(String preferanceName, int shopId) {
		System.out.println("inside getDuplicatePreferance duplicate dao");
		Criteria criteria = null;
		PreferanceModel preferanceModel = new PreferanceModel();
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(PreferanceModel.class).add(Restrictions.eq("preferanceName", preferanceName))
																.add(Restrictions.eq("shopId", shopId));
		preferanceModel = (PreferanceModel) criteria.setMaxResults(1).uniqueResult();
		System.out.println("inside ServiceType duplicate dao end");
		session.close();
		return preferanceModel;
	}
	@Override
	public ClothTypeModel getDuplicateClothType(String clothType, int shopId) {
		System.out.println("inside getDuplicateClothType duplicate dao");
		Criteria criteria = null;
		ClothTypeModel clothTypeModel = new ClothTypeModel();
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(ClothTypeModel.class).add(Restrictions.eq("clothType", clothType))
															   .add(Restrictions.eq("shopId", shopId));
		clothTypeModel = (ClothTypeModel) criteria.setMaxResults(1).uniqueResult();
		System.out.println("inside ServiceType duplicate dao end");
		session.close();
		return clothTypeModel;
	}
	@Override
	public ServiceTypeModel getDuplicateServiceType(String serviceTypeName, int shopId) {
		System.out.println("inside getDuplicateServiceType duplicate dao");
		Criteria criteria = null;
		ServiceTypeModel serviceTypeModel = new ServiceTypeModel();
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(ServiceTypeModel.class).add(Restrictions.eq("serviceTypeName", serviceTypeName))
																 .add(Restrictions.eq("shopId", shopId));
		serviceTypeModel = (ServiceTypeModel) criteria.setMaxResults(1).uniqueResult();
		System.out.println("inside ServiceType duplicate dao end");
		session.close();
		return serviceTypeModel;
	}
	
	@Override
	public void saveCloths(ClothTypeModel clothTypeModel) {
		session=sessionFactory.openSession();
		session.save(clothTypeModel);
		session.beginTransaction().commit();
		session.close();
	}
	
	@Override
	public void updateClothType(int clothId, String clothTypeName, int shopNameId) {
		System.out.println("Inside updateClothType Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update ClothTypeModel c set c.clothType='"+clothTypeName+"' where c.clothTypeID="+clothId+" and c.shopId="+shopNameId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Cloth Type Updated...");
	}
	
	@Override
	public void deleteClothType(int clothId, int shopNameId) {
		System.out.println("Inside delete ClothType Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("delete from ClothTypeModel c where clothTypeID="+clothId+" and c.shopId="+shopNameId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Cloth Type Deleted...");
	}
	
	@Override
	public void saveService(ServiceTypeModel serviceTypeModel) {
		session=sessionFactory.openSession();
		session.save(serviceTypeModel);
		session.beginTransaction().commit();
		session.close();
	}
	
	@Override
	public HashMap<String, String> getClothList(int shopNameId) {
		List<HashMap> list = new ArrayList<HashMap>();
		@SuppressWarnings("rawtypes")
		HashMap mapOfCloth = new HashMap();
		Session session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery("SELECT c.clothTypeID,c.clothType FROM  ClothTypeModel c where c.flag=1 and c.shopId="
						+ shopNameId + " ");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		System.out.println("get cloth Type table: " + list);
		for (HashMap map : list) {
			mapOfCloth.put(map.get("clothTypeID"), map.get("clothType"));
		}
		session.close();
		return mapOfCloth;
	}

	@Override
	public HashMap<String, String> getServiceList(int shopNameId) {
		List<HashMap> list = new ArrayList<HashMap>();
		@SuppressWarnings("rawtypes")
		HashMap mapOfService = new HashMap();
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT s.serviceTypeID,s.serviceTypeName FROM  ServiceTypeModel s where s.flag=1 and s.shopId=" + shopNameId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		System.out.println("get Service table: " + list);
		for (HashMap map : list) {
			mapOfService.put(map.get("serviceTypeID"), map.get("serviceTypeName"));
		}
		session.close();
		return mapOfService;
	}
	
	@Override
	public List<String> getClothTypeList(int shopNameId) {
		List<String> ClothTypeList= new ArrayList<String>();
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from ClothTypeModel c where c.flag=1 and c.shopId=" + shopNameId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		ClothTypeList=query.list();
		session.close();
		return ClothTypeList;
	}
	
	@Override
	public List<String> getServiceTypeList(int shopNameId) {
		List<String> ServiceTypeList= new ArrayList<String>();
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from ServiceTypeModel s where s.flag=1 and s.shopId=" + shopNameId + "");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		ServiceTypeList=query.list();
		session.close();
		return ServiceTypeList;
	}
	
	@Override
	public List<String> getRateList(int shopNameId) {
		 List<String> rateList= new ArrayList<String>();
		  session=sessionFactory.openSession();
		  SQLQuery query = session.createSQLQuery("select c.clothType,s.serviceTypeName,r.rate,r.rateId from ratemastermodel r " +
		  		"inner join clothtypemodel c on r.clothTypeID=c.clothTypeID\r\n" + 
		  		"inner join servicetypemodel s on r.serviceTypeID=s.serviceTypeID where r.shopId=" + shopNameId + "");
		  query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		  rateList=query.list();
		  session.close();
		  return rateList;
	}
	
	@Override
	public void updateServiceType(int serviceId, String serviceTypeName, String serviceType, int shopNameId) {
		System.out.println("Inside updateServiceType Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update ServiceTypeModel s set s.serviceTypeName='"+serviceTypeName+"', s.serviceType='"+serviceType+"' where s.serviceTypeID="+serviceId+" and s.shopId="+shopNameId+" ");
		query.executeUpdate();
		session.close();
		System.out.println("Service Type Updated...");
	}
	
	@Override
	public void deleteServiceType(int serviceTypeID, int shopNameId) {
		System.out.println("Inside delete Service Type Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("delete from ServiceTypeModel s where s.serviceTypeID="+serviceTypeID+" and s.shopId="+shopNameId+" ");
		query.executeUpdate();
		session.close();
		System.out.println("Service Type Deleted...");
	}
	
	@Override
	public ClothTypeModel findClothTypeiD(int clothTypeID, int shopId) {
		System.out.println("inside findClothTypeiD Dao");
		Criteria criteria = null;
		ClothTypeModel clothTypeModel = new ClothTypeModel();
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(ClothTypeModel.class).add(Restrictions.eq("clothTypeID", clothTypeID))
																.add(Restrictions.eq("shopId", shopId));
		clothTypeModel = (ClothTypeModel) criteria.setMaxResults(1).uniqueResult();
		System.out.println("inside findClothTypeiD Dao end");
		session.close();
		return clothTypeModel;
	}
	
	@Override
	public ServiceTypeModel findServiceTypeiD(int serviceTypeID, int shopId) {
		System.out.println("inside findServiceTypeiD dao");
		Criteria criteria = null;
		ServiceTypeModel serviceTypeModel = new ServiceTypeModel();
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(ServiceTypeModel.class).add(Restrictions.eq("serviceTypeID", serviceTypeID))
																 .add(Restrictions.eq("shopId", shopId));
		serviceTypeModel = (ServiceTypeModel) criteria.setMaxResults(1).uniqueResult();
		System.out.println("inside findServiceTypeiD dao end");
		session.close();
		return serviceTypeModel;
	}
	
	@Override
	public void saveRate(RateMasterModel rateMasterModel) {
		session =sessionFactory.openSession();
		session.save(rateMasterModel);
		session.beginTransaction().commit();
		session.close();
		System.out.println("Rate Saved...");
	}
	
	@Override
	public void updateRate(int rateId, double rate, int shopNameId) {
		System.out.println("Inside update Rate Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update RateMasterModel set rate='"+rate+"' where rateId="+rateId+" and shopId="+shopNameId+" ");
		query.executeUpdate();
		session.close();
		System.out.println("Rate Updated...");
	}
	
	@Override
	public void deleteRate(int rateId, int shopNameId) {
		System.out.println("Inside delete Rate Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("delete from RateMasterModel where rateId="+rateId+" and shopId="+shopNameId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Rate Deleted...");
	}
	
	@Override
	public List checkForDuplicateRate(int clothTypeID, int serviceTypeID, int shopNameId) {
		List rList= new ArrayList<String>();
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("SELECT * FROM ratemastermodel r where r.clothTypeID="+clothTypeID+" and r.serviceTypeID="+serviceTypeID+" and r.shopId="+shopNameId+"");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		rList=query.list();
		session.close();
		return rList;
	}
	

}
