package edu.tcu.mi.springmvc.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.tcu.mi.springmvc.model.User;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

@Controller
@SessionAttributes(types = User.class)
@RequestMapping("/Facebook")
public class FacebookController {
	public static Logger logger = Logger.getLogger(FacebookController.class);
	private Facebook facebook;
	
	public FacebookController(){
	}

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
    }
	

	@RequestMapping(value = {"/login/**", "/Login/**"}, method = RequestMethod.GET)
	public String login(Model model){
		facebook = new FacebookFactory().getInstance();
		Properties prop = new Properties();
		try {
			ClassLoader loader = FacebookController.class.getClassLoader();
			InputStream is = loader.getResourceAsStream("facebook4j.properties");
			prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String callbackURL = prop.getProperty("oauth.callbackURL");
		String url = facebook.getOAuthAuthorizationURL(callbackURL);
		return "redirect:" + url;
	}
	

	@RequestMapping(value = {"/callback/**"}, method = RequestMethod.GET)
	public String callback(@RequestParam(value="code", required=false) String code, HttpServletRequest request, Model model){
    	try {
    		HttpSession session = request.getSession();
			if(facebook != null){
				AccessToken token = facebook.getOAuthAccessToken(code);
				session.setAttribute("facebook", facebook);
			}
    		else {
    			logger.error("facebook object is null");
    		}
		} catch (FacebookException e) {
			e.printStackTrace();
		}
		return "redirect:" + "/";
	}
	
}
