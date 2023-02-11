package com.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.service.MemberService;

import lombok.RequiredArgsConstructor;
//import com.java.member.naver.NaverLoginManager;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class Membercontroller {
	
	private final MemberService memberService;
	
	//@Autowired
	//private NaverLoginManager naverLoginManager;
	
	
			
	// 카카오, 이메일 성공시
	@RequestMapping(value="mailLoginOk.go", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView memberMailLoginOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		memberService.memberMailLoginOk(mav);
		return mav;
	}
	
	// 마이페이지
		@RequestMapping(value="myPage.go", method=RequestMethod.GET)
		public ModelAndView memberMyPage(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView mav=new ModelAndView();
			mav.addObject("request", request);
			memberService.memberMypage(mav);
			return mav;
		}
		
	// 메인하면 가면서 세션 다 끊기
	@GetMapping("main.go")
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
//		HttpSession session=request.getSession();
//		request.getSession().invalidate();
		
		return "/member/main.tiles";
	}
	// 로그아웃
	@GetMapping("logout.go")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/member/logout.tiles";
	}



}




