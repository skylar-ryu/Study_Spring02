package com.ncs.green;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import service.MemberService;
import vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service ;

	@RequestMapping(value = "/mlist")
	public ModelAndView mlist(ModelAndView mv) {

		List<MemberVO> list = service.selectList();
		if (list != null) {
			mv.addObject("Banana", list);
		}else {
			mv.addObject("message", "~~ 출력할 자료가 한건도 없습니다 ~~") ;
		}
		mv.setViewName("member/memberList");
		return mv;
	} //mlist

	// ** Loginf	
	@RequestMapping(value = "/loginf")
	public ModelAndView loginf(ModelAndView mv) {
		mv.setViewName("member/loginForm");
		return mv;
	} //loginf
	// ** Login
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, ModelAndView mv, MemberVO vo) {

		String password = vo.getPassword();
		// => 입력값의 오류에 대한 확인은 UI 에서 JavaScript로 처리
		vo = service.selectOne(vo);
		if (vo != null) {
			if (vo.getPassword().equals(password)) {
				// 로그인 성공 : 로그인정보 session에 보관,  home으로
				request.getSession().setAttribute("loginID",vo.getId());
				request.getSession().setAttribute("loginName",vo.getName());
				mv.setViewName("redirect:home");
			}else {
				// password 오류 : message , 재로그인 유도 (loginForm 으로)
				mv.addObject("message"," password 오류 !! 다시 하세요 ~~");
				mv.setViewName("member/loginForm");
			}
		}else {
			// ID 오류
			mv.addObject("message"," ID 오류 !! 다시 하세요 ~~");
			mv.setViewName("member/loginForm");
		}
		return mv;
	} //login

	// ** redirect 시 message 처리하기
	// => RedirectAttributes
	//   - addFlashAttribute("message",message)
	//     -> 세션을 통해 url에 표시되지 않게 보낼 수 있으면서, 일회성 임.
	//        session 에 보관되므로 url에 붙지않기 때문에 깨끗하고 f5(새로고침)에 영향을 주지않음 
	//        즉, home 에서 다시 request 처리하지않아도 됨
	//     -> 비교 : mv.addObject("message", message) 하고, 
	//              redirect 하면 message 내용이 url 에 붙어 전달됨
	//   - addAttribute("message",message)
	//     -> url 에 붙어전달됨
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request, ModelAndView mv, RedirectAttributes rttr) {
		// 존재하는 session 확인 후 삭제
		HttpSession session = request.getSession(false);
		if (session!=null) {
			session.invalidate();
		}
		//mv.addObject("message", "~~ 로그아웃 !!! ~~~");
		rttr.addFlashAttribute("message", "~~ 로그아웃 !!! ~~~");
		mv.setViewName("redirect:home");
		return mv;
	} //logout
	// => viewName 을 지정하지 않으면 요청명으로 찾음 ( /WEB-INF/views/logout.jsp 을 찾음 )

	@RequestMapping(value = "/mdetail")
	public ModelAndView mdetail(HttpServletRequest request, ModelAndView mv, MemberVO vo) {

		HttpSession session = request.getSession(false);
		if (session!=null && session.getAttribute("loginID") !=null) {
			vo.setId((String)session.getAttribute("loginID"));

			if  (request.getParameter("id")!=null) vo.setId(request.getParameter("id"));

			vo=service.selectOne(vo);
			if (vo!=null) {
				mv.addObject("Apple", vo);
				// updateForm 요청인지 확인 
				if ("U".equals(request.getParameter("jcode"))) 
					mv.setViewName("member/updateForm");
				else mv.setViewName("member/memberDetail");
			}else {
				mv.addObject("message","~~ 정보를 찾을 수 없습니다, 로그인 후 이용하세요 ~~");
				mv.setViewName("member/loginForm");
			}
		}else {
			// 로그인 정보 없음
			mv.addObject("message","~~ 로그인 정보 없습니다, 로그인 후 이용하세요 ~~");
			mv.setViewName("member/loginForm");
		}
		return mv;
	} //mdetail

	// ** Joinf
	@RequestMapping(value = "/joinf")
	public ModelAndView joinf(ModelAndView mv) {
		mv.setViewName("member/joinForm");
		return mv;
	} //joinf

	// ** Join
	@RequestMapping(value = "/join")
	public ModelAndView join(ModelAndView mv, MemberVO vo) {

		if (service.insert(vo) > 0) {
			// Join 성공 -> 로그인 유도
			mv.addObject("message", "~~ 회원가입 완료, 로그인 하세요 ~~");
			mv.setViewName("member/loginForm");
		}else {
			// Join 실패 -> 재가입 유도
			mv.addObject("message", "~~ 회원가입 오류, 다시 하세요 ~~");
			mv.setViewName("member/joinForm");
		}
		return mv;
	} //join

	// ** Update
	@RequestMapping(value = "/mupdate")
	public ModelAndView mupdate(ModelAndView mv, MemberVO vo, RedirectAttributes rttr) {

		if (service.update(vo) > 0) {
			// Update 성공 -> mList
			rttr.addFlashAttribute("message", "~~ 정보 수정 성공 ~~");
			mv.setViewName("redirect:mlist");
		}else {
			// Update 실패 -> 재수정 할 수 있도록 유도
			rttr.addFlashAttribute("message", "~~ 정보수정 오류, 다시 하세요 ~~");
			mv.setViewName("redirect:mdetail?id="+vo.getId()+"&jcode=U");
		}
		return mv;
	} //mupdate

	// ** Member Delete : 회원탈퇴
	@RequestMapping(value = "/mdelete")
	public ModelAndView mdelete(HttpServletRequest request, ModelAndView mv, MemberVO vo,
							RedirectAttributes rttr) {

		// => 삭제 대상 -> vo 에 set
		HttpSession session = request.getSession(false);
		String loginID = (String)session.getAttribute("loginID");

		if (session!=null && loginID!=null) {
			// ** 삭제 가능 => message, home.jsp
			// => 삭제대상 확인, session 삭제여부 확인 
			vo.setId(loginID);
			if (service.delete(vo) > 0) {
				// 삭제성공
				session.invalidate();  // session 삭제 
				rttr.addFlashAttribute("message", "~~ 회원탈퇴 성공 !!  1개월후 재가입 가능 합니다 ~~");
				mv.setViewName("redirect:home");
				// mv.setViewName("redirect:"); 허용되지않음
			}else { // 삭제실패
				rttr.addFlashAttribute("message", "~~ 회원탈퇴 오류 !!  다시 하세요 ~~");
				mv.setViewName("redirect:mdetail?id="+vo.getId());
			}
		}else {
			// 탈퇴 불가능 => message, loginForm.jsp 
			mv.addObject("message", "~~ 탈퇴 불가능 !!  로그인후 하세요 ~~");
			mv.setViewName("member/loginForm");
		}
		return mv;
	} //mdelete


} //class
