package com.amazonaws.cloudhsm.examples.mutualauth.operations;

import com.cavium.cfm2.CFM2Exception;
import com.cavium.cfm2.LoginManager;

public class LoginLogoutExample {

	public static void loginWithExplicitCredentials(String cryptoUser, String password) {
		LoginManager lm = LoginManager.getInstance();
		lm.loadNative();
		try {
			lm.login("PARTITION_1", cryptoUser, password);
			int appID = lm.getAppid();
			System.out.println("App ID = " + appID);
			int sessionID = lm.getSessionid();
			System.out.println("Session ID = " + sessionID);
		} catch (CFM2Exception e) {
			e.printStackTrace();
		}
	}

	public static void loginWithVariables() {
		LoginManager lm = LoginManager.getInstance();
		lm.loadNative();
		try {
			lm.login();
			int appID = lm.getAppid();
			System.out.println("App ID = " + appID);
			int sessionID = lm.getSessionid();
			System.out.println("Session ID = " + sessionID);
		} catch (CFM2Exception e) {
			e.printStackTrace();
		}
	}

	public static void logout() {
		try {
			LoginManager.getInstance().logout();
		} catch (CFM2Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}