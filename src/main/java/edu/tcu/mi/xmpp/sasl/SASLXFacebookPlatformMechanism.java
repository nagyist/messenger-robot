package edu.tcu.mi.xmpp.sasl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.Sasl;

import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.sasl.SASLMechanism;

public class SASLXFacebookPlatformMechanism extends SASLMechanism {
	public static final String NAME = "X-FACEBOOK-PLATFORM";

	public SASLXFacebookPlatformMechanism(SASLAuthentication saslAuthentication) {
		super(saslAuthentication);
	}

	private String apiKey = "";

	private String accessToken = "";

	protected void authenticate() throws IOException, XMPPException {
		AuthMechanism stanza = new AuthMechanism(getName(), null);
		getSASLAuthentication().send(stanza);
	}

	@Override
	public void authenticate(String apiKey, String host, String accessToken)
			throws IOException, XMPPException {
		if (apiKey == null || accessToken == null) {
			throw new IllegalStateException("Invalid parameters!");
		}

		this.apiKey = apiKey;
		this.accessToken = accessToken;
		this.hostname = host;

		String[] mechanisms = { "DIGEST-MD5" };
		Map<String, String> props = new HashMap<String, String>();
		this.sc = Sasl.createSaslClient(mechanisms, null, "xmpp", host, props,
				this);
		authenticate();
	}

	@Override
	public void authenticate(String username, String host, CallbackHandler cbh)
			throws IOException, XMPPException {
		String[] mechanisms = { "DIGEST-MD5" };
		Map<String, String> props = new HashMap<String, String>();
		this.sc = Sasl.createSaslClient(mechanisms, null, "xmpp", host, props,
				cbh);
		authenticate();
	}

	@Override
	protected String getName() {
		return NAME;
	}

	@Override
	public void challengeReceived(String challenge) throws IOException {
		byte response[] = null;
		if (challenge != null) {
			String decodedResponse = new String(
					org.jivesoftware.smack.util.Base64.decode(challenge));
			Map<String, String> parameters = getQueryMap(decodedResponse);

			String version = "1.0";
			String nonce = parameters.get("nonce");
			String method = parameters.get("method");

			Long callId = Long.valueOf(System.currentTimeMillis());

			String composedResponse = String
					.format("method=%s&nonce=%s&access_token=%s&api_key=%s&call_id=%s&v=%s",
							URLEncoder.encode(method, "UTF-8"),
							URLEncoder.encode(nonce, "UTF-8"),
							URLEncoder.encode(this.accessToken, "UTF-8"),
							URLEncoder.encode(this.apiKey, "UTF-8"), callId,
							URLEncoder.encode(version, "UTF-8"));
			response = composedResponse.getBytes();
		}

		String authenticationText = "";

		if (response != null) {
			authenticationText = org.jivesoftware.smack.util.Base64
					.encodeBytes(response,
							org.jivesoftware.smack.util.Base64.DONT_BREAK_LINES);
		}

		Response stanza = new Response(authenticationText);

		getSASLAuthentication().send(stanza);
	}

	private Map<String, String> getQueryMap(String query) {
		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, value);
		}
		return map;
	}
}