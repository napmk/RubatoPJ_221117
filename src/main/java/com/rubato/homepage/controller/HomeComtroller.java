package com.rubato.homepage.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.rubato.homepage.dao.IDao;
import com.rubato.homepage.dto.FileDto;
import com.rubato.homepage.dto.RFBoardDto;
import com.rubato.homepage.dto.RReplyDto;

@Controller
public class HomeComtroller {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping (value = "/")
	public String home() {
		
		return "redirect:index";
	}
	
	
	@RequestMapping (value = "index")
	public String index(Model model) {
			
		IDao dao = sqlSession.getMapper(IDao.class);
		
		List<RFBoardDto> boardDtos = dao.rfbList(); //전체 글 리스트 불러오기	
		
		int boardSize =boardDtos.size(); //전체 글 개수
		
		if(boardSize > 4) {
			boardDtos = boardDtos.subList(0 , 4);
		}
		 //전체글의 개수가 4개보다 작을때 발생하는 인덱스 에러 방지
		

		
//		boardDtos.get(0); //가장 최근 글 첫번째
//		boardDtos.get(1); //가장 최근 글 두번째
//		boardDtos.get(2); //가장 최근 글 세번째
//		boardDtos.get(3); //가장 최근 글 네번째
		
		
//		
//		model.addAttribute("freeboard01", boardDtos.get(0));
//		model.addAttribute("freeboard02", boardDtos.get(1));
//		model.addAttribute("freeboard03", boardDtos.get(2));
//		model.addAttribute("freeboard04", boardDtos.get(3));
		
		model.addAttribute("latestDtos", boardDtos);
		
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
		ArrayList<RReplyDto> replyDtos= dao.rrlist(rfbnum);
		
		FileDto fileDto =dao.getFileInfo(rfbnum);
		
		model.addAttribute("rfbView" , rfboardDto);
		model.addAttribute("replylist" ,replyDtos); // 해당글에 달린 댓글 리스트
		model.addAttribute("fileDto" , fileDto);// 해당글에 첨부된 파일의 모든 정보 Dto
		
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
	public String writeOk(HttpServletRequest request, HttpSession session, @RequestPart MultipartFile files) throws IllegalStateException, IOException {
		
		String boardName = request.getParameter("rfbname");
		String boardTitle = request.getParameter("rfbtitle");
		String boardContent = request.getParameter("rfbcontent");
		
		
		String sessionId = (String) session.getAttribute("memberId");
		//글쓴이의 아이디는 현재 로그인된 유저의 아이디이므로 세션에서 가져와서 전달 
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		if(files.isEmpty()) { //파일의 첨부여부 확인
			dao.rfbWrite(boardName, boardTitle, boardContent, sessionId , 0);
		} else {
			dao.rfbWrite(boardName, boardTitle, boardContent, sessionId , 1);
			 ArrayList<RFBoardDto> latestBoard = dao.boardLatestInfo(sessionId);
			 RFBoardDto dto = latestBoard.get(0); //방금쓴글 다음 가장 최근글 가져오기
			int rfbnum = dto.getRfbnum();
			 
			 
			//파일첨부 
			String fileoriname = files.getOriginalFilename(); //첨부된 파일의 원래 이름
			String fileextension = FilenameUtils.getExtension(fileoriname).toLowerCase();  //toLowerCase 소문자 바꿔주는 메소드
			//아까 불러온 라이브러리중 확장자 추출후 소문자로 강제 변경
			File destinationFile; // import java.io로 하세요
			String destinationFileName; //실제 서버에 저장된 파일의 변경된 이름이 저장될 변수 선언
			String fileurl = "C:/springBootWork/rubatoPJ_221117/src/main/resources/static/uploadfiles/" ;
			//첨부된 파일이 저장될 서버의 실제 폴더 경로
			
			do {
			destinationFileName =  RandomStringUtils.randomAlphanumeric(32) + "." + fileextension;//랜덤파일 만들어주는것 숫자는 파일이름길이
			//알파벳 대소문자와 숫자를 포함한 랜덤 32자 문자열 생성 후 .을 구분자로 원본파일의 확장자를 연결 -> 실제 서버에 저장될 파일의 이름
			destinationFile = new File(fileurl+destinationFileName);
			} while (destinationFile.exists()); //혹시 같은이름의 파일이름이 존재하는지 확인
			
			
			destinationFile.getParentFile().mkdir();
			files.transferTo(destinationFile); // 에러나면 add throws 처음꺼 처리해줌
			//업로드된 파일이 지정한 폴더로 이동완료!
			
			dao.fileInfoInsert(rfbnum, fileoriname, destinationFileName, fileextension, fileurl);
		}
	
		return "redirect:board_list";
	}
	

	
	@RequestMapping (value = "delete")
	public String delete(HttpServletRequest request) {
		
		String rfbnum = request.getParameter("rfbnum");
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.delete(rfbnum);
		
		
		return "redirect:board_list";
		
	}
	

	
	@RequestMapping (value = "replyOk")
	public String replyOk(HttpServletRequest request, HttpSession session, Model model, HttpServletResponse response) {
		
		String rrorinum = request.getParameter("rfbnum"); //댓글이 달린 원래 글의 번호
		String rrcontent  = request.getParameter("rrcontent");//댓글내용
		
		String sessionId = (String)session.getAttribute("memberId"); //현재 로그인한 유저의 아이디
		
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
			
		} else {
			
			IDao dao = sqlSession.getMapper(IDao.class);
			dao.rrwrite(rrorinum, sessionId, rrcontent); //댓글쓰기
			dao.rrcount(rrorinum); //해당글 댓글 총 개수 증가
			
			RFBoardDto rfboardDto = dao.rfboardView(rrorinum);
			ArrayList<RReplyDto> replyDtos= dao.rrlist(rrorinum);
			
			model.addAttribute("rfbView" , rfboardDto);// 원글의 게시글 내용전부
			model.addAttribute("replylist" ,replyDtos); // 해당글에 달린 댓글 리스트
			
		}
		
		return "board_view";
	}
		@RequestMapping (value = "replyDelete")
		public String replyDelete(HttpServletRequest request, Model model,HttpSession session,HttpServletResponse response) {
			
			
			String rrnum = request.getParameter("rrnum"); //댓글의 고유 번호
			String rrorinum = request.getParameter("rfbnum"); //댓글이 달린 원글의 고유 번호
			
			String sessionId = (String)session.getAttribute("memberId"); //현재 로그인한 유저의 아이디
			
			if(sessionId == null) {//참이면 로그인이 안된 상태
				PrintWriter out;
				try {
					response.setContentType("text/html;charset=utf-8");
					out = response.getWriter();
					out.println("<script>alert('로그인하지 않으면 댓글을 삭제 하실수 없습니다!');history.go(-1);</script>");
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
			
			
			IDao dao = sqlSession.getMapper(IDao.class);
			dao.rrdelete(rrnum); //댓글삭제
			dao.rrcountMinus(rrorinum); // 해당글 댓글카운트하니씩 삭제
			
			RFBoardDto rfboardDto = dao.rfboardView(rrorinum);
			ArrayList<RReplyDto> replyDtos= dao.rrlist(rrorinum);
			
			model.addAttribute("rfbView" , rfboardDto);// 원글의 게시글 내용전부
			model.addAttribute("replylist" ,replyDtos); // 해당글에 달린 댓글 리스트
			
			}
			
			return "board_view";
		}
		
		@RequestMapping (value = "search_list")
		public String search_list(HttpServletRequest request, Model model) {
			
		
			String searchOption = request.getParameter("searchOption"); 
			// title , content, writer  3개중 한개의 값을 저장
			String searchKey = request.getParameter("searchKey"); 
			//제목/내용/글쓴이 에 포함된 검색 키워드
			
			IDao dao = sqlSession.getMapper(IDao.class);
			
			ArrayList<RFBoardDto> boardDtos =null; // if문을 위한 선언해주기
			
			if(searchOption.equals("title")) {
				boardDtos = dao.rfbSearchTitleList(searchKey);
			} else if (searchOption.equals("content")){
				boardDtos = dao.rfbSearchContentList(searchKey);
			} else if (searchOption.equals("writer")){
				boardDtos = dao.rfbSearchWriterList(searchKey);
			} 
			
			
			model.addAttribute("boardList",boardDtos);
			model.addAttribute("boardCount",boardDtos.size());//검색결과 게시물의 개수
			
			return "board_list";
		}
		
		@RequestMapping(value = "file_down")
		public String file_down(HttpServletRequest request, Model model, HttpServletResponse response) {

			String rfbnum = request.getParameter("rfbnum");//파일이 첨부된 원글 번호

			IDao dao = sqlSession.getMapper(IDao.class);

			FileDto fileDto = dao.getFileInfo(rfbnum);

			String filename = fileDto.getFilename();

			PrintWriter out;
			try {
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				out.println("<script>window.location.href='/resources/uploadfiles/" + filename + "'</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return "redirect:board_list";
		}

		
}
