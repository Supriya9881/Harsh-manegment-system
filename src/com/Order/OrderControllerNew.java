package com.Order;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.admin.AdminServiceInterface;

@Controller
public class OrderControllerNew {

	@Autowired
	OrderServiceInterface orderServiceInterface;
	
	@Autowired
	AdminServiceInterface adminServiceInterface;
	// getTodaysDelivery
	@RequestMapping(value = "getTodaysDelivery")
	public ModelAndView getTodaysDelivery(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("getTodaysDelivery");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> TodaysDeliveryDetails = orderServiceInterface.getTodaysDeliveryDetails(ShopNameId);
			HashMap<String, String> AllDetailsList;
			AllDetailsList = adminServiceInterface.getAllDetailsList(ShopNameId);
			model.addAttribute("Todays_Deliverys", AllDetailsList.get("Todays_Deliverys"));
			System.out.println("TodaysDeliveryDetails " + TodaysDeliveryDetails);
			model.addAttribute("TodaysDeliveryDetails", TodaysDeliveryDetails);
			return new ModelAndView("TodaysDeliveryPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// getTodaysOrders
	@RequestMapping(value = "getTodaysOrders")
	public ModelAndView getTodaysOrders(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("getTodaysOrders");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> TodaysOrdersDetails = orderServiceInterface.getTodaysOrdersDetails(ShopNameId);
			HashMap<String, String> AllDetailsList;
			AllDetailsList = adminServiceInterface.getAllDetailsList(ShopNameId);
			model.addAttribute("Todays_Orders", AllDetailsList.get("Todays_Orders"));
			System.out.println("TodaysOrdersDetails " + TodaysOrdersDetails);
			model.addAttribute("TodaysOrdersDetails", TodaysOrdersDetails);
			return new ModelAndView("TodaysOrdersPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// getTodaysCollection
	@RequestMapping(value = "getTodaysCollection")
	public ModelAndView getTodaysCollection(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("getTodaysCollection");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> TodaysOrdersDetails = orderServiceInterface.getTodaysCollectionDetails(ShopNameId);
			HashMap<String, String> AllDetailsList;
			AllDetailsList = adminServiceInterface.getAllDetailsList(ShopNameId);
			System.out.println("TodaysOrdersDetails " + TodaysOrdersDetails);
			model.addAttribute("TodaysOrdersDetails", TodaysOrdersDetails);
			model.addAttribute("todays_collection", AllDetailsList.get("todays_collection"));
			return new ModelAndView("TodaysCollectionPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// getTodaysRevenue
	@RequestMapping(value = "getTodaysRevenue")
	public ModelAndView getTodaysRevenue(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("getTodaysRevenue");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> TodaysOrdersDetails = orderServiceInterface.getTodaysOrdersDetails(ShopNameId);
			HashMap<String, String> AllDetailsList;
			AllDetailsList = adminServiceInterface.getAllDetailsList(ShopNameId);
			model.addAttribute("todays_Amount", AllDetailsList.get("todays_Amount"));
			System.out.println("TodaysOrdersDetails " + TodaysOrdersDetails);
			model.addAttribute("TodaysOrdersDetails", TodaysOrdersDetails);
			return new ModelAndView("getTodaysRevenuePage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// paidReportPage.html
	@RequestMapping(value = "paidReportPage")
	public ModelAndView paidPendingReportPage(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("paidReportPage.html");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> TodaysOrdersDetails = orderServiceInterface.getPaidPendingOrdersData(ShopNameId);
			System.out.println("TodaysOrdersDetails " + TodaysOrdersDetails);
			model.addAttribute("TodaysOrdersDetails", TodaysOrdersDetails);
			return new ModelAndView("PaidReportPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// paidPendingReportPage
	@RequestMapping(value = "PendingReportPage")
	public ModelAndView PendingReportPage(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("PendingReportPage");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> TodaysOrdersDetails = orderServiceInterface.getPendingOrdersData(ShopNameId);
			System.out.println("TodaysOrdersDetails " + TodaysOrdersDetails);
			model.addAttribute("TodaysOrdersDetails", TodaysOrdersDetails);
			return new ModelAndView("PendingReportPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
}
