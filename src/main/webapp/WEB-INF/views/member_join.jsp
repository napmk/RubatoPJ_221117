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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/board_left.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/board_write_main.css" >
</head>
<body>
	<% 
		String sessionId = (String)session.getAttribute("memberId");
	
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
                    <li><a href="member_join">JOIN |</a></li>
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
	                    <a href="member_join"><img src="${pageContext.request.contextPath}/resources/image/btn_join.gif"></a>
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
            <nav id="sub_menu"><!--서브메뉴 시작-->
                <ul>
                    <li><a href="board_list">+ 자유게시판</a></li>
                    <li><a href="#">+ 방명록</a></li>
                    <li><a href="#">+ 공지사항</a></li>
                    <li><a href="#">+ 등업요청</a></li>
                    <li><a href="#">+ 포토갤러리</a></li>
                </ul>
            </nav><!--서브메뉴 끝-->
            <article id="sub_banner">
                <ul>
                    <li><img src="${pageContext.request.contextPath}/resources/image/banner1.png"></li>
                    <li><img src="${pageContext.request.contextPath}/resources/image/banner2.png"></li>
                    <li><img src="${pageContext.request.contextPath}/resources/image/banner3.png"></li>
                </ul>
            </article>
        </aside>
        <main>
            <section id="main">
                <img src="${pageContext.request.contextPath}/resources/image/comm.gif">
                <h2 id="board_title">루바토 회원 가입</h2>
                <div id="write_title"><h2>회원 정보 입력</h2></div>
                <form action="joinOk">
	                <table>
	                    <tr class="joinip">
	                        <td class="col1">아이디</td>
	                        <td class="col2"><input type="text" name="mid"></td>
	                    </tr>
	                    <tr class="joinip">
	                        <td class="col1">비밀번호</td>
	                        <td class="col2"><input type="text" name="mpw"></td>
	                    </tr>
	                    <tr class="joinip">
	                        <td class="col1">이름</td>
	                        <td class="col2"><input type="text" name="mname"></td>
	                    </tr>
	                    <tr class="joinip">
	                        <td class="col1">이메일</td>
	                        <td class="col2"><input type="text" name="memail"></td>
	                    </tr>
	                </table>
	                 <div id="buttons">
	                   <input type="image" src="${pageContext.request.contextPath}/resources/image/ok.png">
	                    <a href="board_list"><img src="${pageContext.request.contextPath}/resources/image/list.png"></a>
	                </div>
                </form>
            </section><!--section main 끝-->
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