package com.amazonaws.cloudhsm.examples.mutualauth.keystore;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;

public class ClientCertKeyManager extends X509ExtendedKeyManager {

	private static String ALIAS_NAME;
	private final PrivateKey privKey;
	private final X509Certificate[] chain;

	public ClientCertKeyManager(PrivateKey privKey, X509Certificate[] chain) {
		this.privKey = privKey;
		this.chain = chain;
	}

	@Override
	public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
		return ALIAS_NAME;
	}

	@Override
	public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
		return null;
	}

	@Override
	public X509Certificate[] getCertificateChain(String alias) {
		return alias.equals(ALIAS_NAME) ? chain.clone() : null;
	}

	@Override
	public String[] getClientAliases(String keyType, Principal[] issuers) {
		return new String[] { ALIAS_NAME };
	}

	@Override
	public PrivateKey getPrivateKey(String alias) {
		return alias.equals(ALIAS_NAME) ? privKey : null;
	}

	@Override
	public String[] getServerAliases(String arg0, Principal[] arg1) {
		return null;
	}

	@Override
	public String chooseEngineClientAlias(String[] keyType, Principal[] issuers, SSLEngine engine) {
		return ALIAS_NAME;
	}

	@Override
	public String chooseEngineServerAlias(String keyType, Principal[] issuers, SSLEngine engine) {
		return null;
	}

	public static void setALIAS_NAME(String aLIAS_NAME) {
		ALIAS_NAME = aLIAS_NAME;
	}
}