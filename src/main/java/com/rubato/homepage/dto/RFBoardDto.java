package com.rubato.homepage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RFBoardDto {
	private int rfbnum;
	private String rfbname;
	private String rfbtitle;
	private String rfbcontent;
	private int rfbhit; //조회수
	private String rfbuserid; //글쓴이아이디
	private int rfbreplycount; //첨부된 댓글개수
	private String rfbdate;
	private int filecount;//첨부된 파일개수

}
