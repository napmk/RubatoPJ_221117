package com.rubato.homepage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rubato.homepage.dao.IDao;
import com.rubato.homepage.dto.RFBoardDto;

@Controller
public class HomeComtroller {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping (value = "index")
	public String index() {
		
		return "index";
	}
	
	@RequestMapping (value = "board_list")
	public String board_list(Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		ArrayList<RFBoardDto> boardDtos = dao.rfbList();
		
		int boardCount = dao.rfboardAllCount();
		
		model.addAttribute("boardList",boardDtos);
		model.addAttribute("boardCount",boardCount);
		
		
		return "board_list";
	}
	
	@RequestMapping(value = "board_write")
	public String board_write(HttpSession session, HttpServletResponse response) {
		String sessionId = (String) session.getAttribute("memberId");
		if(sessionId == null) {//참이면 로그인이 안된 상태
			PrintWriter out;
			try {
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				out.println("<script>alert('로그인하지 않으면 글을 쓰실수 없습니다!');history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
		
		return "board_write";
	}
	
	@RequestMapping (value = "board_view")
	public String board_view(HttpServletRequest request, Model model) {
		String rfbnum = request.getParameter("rfbnum");
		//사용자가 클릭한 글의 번호
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.rfbhit(rfbnum);// 조회수 증가 ★★★순서 중요 
		
		RFBoardDto rfboardDto = dao.rfboardView(rfbnum);
		model.addAttribute("rfbView" , rfboardDto);
		
		return "board_view";
		
		
	}
	
	@RequestMapping (value = "member_join")
	public String member_join() {
		
		return "member_join";
	}
	
	@RequestMapping (value = "joinOk")
	public String joinOk(HttpServletRequest request ,HttpSession session) {
		
		String memberId = request.getParameter("mid");
		String memberPw = request.getParameter("mpw");
		String memberName = request.getParameter("mname");
		String memberEmail = request.getParameter("memail");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.joinMember(memberId, memberPw, memberName, memberEmail);
		
		session.setAttribute("memberId", memberId);// 회원가입후 자동 로그인 기능
		
		
		return "redirect:index";
	}
	
	@RequestMapping (value = "loginOk")
	public String loginOk(HttpServletRequest request, HttpSession session) {
		
		String memberId = request.getParameter("mid");
		String memberPw = request.getParameter("mpw");
		

		IDao dao = sqlSession.getMapper(IDao.class);
		//int checkIdFlag = dao.checkUserId(mid); 
		//select문이니까 고민이 필요함 그래서 int checkIdFlag 로 가져옴.
		int checkIdFlag = dao.checkUserIdAndPw(memberId, memberPw);  // 1이면 로그인 OK , 0이면 로그인x
		
		if (checkIdFlag == 1) {
			session.setAttribute("memberId", memberId);
		}
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "writeOk")
	public String writeOk(HttpServletRequest request, HttpSession session) {
		
		String boardName = request.getParameter("rfbname");
		String boardTitle = request.getParameter("rfbtitle");
		String boardContent = request.getParameter("rfbcontent");
		
		String sessionId = (String) session.getAttribute("memberId");
		//글쓴이의 아이디는 현재 로그인된 유저의 아이디이므로 세션에서 가져와서 전달 
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.rfbWrite(boardName, boardTitle, boardContent, sessionId);
		
		return "redirect:board_list";
	}
	
	@RequestMapping (value = "delete")
	public String delete(HttpServletRequest request) {
		
		String rfbnum = request.getParameter("rfbnum");
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.delete(rfbnum);
		
		
		return "redirect:board_list";
		
	}

}
