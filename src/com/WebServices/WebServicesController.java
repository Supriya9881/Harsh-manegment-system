package com.WebServices;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.CustomerDetailModel;

@Controller
public class WebServicesController {

	@Autowired
	WebServicesServiceInterface webServicesServiceInterface;
	
	@RequestMapping(value="SaveCustomerDetailsByJSON",method = RequestMethod.GET)
	@ResponseBody
	public void SaveCustomerDetailsByJSON(HttpServletRequest request,@ModelAttribute("")CustomerDetailModel customerDetailModel) {
		System.out.println("Inside Save Controller");
		webServicesServiceInterface.SaveCustomerDetailsByJSON(customerDetailModel);
	}
	
	@RequestMapping(value="GetCustomerDetailsByJSON",method = RequestMethod.GET)
	@ResponseBody
	public List<String> GetCustomerDetailsByJSON(){
		System.out.println("Inside Save Controller");
		List<String> list = new ArrayList<>();
		list = webServicesServiceInterface.GetCustomerDetailsByJSON();
		return list;
	}
}
