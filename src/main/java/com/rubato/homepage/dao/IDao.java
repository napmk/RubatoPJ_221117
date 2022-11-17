package com.rubato.homepage.dao;

import com.rubato.homepage.dto.RMemberDto;

public interface IDao {
	
	//맴버관련
	public void joinMember(String mid, String mpw , String mname, String memail);//insert
	public int checkUserId(String mid); //select
	public int checkUserIdAndPw(String mid,String mpw); //select
	
	//게시판관련
	public void rfbWrite(String rfbname, String rfbtitle, String rfbcontent, String rfbuserid);
	
}
