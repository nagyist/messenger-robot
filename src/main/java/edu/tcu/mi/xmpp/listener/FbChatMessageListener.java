package edu.tcu.mi.xmpp.listener;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class FbChatMessageListener implements MessageListener {
	public void processMessage(Chat chat, Message message) {
		System.out.println("XML : " + message.toXML());
		System.out.println("Type : " + message.getType());
		System.out.println("Body : " + message.getBody());
		System.out.println("=========================");
//		String participant = chat.getParticipant();
//		Message msg = new Message(participant, Message.Type.chat);
//		msg.setBody("I'm Fine");
//		try {
//			chat.sendMessage(msg);
//		} catch (XMPPException e) {
//			e.printStackTrace();
//		}
	}

}
