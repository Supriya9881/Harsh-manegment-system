package com.Developer;

import java.util.List;

import com.model.master.OwnerDetailsModel;
import com.model.master.ShopDetailsModel;

public interface DeveloperDaoInterface {

	List<String> getShopNameList();

	ShopDetailsModel findShopNameID(int shopNameId);

	void saveShopDetails(ShopDetailsModel shopDetailsModel);

	void saveOwnerDetails(OwnerDetailsModel ownerDetailsModel);

	List<String> getOwnerNameList();

}
