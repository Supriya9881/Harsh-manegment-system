package com.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Masters.MasterServiceInterface;
import com.admin.AdminServiceInterface;

@Controller
public class ReturnOrderController {

	@Autowired
	CustomerServiceInterface customerServiceInterface;
	
	@Autowired
	MasterServiceInterface masterServiceInterface;
	
	@RequestMapping(value="ReturnOrder")
	public ModelAndView ReturnOrder(Model model, HttpServletRequest request, HttpSession httpSession){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Add ReturnOrder Page");
			
			
			return new ModelAndView("ReturnOrderNew");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	@RequestMapping(value="ReturnOrderAction", method = RequestMethod.GET)
	public ModelAndView ReturnOrderAction(Model model, HttpServletRequest request, HttpSession httpSession){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Return Order Action Page");
			String orderIdForReturn =request.getParameter("orderIdForReturn");
			System.out.println(orderIdForReturn);
			model.addAttribute("orderIdForReturn", orderIdForReturn);
			return new ModelAndView("ReturnOrderNew");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	@RequestMapping(value="GetReturnOrderAction", method = RequestMethod.GET)
	public ModelAndView GetOrderAction(Model model, HttpServletRequest request, HttpSession httpSession){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Add ReturnOrder Page");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			String mobileNoForGetOrder = request.getParameter("mobileNoForGetOrder");
			System.out.println(mobileNoForGetOrder);
			// dropdown cloth list
			HashMap<String, String> ClothList;
			ClothList = masterServiceInterface.getClothList(ShopNameId);
			model.addAttribute("ClothList", ClothList);
			// dropdown Service List
			HashMap<String, String> ServiceList;
			ServiceList = masterServiceInterface.getServiceList(ShopNameId);
			model.addAttribute("ServiceList", ServiceList);
		    model.addAttribute("mobileNoForGetOrder", mobileNoForGetOrder);
			return new ModelAndView("GetOrderNew");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	@RequestMapping(value = "getClothTypeDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getClothTypeDetails(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		System.out.println("orderId=> " + orderId);
		List<String> list = new ArrayList<>();
		list = customerServiceInterface.getClothTypeDetails(orderId);
		System.out.println(list);
		return list;
	}
//========== getServiceTypeList  ===========getQuantity
	@RequestMapping(value = "getServiceTypeList", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getServiceTypeList(HttpServletRequest request) {
		String ClothTypeID = request.getParameter("ClothTypeID");
		String orderId = request.getParameter("orderId");
		System.out.println("ClothTypeID=> " + ClothTypeID);
		List<String> list = new ArrayList<>();
		list = customerServiceInterface.getServiceTypeList(orderId,ClothTypeID);
		System.out.println(list);
		return list;
	}
//========== getQuantity  ===========	
	@RequestMapping(value = "getQuantity", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getQuantity(HttpServletRequest request) {
		String ClothTypeID = request.getParameter("ClothTypeID");
		String orderId = request.getParameter("orderId");
		String ServiceTypeID = request.getParameter("ServiceTypeID");
		System.out.println("ServiceTypeID => "+ServiceTypeID);
		List<String> list = new ArrayList<>();
		list = customerServiceInterface.getQuantity(orderId,ClothTypeID,ServiceTypeID);
		System.out.println(list);
		return list;
	}

//========== getRemainingAmount  ===========
			@RequestMapping(value = "getRemainingAmount", method = RequestMethod.GET)
			@ResponseBody
			public List<String> getRemainingAmount(HttpServletRequest request) {

				String orderId = request.getParameter("orderId");
				
				List<String> list = new ArrayList<>();
				list = customerServiceInterface.getRemainingAmount(orderId);
				System.out.println(list);
				return list;
			}
			
//========== Update Remaining  ===========	
			
			@RequestMapping(value = "updateReturnPayment" , method = RequestMethod.POST)
			public ModelAndView updatePayment(HttpServletRequest request) {
				
				System.out.println("updatePayment");
		
		  String orderId = request.getParameter("qrOrderId"); 
		  double amountPaid = Double.parseDouble(request.getParameter("amountPaid")); 
		  double amountRemaining =Double.parseDouble(request.getParameter("amountRemaining"));
		  double allRemainingAmt =Double.parseDouble(request.getParameter("allRemainingAmt"));
		  String totalQuantity =request.getParameter("qrQuantity");
		  String CompleteQuantity =request.getParameter("completeQuan");
		  
		  int qrQuantity=Integer.parseInt(totalQuantity)-Integer.parseInt(CompleteQuantity);
		  System.out.println(qrQuantity+" "+orderId+" "+amountPaid+" "+amountRemaining+" "+totalQuantity+" "+CompleteQuantity+" "+allRemainingAmt);
		  
		  customerServiceInterface.updatePayment(orderId,amountPaid,amountRemaining,qrQuantity,allRemainingAmt);
		 
				return new ModelAndView("ReturnOrderNew");
			}
			
}
