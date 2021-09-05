package com.ncs.green;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;
import service.MemberService;
import vo.MemberVO;

@Log4j
@RequestMapping(value = "/member") 	//"/member"로 시작하는 모든 요청을 처리함.
@Controller
public class MemberController2 {
	
	@Autowired
	MemberService service ;
	
	// ** Oracle Test
	@RequestMapping(value = "/listOracle")
	public ModelAndView listOracle(ModelAndView mv) {
		List<MemberVO> list = service.selectListOracle();
		if (list != null) 
			mv.addObject("Banana", list);
		else mv.addObject("message", "~~ 출력할 자료가 한건도 없습니다 ~~") ;
		mv.setViewName("member/memberList");
		return mv;
	} //listOracle

	@RequestMapping(value = "/list")
	public ModelAndView list(ModelAndView mv) {
		List<MemberVO> list = service.selectList();
		if (list != null) 
			mv.addObject("Banana", list);
		else mv.addObject("message", "~~ 출력할 자료가 한건도 없습니다 ~~") ;
		mv.setViewName("member/memberList");
		return mv;
	} //list
	
	@RequestMapping(value = "/memberList3")
	public ModelAndView memberList3(ModelAndView mv) {
		List<MemberVO> list = service.selectList();
		if (list != null) 
			mv.addObject("Banana", list);
		else mv.addObject("message", "~~ 출력할 자료가 한건도 없습니다 ~~") ;
		// mv.setViewName("member/memberList3");
		// => 생략하면 요청명이 viewName으로 전달됨
		return mv;
	} //memberList
	
	



} //class
