package com.bbs.service;

import com.bbs.vo.Authmail;

// service를 구성할 interface 표준 설계 작성
public interface UsersService {
	
	public int idCheck(String user_id) throws Exception;
	public int setAuthnum(String user_mail) throws Exception;
	public int checkAuthnum(Authmail authmail) throws Exception; // boolean도 가능하지만 다양한 실패의 원인을 알려주기 위해 int를 사용한다

}
