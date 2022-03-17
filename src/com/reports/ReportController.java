package com.reports;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.customer.CustomerServiceInterface;
import com.model.CustomerDetailModel;
import com.model.CustomerOrderModel;
import com.model.OrderDetails;
import com.model.master.ClothTypeModel;

import net.sf.jasperreports.engine.JRException;
import util.PrintJasperReport;

@Controller
public class ReportController {

	@Autowired
	ReportServiceInterface reportServiceInterface;
	
	@Autowired
	CustomerServiceInterface customerServiceInterface;
	
	List<String> keyword = new ArrayList<>();
	
		
	@RequestMapping("/searchOrderId")
	@ResponseBody
	public List<String> autoOrderIdcomplete(HttpServletRequest requet) {
		keyword = reportServiceInterface.searchOrderId(requet.getParameter("term")); // Calling Autocomplete method
		return keyword;
	}
	
	// Calling searchOrderDate Autocomplete ==========
	@RequestMapping("/searchOrderDate")
	@ResponseBody
	public List<String> searchOrderDate(HttpServletRequest request, HttpSession httpSessionMob) {
		httpSessionMob = request.getSession();
		String UsMobile = (String) httpSessionMob.getAttribute("SessionMobileNo");

		keyword = reportServiceInterface.searchOrderDate(request.getParameter("term"), UsMobile); // Calling
																									// Autocomplete
		return keyword;
	}
			
	// Json get get Rate From Customer Order page
	@RequestMapping(value = "setMobileNoAsSession", method = RequestMethod.GET)
	@ResponseBody
	public void deleteOrder(HttpServletRequest request, HttpSession httpSessionMob) {
		String MobileNo = request.getParameter("MobileNo");
		httpSessionMob = request.getSession();
		httpSessionMob.setAttribute("SessionMobileNo", MobileNo);
		String UsMobile = (String) httpSessionMob.getAttribute("SessionMobileNo");
		System.out.println("MobileNo " + MobileNo + " UsMobile " + UsMobile);
	}	
	
	
	
	@RequestMapping(value = "AllOrdersReport")
	public ModelAndView AllOrdersReport(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			return new ModelAndView("AllOrdersReportView");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	@RequestMapping(value = "getAllOrdersReport",params="DateWiseReport")
	public ModelAndView DateWiseReport(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ParseException, JRException, NamingException, SQLException, IOException {
		System.out.println("IN SIDE get Mobile No Wise Report");
		HttpSession httpSession;
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			String orderDate = request.getParameter("orderDate");
			System.out.println("orderDate " + orderDate);
			// use for jasper page report
			String filename1 = "DateWiseOrdersReport";
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("orderDate", orderDate);

			System.out.println(hm);
			PrintJasperReport.printreport(filename1, request, response, hm);
			// end
			return new ModelAndView("AllOrdersReportView");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	@RequestMapping(value = "getAllOrdersReport",params="AllReportReport")
	public ModelAndView AllReportReport(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ParseException, JRException, NamingException, SQLException, IOException {
		System.out.println("IN SIDE get Mobile No Wise Report");
		HttpSession httpSession;
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			// use for jasper page report
			String filename1 = "AllOrdersReport";
			HashMap<String, Object> hm = new HashMap<>();
			System.out.println(hm);
			PrintJasperReport.printreport(filename1, request, response, hm);
			// end
			return new ModelAndView("AllOrdersReportView");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	//******Order Slip Report**********	
		@RequestMapping(value = "getOrderSlip")
		public ModelAndView getOrderSlip( OrderDetails orderDetails,Model model, HttpServletRequest request, 
				 HttpSession httpSession, HttpServletResponse response) throws JRException, NamingException, SQLException, IOException{
			httpSession = request.getSession();
			String UsName = (String) httpSession.getAttribute("UsernameAdmin");
			System.out.println(UsName + " is User");
			if (UsName != null) {
		
				System.out.println("inside OrderSlip report");		
				String orderId=request.getParameter("qrOrderId");
				System.out.println("qrOrderId:"+orderId);
				
				  String filename = "orderRecipts";
				  HashMap<String, Object> hm = new HashMap<String, Object>();
				  hm.put("orderId",orderId); 
				  PrintJasperReport.printreport(filename, request, response, hm);
				
				return new ModelAndView("GetOrderPage");
			} else {
				System.out.println("Invalid Admin...");
				model.addAttribute("Message", "First Login Your Account...");
				return new ModelAndView("accessD");
			}
		}
		
		
		
		@RequestMapping(value = "GenerateBarcodeUsingOrderIdPage")
		public ModelAndView OrderIdWiseGenerateBarcode(Model model, HttpServletRequest request, HttpSession httpSession) {
			httpSession = request.getSession();
			String UsName = (String) httpSession.getAttribute("UsernameAdmin");
			System.out.println(UsName + " is User");
			if (UsName != null) {
				return new ModelAndView("GenerateBarcodeUsingOrderIdPage");
			} else {
				System.out.println("Invalid Admin...");
				model.addAttribute("Message", "First Login Your Account...");
				return new ModelAndView("accessD");
			}
		}

		
		//allReports
		@RequestMapping(value = "AllReports") //allReports
		public ModelAndView allReports(Model model, HttpServletRequest request, HttpSession httpSession) {
			httpSession = request.getSession();
			String UsName = (String) httpSession.getAttribute("UsernameAdmin");
			System.out.println(UsName + " is User");
			if (UsName != null) {
				//return new ModelAndView("allReports");
				return new ModelAndView("allReportsNew");
			} else {
				System.out.println("Invalid Admin...");
				model.addAttribute("Message", "First Login Your Account...");
				return new ModelAndView("accessD");
			}
		}
		

		
	
		
}
