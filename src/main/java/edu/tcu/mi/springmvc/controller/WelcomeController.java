package edu.tcu.mi.springmvc.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.ChatManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.auth.AccessToken;



@Controller
@RequestMapping("/")
public class WelcomeController extends GenericController {
	public static Logger logger = Logger.getLogger(WelcomeController.class);
	private ChatManager chatManager;
	
	public WelcomeController(){
        super.title = "Welcome";
	}
	
	@RequestMapping(value = {"",  "/", "/index/**", "/Index/**"}, method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		Facebook facebook = (Facebook) session.getAttribute("facebook");
		if(facebook == null) {
			return "redirect:" + "/Facebook/login";
		}
		AccessToken accessToken = facebook.getOAuthAccessToken();
		if(accessToken == null) {
			return "redirect:" + "/Facebook/login";
		}
		logger.debug(accessToken);
		logger.info(accessToken);
		
		model.addAttribute("title", title);
		return "welcome/index";
	}
	
	private void xmpp(){
//		Properties prop = new Properties();
//		try {
//			ClassLoader loader = WelcomeController.class.getClassLoader();
//			InputStream is = loader.getResourceAsStream("facebook4j.properties");
//			prop.load(is);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		String App_Id = prop.getProperty("oauth.appId");
//		String chat_host = prop.getProperty("chat.host");
//		String chat_port = prop.getProperty("chat.port");
//		int port = Integer.valueOf(chat_port);
//		
//		XMPPConnection connection;
//		OfflineMessageManager offlineManager;
//		FileTransferManager fileTransferManager;
//		Roster roster;
//		XMPPConnection.DEBUG_ENABLED = true;
//		ConnectionConfiguration config = new ConnectionConfiguration(chat_host, port);
//		config.setSASLAuthenticationEnabled(true);
//		config.setSendPresence(false);
//		config.setRosterLoadedAtLogin(false);
//		connection = new XMPPConnection(config);
//		SASLAuthentication.registerSASLMechanism(SASLXFacebookPlatformMechanism.NAME, SASLXFacebookPlatformMechanism.class);
//		SASLAuthentication.supportSASLMechanism(SASLXFacebookPlatformMechanism.NAME, 0);
//		try {
//			connection.connect();
//			connection.login(App_Id, token);
//		} catch (XMPPException e) {
//			e.printStackTrace();
//		}
//		connection.addConnectionListener(new FbChatConnectionListener());
//		chatManager = connection.getChatManager();
//		chatManager.addChatListener(new FbChatChatManagerListener());
//		roster = connection.getRoster();
//		roster.addRosterListener(new FbChatRosterListener());
	}
}
