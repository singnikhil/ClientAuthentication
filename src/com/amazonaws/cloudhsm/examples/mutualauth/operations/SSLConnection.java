package com.amazonaws.cloudhsm.examples.mutualauth.operations;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import com.amazonaws.cloudhsm.examples.mutualauth.keystore.ClientCertKeyManager;
import com.cavium.key.CaviumRSAPrivateKey;

public class SSLConnection {

	public void initalizeMutualAuth(CaviumRSAPrivateKey privKey, X509Certificate[] chain) {
		SSLContext sslctx;
		try {
			sslctx = SSLContext.getInstance("TLSv1.2");
			ClientCertKeyManager keyManager = new ClientCertKeyManager(privKey, chain);
			sslctx.init(new KeyManager[] { keyManager }, null, new SecureRandom());
			SSLSocketFactory ssf = sslctx.getSocketFactory();
			HttpsURLConnection.setDefaultSSLSocketFactory(ssf);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connectToUrl(String mutualAuthUrl) {
		System.out.println("Creating Https Connection!");
		URL url;
		try {
			url = new URL(mutualAuthUrl);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(is);
			int read = inputStreamReader.read();
			while (	read!=-1) {
				char character = (char) read;
				System.out.print(character);
				read = inputStreamReader.read();
			} 
			inputStreamReader.close();
			is.close();
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
