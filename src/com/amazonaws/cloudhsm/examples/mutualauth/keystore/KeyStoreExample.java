package com.amazonaws.cloudhsm.examples.mutualauth.keystore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

import com.amazonaws.cloudhsm.examples.digest.SignatureExample;
import com.cavium.key.CaviumRSAKey;
import com.cavium.key.CaviumRSAPrivateKey;

import java.security.*;
import java.util.Base64;
import java.util.Collection;

public class KeyStoreExample {

	public CaviumRSAPrivateKey getPrivateKey(String alias) {
		CaviumRSAKey rsaKey = null;
		Key rsaPrivateKey = null ;
		try {
			KeyStore ks = KeyStore.getInstance("Cavium","Cavium");
			ks.load(null, null);
			rsaPrivateKey = ks.getKey(alias,null);

			rsaKey= (CaviumRSAPrivateKey)rsaPrivateKey;
			System.out.println(rsaKey.getHandle());
			System.out.println(rsaKey.getSize());
			//System.out.println(Base64.getEncoder().encode(rsaKey.getEncoded()));
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (CaviumRSAPrivateKey) rsaKey; 
	}

	@SuppressWarnings("unchecked")
	public X509Certificate[] getCertificate(String certFile) {
		CertificateFactory certFactory;
		FileInputStream fileInputStream;
		Collection<X509Certificate> certificates = null;

		try {
			certFactory = CertificateFactory.getInstance("X.509");
			fileInputStream = new FileInputStream (certFile);
			certificates = (Collection<X509Certificate>) certFactory.generateCertificates(fileInputStream);
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return certificates.toArray(new X509Certificate[0]);
	}

	public void validateKeyAndCert(CaviumRSAPrivateKey rsaPrivateKey, X509Certificate[] chain) {
		X509Certificate cert = chain[0];
		System.out.println(cert.getSubjectDN());
        RSAPublicKey rsaPublicKey = (RSAPublicKey) cert.getPublicKey();
		SignatureExample obj = new SignatureExample();

        byte[] signature = obj.signMessage("Message to be signed", "NONEwithRSA", (CaviumRSAPrivateKey) rsaPrivateKey);
		System.out.println("Signature : " + Base64.getEncoder().encodeToString(signature));
		boolean isVerificationSuccessful = obj.verifySign("Message to be signed", "NONEwithRSA", rsaPublicKey, signature);
		System.out.println("***************************************");

		System.out.println("isVerificationSuccessful = " + isVerificationSuccessful);

		System.out.println("***************************************");
        
	}
}
