package edu.tcu.mi.xmpp.listener;

import java.util.Collection;
import java.util.Iterator;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;

public class FbChatChatManagerListener implements ChatManagerListener {

	public void chatCreated(Chat chat, boolean createdLocally) {
		System.out.println(chat.getThreadID());
		System.out.println(chat.getParticipant());
		System.out.println(createdLocally);
		Collection<MessageListener> listeners = chat.getListeners();
		Iterator<MessageListener> iterator = listeners.iterator();
		while(iterator.hasNext()){
			MessageListener next = iterator.next();
			System.out.println(next.toString());
		}
		if (!createdLocally && listeners.isEmpty()) {
			System.out.println("It's not createdLocally");
			chat.addMessageListener(new FbChatMessageListener());
		}
	}

}
