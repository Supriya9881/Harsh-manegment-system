package com.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.admin.AdminServiceInterface;
import com.customer.CustomerServiceInterface;

@Controller
public class MainController {

	@Autowired
	AdminServiceInterface adminServiceInterface;
	
	@Autowired
	CustomerServiceInterface customerServiceInterface;
	
	//mainLoginPage
	@RequestMapping("mainLoginPage")
	public ModelAndView mainLoginPage(HttpServletRequest request, HttpSession httpSession, Model model){
		System.out.println("mainLoginPage");
		return new ModelAndView("mainLoginPage");
	}
	
	//forgatePasswordPage
	@RequestMapping("forgatePasswordPage")
	public ModelAndView forgatePasswordPage(HttpServletRequest request, HttpSession httpSession, Model model){
		System.out.println("forgatePasswordPage");
		return new ModelAndView("forgatePasswordPage");
	}
	
	//RegisterPage
	@RequestMapping("RegisterPage")
	public ModelAndView RegisterPage(HttpServletRequest request, HttpSession httpSession, Model model){
		System.out.println("RegisterPage");
		return new ModelAndView("RegisterPage");
	}
		
		//LockScreen
		@RequestMapping("LockScreen")
		public ModelAndView LockScreen(HttpServletRequest request, HttpSession httpSession, Model model){
			System.out.println("LockScreen");
			return new ModelAndView("LockScreenPage");
		}
		
		
		//AddEmployeePage
		@RequestMapping("AddEmployeePage")
		public ModelAndView AddEmployeePage(HttpServletRequest request, HttpSession httpSession, Model model){
			httpSession = request.getSession();
			String UsName = (String) httpSession.getAttribute("UsernameAdmin");
			System.out.println(UsName + " is User");
			if (UsName != null) {
				System.out.println("AddEmployeePage");
				return new ModelAndView("AddEmployeePage");
			}else {
				System.out.println("Invalid Admin...");
				model.addAttribute("Message", "First Login Your Account...");
				return new ModelAndView("mainLoginPage");
			}
		}
}
