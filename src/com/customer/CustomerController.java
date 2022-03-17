package com.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Developer.DeveloperServiceInterface;
import com.Masters.MasterServiceInterface;
import com.Order.OrderServiceInterface;
import com.SendSMS.STCOPSMS;
import com.admin.AdminServiceInterface;
import com.model.CustomerDetailModel;
import com.model.CustomerOrderModel;
import com.model.CustomerOrderModel_2;
import com.model.master.RateMasterModel;

@Controller
public class CustomerController {

	@Autowired
	CustomerServiceInterface customerServiceInterface;
	@Autowired
	AdminServiceInterface adminServiceInterface;
	@Autowired
	DeveloperServiceInterface developerServiceInterface;
	@Autowired
	MasterServiceInterface masterServiceInterface;
	@Autowired
	OrderServiceInterface orderServiceInterface;
	List<String> keyword = new ArrayList<>();

	@Autowired
	SessionFactory sessionFactory;

	// AddCustomerPage
	@RequestMapping("AddCustomerPage")
	public ModelAndView AddCustomerPage(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("AddCustomerPage");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> CustomerList;
			CustomerList = customerServiceInterface.getCustomerList(ShopNameId);
			model.addAttribute("CustomerList", CustomerList);
			return new ModelAndView("AddCustomerPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

//================== SaveCustomer =========================	New
	@RequestMapping(value = "SaveNewCustomer", method = RequestMethod.POST, params = "btnSaveCustomer")
	public ModelAndView SaveCustomer(Model model, @ModelAttribute("") CustomerDetailModel customerDetailModel,
			HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Save Customer Info");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			String ShopName = (String) httpSession.getAttribute("ShopName");
			customerDetailModel.setShopName(ShopName);
			String mb = customerDetailModel.getcMobile();
			if (mb.equals("5555555555")) {
				System.out.println("5555555555");
				customerDetailModel.setShopId(ShopNameId);
				customerServiceInterface.saveCustomerDetails(customerDetailModel);
				List<String> CustomerList;
				CustomerList = customerServiceInterface.getCustomerList(ShopNameId);
				model.addAttribute("CustomerList", CustomerList);
				return new ModelAndView("AddCustomerPage");
			} else {
				CustomerDetailModel customerDetailModel2 = new CustomerDetailModel();
				customerDetailModel2 = customerServiceInterface.checkDuplicateCustomer(customerDetailModel.getcMobile(),
						ShopNameId);
				if (customerDetailModel2 == null) {
					customerDetailModel.setShopId(ShopNameId);
					customerServiceInterface.saveCustomerDetails(customerDetailModel);
					SendSmsForNewCustomer(customerDetailModel);
				} else {
					model.addAttribute("msgError", "Customer Already Exist...");
					System.out.println("Duplicate Customer..");
				}
				List<String> CustomerList;
				CustomerList = customerServiceInterface.getCustomerList(ShopNameId);
				model.addAttribute("CustomerList", CustomerList);
				return new ModelAndView("AddCustomerPage");
			}
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	public void SendSmsForNewCustomer(CustomerDetailModel customerDetailModel) {
		String mobile = "";
		String msg = "";
		String gender_name = customerDetailModel.getGender() + " " + customerDetailModel.getcName();
		// String gender = "";
		try {
			mobile = customerDetailModel.getcMobile();
			/*msg = "Hi " + customerDetailModel.getGender() + " " + customerDetailModel.getcName() + ", Welcome To "
					+ customerDetailModel.getShopName() + " Laundry Services.%0AThank you for joining us."; */

			msg = "Hi "+gender_name+" welcome to Perclean Laundry Services.%0AThank you for joining Us.";
			
			System.out.println("mobile : " + mobile + " msg " + msg);
			STCOPSMS stcopsms = new STCOPSMS();
			stcopsms.sendSMS(mobile, msg);
			System.out.println("msg send success..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// sendReadyOrderSMS
	@RequestMapping(value = "sendReadyOrderSMS", method = RequestMethod.GET)
	@ResponseBody
	public void sendReadyOrderSMS(HttpServletRequest request) throws IOException {
		String cName = request.getParameter("cName");
		String orderId = request.getParameter("orderId");
		String invoiceNo = request.getParameter("invoiceNo");
		String cMobile = request.getParameter("cMobile");

		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(CustomerDetailModel.class);
		Criterion cr1 = Restrictions.eq("cMobile", cMobile);
		criteria.add(cr1);
		CustomerDetailModel customerDetailModel = (CustomerDetailModel) criteria.setMaxResults(1).uniqueResult();
		String gender_name = customerDetailModel.getGender() + " " + customerDetailModel.getcName();
		
		/*String sms = "Perclean Laundry.%0ADear " + customerDetailModel.getGender() + " "
				+ customerDetailModel.getcName() + "%0AYour In. No :" + invoiceNo
				+ " is Ready please collect them.%0AThank You.";*/

		String sms2 = "Perclean Laundry.%0A" + gender_name + " Your In.No:" + invoiceNo
				+ " is ready please collect them.%0AThank you.%0APerclean";

		System.out.println("Mob " + cMobile + " sms - " + sms2);

		STCOPSMS sendSms = new STCOPSMS();
		sendSms.sendSMS(cMobile, sms2);
		System.out.println("msg send success..");
	}

	// editCustomerDetails
	@RequestMapping(value = "SaveNewCustomer", params = "btnUpdateCustomer", method = RequestMethod.POST)
	public ModelAndView updateCustomer(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("update Cutomer");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			int CustomerId = Integer.parseInt(request.getParameter("cId2"));
			String CustomerName = request.getParameter("cName");
			String CustomerMobile = request.getParameter("cMobile");
			String CustomerAddress = request.getParameter("cAddress");
			customerServiceInterface.updateCustomerDetails(CustomerId, CustomerMobile, CustomerName, CustomerAddress,
					ShopNameId);
			List<String> CustomerList;
			CustomerList = customerServiceInterface.getCustomerList(ShopNameId);
			model.addAttribute("CustomerList", CustomerList);
			return new ModelAndView("AddCustomerPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	@RequestMapping(value = "SaveNewCustomer", params = "btnDeleteCustomer", method = RequestMethod.POST)
	public ModelAndView deleteCustomer(Model model, HttpServletRequest request, HttpSession httpSession,
			@RequestParam("cId2") int CustomerId) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("delete Cutomer");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			int CustomerId1 = Integer.parseInt(request.getParameter("cId2"));
			customerServiceInterface.deleteCustomerDetails(CustomerId1, ShopNameId);
			List<String> CustomerList;
			CustomerList = customerServiceInterface.getCustomerList(ShopNameId);
			model.addAttribute("CustomerList", CustomerList);
			return new ModelAndView("AddCustomerPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	/////////////////////////////// SearchCustomer Page////////////////////////

	@RequestMapping("SearchCustomer")
	public ModelAndView SearchCustomer(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("SearchCustomer");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> CustomerList;
			CustomerList = customerServiceInterface.getCustomerList(ShopNameId);
			model.addAttribute("CustomerList", CustomerList);
			return new ModelAndView("SearchCustomerPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// searchCustomerMobileNo // Calling customer Autocomplete
	@RequestMapping("searchCustomerContactAutoComplete")
	@ResponseBody
	public List<String> searchCustomerContactAutoComplete(HttpServletRequest requet, HttpSession httpSession) {
		httpSession = requet.getSession();
		int ShopNameId = (int) httpSession.getAttribute("ShopNameId");

		keyword = orderServiceInterface.searchCustomerContactAutoComplete(requet.getParameter("term"), ShopNameId);

		return keyword;
	}

	// CustomerDetailsPage
	@RequestMapping("CustomerDetailsPage")
	public ModelAndView CustomerDetailsPage(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("CustomerDetailsPage");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			String CustomerContact = request.getParameter("CustomerContact");
			System.out.println("CustomerContact " + CustomerContact);
			List<String> CustomerInfo = orderServiceInterface.getCustomerInfo(CustomerContact, ShopNameId);
			if (CustomerInfo.isEmpty()) {
				model.addAttribute("mgsNotFound", "Customer Not Found Plz Add New Customer");
				List<String> CustomerList;
				CustomerList = customerServiceInterface.getCustomerList(ShopNameId);
				model.addAttribute("CustomerList", CustomerList);
				return new ModelAndView("SearchCustomerPage");
			} else {
				List<String> AllOrderDetails = orderServiceInterface.getAllOrderDetails(CustomerContact);
				System.out.println("AllOrderDetails " + AllOrderDetails);
				model.addAttribute("CustomerInfo", CustomerInfo);
				model.addAttribute("AllOrderDetails", AllOrderDetails);
				return new ModelAndView("CustomerDetailsPage");
			}

		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// SearchCustomerContactDetails
	@RequestMapping(value = "SearchCustomerContactDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<String> SearchCustomerContactDetails(HttpServletRequest request) {
		String CustomerContact = request.getParameter("CustomerContact");
		System.out.println("CustomerContact=> " + CustomerContact);
		List<String> list = new ArrayList<>();
		list = orderServiceInterface.SearchCustomerContactDetails(CustomerContact);
		System.out.println(list);
		return list;
	}

	// searchCustomerMobileNo
	// Calling customer Autocomplete
	@RequestMapping("searchCustomerMobileNo")
	@ResponseBody
	public List<String> searchCustomerMobileNo(HttpServletRequest requet) {
		keyword = orderServiceInterface.searchCustomerMobileNo(requet.getParameter("term")); // Calling Autocomplete
																								// method
		return keyword;
	}

//===================================== getRate Details From Rate Master ====================	
	// Json get rate List from Rate Master page
	@RequestMapping(value = "getRateDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getRateDetails(HttpServletRequest request,
			@ModelAttribute("") RateMasterModel rateMasterModel) {
		String ServiceTypeID = request.getParameter("ServiceTypeID");
		String ClothTypeID = request.getParameter("ClothTypeID");
		System.out.println("ClothTypeID=> " + ClothTypeID + " ServiceTypeID=> " + ServiceTypeID);
		List<String> list = new ArrayList<>();
		list = customerServiceInterface.getRateDetails(ServiceTypeID, ClothTypeID);
		System.out.println(list);
		return list;
	}

//======================= getRateFromCustomerOrder =========================
	// Json get get Rate From Customer Order page
	@RequestMapping(value = "getRateFromCustomerOrder", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getRateFromCustomerOrder(HttpServletRequest request,
			@ModelAttribute("") CustomerOrderModel customerOrderModel) {
		String CustomerId = request.getParameter("CustomerId");
		String ServiceTypeID = request.getParameter("ServiceTypeID");
		String ClothTypeID = request.getParameter("ClothTypeID");
		System.out.println(
				"CustomerId " + CustomerId + " ClothTypeID " + ClothTypeID + " ServiceTypeID=> " + ServiceTypeID);
		System.out.println("getting previous rate...");
		List<String> list = new ArrayList<>();
		list = customerServiceInterface.getRateFromCustomerOrder(CustomerId, ServiceTypeID, ClothTypeID);
		System.out.println(list);
		return list;
	}

	// Calling stock Autocomplete ========== deleteOrder
	@RequestMapping("/searchCustomerContact")
	@ResponseBody
	public List<String> autoContactcomplete(HttpServletRequest requet) {
		keyword = customerServiceInterface.searchCustomerContact(requet.getParameter("term"));

		return keyword;
	}

	// Json Customer All Details
	@RequestMapping(value = "getCustomerContactAllDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getCustomerContactAllDetails(HttpServletRequest request,
			@ModelAttribute("") CustomerDetailModel customerDetailModel) {
		String CustomerContact = request.getParameter("CustomerContact");
		System.out.println("CustomerContact = " + CustomerContact);
		List<String> list = new ArrayList<>();
		list = customerServiceInterface.getCustomerContactAllDetails(CustomerContact);
		System.out.println(list);
		return list;
	}

//======================= Get Order =================================== 
	@RequestMapping(value = "GetOrderNew")
	public ModelAndView GetOrder(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Get Order Customer Page");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			// dropdown cloth list
			HashMap<String, String> ClothList;
			ClothList = masterServiceInterface.getClothList(ShopNameId);
			model.addAttribute("ClothList", ClothList);
			// dropdown Service List
			HashMap<String, String> ServiceList;
			ServiceList = masterServiceInterface.getServiceList(ShopNameId);
			model.addAttribute("ServiceList", ServiceList);

			List<String> CustomerList;
			CustomerList = customerServiceInterface.getCustomerList(ShopNameId);
			model.addAttribute("CustomerList", CustomerList);
			return new ModelAndView("GetOrderNew");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	@RequestMapping(value = "allOrders")
	public ModelAndView allOrders(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("all Orders Page");

			List<String> allOrders;
			allOrders = customerServiceInterface.getAllOrdersList();
			model.addAttribute("allOrders", allOrders);
			System.out.println("ALl Orders : " + allOrders);
			return new ModelAndView("AllOrdersPageNew");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
}
