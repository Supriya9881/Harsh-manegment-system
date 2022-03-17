package com.promotion;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SendSMS.STCOPSMS;
import com.customer.CustomerServiceInterface;
import com.model.CustomerDetailModel;

@Controller
public class PromotionController {

	@Autowired
	CustomerServiceInterface customerServiceInterface;
	
	@Autowired
	SessionFactory sessionFactory;

	// greetingMessagePage.html
	@RequestMapping("greetingMessagePage")
	public ModelAndView greetingMessagePage(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("greetingMessagePage");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> CustomerList;
			CustomerList = customerServiceInterface.getCustomerList(ShopNameId);
			model.addAttribute("CustomerList", CustomerList);
			return new ModelAndView("greetingMessagePage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// sendPromotionMessage
	@RequestMapping(value = "sendPromotionMessage", params = "btnSendToSelectedCustomer", method = RequestMethod.POST)
	public ModelAndView btnSendToSelectedCustomer(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			try {
				System.out.println("Check Uncheck");
				String cMobile = "";
				String msg = request.getParameter("msg");
				System.out.println("Message is : " + msg);
				String[] selectedClothOrderIds = request.getParameterValues("SelectedId");
				System.out.println("Selected Customers id");
				for (int i = 0; i < selectedClothOrderIds.length; i++) {
					System.out.println(selectedClothOrderIds[i]);
				}
					for (int i = 0; i < selectedClothOrderIds.length; i++) {
						cMobile = selectedClothOrderIds[i];
						Criteria criteria = null;
						Session session = sessionFactory.openSession();
						criteria = session.createCriteria(CustomerDetailModel.class);
						Criterion cr1 = Restrictions.eq("cMobile", cMobile);
						criteria.add(cr1);
						CustomerDetailModel customerDetailModel = (CustomerDetailModel) criteria.setMaxResults(1).uniqueResult();
						String newMsg = "Dear "+customerDetailModel.getGender()+" "+customerDetailModel.getcName()+"%0A"+msg+"%0APerClean Laundry.";
						System.out.println("mobile : " + cMobile + " newMsg " + newMsg);
						STCOPSMS stcopsms = new STCOPSMS();
						stcopsms.sendSMS(cMobile, newMsg);
						System.out.println("msg sended to "+cMobile);
					}
				
				List<String> CustomerList;
				CustomerList = customerServiceInterface.getCustomerList(ShopNameId);
				model.addAttribute("CustomerList", CustomerList);
				return new ModelAndView("greetingMessagePage");
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

}
