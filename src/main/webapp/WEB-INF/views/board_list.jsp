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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/board_list_main.css" >
</head>
<body>
    <div id="wrap">
        <header> <!--header 시작-->
            <a href="index"><img id="logo" src="${pageContext.request.contextPath}/resources/image/logo.png" alt="logo"></a>
            <nav id="top_menu">
                <ul>
                    <li>HOME |</li>
                    <li>LOGIN |</li>
                    <li>JOIN |</li>
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
                <div id="input_button">
                    <ul id="login_input">
                        <li><input type="text"></li>
                        <li><input type="password"></li>
                    </ul>
                    <img id="login_btn" src="${pageContext.request.contextPath}/resources/image/btn_login.gif">
                </div>
                <div class="clear"></div>
                <div id="join_search">
                    <img src="${pageContext.request.contextPath}/resources/image/btn_join.gif">
                    <img src="${pageContext.request.contextPath}/resources/image/btn_search.gif">
                 </div>
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
                <h2 id="board_title">자유게시판</h2>
                <div id="total_search">
                    <div id="total">▷ 총 5개의 게시물이 있습니다.</div>
                    <div id="search">
                        <div id="select_img">
                            <img src="${pageContext.request.contextPath}/resources/image/select_search.gif">
                        </div>
                        <div id="search_select">
                            <select>
                                <option>제목</option>
                                <option>내용</option>
                                <option>글쓴이</option>
                            </select>
                        </div>
                        <div id="select_input"><input type="text"></div>
                        <div id="select_btn">
                            <img src="${pageContext.request.contextPath}/resources/image/search_button.gif">
                        </div>
                      </div>
                </div><!--total search 끝-->
                <table>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>일시</th>
                        <th>조회수</th>
                    </tr>
                    <tr>
                        <td class="col1">1</td>
                        <td class="col2">
                            <a href="board_view">까스통님의 선물인 보드카가 정말 독하네요!!</a>
                        </td>
                        <td class="col3">루바토 </td>
                        <td class="col4">2022-09-30 </td>
                        <td class="col5">13</td>
                    </tr>
                    <tr>
                        <td class="col1">2</td>
                        <td class="col2">
                            <a href="board_view">까스통님의 선물인 보드카가 정말 독하네요!!</a>
                        </td>
                        <td class="col3">루바토 </td>
                        <td class="col4">2022-09-30 </td>
                        <td class="col5">13</td>
                    </tr>
                    <tr>
                        <td class="col1">3</td>
                        <td class="col2">
                            <a href="board_view">까스통님의 선물인 보드카가 정말 독하네요!!</a>
                        </td>
                        <td class="col3">루바토 </td>
                        <td class="col4">2022-09-30 </td>
                        <td class="col5">13</td>
                    </tr>
                </table><!--게시판 목록 테이블 끝-->
                <div id="buttons">
                    <div class="col1">◀ 이전 1 다음 ▶</div>
                    <div class="col2">
                        <img src="${pageContext.request.contextPath}/resources/image/list.png">
                        <a href="board_write"><img src="${pageContext.request.contextPath}/resources/image/write.png"></a>
                    </div>
                </div>
                
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