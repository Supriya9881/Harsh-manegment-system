package com.Developer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.master.OwnerDetailsModel;
import com.model.master.ShopDetailsModel;

@Controller
public class DeveloperController {
	
	@Autowired
	DeveloperServiceInterface developerServiceInterface; 

	//devloperMasterPage.html
	@RequestMapping(value = "devloperMasterPage", method = RequestMethod.GET)
	public ModelAndView devloperMasterPage(Model model,HttpServletRequest request,HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		String Pwd = (String) httpSession.getAttribute("PasswordAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			if(UsName.equals("Vishal@1234") && Pwd.equals("Raj@1234")) {
				System.out.println("Home Page To Show");
				
				List<String> ShopNameList,OwnerNameList;
				ShopNameList = developerServiceInterface.getShopNameList();
				OwnerNameList = developerServiceInterface.getOwnerNameList();
				model.addAttribute("developerPage", "Developer Page");
				model.addAttribute("ShopNameList", ShopNameList);
				model.addAttribute("OwnerNameList", OwnerNameList);
				return new ModelAndView("devloperMasterPage");
			}else {
				System.out.println("Invalid Admin...");
				model.addAttribute("Message", "Invalid Developer");
				return new ModelAndView("mainLoginPage");
			}
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	// SaveShopNameDetails
	@RequestMapping(value = "SaveShopNameDetails", params = "btnSaveShopDetails", method = RequestMethod.POST)
	public ModelAndView SaveShopNameDetails(Model model, HttpServletRequest request, HttpSession httpSession,
			@ModelAttribute("") ShopDetailsModel shopDetailsModel) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		String Pwd = (String) httpSession.getAttribute("PasswordAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			if (UsName.equals("Vishal@1234") && Pwd.equals("Raj@1234")) {
				System.out.println("Home Page To Show");
				developerServiceInterface.saveShopDetails(shopDetailsModel);
				List<String> ShopNameList,OwnerNameList;
				ShopNameList = developerServiceInterface.getShopNameList();
				OwnerNameList = developerServiceInterface.getOwnerNameList();
				model.addAttribute("developerPage", "Developer Page");
				model.addAttribute("ShopNameList", ShopNameList);
				model.addAttribute("OwnerNameList", OwnerNameList);
				return new ModelAndView("devloperMasterPage");
			} else {
				System.out.println("Invalid Admin...");
				model.addAttribute("Message", "Invalid Developer");
				return new ModelAndView("mainLoginPage");
			}
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// SaveShopNameDetails
	@RequestMapping(value = "SaveOwnerDetails", params = "btnSaveOwnerDetails", method = RequestMethod.POST)
	public ModelAndView SaveOwnerDetails(Model model, HttpServletRequest request, HttpSession httpSession,
			@ModelAttribute("") OwnerDetailsModel ownerDetailsModel) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		String Pwd = (String) httpSession.getAttribute("PasswordAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			if (UsName.equals("Vishal@1234") && Pwd.equals("Raj@1234")) {
				System.out.println("Home Page To Show");
				int ShopNameId = Integer.parseInt(request.getParameter("ShopNameId"));
				ShopDetailsModel shopDetailsModel = new ShopDetailsModel();
				shopDetailsModel = developerServiceInterface.findShopNameID(ShopNameId);
				ownerDetailsModel.setShopDetailsModel(shopDetailsModel);
				ownerDetailsModel.setShopName(shopDetailsModel.getShopName());
				developerServiceInterface.saveOwnerDetails(ownerDetailsModel);
				
				List<String> ShopNameList,OwnerNameList;
				ShopNameList = developerServiceInterface.getShopNameList();
				OwnerNameList = developerServiceInterface.getOwnerNameList();
				model.addAttribute("developerPage", "Developer Page");
				model.addAttribute("ShopNameList", ShopNameList);
				model.addAttribute("OwnerNameList", OwnerNameList);
				return new ModelAndView("devloperMasterPage");
			} else {
				System.out.println("Invalid Admin...");
				model.addAttribute("Message", "Invalid Developer");
				return new ModelAndView("mainLoginPage");
			}
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
}
