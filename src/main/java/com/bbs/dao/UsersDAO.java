package com.bbs.dao;

import com.bbs.vo.Authmail;
import com.bbs.vo.Users;

// DAO는 dataAccess다 
public interface UsersDAO {
	
	public String idCheck(String user_id) throws Exception; // select라서 반환값이 존재하기에 string으로 선언
	public Integer getAuthnum(String user_mail) throws Exception;
	public void setAuthnum(Authmail authmail) throws Exception;  // insert로 반환값이 존재하지 않기에 void 해줌  
	public void resetAuthnum(Authmail authmail) throws Exception;
	public void deleteAuthmail(String user_id) throws Exception;
	public void join(Users users) throws Exception;

}
