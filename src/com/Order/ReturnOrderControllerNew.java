package com.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Barcode.BarcodeServiceInterface;
import com.model.OrderDetails;
import com.reports.ReportServiceInterface;

@Controller
public class ReturnOrderControllerNew {

	@Autowired
	OrderServiceInterface orderServiceInterface;
	
	@Autowired
	BarcodeServiceInterface barcodeServiceInterface;
	@Autowired
	ReportServiceInterface reportServiceInterface;
	@Autowired
	SessionFactory sessionFactory;
	
	//Customer_NotReadyPage
	@RequestMapping(value="Customer_NotReadyPage")
	public ModelAndView Customer_NotReadyPage(HttpServletRequest request, HttpSession httpSession, Model model){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("CustomerDetailsPage");
			int invoiceNo = Integer.parseInt(request.getParameter("i"));
			String orderId = request.getParameter("o");
			int cId = Integer.parseInt(request.getParameter("c"));
			System.out.println("i= "+invoiceNo+" o= "+orderId+" c= "+cId);
			List <String> CustomerInfo = orderServiceInterface.getCustomerInfo(cId);
			List <String> AllOrderDetails = orderServiceInterface.getAllOrderDetails(invoiceNo,orderId);
			
			//System.out.println("AllOrderDetails "+AllOrderDetails);
			model.addAttribute("CustomerInfo", CustomerInfo);
			model.addAttribute("AllOrderDetails", AllOrderDetails);
			return new ModelAndView("Customer_NotReadyPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// JSON
	// getOrderChiAllDetail
	@RequestMapping(value = "getOrderChiAllDetail", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getOrderChiAllDetail(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
		System.out.println("orderId=> " + orderId + " invoiceNo " + invoiceNo);
		List<String> list = new ArrayList<>();
		list = orderServiceInterface.getOrderChiAllDetail(orderId, invoiceNo);
		System.out.println(list);
		return list;
	}

	// JSON
	// getOrderChiAllDetail
	@RequestMapping(value = "getOrderChiDetailNew", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getOrderChiDetailNew(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
		System.out.println("orderId=> " + orderId + " invoiceNo " + invoiceNo);
		List<String> list = new ArrayList<>();
		list = orderServiceInterface.getOrderChiDetailNew(orderId, invoiceNo);
		System.out.println(list);
		return list;
	}
	
	//SaveReadyClothsInOrder
	@RequestMapping(value = "SaveReadyClothsInOrder", params = "SaveReadyOrder", method = RequestMethod.POST)
	public ModelAndView SaveReadyClothsInOrder(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			try {
				System.out.println("Check Uncheck");
				int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
				String orderId = request.getParameter("orderId");
				String[] selectedClothOrderIds = request.getParameterValues("SelectedId");
				int cId = Integer.parseInt(request.getParameter("cId2"));

				orderServiceInterface.updateClothReadyStatus(selectedClothOrderIds, invoiceNo, orderId);
				List<String> CustomerInfo = orderServiceInterface.getCustomerInfo(cId);
				List<String> AllOrderDetails = orderServiceInterface.getAllOrderDetails(invoiceNo, orderId);
				model.addAttribute("CustomerInfo", CustomerInfo);
				model.addAttribute("AllOrderDetails", AllOrderDetails);
				return new ModelAndView("Customer_NotReadyPage");
			} catch (Exception e) {
				model.addAttribute("Message", e);
				return new ModelAndView("mainLoginPage");
			}
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	//
	//SaveReadyClothsInOrder
	@RequestMapping(value = "SaveReadyClothsInOrder",params = "DeliverClothOrder", method = RequestMethod.POST)
	public ModelAndView DeleverReadyClothsInOrder(Model model, HttpServletRequest request, 
			 HttpSession httpSession, HttpServletResponse response) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("inside deliver ClothsOrder details");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			System.out.println("Check Uncheck");
			int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
			String cMobile = request.getParameter("cMobile2");
			String orderId = request.getParameter("orderId");
			String[] selectedClothOrderIds = request.getParameterValues("SelectedId");
			String custom_radio_1 = request.getParameter("custom_radio_1");
			String paymentMode = request.getParameter("paymentMode");
			String discription = request.getParameter("discription");
			double balanceAmt = Double.parseDouble(request.getParameter("balanceAmt"));
			double paidAmt = Double.parseDouble(request.getParameter("paidAmt"));
			int cId=Integer.parseInt(request.getParameter("cId2"));
			String CId = request.getParameter("cId2");
			
			Criteria criteria = null;
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(OrderDetails.class);
			Criterion cr1 = Restrictions.eq("orderId", orderId);
			criteria.add(cr1);
			OrderDetails orderDetails = (OrderDetails) criteria.setMaxResults(1).uniqueResult();
			/*
			if (paidAmt > balanceAmt) {
				session = sessionFactory.openSession();
				SQLQuery query = session.createSQLQuery(
						"update orderdetails o1 set o1.amountRemaining=0,o1.amountPaid=o1.totalAmount where o1.cId=" + cId + " ");
				query.executeUpdate();
				session.close();
				System.out.println("Order Updated...");
			} else {
				
				if (orderDetails.getAmountPaid() > orderDetails.getTotalAmount()) {
					Double extra = orderDetails.getAmountPaid() - orderDetails.getTotalAmount();
					System.out.println("Extra Amt = " + extra);
					List<Map> ordersList = orderServiceInterface.getOrdersList(cId,ShopNameId);
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
							orderServiceInterface.updateOrdersAmount(CId,oId,rem,paid,ShopNameId);
						}
					}
				}
			}
			*/
			System.out.println("custom_radio_1 "+custom_radio_1+" C Id-> "+cId+" balanceAmt "+balanceAmt+" paidAmt-> "+paidAmt);
			orderServiceInterface.updateClothDeliveredStatus(selectedClothOrderIds,invoiceNo,orderId,balanceAmt,paidAmt,cId);
			System.out.println("Order Delivered...");
			
			System.out.println("CustomerContact "+cMobile);
			List <String> CustomerInfo = orderServiceInterface.getCustomerInfo(cMobile,ShopNameId);
			List <String> AllOrderDetails = orderServiceInterface.getAllOrderDetails(cMobile);
			System.out.println("AllOrderDetails "+AllOrderDetails);
			model.addAttribute("CustomerInfo", CustomerInfo);
			model.addAttribute("AllOrderDetails", AllOrderDetails);
			return new ModelAndView("CustomerDetailsPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	//payRemAmt
	@RequestMapping(value = "payRemAmt", params = "btnPayRemAmt", method = RequestMethod.GET)
	public ModelAndView payRemAmt(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			int cId = Integer.parseInt(request.getParameter("cId2"));
			Double nowPaidAmt = Double.parseDouble(request.getParameter("nowPaidAmt"));
			String orderId = request.getParameter("oId2");
			System.out.println("cId "+cId+" orderId "+orderId+" nowPaidAmt "+nowPaidAmt);
			
			orderServiceInterface.payRemAmt(cId,nowPaidAmt,orderId);
			
			List<String> AllOrderList = reportServiceInterface.getAllOrderList(ShopNameId);
			model.addAttribute("AllOrderList", AllOrderList);
			return new ModelAndView("AllOrderReportPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
}
