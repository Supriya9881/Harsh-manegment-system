package com.WebServices;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.CustomerDetailModel;

@Service("WebServicesServiceInterface")
@Transactional(propagation = Propagation.SUPPORTS)
public class WebServicesServiceClass implements WebServicesServiceInterface {

	@Autowired
	WebServicesDaoInterface webServicesDaoInterface; 

	@Override
	public void SaveCustomerDetailsByJSON(CustomerDetailModel customerDetailModel) {
		webServicesDaoInterface.SaveCustomerDetailsByJSON( customerDetailModel);
	}

	@Override
	public List<String> GetCustomerDetailsByJSON() {
		return webServicesDaoInterface.GetCustomerDetailsByJSON();
	}
}
