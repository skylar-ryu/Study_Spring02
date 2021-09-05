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
import service.BoardService;
import vo.BoardVO;

@Log4j
@Controller
public class BoardController {

	@Autowired
	BoardService service;
	
	
	// ** @Log4j Test
	@RequestMapping(value = "/logj")
	public ModelAndView logj(ModelAndView mv, BoardVO vo) {
		log.info("** Log4j Test : Info **");
		log.error("** Log4j Test : error **");
		mv.setViewName("home");
		return mv;
	} // logj
	
	
	// ** 답글달기
	@RequestMapping(value = "/replyf")
	public ModelAndView replyf(ModelAndView mv, BoardVO vo) {
		// => vo 에는 전달된 부모글의 root, step, indent 가 담겨있음 
		// => 매핑메서드의 인자로 정의된 vo 는 request.setAttribute 와 동일 scope
		//    단, 클래스명의 첫글자를 소문자로 ...  ${boardVO.root}
		log.info("** replyf vo : "+vo);
		mv.setViewName("board/replyForm");
		return mv;
	} //replyf 
	
	@RequestMapping(value = "/reply")
	public ModelAndView reply(ModelAndView mv, BoardVO vo, RedirectAttributes rttr) {
		// ** 답글 입력 Service
		// => 성공 : blist
		// => 실패 : 재입력 유도 (replyForm) 
		
		// => 전달된 vo 에 담겨있는 value : id, title, content 
		// => 추가적으로 필요한 value : 부모글의 root, step, indent
		//    root : 부모글의 root와 동일
		//    step: 부모글의 step+1 
		//    (기존 답글의 step 값이 현재 계산된 이 값보다 같거나 큰 값은 +1 : sql 에서 처리) 
		//    indent: 부모글의 indent+1 
		// => 이를 위해 boardDetail 에서 요청시 퀴리스트링으로 전달 -> replyf  
		// => 부모글의 root, step, indent 를 replyForm 에서 hidden으로 처리한 후 
		//    전달된 vo 에는 이 값이 담겨있으므로 step, indent 만 1씩 증가해주면 됨.
		
		vo.setStep(vo.getStep()+1);
		vo.setIndent(vo.getIndent()+1);
		
		if (service.replyInsert(vo) > 0) {
				// 답글 입력 성공
			rttr.addFlashAttribute("message", "~~ 답글 등록 성공 ~~");
		}else { // 답글 입력 실패
			rttr.addFlashAttribute("message", "~~ 답글 등록 실패 ~~");
		}
		mv.setViewName("redirect:blist");
		return mv;
	} //reply 
	
	// **************************************
	// Board CRUD
	@RequestMapping(value = "/blist")
	public ModelAndView blist(ModelAndView mv) {

		List<BoardVO> list = service.selectList() ;
		if (list != null) {
			mv.addObject("Banana", list);
		}else {
			mv.addObject("message", "~~ 출력할 자료가 없습니다 ~~");
		}
		mv.setViewName("board/boardList");
		return mv;
	} //blist

	@RequestMapping(value = "/bdetail")
	public ModelAndView bdetail(HttpServletRequest request, ModelAndView mv, 
			BoardVO vo, RedirectAttributes rttr) {
		// ** Detail 처리 조건
		// => 로그인 했을때만 글내용을 볼 수 있도록 ( boardList.jsp 에서 처리 ) 
		// => 조회수 증가 
		//    글쓴이(Parameter 로 전달) 와 글보는이(loginID) 가 달라야 함.

		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("loginID") != null) {
			String loginID = (String)session.getAttribute("loginID");
			// 글쓴이(Parameter 로 전달) 와 글보는이(loginID) 가 다른경우에만 조회수 증가
			if (!loginID.equals(vo.getId())) {
				service.countUp(vo) ;
			} 
			// 글내용 조회
			vo = service.selectOne(vo);
			if (vo != null) {
				request.setAttribute("Apple", vo);
				// 글 수정 하기의 경우 
				if ("U".equals(request.getParameter("jcode"))) {
					mv.setViewName("board/bupdateForm");
				}else mv.setViewName("board/boardDetail");  
			}else {
				rttr.addFlashAttribute("message", "~~ 글번호에 해당하는 글을 찾을 수 없습니다 ~~");
				mv.setViewName("redirect:blist"); 
			}
		}else {
			mv.addObject("message", "~~ 로그인 정보가 없습니다 !! 로그인 후 다시 하세요  ~~");
			mv.setViewName("member/loginForm"); 
		}
		return mv;
	} //bdetail

	// ** 새글등록
	@RequestMapping(value = "/binsertf")
	public ModelAndView binsertf(ModelAndView mv) {
		mv.setViewName("board/binsertForm");
		return mv;
	} //binsertf
	
	@RequestMapping(value = "/binsert")
	public ModelAndView binsert(ModelAndView mv, BoardVO vo, RedirectAttributes rttr) {

		if (service.insert(vo) > 0) {
			rttr.addFlashAttribute("message", "~~ 새글 등록 성공 ~~");
			mv.setViewName("redirect:blist"); 
		}else {
			mv.addObject("message", "~~ 새글 등록 실패 !! 다시 하세요 ~~");
			mv.setViewName("board/binsertForm");
		}
		return mv;
	} //binsert	

	@RequestMapping(value = "/bupdate")
	public ModelAndView bupdate(ModelAndView mv, BoardVO vo, RedirectAttributes rttr) {
		if (service.update(vo) > 0) {
			rttr.addFlashAttribute("message", "~~ 글수정 성공 ~~");
			mv.setViewName("redirect:blist");
		}else {
			rttr.addFlashAttribute("message", "~~ 글수정 실패 !!! 다시 하세요 ~~");
			mv.setViewName("redirect:bdetail?seq="+vo.getSeq()+"&jcode=U");
		}
		return mv;
	} //bupdate	
	
	@RequestMapping(value = "/bdelete")
	public ModelAndView bdelete(ModelAndView mv, BoardVO vo, RedirectAttributes rttr) {
		if (service.delete(vo) > 0) {
			rttr.addFlashAttribute("message", "~~ 글삭제 성공 ~~");
			mv.setViewName("redirect:blist");
		}else {
			rttr.addFlashAttribute("message", "~~ 글삭제 실패 !!! 다시 하세요 ~~");
			mv.setViewName("redirect:bdetail?seq="+vo.getSeq());
		}
		return mv;
	} //bdelete	

} // class
