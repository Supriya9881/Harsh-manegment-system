package com.admin.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Masters.MasterServiceInterface;
import com.Order.OrderServiceInterface;
import com.admin.AdminServiceInterface;
import com.customer.CustomerServiceInterface;
import com.model.master.OwnerDetailsModel;

import util.DatabaseBackUp;

@Controller
public class LoginPageController {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	AdminServiceInterface adminServiceInterface;
	
	@Autowired
	MasterServiceInterface masterServiceInterface;
	
	@Autowired
	CustomerServiceInterface customerServiceInterface;
	
	@Autowired
	OrderServiceInterface orderServiceInterface;
////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("getMainLogin")
	public ModelAndView FirstPage(HttpServletRequest request,HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		System.out.println("Login For Admin...");
		Session session=sessionFactory.openSession();
		SQLQuery query=session.createSQLQuery("select *from numberdata");
		List l=query.list();
		if(l.isEmpty())
		{
			for(int i=1;i<=500;i++)
			{
				SQLQuery query2=session.createSQLQuery("insert into numberdata values('"+i+"')");
				query2.executeUpdate();
			}
		}
		System.out.println("generate Number Data table");
		return new ModelAndView("mainLoginPage");
	}

	@RequestMapping(value = "HomePageFromLogin", method = RequestMethod.POST) // ,method = RequestMethod.POST
	public ModelAndView HomePage(Model model, HttpServletRequest request, HttpSession httpSession,
			@RequestParam("UserName") String UserName, @RequestParam("password") String password) {
		System.out.println("Home Page");
		System.out.println("User-Id-> " + UserName);
		System.out.println(" pwd-> " + password);

		OwnerDetailsModel ownerDetailsModel = new OwnerDetailsModel();
		ownerDetailsModel = adminServiceInterface.checkOwnerLogin(UserName, password);
		System.out.println(ownerDetailsModel);

		if (UserName.equals("Vishal@1234") && password.equals("Raj@1234")) {
			httpSession = request.getSession();
			httpSession.setAttribute("UsernameAdmin", UserName);
			httpSession.setAttribute("PasswordAdmin", password);
			httpSession.setAttribute("ShopName", "OMVSAB it Solution");
			System.out.println("Admin Logged in...");
			model.addAttribute("ShopName", "OmVsab");
			model.addAttribute("developerPage", "Developer Page");
			return new ModelAndView("DashboardPage");
		} else {
			if (ownerDetailsModel != null) {
				//if (UserName.equals("Admin") && password.equals("Admin")) {
				if (ownerDetailsModel.getOwnerUserName().equals(UserName) && ownerDetailsModel.getOwnerPassword().equals(password)) {
					httpSession = request.getSession();
					httpSession.setAttribute("UsernameAdmin", UserName);
					httpSession.setAttribute("PasswordAdmin", password);
					httpSession.setAttribute("ShopName", ownerDetailsModel.getShopDetailsModel().getShopName());
					httpSession.setAttribute("ShopNameId", ownerDetailsModel.getShopDetailsModel().getShopId());
					httpSession.setAttribute("OwnerNameId", ownerDetailsModel.getOwnerId());
					System.out.println("Admin Logged in...");
					//int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
					HashMap<String, String> AllDetailsList;
					AllDetailsList = adminServiceInterface.getAllDetailsList(ownerDetailsModel.getShopDetailsModel().getShopId());
					model.addAttribute("Todays_Orders", AllDetailsList.get("Todays_Orders"));
					model.addAttribute("Todays_Deliverys", AllDetailsList.get("Todays_Deliverys"));
					model.addAttribute("Todays_Cloths", AllDetailsList.get("Todays_Cloths"));
					model.addAttribute("todays_Amount", AllDetailsList.get("todays_Amount"));
					model.addAttribute("months_Amount", AllDetailsList.get("months_Amount"));
					model.addAttribute("todays_collection", AllDetailsList.get("todays_collection"));
					httpSession = request.getSession();
					httpSession.setAttribute("todaysCollection", AllDetailsList.get("todays_collection"));

					// model.addAttribute("AllDetailsList", AllDetailsList);
					return new ModelAndView("DashboardPage");
				} else {
					model.addAttribute("Message", "Please Enter Correct UserName And Password");
					return new ModelAndView("mainLoginPage");
				}
			} else {
				model.addAttribute("Message", "Please Enter Correct UserName And Password");
				return new ModelAndView("mainLoginPage");
			}
		}
	}
	
