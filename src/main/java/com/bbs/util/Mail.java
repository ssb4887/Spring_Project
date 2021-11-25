package com.bbs.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class Mail extends Authenticator {
		
	protected static String id;
	protected static String pw;
		
	public Mail() {
		id = "ssb4887@naver.com";
		pw = "qazwsx57544";
	}
		
	protected PasswordAuthentication getPasswordAuthentication() {
	
		return new PasswordAuthentication(id, pw);
	}

}
