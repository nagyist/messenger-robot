package edu.tcu.mi.xmpp.listener;

import java.io.File;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransferListener;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.FileTransferNegotiator;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;
import org.jivesoftware.smackx.filetransfer.IncomingFileTransfer;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;

public class FbChatFileTransferListener implements FileTransferListener {
	XMPPConnection connection;

	public FbChatFileTransferListener(XMPPConnection con) {
		connection = con;
	}

	public void fileTransferRequest(FileTransferRequest request) {
		System.out.println(request.getFileName());
		System.out.println(request.getFileSize());
		System.out.println(request.getMimeType());
		IncomingFileTransfer accept = request.accept();
		String fileName = request.getFileName();
		String mimeType = request.getMimeType();
		System.out.println("status is:" + accept.getStatus());
		try {
			accept.recieveFile(new File(fileName + "." + mimeType));
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		System.out.println("file recieved");
	}

}
