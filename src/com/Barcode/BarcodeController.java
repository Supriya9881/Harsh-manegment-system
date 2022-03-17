package com.Barcode;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.SendSMS.STCOPSMS;
import com.admin.AdminServiceInterface;
import com.customer.CustomerServiceInterface;
import com.model.CustomerDetailModel;
import com.model.OrderDetails;
import com.reports.ReportServiceInterface;

import net.sf.jasperreports.engine.JRException;
import util.PrintJasperReport;

@Controller
public class BarcodeController {

	@Autowired
	CustomerServiceInterface customerServiceInterface;
	@Autowired
	AdminServiceInterface adminServiceInterface;
	@Autowired
	BarcodeServiceInterface barcodeServiceInterface;
	@Autowired
	ReportServiceInterface reportServiceInterface;

	// PrintBarcodePage
	@RequestMapping("PrintBarcode")
	public ModelAndView PrintBarcodePage(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			System.out.println("PrintBarcodePage");
			List<String> AllOrderList = reportServiceInterface.getAllOrderList(ShopNameId);
			model.addAttribute("AllOrderList", AllOrderList);
			return new ModelAndView("PrintBarcodePage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// printAllBrcodeOfOrder
	@RequestMapping(value = "printAllBrcodeOfOrder", method = RequestMethod.GET)
	public ModelAndView printOrderOfOrder(OrderDetails orderDetails, Model model, HttpServletRequest request,
			HttpSession httpSession, HttpServletResponse response)
			throws JRException, NamingException, SQLException, IOException {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {

			System.out.println("inside barCode report");
			String orderId = request.getParameter("orderId");

			String filename = "NewBarcode";
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("orderId", orderId);
			PrintJasperReport.printreport(filename, request, response, hm);

			return new ModelAndView("GetOrderPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}

//================== GenerateBarcodeUsingOrderId ======================
	@RequestMapping(value = "GenerateBarcodeUsingOrderId", method = RequestMethod.POST)
	public ModelAndView GenerateBarcodeUsingOrderId(OrderDetails orderDetails, Model model, HttpServletRequest request,
			HttpSession httpSession, HttpServletResponse response)
			throws JRException, NamingException, SQLException, IOException {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {

			System.out.println("inside barCode report");
			String orderId = request.getParameter("orderId");

			String filename = "barcodeGenerator";
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("orderId", orderId);
			/* hm.put("orderId",orderId); */
			PrintJasperReport.printreport(filename, request, response, hm);

			return new ModelAndView("GetOrderPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}

//====================== sendReadySMS.html ========================= 	
	@RequestMapping(value = "sendReadySMS", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView sendReadySMS(Model model, HttpServletRequest request, HttpSession httpSession)
			throws IOException {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");

		System.out.println(UsName + " is User");
		String mobile = "";
		String msg = "";
		if (UsName != null) {
			try {
				System.out.println("Sending Order Ready SMS");
				String orderId = request.getParameter("orderId");
				// mobile = request.getParameter("mobile");
				// msg = request.getParameter("sms");
				System.out.println("orderId " + orderId + " mobile : " + mobile + " msg " + msg);
				// customerServiceInterface.updateOrderStatus(orderId);

				STCOPSMS stcopsms = new STCOPSMS();

				stcopsms.sendSMS(mobile, msg);

				System.out.println("msg send success..");
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<String> allOrders;
			allOrders = customerServiceInterface.getAllOrdersList();
			model.addAttribute("allOrders", allOrders);
			// System.out.println("ALl Orders : "+allOrders);
			return new ModelAndView("AllOrdersPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}

	// PrintOutsideBarcode
	@RequestMapping("PrintOutsideBarcode")
	public ModelAndView PrintOutsideBarcode(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("PrintOutsideBarcodePage");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> AllOrderList = reportServiceInterface.getAllOrderList(ShopNameId);
			model.addAttribute("AllOrderList", AllOrderList);
			return new ModelAndView("PrintOutsideBarcodePage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// printOutsideServesesBarcode
	@RequestMapping(value = "printOutsideServesesBarcode", method = RequestMethod.GET)
	public ModelAndView printOutsideServesesBarcode(OrderDetails orderDetails, Model model, HttpServletRequest request,
			HttpSession httpSession, HttpServletResponse response)
			throws JRException, NamingException, SQLException, IOException {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			System.out.println("inside barCode report");
			String orderId = request.getParameter("orderId");
			System.out.println("ShopNameId - "+ShopNameId+" orderId - "+orderId);
			String filename = "NewOutsideBarcode";
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("orderId", orderId);
			hm.put("ShopId", ShopNameId);
			PrintJasperReport.printreport(filename, request, response, hm);

			return new ModelAndView("PrintOutsideBarcodePage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
}
