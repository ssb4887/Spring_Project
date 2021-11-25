package com.bbs.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bbs.vo.Authmail;

@Repository
public class UsersDAOImlp implements UsersDAO {

	@Inject
	SqlSession sqlSession;
	
	final String SESSION = "com.bbs.mappers.bbs";
	
	@Override
	public String idCheck(String user_id) throws Exception {
	
		return sqlSession.selectOne(SESSION + ".idCheck", user_id);
	}

	@Override
	public Integer getAuthnum(String user_mail) throws Exception {
		
		return sqlSession.selectOne(SESSION + ".getAuthnum", user_mail);
	}
	
	@Override
	public void setAuthnum(Authmail authmail) throws Exception {
		sqlSession.insert(SESSION + ".setAuthnum", authmail);
	}


	@Override
	public void resetAuthnum(Authmail authmail) throws Exception {
		sqlSession.update(SESSION + ".resetAuthnum", authmail);
		
	}


}
