package com.WebServices;

import java.util.List;

import com.model.CustomerDetailModel;

public interface WebServicesServiceInterface {

	void SaveCustomerDetailsByJSON(CustomerDetailModel customerDetailModel);

	List<String> GetCustomerDetailsByJSON();

}
