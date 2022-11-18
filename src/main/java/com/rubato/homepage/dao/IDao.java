package com.rubato.homepage.dao;

import java.util.ArrayList;

import com.rubato.homepage.dto.RFBoardDto;
import com.rubato.homepage.dto.RMemberDto;
import com.rubato.homepage.dto.RReplyDto;

public interface IDao {
	
	//맴버관련
	public void joinMember(String mid, String mpw , String mname, String memail);//회원가입 insert
	public int checkUserId(String mid); //회원 아이디 찾기 select 
	public int checkUserIdAndPw(String mid,String mpw); //회원 아이디 및 비밀 번호 체크 select
	
	//게시판관련
	public void rfbWrite(String rfbname, String rfbtitle, String rfbcontent, String rfbuserid); //게시판 쓰기 insert 
	public ArrayList<RFBoardDto> rfbList(); // 게시판 리스트 select
	public int rfboardAllCount(); //총 게시물 갯수 select 
	public RFBoardDto rfboardView(String rfbnum); //클릭한 글의 게시물의 내용보기 select
	public void delete(String rfbnum); //글삭제
	public void rfbhit(String rfbnum);//조회수
	
	//댓글관련
	public void rrwrite(String rrorinum,String rrid, String rrcontent);//새 댓글 입력insert
	public ArrayList<RReplyDto> rrlist(String rrorinum);//해당글의 댓글 리스트 select
	public void rrcount(String rrorinum);//댓글 등록시 해당글의 댓글갯수 1증가 
	public void rrdelete(String rrnum);//댓글삭제
	public void rrcountMinus(String rrorinum); //댓글 삭제시 해당글의 댓글갯수 1감소
	
	//게시판 검색 관련
	public ArrayList<RFBoardDto>  rfbSearchTitleList (String searchKey);
	public ArrayList<RFBoardDto>  rfbSearchContentList (String searchKey);
	public ArrayList<RFBoardDto>  rfbSearchWriterList (String searchKey);
	
	
}
