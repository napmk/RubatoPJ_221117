package com.rubato.homepage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rubato.homepage.dao.IDao;

@Controller
public class HomeComtroller {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping (value = "index")
	public String index() {
		
		return "index";
	}
	
	@RequestMapping (value = "board_list")
	public String board_list() {
		
		return "board_list";
	}
	
	@RequestMapping (value = "board_write")
	public String board_write() {
		
		return "board_write";
	}
	
	@RequestMapping (value = "board_view")
	public String board_view() {
		
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
	public String writeOk(HttpServletRequest request) {
		
		String rfbname = request.getParameter("rfbname");
		String rfbtitle = request.getParameter("rfbtitle");
		String rfbcontent = request.getParameter("rfbcontent");
		String rfbuserid = request.getParameter("rfbuserid");
		
		IDao dao =sqlSession.getMapper(IDao.class);
		dao.rfbWrite(rfbname, rfbtitle, rfbcontent, rfbuserid);
		
		return "redirect:list";
	}

}
