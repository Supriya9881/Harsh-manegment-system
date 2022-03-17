package com.Developer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.master.OwnerDetailsModel;
import com.model.master.ShopDetailsModel;

@Service("DeveloperServiceInterface")
@Transactional(propagation = Propagation.SUPPORTS)
public class DeveloperServiceClass implements DeveloperServiceInterface {

	@Autowired
	DeveloperDaoInterface developerDaoInterface;

	@Override
	public List<String> getShopNameList() {
		return developerDaoInterface.getShopNameList();
	}

	@Override
	public ShopDetailsModel findShopNameID(int shopNameId) {
		return developerDaoInterface.findShopNameID( shopNameId);
	}

	@Override
	public void saveShopDetails(ShopDetailsModel shopDetailsModel) {
		developerDaoInterface.saveShopDetails( shopDetailsModel);
	}

	@Override
	public void saveOwnerDetails(OwnerDetailsModel ownerDetailsModel) {
		developerDaoInterface.saveOwnerDetails( ownerDetailsModel);
	}

	@Override
	public List<String> getOwnerNameList() {
		return developerDaoInterface.getOwnerNameList();
	}
}
