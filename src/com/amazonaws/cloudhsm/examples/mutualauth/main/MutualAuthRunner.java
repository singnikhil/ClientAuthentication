package com.amazonaws.cloudhsm.examples.mutualauth.main;

import java.io.Console;

import java.security.cert.X509Certificate;

import com.amazonaws.cloudhsm.examples.mutualauth.keystore.ClientCertKeyManager;
import com.amazonaws.cloudhsm.examples.mutualauth.keystore.KeyStoreExample;
import com.amazonaws.cloudhsm.examples.mutualauth.operations.CLIOptions;
import com.amazonaws.cloudhsm.examples.mutualauth.operations.LoginLogoutExample;
import com.amazonaws.cloudhsm.examples.mutualauth.operations.SSLConnection;
import com.amazonaws.cloudhsm.examples.mutualauth.pojo.Arguments;
import com.cavium.key.CaviumRSAPrivateKey;

public class MutualAuthRunner {
	public static void main(String[] args) {
		System.out.println("I Rule!");

		CLIOptions options = new CLIOptions(args);
		Arguments cliArgs = options.parse();

		if(cliArgs.getHsmCryptoUser()!=null) {
			if(cliArgs.getHsmCryptoPassword()!=null)
				LoginLogoutExample.loginWithExplicitCredentials(cliArgs.getHsmCryptoUser(), cliArgs.getHsmCryptoPassword());
			else
			{
				Console console = System.console();
				char[] password = console.readPassword("Please enter your CU password");
				LoginLogoutExample.loginWithExplicitCredentials(cliArgs.getHsmCryptoUser(), new String(password));
			}
		} else {
			LoginLogoutExample.loginWithVariables();
		}

		KeyStoreExample obj = new KeyStoreExample();

		ClientCertKeyManager.setALIAS_NAME(cliArgs.getKeyAlias());
		System.out.println("Getting Private Key");
		CaviumRSAPrivateKey privKey = obj.getPrivateKey(cliArgs.getKeyAlias());
		System.out.println(privKey.getHandle());
		System.out.println("Getting Cert chain");
		X509Certificate[] chain = obj.getCertificate(cliArgs.getCertificateFile());

		if(cliArgs.isVerify()) {
			obj.validateKeyAndCert( privKey, chain); 
		}
		
		
		SSLConnection sslConn = new SSLConnection();
		sslConn.initalizeMutualAuth( privKey, chain); 
		sslConn.connectToUrl(cliArgs.getMutualAuthUrl());

		LoginLogoutExample.logout();
	}
}
