package com.bbs.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bbs.dao.UsersDAO;

// homecontroller에서 선언한 service가 여기를 찾아옴
@Service
public class UsersServiceImpl implements UsersService {

	@Inject
	UsersDAO dao;
	
	@Override
	public int idCheck(String user_id) throws Exception {
		
		return 0;
	}

}
