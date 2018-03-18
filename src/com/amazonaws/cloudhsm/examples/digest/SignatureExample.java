package com.amazonaws.cloudhsm.examples.digest;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPublicKey;

import com.cavium.key.CaviumRSAPrivateKey;

public class SignatureExample{

	public byte[] signMessage(String message, String signingAlgo, CaviumRSAPrivateKey privateKey) {
		try {
			Signature sig = Signature.getInstance(signingAlgo, "Cavium");
			sig.initSign(privateKey);
			sig.update(message.getBytes()); 
			byte[] signature = null;
			signature = sig.sign();
			return signature;

		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean verifySign(String message, String signingAlgo, RSAPublicKey publicKey, byte[] signature) {
		try {
			Signature sig = Signature.getInstance(signingAlgo, "Cavium");
			sig.initVerify(publicKey);
			sig.update(message.getBytes());
			boolean isVerificationSuccessful = sig.verify(signature);
			return isVerificationSuccessful;
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}