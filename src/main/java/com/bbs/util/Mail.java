package com.bbs.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class Mail extends Authenticator {
		
	protected static String id;
	protected static String pw;
		
	public Mail() {
		id = "ssb4887suk@daum.net";
		pw = "";
	}
		
	protected PasswordAuthentication getPasswordAuthentication() {
	
		return new PasswordAuthentication(id, pw);
	}

}
