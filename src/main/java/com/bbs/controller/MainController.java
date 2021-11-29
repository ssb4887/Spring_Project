package com.bbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbs.service.UsersService;
import com.bbs.vo.Authmail;
import com.bbs.vo.Users;

@Controller
public class MainController {
	
	// 인터페이스를 자동으로 생성해줌 @Inject
	@Inject  
	UsersService service;
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	// 객체 자동 생성
	@Inject
	UsersService usersService;
	
	// url 패턴이 'path/' 일 경우
	@RequestMapping(value = "/", method = RequestMethod.GET)   //    value = "/"에서 /은 URL이다
	public String main(Model model) throws Exception {
		
		model.addAttribute("msg", "반갑습니다."); // 앞에서하는 작업과 뒤에서 하는 작업의 분리
		
		return "main/main";  //views 폴더에서 보여줄 file or path(만약 main이라는 폴더안에 main 파일이 있다면 main/main을 적어준다
	}
	
	// url 패턴이 'path/join' 일 경우
	@RequestMapping(value = "/join", method = RequestMethod.GET)  // url   
	public String join(Model model) throws Exception {
		
		
		return "main/join";  // 파일 경로
	}
	
	// url 패턴이 'path/login' 일 경우
	@RequestMapping(value = "/login", method = RequestMethod.GET)   
	public String login(Model model) throws Exception {
		
		
		return "main/login";  
	}
	
	// url 패턴이 'path/idCheck' 일 경우
	// http://localhost:8081/idCeck?user_id=qwer
	@RequestMapping(value = "/idCheck", method = RequestMethod.GET)
	// 반환값을 페이지에 직첩 출력
	@ResponseBody 
	public String idCheck(String user_id) throws Exception {
		
		int result = usersService.idCheck(user_id);
		
		return result + "";  // String str = Integer.toString(idCheck)가 정석이지만 번거롭고 길다.
	}
	
	// url 패턴이 'path/sendAuthMail'일 경우
	@RequestMapping(value = "/sendAuthMail", method = RequestMethod.GET)
	@ResponseBody
	public String sendAuthMail(String user_mail) throws Exception {
		
		int result = usersService.setAuthnum(user_mail);
		
		return result +"";
	}
	
	// url 패턴이 'path/mailAuth'일 경우
	@RequestMapping(value = "/mailAuth", method = RequestMethod.POST)
	@ResponseBody
	public String mailAuth(Authmail authmail) throws Exception{
		
		
		return usersService.checkAuthnum(authmail) + ""; 
	}
	
	// url 패턴이 'path/joinAction'일 경우
	@RequestMapping(value = "/joinAction", method = RequestMethod.POST) //@ResponseBody를 안적는 이유는 결과값이 필요하지 않기에 그저 회원가입후 로그인페이지로 이동
	public String joinAction(Users users, String addr1, String addr2, String addr3) throws Exception {
		
		users.setUser_addr(addr1 + " " + addr2 + " " + addr3);
		usersService.joinAction(users);
		
		
		return "redirect:/login"; // redirect:/login  = http://localhost:8081/login
	}
	
	@RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	public String loginAction(Users users, HttpSession session, RedirectAttributes ra) throws Exception {
		
		int result = usersService.loginAction(users);
		String url = null;
		
		if(result == 0) {
			session.setAttribute("user_id", users.getUser_id());
			url = "redirect:/";
			// 페이지 이동 -> localhost:8081/
			
		} else {
			// 메세지 전달 (로그인 정보가 잘못됐습니다.)
			// 페이지 이동 -> localhost:8081/login
			ra.addFlashAttribute("msg", "로그인 정보가 일치하지 않습니다.");
			url = "redirect:/login";
		}
		
		return url;
	}
}



















