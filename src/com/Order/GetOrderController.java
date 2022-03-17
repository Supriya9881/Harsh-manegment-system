package com.Order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Masters.MasterServiceInterface;
import com.SendSMS.STCOPSMS;
import com.admin.AdminServiceInterface;
import com.customer.CustomerServiceInterface;
import com.model.CustomerDetailModel;
import com.model.CustomerOrderModel;
import com.model.CustomerOrderModel_2;
import com.model.OrderDetails;
import com.model.ReturnOrderDetails;
import com.model.ReturnOrderEntryModel;

import net.sf.jasperreports.engine.JRException;
import util.PrintJasperReport;

@Controller
public class GetOrderController {

	@Autowired
	OrderServiceInterface orderServiceInterface;
	@Autowired
	AdminServiceInterface adminServiceInterface;
	@Autowired
	MasterServiceInterface masterServiceInterface;
	@Autowired
	CustomerServiceInterface customerServiceInterface;

	List<String> keyword = new ArrayList<>();

	@Autowired
	SessionFactory sessionFactory;

	// GetOrderPage
	@RequestMapping(value = "GetOrderPage")
	public ModelAndView GetOrderPage(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("GetOrderPage");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			int customerId = Integer.parseInt(request.getParameter("customerId"));

			HashMap<String, String> ClothList;
			ClothList = masterServiceInterface.getClothList(ShopNameId);
			model.addAttribute("ClothList", ClothList);

			HashMap<String, String> ServiceList;
			ServiceList = masterServiceInterface.getServiceList(ShopNameId);
			model.addAttribute("ServiceList", ServiceList);

			List<String> preferanceList;
			preferanceList = masterServiceInterface.getPreferanceList(ShopNameId);
			model.addAttribute("preferanceList", preferanceList);

			model.addAttribute("customerId", customerId);
			return new ModelAndView("GetOrderPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");

		}
	}

	// ===== getCustomerContactDetails Json CustomerDetails ==============
	@RequestMapping(value = "getCustomerContactDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getCustomerContactDetails(HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			String CustomerId = request.getParameter("CustomerId");
			System.out.println("CustomerId=" + CustomerId);
			List<String> list = new ArrayList<>();
			list = customerServiceInterface.getCustomerContactDetails(CustomerId, ShopNameId);
			System.out.println(list);
			return list;
		} else {
			return null;
		}

	}

	// getInvoiceNo
	@RequestMapping(value = "getInvoiceNoForNew", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getInvoiceNoForNew(HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> list = new ArrayList<>();
			list = orderServiceInterface.getInvoiceNo(ShopNameId);
			System.out.println(list);
			return list;
		} else {
			return null;
		}
	}

	// =======================================saveClothOrder==================================================

	// Json get Menu list page
	@RequestMapping(value = "saveClothOrder", method = RequestMethod.GET)
	@ResponseBody
	public void saveClothOrder(HttpServletRequest request, @ModelAttribute("") CustomerOrderModel customerOrderModel,
			HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			String cId = request.getParameter("cId");
			int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
			String ClothTypeID = request.getParameter("ClothTypeID");
			String ServiceTypeID = request.getParameter("ServiceTypeID");
			String PreferecesName = request.getParameter("PreferecesName");
			String orderDate = request.getParameter("orderDate");
			String orderId = request.getParameter("orderId");
			double Rate = Double.parseDouble(request.getParameter("Rate"));
			int Quantity = Integer.parseInt(request.getParameter("Quantity"));
			double Amount = Double.parseDouble(request.getParameter("Amount"));
			CustomerOrderModel_2 customerOrderModel_2 = new CustomerOrderModel_2();
			CustomerDetailModel customerDetailModel = new CustomerDetailModel();
			customerDetailModel = customerServiceInterface.findCustomerID(cId, ShopNameId);
			customerOrderModel.setCustomerDetailModel(customerDetailModel);
			customerOrderModel.setClothType(ClothTypeID);
			customerOrderModel.setServiceType(ServiceTypeID);
			customerOrderModel.setPreferanceType(PreferecesName);
			customerOrderModel.setRate(Rate);
			customerOrderModel.setAmount(Amount);
			customerOrderModel.setQuantity(Quantity);
			customerOrderModel.setOrderDate(orderDate);
			customerOrderModel.setOrderId(orderId);
			customerOrderModel.setInvoiceNo(invoiceNo);

			customerOrderModel_2.setCustomerDetailModel(customerDetailModel);
			customerOrderModel_2.setClothType(ClothTypeID);
			customerOrderModel_2.setServiceType(ServiceTypeID);
			customerOrderModel_2.setPreferanceType(PreferecesName);
			customerOrderModel_2.setRate(Rate);
			customerOrderModel_2.setAmount(Amount);
			customerOrderModel_2.setQuantity(Quantity);
			customerOrderModel_2.setOrderDate(orderDate);
			customerOrderModel_2.setOrderId(orderId);
			customerOrderModel_2.setInvoiceNo(invoiceNo);

			customerOrderModel.setShopId(ShopNameId);
			customerOrderModel_2.setShopId(ShopNameId);
			customerServiceInterface.saveCustomerOrder(customerOrderModel);
			customerServiceInterface.saveCustomerOrder2(customerOrderModel_2);
			System.out.println("okk");
		} else {
			System.out.println("Not Okk");
		}

	}

	// CancelCurrentOrder
	@RequestMapping(value = "CancelCurrentOrder", method = RequestMethod.GET)
	@ResponseBody
	public void deleteOrder(HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			int cId = Integer.parseInt(request.getParameter("cId"));
			String orderId = request.getParameter("orderId");
			String ClothTypeID = request.getParameter("ClothTypeID");
			String ServiceTypeID = request.getParameter("ServiceTypeID");
			int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
			System.out.println("cId " + cId + " ClothTypeID " + ClothTypeID + " ServiceTypeID=> " + ServiceTypeID
					+ " orderId " + orderId + "Shop Id" + ShopNameId + "Invoice No" + invoiceNo);
			customerServiceInterface.deleteOrder(cId, orderId, ClothTypeID, ServiceTypeID, ShopNameId);
		} else {

		}

	}

	// saveCompleteOrder
	@RequestMapping(value = "saveCompleteOrder", method = RequestMethod.POST, params = "GetOrder")
	public ModelAndView saveCompleteOrder(@ModelAttribute("") OrderDetails orderDetails, Model model,
			HttpServletRequest request, HttpSession httpSession, HttpServletResponse response)
			throws JRException, NamingException, SQLException, IOException {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("inside save CompleteOrder details");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			double NewBalance = Double.parseDouble(request.getParameter("NewBalance"));
			double NewWallet = Double.parseDouble(request.getParameter("Wallet"));
			String cId = request.getParameter("cId");
			System.out.println("C Id-> " + cId + "NewBalance-> " + NewBalance + " & New Wallet is " + NewWallet);
			CustomerDetailModel customerDetailModel = new CustomerDetailModel();
			customerDetailModel = customerServiceInterface.findCustomerID(cId, ShopNameId);
			System.out.println("id set -> " + customerDetailModel);
			orderDetails.setCustomerDetailModel(customerDetailModel);
			orderDetails.setShopId(ShopNameId);

			/*
			if (NewWallet > 0) {
				Session session = sessionFactory.openSession();
				SQLQuery query = session.createSQLQuery(
						"update orderdetails o1 set o1.amountRemaining=0,o1.amountPaid=o1.totalAmount where o1.cId="
								+ customerDetailModel.getcId() + " and o1.shopId=" + ShopNameId + " ");
				query.executeUpdate();
				session.close();
				System.out.println("Order Updated...");
			} else {
				if (orderDetails.getAmountPaid() > orderDetails.getTotalAmount()) {
					Double extra = orderDetails.getAmountPaid() - orderDetails.getTotalAmount();
					System.out.println("Extra Amt = " + extra);
					List<Map> ordersList = orderServiceInterface.getOrdersList(customerDetailModel.getcId(),ShopNameId);
					System.out.println(ordersList);

					for (int i = 0; i < ordersList.size(); i++) {
						while (extra > 0.0) {
							Map mp = (Map) ordersList.get(i);
							int oId = (int) mp.get("oId");
							Double rem = (Double) mp.get("amountRemaining");
							Double tot = (Double) mp.get("totalAmount");
							Double paid = (Double) mp.get("amountPaid");
							if (rem <= extra) {
								rem = 0.0;
								paid = tot;
								extra = extra - rem;
							} else {
								if (rem > extra) {
									rem = rem - extra;
									paid = paid + extra;
									extra = 0.0;
								}
							}
							orderServiceInterface.updateOrdersAmount(cId, oId, rem, paid,ShopNameId);
						}
					}
				}
			}
			*/
			if (NewWallet >= NewBalance) {
				System.out.println("NewWallet >= NewBalance");
				NewWallet = NewWallet - NewBalance;
				orderDetails.setAmountPaid(orderDetails.getTotalAmount());
				orderDetails.setAmountRemaining(0);
				NewBalance = 0;
			} else {
				// if(NewBalance > NewWallet) {
				// orderDetails.setAmountRemaining(NewBalance - NewWallet);
				NewBalance = NewBalance - NewWallet;
				orderDetails.setAmountPaid(orderDetails.getAmountPaid() + NewWallet);
				NewWallet = 0;
				// }
			}

			customerServiceInterface.updateAllReamaingAmount(Integer.parseInt(cId), NewBalance, NewWallet,ShopNameId);
			orderDetails.setNotReadyQty(orderDetails.getTotalQuantity());
			customerServiceInterface.saveOrder(orderDetails);
			System.out.println("Order SAVED...");

			SendSmsForGetOrder(orderDetails);

			String filename = "orderRecipts";
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("orderId", orderDetails.getOrderId());
			hm.put("ShopNameId", ShopNameId);
			PrintJasperReport.printreport(filename, request, response, hm);

			/*
			 * String filename = "barcodeGenerator"; HashMap<String, Object> hm = new
			 * HashMap<String, Object>(); hm.put("orderId",orderDetails.getOrderId());
			 * PrintJasperReport.printreport(filename, request, response, hm);
			 */

			return new ModelAndView("SearchCustomerPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	public void SendSmsForGetOrder(OrderDetails orderDetails) {
		String mobile = "";
		String msg = "";
		String msg1 = "";
		String details = "";
		try {
			List<Map> list = orderServiceInterface.getDataForMsgOfGetOrder(orderDetails.getOrderId(),orderDetails.getShopId());
			System.out.println("List is ---> " + list);
			for (int i = 0; i < list.size(); i++) {
				Map mp = (Map) list.get(i);
				System.out.println("Map " + mp);
				details += mp.get("clothType") + " ";
				details += mp.get("serviceType") + " ";
				details += mp.get("quantity") + "%0A";
			}
			System.out.println("details msg - " + details);
			System.out.println("Sending Order Ready SMS");
			mobile = orderDetails.getCustomerDetailModel().getcMobile();
			/*msg1 = orderDetails.getCustomerDetailModel().getShopName()+" Laundry.%0A" + orderDetails.getCustomerDetailModel().getGender() + " "
					+ orderDetails.getCustomerDetailModel().getcName() + "%0AIn.No : " + orderDetails.getInvoiceNo()
					+ " Tot Qty : " + orderDetails.getTotalQuantity() + "%0A" + details + "Tot Amt :"
					+ orderDetails.getTotalAmount() + "%0APaid Amt :" + orderDetails.getAmountPaid() + "%0ARem Amt:"
					+ orderDetails.getAmountRemaining() + "%0AYour Due Date is " + orderDetails.getDueDate()
					+ "%0AThank You.."; */

			msg = "Perclean Laundry.%0A"+ orderDetails.getCustomerDetailModel().getGender()+" "+ orderDetails.getCustomerDetailModel().getcName() +"%0AIn.No:"+ orderDetails.getInvoiceNo()+" Tot Qty:" + orderDetails.getTotalQuantity()  
					+ "%0A" + details +"Tot Amt:"+ orderDetails.getTotalAmount()
					+ "%0APaid Amt:"+ orderDetails.getAmountPaid()
					+ "%0AYour Due Date is "+ orderDetails.getDueDate()
					+ "%0AThank You..";
			
			System.out.println("Invoice No " + orderDetails.getInvoiceNo() + " mobile : " + mobile + " msg " + msg);
			STCOPSMS stcopsms = new STCOPSMS();
			stcopsms.sendSMS(mobile, msg);
			System.out.println("msg send success..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/// ===================== Not in Use Now =================

	@RequestMapping(value = "saveCompleteOrder", method = RequestMethod.POST, params = "OrderSlip")
	public ModelAndView getOrderSlip(OrderDetails orderDetails, Model model, HttpServletRequest request,
			HttpSession httpSession, HttpServletResponse response)
			throws JRException, NamingException, SQLException, IOException {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {

			System.out.println("inside OrderSlip report");
			String orderId = request.getParameter("orderId");
			System.out.println("orderId:" + orderId);

			String filename = "orderRecipts";
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("orderId", orderId);
			PrintJasperReport.printreport(filename, request, response, hm);

			return new ModelAndView("SearchCustomerPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	//
	@RequestMapping(value = "ReturnOrderPage")
	public ModelAndView ReturnOrderPage(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("ReturnOrderPage");
			int invoiceNo = Integer.parseInt(request.getParameter("i"));
			int totalQuantity = Integer.parseInt(request.getParameter("q"));
			String orderId = request.getParameter("o");
			String customerId = request.getParameter("c");
			System.out.println("invoiceNo " + invoiceNo + " orderId " + orderId + " customerId " + customerId);
			List<String> ClothList = orderServiceInterface.getClothListForReturn(invoiceNo, orderId, customerId);
			model.addAttribute("ClothList", ClothList);
			model.addAttribute("invoiceNo", invoiceNo);
			model.addAttribute("customerId", customerId);
			model.addAttribute("orderId", orderId);
			model.addAttribute("totalQuantity", totalQuantity);
			return new ModelAndView("ReturnOrderPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// getServiceTypeListForReturn
	@RequestMapping(value = "getServiceTypeListForReturn", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getServiceTypeListForReturn(HttpServletRequest request) {
		String ClothType = request.getParameter("ClothType");
		String orderId = request.getParameter("orderId");
		int cId = Integer.parseInt(request.getParameter("cId"));

		System.out.println("cId=> " + cId);
		List<String> list = new ArrayList<>();
		list = orderServiceInterface.getServiceTypeList(orderId, ClothType, cId);
		System.out.println(list);
		return list;
	}

	// ========== getQuantityForReturn ===========
	@RequestMapping(value = "getQuantityForReturn", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getQuantityForReturn(HttpServletRequest request) {
		String ClothTypeID = request.getParameter("ClothTypeID");
		String orderId = request.getParameter("orderId");
		String ServiceTypeID = request.getParameter("ServiceTypeID");
		System.out.println("ServiceTypeID => " + ServiceTypeID);
		List<String> list = new ArrayList<>();
		list = orderServiceInterface.getQuantityForReturn(orderId, ClothTypeID, ServiceTypeID);
		System.out.println(list);
		return list;
	}

	/*
	 * @RequestMapping(value = "saveReturnOrderEntry", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public void saveReturnOrderEntry(HttpServletRequest request,
	 * 
	 * @ModelAttribute("") ReturnOrderEntryModel returnOrderEntryModel) { String cId
	 * = request.getParameter("cId"); CustomerDetailModel customerDetailModel = new
	 * CustomerDetailModel(); customerDetailModel =
	 * customerServiceInterface.findCustomerID(cId);
	 * returnOrderEntryModel.setCustomerDetailModel(customerDetailModel);
	 * orderServiceInterface.saveReturnOrderEntry(returnOrderEntryModel);
	 * System.out.println("okk"); }
	 * 
	 * @RequestMapping(value = "saveReturnOrder", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public void saveReturnOrder(HttpServletRequest
	 * request, @ModelAttribute("") ReturnOrderDetails returnOrderDetails) {
	 * 
	 * String cId = request.getParameter("cId");
	 * 
	 * CustomerDetailModel customerDetailModel = new CustomerDetailModel();
	 * customerDetailModel = customerServiceInterface.findCustomerID(cId);
	 * returnOrderDetails.setCustomerDetailModel(customerDetailModel);
	 * orderServiceInterface.saveReturnOrder(returnOrderDetails);
	 * System.out.println("okk"); }
	 */
	// deliverClothsOrder
	@RequestMapping(value = "deliverClothsOrder", method = RequestMethod.GET)
	public ModelAndView deliverClothsOrder(Model model, HttpServletRequest request, HttpSession httpSession,
			HttpServletResponse response) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			System.out.println("inside deliver ClothsOrder details");
			String custom_radio_1 = request.getParameter("custom_radio_1");
			double balanceAmt = Double.parseDouble(request.getParameter("balanceAmt"));
			double paidAmt = Double.parseDouble(request.getParameter("paidAmt"));
			String cId = request.getParameter("cId2");

			System.out.println("custom_radio_1 " + custom_radio_1 + " C Id-> " + cId + " balanceAmt " + balanceAmt
					+ " paidAmt-> " + paidAmt);
			CustomerDetailModel customerDetailModel = new CustomerDetailModel();
			customerDetailModel = customerServiceInterface.findCustomerID(cId, ShopNameId);
			System.out.println("id set -> " + customerDetailModel);
			orderServiceInterface.updateBalance(balanceAmt, paidAmt, cId);
			System.out.println("Order Delivered...");

			return new ModelAndView("SearchCustomerPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}

	//

}
