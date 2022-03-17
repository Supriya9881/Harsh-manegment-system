package com.WebServices;

import java.util.List;

import com.model.CustomerDetailModel;

public interface WebServicesDaoInterface {

	List<String> GetCustomerDetailsByJSON();

	void SaveCustomerDetailsByJSON(CustomerDetailModel customerDetailModel);

}
