package com.Order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrdersStatusController {

	@Autowired
	OrderServiceInterface orderServiceInterface;
	
	//NotReadyOrdersAll
	@RequestMapping(value="NotReadyOrdersAll")
	public ModelAndView NotReadyOrdersAll(HttpServletRequest request, HttpSession httpSession, Model model){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Not Ready Orders All Page");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> NotReadyOrdersList = orderServiceInterface.getNotReadyOrdersList(ShopNameId);
			System.out.println("NotReadyOrdersList " + NotReadyOrdersList);
			model.addAttribute("NotReadyOrdersList", NotReadyOrdersList);
			return new ModelAndView("NotReadyOrdersAllPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
			
		}
	}
	
	//ReadyOrdersAll
	@RequestMapping(value="ReadyOrdersAll")
	public ModelAndView ReadyOrdersAll(HttpServletRequest request, HttpSession httpSession, Model model){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("ReadyOrdersAll Page");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> ReadyOrdersList = orderServiceInterface.getReadyOrdersList(ShopNameId);
			System.out.println("ReadyOrdersList " + ReadyOrdersList);
			model.addAttribute("ReadyOrdersList", ReadyOrdersList);
			return new ModelAndView("ReadyOrdersAllPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
			
		}
	}
	
	//IncompleteOrdersAll
	@RequestMapping(value="IncompleteOrdersAll")
	public ModelAndView IncompleteOrdersAll(HttpServletRequest request, HttpSession httpSession, Model model){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("IncompleteOrdersAll Page");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> IncompleteOrdersList = orderServiceInterface.getIncompleteOrdersList(ShopNameId);
			System.out.println("IncompleteOrdersList " + IncompleteOrdersList);
			model.addAttribute("IncompleteOrdersList", IncompleteOrdersList);
			return new ModelAndView("IncompleteOrdersAllPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
			
		}
	}
	
	//DeliveredOrdersAll
	@RequestMapping(value="DeliveredOrdersAll")
	public ModelAndView DeliveredOrdersAll(HttpServletRequest request, HttpSession httpSession, Model model){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("DeliveredOrdersAll Page");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> DeliveredOrdersList = orderServiceInterface.getDeliveredOrdersList(ShopNameId);
			System.out.println("DeliveredOrdersList " + DeliveredOrdersList);
			model.addAttribute("DeliveredOrdersList", DeliveredOrdersList);
			return new ModelAndView("DeliveredOrdersAllPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
			
		}
	}
	
	
}
