package com.Masters;

import java.util.HashMap;
import java.util.List;

import com.model.master.ClothTypeModel;
import com.model.master.PreferanceModel;
import com.model.master.RateMasterModel;
import com.model.master.ServiceTypeModel;

public interface MasterServiceInterface {

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	List<String> getPreferanceList(int shopNameId);

	void savePreferance(PreferanceModel preferanceModel);

	void updatePreferanceType(int preferanceId, String preferanceName, double preferancePrice, int shopNameId);

	void deletePreferanceType(int preferanceId, int shopNameId);

	PreferanceModel getDuplicatePreferance(String preferanceName, int shopNameId);
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	ClothTypeModel getDuplicateClothType(String clothType, int shopNameId);

	ServiceTypeModel getDuplicateServiceType(String serviceTypeName, int shopNameId);
	
	void saveCloths(ClothTypeModel clothTypeModel);
	
	void saveServiceType(ServiceTypeModel serviceTypeModel);

	HashMap<String, String> getClothList(int shopNameId);

	HashMap<String, String> getServiceList(int shopNameId);

	List<String> getRateList(int shopNameId);

	List<String> getClothTypeList(int shopNameId);

	List<String> getServiceTypeList(int shopNameId);

	void updateServiceType(int serviceTypeID, String serviceTypeName, String serviceType, int shopNameId);

	void deleteServiceType(int serviceTypeID, int shopNameId);

	ClothTypeModel findClothTypeiD(int clothTypeID, int shopNameId);

	ServiceTypeModel findServiceTypeiD(int serviceTypeID, int shopNameId);

	void saveRate(RateMasterModel rateMasterModel);

	void updateRate(int rateId, double rate, int shopNameId);

	void deleteRate(int rateId, int shopNameId);

	void updateClothType(int clothId, String clothTypeName, int shopNameId);

	void deleteClothType(int clothId, int shopNameId);

	List checkForDuplicateRate(int clothTypeID, int serviceTypeID, int shopNameId);

	

	
}