	//Dashboard
	@RequestMapping("Dashboard")
	public ModelAndView DashboardPage(HttpServletRequest request, HttpSession httpSession, Model model){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("DashboardPage");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			HashMap<String, String> AllDetailsList;
			AllDetailsList = adminServiceInterface.getAllDetailsList(ShopNameId);
			model.addAttribute("Todays_Orders", AllDetailsList.get("Todays_Orders"));
			model.addAttribute("Todays_Deliverys", AllDetailsList.get("Todays_Deliverys"));
			model.addAttribute("Todays_Cloths", AllDetailsList.get("Todays_Cloths"));
			model.addAttribute("todays_Amount", AllDetailsList.get("todays_Amount"));
			model.addAttribute("months_Amount", AllDetailsList.get("months_Amount"));
			model.addAttribute("todays_collection", AllDetailsList.get("todays_collection"));
			//model.addAttribute("AllDetailsList", AllDetailsList);
			return new ModelAndView("DashboardPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	@RequestMapping(value = "HomeFromHome", method = RequestMethod.GET)
	public ModelAndView HomePageToShow(Model model,HttpServletRequest request,HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Home Page To Show");
			return new ModelAndView("mainHomePage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}

	///////////////// OLD /////////////////////////
	@RequestMapping(value = "HomePageView", method = RequestMethod.GET)
	public ModelAndView HomePageView(Model model,HttpServletRequest request,HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Home Page To Show");
			return new ModelAndView("HomePageView");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	/*
	 * @RequestMapping(value = "homePageAction", params = "AddNewCustomer", method =
	 * RequestMethod.POST) public ModelAndView AddNewCustomer(Model
	 * model,HttpServletRequest request, HttpSession httpSession) { httpSession =
	 * request.getSession(); String UsName = (String)
	 * httpSession.getAttribute("UsernameAdmin"); System.out.println(UsName +
	 * " is User"); if (UsName != null) { int ShopNameId = (int)
	 * httpSession.getAttribute("ShopNameId"); String str =
	 * request.getParameter("customer"); HashMap<String, String> ClothList;
	 * ClothList = masterServiceInterface.getClothList(ShopNameId);
	 * model.addAttribute("ClothList", ClothList); //dropdown Service List
	 * HashMap<String, String> ServiceList; ServiceList =
	 * masterServiceInterface.getServiceList(ShopNameId);
	 * model.addAttribute("ServiceList", ServiceList);
	 * 
	 * List<String> CustomerList; CustomerList =
	 * customerServiceInterface.getCustomerList();
	 * model.addAttribute("CustomerList", CustomerList); return new
	 * ModelAndView("AddCustomerPageNew"); } else {
	 * System.out.println("Invalid Admin..."); model.addAttribute("Message",
	 * "First Login Your Account..."); return new ModelAndView("accessD"); } }
	 */
	
	@RequestMapping(value = "LogoutAdmin", method = RequestMethod.GET)
	public ModelAndView LogoutPage(HttpServletRequest request,HttpSession httpSession) {
		DatabaseBackUp databaseBackUp = new DatabaseBackUp(); 
		if(databaseBackUp.backup())
			System.out.println(" Data Backup Successfully");
		else 
			System.out.println("Error in backup time");
		
		System.out.println("Logout Page");
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " Session Distroyed..");
		httpSession.invalidate();
		return new ModelAndView("mainLoginPage");
	}
	
/*makeReamainingPayment.html*/
	
	@RequestMapping(value="makeReamainingPayment", method = RequestMethod.GET)
	public ModelAndView makeReamainingPayment(Model model,HttpServletRequest request,HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			int cId = Integer.parseInt(request.getParameter("cId2"));
			double payingAmount =Double.parseDouble(request.getParameter("payingAmount"));
			double paidAmt =Double.parseDouble(request.getParameter("paidAmt"));
			System.out.println("cId-> "+cId+" payingAmount-> "+payingAmount+" paidAmt-> "+paidAmt);
			//adminServiceInterface.updateReamainingPayment(cId,payingAmount,paidAmt);
			String CustomerContact = adminServiceInterface.updateReamainingPayment(cId,payingAmount,paidAmt);
			
			System.out.println("CustomerContact "+CustomerContact);
			List <String> CustomerInfo = orderServiceInterface.getCustomerInfo(CustomerContact, ShopNameId);
			List <String> AllOrderDetails = orderServiceInterface.getAllOrderDetails(CustomerContact);
			System.out.println("AllOrderDetails "+AllOrderDetails);
			model.addAttribute("CustomerInfo", CustomerInfo);
			model.addAttribute("AllOrderDetails", AllOrderDetails);
			return new ModelAndView("CustomerDetailsPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
}
