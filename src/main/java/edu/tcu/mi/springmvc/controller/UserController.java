package edu.tcu.mi.springmvc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.tcu.mi.springmvc.model.User;
import edu.tcu.mi.springmvc.service.IService;

@Controller
@SessionAttributes(types = User.class)
@RequestMapping("/User")
public class UserController extends GenericController<User> {
	public static Logger logger = Logger.getLogger(UserController.class);
	private IService<User> service;
	private UserDetailsService userDetailsService;
	
	public UserController(){
        super.viewPath = "/user";
        super.routePath = "/User";
        super.title = "User";
	}

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
    }
	
	
	public IService<User> getService() {
		return service;
	}

	@Autowired
	@Qualifier("_GenericServiceImp")
	public void setService(IService<User> service) {
		this.service = service;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@RequestMapping(value = {"",  "/", "/index/", "/index", "/Index/", "/Index"}, method = RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("title", title);
		return viewPath + "/index";
	}
	

    @RequestMapping(value = {"/create/**", "/Create/**"}, method = RequestMethod.GET)
	public String create(Model model) { 
    	User user = new User();
		model.addAttribute("isNew", true);
        model.addAttribute(user);
		model.addAttribute("title", title);
		return viewPath + "/createOrUpdate";
    }
    
    @RequestMapping(value = {"/create/**", "/Create/**"}, method = RequestMethod.POST)
	public String create(User user, Model model, BindingResult result, SessionStatus status) {
		model.addAttribute("title", title);
    	UserDetails _user = userDetailsService.loadUserByUsername(user.getUsername());
    	if(_user != null){
    		result.addError(new ObjectError("duplication", "Database has duplication email : " + _user.getUsername()));
    	}
		if(result.hasErrors()){
			model.addAttribute(user);
			return viewPath + "/createOrUpdate";
    	}else{
        	service.save(user, User.class);
    		status.setComplete();
    		return "redirect:" + routePath + "/index/";
    	}
	}  
    


    @RequestMapping(value = {"/update/{username}/**", "/Update/{username}/**"}, method = RequestMethod.GET)
	public String update(@PathVariable("username") String username, Model model) { 
    	User user = (User) userDetailsService.loadUserByUsername(username);
		model.addAttribute("isNew", false);
        model.addAttribute(user);
		model.addAttribute("title", title);
		return viewPath + "/createOrUpdate";
    }
    
    @RequestMapping(value = {"/update/**", "/Update/**"}, method = RequestMethod.PUT)
	public String update(User user, Model model, BindingResult result, SessionStatus status) {
		model.addAttribute("title", title);
		if(result.hasErrors()){
			model.addAttribute(user);
			return viewPath + "/createOrUpdate";
    	}else{
        	service.save(user, User.class);
    		status.setComplete();
    		return "redirect:" + routePath + "/index/";
    	}
	}  

	@RequestMapping(value = {"/login/**", "/Login/**"}, method = RequestMethod.GET)
	public String login(Model model){
		model.addAttribute("title", title);
		return viewPath + "/login";
	}
	

	@RequestMapping(value = {"/login-error/**", "/Login-error/**"}, method = RequestMethod.GET)
	public String loginError(Model model){    
		model.addAttribute("title", title);
		model.addAttribute("loginError", true);
		return viewPath + "/login";
	}
	
	
	
	
}
