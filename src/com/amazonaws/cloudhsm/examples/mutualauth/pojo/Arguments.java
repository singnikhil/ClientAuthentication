package com.amazonaws.cloudhsm.examples.mutualauth.pojo;

public class Arguments {
	private String certificateFile;
	private String hsmKeyAlias;
	private String mutualAuthUrl;
	private String hsmCryptoUser;
	private String hsmCryptoPassword;
	private boolean verify = false;
	
	public String getHsmCryptoUser() {
		return hsmCryptoUser;
	}
	public void setHsmCryptoUser(String hsmCryptoUser) {
		this.hsmCryptoUser = hsmCryptoUser;
	}
	public String getHsmCryptoPassword() {
		return hsmCryptoPassword;
	}
	public void setHsmCryptoPassword(String hsmCryptoPassword) {
		this.hsmCryptoPassword = hsmCryptoPassword;
	}
	public String getCertificateFile() {
		return certificateFile;
	}
	public void setCertificateFile(String certificateFile) {
		this.certificateFile = certificateFile;
	}
	public String getKeyAlias() {
		return hsmKeyAlias;
	}
	public void setKeyAlias(String hsmKeyAlias) {
		this.hsmKeyAlias = hsmKeyAlias;
	}
	public String getMutualAuthUrl() {
		return mutualAuthUrl;
	}
	public void setMutualAuthUrl(String mutualAuthUrl) {
		this.mutualAuthUrl = mutualAuthUrl;
	}
	public boolean isVerify() {
		return verify;
	}
	public void setVerify(boolean verify) {
		this.verify = verify;
	}
	
}
