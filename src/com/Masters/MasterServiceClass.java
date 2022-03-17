package com.Masters;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.master.ClothTypeModel;
import com.model.master.PreferanceModel;
import com.model.master.RateMasterModel;
import com.model.master.ServiceTypeModel;

@Service("MasterServiceInterface")
@Transactional(propagation = Propagation.SUPPORTS)
public class MasterServiceClass implements MasterServiceInterface {

	@Autowired
	MasterDaoInterface masterDaoInterface;

	@Override
	public List<String> getPreferanceList(int shopNameId) {
		return masterDaoInterface.getPreferanceList( shopNameId);
	}

	@Override
	public void savePreferance(PreferanceModel preferanceModel) {
		masterDaoInterface.savePreferance( preferanceModel);
	}

	@Override
	public void updatePreferanceType(int preferanceId, String preferanceName, double preferancePrice, int shopNameId) {
		masterDaoInterface.updatePreferanceType( preferanceId, preferanceName, preferancePrice, shopNameId);
	}

	@Override
	public void deletePreferanceType(int preferanceId, int shopNameId) {
		masterDaoInterface.deletePreferanceType( preferanceId, shopNameId);
	}

	@Override
	public PreferanceModel getDuplicatePreferance(String preferanceName, int shopNameId) {
		return masterDaoInterface.getDuplicatePreferance( preferanceName, shopNameId);
	}

	@Override
	public ClothTypeModel getDuplicateClothType(String clothType, int shopNameId) {
		return masterDaoInterface.getDuplicateClothType( clothType, shopNameId);
	}

	@Override
	public ServiceTypeModel getDuplicateServiceType(String serviceTypeName, int shopNameId) {
		return masterDaoInterface.getDuplicateServiceType(serviceTypeName, shopNameId);
	}
	
	@Override
	public void saveCloths(ClothTypeModel clothTypeModel) {
		masterDaoInterface.saveCloths( clothTypeModel);
	}
	
	@Override
	public void saveServiceType(ServiceTypeModel serviceTypeModel) {
		masterDaoInterface.saveService(serviceTypeModel);
	}

	@Override
	public HashMap<String, String> getClothList(int shopNameId) {
		return masterDaoInterface.getClothList( shopNameId);
	}

	@Override
	public HashMap<String, String> getServiceList(int shopNameId) {
		return masterDaoInterface.getServiceList( shopNameId);
	}

	@Override
	public List<String> getRateList(int shopNameId) {
		return masterDaoInterface.getRateList( shopNameId);
	}

	@Override
	public List<String> getClothTypeList(int shopNameId) {
		return masterDaoInterface.getClothTypeList( shopNameId);
	}

	@Override
	public List<String> getServiceTypeList(int shopNameId) {
		return masterDaoInterface.getServiceTypeList( shopNameId);
	}

	@Override
	public void updateServiceType(int serviceTypeID, String serviceTypeName, String serviceType, int shopNameId) {
		masterDaoInterface.updateServiceType(serviceTypeID, serviceTypeName, serviceType, shopNameId);
	}

	@Override
	public void deleteServiceType(int serviceTypeID, int shopNameId) {
		masterDaoInterface.deleteServiceType( serviceTypeID, shopNameId);
	}

	@Override
	public ClothTypeModel findClothTypeiD(int clothTypeID, int shopNameId) {
		return masterDaoInterface.findClothTypeiD( clothTypeID, shopNameId);
	}

	@Override
	public ServiceTypeModel findServiceTypeiD(int serviceTypeID, int shopNameId) {
		return masterDaoInterface.findServiceTypeiD( serviceTypeID, shopNameId);
	}
	
	@Override
	public void saveRate(RateMasterModel rateMasterModel) {
		masterDaoInterface.saveRate( rateMasterModel);
	}

	@Override
	public void updateRate(int rateId, double rate, int shopNameId) {
		masterDaoInterface.updateRate( rateId, rate, shopNameId);
	}

	@Override
	public void deleteRate(int rateId, int shopNameId) {
		masterDaoInterface.deleteRate( rateId, shopNameId);
	}

	@Override
	public void updateClothType(int clothId, String clothTypeName, int shopNameId) {
		masterDaoInterface.updateClothType( clothId, clothTypeName, shopNameId);
	}

	@Override
	public void deleteClothType(int clothId, int shopNameId) {
		masterDaoInterface.deleteClothType( clothId, shopNameId);
	}

	@Override
	public List checkForDuplicateRate(int clothTypeID, int serviceTypeID, int shopNameId) {
		return masterDaoInterface.checkForDuplicateRate( clothTypeID, serviceTypeID, shopNameId);
	}

}
