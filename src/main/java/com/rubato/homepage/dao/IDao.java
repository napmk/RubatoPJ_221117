package com.rubato.homepage.dao;

import com.rubato.homepage.dto.RMemberDto;

public interface IDao {
	public void joinMember(String mid, String mpw , String mname, String memail);//insert
	public int checkUserId(String mid); //select
	public int checkUserIdAndPw(String mid,String mpw); //select
	
}
