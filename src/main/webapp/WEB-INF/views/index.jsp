<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>클래식 기타 커뮤니티</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/header.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/footer.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" >
</head>
<body>
	<% 
		String sessionId = (String)session.getAttribute("memberId");
		//로그인 중이라면 로그인한 아이디가 저장되고 비로그인 중이라면 sessionId == null 임
		//System.out.println(sessionId);
	
		//if(sessionId == null){
		//	System.out.println("로그인안됨");
		//}else{
		//	System.out.println("로그인 한 아이디:" + sessionId);
		//}
		//System.out.println(sessionId);
	
	%>

    <div id="wrap">
        <header> <!--header 시작-->
            <a href="index"><img id="logo" src="${pageContext.request.contextPath}/resources/image/logo.png" alt="logo"></a>
            <nav id="top_menu">
                <ul>
                    <li>HOME |</li>
                    <% if(sessionId == null) { %>
                    <li>LOGIN |</li>
                    <% } else { %>
                    <li><a href="logout">LOGOUT </a>|</li>
                    <% } %>
                    <% if(sessionId == null) { %>
                    <li>JOIN |</li>
                    <% } else { %>
                    <li>MY INFO |</li>
                    <% } %>
                    <li>NOTICE</li>
                </ul>
             </nav>
            <nav id="main_menu">
                <ul>
                    <li><a href="board_list">자유게시판</a></li>
                    <li><a href="#">기타연주</a></li>
                    <li><a href="#">공동구매</a></li>
                    <li><a href="#">연주회 안내</a></li>
                    <li><a href="#">회원 게시판</a></li>
                </ul>
            </nav>
        </header><!--header 끝-->
        <aside>
            <article id="login_box"><!--login 시작-->
                <img id="login_title" src="${pageContext.request.contextPath}/resources/image/ttl_login.png">
                
                <% if(sessionId == null) { %>
                <form action="loginOk">
	                <div id="input_button">
	                    <ul id="login_input">
	                        <li><input type="text" name="mid"></li>
	                        <li><input type="password" name="mpw"></li>
	                    </ul>
	                    <input type="image" id="login_btn" src="${pageContext.request.contextPath}/resources/image/btn_login.gif">
	                </div>
                </form>
                   <div class="clear"></div>
	               <div id="join_search">
	                    <img src="${pageContext.request.contextPath}/resources/image/btn_join.gif">
	                    <img src="${pageContext.request.contextPath}/resources/image/btn_search.gif">
	               </div>
                <% } else { %>
                <div id="input_button">
	               <ul id="login_input">
	                    <%= sessionId %>님 로그인중입니다.
	               </ul>
	                <br><br>
	                <a href="logout" >로그아웃</a>
	            </div>
                 <% } %>
                
                
                
             
            </article><!--login 끝-->
            <article id="guestbook"><!--guestbook 시작-->
                <div id="guestbook_title">
                    <img src="${pageContext.request.contextPath}/resources/image/ttl_memo.gif">
                </div>
                <ul>
                    <li>안녕하세요</li>
                    <li>안녕하세요</li>
                    <li>안녕하세요</li>
                    <li>안녕하세요</li>
                </ul>
            </article><!--guestbook 끝-->
        </aside>
        <main>
            <section id="main">
                <img src="${pageContext.request.contextPath}/resources/image/main_img.png">
                <section id="notice_free_youtube"><!--공지사항/자유게시판/유튜브시작-->
                    <article id="notice"><!--공지사항-->
                        <div class="latest_title">
                            <img class="latest_img" src="${pageContext.request.contextPath}/resources/image/latest1.gif">
                            <img class="more" src="${pageContext.request.contextPath}/resources/image/more.gif">
                            <div class="clear"></div>
                        </div>
                        <div class="latest_content">
                            <img class="image" src="${pageContext.request.contextPath}/resources/image/book_pen.gif">
                            <ul class="list">
                                <li>
                                    <div class="subject">루바토 개편과 사이트 이용...</div>
                                    <div class="date">2022-09-30</div>
                                    <div class="clear"></div>
                                 </li>
                                <li>
                                    <div class="subject">루바토 개편과 사이트 이용...</div>
                                    <div class="date">2022-09-30</div>
                                    <div class="clear"></div>
                                 </li>
                                <li>
                                    <div class="subject">루바토 개편과 사이트 이용...</div>
                                    <div class="date">2022-09-30</div>
                                    <div class="clear"></div>
                                 </li>
                                <li>
                                    <div class="subject">루바토 개편과 사이트 이용...</div>
                                    <div class="date">2022-09-30</div>
                                    <div class="clear"></div>
                                 </li>
                            </ul>
                        </div>
                        
                    </article><!--공지사항끝-->
                    <article id="freeBoard"><!--자유게시판-->
                         <div class="latest_title">
                            <img class="latest_img" src="${pageContext.request.contextPath}/resources/image/latest2.gif">
                            <img class="more" src="${pageContext.request.contextPath}/resources/image/more.gif">
                            <div class="clear"></div>
                        </div>
                        <div class="latest_content">
                            <img class="image" src="${pageContext.request.contextPath}/resources/image/book_pen.gif">
                            <ul class="list">
                                <li>
                                    <div class="subject">까스통님의 선물인 보드카..</div>
                                    <div class="date">2022-09-30</div>
                                    <div class="clear"></div>
                                 </li>
                                <li>
                                    <div class="subject">까스통님의 선물인 보드카..</div>
                                    <div class="date">2022-09-30</div>
                                    <div class="clear"></div>
                                 </li>
                                <li>
                                    <div class="subject">까스통님의 선물인 보드카..</div>
                                    <div class="date">2022-09-30</div>
                                    <div class="clear"></div>
                                 </li>
                                <li>
                                    <div class="subject">까스통님의 선물인 보드카..</div>
                                    <div class="date">2022-09-30</div>
                                    <div class="clear"></div>
                                 </li>
                            </ul>
                        </div>
                    </article><!--자유게시판 끝-->
                    <article id="youtube"><!--유튜브-->
                        <div class="latest_title">
                            <img class="latest_img" src="${pageContext.request.contextPath}/resources/image/latest3.gif">
                            <img class="more" src="${pageContext.request.contextPath}/resources/image/more.gif">
                            <div class="clear"></div>
                        </div>
                        <img id="youtube_img" src="${pageContext.request.contextPath}/resources/image/bach.jpg">
                        
                    </article><!--유튜브 끝-->
                </section><!--공지사항/자유게시판/유튜브 끝-->
                <section id="gallery">
                    <img src="${pageContext.request.contextPath}/resources/image/latest4.gif">
                    <div id="gallery_box"><!--gallery_BOX 시작-->
                        <div id="gallery_list"><!--gallery_list 시작-->
                            <div class="items">
                                <ul>
                                    <li><img src="${pageContext.request.contextPath}/resources/image/img1.jpg"></li>
                                    <li>기타 페스티벌 4중주</li>
                                </ul>
                            </div>    
                            <div class="items">
                                <ul>
                                    <li><img src="${pageContext.request.contextPath}/resources/image/img1.jpg"></li>
                                    <li>기타 페스티벌 4중주</li>
                                </ul>
                            </div>    
                            <div class="items">
                                <ul>
                                    <li><img src="${pageContext.request.contextPath}/resources/image/img1.jpg"></li>
                                    <li>기타 페스티벌 4중주</li>
                                </ul>
                            </div>    
                        </div>  <!--gallery_list 끝-->                       </div><!--gallery_BOX 시작-->
                </section><!--갤러리끝 -->
            </section>
        </main>
        <div class="clear"></div>
        <footer><!--footer 시작-->
            <img id="footer_logo" src="${pageContext.request.contextPath}/resources/image/logo_footer.gif">
            <ul id="address">
                <li>서울시 강남구 삼성동 1234 우:123-1234</li>
                <li>TEL : 02-1264-1254 EMAIL : robato@naner.com</li>
                <li id="copyright">COPYRIGHT(C) 루바토 ALL RIGHT RESERVED</li>
            </ul>
              <ul id="footer_sns">
                <li><img src="${pageContext.request.contextPath}/resources/image/facebook.gif"></li>
                <li><img src="${pageContext.request.contextPath}/resources/image/blog.gif"></li>
                <li><img src="${pageContext.request.contextPath}/resources/image/twitter.gif"></li>
            </ul>
        </footer><!--footer 끝-->
    </div>
</body>
</html>