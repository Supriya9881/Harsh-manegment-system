 package com.Masters;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.Developer.DeveloperServiceInterface;
import com.model.master.ClothTypeModel;
import com.model.master.PreferanceModel;
import com.model.master.RateMasterModel;
import com.model.master.ServiceTypeModel;

@Controller
public class MastersController {

	@Autowired
	MasterServiceInterface masterServiceInterface;

	@Autowired
	DeveloperServiceInterface developerServiceInterface;

	////////////////////////////////////////// PreferencesMasterPage
	////////////////////////////////////////// START///////////////////////////////////////////////

	@RequestMapping("PreferencesMasterPage")
	public ModelAndView PreferencesMasterPage(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("PreferencesMasterPage");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			List<String> preferanceList;
			preferanceList = masterServiceInterface.getPreferanceList(ShopNameId);
			model.addAttribute("preferanceList", preferanceList);
			return new ModelAndView("PreferencesMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// Save Preferance Type
	@RequestMapping(value = "SaveUpdateDeletePreferanceType", params = "btnAdd", method = RequestMethod.POST)
	public ModelAndView SavePreferanceType(Model model, @ModelAttribute("") PreferanceModel preferanceModel,
			HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("SavePreferance details");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			PreferanceModel preferanceModel1 = masterServiceInterface
					.getDuplicatePreferance(preferanceModel.getPreferanceName(), ShopNameId);
			if (preferanceModel1 == null) {
				// ShopDetailsModel shopDetailsModel = new ShopDetailsModel();
				// shopDetailsModel = developerServiceInterface.findShopNameID(ShopNameId);
				preferanceModel.setShopId(ShopNameId);
				masterServiceInterface.savePreferance(preferanceModel);
				List<String> preferanceList;
				preferanceList = masterServiceInterface.getPreferanceList(ShopNameId);
				model.addAttribute("preferanceList", preferanceList);
			} else {
				model.addAttribute("errorMsgPreferance", "Preferance is already added");
				List<String> preferanceList;
				preferanceList = masterServiceInterface.getPreferanceList(ShopNameId);
				model.addAttribute("preferanceList", preferanceList);
			}
			return new ModelAndView("PreferencesMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// Update Preferance Type
	@RequestMapping(value = "SaveUpdateDeletePreferanceType", params = "btnUpdate", method = RequestMethod.POST)
	public ModelAndView UpdatePreferanceType(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("UpdatePreferanceType");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			int preferanceId = Integer.parseInt(request.getParameter("preferanceId"));
			String preferanceName = request.getParameter("preferanceName");
			double preferancePrice = Double.parseDouble(request.getParameter("preferancePrice"));
			masterServiceInterface.updatePreferanceType(preferanceId, preferanceName, preferancePrice, ShopNameId);
			List<String> preferanceList;
			preferanceList = masterServiceInterface.getPreferanceList(ShopNameId);
			model.addAttribute("preferanceList", preferanceList);
			return new ModelAndView("PreferencesMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// Delete Preferance Type
	@RequestMapping(value = "SaveUpdateDeletePreferanceType", params = "btnDelete", method = RequestMethod.POST)
	public ModelAndView DeletePreferanceType(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("DeletePreferanceType");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			int preferanceId = Integer.parseInt(request.getParameter("preferanceId"));
			masterServiceInterface.deletePreferanceType(preferanceId, ShopNameId);
			List<String> preferanceList;
			preferanceList = masterServiceInterface.getPreferanceList(ShopNameId);
			model.addAttribute("preferanceList", preferanceList);
			return new ModelAndView("PreferencesMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	////////////////////////////////////////// PreferencesMasterPage
	////////////////////////////////////////// END///////////////////////////////////////////////

	////////////////////////////////////////// ClothTypeMasterPage
	////////////////////////////////////////// ///////////////////////////////////////////////////

	@RequestMapping("ClothTypeMasterPage")
	public ModelAndView ClothTypeMasterPage(HttpServletRequest request, HttpSession httpSession, Model model) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("ClothTypeMasterPage");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			return getAllDataLists(model, ShopNameId);
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// SaveUpdateDeleteClothType
	@RequestMapping(value = "SaveClothType", params = "btnAddCloth", method = RequestMethod.POST)
	public ModelAndView saveClothType(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("saveClothType");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			ClothTypeModel clothTypeModel = new ClothTypeModel();
			clothTypeModel.setClothType(request.getParameter("clothType"));
			ClothTypeModel clothTypeModel1 = masterServiceInterface.getDuplicateClothType(clothTypeModel.getClothType(),
					ShopNameId);
			if (clothTypeModel1 == null) {
				// ShopDetailsModel shopDetailsModel = new ShopDetailsModel();
				// shopDetailsModel = developerServiceInterface.findShopNameID(ShopNameId);
				clothTypeModel.setShopId(ShopNameId);
				masterServiceInterface.saveCloths(clothTypeModel);
			} else {
				model.addAttribute("errorMsgClothType", "ClothType is already added");
			}
			return getAllDataLists(model, ShopNameId);
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	@RequestMapping(value = "SaveClothType", params = "btnUpdateCloth", method = RequestMethod.POST)
	public ModelAndView updateClothType(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Update Cloth Type");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			int clothId = Integer.parseInt(request.getParameter("clothTypeID"));
			String clothTypeName = request.getParameter("clothType");
			masterServiceInterface.updateClothType(clothId, clothTypeName, ShopNameId);
			return getAllDataLists(model, ShopNameId);
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	@RequestMapping(value = "SaveClothType", params = "btnDeleteCloth", method = RequestMethod.POST)
	public ModelAndView deleteClothType(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Delete ClothType");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			int clothId = Integer.parseInt(request.getParameter("clothTypeID"));
			masterServiceInterface.deleteClothType(clothId, ShopNameId);
			return getAllDataLists(model, ShopNameId);
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	////////////////////////////////////////// Cloth Type MasterPage
	////////////////////////////////////////// END///////////////////////////////////////////////

	//////////////////////////////////////////// serviceTypeName
	//////////////////////////////////////////// /////////////////////////////////////////////////////
	@RequestMapping(value = "SaveServiceType", params = "btnAddService", method = RequestMethod.POST)
	public ModelAndView saveServiceType(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("saveServiceType");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			ServiceTypeModel serviceTypeModel = new ServiceTypeModel();
			serviceTypeModel.setServiceTypeName(request.getParameter("serviceTypeName"));
			serviceTypeModel.setServiceType(request.getParameter("Service"));
			ServiceTypeModel serviceTypeModel1 = masterServiceInterface.getDuplicateServiceType(serviceTypeModel.getServiceTypeName(), ShopNameId);
			if (serviceTypeModel1 == null) {
				// ShopDetailsModel shopDetailsModel = new ShopDetailsModel();
				// shopDetailsModel = developerServiceInterface.findShopNameID(ShopNameId);
				serviceTypeModel.setShopId(ShopNameId);
				;
				masterServiceInterface.saveServiceType(serviceTypeModel);
			} else {
				model.addAttribute("errorMsgServiceType", "ServiceType is already added");
			}
			return getAllDataLists(model, ShopNameId);
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	@RequestMapping(value = "SaveServiceType", params = "btnUpdateService", method = RequestMethod.POST)
	public ModelAndView updateServiceType(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Update Service Type");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			int serviceTypeID = Integer.parseInt(request.getParameter("serviceTypeID"));
			String serviceTypeName = request.getParameter("serviceTypeName");
			String serviceType = request.getParameter("Service");
			masterServiceInterface.updateServiceType(serviceTypeID, serviceTypeName, serviceType, ShopNameId);
			return getAllDataLists(model, ShopNameId);
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	@RequestMapping(value = "SaveServiceType", params = "btnDeleteService", method = RequestMethod.POST)
	public ModelAndView deleteSeviceType(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Delete Service Type");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			int serviceTypeID = Integer.parseInt(request.getParameter("serviceTypeID"));
			masterServiceInterface.deleteServiceType(serviceTypeID, ShopNameId);
			return getAllDataLists(model, ShopNameId);
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	//////////////////////////////////////////// serviceTypeName
	//////////////////////////////////////////// END///////////////////////////////////////////

	////////////////////////////////////////////// SaveRateMaster//////////////////////////////////////////////
	@RequestMapping(value = "saveRateMaster", method = RequestMethod.POST, params = "btnAddRate")
	public ModelAndView saveRate(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		int clothTypeID = Integer.parseInt(request.getParameter("clothTypeID"));
		int serviceTypeID = Integer.parseInt(request.getParameter("serviceTypeID"));
		double rate = Double.parseDouble(request.getParameter("rate"));
		if (UsName != null) {
			System.out.println("Save Rate Master");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			RateMasterModel rateMasterModel = new RateMasterModel();
			ClothTypeModel clothTypeModel = new ClothTypeModel();
			clothTypeModel = masterServiceInterface.findClothTypeiD(clothTypeID, ShopNameId);
			rateMasterModel.setClothTypeModel(clothTypeModel);
			ServiceTypeModel serviceTypeModel = new ServiceTypeModel();
			serviceTypeModel = masterServiceInterface.findServiceTypeiD(serviceTypeID, ShopNameId);
			rateMasterModel.setServiceTypeModel(serviceTypeModel);
			System.out.println("Cloth Type "+clothTypeModel.getClothType());
			System.out.println("Service Type "+serviceTypeModel.getServiceType());
			List list = masterServiceInterface.checkForDuplicateRate(clothTypeID, serviceTypeID, ShopNameId);
			
			if(list.isEmpty()) {
				rateMasterModel.setRate(rate);
				rateMasterModel.setShopId(ShopNameId);
				masterServiceInterface.saveRate(rateMasterModel);
			}else {
				model.addAttribute("errorMsgRateMaster", "Rate is already added");
			}
			return getAllDataLists(model, ShopNameId);

		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	@RequestMapping(value = "editRateDetails", params = "btnUpdateRate", method = RequestMethod.POST)
	public ModelAndView updateRate(Model model, HttpServletRequest request, HttpSession httpSession,
			@RequestParam("idd") int RateId, @RequestParam("newRate") double Rate) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Update Rate Master");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			masterServiceInterface.updateRate(RateId, Rate, ShopNameId);
			return getAllDataLists(model, ShopNameId);
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}

	@RequestMapping(value = "editRateDetails", params = "btnDeleteRate", method = RequestMethod.POST)
	public ModelAndView deleteRate(Model model, HttpServletRequest request, HttpSession httpSession,
			@RequestParam("idd") int RateId) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Delete Rate Master");
			int ShopNameId = (int) httpSession.getAttribute("ShopNameId");
			masterServiceInterface.deleteRate(RateId, ShopNameId);
			return getAllDataLists(model, ShopNameId);
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}

	public ModelAndView getAllDataLists(Model model, int ShopNameId) {
		HashMap<String, String> ClothList;
		ClothList = masterServiceInterface.getClothList(ShopNameId);
		model.addAttribute("ClothList", ClothList);
		HashMap<String, String> ServiceList;
		ServiceList = masterServiceInterface.getServiceList(ShopNameId);
		model.addAttribute("ServiceList", ServiceList);
		List<String> RateList;
		RateList = masterServiceInterface.getRateList(ShopNameId);
		model.addAttribute("RateList", RateList);
		List<String> ClothTypeList;
		ClothTypeList = masterServiceInterface.getClothTypeList(ShopNameId);
		model.addAttribute("ClothTypeList", ClothTypeList);
		List<String> ServiceTypeList;
		ServiceTypeList = masterServiceInterface.getServiceTypeList(ShopNameId);
		model.addAttribute("ServiceTypeList", ServiceTypeList);
		System.out.println("Congratulations... Its Working...Update");
		return new ModelAndView("ClothTypeMasterPage");
	}
}
