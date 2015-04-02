package edu.tcu.mi.xmpp.listener;

import java.util.Collection;

import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;

public class FbChatRosterListener implements RosterListener {

	public void entriesAdded(Collection<String> addresses) {

	}

	public void entriesUpdated(Collection<String> addresses) {

	}

	public void entriesDeleted(Collection<String> addresses) {

	}

	public void presenceChanged(Presence presence) {
        System.out.println("Presence changed: " + presence.getFrom() + " " + presence);
	}

}
