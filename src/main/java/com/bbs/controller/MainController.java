package com.bbs.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bbs.service.UsersService;

@Controller
public class MainController {
	
	// 인터페이스를 자동으로 생성해줌 @Inject
	@Inject  
	UsersService service;
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)   //    value = "/"에서 /은 URL이다
	public String home(Locale locale, Model model) {
		
		
		return "main/main";  //views 폴더에서 보여줄 file or path(만약 main이라는 폴더안에 main 파일이 있다면 main/main을 적어준다
	}
	
}
